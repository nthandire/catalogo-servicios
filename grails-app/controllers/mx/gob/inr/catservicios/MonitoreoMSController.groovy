package mx.gob.inr.catservicios

import org.grails.plugin.easygrid.Easygrid
import org.grails.plugin.easygrid.Filter
import grails.plugins.springsecurity.Secured
import org.springframework.dao.DataIntegrityViolationException
import groovy.time.TimeCategory

@Secured(['ROLE_SAST_GESTOR'])
class MonitoreoMSController {
    static nombreMenu = "Administración de servicio"
    static ordenMenu = 89

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        log.debug("params = $params")
        def query =
            "  from Solicitud              " +
            " where estado <> 'F'          " +
            "   and estado is not null     "
        def solicitudes = Solicitud.executeQuery(query) //.collect{it.id}
        log.debug("numero de solicitudes = ${solicitudes.size()}")
        def queryDetalle =
            "  from SolicitudDetalle                 " +
            " where idSolicitud in (:solicitudes)    "

        def detalles = SolicitudDetalle.executeQuery (
          "select count (*) " + queryDetalle, [solicitudes: solicitudes])[0]
        log.debug("numero de detalles = ${detalles}")
        //query += " order by fechaSolicitud desc"
        [detallesInstanceList: Solicitud.executeQuery(queryDetalle, [solicitudes: solicitudes], params),
            detallesInstanceTotal: detalles, bOffset: params.offset]
    }

  def showDetalle(Long id) {
    log.debug("params = $params")
    def solicitudDetalleInstance = SolicitudDetalle.get(id)
    if (!solicitudDetalleInstance) {
        flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudDetalle.label', default: 'SolicitudDetalle'), id])
        redirect(action: "list")
        return
    }

    log.debug("bOffset = $params.offset")
    [solicitudDetalleInstance: solicitudDetalleInstance, bOffset: params.offset]
  }

}