package mx.gob.inr.catservicios

import grails.plugins.springsecurity.Secured
import org.springframework.dao.DataIntegrityViolationException

@Secured(['ROLE_SAST_TECNICO','ROLE_SAST_TECNICO_MESA_SERVICIO'])
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
        solicitudArchivoadjuntoInstance.tamaño =
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

    def show(Long id) {
        def solicitudDetalleInstance = SolicitudDetalle.get(id)
        if (!solicitudDetalleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudDetalle.label', default: 'SolicitudDetalle'), id])
            redirect(action: "list")
            return
        }

        [solicitudDetalleInstance: solicitudDetalleInstance]
    }

    def edit(Long id) {
        def solicitudDetalleInstance = SolicitudDetalle.get(id)
        if (!solicitudDetalleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudDetalle.label', default: 'SolicitudDetalle'), id])
            redirect(action: "list")
            return
        }

        def resg = ""
        if (solicitudDetalleInstance?.idResguardoentregadetalle) {
          log.debug("idResguardoentregadetalle = $solicitudDetalleInstance?.idResguardoentregadetalle")
          resg = ResguardoEntregaDetalle.executeQuery('from ResguardoEntregaDetalle where id = ?', solicitudDetalleInstance?.idResguardoentregadetalle)[0]
        }

        log.debug{"Resg = $resg"}
        [solicitudDetalleInstance: solicitudDetalleInstance,
          equipo: resg?resg.toString():'']
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

        solicitudDetalleInstance.properties = params

        if (!solicitudDetalleInstance?.idPrograma) {
          solicitudDetalleInstance.errors.rejectValue("idPrograma", "no.error.estandar",
                    "Debe capturar el programa o tipo de solución")
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
        def detalles = SolicitudDetalle.countByIdSolicitudAndFechaSolucionIsNull(solicitud)
        log.debug("detalles = $detalles")
        if (!detalles) {
            solicitud.estado = 'E' as char
            if (!solicitud.save(flush: true)) {
                log.debug("Fallo salvado de solicitud, ${solicitud.errors}")
                redirect(action: "list")
                return
            }
        }

/* TODO: habilitar cuando no este en desarrollo {copiado de otro lado}
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
