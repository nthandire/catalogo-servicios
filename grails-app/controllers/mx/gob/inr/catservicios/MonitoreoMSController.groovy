package mx.gob.inr.catservicios

import org.grails.plugin.easygrid.Easygrid
import org.grails.plugin.easygrid.Filter
import grails.plugins.springsecurity.Secured
import org.springframework.dao.DataIntegrityViolationException
import groovy.time.TimeCategory

@Secured(['ROLE_SAST_GESTOR'])
class MonitoreoMSController {
    def firmadoService
    static nombreMenu = "Administración de servicio"
    static ordenMenu = 89

    def iniciarSemaforos() {
      if (!(session["semaforoPeligro"])) {
        log.debug("Inicio semaforo")
        session["semaforoPeligro"] = g.message(code:"semaforo.peligro").toLong()
        session["semaforoSeguro"] = g.message(code:"semaforo.seguro").toLong()
      }
    }


    def index() {
      iniciarSemaforos()
      redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        if (!params.offset)
          params["offset"] = 0
        else {
          params.offset = params.offset.toLong()
        }
        log.debug("params = $params")

        session['semaforoPeligro'] += -1
        session['semaforoSeguro'] += -1
        log.debug("session['semaforoPeligro'] = ${session['semaforoPeligro']}")
        log.debug("session['semaforoSeguro'] = ${session['semaforoSeguro']}")

        def query =
            "  from Solicitud              " +
            " where estado <> 'F'          " +
            "   and estado is not null     "
        def solicitudes = Solicitud.executeQuery(query)
        log.debug("numero de solicitudes = ${solicitudes.size()}")
        def queryDetalle =
            "  from SolicitudDetalle                 " +
            " where idSolicitud in (:solicitudes)    "

        def detalles = SolicitudDetalle.executeQuery (
          "select count (*) " + queryDetalle, [solicitudes: solicitudes])[0]
        log.debug("numero de detalles = ${detalles}")
        //query += " order by fechaSolicitud desc"
        def detallesList = Solicitud.executeQuery(queryDetalle, [solicitudes: solicitudes])
        def listaOrdenar = detallesList.collect{new Ordenado(caso: it,
                              orden: firmadoService.retraso(session, it))}
        def listaOrdenada = listaOrdenar.sort{a,b -> a.orden == b.orden ?
          a.caso.idSolicitud.fechaSolicitud <=> a.caso.idSolicitud.fechaSolicitud :
          a.orden <=> b.orden }
        log.debug("params.offset = ${params.offset}")
        log.debug("params.offset+params.max-1 = ${params.offset+params.max-1}")
        log.debug("listaOrdenada.size()-1 = ${listaOrdenada.size()-1}")
        def detallesOrdenadaList = listaOrdenada.collect{it.caso}

        [detallesInstanceList: listaOrdenada[params.offset..Math.min(params.offset+params.max-1,listaOrdenada.size()-1)],
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

class Ordenado {
  SolicitudDetalle caso
  Long orden

  String toString() {
    "$orden : [$caso]"
  }
}

