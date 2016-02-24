package mx.gob.inr.catservicios

import grails.plugins.springsecurity.Secured
import org.springframework.dao.DataIntegrityViolationException

@Secured(['ROLE_SAST_ADMIN'])
class BitacoraDetalleController {
    static nombreMenu = "Detalles de las Bitacoras"
    static ordenMenu = 32

    static allowedMethods = [save: "POST", update: "POST", x_delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [bitacoraDetalleInstanceList: BitacoraDetalle.list(params), bitacoraDetalleInstanceTotal: BitacoraDetalle.count()]
    }

    def create() {
        [bitacoraDetalleInstance: new BitacoraDetalle(params)]
    }

    def save() {
        def bitacoraDetalleInstance = new BitacoraDetalle(params)
        bitacoraDetalleInstance.estado = 'A' as char
        if (!bitacoraDetalleInstance.save(flush: true)) {
            render(view: "create", model: [bitacoraDetalleInstance: bitacoraDetalleInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'bitacoraDetalle.label', default: 'BitacoraDetalle'), bitacoraDetalleInstance.toString()])
        redirect(action: "show", id: bitacoraDetalleInstance.id)
    }

    def show(Long id) {
        def bitacoraDetalleInstance = BitacoraDetalle.get(id)
        if (!bitacoraDetalleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'bitacoraDetalle.label', default: 'BitacoraDetalle'), id])
            redirect(action: "list")
            return
        }

        [bitacoraDetalleInstance: bitacoraDetalleInstance]
    }

    def edit(Long id) {
        def bitacoraDetalleInstance = BitacoraDetalle.get(id)
        if (!bitacoraDetalleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'bitacoraDetalle.label', default: 'BitacoraDetalle'), id])
            redirect(action: "list")
            return
        }

        [bitacoraDetalleInstance: bitacoraDetalleInstance]
    }

    def update(Long id, Long version) {
        def bitacoraDetalleInstance = BitacoraDetalle.get(id)
        if (!bitacoraDetalleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'bitacoraDetalle.label', default: 'BitacoraDetalle'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (bitacoraDetalleInstance.version > version) {
                bitacoraDetalleInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'bitacoraDetalle.label', default: 'BitacoraDetalle')] as Object[],
                          "Another user has updated this BitacoraDetalle while you were editing")
                render(view: "edit", model: [bitacoraDetalleInstance: bitacoraDetalleInstance])
                return
            }
        }

        bitacoraDetalleInstance.properties = params

        if (!bitacoraDetalleInstance.save(flush: true)) {
            render(view: "edit", model: [bitacoraDetalleInstance: bitacoraDetalleInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'bitacoraDetalle.label', default: 'BitacoraDetalle'), bitacoraDetalleInstance.toString()])
        redirect(action: "show", id: bitacoraDetalleInstance.id)
    }

    def x_delete(Long id) {
        def bitacoraDetalleInstance = BitacoraDetalle.get(id)
        if (!bitacoraDetalleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'bitacoraDetalle.label', default: 'BitacoraDetalle'), id])
            redirect(action: "list")
            return
        }

        try {
            bitacoraDetalleInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'bitacoraDetalle.label', default: 'BitacoraDetalle'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'bitacoraDetalle.label', default: 'BitacoraDetalle'), id])
            redirect(action: "show", id: id)
        }
    }
}
