package mx.gob.inr.catservicios

import grails.plugins.springsecurity.Secured
import org.springframework.dao.DataIntegrityViolationException

@Secured(['ROLE_SAST_USUARIO'])
class SolicitudArchivoadjuntoController {
    def springSecurityService
    static nombreMenu = "Solicitud Archivos adjuntos"
    static ordenMenu = -82

    static allowedMethods = [save: "POST", update: "POST", x_delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [solicitudArchivoadjuntoInstanceList: SolicitudArchivoadjunto.list(params), solicitudArchivoadjuntoInstanceTotal: SolicitudArchivoadjunto.count()]
    }

    def create() {
      def solicitudArchivoadjuntoInstance = new SolicitudArchivoadjunto()
      def solicitud = Solicitud.get(params.solicitud["id"])
      solicitudArchivoadjuntoInstance.idSolicitud = solicitud
      [solicitudArchivoadjuntoInstance: solicitudArchivoadjuntoInstance]
    }

    def save() {
      def file = request.getFile('file')
      def solicitudArchivoadjuntoInstance = new SolicitudArchivoadjunto()
      def solicitud = Solicitud.get(params.idSolicitud)
      solicitudArchivoadjuntoInstance.idSolicitud = solicitud
      if(file.empty) {
        flash.message = "Debe enviar algún archivo"
        render(view: "create", model: [solicitudArchivoadjuntoInstance:
                                       solicitudArchivoadjuntoInstance])
        return
      } else {
        def nombre = file.originalFilename
        solicitudArchivoadjuntoInstance.nombre = nombre
        solicitudArchivoadjuntoInstance.datos = file.getBytes()
        solicitudArchivoadjuntoInstance.tamanio =
          solicitudArchivoadjuntoInstance.datos.size()

        if (solicitudArchivoadjuntoInstance.tamanio > 5242880) {
          flash.error = "No puede subir archivos de más de 5 MB"
          render(view: "create", model: [solicitudArchivoadjuntoInstance:
                                         solicitudArchivoadjuntoInstance])
          return
        }

        solicitudArchivoadjuntoInstance.idUsuario = springSecurityService.principal.id
        solicitudArchivoadjuntoInstance.ipTerminal = request.getRemoteAddr()
        def dot = nombre.lastIndexOf('.');
        if (dot > 0)
          solicitudArchivoadjuntoInstance.tipo = nombre.substring(dot + 1).
            toUpperCase()
        else
          solicitudArchivoadjuntoInstance.tipo = ""
        if (!solicitudArchivoadjuntoInstance.save(flush: true)) {
             render(view: "create", model: [solicitudArchivoadjuntoInstance:
                                            solicitudArchivoadjuntoInstance])
            return
        }
        redirect (controller: "solicitud", action:'show', id: solicitud.id)
      }
    }

    def show(Long id) {
        def solicitudArchivoadjuntoInstance = SolicitudArchivoadjunto.get(id)
        if (!solicitudArchivoadjuntoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudArchivoadjunto.label', default: 'SolicitudArchivoadjunto'), id])
            redirect(action: "list")
            return
        }

        [solicitudArchivoadjuntoInstance: solicitudArchivoadjuntoInstance]
    }

    def download(long id) {
        SolicitudArchivoadjunto solicitudArchivoadjuntoInstance =
          SolicitudArchivoadjunto.get(id)
        if ( SolicitudArchivoadjunto == null) {
            flash.message = "Documento no encontrado."
            redirect (controller: "solicitud", action:'list')
        } else {
            response.setContentType("APPLICATION/OCTET-STREAM")
            response.setHeader("Content-Disposition",
              "Attachment;Filename=\"${solicitudArchivoadjuntoInstance.nombre}\"")

            def outputStream = response.getOutputStream()
            outputStream << solicitudArchivoadjuntoInstance.datos
            outputStream.flush()
            outputStream.close()
        }
    }

    def x_delete(Long id) {
        def solicitudArchivoadjuntoInstance = SolicitudArchivoadjunto.get(id)
        if (!solicitudArchivoadjuntoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudArchivoadjunto.label', default: 'SolicitudArchivoadjunto'), id])
            redirect(action: "list")
            return
        }

        try {
            solicitudArchivoadjuntoInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'solicitudArchivoadjunto.label', default: 'SolicitudArchivoadjunto'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'solicitudArchivoadjunto.label', default: 'SolicitudArchivoadjunto'), id])
            redirect(action: "show", id: id)
        }
    }
}
