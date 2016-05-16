package mx.gob.inr.catservicios

import grails.plugins.springsecurity.Secured
import org.springframework.dao.DataIntegrityViolationException

@Secured(['ROLE_SAST_ADMIN'])
class Cat_servCobController {
    static nombreMenu = "Catálogo de Coberturas"
    static ordenMenu = 4

    static allowedMethods = [save: "POST", update: "POST", x_delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [cat_servCobInstanceList: Cat_servCob.list(params), cat_servCobInstanceTotal: Cat_servCob.count()]
    }

    def create() {
        [cat_servCobInstance: new Cat_servCob(params)]
    }

    def save() {
        def cat_servCobInstance = new Cat_servCob(params)
        if (!cat_servCobInstance.save(flush: true)) {
            render(view: "create", model: [cat_servCobInstance: cat_servCobInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'cat_servCob.label', default: 'Cat_servCob'), cat_servCobInstance.toString()])
        redirect(action: "show", id: cat_servCobInstance.id)
    }

    def show(Long id) {
        def cat_servCobInstance = Cat_servCob.get(id)
        if (!cat_servCobInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cat_servCob.label', default: 'Cat_servCob'), id])
            redirect(action: "list")
            return
        }

        [cat_servCobInstance: cat_servCobInstance]
    }

    def edit(Long id) {
        def cat_servCobInstance = Cat_servCob.get(id)
        if (!cat_servCobInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cat_servCob.label', default: 'Cat_servCob'), id])
            redirect(action: "list")
            return
        }

        [cat_servCobInstance: cat_servCobInstance]
    }

    def update(Long id, Long version) {
        def cat_servCobInstance = Cat_servCob.get(id)
        if (!cat_servCobInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cat_servCob.label', default: 'Cat_servCob'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (cat_servCobInstance.version > version) {
                cat_servCobInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'cat_servCob.label', default: 'Cat_servCob')] as Object[],
                          "Another user has updated this Cat_servCob while you were editing")
                render(view: "edit", model: [cat_servCobInstance: cat_servCobInstance])
                return
            }
        }

        cat_servCobInstance.properties = params

        if (!cat_servCobInstance.save(flush: true)) {
            render(view: "edit", model: [cat_servCobInstance: cat_servCobInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'cat_servCob.label', default: 'Cat_servCob'), cat_servCobInstance.toString()])
        redirect(action: "show", id: cat_servCobInstance.id)
    }

    def x_delete(Long id) {
        def cat_servCobInstance = Cat_servCob.get(id)
        if (!cat_servCobInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cat_servCob.label', default: 'Cat_servCob'), id])
            redirect(action: "list")
            return
        }

        try {
            cat_servCobInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'cat_servCob.label', default: 'Cat_servCob'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'cat_servCob.label', default: 'Cat_servCob'), id])
            redirect(action: "show", id: id)
        }
    }
}
