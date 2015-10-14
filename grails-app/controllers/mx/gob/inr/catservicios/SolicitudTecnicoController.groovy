package mx.gob.inr.catservicios

import grails.plugins.springsecurity.Secured
import org.springframework.dao.DataIntegrityViolationException

@Secured(['ROLE_SAST_TECNICO'])
class SolicitudTecnicoController {
    def springSecurityService
    static nombreMenu = "Solucionar Solicitudes"
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
        [solicitudDetalleInstanceList: SolicitudDetalle.findAllByIdTecnicoAndFechaSolucionIsNull((Integer)userID, params),
            solicitudDetalleInstanceTotal: SolicitudDetalle.countByIdTecnicoAndFechaSolucionIsNull((Integer)userID)]
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
        redirect (action:'show', id: solicitud.id)
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
      log.debug("programas = ${programas}")

      [solicitudDetalleInstance: solicitudDetalleInstance,
        equipo: resg?resg.toString():'',
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
        def firma = Firmadigital.findById(userID)?.passwordfirma
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
          SolicitudDetalle.countByIdSolicitudAndFechaSolucionIsNull(solicitud)
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
            log.debug("asunto = ${asunto}")
            def msg = "Hola ${solicitante}<br/><br/>La solicitud folio " +
              "${solicitud} ya ha sido atendida, es necesario que llenes " +
              "la forma de encuesta, usando la siguiente liga: <br/><br/>" +
              "<a href='${liga}'>${solicitud}</a>"
            log.debug("msg = ${msg}")
            sendMail {
              to message(code: "correo.general") // TODO: mandar el correo al que solicito       solicitante.correo
              subject asunto
              html msg
            }

        }

        flash.message = message(code: 'default.updated.message',
                                args: [message(code: 'solicitudDetalle.label',
                                               default: 'SolicitudDetalle'),
                                solicitudDetalleInstance.toString()])
        redirect(action: "list")
    }

    def x_delete(Long id) {
        def solicitudDetalleInstance = SolicitudDetalle.get(id)
        if (!solicitudDetalleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudDetalle.label', default: 'SolicitudDetalle'), id])
            redirect(action: "list")
            return
        }

        try {
            solicitudDetalleInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'solicitudDetalle.label', default: 'SolicitudDetalle'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'solicitudDetalle.label', default: 'SolicitudDetalle'), id])
            redirect(action: "show", id: id)
        }
    }
}
