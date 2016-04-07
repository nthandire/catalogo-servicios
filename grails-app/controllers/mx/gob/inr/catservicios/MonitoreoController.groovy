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
        def hoy = new Date()
        def hace4Meses = hoy.clone()
        use(TimeCategory) {
          hace4Meses -= 4.months
        }
        log.debug("hace4Meses = $hace4Meses")
        def lista = Monitoreo.findAllByFechaBetween(hace4Meses, hoy)
        def cuantos = Monitoreo.countByFechaBetween(hace4Meses, hoy)

        if (params.sort == "numeroMonitoreo") {
          lista.sort{it.numeroMonitoreo}
        } else if (params.sort == "estado") {
          lista.sort{it.estado}
        } else if (params.sort == "semaforo") {
          lista.sort{it.semaforo}
        } else if (params.sort == "fecha") {
          lista.sort{it.fecha}
        } else {
          lista.sort{-it.id}
        }
        if (params.order == "desc") {
          lista = lista.reverse()
        }

        def offset = params.offset ?: 0

        [monitoreoInstanceList: lista[offset..Math.min(offset+params.max-1,lista.size()-1)],
          monitoreoInstanceTotal: cuantos]
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
      (monitoreoInstance, regresoBrusco) = _update(id, version, 'A' as char)
      if (regresoBrusco) {
        return
      }

      flash.message = message(code: 'default.updated.message', args: [message(code: 'monitoreo.label', default: 'Monitoreo'), monitoreoInstance.toString()])
      redirect(action: "show", id: monitoreoInstance.id)
    }

    def problema(Long id, Long version) {

      def userID = springSecurityService.principal.id
      def firmaTeclada = params['passwordfirma']
      def firma = Firmadigital.findById(userID)?.passwordfirma
      def monitoreoInstance = null
      if (firmaTeclada != firma) {
        flash.error = "Error en contaseña"
        monitoreoInstance = Monitoreo.get(id)
        monitoreoInstance.properties = params
        render(view: "edit", model: [monitoreoInstance: monitoreoInstance])
        return
      }

      def regresoBrusco = null
      (monitoreoInstance, regresoBrusco) = _update(id, version, 'P' as char)
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
      problema.observaciones = params["observaciones"]
      problema.idUsuario = springSecurityService.principal.id
      problema.ipTerminal = request.getRemoteAddr()

      if (!problema.save(flush: true)) {
          render(view: "edit", model: [monitoreoInstance: monitoreoInstance])
          return
      }

      flash.message = "Guardado y generado un problema relacionado"
      redirect(action: "show", id: monitoreoInstance.id)
    }

    def _update(Long id, Long version, Character estadoParam) {
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
      monitoreoInstance.estado = estadoParam

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

    def createArchivo() {
      log.debug("params = $params")
      def monitoreoArchivoadjuntoInstance = new MonitoreoArchivoadjunto()
      monitoreoArchivoadjuntoInstance.idMonitoreo =
        (params.monitoreo['id']).toInteger()
      log.debug("idMonitoreo = ${monitoreoArchivoadjuntoInstance.idMonitoreo}")
      [monitoreoArchivoadjuntoInstance: monitoreoArchivoadjuntoInstance]
    }

    def saveArchivo() {
      log.debug("params = $params")
      def file = request.getFile('file')
      if(file.empty) {
        flash.message = "Debe enviar algún archivo"
        render(view: "createArchivo")
        return
      } else {
        def monitoreoArchivoadjuntoInstance = new MonitoreoArchivoadjunto()
        monitoreoArchivoadjuntoInstance.idMonitoreo =
          (params.idMonitoreo).toInteger()
        def nombre = file.originalFilename
        monitoreoArchivoadjuntoInstance.nombre = nombre
        monitoreoArchivoadjuntoInstance.datos = file.getBytes()
        monitoreoArchivoadjuntoInstance.tamanio =
          monitoreoArchivoadjuntoInstance.datos.size()

        if (monitoreoArchivoadjuntoInstance.tamanio > 5242880) {
          flash.error = "No puede subir archivos de más de 5 MB"
          render(view: "createArchivo", model: [monitoreoArchivoadjuntoInstance:
            monitoreoArchivoadjuntoInstance])
          return
        }

        monitoreoArchivoadjuntoInstance.idUsuario = springSecurityService.principal.id
        monitoreoArchivoadjuntoInstance.ipTerminal = request.getRemoteAddr()
        def dot = nombre.lastIndexOf('.');
        if (dot > 0)
          monitoreoArchivoadjuntoInstance.tipo = nombre.substring(dot + 1).
            toUpperCase()
        else
          monitoreoArchivoadjuntoInstance.tipo = ""
        if (!monitoreoArchivoadjuntoInstance.save(flush: true)) {
          render(view: "createArchivo", model: [monitoreoArchivoadjuntoInstance:
            monitoreoArchivoadjuntoInstance])
            return
        }
        redirect (action:'edit', id: params.idMonitoreo)
      }
    }

    def download(long id) {
        MonitoreoArchivoadjunto monitoreoArchivoadjuntoInstance =
          MonitoreoArchivoadjunto.get(id)
        if ( MonitoreoArchivoadjunto == null) {
            flash.message = "Documento no encontrado."
            redirect (controller: "incidente", action:'list')
        } else {
            response.setContentType("APPLICATION/OCTET-STREAM")
            response.setHeader("Content-Disposition",
              "Attachment;Filename=\"${monitoreoArchivoadjuntoInstance.nombre}\"")

            def outputStream = response.getOutputStream()
            outputStream << monitoreoArchivoadjuntoInstance.datos
            outputStream.flush()
            outputStream.close()
        }
    }

}
