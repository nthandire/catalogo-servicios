package mx.gob.inr.catservicios

import grails.plugins.springsecurity.Secured
import org.springframework.dao.DataIntegrityViolationException
import groovy.time.TimeCategory

@Secured(['ROLE_SAST_USUARIO'])
class SolicitudVBController {
    def springSecurityService
    def grailsApplication
    def firmadoService
    static nombreMenu = "Visto Bueno"
    static ordenMenu = 85

    static allowedMethods = [save: "POST", update: "POST", x_delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        log.debug("params = $params")
        def userID = springSecurityService.principal.id
        log.debug("userID = $userID")
        def autorizables = Solicitud.countByIdVbAndEstado((Integer)userID, 'A' as char)
        log.debug("numero de autorizables = $autorizables")
        [autorizablesInstanceList: Solicitud.findAllByIdVbAndEstado((Integer)userID, 'A' as char, params),
          autorizablesInstanceTotal: autorizables]
    }

    def listAutorizados(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        log.debug("params = $params")
        def userID = springSecurityService.principal.id
        log.debug("userID = $userID")
        def autorizados = Solicitud.countByIdVbAndEstado((Integer)userID, 'V' as char)
        log.debug("numero de autorizados = $autorizados")
        [autorizadosInstanceList: Solicitud.findAllByIdVbAndEstado((Integer)userID, 'V' as char, params),
          autorizadosInstanceTotal: autorizados]
    }

    def listTerminadas(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        log.debug("params = $params")
        def userID = springSecurityService.principal.id
        log.debug("userID = $userID")
        def terminadas = Solicitud.countByIdVbAndEstado((Integer)userID, 'T' as char)
        log.debug("numero de terminadas = $terminadas")
        [terminadasInstanceList: Solicitud.
            findAllByIdVbAndEstado((Integer)userID, 'T' as char, params),
          terminadasInstanceTotal: terminadas]
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

    def showDetalle(Long id) {
        def solicitudDetalleInstance = SolicitudDetalle.get(id)
        if (!solicitudDetalleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudDetalle.label', default: 'SolicitudDetalle'), id])
            redirect(action: "list")
            return
        }

        [solicitudDetalleInstance: solicitudDetalleInstance]
    }

    def firmar(Long id) {
        def solicitudInstance = Solicitud.get(id)
        if (!solicitudInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitud.label', default: 'Solicitud'), id])
            redirect(action: "list")
            return
        }

        if (solicitudInstance?.detalles?.size()) {
        } else {
            flash.error = "La solicitud debe tener al menos un detalle para poderla firmar"
            render(view: "show", model: [solicitudInstance: solicitudInstance])
            return
        }

        [solicitudInstance: solicitudInstance]
    }

    def firmarUpdate(Long id) {
        def solicitudInstance = Solicitud.get(id)
        if (!solicitudInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitud.label', default: 'Solicitud'), id])
            redirect(action: "list")
            return
        }

        def userID = springSecurityService.principal.id
        log.debug("userID = $userID")

        def firmaTeclada = params['passwordfirma']
        log.debug("firmaTeclada = $firmaTeclada")
        def firma = Firmadigital.findById(userID)?.passwordfirma
        log.debug("firma = $firma")

        if (firmaTeclada != firma) {
            flash.error = "Error en contaseña"
            render(view: "show", model: [solicitudInstance: solicitudInstance])
            return
        }

        if (solicitudInstance?.idVb != userID) {
          flash.error = "Este incidente no esta asignado a Usted"
          render(view: "show", model: [solicitudInstance: solicitudInstance])
          return
        }

        solicitudInstance.fechaVb = new Date()
        solicitudInstance.estado = 'V' as char

        if (!solicitudInstance.save(flush: true)) {
            render(view: "show", model: [solicitudInstance: solicitudInstance])
            return
        }

        def idSolicitante = solicitudInstance.idSolicitante
        def asunto = "La solicitud ${solicitudInstance} ya recibió el visto bueno"
        def persona = Usuario.get(idSolicitante)
        def correo = persona.correo ?: grailsApplication.config.correo.general
        def msg = "Hola ${persona}\n\nSu solicitud folio " +
          "${solicitudInstance.toString()} " +
          "ya ha recibido el visto bueno.\n"
        firmadoService.sendMail(correo, asunto, msg)

        // TODO: Quitar.
        // def rolGestor = Rol.withNewSession {Rol.findByAuthority("ROLE_SAST_GESTOR")}
        // def gestores = UsuarioRol.withNewSession {UsuarioRol.findAllByRol(rolGestor)["usuario"]}
        // log.debug("gestores = ${gestores}")

        // def liga = createLink(controller:"solicitudGestion", action: "show",
        //                       id: solicitudInstance.id, absolute: "true")
        // log.debug("liga = $liga")

        // gestores.each {
        //   def cuerpoCorreo = "Hola ${it} <br/><br/>La solicitud folio " +
        //     "${solicitudInstance} (${solicitudInstance.justificacion}) " +
        //     "ya recibió el visto bueno, debe atenderla a la brevedad.<br/><br/>" +
        //     "Utilice la liga siguiente para revisarla. <br/><br/>" +
        //     "<a href='${liga}'>Solicitud: ${solicitudInstance}</a>"
        //   correo = it.correo ?: grailsApplication.config.correo.general
        //   firmadoService.sendMailHTML(correo, asunto, cuerpoCorreo)
        // }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'solicitud.label', default: 'Solicitud'), solicitudInstance.toString()])
        redirect(action: "list")
    }

    def cancelaUpdate(Long id) {
        log.debug("params = $params")
        def solicitudInstance = Solicitud.get(id)
        if (!solicitudInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitud.label', default: 'Solicitud'), id])
            redirect(action: "list")
            return
        }

        def userID = springSecurityService.principal.id
        log.debug("userID = $userID")

        if (userID != solicitudInstance.idVb) {
            flash.error = "Usted no puede dar el visto bueno."
            render(view: "show", model: [solicitudInstance: solicitudInstance])
            return
        }

        def firmaTeclada = params['passwordfirma']
        log.debug("firmaTeclada = $firmaTeclada")
        def firma = Firmadigital.findById(userID)?.passwordfirma
        log.debug("firma = $firma")

        if (firmaTeclada != firma) {
            flash.error = "Error en contaseña"
            render(view: "show", model: [solicitudInstance: solicitudInstance])
            return
        }

        solicitudInstance.properties = params
        solicitudInstance.fechaVb = new Date()
        solicitudInstance.estado = 'C' as char

        if (!solicitudInstance.save(flush: true)) {
            render(view: "show", model: [solicitudInstance: solicitudInstance])
            return
        }

        def idSolicitante = solicitudInstance.idSolicitante
        def persona = Usuario.get(idSolicitante)
        def correo = persona.correo ?: grailsApplication.config.correo.general
        def asunto = "Solicitud ${solicitudInstance} no recibio el visto bueno"
        def msg = "Hola ${persona}\n\nSu solicitud folio " +
          "${solicitudInstance.toString()}, '${solicitudInstance.justificacion}', " +
          "no recibio visto bueno, contacte a la mesa de servicio si requiere " +
          "más información.\n"
        firmadoService.sendMail(correo, asunto, msg)

        flash.message = message(code: 'default.updated.message', args: [message(code: 'solicitud.label', default: 'Solicitud'), solicitudInstance.toString()])
        redirect(action: "show", id: solicitudInstance.id)
    }

}
