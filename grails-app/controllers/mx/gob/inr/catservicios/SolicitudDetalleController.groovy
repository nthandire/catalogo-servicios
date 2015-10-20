package mx.gob.inr.catservicios

import grails.plugins.springsecurity.Secured
import org.springframework.dao.DataIntegrityViolationException

@Secured(['ROLE_SAST_USUARIO'])
class SolicitudDetalleController {
    static nombreMenu = "Solicitud Detalle"
    static ordenMenu = -81

    static allowedMethods = [save: "POST", update: "POST", x_delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [solicitudDetalleInstanceList: SolicitudDetalle.list(params), solicitudDetalleInstanceTotal: SolicitudDetalle.count()]
    }

    def create() {
      def solicitudDetalleInstance = new SolicitudDetalle(params)
      def solicitud = Solicitud.get(params.solicitud["id"])
      solicitudDetalleInstance.idSolicitud = solicitud

      def query =
          "  from Cat_servCat c               \n" +
          " where exists                      \n" +
          "      ( from Cat_servSub s,        \n" +
          "             Cat_serv t            \n" +
          "       where s.id = t.servSub      \n" +
          "         and t.incidente = 'f'     \n" +
          "      )                            \n"
      log.debug("query = \n${query}")

      def categorias = Cat_servCat.executeQuery(query)
      log.debug("numero de categorias = ${categorias.size()}")

      [solicitudDetalleInstance: solicitudDetalleInstance,
          categorias: categorias]
    }

    def save() {
        log.debug("params = $params")
        def paramsFiltrado = params.findAll {it.key != 'idSolicitud' && (it.key != 'idResguardoentregadetalle' || it.value )}
        log.debug("paramsFiltrado = $paramsFiltrado")
        def solicitudDetalleInstance = new SolicitudDetalle(paramsFiltrado)
        def solicitud = Solicitud.get(params.idSolicitud)
        solicitudDetalleInstance.idSolicitud = solicitud
        solicitudDetalleInstance.estado = 'A' as char
        if (!solicitudDetalleInstance?.idServcat?.id) {
            flash.error = "Debe capturar la categoría de su solicitud"
            render(view: "create", model: [solicitudDetalleInstance: solicitudDetalleInstance])
            return
        }
        if (!solicitudDetalleInstance.save(flush: true)) {
            render(view: "create", model: [solicitudDetalleInstance: solicitudDetalleInstance])
            return
        }

        flash.message = message(code: 'default.created.message',
            args: [message(code: 'solicitudDetalle.label',
                default: 'SolicitudDetalle'), solicitudDetalleInstance.toString()])
        redirect (controller: "solicitud", action:'show', id: solicitud.id)
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

        def query =
            "  from Cat_servCat c               \n" +
            " where exists                      \n" +
            "      ( from Cat_servSub s,        \n" +
            "             Cat_serv t            \n" +
            "       where s.id = t.servSub      \n" +
            "         and t.incidente = 'f'     \n" +
            "      )                            \n"
        log.debug("query = \n${query}")

        def categorias = Cat_servCat.executeQuery(query)
        log.debug("numero de categorias = ${categorias.size()}")

        [solicitudDetalleInstance: solicitudDetalleInstance,
            categorias: categorias]
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

        log.debug("params = $params")
        def paramsFiltrado = params.findAll {it.key != 'idResguardoentregadetalle' || it.value}
        log.debug("paramsFiltrado = $paramsFiltrado")
        solicitudDetalleInstance.properties = paramsFiltrado

        if (!solicitudDetalleInstance.save(flush: true)) {
            render(view: "edit", model: [solicitudDetalleInstance: solicitudDetalleInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'solicitudDetalle.label', default: 'SolicitudDetalle'), solicitudDetalleInstance.toString()])
        redirect(controller: "solicitud", action: "show", id: solicitudDetalleInstance.idSolicitud.id)
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
