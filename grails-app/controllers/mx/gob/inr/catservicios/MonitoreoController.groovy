package mx.gob.inr.catservicios

import org.springframework.dao.DataIntegrityViolationException

class MonitoreoController {
    static nombreMenu = "Monitoreos"
    static ordenMenu = 53

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [monitoreoInstanceList: Monitoreo.list(params), monitoreoInstanceTotal: Monitoreo.count()]
    }

    def create() {
        [monitoreoInstance: new Monitoreo(params)]
    }

    def save() {
        def monitoreoInstance = new Monitoreo(params)
        monitoreoInstance.ipTerminal = request.getRemoteAddr()
        if (!monitoreoInstance.save(flush: true)) {
            render(view: "create", model: [monitoreoInstance: monitoreoInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'monitoreo.label', default: 'Monitoreo'), monitoreoInstance.id])
        redirect(action: "show", id: monitoreoInstance.id)
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
        def monitoreoInstance = Monitoreo.get(id)
        if (!monitoreoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'monitoreo.label', default: 'Monitoreo'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (monitoreoInstance.version > version) {
                monitoreoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'monitoreo.label', default: 'Monitoreo')] as Object[],
                          "Another user has updated this Monitoreo while you were editing")
                render(view: "edit", model: [monitoreoInstance: monitoreoInstance])
                return
            }
        }

        monitoreoInstance.properties = params

        if (!monitoreoInstance.save(flush: true)) {
            render(view: "edit", model: [monitoreoInstance: monitoreoInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'monitoreo.label', default: 'Monitoreo'), monitoreoInstance.id])
        redirect(action: "show", id: monitoreoInstance.id)
    }

    def delete(Long id) {
        def monitoreoInstance = Monitoreo.get(id)
        if (!monitoreoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'monitoreo.label', default: 'Monitoreo'), id])
            redirect(action: "list")
            return
        }

        try {
            monitoreoInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'monitoreo.label', default: 'Monitoreo'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'monitoreo.label', default: 'Monitoreo'), id])
            redirect(action: "show", id: id)
        }
    }
}
