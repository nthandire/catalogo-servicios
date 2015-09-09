package mx.gob.inr.catservicios

import grails.plugins.springsecurity.Secured
import org.springframework.dao.DataIntegrityViolationException

@Secured(['ROLE_SAST_ADMIN'])
class ProveedorController {
    static nombreMenu = "Proveedores"
    static ordenMenu = 91

    static allowedMethods = [save: "POST", update: "POST", x_delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [proveedorInstanceList: Proveedor.list(params), proveedorInstanceTotal: Proveedor.count()]
    }

    def create() {
        [proveedorInstance: new Proveedor(params)]
    }

    def save() {
        def proveedorInstance = new Proveedor(params)
        if (!proveedorInstance.save(flush: true)) {
            render(view: "create", model: [proveedorInstance: proveedorInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'proveedor.label', default: 'Proveedor'), proveedorInstance.toString()])
        redirect(action: "show", id: proveedorInstance.id)
    }

    def show(Long id) {
        def proveedorInstance = Proveedor.get(id)
        if (!proveedorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'proveedor.label', default: 'Proveedor'), id])
            redirect(action: "list")
            return
        }

        [proveedorInstance: proveedorInstance]
    }

    def edit(Long id) {
        def proveedorInstance = Proveedor.get(id)
        if (!proveedorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'proveedor.label', default: 'Proveedor'), id])
            redirect(action: "list")
            return
        }

        [proveedorInstance: proveedorInstance]
    }

    def update(Long id, Long version) {
        def proveedorInstance = Proveedor.get(id)
        if (!proveedorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'proveedor.label', default: 'Proveedor'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (proveedorInstance.version > version) {
                proveedorInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'proveedor.label', default: 'Proveedor')] as Object[],
                          "Another user has updated this Proveedor while you were editing")
                render(view: "edit", model: [proveedorInstance: proveedorInstance])
                return
            }
        }

        proveedorInstance.properties = params

        if (!proveedorInstance.save(flush: true)) {
            render(view: "edit", model: [proveedorInstance: proveedorInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'proveedor.label', default: 'Proveedor'), proveedorInstance.toString()])
        redirect(action: "show", id: proveedorInstance.id)
    }

    def x_delete(Long id) {
        def proveedorInstance = Proveedor.get(id)
        if (!proveedorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'proveedor.label', default: 'Proveedor'), id])
            redirect(action: "list")
            return
        }

        try {
            proveedorInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'proveedor.label', default: 'Proveedor'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'proveedor.label', default: 'Proveedor'), id])
            redirect(action: "show", id: id)
        }
    }
}
