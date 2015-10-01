package mx.gob.inr.catservicios

import grails.plugins.springsecurity.Secured
import org.springframework.dao.DataIntegrityViolationException

@Secured(['ROLE_SAST_COORDINADOR_DE_GESTION','ROLE_SAST_TECNICO'])
class IncidenteLaboratorioController {
    def springSecurityService
    static nombreMenu = "Incidentes Laboratorio"
    static ordenMenu = -71

    static allowedMethods = [save: "POST", update: "POST", x_delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [incidenteLaboratorioInstanceList: IncidenteLaboratorio.list(params), incidenteLaboratorioInstanceTotal: IncidenteLaboratorio.count()]
    }

    def create() {
        [incidenteLaboratorioInstance: new IncidenteLaboratorio(params)]
    }

    def save() {
        def incidenteLaboratorioInstance = new IncidenteLaboratorio(params)
        if (!incidenteLaboratorioInstance.save(flush: true)) {
            render(view: "create", model: [incidenteLaboratorioInstance: incidenteLaboratorioInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'incidenteLaboratorio.label', default: 'IncidenteLaboratorio'), incidenteLaboratorioInstance.toString()])
        redirect(action: "show", id: incidenteLaboratorioInstance.id)
    }

    def show(Long id) {
        def incidenteLaboratorioInstance = IncidenteLaboratorio.get(id)
        if (!incidenteLaboratorioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'incidenteLaboratorio.label', default: 'IncidenteLaboratorio'), id])
            redirect(action: "list")
            return
        }

        [incidenteLaboratorioInstance: incidenteLaboratorioInstance]
    }

    def edit(Long id) {
        def incidenteLaboratorioInstance = IncidenteLaboratorio.get(id)
        if (!incidenteLaboratorioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'incidenteLaboratorio.label', default: 'IncidenteLaboratorio'), id])
            redirect(action: "list")
            return
        }

        [incidenteLaboratorioInstance: incidenteLaboratorioInstance]
    }

    def update(Long id, Long version) {
        def incidenteLaboratorioInstance = IncidenteLaboratorio.get(id)
        if (!incidenteLaboratorioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'incidenteLaboratorio.label', default: 'IncidenteLaboratorio'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (incidenteLaboratorioInstance.version > version) {
                incidenteLaboratorioInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'incidenteLaboratorio.label', default: 'IncidenteLaboratorio')] as Object[],
                          "Another user has updated this IncidenteLaboratorio while you were editing")
                render(view: "edit", model: [incidenteLaboratorioInstance: incidenteLaboratorioInstance])
                return
            }
        }

        incidenteLaboratorioInstance.properties = params

        if (!incidenteLaboratorioInstance.save(flush: true)) {
            render(view: "edit", model: [incidenteLaboratorioInstance: incidenteLaboratorioInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'incidenteLaboratorio.label', default: 'IncidenteLaboratorio'), incidenteLaboratorioInstance.toString()])
        redirect(action: "show", id: incidenteLaboratorioInstance.id)
    }

    def x_delete(Long id) {
        def incidenteLaboratorioInstance = IncidenteLaboratorio.get(id)
        if (!incidenteLaboratorioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'incidenteLaboratorio.label', default: 'IncidenteLaboratorio'), id])
            redirect(action: "list")
            return
        }

        try {
            incidenteLaboratorioInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'incidenteLaboratorio.label', default: 'IncidenteLaboratorio'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'incidenteLaboratorio.label', default: 'IncidenteLaboratorio'), id])
            redirect(action: "show", id: id)
        }
    }
}
