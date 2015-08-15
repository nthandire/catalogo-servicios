package mx.gob.inr.catservicios

import org.springframework.dao.DataIntegrityViolationException

class SolicitudArchivoadjuntoController {
    static nombreMenu = "Solicitud Archivos adjuntos"
    static ordenMenu = 82

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [solicitudArchivoadjuntoInstanceList: SolicitudArchivoadjunto.list(params), solicitudArchivoadjuntoInstanceTotal: SolicitudArchivoadjunto.count()]
    }

    def create() {
        [solicitudArchivoadjuntoInstance: new SolicitudArchivoadjunto(params)]
    }

    def save() {
        def solicitudArchivoadjuntoInstance = new SolicitudArchivoadjunto(params)
        if (!solicitudArchivoadjuntoInstance.save(flush: true)) {
            render(view: "create", model: [solicitudArchivoadjuntoInstance: solicitudArchivoadjuntoInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'solicitudArchivoadjunto.label', default: 'SolicitudArchivoadjunto'), solicitudArchivoadjuntoInstance.toString()])
        redirect(action: "show", id: solicitudArchivoadjuntoInstance.id)
    }

    def show(Long id) {
        def solicitudArchivoadjuntoInstance = SolicitudArchivoadjunto.get(id)
        if (!solicitudArchivoadjuntoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudArchivoadjunto.label', default: 'SolicitudArchivoadjunto'), id])
            redirect(action: "list")
            return
        }

        [solicitudArchivoadjuntoInstance: solicitudArchivoadjuntoInstance]
    }

    def edit(Long id) {
        def solicitudArchivoadjuntoInstance = SolicitudArchivoadjunto.get(id)
        if (!solicitudArchivoadjuntoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudArchivoadjunto.label', default: 'SolicitudArchivoadjunto'), id])
            redirect(action: "list")
            return
        }

        [solicitudArchivoadjuntoInstance: solicitudArchivoadjuntoInstance]
    }

    def update(Long id, Long version) {
        def solicitudArchivoadjuntoInstance = SolicitudArchivoadjunto.get(id)
        if (!solicitudArchivoadjuntoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudArchivoadjunto.label', default: 'SolicitudArchivoadjunto'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (solicitudArchivoadjuntoInstance.version > version) {
                solicitudArchivoadjuntoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'solicitudArchivoadjunto.label', default: 'SolicitudArchivoadjunto')] as Object[],
                          "Another user has updated this SolicitudArchivoadjunto while you were editing")
                render(view: "edit", model: [solicitudArchivoadjuntoInstance: solicitudArchivoadjuntoInstance])
                return
            }
        }

        solicitudArchivoadjuntoInstance.properties = params

        if (!solicitudArchivoadjuntoInstance.save(flush: true)) {
            render(view: "edit", model: [solicitudArchivoadjuntoInstance: solicitudArchivoadjuntoInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'solicitudArchivoadjunto.label', default: 'SolicitudArchivoadjunto'), solicitudArchivoadjuntoInstance.toString()])
        redirect(action: "show", id: solicitudArchivoadjuntoInstance.id)
    }

    def delete(Long id) {
        def solicitudArchivoadjuntoInstance = SolicitudArchivoadjunto.get(id)
        if (!solicitudArchivoadjuntoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudArchivoadjunto.label', default: 'SolicitudArchivoadjunto'), id])
            redirect(action: "list")
            return
        }

        try {
            solicitudArchivoadjuntoInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'solicitudArchivoadjunto.label', default: 'SolicitudArchivoadjunto'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'solicitudArchivoadjunto.label', default: 'SolicitudArchivoadjunto'), id])
            redirect(action: "show", id: id)
        }
    }
}
