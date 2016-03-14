package mx.gob.inr.catservicios

import grails.plugins.springsecurity.Secured
import org.springframework.dao.DataIntegrityViolationException

@Secured(['ROLE_SAST_APROBADOR', 'ROLE_SAST_TECNICO', 'ROLE_SAST_GESTOR'])
class ProblemaController {
  def springSecurityService
    static nombreMenu = "Problemas"
    static ordenMenu = -90

    static allowedMethods = [save: "POST", update: "POST", x_delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        // si es de DGAIT, solo mostrar problemas de Bitacoras
        def areaLab = message(code: "area.de.laboratorio")
        def userID = springSecurityService.principal.id
        def userArea = UsuarioAutorizado.get(userID)?.area
        def lista = []
        if (userArea == areaLab) {
          lista = Problema.findAllByFuente('Bitacora', params)
        } else {
          lista = Problema.list(params)
        }
        [problemaInstanceList: lista, problemaInstanceTotal: lista.size()]
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

        [problemaInstance: problemaInstance]
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
                render(view: "edit", model: [problemaInstance: problemaInstance])
                return
            }
        }

        problemaInstance.properties = params

        if (!problemaInstance.save(flush: true)) {
            render(view: "edit", model: [problemaInstance: problemaInstance])
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
