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
    params.tipoServicio = "Requerimientos"
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
    params["mes"] = startDate.format('MMMM').capitalize()
    params["anio"] = startDate.format('YYYY')

    def requerimientos = requerimientosConEncuesta(startDate, endDate)
    log.debug("requerimientos = $requerimientos")

    if (!requerimientos) {
      flash.error = "No hay datos"
      redirect(action: "list")
      return
    }

    def locale = new Locale('es', 'MX')
    def dfs = new DecimalFormatSymbols(locale)
    def formato = new DecimalFormat("#,##0", dfs)
    def formatoPorciento = new DecimalFormat("#,##0.00", dfs)

    params["preg1Si"] = contar(requerimientosConEncuesta(startDate, endDate),"p01", 1)
    params["preg1No"] = contar(requerimientosConEncuesta(startDate, endDate),"p01", 2)
    params["preg2Si"] = contar(requerimientosConEncuesta(startDate, endDate),"p02", 1)
    params["preg2No"] = contar(requerimientosConEncuesta(startDate, endDate),"p02", 2)
    params["preg3Si"] = contar(requerimientosConEncuesta(startDate, endDate),"p03", 1)
    params["preg3No"] = contar(requerimientosConEncuesta(startDate, endDate),"p03", 2)
    params["preg4Si"] = contar(requerimientosConEncuesta(startDate, endDate),"p04", 1)
    params["preg4No"] = contar(requerimientosConEncuesta(startDate, endDate),"p04", 2)

    def recibidas = firmadoService.recibidas(startDate, endDate)
    params["recibidas"] = formato.format(recibidas)
    def resueltas = firmadoService.resueltas(startDate, endDate)
    params["resueltas"] = formato.format(resueltas)
    params["pendientes"] = formato.format(firmadoService.pendientes(startDate, endDate))
    params["canceladas"] = formato.format(firmadoService.canceladas(startDate, endDate))
    def enTiempo = firmadoService.enTiempo(startDate, endDate)
    params["enTiempo"] = formato.format(enTiempo)
    def satisfechos = firmadoService.satisfechos(startDate, endDate)
    params["satisfechos"] = formato.format(satisfechos)
    params["insatisfechos"] = formato.format(firmadoService.insatisfechos(startDate, endDate))

    params["calidad"] = formatoPorciento.format(satisfechos / resueltas * 100)
    params["eficiencia"] = formatoPorciento.format(enTiempo / recibidas * 100)


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

    flash.error = ""
    redirect(action: "list")
    chain (controller:"jasper", action:"index", model:[data:data], params:params)
  }

  def reporteInciResultadosYSeguimiento() {
    def data = []
    params.image_dir = "${servletContext.getRealPath('/images')}/"
    params.tipoServicio = "Incidentes"
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

    def incidentes = incidentesConEncuesta(startDate, endDate)
    log.debug("incidentes 2 = $incidentes")

    if (!incidentes) {
      flash.error = "No hay datos"
      redirect(action: "list")
      return
    }

    def locale = new Locale('es', 'MX')
    def dfs = new DecimalFormatSymbols(locale)
    def formato = new DecimalFormat("#,##0", dfs)
    def formatoPorciento = new DecimalFormat("#,##0.00", dfs)

    params["mes"] = startDate.format('MMMM').capitalize()
    params["anio"] = startDate.format('YYYY')
    params["preg1Si"] = contar(incidentesConEncuesta(startDate, endDate),"p01", 1)
    params["preg1No"] = contar(incidentesConEncuesta(startDate, endDate),"p01", 2)
    params["preg2Si"] = contar(incidentesConEncuesta(startDate, endDate),"p02", 1)
    params["preg2No"] = contar(incidentesConEncuesta(startDate, endDate),"p02", 2)
    params["preg3Si"] = contar(incidentesConEncuesta(startDate, endDate),"p03", 1)
    params["preg3No"] = contar(incidentesConEncuesta(startDate, endDate),"p03", 2)
    params["preg4Si"] = contar(incidentesConEncuesta(startDate, endDate),"p04", 1)
    params["preg4No"] = contar(incidentesConEncuesta(startDate, endDate),"p04", 2)

    def recibidas = firmadoService.inciRecibidas(startDate, endDate)
    params["recibidas"] = formato.format(recibidas)
    def resueltas = firmadoService.inciResueltas(startDate, endDate)
    params["resueltas"] = formato.format(resueltas)
    params["pendientes"] = formato.format(firmadoService.inciPendientes(startDate, endDate))
    params["canceladas"] = formato.format(firmadoService.inciCanceladas(startDate, endDate))
    def enTiempo = firmadoService.enTiempoInci(startDate, endDate)
    params["enTiempo"] = formato.format(enTiempo)
    def satisfechos = firmadoService.inciSatisfechos(startDate, endDate)
    params["satisfechos"] = formato.format(satisfechos)
    params["insatisfechos"] = formato.format(firmadoService.inciInsatisfechos(startDate, endDate))

    params["calidad"] = formatoPorciento.format(satisfechos / resueltas * 100)
    params["eficiencia"] = formatoPorciento.format(enTiempo / recibidas * 100)


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

  def incidentesConEncuesta(Date startDate, Date endDate) {
    if (!request["listaIncidentes"]) {
      log.debug("si lei los incidentes")
      request["listaIncidentes"] =
        firmadoService.incidentesConEncuesta(startDate, endDate)
      log.debug("incidentes = ${request["listaIncidentes"]}")
    }
    request["listaIncidentes"]
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
    params["mes"] = startDate.format('MMMM').capitalize()
    params["anio"] = startDate.format('YYYY')


    def query =
    //    " where upper(nombre) || case when paterno is null then  '' else ' ' || upper(paterno) end || case when materno is null then  '' else ' ' || upper(materno) end like '%${term}%'   "
      // " where upper(nombre) || ' ' || upper(nvl(paterno,''))  || ' ' || upper(nvl(materno,'')) like '%${term}%'   "
      "  from Incidente                             " +
      " where fechaIncidente between ? and ?        "
    log.debug("query = $query")
    def incidentes = Incidente.findAll(query, [startDate, endDate])
    log.debug("incidentes = $incidentes")

    def incidentesTerminados = incidentes.findAll {it.estado in ['E' as char, 'T' as char]}
    // contar cuantas se resolvieron en primer nivel
    def inciResueltoPrimerList = incidentesTerminados.findAll { it.fechaSolnivel1 && !it.fechaSolnivel2 }
    log.debug("inciResueltoPrimerList = $inciResueltoPrimerList")
    Integer inciResueltoPrimer = inciResueltoPrimerList.size()

    def locale = new Locale('es', 'MX')
    def dfs = new DecimalFormatSymbols(locale)
    def formato = new DecimalFormat("#,##0", dfs)
    params["inciResueltoPrimer"] = formato.format(inciResueltoPrimer)

    // contar cuantas se resolvieron en segundo nivel
    def inciResueltoSegundoList = incidentesTerminados.findAll { it.fechaSolnivel2 && !it.fechaSolnivel3 }
    log.debug("inciResueltoSegundoList = $inciResueltoSegundoList")
    Integer inciResueltoSegundo = inciResueltoSegundoList.size()
    params["inciResueltoSegundo"] = formato.format(inciResueltoSegundo)

    // contar cuantas se resolvieron en tercer nivel
    def inciResueltoTercerList = incidentesTerminados.findAll { it.fechaSolnivel3 }
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

    def inciResueltoTotalEnTiempo = inciResueltoPrimerEnTiempo +
        inciResueltoSegundoEnTiempo + inciResueltoTercerEnTiempo
    params["inciResueltoTotalEnTiempo"] = formato.format(inciResueltoTotalEnTiempo)

    def inciRecibidos = incidentes.count {it.estado != 'C' as char}
    params["inciNoResueltoTotalEnTiempo"] = formato.format(inciRecibidos - params["inciResueltoTotalEnTiempo"].toInteger())


    def formatoFijo = new DecimalFormat("#,##0.00", dfs)

    params["primerOLA"] = !inciResueltoPrimer ? "0 %" : formatoFijo.format(inciResueltoPrimerEnTiempo / inciResueltoPrimer * 100) + " %"
    params["segundoOLA"] = !inciResueltoSegundo ? "0 %" : formatoFijo.format(inciResueltoSegundoEnTiempo / inciResueltoSegundo * 100) + " %"
    params["terceroOLA"] = !inciResueltoTercer ? "0 %" : formatoFijo.format(inciResueltoTercerEnTiempo / inciResueltoTercer * 100) + " %"

    params["SLA"] = !inciResueltoTotal ? "0 %" : formatoFijo.format(inciResueltoTotalEnTiempo / inciRecibidos * 100) + " %"

// seleccionar las solicitudes en el rango
// ver si todos sus detalles terminaron en tiempo

    query =
      "  from Solicitud                           " +
      " where fechaAutoriza between ? and ?     "
    log.debug("query = $query")
    def requerimientos = Solicitud.findAll(query, [startDate, endDate])
    log.debug("requerimientos = $requerimientos")

    def recibidas = requerimientos.size()

    Integer contRequerimientos = firmadoService.cuantosReqEnTiempoNivel(requerimientos, 2)
    params["reqResueltoSegundo"] = formato.format(contRequerimientos)


    // contar cuantas se resolvieron en total
    Integer reqResueltoTotal = contRequerimientos
    log.debug("reqResueltoTotal = $reqResueltoTotal")
    params["reqResueltoTotal"] = formato.format(reqResueltoTotal)

    // TODO: Quitar.
    Integer reqResueltoPrimerEnTiempo = firmadoService.
        cuantosReqEnTiempoNivel(requerimientos, 1)
    params["reqResueltoPrimerEnTiempo"] = formato.format(reqResueltoPrimerEnTiempo)

    // TODO: Quitar.
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


    Integer contTotalRequerimientos = requerimientos.count {it.estado != 'C' as char}
    params["segundoReqOLA"] = !contRequerimientos ? "0 %" : formatoFijo.format(reqResueltoSegundoEnTiempo / contTotalRequerimientos * 100) + " %"


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

  def reporteSolicitudes() {
    def data = []
    params.image_dir = "${servletContext.getRealPath('/images')}/"

    def startDate = params.startDate
    def endDate = params.endDate

    if (endDate < startDate) {
      flash.error = "La fecha final no puede ser menor que la inicial"
      redirect(action: "list")
      return
    }

    use(TimeCategory) {
      endDate = endDate + 1.day - 1.seconds
    }
    log.debug("startDate = $startDate, endDate = $endDate")
    params["rangoFechas"] = "del ${startDate.format("YYYY-MM-dd")} al ${endDate.format("YYYY-MM-dd")}"
    params["anio"] = startDate.format('YYYY')
    def folio = params.folio.toInteger()
    log.debug("folio = ${folio}")
    def anioFolio = params.anioFolio
    log.debug("anioFolio = ${anioFolio.format("YYYY-MM-dd")}")
    def idReporta = params.idReporta.toInteger()
    log.debug("idReporta = ${idReporta}")
    def inventarioParam = params.inventario.toLong()
    log.debug("inventarioParam = $inventarioParam")
    def estado = params.estado
    log.debug("estado = $estado")

    def capturados = 0
    capturados += folio ? 1 : 0
    log.debug("folio = $folio")
    log.debug("capturados, folio = ${folio ? 1 : 0}")
    capturados += inventarioParam ? 1 : 0
    log.debug("inventarioParam = $inventarioParam")
    log.debug("capturados, inventarioParam = ${inventarioParam ? 1 : 0}")
    capturados += idReporta ? 1 : 0
    log.debug("idReporta = $idReporta")
    log.debug("capturados, idReporta = ${idReporta ? 1 : 0}")
    capturados += estado ? 1 : 0
    log.debug("capturados, estado = ${estado ? 1 : 0}")

    if (capturados > 1) {
      flash.error = "Escoja solo uno de los tres, o preguntar por el folio, o por el numero de inventario o por el estado"
      redirect(action: "list")
      return
    }

    def locale = new Locale('es', 'MX')
    def dfs = new DecimalFormatSymbols(locale)
    def formato = new DecimalFormat("#,##0", dfs)

    def query =
      "  from Solicitud                          " +
      " where estado is not null                 " +
      "   and estado <> 'F'                      "

    def parametros = []
    if (!folio) {
      query +=
        "   and fechaAutoriza between ? and ?      "
      parametros << startDate
      parametros << endDate
    }

    if (folio) {
      query +=
        "   and numeroSolicitud = ?                       " +
        "   and TO_CHAR(fechaSolicitud,'%Y') = ? "
      parametros << folio
      parametros << (anioFolio[Calendar.YEAR].toString())
    }

    if (idReporta) {
      query +=
        "   and idSolicitante = ?                       "
      parametros << idReporta
    }

    log.debug("query = $query")
    log.debug("parametros = $parametros")
    log.debug("año clase = ${(anioFolio[Calendar.YEAR]).getClass()}")
    def requerimientos = Solicitud.findAll(query, parametros)
    log.debug("requerimientos = $requerimientos")
    def casos = []
    requerimientos.each {
      it.detalles.each { det ->
        if (det.estado == 'A' as char) {
          if (inventarioParam) {
            if (inventarioEquipo(det) == inventarioParam) {
              casos << new Servicio (caso: det, tipo: "Requerimiento",
                orden: it.fechaSolicitud[Calendar.YEAR] * 10000 + (it.numeroSolicitud ?: 0))
            }
          } else {
            log.debug("it = $it, it.fechaSolicitud = ${it.fechaSolicitud}, it.numeroSolicitud = ${it.numeroSolicitud}")
            casos << new Servicio (caso: det, tipo: "Requerimiento",
              orden: it.fechaSolicitud[Calendar.YEAR] * 10000 + (it.numeroSolicitud ?: 0))
          }
        }
      }
    }

    def queryInci =
      "  from Incidente                          " +
      " where estado is not null                 "

    parametros = []
    if (!folio) {
      queryInci +=
        "   and fechaIncidente between ? and ?      "
      parametros << startDate
      parametros << endDate
    }

    if (folio) {
      queryInci +=
        "   and numeroIncidente = ?                  " +
        "   and TO_CHAR(fechaIncidente,'%Y') = ?     "
      parametros << folio
      parametros << (anioFolio[Calendar.YEAR].toString())
    }

    if (idReporta) {
      queryInci +=
        "   and idReporta = ?                       "
      parametros << idReporta
    }


    log.debug("queryInci = $queryInci")
    log.debug("parametros = $parametros")
    def incidentes = Incidente.findAll(queryInci, parametros)
    log.debug("incidentes = $incidentes")
    incidentes.each {
      if (inventarioParam) {
        if (inventarioEquipo(it) == inventarioParam)
          casos << new Servicio (caso: it, tipo: "Incidente",
            orden: it.fechaIncidente[Calendar.YEAR] * 10000 + it.numeroIncidente)
      } else {
        casos << new Servicio (caso: it, tipo: "Incidente",
          orden: it.fechaIncidente[Calendar.YEAR] * 10000 + it.numeroIncidente)
      }
    }

    def queryProblema =
      "  from Problema                    " +
      " where idFuente is not null        "

    parametros = []
    if (!folio) {
      queryProblema +=
        "   and fechaProblema between ? and ?      "
      parametros << startDate
      parametros << endDate
    }

    if (folio) {
      queryProblema +=
        "   and folio = ?                  " +
        "   and TO_CHAR(fechaProblema,'%Y') = ?     "
      parametros << folio
      parametros << (anioFolio[Calendar.YEAR].toString())
    }


    log.debug("queryProblema = $queryProblema")
    log.debug("parametros = $parametros")
    def problemas = Problema.findAll(queryProblema, parametros)
    log.debug("problemas = $problemas")
    problemas.each {
      if (inventarioParam || idReporta) {
        if (it.fuente == 'Incidente') { // TODO: Agregar logica para cuando no sea el problema originado por un incidente, sino por una bitacora
          def incidente = Incidente.get(it.idFuente)
          if (inventarioParam) {
            if (inventarioEquipo(incidente) == inventarioParam) {
              casos << new Servicio (caso: it, tipo: "Problema",
                orden: it.fechaProblema[Calendar.YEAR] * 10000 + it.folio)
            }
          } else {
            if (incidente.idReporta == idReporta) {
              casos << new Servicio (caso: it, tipo: "Problema",
                orden: it.fechaProblema[Calendar.YEAR] * 10000 + it.folio)
            }
          }
        }
      } else {
        casos << new Servicio (caso: it, tipo: "Problema",
          orden: it.fechaProblema[Calendar.YEAR] * 10000 + it.folio)
      }
    }

    log.debug("startDate = $startDate")
    log.debug("endDate = $endDate")
    log.debug("params = $params")

    if (!casos) {
      flash.error = "No hay datos de solicitudes"
      redirect(action: "list")
      return
    }

    casos.sort{it.orden}
    log.debug("casos = $casos")

    casos.each { it ->
      def caso = it.caso
      def inventario = ""
      if (!(caso instanceof Problema)) {
        inventario = inventarioEquipo(caso)
      }

      def renglon = null
      switch (caso.getClass()) {
        case SolicitudDetalle:
          def solicitud = caso.idSolicitud
          def gestionado = ""
          if (solicitud.fechaRevisa) {
            if (caso.idTecnico) {
              gestionado = Usuario.get(caso.idTecnico)
            } else {
              gestionado = caso.idServ.servResp2
            }
          } else if (caso.idAprobador) {
            gestionado = Usuario.get(caso.idAprobador)
          } else if (caso.idServ) {
            gestionado = caso.idServ.servResp1
          } else {
            gestionado = caso.idServcat.servResp
          }
          log.debug("caso = $caso, gestionado = $gestionado")
          renglon = new RptSolicitud (
            folio: solicitud,
            tipo: it.tipo,
            estado: firmadoService.estadoDescriptivo(solicitud),
            area: firmadoService.areaNombre(solicitud.idSolicitante),
            nombre: Usuario.get(solicitud.idSolicitante),
            categoria: caso.idServcat,
            subcategoria: caso?.idServ?.servSub ?: "",
            tercerNivel: caso?.idServ ?: "",
            descripcion: caso?.descripcion ?: "",
            inventario: inventario ?: "",
            responsable: caso.idServcat.servResp,
            gestionadoA: gestionado,
            prioridad: message(code:"intensidad.valor.${caso.prioridad}"),
            fechaRecepcion: caso.idSolicitud.fechaAutoriza ? (caso.idSolicitud.fechaAutoriza).format("YYYY-MM-dd HH:mm") : "",
            fechaCierre: caso.fechaSolucion ? (caso.fechaSolucion).format("YYYY-MM-dd HH:mm") : "",
            solucion: caso.solucion ?: "",
          )
          break
        case Incidente:
          def nivel = caso.nivel
          renglon = new RptSolicitud (
            folio: caso,
            tipo: it.tipo,
            estado: firmadoService.estadoDescriptivo(caso),
            area: firmadoService.areaNombre(caso.idReporta),
            nombre: Usuario.get(caso.idReporta),
            categoria: caso.idServ.servSub.servCat,
            subcategoria: caso?.idServ?.servSub ?: "",
            tercerNivel: caso?.idServ ?: "",
            descripcion: caso?.descripcion ?: "",
            inventario: inventario ?: "",
            responsable: caso.idServ.servSub.servCat.servResp,
            gestionadoA: caso."idNivel$nivel" ? Usuario.get(caso."idNivel$nivel") :
              caso.idServ."servResp$nivel",
            prioridad: message(code:"intensidad.valor.${caso.idServ.impacto}"),
            fechaRecepcion: caso.fechaIncidente.format("YYYY-MM-dd HH:mm"),
            fechaCierre: caso.estado in 'ET' ? caso."fechaSolnivel$nivel" : "",
            solucion: caso.estado in 'ET' ? caso."solucionNivel$nivel" : "",
          )
          break
        case Problema:
          def fuente = null
          switch (caso.fuente) {
            case "Incidente":
              fuente = Incidente.get(caso.idFuente)
              def nivel = fuente.nivel
              inventario = inventarioEquipo(fuente)
              renglon = new RptSolicitud (
                folio: caso,
                tipo: it.tipo,
                estado: "",
                area: firmadoService.areaNombre(caso.idUsuario),
                nombre: Usuario.get(caso.idUsuario),
                categoria: fuente.idServ.servSub.servCat,
                subcategoria: fuente?.idServ?.servSub ?: "",
                tercerNivel: fuente?.idServ ?: "",
                descripcion: fuente?.descripcion ?: "",
                inventario: inventario ?: "",
                responsable: fuente.idServ.servSub.servCat.servResp,
                gestionadoA: fuente."idNivel$nivel" ? Usuario.get(fuente."idNivel$nivel") :
                  fuente.idServ."servResp$nivel",
                prioridad: message(code:"intensidad.valor.${fuente.idServ.impacto}"),
                fechaRecepcion: caso.fechaProblema.format("YYYY-MM-dd HH:mm"),
                fechaCierre: caso.fechaSolucion ? caso.fechaSolucion.format("YYYY-MM-dd HH:mm") : "",
                solucion: caso.solucion ?: "",
              )
              break
            case "Solicitud":
              fuente = SolicitudDetalle.get(caso.idFuente)
              inventario = inventarioEquipo(fuente)
              def solicitud = fuente.idSolicitud
              renglon = new RptSolicitud (
                folio: solicitud,
                tipo: it.tipo,
                estado: firmadoService.estadoDescriptivo(solicitud),
                area: firmadoService.areaNombre(solicitud.idSolicitante),
                nombre: Usuario.get(solicitud.idSolicitante),
                categoria: fuente.idServcat,
                subcategoria: fuente?.idServ?.servSub ?: "",
                tercerNivel: fuente?.idServ ?: "",
                descripcion: fuente?.descripcion ?: "",
                inventario: inventario ?: "",
                responsable: fuente.idServcat.servResp,
                gestionadoA: fuente.idTecnico ? Usuario.get(fuente.idTecnico) : fuente.idServcat.servResp,
                prioridad: message(code:"intensidad.valor.${fuente.prioridad}"),
                fechaRecepcion: caso.fechaProblema.format("YYYY-MM-dd HH:mm"),
                fechaCierre: caso.fechaSolucion ? caso.fechaSolucion.format("YYYY-MM-dd HH:mm") : "",
                solucion: caso.solucion ?: "",
              )
              break
          }
          break
      }

      // log.debug("renglon = ${renglon}, ${renglon.folio}, ${renglon.tipo}, ${renglon.nombre}, ${renglon.tercerNivel}")
      data.add(renglon)
    }

    chain (controller:"jasper", action:"index", model:[data:data], params:params)
  }

  def inventarioEquipo(fuente) {
    def inventario = 0
    if (fuente.idResguardoentregadetalle) {
      def equipo = ResguardoEntregaDetalle.get(fuente.idResguardoentregadetalle)
      inventario = equipo.inventario
    }
    inventario
  }

  def reportePortafolio() {
    def data = []
    params.image_dir = "${servletContext.getRealPath('/images')}/"

    def categorias = Cat_servCat.findAllByEstado('A' as char)

    log.debug("categorias = $categorias")
    log.debug("params = $params")

    categorias.each { it ->
      def renglon = new RptPortafolio (
        id: it.id.toString(),
        categoria: it,
        descripcion: it.descripcion,
        responsable: it.servResp,
        valoracion: message(code:"intensidad.valor.${it.valoracion}"),
        disponibilidad: "${it.disponibilidad} %",
        cobertura: it.servCob,
      )
      data.add(renglon)
    }

    chain (controller:"jasper", action:"index", model:[data:data], params:params)
  }

  def reporteSubcategoria() {
    def data = []
    params.image_dir = "${servletContext.getRealPath('/images')}/"

    // obtener las catecorias con estado A
    def categorias = Cat_servCat.findAllByEstado('A' as char)
    // de esas , obtener las subcategorias con estado A
    def subcategorias = Cat_servSub.findAllByEstadoAndServCatInList('A' as char,
      categorias, [sort: "servCat"])

    log.debug("subcategorias = $subcategorias")
    log.debug("params = $params")

    subcategorias.each { it ->
      def categoria = it.servCat
      def renglon = new rptSubcategoria (
        id: categoria.id,
        categoria: categoria,
        categoria_id: categoria.id,
        descripcion: categoria.descripcion,
        responsable: categoria.servResp,
        valoracion: message(code:"intensidad.valor.${categoria.valoracion}"),
        disponibilidad: "${categoria.disponibilidad} %",
        cobertura: categoria.servCob,
        subcategoria: it,
      )
      log.debug("sub = ${renglon.subcategoria}")
      data.add(renglon)
    }

    chain (controller:"jasper", action:"index", model:[data:data], params:params)
  }

  def reporteServicios() {
    def data = []
    params.image_dir = "${servletContext.getRealPath('/images')}/"

    def locale = new Locale('es', 'MX')
    def dfs = new DecimalFormatSymbols(locale)
    def formato8 = new DecimalFormat("00000000", dfs)

    def paraOrdenar = Cat_serv.list().collect { new Servicio(caso:it, tipo:"",
      orden:formato8.format(it.servSub.servCat.id) +
        formato8.format(it.servSub.id) + formato8.format(it.id))}
    paraOrdenar.sort{it.orden}
    def servicios = paraOrdenar.collect {it.caso}
    log.debug("paraOrdenar = ${paraOrdenar.collect{it.orden}}")

    log.debug("params = $params")

    servicios.each {
      def categoria = it.servSub.servCat
      def renglon = new rptServicios (
        id: categoria.id,
        categoria: categoria,
        descripcion: categoria.descripcion,
        responsable: categoria.servResp,
        valoracion: message(code:"intensidad.valor.${categoria.valoracion}"),
        disponibilidad: "${categoria.disponibilidad} %",
        estado: it.estadoServ == 'A' as char ? "Activo" : "Incactivo",
        cobertura: categoria.servCob,
        subcategoria: it.servSub,
        tercerNivel: it,
        visible: it.portal ? "*" : "",
        incidente: it.incidente ? "*" : "",
        solicitud: it.solicitud ? "*" : "",
        problema: it.problema ? "*" : "",
      )
      data.add(renglon)
    }

    chain (controller:"jasper", action:"index", model:[data:data], params:params)
  }

  def reporteNiveles() {
    def data = []
    params.image_dir = "${servletContext.getRealPath('/images')}/"
    params.tipoServicio = "Requerimientos"


    def startDate = params.startDate
    startDate[Calendar.DATE] = 1
    startDate[Calendar.HOUR_OF_DAY] = 0
    startDate[Calendar.MINUTE] = 0
    def endDate = startDate.clone()
    use(TimeCategory) {
      endDate = endDate + 1.month - 1.seconds
    }
    log.debug("startDate = $startDate, endDate = $endDate")
    params["mes"] = startDate.format('MMMM').capitalize()
    params["anio"] = startDate.format('YYYY')

    def query =
      "  from Solicitud                          " +
      " where estado is not null                 " +
      "   and estado <> 'F'                      " +
      "   and fechaAutoriza between ? and ?      " +
      " order by numeroSolicitud                 "
    log.debug("query = $query")
    def requerimientos = Solicitud.findAll(query, [startDate, endDate])
    log.debug("requerimientos = $requerimientos")
    def detalles = []
    requerimientos.each {
      it.detalles.each { det ->
        if (det.estado == 'A' as char) {
          detalles << det
        }
      }
    }

    log.debug("params = $params")
    log.debug("detalles = ${detalles.collect {it.id}}")

    detalles.each {
      def solicitud = it.idSolicitud

      // reportar el tiempo del gestor
      def tiempoAsignado = it.idServ ? firmadoService.tiempoAsignadoNivel(it.idServ, 1) : 30
      log.debug("tiempoAsignado = $tiempoAsignado")
      def tiempoAsignadoString = firmadoService.minutesToString(tiempoAsignado)
      log.debug("tiempoAsignado = $tiempoAsignado")
      def inicio = solicitud.fechaVb ?: solicitud.fechaAutoriza
      def fin = solicitud.fechaRevisa ?: new Date()
      def tiempoReal = firmadoService.diff(inicio, fin)
      def tiempoRealString = ""
      if (solicitud.fechaRevisa) {
        tiempoRealString = firmadoService.diffString(inicio, fin)
      }
      log.debug("tiempoRealString = $tiempoRealString")
      def renglon = new rptNiveles (
        folio: solicitud,
        nivel: "gestoria",
        area: firmadoService.areaNombre(solicitud.idSolicitante),
        nombre: Usuario.get(solicitud.idSolicitante),
        categoria: it.idServcat,
        subcategoria: it.idServ?.servSub ?: "",
        tercerNivel: it.idServ ?: "",
        descripcion: it?.descripcion,
        fechaInicio: inicio.format("YYYY-MM-dd HH:mm"),
        fechaFinal: solicitud.fechaRevisa ? fin.format("YYYY-MM-dd HH:mm") : "",
        tiempoPrometido: tiempoAsignadoString,
        tiempoReal: tiempoRealString,
        cumple: tiempoAsignado >= tiempoReal ? solicitud.fechaRevisa ? "SI" : "" : "NO",
      )
      data.add(renglon)

      if (solicitud.fechaRevisa) {
        tiempoAsignado = firmadoService.tiempoAsignadoNivel(it.idServ, 2)
        log.debug("tiempoAsignado = $tiempoAsignado")
        tiempoAsignadoString = firmadoService.minutesToString(tiempoAsignado)
        log.debug("tiempoAsignado = $tiempoAsignado")
        inicio = solicitud.fechaRevisa
        fin = it.fechaSolucion ?: new Date()
        tiempoReal = firmadoService.diff(inicio, fin)
        tiempoRealString = ""
        if (it.fechaSolucion) {
          tiempoRealString = firmadoService.diffString(inicio, fin)
        }
        log.debug("tiempoRealString = $tiempoRealString")
        def renglon2 = new rptNiveles (
          folio: solicitud,
          nivel: "segundo",
          area: "",
          nombre: "",
          categoria: "",
          subcategoria: "",
          tercerNivel: "",
          descripcion: '',
          fechaInicio: inicio.format("YYYY-MM-dd HH:mm"),
          fechaFinal: it.fechaSolucion ? fin.format("YYYY-MM-dd HH:mm") : "",
          tiempoPrometido: tiempoAsignadoString,
          tiempoReal: tiempoRealString,
          cumple: tiempoAsignado >= tiempoReal ? it.fechaSolucion ? "SI" : "" : "NO",
        )
        data.add(renglon2)
      }
    }

    chain (controller:"jasper", action:"index", model:[data:data], params:params)
  }

  def reporteInciNiveles() {
    def data = []
    params.image_dir = "${servletContext.getRealPath('/images')}/"
    params.tipoServicio = "Incidentes"


    def startDate = params.startDate
    startDate[Calendar.DATE] = 1
    startDate[Calendar.HOUR_OF_DAY] = 0
    startDate[Calendar.MINUTE] = 0
    def endDate = startDate.clone()
    use(TimeCategory) {
      endDate = endDate + 1.month - 1.seconds
    }
    log.debug("startDate = $startDate, endDate = $endDate")
    params["mes"] = startDate.format('MMMM').capitalize()
    params["anio"] = startDate.format('YYYY')

    def query =
      "  from Incidente                          " +
      " where fechaIncidente between ? and ?     " +
      " order by numeroIncidente                 "
    log.debug("query = $query")
    def incidentes = Incidente.findAll(query, [startDate, endDate])
    log.debug("incidentes = $incidentes")

    log.debug("params = $params")

    incidentes.each {
      for (nivel in 1..it.nivel) {
        def tiempoAsignado = firmadoService.tiempoAsignadoNivel(it.idServ, nivel)
        log.debug("incidente = $it")
        log.debug("tiempoAsignado = $tiempoAsignado")
        def tiempoAsignadoString = firmadoService.minutesToString(tiempoAsignado)
        log.debug("tiempoAsignado = $tiempoAsignado")
        def inicio = it."fechaNivel$nivel" ?: it.fechaIncidente
        log.debug("inicio = $inicio")
        def solucion = it."fechaSolnivel$nivel" ?: new Date()
        log.debug("solucion = $solucion")
        def tiempoReal = firmadoService.diff(inicio, solucion)
        def tiempoRealString = ""
        if (it."fechaSolnivel$nivel") {
          tiempoRealString = firmadoService.diffString(inicio, solucion)
        }
        log.debug("tiempoRealString = $tiempoRealString")
        def renglon = new rptNiveles (
          folio: it,
          nivel: nivel,
          area: firmadoService.areaNombre(it.idReporta),
          nombre: Usuario.get(it.idReporta),
          categoria: it.idServ.servSub.servCat,
          subcategoria: it.idServ.servSub,
          tercerNivel: it.idServ,
          descripcion: it?.descripcion,
          fechaInicio: inicio.format("YYYY-MM-dd HH:mm"),
          fechaFinal: (it."fechaSolnivel$nivel") ? solucion.format("YYYY-MM-dd HH:mm") : "",
          tiempoPrometido: tiempoAsignadoString,
          tiempoReal: tiempoRealString,
          cumple: tiempoAsignado >= tiempoReal ? it."fechaSolnivel$nivel" ? "SI" : "" : "NO",
        )
        data.add(renglon)
      }
    }

    chain (controller:"jasper", action:"index", model:[data:data], params:params)
  }

  def reporteTiempos() {
    def data = []
    params.image_dir = "${servletContext.getRealPath('/images')}/"
    params.tipoServicio = "Requerimientos"


    def startDate = params.startDate
    startDate[Calendar.DATE] = 1
    startDate[Calendar.HOUR_OF_DAY] = 0
    startDate[Calendar.MINUTE] = 0
    def endDate = startDate.clone()
    use(TimeCategory) {
      endDate = endDate + 1.month - 1.seconds
    }
    log.debug("startDate = $startDate, endDate = $endDate")
    params["mes"] = startDate.format('MMMM').capitalize()
    params["anio"] = startDate.format('YYYY')

    def query =
      "  from Solicitud                          " +
      " where estado is not null                 " +
      "   and estado <> 'F'                      " +
      "   and fechaAutoriza between ? and ?      " +
      " order by numeroSolicitud                 "
    log.debug("query = $query")
    def requerimientos = Solicitud.findAll(query, [startDate, endDate])
    log.debug("requerimientos = $requerimientos")
    def detalles = []
    requerimientos.each {
      it.detalles.each { det ->
        if (det.estado == 'A' as char) {
          detalles << det
        }
      }
    }

    if (!detalles) {
      flash.error = "No hay datos"
      redirect(action: "list")
      return
    }

    log.debug("params = $params")
    log.debug("detalles = ${detalles.collect {it.id}}")

    detalles.each {
      def solicitud = it.idSolicitud
      def tiempoAsignado = it.idServ ? firmadoService.tiempoAsignadoNivel(it.idServ, 1) +
        firmadoService.tiempoAsignadoNivel(it.idServ, 2) : 30 + 60
      log.debug("tiempoAsignado = $tiempoAsignado")
      def tiempoAsignadoString = firmadoService.minutesToString(tiempoAsignado)
      log.debug("tiempoAsignado = $tiempoAsignado")
      def inicio = solicitud.fechaVb ?: solicitud.fechaAutoriza
      def fin = it.fechaSolucion ?: new Date()
      def tiempoReal = firmadoService.diff(inicio, fin)
      def tiempoRealString = ""
      if (it.fechaSolucion) {
        tiempoRealString = firmadoService.diffString(solicitud.fechaRevisa, it.fechaSolucion)
      }
      log.debug("tiempoRealString = $tiempoRealString")
      def renglon = new rptNiveles (
        folio: solicitud,
        nivel: it.fechaSolucion ? "segundo" : "",
        area: firmadoService.areaNombre(solicitud.idSolicitante),
        nombre: Usuario.get(solicitud.idSolicitante),
        categoria: it.idServcat,
        subcategoria: it.idServ?.servSub ?: "",
        tercerNivel: it.idServ ?: "",
        descripcion: it?.descripcion,
        fechaInicio: solicitud.fechaRevisa ? (solicitud.fechaRevisa).format("YYYY-MM-dd HH:mm") : "",
        fechaFinal: it.fechaSolucion ? (it.fechaSolucion).format("YYYY-MM-dd HH:mm") : "",
        tiempoPrometido: tiempoAsignadoString,
        tiempoReal: tiempoRealString,
        cumple: tiempoAsignado >= tiempoReal ? it.fechaSolucion ? "SI" : "" : "NO",
      )
      data.add(renglon)
    }

    chain (controller:"jasper", action:"index", model:[data:data], params:params)
  }

  def reporteInciTiempos() {
    def data = []
    params.image_dir = "${servletContext.getRealPath('/images')}/"
    params.tipoServicio = "Incidentes"


    def startDate = params.startDate
    startDate[Calendar.DATE] = 1
    startDate[Calendar.HOUR_OF_DAY] = 0
    startDate[Calendar.MINUTE] = 0
    def endDate = startDate.clone()
    use(TimeCategory) {
      endDate = endDate + 1.month - 1.seconds
    }
    log.debug("startDate = $startDate, endDate = $endDate")
    params["mes"] = startDate.format('MMMM').capitalize()
    params["anio"] = startDate.format('YYYY')

    def query =
      "  from Incidente                          " +
      " where fechaIncidente between ? and ?     " +
      " order by numeroIncidente                 "
    log.debug("query = $query")
    def incidentes = Incidente.findAll(query, [startDate, endDate])
    log.debug("incidentes = $incidentes")

    if (!incidentes) {
      flash.error = "No hay datos"
      redirect(action: "list")
      return
    }

    log.debug("params = $params")

    incidentes.each {
      def nivel = it.nivel
      def tiempoAsignado = 0
      for (i in 1..nivel) {
        tiempoAsignado += firmadoService.tiempoAsignadoNivel(it.idServ, i)
      }
      log.debug("tiempoAsignado = $tiempoAsignado")
      def tiempoAsignadoString = firmadoService.minutesToString(tiempoAsignado)
      log.debug("tiempoAsignado = $tiempoAsignado")
      def fin = it."fechaSolnivel$nivel" ?: new Date()
      def tiempoReal = firmadoService.diff(it.fechaIncidente, fin)
      def tiempoRealString = firmadoService.diffString(it.fechaIncidente, fin)
      log.debug("tiempoRealString = $tiempoRealString")
      def renglon = new rptNiveles (
        folio: it,
        nivel: it."fechaSolnivel$nivel" ? nivel : "",
        area: firmadoService.areaNombre(it.idReporta),
        nombre: Usuario.get(it.idReporta),
        categoria: it.idServ.servSub.servCat,
        subcategoria: it.idServ.servSub,
        tercerNivel: it.idServ,
        descripcion: it?.descripcion,
        fechaInicio: (it.fechaIncidente).format("YYYY-MM-dd HH:mm"),
        fechaFinal: it."fechaSolnivel$nivel" ? fin.format("YYYY-MM-dd HH:mm") : "",
        tiempoPrometido: tiempoAsignadoString,
        tiempoReal: it."fechaSolnivel$nivel" ? tiempoRealString : "",
        cumple: tiempoAsignado >= tiempoReal ? it."fechaSolnivel$nivel" ? "SI" : "" : "NO",
      )
      data.add(renglon)
    }

    chain (controller:"jasper", action:"index", model:[data:data], params:params)
  }

}

class Servicio {
  Object caso
  String tipo
  String orden

  String toString() {
    caso.toString()
  }
}

class rptNiveles {
  String folio
  String nivel
  String area
  String nombre
  String categoria
  String subcategoria
  String tercerNivel
  String descripcion
  String fechaInicio
  String fechaFinal
  String tiempoPrometido
  String tiempoReal
  String cumple
}

class rptServicios {
  String id
  String categoria
  String descripcion
  String responsable
  String valoracion
  String disponibilidad
  String estado
  String cobertura
  String subcategoria
  String tercerNivel
  String visible
  String incidente
  String solicitud
  String problema
}

class RptSolicitud {
  String folio
  String tipo
  String estado
  String area
  String nombre
  String categoria
  String subcategoria
  String tercerNivel
  String descripcion
  String inventario
  String responsable
  String gestionadoA
  String prioridad
  String fechaRecepcion
  String fechaCierre
  String solucion
}

class RptPortafolio {
  String id
  String categoria
  String descripcion
  String responsable
  String valoracion
  String disponibilidad
  String cobertura
}

class rptSubcategoria {
  String id
  String categoria
  String categoria_id
  String descripcion
  String responsable
  String valoracion
  String disponibilidad
  String cobertura
  String subcategoria
}
