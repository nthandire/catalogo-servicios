package mx.gob.inr.catservicios

import grails.plugins.springsecurity.Secured
import org.springframework.dao.DataIntegrityViolationException

@Secured(['ROLE_SAST_ADMIN'])
class Cat_servRespController {
    static nombreMenu = "Catálogo de Responsables"
    static ordenMenu = 5
 
    static allowedMethods = [save: "POST", update: "POST", x_delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 100, 100)
        [cat_servRespInstanceList: Cat_servResp.list(params), cat_servRespInstanceTotal: Cat_servResp.count()]
    }

    def create() {
        [cat_servRespInstance: new Cat_servResp(params)]
    }

    def save() {
        def cat_servRespInstance = new Cat_servResp(params)
        if (!cat_servRespInstance.save(flush: true)) {
            render(view: "create", model: [cat_servRespInstance: cat_servRespInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'cat_servResp.label', default: 'Cat_servResp'), cat_servRespInstance.toString()])
        redirect(action: "show", id: cat_servRespInstance.id)
    }

    def show(Long id) {
        def cat_servRespInstance = Cat_servResp.get(id)
        if (!cat_servRespInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cat_servResp.label', default: 'Cat_servResp'), id])
            redirect(action: "list")
            return
        }

        [cat_servRespInstance: cat_servRespInstance]
    }

    def edit(Long id) {
        def cat_servRespInstance = Cat_servResp.get(id)
        if (!cat_servRespInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cat_servResp.label', default: 'Cat_servResp'), id])
            redirect(action: "list")
            return
        }

        [cat_servRespInstance: cat_servRespInstance]
    }

    def update(Long id, Long version) {
        def cat_servRespInstance = Cat_servResp.get(id)
        if (!cat_servRespInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cat_servResp.label', default: 'Cat_servResp'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (cat_servRespInstance.version > version) {
                cat_servRespInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'cat_servResp.label', default: 'Cat_servResp')] as Object[],
                          "Another user has updated this Cat_servResp while you were editing")
                render(view: "edit", model: [cat_servRespInstance: cat_servRespInstance])
                return
            }
        }

        cat_servRespInstance.properties = params

        if (!cat_servRespInstance.save(flush: true)) {
            render(view: "edit", model: [cat_servRespInstance: cat_servRespInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'cat_servResp.label', default: 'Cat_servResp'), cat_servRespInstance.toString()])
        redirect(action: "show", id: cat_servRespInstance.id)
    }

    def x_delete(Long id) {
        def cat_servRespInstance = Cat_servResp.get(id)
        if (!cat_servRespInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cat_servResp.label', default: 'Cat_servResp'), id])
            redirect(action: "list")
            return
        }

        try {
            cat_servRespInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'cat_servResp.label', default: 'Cat_servResp'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'cat_servResp.label', default: 'Cat_servResp'), id])
            redirect(action: "show", id: id)
        }
    }
}
