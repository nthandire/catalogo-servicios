package mx.gob.inr.catservicios

import grails.plugins.springsecurity.Secured
import org.springframework.dao.DataIntegrityViolationException

@Secured(['ROLE_ADMIN'])
class IncidenteArchivoadjuntoController {
    static nombreMenu = "Incidentes Archivos adjuntos"
    static ordenMenu = 72

    static allowedMethods = [save: "POST", update: "POST", x_delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [incidenteArchivoadjuntoInstanceList: IncidenteArchivoadjunto.list(params), incidenteArchivoadjuntoInstanceTotal: IncidenteArchivoadjunto.count()]
    }

    def create() {
        [incidenteArchivoadjuntoInstance: new IncidenteArchivoadjunto(params)]
    }

    def save() {
        def incidenteArchivoadjuntoInstance = new IncidenteArchivoadjunto(params)
        if (!incidenteArchivoadjuntoInstance.save(flush: true)) {
            render(view: "create", model: [incidenteArchivoadjuntoInstance: incidenteArchivoadjuntoInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'incidenteArchivoadjunto.label', default: 'IncidenteArchivoadjunto'), incidenteArchivoadjuntoInstance.toString()])
        redirect(action: "show", id: incidenteArchivoadjuntoInstance.id)
    }

    def show(Long id) {
        def incidenteArchivoadjuntoInstance = IncidenteArchivoadjunto.get(id)
        if (!incidenteArchivoadjuntoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'incidenteArchivoadjunto.label', default: 'IncidenteArchivoadjunto'), id])
            redirect(action: "list")
            return
        }

        [incidenteArchivoadjuntoInstance: incidenteArchivoadjuntoInstance]
    }

    def edit(Long id) {
        def incidenteArchivoadjuntoInstance = IncidenteArchivoadjunto.get(id)
        if (!incidenteArchivoadjuntoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'incidenteArchivoadjunto.label', default: 'IncidenteArchivoadjunto'), id])
            redirect(action: "list")
            return
        }

        [incidenteArchivoadjuntoInstance: incidenteArchivoadjuntoInstance]
    }

    def update(Long id, Long version) {
        def incidenteArchivoadjuntoInstance = IncidenteArchivoadjunto.get(id)
        if (!incidenteArchivoadjuntoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'incidenteArchivoadjunto.label', default: 'IncidenteArchivoadjunto'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (incidenteArchivoadjuntoInstance.version > version) {
                incidenteArchivoadjuntoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'incidenteArchivoadjunto.label', default: 'IncidenteArchivoadjunto')] as Object[],
                          "Another user has updated this IncidenteArchivoadjunto while you were editing")
                render(view: "edit", model: [incidenteArchivoadjuntoInstance: incidenteArchivoadjuntoInstance])
                return
            }
        }

        incidenteArchivoadjuntoInstance.properties = params

        if (!incidenteArchivoadjuntoInstance.save(flush: true)) {
            render(view: "edit", model: [incidenteArchivoadjuntoInstance: incidenteArchivoadjuntoInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'incidenteArchivoadjunto.label', default: 'IncidenteArchivoadjunto'), incidenteArchivoadjuntoInstance.toString()])
        redirect(action: "show", id: incidenteArchivoadjuntoInstance.id)
    }

    def x_delete(Long id) {
        def incidenteArchivoadjuntoInstance = IncidenteArchivoadjunto.get(id)
        if (!incidenteArchivoadjuntoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'incidenteArchivoadjunto.label', default: 'IncidenteArchivoadjunto'), id])
            redirect(action: "list")
            return
        }

        try {
            incidenteArchivoadjuntoInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'incidenteArchivoadjunto.label', default: 'IncidenteArchivoadjunto'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'incidenteArchivoadjunto.label', default: 'IncidenteArchivoadjunto'), id])
            redirect(action: "show", id: id)
        }
    }
}
