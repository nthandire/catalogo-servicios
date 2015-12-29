package mx.gob.inr.catservicios

import java.text.*

import grails.plugins.springsecurity.Secured
import org.springframework.dao.DataIntegrityViolationException
import groovy.time.TimeCategory

@Secured(['ROLE_SAST_ADMIN'])
class ReportesController {
    def firmadoService
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
    params["preg1Si"] = contar(requerimientosConEncuesta(startDate, endDate),"p01", 1)
    params["preg1No"] = contar(requerimientosConEncuesta(startDate, endDate),"p01", 2)
    params["preg2Si"] = contar(requerimientosConEncuesta(startDate, endDate),"p02", 1)
    params["preg2No"] = contar(requerimientosConEncuesta(startDate, endDate),"p02", 2)
    params["preg3Si"] = contar(requerimientosConEncuesta(startDate, endDate),"p03", 1)
    params["preg3No"] = contar(requerimientosConEncuesta(startDate, endDate),"p03", 2)
    params["preg4Si"] = contar(requerimientosConEncuesta(startDate, endDate),"p04", 1)
    params["preg4No"] = contar(requerimientosConEncuesta(startDate, endDate),"p04", 2)

    def locale = new Locale('es', 'MX')
    def dfs = new DecimalFormatSymbols(locale)
    def formato = new DecimalFormat("#,##0", dfs)
    params["recibidas"] = formato.format(firmadoService.recibidas(startDate, endDate))
    params["resueltas"] = formato.format(firmadoService.resueltas(startDate, endDate))
    params["pendientes"] = formato.format(firmadoService.pendientes(startDate, endDate))
    params["enTiempo"] = formato.format(firmadoService.enTiempo(startDate, endDate))
    params["satisfechos"] = formato.format(firmadoService.satisfechos(startDate, endDate))
    params["insatisfechos"] = formato.format(firmadoService.insatisfechos(startDate, endDate))


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

