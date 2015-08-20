package mx.gob.inr.catservicios

import org.springframework.dao.DataIntegrityViolationException
import groovy.time.TimeCategory

class SolicitudController {
    def springSecurityService
    static nombreMenu = "Solicitud"
    static ordenMenu = 80

    static allowedMethods = [save: "POST", update: "POST", x_delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [solicitudInstanceList: Solicitud.list(params), solicitudInstanceTotal: Solicitud.count()]
    }

    def create() {
        [solicitudInstance: new Solicitud(params)]
    }

    def save() {
        def solicitudInstance = new Solicitud(params)
        solicitudInstance.fechaSolicitud = new Date()
        solicitudInstance.idSolicitante = springSecurityService.principal.id
        solicitudInstance.ipTerminal = request.getRemoteAddr()

        def startDate = new Date().clearTime()
        startDate[Calendar.MONTH] = 0
        startDate[Calendar.DATE] = 1
        log.debug("startDate = $startDate")
        def endDate = startDate.clone()
        use(TimeCategory) {
          endDate = endDate + 1.years - 1.seconds
        }
        log.debug("endDate = $endDate")

        def criterio = Solicitud.createCriteria()
        def maxID = criterio.get {
          between("fechaSolicitud", startDate, endDate)
          projections {
            max "id"
          }
        } ?: 0
        log.debug("maxID = $maxID")

        solicitudInstance.numeroSolicitud = ++maxID

        if (!solicitudInstance.save(flush: true)) {
            render(view: "create", model: [solicitudInstance: solicitudInstance])
            return
        }

        def idUsuario = springSecurityService.principal.id
        def personasInstance = Usuario.get(idUsuario)
        sendMail {
          to 'dzamora@inr.gob.mx' // TODO: mandar el correo al que solicito       personasInstance.correo
          subject "Solicitud ${solicitudInstance.toString()} registrada en el sistema"
          body "Hola ${personasInstance.username}\n\nSu solicitud folio " + 
            "${solicitudInstance.toString()} ya esta registrada en el sistema, " +
            "pronto seras contactado con relación a el\n"
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'solicitud.label', default: 'Solicitud'), solicitudInstance.toString()])
        redirect(action: "show", id: solicitudInstance.id)
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

    def edit(Long id) {
        def solicitudInstance = Solicitud.get(id)
        if (!solicitudInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitud.label', default: 'Solicitud'), id])
            redirect(action: "list")
            return
        }

        [solicitudInstance: solicitudInstance]
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

        solicitudInstance.properties = params

        if (!solicitudInstance.save(flush: true)) {
            render(view: "edit", model: [solicitudInstance: solicitudInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'solicitud.label', default: 'Solicitud'), solicitudInstance.toString()])
        redirect(action: "show", id: solicitudInstance.id)
    }

    def x_delete(Long id) {
        def solicitudInstance = Solicitud.get(id)
        if (!solicitudInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitud.label', default: 'Solicitud'), id])
            redirect(action: "list")
            return
        }

        try {
            solicitudInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'solicitud.label', default: 'Solicitud'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'solicitud.label', default: 'Solicitud'), id])
            redirect(action: "show", id: id)
        }
    }
}
