package mx.gob.inr.catservicios

import grails.plugins.springsecurity.Secured
import org.springframework.dao.DataIntegrityViolationException
import groovy.time.TimeCategory

@Secured(['ROLE_SAST_ADMIN'])
class ReportesController {
    def springSecurityService
    static nombreMenu = "Reportes"
    static ordenMenu = 120

    static allowedMethods = [save: "POST", update: "POST", x_delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        []
    }

  def reporteResultadosYSeguimiento() {
    def data = []
    params.image_dir = "${servletContext.getRealPath('/images')}/"
    params.tipoServicio = "Requerimientos" //"Incidentes"
    params.preg1 = g.message(code:"solicitud.p01.label", default:"Numero Solicitud")
    params.preg2 = g.message(code:"solicitud.p02.label", default:"Numero Solicitud")
    params.preg3 = g.message(code:"solicitud.p03.label", default:"Numero Solicitud")
    params.preg4 = g.message(code:"solicitud.p04.label", default:"Numero Solicitud")

    def startDate = params.startDate
    startDate[Calendar.DATE] = 1
    startDate[Calendar.HOUR_OF_DAY] = 0
    startDate[Calendar.MINUTE] = 0
    def endDate = startDate.clone()
    use(TimeCategory) {
      endDate = endDate + 1.month - 1.seconds
    }
    params["mes"] = startDate.format('MMMM').toString()
    params["anio"] = startDate.format('YYYY')
    params["preg1Si"] = contar(requerimientos(startDate, endDate),"p01", 1)
    params["preg1No"] = contar(requerimientos(startDate, endDate),"p01", 2)
    params["preg2Si"] = contar(requerimientos(startDate, endDate),"p02", 1)
    params["preg2No"] = contar(requerimientos(startDate, endDate),"p02", 2)
    params["preg3Si"] = contar(requerimientos(startDate, endDate),"p03", 1)
    params["preg3No"] = contar(requerimientos(startDate, endDate),"p03", 2)
    params["preg4Si"] = contar(requerimientos(startDate, endDate),"p04", 1)
    params["preg4No"] = contar(requerimientos(startDate, endDate),"p04", 2)
    log.debug("startDate = $startDate")
    log.debug("endDate = $endDate")
    log.debug("params = $params")

    def lista =  [] //Cat_bitacora.findAllByLastUpdatedGreaterThanEqualsAndLastUpdatedLessThanEquals(startDate, endDate)

    lista.each { it ->
      def rowBitacora = new RptBitacora (  // Esta clase esta en Cat_bitacoraController
        num_solicitud: it.folio,
        fecha: it.lastUpdated,
        servicio:it.servicio?.descripcion,
        subcategoria:it.servicio?.servSub?.descripcion,
        categoria:it.servicio?.servSub?.servCat?.categoria,
        descripcion:it.descripcion,
        observaciones:it.descripcion,
        responsable:Usuario.get(it.idUsuario),
      )
      data.add(rowBitacora)
    }

    chain (controller:"jasper", action:"index", model:[data:data], params:params)
  }

  def requerimientos(Date startDate, Date endDate) {
    if (!request["listaRequerimientos"]) {
      log.debug("si lei los requerimientos")
      request["listaRequerimientos"] = Solicitud.findAllByEstadoAndLastUpdatedBetween('T' as char, startDate, endDate)
      log.debug("requerimientos = ${request["listaRequerimientos"]}")
    }
    request["listaRequerimientos"]
  }

  Integer contar(ArrayList lista, String campo, Integer valor) {
    def cuantos = 0
    lista.each {
      if (it[campo] == valor) cuantos++
    }
    cuantos
  }

}

