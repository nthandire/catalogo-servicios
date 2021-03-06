package mx.gob.inr.catservicios

import grails.plugins.springsecurity.Secured
import org.springframework.dao.DataIntegrityViolationException

@Secured(['ROLE_SAST_APROBADOR'])
class ProblemaController {
  def springSecurityService
    static nombreMenu = "Problemas"
    static ordenMenu = 90

    static allowedMethods = [save: "POST", update: "POST", x_delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 100, 100)

      def problemaInstanceList = Problema.list()
      def problemaInstanceTotal = problemaInstanceList.size()
      switch (params['sort']) {
        case null:
          params['order'] = 'desc'
        case "idFuente":
          log.debug("idFuente")
          problemaInstanceList.sort{it.paraOrdenar()}
        break
        case "fuente":
          log.debug("fuente")
          problemaInstanceList.sort{it.fuente}
        break
        case "fechaProblema":
          log.debug("fechaProblema")
          problemaInstanceList.sort{it?.fechaProblema}
        break
        case "folio":
          log.debug("folio")
          problemaInstanceList.sort{it.folio}
        break
        case "observaciones":
          log.debug("observaciones")
          problemaInstanceList.sort{it.observaciones?.toUpperCase()}
        break
        case "solucion":
          log.debug("solucion")
          problemaInstanceList.sort{it.solucion?.toUpperCase()}
        break
      }
      log.debug("problemaInstanceList = ${problemaInstanceList}")

      if (params['order'] == 'desc') {
        problemaInstanceList = problemaInstanceList.reverse()
      }
      log.debug("problemaInstanceList = ${problemaInstanceList}")

      def paramMax = (params['max']?:'0').toInteger()
      def paramOffset = (params['offset']?:'0').toInteger()
      problemaInstanceList = problemaInstanceList.size() ?
        problemaInstanceList[paramOffset..
          Math.min(paramOffset+paramMax-1, problemaInstanceList.size()-1)] :
        []
      log.debug("problemaInstanceList = ${problemaInstanceList}")
      [problemaInstanceList: problemaInstanceList,
        problemaInstanceTotal: problemaInstanceTotal]
    }

    def create() {
        [problemaInstance: new Problema(params)]
    }

    def save() {
        def problemaInstance = new Problema(params)
        if (!problemaInstance.save(flush: true)) {
            render(view: "create", model: [problemaInstance: problemaInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'problema.label', default: 'Problema'), problemaInstance.toString()])
        redirect(action: "show", id: problemaInstance.id)
    }

    def show(Long id) {
      def problemaInstance = Problema.get(id)
      if (!problemaInstance) {
          flash.message = message(code: 'default.not.found.message', args: [message(code: 'problema.label', default: 'Problema'), id])
          redirect(action: "list")
          return
      }

      def controlador = problemaInstance.fuente
      def accion = "show"
      switch (problemaInstance.fuente) {
        case "Bitacora":
          controlador = "Bitacora"
          break
      }

      [problemaInstance: problemaInstance, controlador: controlador]
    }

    def edit(Long id) {
        def problemaInstance = Problema.get(id)
        if (!problemaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'problema.label', default: 'Problema'), id])
            redirect(action: "list")
            return
        }

        [problemaInstance: problemaInstance]
    }

    def cerrar(Long id) {
        def problemaInstance = Problema.get(id)
        if (!problemaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'problema.label', default: 'Problema'), id])
            redirect(action: "list")
            return
        }

        [problemaInstance: problemaInstance]
    }

    def update(Long id, Long version) {
        def problemaInstance = Problema.get(id)
        if (!problemaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'problema.label', default: 'Problema'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (problemaInstance.version > version) {
                problemaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'problema.label', default: 'Problema')] as Object[],
                          "Another user has updated this Problema while you were editing")
                render(view: "cerrar", model: [problemaInstance: problemaInstance])
                return
            }
        }

        problemaInstance.properties = params
        def userID = springSecurityService.principal.id
        def firmaTeclada = params['passwordfirma']
        //log.debug("firmaTeclada = $firmaTeclada")
        def firma = Firmadigital.findById(userID)?.passwordfirma?.reverse()
        //log.debug("firma= $firma")
        if (firmaTeclada != firma) {
          flash.error = "Error en contaseña"
          render(view: "cerrar", model: [problemaInstance: problemaInstance])
          return
        }

        problemaInstance.fechaSolucion = new Date()

        if (!problemaInstance.save(flush: true)) {
            render(view: "cerrar", model: [problemaInstance: problemaInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'problema.label', default: 'Problema'), problemaInstance.toString()])
        redirect(action: "show", id: problemaInstance.id)
    }

    def x_delete(Long id) {
        def problemaInstance = Problema.get(id)
        if (!problemaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'problema.label', default: 'Problema'), id])
            redirect(action: "list")
            return
        }

        try {
            problemaInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'problema.label', default: 'Problema'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'problema.label', default: 'Problema'), id])
            redirect(action: "show", id: id)
        }
    }
}
