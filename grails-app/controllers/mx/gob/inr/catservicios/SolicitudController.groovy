package mx.gob.inr.catservicios

import grails.plugins.springsecurity.Secured
import org.springframework.dao.DataIntegrityViolationException
import groovy.time.TimeCategory

@Secured(['ROLE_SAST_USUARIO'])
class SolicitudController {
    def springSecurityService
    def grailsApplication
    static nombreMenu = "Solicitud"
    static ordenMenu = 80

    static allowedMethods = [save: "POST", update: "POST", x_delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        log.debug("params = $params")
        def userID = springSecurityService.principal.id
        log.debug("userID = $userID")
        def solicitudes = Solicitud.withCriteria {
            projections {count()}
            eq('idSolicitante',(Integer)userID)
            or {
                and {
                    ne('estado','T' as char)
                    ne('estado','C' as char) }
                isNull('estado') }
        }[0]
        log.debug("numero de solicitudes = $solicitudes")
        def criterio = Solicitud.createCriteria()
        def solicitudesList = criterio.list(max:params.max, offset:params.offset,
                                            sort:"fechaSolicitud", order:"desc") {
            eq('idSolicitante',(Integer)userID)
            or {
                and {
                    ne('estado','T' as char)
                    ne('estado','C' as char)
                    }
                isNull('estado')
                }
        }
        [solicitudInstanceList: solicitudesList,
            solicitudInstanceTotal: solicitudes]
    }

    def create() {
        [solicitudInstance: new Solicitud(params),
          autorizadores:listaDeAutorizadores()]
    }

    def save() {
        def solicitudInstance = new Solicitud(params)
        solicitudInstance.idSolicitante = springSecurityService.principal.id
        solicitudInstance.ipTerminal = request.getRemoteAddr()

        if (!solicitudInstance.save(flush: true)) {
            render(view: "create", model: [solicitudInstance: solicitudInstance])
            return
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

      def autorizador = null
      if (solicitudInstance?.idAutoriza) {
        Usuario.withNewSession { sessionU ->
          def usuarioAutorizador = Usuario.get(solicitudInstance?.idAutoriza)
          autorizador = usuarioAutorizador.toString()
        }
      }

        [solicitudInstance: solicitudInstance,
          autorizador: autorizador]
    }

    def listaDeAutorizadores() {
      def userID = springSecurityService.principal.id
      def area = UsuarioAutorizado.get(userID)?.area
      if (!area) {
        area = Usuario.get(userID).idUnidadMedica
      }
      def miembros = UsuarioAutorizado.findAllAutorizaByAreaAndEstado(area,
                                                'A' as char).collect{it.id}

      def autorizadores = []
      Usuario.withNewSession { sessionU ->
        autorizadores = Usuario.findAllByIdInList(miembros)
      }

      log.debug("numero de autorizadores = ${autorizadores.size()}")
      return autorizadores
    }

    def edit(Long id) {
        def solicitudInstance = Solicitud.get(id)
        if (!solicitudInstance) {
            flash.message = message(code: 'default.not.found.message',
                                    args: [message(code: 'solicitud.label',
                                                   default: 'Solicitud'), id])
            redirect(action: "list")
            return
        }

        [solicitudInstance: solicitudInstance,
          autorizadores:listaDeAutorizadores()]
    }

    def firmarUpdate(Long id, Long version) {
        log.debug("params = $params")
        def solicitudInstance = Solicitud.get(id)
        if (!solicitudInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitud.label', default: 'Solicitud'), id])
            redirect(action: "list")
            return
        }

        if (solicitudInstance?.detalles?.size()) {
        } else {
            flash.error = "Debe tener al menos un detalle para poderla firmar"
            render(view: "show", model: [solicitudInstance: solicitudInstance])
            return
        }

        if (version != null) {
            if (solicitudInstance.version > version) {
                solicitudInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'solicitud.label', default: 'Solicitud')] as Object[],
                          "Alguien más ha modificado esta Solicitud mientras usted la estaba firmando")
                render(view: "show", model: [solicitudInstance: solicitudInstance])
                return
            }
        }

        def userID = springSecurityService.principal.id
        log.debug("userID = $userID")

        def firmaTeclada = params['passwordfirma']
        def firma = Firmadigital.findById(userID)?.passwordfirma
        if (firmaTeclada != firma) {
            flash.error = "Error en contaseña"
            render(view: "show", model: [solicitudInstance: solicitudInstance])
            return
        }

        solicitudInstance.fechaSolicitud = new Date()
        solicitudInstance.estado = 'F' as char

        if (!solicitudInstance.save(flush: true)) {
            render(view: "show", model: [solicitudInstance: solicitudInstance])
            return
        }

        def personasInstance = Usuario.get(solicitudInstance.idAutoriza)
        def liga = createLink(controller:"solicitudAutoriza", action: "show",
                              id: solicitudInstance.id, absolute: "true")
        log.debug("liga = $liga")
        def correo = grailsApplication.config.correo.general
        log.debug("correo = $correo")
        sendMail {
          to correo // TODO: mandar el correo al que solicito       personasInstance.correo
          subject "La solicitud ${solicitudInstance} requiere autorización"
          html "Hola ${personasInstance}<br/><br/>La solicitud folio " +
            "${solicitudInstance} requiere que la autorices, " +
            "utilice la liga siguiente para revisarla y autorizarla. <br/><br/>" +
            "<a href='${liga}'>Solicitud: ${solicitudInstance}</a>"
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'solicitud.label', default: 'Solicitud'), solicitudInstance.toString()])
        redirect(action: "show", id: solicitudInstance.id)
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
                          "Alguien más ha modificado esta Solicitud mientras usted la estaba editando")
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
