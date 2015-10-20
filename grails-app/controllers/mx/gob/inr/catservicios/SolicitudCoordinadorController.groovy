package mx.gob.inr.catservicios

import grails.plugins.springsecurity.Secured
import org.springframework.dao.DataIntegrityViolationException
import groovy.time.TimeCategory

@Secured(['ROLE_SAST_COORDINADOR','ROLE_SAST_COORDINADOR_DE_GESTION'])
class SolicitudCoordinadorController {
    def springSecurityService
    def grailsApplication
    static nombreMenu = "Coordinación"
    static ordenMenu = 20

    static allowedMethods = [save: "POST", update: "POST", x_delete: "POST"]

    def index() {
        redirect(action: "listDetalle", params: params)
    }

    def listDetalle(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        log.debug("params = $params")
        def userID = springSecurityService.principal.id
        def area = UsuarioAutorizado.get(userID)?.area
        log.debug("area = ${area}")

        def query =
            "  from SolicitudDetalle d                    \n" +
            " where idTecnico is null                     \n" +
            "   and d.estado = 'A'                        \n" +
            "   and exists                                \n" +
            "      ( from Cat_serv t,                     \n" +
            "             Cat_servResp r                  \n" +
            "       where d.idServ = t.id                 \n" +
            "         and t.servResp2 = r.id              \n" +
            "         and r.descripcion like '%${area}%') \n" +
            "   and exists                                \n" +
            "      ( from Solicitud s                     \n" +
            "       where s.id = d.idSolicitud            \n" +
            "         and s.estado = 'R')                 \n"
        log.debug("query = \n${query}")
        def detalles = SolicitudDetalle.executeQuery (
              "select count (*) " + query
            )[0]
        log.debug("numero de detalles = ${detalles}")
        [solicitudDetalleInstanceList: SolicitudDetalle.
            executeQuery(query + "order by d.id desc", [], params),
            solicitudDetalleInstanceTotal: detalles]
    }

    def listAsignados(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        log.debug("params = $params")

        def query =
          "from Solicitud s                                     " +
          " where s.estado = 'R'                                " +
          "   and not exists(select id from SolicitudDetalle d  " +
          "               where s.id = d.idSolicitud            " +
          "                 and idTecnico is null)              "
        def asignados = Solicitud.executeQuery(
            "select count(*) " + query
        )[0]
        log.debug("numero de asignados = ${asignados}")
        [asignadosInstanceList: Solicitud.executeQuery(query, [], params),
            asignadosInstanceTotal: asignados]
    }

    def listEncuestas(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        log.debug("params = $params")

        def query =
          "from Solicitud s          " +
          " where s.estado = 'E'     "
        def enEncuestas = Solicitud.executeQuery(
            "select count(*) " + query
        )[0]
        log.debug("numero de enEncuestas = ${enEncuestas}")
        [enEncuestasInstanceList: Solicitud.executeQuery(query, [], params),
            enEncuestasInstanceTotal: enEncuestas]
    }

