package mx.gob.inr.catservicios

import grails.plugins.springsecurity.Secured
import org.springframework.dao.DataIntegrityViolationException

@Secured(['ROLE_SAST_ADMIN'])
class CatSistemaController {
    static nombreMenu = "Sistemas"
    static ordenMenu = 102

    static allowedMethods = [save: "POST", update: "POST", x_delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [catSistemaInstanceList: CatSistema.list(params), catSistemaInstanceTotal: CatSistema.count()]
    }

    def create() {
        [catSistemaInstance: new CatSistema(params)]
    }

    def save() {
        def catSistemaInstance = new CatSistema(params)
        if (!catSistemaInstance.save(flush: true)) {
            render(view: "create", model: [catSistemaInstance: catSistemaInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'catSistema.label', default: 'CatSistema'), catSistemaInstance.toString()])
        redirect(action: "show", id: catSistemaInstance.id)
    }

    def show(Long id) {
        def catSistemaInstance = CatSistema.get(id)
        if (!catSistemaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'catSistema.label', default: 'CatSistema'), id])
            redirect(action: "list")
            return
        }

        [catSistemaInstance: catSistemaInstance]
    }

    def edit(Long id) {
        def catSistemaInstance = CatSistema.get(id)
        if (!catSistemaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'catSistema.label', default: 'CatSistema'), id])
            redirect(action: "list")
            return
        }

        [catSistemaInstance: catSistemaInstance]
    }

    def update(Long id, Long version) {
        def catSistemaInstance = CatSistema.get(id)
        if (!catSistemaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'catSistema.label', default: 'CatSistema'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (catSistemaInstance.version > version) {
                catSistemaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'catSistema.label', default: 'CatSistema')] as Object[],
                          "Another user has updated this CatSistema while you were editing")
                render(view: "edit", model: [catSistemaInstance: catSistemaInstance])
                return
            }
        }

        catSistemaInstance.properties = params

        if (!catSistemaInstance.save(flush: true)) {
            render(view: "edit", model: [catSistemaInstance: catSistemaInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'catSistema.label', default: 'CatSistema'), catSistemaInstance.toString()])
        redirect(action: "show", id: catSistemaInstance.id)
    }

    def x_delete(Long id) {
        def catSistemaInstance = CatSistema.get(id)
        if (!catSistemaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'catSistema.label', default: 'CatSistema'), id])
            redirect(action: "list")
            return
        }

        try {
            catSistemaInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'catSistema.label', default: 'CatSistema'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'catSistema.label', default: 'CatSistema'), id])
            redirect(action: "show", id: id)
        }
    }
}