  def requerimientosConEncuesta(Date startDate, Date endDate) {
    if (!request["listaRequerimientos"]) {
      log.debug("si lei los requerimientos")
      request["listaRequerimientos"] =
        firmadoService.requerimientosConEncuesta(startDate, endDate)
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

  def reporteMonitoreoOLAySLA() {
    def data = []
    params.image_dir = "${servletContext.getRealPath('/images')}/"

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

    // TODO: Quitar
    // obtener todos los incidentes de ese mes 
    // def estados = ['T' as char, 'E' as char]
    // def incidentes = Incidente.findAllByEstadoInListAndLastUpdatedBetween(estados, startDate, endDate)
    // log.debug("incidentes = $incidentes")

    // los incidentes con soluciones en ese mes
    def query =
    //    " where upper(nombre) || case when paterno is null then  '' else ' ' || upper(paterno) end || case when materno is null then  '' else ' ' || upper(materno) end like '%${term}%'   "
      // " where upper(nombre) || ' ' || upper(nvl(paterno,''))  || ' ' || upper(nvl(materno,'')) like '%${term}%'   "
      "  from Incidente                             " +
      " where estado in ('T', 'E')                  " +
      "   and (fechaSolnivel1 between ? and ?       " +
      "        or fechaSolnivel2 between ? and ?    " +
      "        or fechaSolnivel3 between ? and ?    " +
      "       )                                     "
    log.debug("query = $query")
    def incidentes = Incidente.findAll(query, [startDate, endDate, startDate, endDate, startDate, endDate])
    log.debug("incidentes = $incidentes")

    // contar cuantas se resolvieron en primer nivel
    def inciResueltoPrimerList = incidentes.findAll { it.fechaSolnivel1 && !it.fechaSolnivel2 }
    log.debug("inciResueltoPrimerList = $inciResueltoPrimerList")
    Integer inciResueltoPrimer = inciResueltoPrimerList.size()

    def locale = new Locale('es', 'MX')
    def dfs = new DecimalFormatSymbols(locale)
    def formato = new DecimalFormat("#,##0", dfs)
    params["inciResueltoPrimer"] = formato.format(inciResueltoPrimer)

    // contar cuantas se resolvieron en segundo nivel
    def inciResueltoSegundoList = incidentes.findAll { it.fechaSolnivel2 && !it.fechaSolnivel3 }
    log.debug("inciResueltoSegundoList = $inciResueltoSegundoList")
    Integer inciResueltoSegundo = inciResueltoSegundoList.size()
    params["inciResueltoSegundo"] = formato.format(inciResueltoSegundo)

    // contar cuantas se resolvieron en tercer nivel
    def inciResueltoTercerList = incidentes.findAll { it.fechaSolnivel3 }
    log.debug("inciResueltoTercerList = $inciResueltoTercerList")
    Integer inciResueltoTercer = inciResueltoTercerList.size()
    params["inciResueltoTercer"] = formato.format(inciResueltoTercer)

    // contar cuantas se resolvieron en total
    Integer inciResueltoTotal = inciResueltoPrimer + inciResueltoSegundo +
        inciResueltoTercer
    log.debug("inciResueltoTotal = $inciResueltoTotal")
    params["inciResueltoTotal"] = formato.format(inciResueltoTotal)


    Integer inciResueltoPrimerEnTiempo = firmadoService.
        cuantosEnTiempoNivel(inciResueltoPrimerList, 1)
    params["inciResueltoPrimerEnTiempo"] = formato.format(inciResueltoPrimerEnTiempo)

    params["inciNoResueltoPrimerEnTiempo"] = formato.format(inciResueltoPrimerList.size() - inciResueltoPrimerEnTiempo)

    Integer inciResueltoSegundoEnTiempo = firmadoService.
        cuantosEnTiempoNivel(inciResueltoSegundoList, 2)
    params["inciResueltoSegundoEnTiempo"] = formato.format(inciResueltoSegundoEnTiempo)

    params["inciNoResueltoSegundoEnTiempo"] = formato.format(inciResueltoSegundoList.size() - inciResueltoSegundoEnTiempo)

    Integer inciResueltoTercerEnTiempo = firmadoService.
        cuantosEnTiempoNivel(inciResueltoTercerList, 3)
    params["inciResueltoTercerEnTiempo"] = formato.format(inciResueltoTercerEnTiempo)

    params["inciNoResueltoTercerEnTiempo"] = formato.format(inciResueltoTercerList.size() - inciResueltoTercerEnTiempo)

    params["inciResueltoTotalEnTiempo"] = formato.format(inciResueltoPrimerEnTiempo
        + inciResueltoSegundoEnTiempo + inciResueltoTercerEnTiempo)

    params["inciNoResueltoTotalEnTiempo"] = formato.format(incidentes.size() - params["inciResueltoTotalEnTiempo"].toInteger())


    def formatoFijo = new DecimalFormat("#,##0.00", dfs)

    params["primerOLA"] = formatoFijo.format(inciResueltoTotal / inciResueltoPrimer * 100) + " %"
    params["segundoOLA"] = formatoFijo.format(inciResueltoTotal / inciResueltoSegundo * 100) + " %"
    params["terceroOLA"] = formatoFijo.format(inciResueltoTotal / inciResueltoTercer * 100) + " %"



    query =
      "  from Solicitud                             " +
      " where estado in ('T', 'E')                  " +
      "   and fechaAutoriza between ? and ?         "
    log.debug("query = $query")
    def requerimientos = Solicitud.findAll(query, [startDate, endDate])
    log.debug("requerimientos = $requerimientos")

    // contar cuantas se resolvieron en segundo nivel
    Integer contRequerimientos = 0
    requerimientos.each {
      it.detalles.each { det ->
        if (det.estado == 'A' as char) {
          contRequerimientos += 1
        }
      }
    }
    params["reqResueltoSegundo"] = formato.format(contRequerimientos)


    // contar cuantas se resolvieron en total
    Integer reqResueltoTotal = contRequerimientos
    log.debug("reqResueltoTotal = $reqResueltoTotal")
    params["reqResueltoTotal"] = formato.format(reqResueltoTotal)


    Integer reqResueltoPrimerEnTiempo = firmadoService.
        cuantosReqEnTiempoNivel(requerimientos, 1)
    params["reqResueltoPrimerEnTiempo"] = formato.format(reqResueltoPrimerEnTiempo)

    params["reqNoResueltoPrimerEnTiempo"] = formato.format(contRequerimientos -
        reqResueltoPrimerEnTiempo)

    Integer reqResueltoSegundoEnTiempo = firmadoService.
        cuantosReqEnTiempoNivel(requerimientos, 2)
    params["reqResueltoSegundoEnTiempo"] = formato.format(reqResueltoSegundoEnTiempo)

    params["reqNoResueltoSegundoEnTiempo"] = formato.format(contRequerimientos -
        reqResueltoSegundoEnTiempo)

    params["reqResueltoTotalEnTiempo"] = formato.format(reqResueltoSegundoEnTiempo)

    params["reqNoResueltoTotalEnTiempo"] = formato.format(contRequerimientos -
        params["reqResueltoTotalEnTiempo"].toInteger())


    params["segundoReqOLA"] = formatoFijo.format(reqResueltoSegundoEnTiempo / contRequerimientos * 100) + " %"



    log.debug("startDate = $startDate")
    log.debug("endDate = $endDate")
    log.debug("params = $params")

    def lista =  [] //Cat_bitacora.findAllByLastUpdatedGreaterThanEqualsAndLastUpdatedLessThanEquals(startDate, endDate)

    lista.each { it ->
      def rowBitacora = new RptBitacora (  // Esta clase esta en Cat_bitacoraController
        num_solicitud: it.folio, // TODO: borra o modificar
      )
      data.add(rowBitacora)
    }

    chain (controller:"jasper", action:"index", model:[data:data], params:params)
  }

}

