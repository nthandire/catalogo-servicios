package mx.gob.inr.catservicios

import grails.plugins.springsecurity.Secured
import org.springframework.dao.DataIntegrityViolationException

@Secured(['ROLE_SAST_COORDINADOR_DE_GESTION','ROLE_SAST_TECNICO_MESA_SERVICIO'])
class IncidenteController {
    static nombreMenu = "Incidentes"
    static ordenMenu = 70

    static allowedMethods = [save: "POST", update: "POST", x_delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [incidenteInstanceList: Incidente.list(params), incidenteInstanceTotal: Incidente.count()]
    }

    def create() {
        [incidenteInstance: new Incidente(params)]
    }

    def save() {
        def incidenteInstance = new Incidente(params)
        if (!incidenteInstance.save(flush: true)) {
            render(view: "create", model: [incidenteInstance: incidenteInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'incidente.label', default: 'Incidente'), incidenteInstance.toString()])
        redirect(action: "show", id: incidenteInstance.id)
    }

    def show(Long id) {
        def incidenteInstance = Incidente.get(id)
        if (!incidenteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'incidente.label', default: 'Incidente'), id])
            redirect(action: "list")
            return
        }

        [incidenteInstance: incidenteInstance]
    }

    def edit(Long id) {
        def incidenteInstance = Incidente.get(id)
        if (!incidenteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'incidente.label', default: 'Incidente'), id])
            redirect(action: "list")
            return
        }

        [incidenteInstance: incidenteInstance]
    }

    def update(Long id, Long version) {
        def incidenteInstance = Incidente.get(id)
        if (!incidenteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'incidente.label', default: 'Incidente'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (incidenteInstance.version > version) {
                incidenteInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'incidente.label', default: 'Incidente')] as Object[],
                          "Another user has updated this Incidente while you were editing")
                render(view: "edit", model: [incidenteInstance: incidenteInstance])
                return
            }
        }

        incidenteInstance.properties = params

        if (!incidenteInstance.save(flush: true)) {
            render(view: "edit", model: [incidenteInstance: incidenteInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'incidente.label', default: 'Incidente'), incidenteInstance.toString()])
        redirect(action: "show", id: incidenteInstance.id)
    }

    def x_delete(Long id) {
        def incidenteInstance = Incidente.get(id)
        if (!incidenteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'incidente.label', default: 'Incidente'), id])
            redirect(action: "list")
            return
        }

        try {
            incidenteInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'incidente.label', default: 'Incidente'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'incidente.label', default: 'Incidente'), id])
            redirect(action: "show", id: id)
        }
    }
}
