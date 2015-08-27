package mx.gob.inr.catservicios

import grails.plugins.springsecurity.Secured
import org.springframework.dao.DataIntegrityViolationException

@Secured(['ROLE_ADMIN'])
class CatTiposervcioController {
    static nombreMenu = "Tipos de Servicios"
    static ordenMenu = 103

    static allowedMethods = [save: "POST", update: "POST", x_delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [catTiposervcioInstanceList: CatTiposervcio.list(params), catTiposervcioInstanceTotal: CatTiposervcio.count()]
    }

    def create() {
        [catTiposervcioInstance: new CatTiposervcio(params)]
    }

    def save() {
        def catTiposervcioInstance = new CatTiposervcio(params)
        if (!catTiposervcioInstance.save(flush: true)) {
            render(view: "create", model: [catTiposervcioInstance: catTiposervcioInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'catTiposervcio.label', default: 'CatTiposervcio'), catTiposervcioInstance.toString()])
        redirect(action: "show", id: catTiposervcioInstance.id)
    }

    def show(Long id) {
        def catTiposervcioInstance = CatTiposervcio.get(id)
        if (!catTiposervcioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'catTiposervcio.label', default: 'CatTiposervcio'), id])
            redirect(action: "list")
            return
        }

        [catTiposervcioInstance: catTiposervcioInstance]
    }

    def edit(Long id) {
        def catTiposervcioInstance = CatTiposervcio.get(id)
        if (!catTiposervcioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'catTiposervcio.label', default: 'CatTiposervcio'), id])
            redirect(action: "list")
            return
        }

        [catTiposervcioInstance: catTiposervcioInstance]
    }

    def update(Long id, Long version) {
        def catTiposervcioInstance = CatTiposervcio.get(id)
        if (!catTiposervcioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'catTiposervcio.label', default: 'CatTiposervcio'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (catTiposervcioInstance.version > version) {
                catTiposervcioInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'catTiposervcio.label', default: 'CatTiposervcio')] as Object[],
                          "Another user has updated this CatTiposervcio while you were editing")
                render(view: "edit", model: [catTiposervcioInstance: catTiposervcioInstance])
                return
            }
        }

        catTiposervcioInstance.properties = params

        if (!catTiposervcioInstance.save(flush: true)) {
            render(view: "edit", model: [catTiposervcioInstance: catTiposervcioInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'catTiposervcio.label', default: 'CatTiposervcio'), catTiposervcioInstance.toString()])
        redirect(action: "show", id: catTiposervcioInstance.id)
    }

    def x_delete(Long id) {
        def catTiposervcioInstance = CatTiposervcio.get(id)
        if (!catTiposervcioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'catTiposervcio.label', default: 'CatTiposervcio'), id])
            redirect(action: "list")
            return
        }

        try {
            catTiposervcioInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'catTiposervcio.label', default: 'CatTiposervcio'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'catTiposervcio.label', default: 'CatTiposervcio'), id])
            redirect(action: "show", id: id)
        }
    }
}
