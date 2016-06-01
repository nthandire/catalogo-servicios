package mx.gob.inr.catservicios

import grails.plugins.springsecurity.Secured
import org.springframework.dao.DataIntegrityViolationException

@Secured(['ROLE_SAST_TECNICO'])
class SolicitudTecnicoController {
    def springSecurityService
    def grailsApplication
    def firmadoService
    static nombreMenu = "Solucionar Requerimientos"
    static ordenMenu = 86

    static allowedMethods = [save: "POST", update: "POST", x_delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        log.debug("params = $params")
        def userID = springSecurityService.principal.id
        log.debug("userID = $userID")

        def solicitudes = SolicitudDetalle.findAllByIdTecnicoAndFechaSolucionIsNull((Integer)userID)
        log.debug("lista solicitudes = ${solicitudes.collect{"$it.id:$it"}}")

        log.debug("params['sort'] = ${params['sort']}")
        switch (params['sort']) {
          case null:
          case "numeroSolicitud":
            log.debug("numeroSolicitud")
            solicitudes.sort{it.idSolicitud.paraOrdenar()}
          break
          case "lastUpdated":
            log.debug("fechaSolicitud")
            solicitudes.sort{it.fechaSolicitud ?: it.lastUpdated}
          break
          case "justificacion":
            log.debug("justificacion")
            solicitudes.sort{it?.justificacion}
          break
          case "estado":
            log.debug("estado")
            solicitudes.sort{it?.estado}
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

    def create() {
      log.debug("params = $params")
      def solicitudArchivoadjuntoInstance = new SolicitudArchivoadjunto()
      def solicitud = Solicitud.get(params.solicitud["id"])
      solicitudArchivoadjuntoInstance.idSolicitud = solicitud
      [solicitudArchivoadjuntoInstance: solicitudArchivoadjuntoInstance, back:params.detalle["id"]]
    }

    def save() {
      log.debug("params = $params")
      def file = request.getFile('file')
      if(file.empty) {
        flash.message = "Debe enviar algún archivo"
        render(view: "create")
        return
      } else {
        def solicitudArchivoadjuntoInstance = new SolicitudArchivoadjunto()
        def solicitud = Solicitud.get(params.idSolicitud)
        solicitudArchivoadjuntoInstance.idSolicitud = solicitud
        def nombre = file.originalFilename
        solicitudArchivoadjuntoInstance.nombre = nombre
        solicitudArchivoadjuntoInstance.datos = file.getBytes()
        solicitudArchivoadjuntoInstance.tamanio =
          solicitudArchivoadjuntoInstance.datos.size()
        solicitudArchivoadjuntoInstance.idUsuario = springSecurityService.principal.id
        solicitudArchivoadjuntoInstance.ipTerminal = request.getRemoteAddr()
        def dot = nombre.lastIndexOf('.');
        if (dot > 0)
          solicitudArchivoadjuntoInstance.tipo = nombre.substring(dot + 1).
            toUpperCase()
        else
          solicitudArchivoadjuntoInstance.tipo = ""
        if (!solicitudArchivoadjuntoInstance.save(flush: true)) {
            render(view: "create", model: [solicitudArchivoadjuntoInstance: solicitudArchivoadjuntoInstance])
            return
        }
        redirect (action:'edit', id: params.idDetalle)
      }
    }

    def edit(Long id) {
      log.debug("params = $params")
      def solicitudDetalleInstance = SolicitudDetalle.get(id)
      if (!solicitudDetalleInstance) {
          flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudDetalle.label', default: 'SolicitudDetalle'), id])
          redirect(action: "list")
          return
      }

      def resg = null
      if (solicitudDetalleInstance?.idResguardoentregadetalle) {
        log.debug("idResguardoentregadetalle = ${solicitudDetalleInstance?.idResguardoentregadetalle}")
        resg = ResguardoEntregaDetalle.executeQuery(
          'from ResguardoEntregaDetalle where id = ?',
          solicitudDetalleInstance?.idResguardoentregadetalle)[0]
      }
      log.debug("resg = ${resg}")

      def userID = springSecurityService.principal.id
      def area = UsuarioAutorizado.get(userID)["area"]
      log.debug("area = ${area}")
      def areaLab = message(code: "area.de.laboratorio")
      log.debug("areaLab = ${areaLab}")
      def minPrograma = message(code: "laboratorio.programa.normal").toInteger()
      if (area == areaLab)
        minPrograma = message(code: "laboratorio.programa.DGAIT").toInteger()
      log.debug("minPrograma = ${minPrograma}")
      def programas = CatPrograma.findAllByIdGreaterThanEquals(minPrograma)
      programas = programas.findAll{!it.desPrograma.contains("Atención de Servicio") }
      log.debug("programas = ${programas}")

      [solicitudDetalleInstance: solicitudDetalleInstance,
        equipo: resg,
        programas: programas]
    }

    def update(Long id, Long version) {
        def solicitudDetalleInstance = SolicitudDetalle.get(id)
        if (!solicitudDetalleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudDetalle.label', default: 'SolicitudDetalle'), id])
            redirect(action: "list")
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
        if (userID != solicitudDetalleInstance.idTecnico) {
            flash.error = "Usted no tiene asignada esta solicitud"
            redirect(action: "list")
            return
        }

        solicitudDetalleInstance.properties = params

        if (!solicitudDetalleInstance.save(flush: true)) {
            render(view: "edit", model: [solicitudDetalleInstance: solicitudDetalleInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'solicitudDetalle.label', default: 'SolicitudDetalle'), solicitudDetalleInstance.toString()])
        redirect(action: "edit", id: id)
    }

    def solucionar(Long id) {
        def solicitudDetalleInstance = SolicitudDetalle.get(id)
        if (!solicitudDetalleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudDetalle.label', default: 'SolicitudDetalle'), id])
            redirect(action: "list")
            return
        }

        def userID = springSecurityService.principal.id
        log.debug("userID = $userID")

        def firmaTeclada = params['passwordfirma']
        log.debug("firmaTeclada = $firmaTeclada")
        def firma = Firmadigital.findById(userID)?.passwordfirma?.reverse()
        log.debug("firma = $firma")

        solicitudDetalleInstance.properties = params

        if (firmaTeclada != firma) {
            flash.error = "Error en contaseña"
            render(view: "edit", model: [solicitudDetalleInstance: solicitudDetalleInstance])
            return
        }

        if (!solicitudDetalleInstance?.idPrograma) {
          solicitudDetalleInstance.errors.rejectValue("idPrograma", "no.error.estandar",
                    "Debe capturar el Estado de cierre")
          render(view: "edit", model: [solicitudDetalleInstance: solicitudDetalleInstance])
          return
        }

        if (!solicitudDetalleInstance?.solucion) {
          solicitudDetalleInstance.errors.rejectValue("solucion", "no.error.estandar",
                    "Debe capturar la solución")
          render(view: "edit", model: [solicitudDetalleInstance: solicitudDetalleInstance])
          return
        }

        solicitudDetalleInstance.fechaSolucion = new Date()

        if (!solicitudDetalleInstance.save(flush: true)) {
            render(view: "edit", model: [solicitudDetalleInstance: solicitudDetalleInstance])
            return
        }

        def solicitud = solicitudDetalleInstance.idSolicitud
        log.debug("solicitud = $solicitud")
        def detallesPendientes =
          SolicitudDetalle.
            countByIdSolicitudAndFechaSolucionIsNullAndEstado(solicitud,
                                                              'A' as char)
        log.debug("detallesPendientes = $detallesPendientes")
        if (!detallesPendientes) {
            solicitud.estado = 'E' as char
            if (!solicitud.save(flush: true)) {
                log.debug("Fallo salvado de solicitud, ${solicitud.errors}")
                redirect(action: "list")
                return
            }

            def solicitante = Usuario.get(solicitud.idSolicitante)
            def liga = createLink(controller:"SolicitudEncuesta", action: "edit",
                                  id: solicitud.id, absolute: "true")
            log.debug("liga = ${liga}")
            def asunto = "La solicitud ${solicitud} ya ha sido atendida"
            def cuerpoCorreo = """Hola ${solicitante}<br/><br/>

Su solicitud ${solicitud} ya ha sido atendida, para mejorar la calidad del servicio se solicita conteste la siguiente encuesta, usando la siguiente liga:<br/><br/>

<a href='${liga}'>${solicitud}</a>
<br/><br/>
Se le recuerda que solo tiene una semana para contestar dicha encuesta, después de ese tiempo, la encuesta se dará por satisfactoria.
              """

            def correo = firmadoService.correo(solicitante.idEmpleado)
            firmadoService.sendMailHTML(correo, asunto, cuerpoCorreo)
        }

        flash.message = message(code: 'default.updated.message',
                                args: [message(code: 'solicitudDetalle.label',
                                               default: 'SolicitudDetalle'),
                                solicitudDetalleInstance.toString()])
        redirect(action: "list")
    }

}
