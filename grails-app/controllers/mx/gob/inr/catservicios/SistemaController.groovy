package mx.gob.inr.catservicios

import grails.plugins.springsecurity.Secured
import org.springframework.dao.DataIntegrityViolationException

@Secured(['ROLE_SAST_BITACORAS'])
class SistemaController {
    static nombreMenu = "Listado de Sistemas"
    static ordenMenu = 94

    static allowedMethods = [save: "POST", update: "POST", x_delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [bitacoraInstanceList: Sistema.list(params), bitacoraInstanceTotal: Sistema.count()]
    }

    def create() {
        log.debug("Entré a create")
        [bitacoraInstance: new Sistema(params)]
    }

    def save() {
        log.debug("Entré a save")
        def bitacoraInstance = new Sistema(params)
        if (!bitacoraInstance.validate()) {
          render(view: "create", model: [bitacoraInstance: bitacoraInstance])
          return
        }
        if (!bitacoraInstance.save(flush: true)) {
            render(view: "create", model: [bitacoraInstance: bitacoraInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'bitacora.label', default: 'Sistema'), bitacoraInstance.toString()])
        redirect(action: "show", id: bitacoraInstance.id)
    }

    def show(Long id) {
        def bitacoraInstance = Sistema.get(id)
        if (!bitacoraInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'bitacora.label', default: 'Sistema'), id])
            redirect(action: "list")
            return
        }

        [bitacoraInstance: bitacoraInstance]
    }

    def edit(Long id) {
        def bitacoraInstance = Sistema.get(id)
        if (!bitacoraInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'bitacora.label', default: 'Sistema'), id])
            redirect(action: "list")
            return
        }

        [bitacoraInstance: bitacoraInstance]
    }

    def update(Long id, Long version) {
        def bitacoraInstance = Sistema.get(id)
        if (!bitacoraInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'bitacora.label', default: 'Sistema'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (bitacoraInstance.version > version) {
                bitacoraInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'bitacora.label', default: 'Sistema')] as Object[],
                          "Another user has updated this Sistema while you were editing")
                render(view: "edit", model: [bitacoraInstance: bitacoraInstance])
                return
            }
        }

        bitacoraInstance.properties = params

        if (!bitacoraInstance.save(flush: true)) {
            render(view: "edit", model: [bitacoraInstance: bitacoraInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'bitacora.label', default: 'Sistema'), bitacoraInstance.toString()])
        redirect(action: "show", id: bitacoraInstance.id)
    }

}
