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
    params.preg1 = g.message(code:"solicitud.p01.reporte", default:"Numero Solicitud")
    params.preg2 = g.message(code:"solicitud.p02.reporte", default:"Numero Solicitud")
    params.preg3 = g.message(code:"solicitud.p03.reporte", default:"Numero Solicitud")
    params.preg4 = g.message(code:"solicitud.p04.reporte", default:"Numero Solicitud")

    def startDate = params.startDate
    startDate[Calendar.DATE] = 1
    startDate[Calendar.HOUR_OF_DAY] = 0
    startDate[Calendar.MINUTE] = 0
    def endDate = startDate.clone()
    use(TimeCategory) {
      endDate = endDate + 1.month - 1.seconds
    }
    params["mes"] = startDate.format('MMMM')
    params["anio"] = startDate.format('YYYY')

    def requerimientos = requerimientosConEncuesta(startDate, endDate)
    log.debug("requerimientos = $requerimientos")

    if (!requerimientos) {
      flash.error = "No hay datos"
      redirect(action: "list")
      return
    }

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

    flash.error = ""
    redirect(action: "list")
    chain (controller:"jasper", action:"index", model:[data:data], params:params)
  }

  def reporteInciResultadosYSeguimiento() {
    def data = []
    params.image_dir = "${servletContext.getRealPath('/images')}/"
    params.tipoServicio = "Incidentes"
    params.preg1 = g.message(code:"solicitud.p01.reporte", default:"Numero Solicitud")
    params.preg2 = g.message(code:"solicitud.p02.reporte", default:"Numero Solicitud")
    params.preg3 = g.message(code:"solicitud.p03.reporte", default:"Numero Solicitud")
    params.preg4 = g.message(code:"solicitud.p04.reporte", default:"Numero Solicitud")

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

    params["mes"] = startDate.format('MMMM')
    params["anio"] = startDate.format('YYYY')
    params["preg1Si"] = contar(incidentesConEncuesta(startDate, endDate),"p01", 1)
    params["preg1No"] = contar(incidentesConEncuesta(startDate, endDate),"p01", 2)
    params["preg2Si"] = contar(incidentesConEncuesta(startDate, endDate),"p02", 1)
    params["preg2No"] = contar(incidentesConEncuesta(startDate, endDate),"p02", 2)
    params["preg3Si"] = contar(incidentesConEncuesta(startDate, endDate),"p03", 1)
    params["preg3No"] = contar(incidentesConEncuesta(startDate, endDate),"p03", 2)
    params["preg4Si"] = contar(incidentesConEncuesta(startDate, endDate),"p04", 1)
    params["preg4No"] = contar(incidentesConEncuesta(startDate, endDate),"p04", 2)

    def locale = new Locale('es', 'MX')
    def dfs = new DecimalFormatSymbols(locale)
    def formato = new DecimalFormat("#,##0", dfs)
    params["recibidas"] = formato.format(firmadoService.inciRecibidas(startDate, endDate))
    params["resueltas"] = formato.format(firmadoService.inciResueltas(startDate, endDate))
    params["pendientes"] = formato.format(firmadoService.inciPendientes(startDate, endDate))
    params["enTiempo"] = formato.format(firmadoService.enTiempoInci(startDate, endDate))
    params["satisfechos"] = formato.format(firmadoService.inciSatisfechos(startDate, endDate))
    params["insatisfechos"] = formato.format(firmadoService.inciInsatisfechos(startDate, endDate))


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

    def inciResueltoTotalEnTiempo = inciResueltoPrimerEnTiempo +
        inciResueltoSegundoEnTiempo + inciResueltoTercerEnTiempo
    params["inciResueltoTotalEnTiempo"] = formato.format(inciResueltoTotalEnTiempo)

    params["inciNoResueltoTotalEnTiempo"] = formato.format(incidentes.size() - params["inciResueltoTotalEnTiempo"].toInteger())


    def formatoFijo = new DecimalFormat("#,##0.00", dfs)

    params["primerOLA"] = !inciResueltoPrimer ? "0 %" : formatoFijo.format(inciResueltoTotal / inciResueltoPrimer * 100) + " %"
    params["segundoOLA"] = !inciResueltoSegundo ? "0 %" : formatoFijo.format(inciResueltoTotal / inciResueltoSegundo * 100) + " %"
    params["terceroOLA"] = !inciResueltoTercer ? "0 %" : formatoFijo.format(inciResueltoTotal / inciResueltoTercer * 100) + " %"



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


    params["segundoReqOLA"] = !contRequerimientos ? "0 %" : formatoFijo.format(reqResueltoSegundoEnTiempo / contRequerimientos * 100) + " %"
    params["SLA"] = !inciResueltoTotal ? "0 %" : formatoFijo.format(inciResueltoTotalEnTiempo / inciResueltoTotal * 100) + " %"


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
    startDate[Calendar.DATE] = 1
    startDate[Calendar.HOUR_OF_DAY] = 0
    startDate[Calendar.MINUTE] = 0
    def endDate = startDate.clone()
    use(TimeCategory) {
      endDate = endDate + 1.month - 1.seconds
    }
    params["mes"] = startDate.format('MMMM').toString()
    params["anio"] = startDate.format('YYYY')

    def locale = new Locale('es', 'MX')
    def dfs = new DecimalFormatSymbols(locale)
    def formato = new DecimalFormat("#,##0", dfs)
    def formatoFijo = new DecimalFormat("#,##0.00", dfs)


    def query =
      "  from Solicitud                          " +
      " where estado is not null                 " +
      "   and estado <> 'F'                      " +
      "   and fechaAutoriza between ? and ?      "
    log.debug("query = $query")
    def requerimientos = Solicitud.findAll(query, [startDate, endDate])
    log.debug("requerimientos = $requerimientos")
    def casos = []
    requerimientos.each {
      it.detalles.each { det ->
        if (det.estado == 'A' as char) {
          casos << new Servicio (caso: det, tipo: "Requerimiento",
            orden: det.idSolicitud.fechaAutoriza.format("YYYY-MM-dd HH:mm"))
        }
      }
    }

    def queryInci =
      "  from Incidente                          " +
      " where fechaIncidente between ? and ?     "
    log.debug("queryInci = $queryInci")
    def incidentes = Incidente.findAll(queryInci, [startDate, endDate])
    log.debug("incidentes = $incidentes")
    incidentes.each {
      casos << new Servicio (caso: it, tipo: "Incidente",
        orden: it.fechaIncidente.format("YYYY-MM-dd HH:mm"))
    }

    def queryProblema =
      "  from Problema                          " +
      " where fechaProblema between ? and ?     "
    log.debug("queryProblema = $queryProblema")
    def problemas = Problema.findAll(queryProblema, [startDate, endDate])
    log.debug("problemas = $problemas")
    problemas.each {
      casos << new Servicio (caso: it, tipo: "Problema",
        orden: it.fechaProblema.format("YYYY-MM-dd HH:mm"))
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
          renglon = new RptSolicitud (
            folio: solicitud.toString(),
            tipo: it.tipo,
            estado: firmadoService.estadoDescriptivo(solicitud),
            area: firmadoService.areaNombre(solicitud.idSolicitante),
            nombre: Usuario.get(solicitud.idSolicitante).toString(),
            categoria: caso.idServcat.toString(),
            subcategoria: caso?.idServ?.servSub?.toString(),
            tercerNivel: caso?.idServ?.toString(),
            descripcion: caso?.descripcion,
            inventario: inventario,
            responsable: caso.idServcat.servResp.toString(),
            gestionadoA: caso.idTecnico ? Usuario.get(caso.idTecnico).toString() : caso.idServcat.servResp.toString(),
            prioridad: message(code:"intensidad.valor.${caso.prioridad}"),
            fechaRecepcion: caso.idSolicitud.fechaAutoriza ? (caso.idSolicitud.fechaAutoriza).format("YYYY-MM-dd HH:mm") : "",
            fechaCierre: caso.fechaSolucion ? (caso.fechaSolucion).format("YYYY-MM-dd HH:mm") : "",
            solucion: caso.solucion ?: "",
          )
          break
        case Incidente:
          def nivel = caso.nivel
          renglon = new RptSolicitud (
            folio: caso.toString(),
            tipo: it.tipo,
            estado: firmadoService.estadoDescriptivo(caso),
            area: firmadoService.areaNombre(caso.idReporta),
            nombre: Usuario.get(caso.idReporta).toString(),
            categoria: caso.idServ.servSub.servCat.toString(),
            subcategoria: caso?.idServ?.servSub?.toString(),
            tercerNivel: caso?.idServ?.toString(),
            descripcion: caso?.descripcion,
            inventario: inventario,
            responsable: caso.idServ.servSub.servCat.servResp.toString(),
            gestionadoA: caso."idNivel$nivel" ? Usuario.get(caso."idNivel$nivel").toString() :
              caso.idServ."servResp$nivel".toString(),
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
                folio: caso.toString(),
                tipo: it.tipo,
                estado: "",
                area: firmadoService.areaNombre(caso.idUsuario),
                nombre: Usuario.get(caso.idUsuario).toString(),
                categoria: fuente.idServ.servSub.servCat.toString(),
                subcategoria: fuente?.idServ?.servSub?.toString(),
                tercerNivel: fuente?.idServ?.toString(),
                descripcion: fuente?.descripcion,
                inventario: inventario,
                responsable: fuente.idServ.servSub.servCat.servResp.toString(),
                gestionadoA: fuente."idNivel$nivel" ? Usuario.get(fuente."idNivel$nivel").toString() :
                  fuente.idServ."servResp$nivel".toString(),
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
                folio: solicitud.toString(),
                tipo: it.tipo,
                estado: firmadoService.estadoDescriptivo(solicitud),
                area: firmadoService.areaNombre(solicitud.idSolicitante),
                nombre: Usuario.get(solicitud.idSolicitante).toString(),
                categoria: fuente.idServcat.toString(),
                subcategoria: fuente?.idServ?.servSub?.toString(),
                tercerNivel: fuente?.idServ?.toString(),
                descripcion: fuente?.descripcion,
                inventario: inventario,
                responsable: fuente.idServcat.servResp.toString(),
                gestionadoA: fuente.idTecnico ? Usuario.get(fuente.idTecnico).toString() : fuente.idServcat.servResp.toString(),
                prioridad: message(code:"intensidad.valor.${fuente.prioridad}"),
                fechaRecepcion: caso.fechaProblema.format("YYYY-MM-dd HH:mm"),
                fechaCierre: caso.fechaSolucion ? caso.fechaSolucion.format("YYYY-MM-dd HH:mm") : "",
                solucion: caso.solucion ?: "",
              )
              break
          }
          break
      }

      data.add(renglon)
    }

    chain (controller:"jasper", action:"index", model:[data:data], params:params)
  }

  def inventarioEquipo(fuente) {
    def inventario = ""
    if (fuente.idResguardoentregadetalle) {
      def equipo = ResguardoEntregaDetalle.get(fuente.idResguardoentregadetalle)
      inventario = equipo.inventario.toString()
    }
    inventario
  }

  def reportePortafolio() {
    def data = []
    params.image_dir = "${servletContext.getRealPath('/images')}/"

    def locale = new Locale('es', 'MX')
    def dfs = new DecimalFormatSymbols(locale)
    def formato = new DecimalFormat("#,##0", dfs)
    def formatoFijo = new DecimalFormat("#,##0.00", dfs)

    // obtener las catecorias con estado A
    def categorias = Cat_servCat.findAllByEstado('A' as char)
    // de esas , obtener las subcategorias con estado A
    def subcategorias = Cat_servSub.findAllByEstadoAndServCatInList('A' as char, categorias)
    // y de esas, obtener los servicios con estado A
    def servicios = Cat_serv.findAllByEstadoServAndServSubInList('A' as char, subcategorias, [sort: "id"])

    log.debug("servicios = $servicios")
    log.debug("params = $params")

    servicios.each { it ->
      def categoria = it.servSub.servCat
      def renglon = new RptPortafolio (
        id: it.id.toString(),
        categoria: categoria.toString(),
        descripcion: it.descripcion,
        responsable: categoria.toString(),
        valoracion: message(code:"intensidad.valor.${categoria.valoracion}"),
        disponibilidad: "${categoria.disponibilidad} %",
        cobertura: categoria.servCob,
      )
      data.add(renglon)
    }

    chain (controller:"jasper", action:"index", model:[data:data], params:params)
  }

  def reporteSubcategoria() {
    def data = []
    params.image_dir = "${servletContext.getRealPath('/images')}/"

    def locale = new Locale('es', 'MX')
    def dfs = new DecimalFormatSymbols(locale)
    def formato = new DecimalFormat("#,##0", dfs)
    def formatoFijo = new DecimalFormat("#,##0.00", dfs)

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
        id: categoria.id.toString(),
        categoria: categoria.toString(),
        categoria_id: categoria.id.toString(),
        descripcion: categoria.descripcion,
        responsable: categoria.toString(),
        valoracion: message(code:"intensidad.valor.${categoria.valoracion}"),
        disponibilidad: "${categoria.disponibilidad} %",
        cobertura: categoria.servCob,
        subcategoria: it.toString(),
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
    def formato = new DecimalFormat("#,##0", dfs)
    def formatoFijo = new DecimalFormat("#,##0.00", dfs)

    def servicios = Cat_serv.list(sort: "servSub.id")

    log.debug("params = $params")

    servicios.each {
      def categoria = it.servSub.servCat
      def renglon = new rptServicios (
        id: it.id.toString(),
        categoria: categoria.toString(),
        descripcion: it.descripcion,
        responsable: categoria.servResp.toString(),
        valoracion: message(code:"intensidad.valor.${categoria.valoracion}"),
        disponibilidad: "${categoria.disponibilidad} %",
        estado: it.estadoServ,
        cobertura: categoria.servCob,
        subcategoria: it.servSub.toString(),
        tercerNivel: it.toString(),
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

    def startDate = params.startDate
    startDate[Calendar.DATE] = 1
    startDate[Calendar.HOUR_OF_DAY] = 0
    startDate[Calendar.MINUTE] = 0
    def endDate = startDate.clone()
    use(TimeCategory) {
      endDate = endDate + 1.month - 1.seconds
    }
    log.debug("startDate = $startDate, endDate = $endDate")
    params["mes"] = startDate.format('MMMM').toString()
    params["anio"] = startDate.format('YYYY')

    def locale = new Locale('es', 'MX')
    def dfs = new DecimalFormatSymbols(locale)
    def formato = new DecimalFormat("#,##0", dfs)
    def formatoFijo = new DecimalFormat("#,##0.00", dfs)

    def query =
      "  from Solicitud                          " +
      " where estado in ('T','E')                " +
      "   and lastUpdated between ? and ?      "
    log.debug("query = $query")
    def requerimientos = Solicitud.findAll(query, [startDate, endDate])
    log.debug("requerimientos = $requerimientos")
    def detalles = []
    requerimientos.each {
      it.detalles.each { det ->
        if (det.estado == 'A' as char && det.fechaSolucion >= startDate &&
            det.fechaSolucion <= endDate) {
          detalles << det
        }
      }
    }

    log.debug("params = $params")

    detalles.each {
      def solicitud = it.idSolicitud
      def tiempoAsignado = firmadoService.tiempoAsignadoNivel(it.idServ, 2)
      def tiempoReal = firmadoService.diff(solicitud.fechaRevisa, it.fechaSolucion)
      def renglon = new rptNiveles (
        folio: solicitud.toString(),
        nivel: "segundo",
        area: firmadoService.areaNombre(solicitud.id),
        nombre: Usuario.get(solicitud.idSolicitante).toString(),
        categoria: it.idServcat.toString(),
        subcategoria: it.idServ.servSub.toString(),
        tercerNivel: it.toString(),
        descripcion: it?.descripcion,
        fechaInicio: (solicitud.fechaRevisa).format("YYYY-MM-dd HH:mm"),
        fechaFinal: (it.fechaSolucion).format("YYYY-MM-dd HH:mm"),
        tiempoPrometido: tiempoAsignado,
        tiempoReal: tiempoReal,
        cumple: tiempoAsignado >= tiempoReal ? "SI" : "NO",
      )
      data.add(renglon)
    }

    chain (controller:"jasper", action:"index", model:[data:data], params:params)
  }

  def reporteTiempos() {
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
    log.debug("startDate = $startDate, endDate = $endDate")
    params["mes"] = startDate.format('MMMM').toString()
    params["anio"] = startDate.format('YYYY')

    def locale = new Locale('es', 'MX')
    def dfs = new DecimalFormatSymbols(locale)
    def formato = new DecimalFormat("#,##0", dfs)
    def formatoFijo = new DecimalFormat("#,##0.00", dfs)

    def query =
      "  from Solicitud                          " +
      " where estado in ('T','E')                " +
      "   and lastUpdated between ? and ?      "
    log.debug("query = $query")
    def requerimientos = Solicitud.findAll(query, [startDate, endDate])
    log.debug("requerimientos = $requerimientos")
    def detalles = []
    requerimientos.each {
      it.detalles.each { det ->
        if (det.estado == 'A' as char && det.fechaSolucion >= startDate &&
            det.fechaSolucion <= endDate) {
          detalles << det
        }
      }
    }

    log.debug("params = $params")

    detalles.each {
      def solicitud = it.idSolicitud
      def tiempoAsignado = firmadoService.tiempoAsignadoNivel(it.idServ, 2)
      def tiempoReal = firmadoService.diff(solicitud.fechaRevisa, it.fechaSolucion)
      def renglon = new rptNiveles (
        folio: solicitud.toString(),
        nivel: "segundo",
        area: firmadoService.areaNombre(solicitud.id),
        nombre: Usuario.get(solicitud.idSolicitante).toString(),
        categoria: it.idServcat.toString(),
        subcategoria: it.idServ.servSub.toString(),
        tercerNivel: it.toString(),
        descripcion: it?.descripcion,
        fechaInicio: (solicitud.fechaRevisa).format("YYYY-MM-dd HH:mm"),
        fechaFinal: (it.fechaSolucion).format("YYYY-MM-dd HH:mm"),
        tiempoPrometido: tiempoAsignado,
        tiempoReal: tiempoReal,
        cumple: tiempoAsignado >= tiempoReal ? "SI" : "NO",
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
