package mx.gob.inr.catservicios

import grails.plugins.springsecurity.Secured
import org.springframework.dao.DataIntegrityViolationException
import groovy.time.TimeCategory

@Secured(['ROLE_SAST_USUARIO'])
class SolicitudEncuestaController {
    def springSecurityService
    static nombreMenu = "Encuesta"
    static ordenMenu = 87

    static allowedMethods = [save: "POST", update: "POST", x_delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        log.debug("params = $params")
        def userID = springSecurityService.principal.id
        log.debug("userID = $userID")
        def solicitudes = Solicitud.countByIdSolicitanteAndEstado((Integer)userID, 'E' as char)
        log.debug("numero de solicitudes = ${solicitudes}")
        def solicitudLista = Solicitud.findAllByIdSolicitanteAndEstado((Integer)userID, 'E' as char, params)
        def incidentes = Incidente.countByIdReportaAndEstado((Integer)userID, 'E' as char)
        log.debug("numero de solicitudes = ${solicitudes}")
        def incidenteLista = Incidente.findAllByIdReportaAndEstado((Integer)userID, 'E' as char, params)
        [solicitudInstanceList: solicitudLista + incidenteLista,
          solicitudInstanceTotal: solicitudes + incidentes]
    }

    def show(Long id) {
        def solicitudInstance = Solicitud.get(id)
        if (!solicitudInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitud.label', default: 'Solicitud'), id])
            redirect(action: "list")
            return
        }

        [solicitudInstance: solicitudInstance]
    }

    def showIncidente(Long id) {
        def incidenteInstance = Incidente.get(id)
        if (!incidenteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitud.label', default: 'Incidente'), id])
            redirect(action: "list")
            return
        }

        def archivos =
          IncidenteArchivoadjunto.findAllByIdIncidente(incidenteInstance.id)
        def nivel = incidenteInstance.nivel
        def tecnico = Usuario.get(incidenteInstance."idNivel${nivel}")

        [incidenteInstance: incidenteInstance,
          archivos: archivos, tecnico: tecnico]
    }

    def downloadIncidente(long id) {
        IncidenteArchivoadjunto incidenteArchivoadjuntoInstance =
          IncidenteArchivoadjunto.get(id)
        if ( IncidenteArchivoadjunto == null) {
            flash.message = "Documento no encontrado."
            redirect (controller: "incidente", action:'list')
        } else {
            response.setContentType("APPLICATION/OCTET-STREAM")
            response.setHeader("Content-Disposition",
              "Attachment;Filename=\"${incidenteArchivoadjuntoInstance.nombre}\"")

            def outputStream = response.getOutputStream()
            outputStream << incidenteArchivoadjuntoInstance.datos
            outputStream.flush()
            outputStream.close()
        }
    }

    def edit(Long id) {
        def solicitudInstance = Solicitud.get(id)
        if (!solicitudInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitud.label', default: 'Solicitud'), id])
            redirect(action: "list")
            return
        }

        [solicitudInstance: solicitudInstance]
    }

    def editIncidente(Long id) {
        def incidenteInstance = Incidente.get(id)
        if (!incidenteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitud.label', default: 'Incidente'), id])
            redirect(action: "list")
            return
        }

        [incidenteInstance: incidenteInstance]
    }

    def update(Long id, Long version) {
        def solicitudInstance = Solicitud.get(id)
        if (!solicitudInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitud.label', default: 'Solicitud'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (solicitudInstance.version > version) {
                solicitudInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'solicitud.label', default: 'Solicitud')] as Object[],
                          "Another user has updated this Solicitud while you were editing")
                render(view: "edit", model: [solicitudInstance: solicitudInstance])
                return
            }
        }

        def userID = springSecurityService.principal.id
        if (userID != solicitudInstance.idSolicitante) {
            flash.error = "Usted no puede realizar la encuesta."
            redirect(action: "list")
            return
        }

        solicitudInstance.properties = params
        solicitudInstance.estado = 'T' as char

        if (!solicitudInstance.save(flush: true)) {
            render(view: "edit", model: [solicitudInstance: solicitudInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'solicitud.label', default: 'Solicitud'), solicitudInstance.toString()])
        redirect(action: "show", id: solicitudInstance.id)
    }

    def UpdateIncidente(Long id, Long version) {
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
                render(view: "editIncedente", model: [incidenteInstance: incidenteInstance])
                return
            }
        }

        def userID = springSecurityService.principal.id
        if (userID != incidenteInstance.idReporta) {
            flash.error = "Usted no puede realizar la encuesta."
            redirect(action: "list")
            return
        }

        incidenteInstance.properties = params
        incidenteInstance.estado = 'T' as char

        if (!incidenteInstance.save(flush: true)) {
            render(view: "editIncidente", model: [incidenteInstance: incidenteInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'incidente.label', default: 'Incidente'), incidenteInstance.toString()])
        redirect(action: "showIncidente", id: incidenteInstance.id)
    }

}
