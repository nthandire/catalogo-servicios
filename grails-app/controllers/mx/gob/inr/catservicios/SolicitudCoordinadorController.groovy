/*

Al inicio del desarrollo se les llamava Coordinadores a los Aprovadores,
por eso, este modulo se llama así.

No hay problema en cambiar el nombre, simplemente hay que hacer muchos cambios
y probar y probar.

*/
package mx.gob.inr.catservicios

import grails.plugins.springsecurity.Secured
import org.springframework.dao.DataIntegrityViolationException
import groovy.time.TimeCategory

@Secured(['ROLE_SAST_APROBADOR','ROLE_SAST_GESTOR'])
class SolicitudCoordinadorController {
    def springSecurityService
    def grailsApplication
    def firmadoService
    static nombreMenu = "Aprobador"
    static ordenMenu = 90

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

        def solicitudes = SolicitudDetalle.executeQuery (query)
        log.debug("lista solicitudes = ${solicitudes.collect{"$it.id:$it"}}")

        log.debug("params['sort'] = ${params['sort']}")
        switch (params['sort']) {
          case null:
          case "numeroSolicitud":
            log.debug("numeroSolicitud")
            solicitudes.sort{it.idSolicitud.paraOrdenar()}
          break
          case "categoria":
            log.debug("categoria")
            solicitudes.sort{it.idServcat.categoria}
          break
          case "servicio":
            log.debug("servicio")
            solicitudes.sort{it.idServ?.toString()}
          case "descripcion":
            log.debug("descripcion")
            solicitudes.sort{it?.descripcion}
          break
          case "prioridad":
            log.debug("prioridad")
            solicitudes.sort{it?.prioridad?:(it?.idServ?.impacto)}
          break
          case "lastUpdated":
            log.debug("lastUpdated")
            solicitudes.sort{it.idSolicitud.fechaRevisa}
          break
          case "tiempo":
            log.debug("tiempo")
            solicitudes.sort{it.idServ?.tiempo2?:"" + it?.idServ?.unidades2?.descripcion?:""}
          break
        }
        log.debug("solicitudes = ${solicitudes.collect{"$it.id:$it"}}")

        def paramMax = (params['max']?:'0').toInteger()
        def paramOffset = (params['offset']?:'0').toInteger()

        log.debug("paramOffset = ${paramOffset}")
        log.debug("paramOffset+paramMax-1 = ${paramOffset+paramMax-1}")
        log.debug("solicitudes.size()-1 = ${solicitudes.size()-1}")
        log.debug("Math.max(solicitudes.size()-1, 0) = ${Math.max(solicitudes.size()-1, 0)}")
        log.debug("Math.min(paramOffset+paramMax-1, Math.max(solicitudes.size()-1, 0)) = ${Math.min(paramOffset+paramMax-1, Math.max(solicitudes.size()-1, 0))}")

        if (params['order'] == 'desc') {
          solicitudes = solicitudes.reverse()
        }
        def total = solicitudes.size()
        def solicitudesPaginación = total ?
          solicitudes[paramOffset..
            Math.min(paramOffset+paramMax-1, solicitudes.size()-1)] :
          []
        log.debug("solicitudesPaginación = ${solicitudesPaginación.collect{"$it.id:$it"}}")

        [solicitudDetalleInstanceList: solicitudesPaginación,
          solicitudDetalleInstanceTotal: total]
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

      def rolTecnicos = null
      def rolCoordinadores = null
      Rol.withNewSession { session ->
        rolTecnicos = Rol.findByAuthority('ROLE_SAST_TECNICO')
      }
      log.debug("rolTecnicos = $rolTecnicos")
      def tecnicos = []
      UsuarioRol.withNewSession { sessionUR ->
        tecnicos = UsuarioRol.findAllByRol(rolTecnicos).
          findAll {it.usuario.id in miembros}.collect {it.usuario}
      }
      tecnicos.sort{it.nombre}
      log.debug("tecnicos = $tecnicos")
      tecnicos
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
                render(view: "edit", model: [solicitudDetalleInstance: solicitudDetalleInstance,
                                              tecnicos:listaDeTecnicos()])
                return
            }
        }

        def userID = springSecurityService.principal.id
        log.debug("userID = $userID")

        def firmaTeclada = params['passwordfirma']
        log.debug("firmaTeclada = $firmaTeclada")
        def firma = Firmadigital.findById(userID)?.passwordfirma?.reverse()
        log.debug("firma = $firma")

        if (firmaTeclada != firma) {
           flash.error = "Error en contaseña"
            render(view: "edit", model: [solicitudDetalleInstance: solicitudDetalleInstance,
                                          tecnicos:listaDeTecnicos()])
            return // TODO: aquí voy
        }

        solicitudDetalleInstance.properties = params
        solicitudDetalleInstance.idAprobador = userID
        solicitudDetalleInstance.fechaAprobador = new Date()
        if (!solicitudDetalleInstance.save(flush: true)) {
           render(view: "edit", model: [solicitudDetalleInstance: solicitudDetalleInstance,
                                          tecnicos:listaDeTecnicos()])
            return
        }
        def tecnico = Usuario.get(solicitudDetalleInstance.idTecnico)
        def liga = createLink(controller:"SolicitudTecnico", action: "edit",
                              id: solicitudDetalleInstance.id, absolute: "true")
        log.debug("liga = $liga")
        def correo = firmadoService.correo(tecnico.idEmpleado)
        def asunto = "La solicitud ${solicitudDetalleInstance.idSolicitud} requiere ser atendida"
        def cuerpoCorreo = """Hola ${tecnico}<br/><br/>

Tiene una nueva solicitud para su atención:<br/><br/>

Folio: ${solicitudDetalleInstance.idSolicitud}<br/>
Fecha de Acuse: ${solicitudDetalleInstance.idSolicitud.fechaRevisa.
  format('dd/MM/yy hh:mm')} hrs.<br/>
Tiempo de Atención: ${solicitudDetalleInstance.idServ.tiempo2}
  ${solicitudDetalleInstance.idServ.unidades2.descripcion}<br/>
Prioridad: Alta<br/><br/>

utilice la liga siguiente para atenderla. <br/><br/>
<a href='${liga}'>${solicitudDetalleInstance.idSolicitud}</a>
          """
        firmadoService.sendMailHTML(correo, asunto, cuerpoCorreo)

        flash.message = message(code: 'default.updated.message', args: [message(code: 'solicitudDetalle.label', default: 'SolicitudDetalle'), solicitudDetalleInstance.toString()])
        redirect(action: "listDetalle")
    }

}
