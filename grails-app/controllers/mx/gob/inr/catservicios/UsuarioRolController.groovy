package mx.gob.inr.catservicios

import grails.plugins.springsecurity.Secured
import org.springframework.dao.DataIntegrityViolationException

@Secured(['ROLE_SAST_ADMIN'])
class UsuarioRolController {
    static nombreMenu = "Usuarios-Roles"
    static ordenMenu = -163

    static allowedMethods = [save: "POST", update: "POST", x_delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [usuarioRolInstanceList: UsuarioRol.list(params), usuarioRolInstanceTotal: UsuarioRol.count()]
    }

    def create() {
        [usuarioRolInstance: new UsuarioRol(params)]
    }

    def save() {
        def usuarioRolInstance = new UsuarioRol(params)
        if (!usuarioRolInstance.save(flush: true)) {
            render(view: "create", model: [usuarioRolInstance: usuarioRolInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'usuarioRol.label', default: 'UsuarioRol'), usuarioRolInstance.toString()])
        redirect(action: "show", id: usuarioRolInstance.id)
    }

    def show(Long id) {
        def usuarioRolInstance = UsuarioRol.get(id)
        if (!usuarioRolInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'usuarioRol.label', default: 'UsuarioRol'), id])
            redirect(action: "list")
            return
        }

        [usuarioRolInstance: usuarioRolInstance]
    }

    def edit(Long id) {
        def usuarioRolInstance = UsuarioRol.get(id)
        if (!usuarioRolInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'usuarioRol.label', default: 'UsuarioRol'), id])
            redirect(action: "list")
            return
        }

        [usuarioRolInstance: usuarioRolInstance]
    }

    def update(Long id, Long version) {
        def usuarioRolInstance = UsuarioRol.get(id)
        if (!usuarioRolInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'usuarioRol.label', default: 'UsuarioRol'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (usuarioRolInstance.version > version) {
                usuarioRolInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'usuarioRol.label', default: 'UsuarioRol')] as Object[],
                          "Another user has updated this UsuarioRol while you were editing")
                render(view: "edit", model: [usuarioRolInstance: usuarioRolInstance])
                return
            }
        }

        usuarioRolInstance.properties = params

        if (!usuarioRolInstance.save(flush: true)) {
            render(view: "edit", model: [usuarioRolInstance: usuarioRolInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'usuarioRol.label', default: 'UsuarioRol'), usuarioRolInstance.toString()])
        redirect(action: "show", id: usuarioRolInstance.id)
    }

    def x_delete(Long id) {
        def usuarioRolInstance = UsuarioRol.get(id)
        if (!usuarioRolInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'usuarioRol.label', default: 'UsuarioRol'), id])
            redirect(action: "list")
            return
        }

        try {
            usuarioRolInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'usuarioRol.label', default: 'UsuarioRol'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'usuarioRol.label', default: 'UsuarioRol'), id])
            redirect(action: "show", id: id)
        }
    }
}
