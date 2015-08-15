package mx.gob.inr.catservicios

import org.springframework.dao.DataIntegrityViolationException

class SolicitudDetalleController {
    static nombreMenu = "Solicitud Detalle"
    static ordenMenu = 81

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        println params
        [solicitudDetalleInstanceList: SolicitudDetalle.list(params), solicitudDetalleInstanceTotal: SolicitudDetalle.count()]
    }

    def create() {
        [solicitudDetalleInstance: new SolicitudDetalle(params)]
    }

    def save() {
        def solicitudDetalleInstance = new SolicitudDetalle(params)
        if (!solicitudDetalleInstance.save(flush: true)) {
            render(view: "create", model: [solicitudDetalleInstance: solicitudDetalleInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'solicitudDetalle.label', default: 'SolicitudDetalle'), solicitudDetalleInstance.toString()])
        redirect(action: "show", id: solicitudDetalleInstance.id)
    }

    def show(Long id) {
        def solicitudDetalleInstance = SolicitudDetalle.get(id)
        if (!solicitudDetalleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudDetalle.label', default: 'SolicitudDetalle'), id])
            redirect(action: "list")
            return
        }

        [solicitudDetalleInstance: solicitudDetalleInstance]
    }

    def edit(Long id) {
        def solicitudDetalleInstance = SolicitudDetalle.get(id)
        if (!solicitudDetalleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudDetalle.label', default: 'SolicitudDetalle'), id])
            redirect(action: "list")
            return
        }

        [solicitudDetalleInstance: solicitudDetalleInstance]
    }

    def update(Long id, Long version) {
        def solicitudDetalleInstance = SolicitudDetalle.get(id)
        if (!solicitudDetalleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudDetalle.label', default: 'SolicitudDetalle'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (solicitudDetalleInstance.version > version) {
                solicitudDetalleInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'solicitudDetalle.label', default: 'SolicitudDetalle')] as Object[],
                          "Another user has updated this SolicitudDetalle while you were editing")
                render(view: "edit", model: [solicitudDetalleInstance: solicitudDetalleInstance])
                return
            }
        }

        solicitudDetalleInstance.properties = params

        if (!solicitudDetalleInstance.save(flush: true)) {
            render(view: "edit", model: [solicitudDetalleInstance: solicitudDetalleInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'solicitudDetalle.label', default: 'SolicitudDetalle'), solicitudDetalleInstance.toString()])
        redirect(action: "show", id: solicitudDetalleInstance.id)
    }

    def delete(Long id) {
        def solicitudDetalleInstance = SolicitudDetalle.get(id)
        if (!solicitudDetalleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudDetalle.label', default: 'SolicitudDetalle'), id])
            redirect(action: "list")
            return
        }

        try {
            solicitudDetalleInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'solicitudDetalle.label', default: 'SolicitudDetalle'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'solicitudDetalle.label', default: 'SolicitudDetalle'), id])
            redirect(action: "show", id: id)
        }
    }
}