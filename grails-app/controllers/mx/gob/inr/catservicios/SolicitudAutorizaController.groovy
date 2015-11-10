package mx.gob.inr.catservicios

import grails.plugins.springsecurity.Secured
import org.springframework.dao.DataIntegrityViolationException
import groovy.time.TimeCategory

@Secured(['ROLE_SAST_USUARIO'])
class SolicitudAutorizaController {
    def springSecurityService
    def firmadoService
    def grailsApplication
    static nombreMenu = "Autoriza"
    static ordenMenu = 83

    // TODO: intento de condicionar el display del controlador, borrar
    // static ordenMenuInterna = 83

    // static Integer getOrdenMenu() {
    //   def springSecurityService
    //   def userID = springSecurityService?.principal?.id
    //   println ("userID = $userID")
    //   if (userID) {
    //     def criteria = Solicitud.createCriteria()
    //     def autorizables = criteria.list {
    //         projections {count()}
    //         eq ('idAutoriza', (Integer)userID)
    //     }
    //     if (autorizables > 0)
    //       ordenMenuInterna
    //     else
    //       -ordenMenuInterna
    //     } else
    //       -ordenMenuInterna
    // }

    static allowedMethods = [save: "POST", update: "POST", x_delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        params.sort = "fechaSolicitud"
        params.order = "desc"
        log.debug("params = $params")
        def userID = springSecurityService.principal.id
        log.debug("userID = $userID")
        def autorizables = Solicitud.countByIdAutorizaAndEstado((Integer)userID, 'F' as char)
        log.debug("numero de autorizables = $autorizables")
        [autorizablesInstanceList: Solicitud.findAllByIdAutorizaAndEstado(
          (Integer)userID, 'F' as char, params),
          autorizablesInstanceTotal: autorizables]
    }

    def listAutorizados(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        log.debug("params = $params")
        def userID = springSecurityService.principal.id
        log.debug("userID = $userID")
        def autorizados = Solicitud.countByIdAutorizaAndEstado((Integer)userID, 'A' as char)
        log.debug("numero de autorizados = $autorizados")
        [autorizadosInstanceList: Solicitud.findAllByIdAutorizaAndEstado((Integer)userID, 'A' as char, params),
          autorizadosInstanceTotal: autorizados]
    }

    def listTerminadas(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        log.debug("params = $params")
        def userID = springSecurityService.principal.id
        log.debug("userID = $userID")
        def terminadas = Solicitud.countByIdAutorizaAndEstado((Integer)userID, 'T' as char)
        log.debug("numero de terminadas = $terminadas")
        [terminadasInstanceList: Solicitud.
            findAllByIdAutorizaAndEstado((Integer)userID, 'T' as char, params),
          terminadasInstanceTotal: terminadas]
    }

    def show(Long id) {
        log.debug("id = ${id}")
        def solicitudInstance = Solicitud.get(id)
        if (!solicitudInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitud.label', default: 'Solicitud'), id])
            redirect(action: "list")
            return
        }

        def area = firmadoService.areaNombre(solicitudInstance?.idSolicitante)

        [solicitudInstance: solicitudInstance, area: area]
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

    def firmarUpdate(Long id, Long version) {
      comandoUpdate(id, version, 'A' as char)
    }

    def cancelaUpdate(Long id, Long version) {
      comandoUpdate(id, version, 'C' as char)
    }

    def comandoUpdate(Long id, Long version, char estado) {
        log.debug("params = $params")
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
                          "Alguien más ha modificado esta Solicitud mientras usted la estaba autorizando")
                render(view: "show", model: [solicitudInstance: solicitudInstance])
                return
            }
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

        solicitudInstance.fechaAutoriza = new Date()
        solicitudInstance.estado = estado

        // Asignarle el siguiente folio dentro del año
        if (estado == 'A' as char) {
          def startDate = new Date().clearTime()
          startDate[Calendar.MONTH] = 0
          startDate[Calendar.DATE] = 1
          log.debug("startDate = $startDate")
          def endDate = startDate.clone()
          use(TimeCategory) {
            endDate = endDate + 1.years - 1.seconds
          }
          log.debug("endDate = $endDate")

          def maxID = Solicitud.withCriteria { // TODO: un test para ver si este algoritmo sique funcionando
            between("fechaAutoriza", startDate, endDate)
            projections {
              max "numeroSolicitud"
            }
          }[0] ?: 0
          log.debug("maxID = $maxID")

          solicitudInstance.numeroSolicitud = ++maxID
        }

        if (!solicitudInstance.save(flush: true)) {
            render(view: "show", model: [solicitudInstance: solicitudInstance])
            return
        }

        def idUsuario = solicitudInstance.idSolicitante
        def persona = Usuario.get(idUsuario)
        /* TODO: quitar código basura
        def tiempo = 0
        def unidad = 0
        SolicitudDetalle.findAllByIdSolicitud(solicitudInstance).each{
          if (it.) // TODO: mesa de servicio nos dira si ponemos el tiempo y como
        }
        */
        def asunto = (estado == 'A' as char) ?
          "La solicitud ${solicitudInstance} ya fue autorizada"
          :
          "La solicitud ${solicitudInstance} fue cancelada"
        def fecha = solicitudInstance.fechaSolicitud
        def msg = (estado == 'A' as char) ?
          """Hola ${persona}

Su solicitud de Servicio de Tecnologías de la Información realizada el ${fecha.format('dd')} de ${fecha.format('MMMMM')} a las ${fecha.format('hh:mm')} ya fue autorizada.
"""
          :
          "Hola ${persona}\n\nSu solicitud " +
            "(${solicitudInstance.justificacion}) "+
            "fue cancelada, investigue con su autorizador el motivo."
        def correo = persona.correo ?: grailsApplication.config.correo.general
        firmadoService.sendMail(correo, asunto, msg)

        // TODO: Quitar.
        // if (estado == 'A' as char) {
        //   def rolGestor = Rol.withNewSession {Rol.findByAuthority("ROLE_SAST_COORDINADOR_DE_GESTION")}
        //   def gestores = UsuarioRol.withNewSession {UsuarioRol.findAllByRol(rolGestor)["usuario"]}
        //   log.debug("gestores = ${gestores}")

        //   def liga = createLink(controller:"solicitudGestion", action: "show",
        //                         id: solicitudInstance.id, absolute: "true")
        //   log.debug("liga = $liga")

        //   gestores.each {
        //     msg = "Hola ${it} <br/><br/>La solicitud folio " +
        //       "${solicitudInstance} (${solicitudInstance.justificacion}) " +
        //       "ya fue autorizada, debe atenderla a la brevedad.<br/><br/>" +
        //       "Utilice la liga siguiente para revisarla. <br/><br/>" +
        //       "<a href='${liga}'>Solicitud: ${solicitudInstance}</a>"
        //     correo = it.correo ?: grailsApplication.config.correo.general
        //     firmadoService.sendMailHTML(correo, asunto, msg)
        //   }
        // }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'solicitud.label', default: 'Solicitud'), solicitudInstance.toString()])
        redirect(action: "list")
    }

}