    def listTerminadas(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        log.debug("params = $params")

        def query =
          "from Solicitud s          " +
          " where s.estado = 'T'     "
        def terminadas = Solicitud.executeQuery(
            "select count(*) " + query
        )[0]
        log.debug("numero de terminadas = ${terminadas}")
        [terminadasInstanceList: Solicitud.executeQuery(query, [], params),
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

    def showArchivo(Long id) {
        def solicitudArchivoadjuntoInstance = SolicitudArchivoadjunto.get(id)
        if (!solicitudArchivoadjuntoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudArchivoadjunto.label', default: 'SolicitudArchivoadjunto'), id])
            redirect(action: "list")
            return
        }

        [solicitudArchivoadjuntoInstance: solicitudArchivoadjuntoInstance]
    }

    def showNoFirma(Long id) {
        def solicitudInstance = Solicitud.get(id)
        if (!solicitudInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitud.label', default: 'Solicitud'), id])
            redirect(action: "list")
            return
        }

        [solicitudInstance: solicitudInstance]
    }

    def edit(Long id) {
      log.debug("params = $params")
        def solicitudDetalleInstance = SolicitudDetalle.get(id)
        if (!solicitudDetalleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudDetalle.label', default: 'SolicitudDetalle'), id])
            redirect(action: "listDetalle")
            return
        }

        [solicitudDetalleInstance: solicitudDetalleInstance,
          tecnicos:listaDeTecnicos()]
    }

    def listaDeTecnicos() {
      def userID = springSecurityService.principal.id
      log.debug("userID = ${userID}")
      def area = UsuarioAutorizado.get(userID)?.area
      log.debug("area = ${area}")
      def miembros = UsuarioAutorizado.findAllByAreaAndEstado(area,'A' as char).
        collect{it.id}
      log.debug("miembros = ${miembros}")

      def rolUsuarios = null
      Rol.withNewSession { session ->
        rolUsuarios = Rol.findByAuthority('ROLE_SAST_TECNICO')
      }
      log.debug("rolUsuarios = $rolUsuarios")
      def usuariosRolesIds = []
      UsuarioRol.withNewSession { sessionUR ->
        usuariosRolesIds = UsuarioRol.findAllByRol(rolUsuarios).
          findAll {it.usuario.id in miembros}.collect {it.usuario.id}
      }
      log.debug("usuariosRolesIds = $usuariosRolesIds")

       def tecnicos = []
       Usuario.withNewSession { sessionU ->
        tecnicos = Usuario.findAllByIdInList(usuariosRolesIds)
       }

      log.debug("numero de tecnicos = ${tecnicos.size()}")
      return tecnicos
    }

    def vistoBueno(Long id) {
        def solicitudInstance = Solicitud.get(id)
        if (!solicitudInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitud.label', default: 'Solicitud'), id])
            redirect(action: "show", id: solicitudInstance.id)
            return
        }

        [solicitudInstance: solicitudInstance]
    }

    def revisar(Long id) {
        def solicitudInstance = Solicitud.get(id)
        if (!solicitudInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitud.label', default: 'Solicitud'), id])
            redirect(action: "list")
            return
        }

        solicitudInstance.estado = 'R' as char

        if (!solicitudInstance.save(flush: true)) {
            render(view: "show", model: [solicitudInstance: solicitudInstance])
            return
        }

/* TODO: correo que quizá falta
        def idUsuario = springSecurityService.principal.id
        def personasInstance = Usuario.get(idUsuario)
        sendMail {
          to 'dzamora@inr.gob.mx' // TODO: mandar el correo al que solicito       personasInstance.correo
          subject "Solicitud ${solicitudInstance.toString()} registrada en el sistema"
          body "Hola ${personasInstance.username}\n\nSu solicitud folio " +
            "${solicitudInstance.toString()} ya esta registrada en el sistema, " +
            "pronto seras contactado con relación a el\n"
        }
*/

        flash.message = message(code: 'default.updated.message', args: [message(code: 'solicitud.label', default: 'Solicitud'), solicitudInstance.toString()])
        redirect(action: "show", id: solicitudInstance.id)
    }

    def updateVB(Long id, Long version) {
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
                render(view: "vistoBueno", model: [solicitudInstance: solicitudInstance])
                return
            }
        }

        solicitudInstance.properties = params

        if (!solicitudInstance.save(flush: true)) {
            render(view: "vistoBueno", model: [solicitudInstance: solicitudInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'solicitud.label', default: 'Solicitud'), solicitudInstance.toString()])
        redirect(action: "list")
    }

    def update(Long id, Long version) {
      log.debug("params = $params")
        def solicitudDetalleInstance = SolicitudDetalle.get(id)
        if (!solicitudDetalleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudDetalle.label', default: 'SolicitudDetalle'), id])
            redirect(action: "listDetalle")
            return
        }

        if (version != null) {
            if (solicitudDetalleInstance.version > version) {
                solicitudDetalleInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'solicitudDetalle.label', default: 'SolicitudDetalle')] as Object[],
                          "Another user has updated this SolicitudDetalle while you were editing")
                render(view: "edit", model: [solicitudDetalleInstance: solicitudDetalleInstance])
                return
            }
        }

        def userID = springSecurityService.principal.id
        log.debug("userID = $userID")

        def firmaTeclada = params['passwordfirma']
        log.debug("firmaTeclada = $firmaTeclada")
        def firma = Firmadigital.findById(userID)?.passwordfirma
        log.debug("firma = $firma")

        solicitudDetalleInstance.properties = params

        if (firmaTeclada != firma) {
            flash.error = "Error en contaseña"
            render(view: "edit", model: [solicitudDetalleInstance: solicitudDetalleInstance])
            return
        }

        if (!solicitudDetalleInstance.save(flush: true)) {
            render(view: "edit", model: [solicitudDetalleInstance: solicitudDetalleInstance])
            return
        }

        def tecnico = Usuario.get(solicitudDetalleInstance.idTecnico)
        def liga = createLink(controller:"SolicitudTecnico", action: "edit",
                              id: solicitudDetalleInstance.id, absolute: "true")
        log.debug("liga = $liga")
        def correo = grailsApplication.config.correo.general
        sendMail {
          to correo // TODO: mandar el correo al que solicito       tecnico.correo
          subject "La solicitud ${solicitudDetalleInstance.idSolicitud} requiere ser atendida"
          html "Hola ${tecnico}<br/><br/>La solicitud folio " +
            "${solicitudDetalleInstance.idSolicitud} requiere ser atendida, se autorizo el " +
            formatDate(date:solicitudDetalleInstance.idSolicitud.fechaVb?:
                            solicitudDetalleInstance.idSolicitud.fechaAutoriza) +
            " y debe ser respondida en ${solicitudDetalleInstance?.idServ?.tiempo1}" +
            " ${solicitudDetalleInstance?.idServ?.unidades1?.descripcion}." +
            "<br/><br/>" +
            "utilice la liga siguiente para atenderla. <br/><br/>" +
            "<a href='${liga}'>${solicitudDetalleInstance.idSolicitud}</a>"
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'solicitudDetalle.label', default: 'SolicitudDetalle'), solicitudDetalleInstance.toString()])
        redirect(action: "listDetalle")
    }

}
