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
        def autorizados = Solicitud.executeQuery (
          "select count (*) " + query
        )[0]
        log.debug("numero de autorizados = ${autorizados}")
        query += " order by fechaSolicitud desc"
        [autorizadosInstanceList: Solicitud.executeQuery(query, [], params),
            autorizadosInstanceTotal: autorizados]
    }
}