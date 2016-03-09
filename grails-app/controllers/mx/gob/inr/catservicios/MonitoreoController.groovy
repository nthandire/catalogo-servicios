package mx.gob.inr.catservicios

import grails.plugins.springsecurity.Secured
import org.springframework.dao.DataIntegrityViolationException
import groovy.time.TimeCategory

@Secured(['ROLE_SAST_BITACORAS'])
class MonitoreoController {
    def springSecurityService
    static nombreMenu = "Monitoreos"
    static ordenMenu = 33

    static allowedMethods = [save: "POST", update: "POST", x_delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        params.sort = "id"
        params.order = "desc"
        def hoy = new Date()
        def haceUnMes = hoy.clone()
        use(TimeCategory) {
          haceUnMes -= 1.months
        }
        def lista = Monitoreo.findAllByFechaBetween(haceUnMes, hoy, params)
        [monitoreoInstanceList: lista, monitoreoInstanceTotal: Monitoreo.count()]
    }

    def create() {
        [monitoreoInstance: new Monitoreo(params)]
    }

    def save() {
        def monitoreoInstance = new Monitoreo(params)
        monitoreoInstance.fecha = new Date()
        monitoreoInstance.estado = 'A' as char
        monitoreoInstance.idUsuario = springSecurityService.principal.id
        monitoreoInstance.ipTerminal = request.getRemoteAddr()

        def startDate = new Date().clearTime()
        startDate[Calendar.MONTH] = 0
        startDate[Calendar.DATE] = 1
        log.debug("startDate = $startDate")
        def endDate = startDate.clone()
        use(TimeCategory) {
            endDate = endDate + 1.years - 1.seconds
        }
        log.debug("endDate = $endDate")

        Long maxID = Monitoreo.withCriteria { // TODO: un test para ver si este algoritmo sique funcionando
          between("fecha", startDate, endDate)
          projections {
            max "numeroMonitoreo"
          }
        }[0] ?: 0
        log.debug("maxID = $maxID")

        monitoreoInstance.numeroMonitoreo = ++maxID

        if (!monitoreoInstance.save(flush: true)) {
            render(view: "create", model: [monitoreoInstance: monitoreoInstance])
            return
        }

        // salvar los detalles
        monitoreoInstance.bitacora.detalles.sort{it.id}.each {
          def det = new MonitoreoDetalle()
          det.estado = 0
          det.monitoreo = monitoreoInstance
          det.bitacoradetalle = it
          if (!det.save(flush: true)) {
            flash.error = "Error al grabar detalles, revisar el programa y la BD."
            render(view: "create", model: [monitoreoInstance: monitoreoInstance])
            return
          }
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'monitoreo.label', default: 'Monitoreo'), monitoreoInstance.toString()])
        redirect(action: "edit", id: monitoreoInstance.id)
    }

    def show(Long id) {
        def monitoreoInstance = Monitoreo.get(id)
        if (!monitoreoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'monitoreo.label', default: 'Monitoreo'), id])
            redirect(action: "list")
            return
        }

        [monitoreoInstance: monitoreoInstance]
    }

    def edit(Long id) {
        def monitoreoInstance = Monitoreo.get(id)
        if (!monitoreoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'monitoreo.label', default: 'Monitoreo'), id])
            redirect(action: "list")
            return
        }

        [monitoreoInstance: monitoreoInstance]
    }

    def update(Long id, Long version) {
      def monitoreoInstance = null
      def regresoBrusco = null
      (monitoreoInstance, regresoBrusco) = _update(id, version)
      if (regresoBrusco) {
        return
      }

      flash.message = message(code: 'default.updated.message', args: [message(code: 'monitoreo.label', default: 'Monitoreo'), monitoreoInstance.toString()])
      redirect(action: "show", id: monitoreoInstance.id)
    }

    def problema(Long id, Long version) {
      def monitoreoInstance = null
      def regresoBrusco = null
      (monitoreoInstance, regresoBrusco) = _update(id, version)
      if (regresoBrusco) {
        return
      }

      //Guardarlo como un problema
      def problema = new Problema()

      problema.fuente = "Bitacora"
      problema.idFuente = monitoreoInstance.id

        // Asignarle el siguiente folio dentro del año
        // TODO: Pasarlo a un servicio
      def startDate = new Date().clearTime()
      startDate[Calendar.MONTH] = 0
      startDate[Calendar.DATE] = 1
      def endDate = startDate.clone()
      use(TimeCategory) {
        endDate = endDate + 1.years - 1.seconds
      }
      log.debug("startDate = $startDate, endDate = $endDate")

      def maxID = Problema.withCriteria { // TODO: un test para ver si este algoritmo sique funcionando
        between("fechaProblema", startDate, endDate)
        projections {
          max "folio"
        }
      }[0] ?: 0
      log.debug("maxID = $maxID")
      problema.folio = ++maxID
      problema.fechaProblema = new Date()
      problema.observaciones = "¿que deberia ir aqui?, Desarrollo debe opinar"
      problema.idUsuario = springSecurityService.principal.id
      problema.ipTerminal = request.getRemoteAddr()

      if (!problema.save(flush: true)) {
          render(view: "edit", model: [monitoreoInstance: monitoreoInstance])
          return
      }

      flash.message = "Guardado y generado un problema relacionado"
      redirect(action: "show", id: monitoreoInstance.id)
    }

    def _update(Long id, Long version) {
      log.debug("params = $params")

      def regresoBrusco = false
      def monitoreoInstance = Monitoreo.get(id)
      if (!monitoreoInstance) {
          regresoBrusco = true
          flash.message = message(code: 'default.not.found.message', args: [message(code: 'monitoreo.label', default: 'Monitoreo'), id])
          redirect(action: "list")
          return
      }

      if (version != null) {
          if (monitoreoInstance.version > version) {
            regresoBrusco = true
            monitoreoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                      [message(code: 'monitoreo.label', default: 'Monitoreo')] as Object[],
                      "Another user has updated this Monitoreo while you were editing")
            render(view: "edit", model: [monitoreoInstance: monitoreoInstance])
            return
          }
      }

      monitoreoInstance.properties = params
      monitoreoInstance.ipTerminal = request.getRemoteAddr()

      if (!monitoreoInstance.save(flush: true)) {
        regresoBrusco = true
        render(view: "edit", model: [monitoreoInstance: monitoreoInstance])
        return
      }

      monitoreoInstance.detalles.each {
        def estado = 0
        if (params["det[${it.id}]"]) {
          estado = 1
        }
        it.estado = estado
        def nota = ""
        if (params["observ[${it.id}]"]) {
          nota = params["observ[${it.id}]"]
        }
        it.observaciones = nota
        if (!it.save(flush: true)) {
          regresoBrusco = true
          flash.error = "Error al grabar detalles (2), revisar el programa y la BD."
          render(view: "edit", model: [monitoreoInstance: monitoreoInstance])
          return
        }
      }

      [monitoreoInstance, regresoBrusco]
    }

}
