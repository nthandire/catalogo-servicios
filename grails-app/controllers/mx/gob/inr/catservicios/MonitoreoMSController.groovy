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
    static ordenMenu = -89 // TODO: Borrar este controlador

    def iniciarSemaforos() {
      if (!(session["semaforoPeligro"])) {
        log.debug("Inicio semaforo")
        session["semaforoPeligro"] = g.message(code:"semaforo.peligro").toLong()
        session["semaforoSeguro"] = g.message(code:"semaforo.seguro").toLong()
      }

      if (!(session["semaforo"])) {
        log.debug("Inicio semaforo")
        session["semaforo"] = Semaforo.list().sort{-it.min}
        log.debug("session['semaforo'] = ${session['semaforo']}")
      }
    }


    def index() {
      iniciarSemaforos()
      redirect(action: "listMonitoreo", params: params)
    }

    def listMonitoreo(Integer max) {
        params.max = Math.min(max ?: 50, 100)
        if (!params.offset) {
          params["offset"] = 0
        } else {
          params.offset = params.offset.toLong()
        }
        log.debug("params = $params")

        def query =
            "  from Solicitud                " +
            " where estado in ('A','V','R')  "
        def solicitudes = Solicitud.executeQuery(query)
        log.debug("numero de solicitudes = ${solicitudes.size()}")
        def queryDetalle =
            "  from SolicitudDetalle                 " +
            " where idSolicitud in (:solicitudes)    "
        def detallesList = Solicitud.executeQuery(queryDetalle, [solicitudes: solicitudes])
        def detalles = detallesList.size()
        log.debug("numero de detalles = ${detalles}")
        def semaforo = session["semaforo"]
        def listaOrdenar = detallesList.collect{new Ordenado(caso: it,
                              orden: firmadoService.retraso(semaforo, it))}
        listaOrdenar.each{it.color = semaforo[it.orden]? semaforo[it.orden].color :"white"}
        def listaOrdenada = listaOrdenar.sort{a,b -> a.orden == b.orden ?
          a.caso.idSolicitud.fechaSolicitud <=> b.caso.idSolicitud.fechaSolicitud :
          a.orden <=> b.orden }
        log.debug("listaOrdenada[0] = ${listaOrdenada[0]}, color = ${listaOrdenada[0].color}")

        [detallesInstanceList: listaOrdenada[params.offset..Math.min(params.offset+params.max-1,listaOrdenada.size()-1)],
          detallesInstanceTotal: detalles, bOffset: params.offset]
    }

    def listPorFolio(Integer max) {
        params.max = Math.min(max ?: 50, 100)
        if (!params.offset)
          params["offset"] = 0
        else {
          params.offset = params.offset.toLong()
        }
        log.debug("params = $params")

        def query =
            "  from Solicitud                " +
            " where estado in ('A','V','R')  "
        def solicitudes = Solicitud.executeQuery(query)
        log.debug("numero de solicitudes = ${solicitudes.size()}")
        def queryDetalle =
            "  from SolicitudDetalle                 " +
            " where idSolicitud in (:solicitudes)    "
        def detallesList = Solicitud.executeQuery(queryDetalle, [solicitudes: solicitudes])
        def detalles = detallesList.size()
        log.debug("numero de detalles = ${detalles}")
        def semaforo = session["semaforo"]
        def listaOrdenar = detallesList.collect{new Ordenado(caso: it,
                              orden: firmadoService.retraso(semaforo, it))}
        listaOrdenar.each{it.color = semaforo[it.orden]? semaforo[it.orden].color :"white"}
        def listaOrdenada = listaOrdenar.sort{it.caso.idSolicitud.numeroSolicitud}
        log.debug("listaOrdenada[0] = ${listaOrdenada[0]}, color = ${listaOrdenada[0].color}")

        [detallesInstanceList: listaOrdenada[params.offset..Math.min(params.offset+params.max-1,listaOrdenada.size()-1)],
          detallesInstanceTotal: detalles, bOffset: params.offset]
    }

    def listPorEstado(Integer max) {
        params.max = Math.min(max ?: 50, 100)
        if (!params.offset)
          params["offset"] = 0
        else {
          params.offset = params.offset.toLong()
        }
        log.debug("params = $params")

        def query =
            "  from Solicitud                " +
            " where estado in ('A','V','R')  "
        def solicitudes = Solicitud.executeQuery(query)
        log.debug("numero de solicitudes = ${solicitudes.size()}")
        def queryDetalle =
            "  from SolicitudDetalle                 " +
            " where idSolicitud in (:solicitudes)    "
        def detallesList = Solicitud.executeQuery(queryDetalle, [solicitudes: solicitudes])
        def detalles = detallesList.size()
        log.debug("numero de detalles = ${detalles}")
        def semaforo = session["semaforo"]
        def listaOrdenar = detallesList.collect{new Ordenado(caso: it,
                              orden: firmadoService.retraso(semaforo, it))}
        listaOrdenar.each{it.color = semaforo[it.orden]? semaforo[it.orden].color :"white"}
        def listaOrdenada = listaOrdenar.sort{a,b -> a.caso.idSolicitud.estado == b.caso.idSolicitud.estado ?
          a.caso.idSolicitud.fechaSolicitud <=> b.caso.idSolicitud.fechaSolicitud :
          a.caso.idSolicitud.estado <=> b.caso.idSolicitud.estado}
        log.debug("listaOrdenada[0] = ${listaOrdenada[0]}, color = ${listaOrdenada[0].color}")

        [detallesInstanceList: listaOrdenada[params.offset..Math.min(params.offset+params.max-1,listaOrdenada.size()-1)],
          detallesInstanceTotal: detalles, bOffset: params.offset]
    }

  def listIncidentes(Integer max) {
    params.max = Math.min(max ?: 10, 100)
    if (!params.offset) {
      params["offset"] = 0
    } else {
      params.offset = params.offset.toLong()
    }
    log.debug("params = $params")

    def query =
      "  from Incidente                " +
      " where estado = 'A'             "
    def incidentesList = Incidente.executeQuery(query)
    def incidentes = incidentesList.size()
    log.debug("numero de incidentes = ${incidentes}")
    def semaforo = session["semaforo"]
    def listaOrdenar = incidentesList.collect{new IncidenteOrdenado(caso: it,
                          orden: 0)} // firmadoService.retraso(semaforo, it))}
    listaOrdenar.each{it.color = semaforo[it.orden]? semaforo[it.orden].color :"white"}
    def listaOrdenada = listaOrdenar.sort{a,b -> a.orden == b.orden ?
      a.caso.fechaIncidente <=> b.caso.fechaIncidente :
      a.orden <=> b.orden }
    log.debug("listaOrdenada[0] = ${listaOrdenada[0]}, color = ${listaOrdenada[0].color}")

    [incidentesInstanceList: listaOrdenada[params.offset..Math.min(params.offset+params.max-1,listaOrdenada.size()-1)],
      incidentesInstanceTotal: incidentes, bOffset: params.offset]
  }

  def showDetalle(Long id) {
    log.debug("params = $params")
    def solicitudDetalleInstance = SolicitudDetalle.get(id)
    if (!solicitudDetalleInstance) {
        flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudDetalle.label', default: 'SolicitudDetalle'), id])
        redirect(action: "listMonitoreo")
        return
    }

    log.debug("bOffset = $params.offset")
    [solicitudDetalleInstance: solicitudDetalleInstance, bOffset: params.offset]
  }

  def showDetallePorFolio(Long id) {
    log.debug("params = $params")
    def solicitudDetalleInstance = SolicitudDetalle.get(id)
    if (!solicitudDetalleInstance) {
        flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudDetalle.label', default: 'SolicitudDetalle'), id])
        redirect(action: "listPorFolio")
        return
    }

    log.debug("bOffset = $params.offset")
    [solicitudDetalleInstance: solicitudDetalleInstance, bOffset: params.offset]
  }

  def correo(Long id) {
    enviaCorreo(id)

    redirect(action: "listMonitoreo", params: params)
  }

  def correoFolio(Long id) {
    enviaCorreo(id)

    redirect(action: "listPorFolio", params: params)
  }

  def correoEstado(Long id) {
    enviaCorreo(id)

    redirect(action: "listPorEstado", params: params)
  }

  def enviaCorreo(Long id) {
    log.debug("params = $params, id = $id")
    def asunto = "Aviso de servicio retrasado"
    def caso = SolicitudDetalle.get(id)
    def usuarios = []
    if (caso.idSolicitud.estado == 'R' as char) {
      if (caso.idTecnico) {
        usuarios << Usuario.get(caso.idTecnico)
      } else {
        usuarios = firmadoService.aprobadores(caso.idServ?.servResp2)
      }
    } else if (caso.idSolicitud.estado == 'V' as char) {
      usuarios = firmadoService.aprobadores(caso.idServ?.servResp2)
    } else /* estado == 'A' */ if (caso.idSolicitud.idVb) {
      usuarios << Usuario.get(caso.idSolicitud.idVb)
    } else {
      usuarios = firmadoService.aprobadores(caso.idServ?.servResp1)
    }

    usuarios.each {
      def correo = it.correo ?:
                     grailsApplication.config.correo.general
      def msg = """Hola ${it},

      El requerimiento con No. de folio ${caso.idSolicitud} a rebasado el tiempo de atención acordado, por lo que se solicita se atienda a la brevedad"""
      firmadoService.sendMail(correo, asunto, msg)
    }
  }

}
