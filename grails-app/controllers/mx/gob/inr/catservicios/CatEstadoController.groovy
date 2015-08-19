package mx.gob.inr.catservicios

import org.springframework.dao.DataIntegrityViolationException

class CatEstadoController {
    static nombreMenu = "Estados"
    static ordenMenu = 100

    static allowedMethods = [save: "POST", update: "POST", x_delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [catEstadoInstanceList: CatEstado.list(params), catEstadoInstanceTotal: CatEstado.count()]
    }

    def create() {
        [catEstadoInstance: new CatEstado(params)]
    }

    def save() {
        def catEstadoInstance = new CatEstado(params)
        if (!catEstadoInstance.save(flush: true)) {
            render(view: "create", model: [catEstadoInstance: catEstadoInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'catEstado.label', default: 'CatEstado'), catEstadoInstance.toString()])
        redirect(action: "show", id: catEstadoInstance.id)
    }

    def show(Long id) {
        def catEstadoInstance = CatEstado.get(id)
        if (!catEstadoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'catEstado.label', default: 'CatEstado'), id])
            redirect(action: "list")
            return
        }

        [catEstadoInstance: catEstadoInstance]
    }

    def edit(Long id) {
        def catEstadoInstance = CatEstado.get(id)
        if (!catEstadoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'catEstado.label', default: 'CatEstado'), id])
            redirect(action: "list")
            return
        }

        [catEstadoInstance: catEstadoInstance]
    }

    def update(Long id, Long version) {
        def catEstadoInstance = CatEstado.get(id)
        if (!catEstadoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'catEstado.label', default: 'CatEstado'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (catEstadoInstance.version > version) {
                catEstadoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'catEstado.label', default: 'CatEstado')] as Object[],
                          "Another user has updated this CatEstado while you were editing")
                render(view: "edit", model: [catEstadoInstance: catEstadoInstance])
                return
            }
        }

        catEstadoInstance.properties = params

        if (!catEstadoInstance.save(flush: true)) {
            render(view: "edit", model: [catEstadoInstance: catEstadoInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'catEstado.label', default: 'CatEstado'), catEstadoInstance.toString()])
        redirect(action: "show", id: catEstadoInstance.id)
    }

    def x_delete(Long id) {
        def catEstadoInstance = CatEstado.get(id)
        if (!catEstadoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'catEstado.label', default: 'CatEstado'), id])
            redirect(action: "list")
            return
        }

        try {
            catEstadoInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'catEstado.label', default: 'CatEstado'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'catEstado.label', default: 'CatEstado'), id])
            redirect(action: "show", id: id)
        }
    }
}
