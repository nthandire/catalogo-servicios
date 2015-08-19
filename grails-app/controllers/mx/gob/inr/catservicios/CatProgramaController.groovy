package mx.gob.inr.catservicios

import org.springframework.dao.DataIntegrityViolationException

class CatProgramaController {
    static nombreMenu = "Programas"
    static ordenMenu = 101

    static allowedMethods = [save: "POST", update: "POST", x_delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [catProgramaInstanceList: CatPrograma.list(params), catProgramaInstanceTotal: CatPrograma.count()]
    }

    def create() {
        [catProgramaInstance: new CatPrograma(params)]
    }

    def save() {
        def catProgramaInstance = new CatPrograma(params)
        if (!catProgramaInstance.save(flush: true)) {
            render(view: "create", model: [catProgramaInstance: catProgramaInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'catPrograma.label', default: 'CatPrograma'), catProgramaInstance.toString()])
        redirect(action: "show", id: catProgramaInstance.id)
    }

    def show(Long id) {
        def catProgramaInstance = CatPrograma.get(id)
        if (!catProgramaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'catPrograma.label', default: 'CatPrograma'), id])
            redirect(action: "list")
            return
        }

        [catProgramaInstance: catProgramaInstance]
    }

    def edit(Long id) {
        def catProgramaInstance = CatPrograma.get(id)
        if (!catProgramaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'catPrograma.label', default: 'CatPrograma'), id])
            redirect(action: "list")
            return
        }

        [catProgramaInstance: catProgramaInstance]
    }

    def update(Long id, Long version) {
        def catProgramaInstance = CatPrograma.get(id)
        if (!catProgramaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'catPrograma.label', default: 'CatPrograma'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (catProgramaInstance.version > version) {
                catProgramaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'catPrograma.label', default: 'CatPrograma')] as Object[],
                          "Another user has updated this CatPrograma while you were editing")
                render(view: "edit", model: [catProgramaInstance: catProgramaInstance])
                return
            }
        }

        catProgramaInstance.properties = params

        if (!catProgramaInstance.save(flush: true)) {
            render(view: "edit", model: [catProgramaInstance: catProgramaInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'catPrograma.label', default: 'CatPrograma'), catProgramaInstance.toString()])
        redirect(action: "show", id: catProgramaInstance.id)
    }

    def x_delete(Long id) {
        def catProgramaInstance = CatPrograma.get(id)
        if (!catProgramaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'catPrograma.label', default: 'CatPrograma'), id])
            redirect(action: "list")
            return
        }

        try {
            catProgramaInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'catPrograma.label', default: 'CatPrograma'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'catPrograma.label', default: 'CatPrograma'), id])
            redirect(action: "show", id: id)
        }
    }
}
