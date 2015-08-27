package mx.gob.inr.catservicios

import org.springframework.dao.DataIntegrityViolationException

class SolicitudArchivoadjuntoController {
    static nombreMenu = "Solicitud Archivos adjuntos"
    static ordenMenu = 82

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
      if(file.empty) {
        flash.message = "No puede ser vacío"
      } else {
        def solicitudArchivoadjuntoInstance = new SolicitudArchivoadjunto()
        def solicitud = Solicitud.get(params.idSolicitud)
        solicitudArchivoadjuntoInstance.idSolicitud = solicitud
        def nombre = file.originalFilename
        solicitudArchivoadjuntoInstance.nombre = nombre
        solicitudArchivoadjuntoInstance.datos = file.getBytes()
        solicitudArchivoadjuntoInstance.tamaño = 
          solicitudArchivoadjuntoInstance.datos.size()
        def dot = nombre.lastIndexOf('.');
        solicitudArchivoadjuntoInstance.tipo = nombre.substring(dot + 1).
          toUpperCase();
        solicitudArchivoadjuntoInstance.save()
      }
      redirect (controller: "solicitud", action:'show', id: 1)
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
            redirect (controller: "solicitud", action:'show', id: 1) // TODO: apuntar al id correcto
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
