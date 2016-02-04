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

    params["preg1Si"] = contar(requerimientosConEncuesta(startDate, endDate),"p01", 1)
    params["preg1No"] = contar(requerimientosConEncuesta(startDate, endDate),"p01", 2)
    params["preg2Si"] = contar(requerimientosConEncuesta(startDate, endDate),"p02", 1)
    params["preg2No"] = contar(requerimientosConEncuesta(startDate, endDate),"p02", 2)
    params["preg3Si"] = contar(requerimientosConEncuesta(startDate, endDate),"p03", 1)
    params["preg3No"] = contar(requerimientosConEncuesta(startDate, endDate),"p03", 2)
    params["preg4Si"] = contar(requerimientosConEncuesta(startDate, endDate),"p04", 1)
    params["preg4No"] = contar(requerimientosConEncuesta(startDate, endDate),"p04", 2)

    params["recibidas"] = formato.format(firmadoService.recibidas(startDate, endDate))
    params["resueltas"] = formato.format(firmadoService.resueltas(startDate, endDate))
    params["pendientes"] = formato.format(firmadoService.pendientes(startDate, endDate))
    params["canceladas"] = formato.format(firmadoService.canceladas(startDate, endDate))
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

    params["recibidas"] = formato.format(firmadoService.inciRecibidas(startDate, endDate))
    params["resueltas"] = formato.format(firmadoService.inciResueltas(startDate, endDate))
    params["pendientes"] = formato.format(firmadoService.inciPendientes(startDate, endDate))
    params["canceladas"] = formato.format(firmadoService.inciCanceladas(startDate, endDate))
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
    params["mes"] = startDate.format('MMMM').capitalize()
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

    params["primerOLA"] = !inciResueltoPrimer ? "0 %" : formatoFijo.format(inciResueltoPrimerEnTiempo / inciResueltoPrimer * 100) + " %"
    params["segundoOLA"] = !inciResueltoSegundo ? "0 %" : formatoFijo.format(inciResueltoSegundoEnTiempo / inciResueltoSegundo * 100) + " %"
    params["terceroOLA"] = !inciResueltoTercer ? "0 %" : formatoFijo.format(inciResueltoTercerEnTiempo / inciResueltoTercer * 100) + " %"



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
    params["mes"] = startDate.format('MMMM').capitalize()
    params["anio"] = startDate.format('YYYY')

    def locale = new Locale('es', 'MX')
    def dfs = new DecimalFormatSymbols(locale)
    def formato = new DecimalFormat("#,##0", dfs)


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
            folio: solicitud,
            tipo: it.tipo,
            estado: firmadoService.estadoDescriptivo(solicitud),
            area: firmadoService.areaNombre(solicitud.idSolicitante),
            nombre: Usuario.get(solicitud.idSolicitante),
            categoria: caso.idServcat,
            subcategoria: caso?.idServ?.servSub ?: "",
            tercerNivel: caso?.idServ ?: "",
            descripcion: caso?.descripcion ?: "",
            inventario: inventario,
            responsable: caso.idServcat.servResp,
            gestionadoA: caso.idTecnico ? Usuario.get(caso.idTecnico) : caso.idServcat.servResp,
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
            inventario: inventario,
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
                inventario: inventario,
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
                inventario: inventario,
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
        id: it.id,
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
      "   and estado <> 'F'                 " +
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
    log.debug("detalles = ${detalles.collect {it.id}}")

    detalles.each {
      def solicitud = it.idSolicitud

      // reportar el tiempo del gestor
      def tiempoAsignado = firmadoService.tiempoAsignadoNivel(it.idServ, 1)
      log.debug("tiempoAsignado = $tiempoAsignado")
      def tiempoAsignadoString = firmadoService.minutesToString(tiempoAsignado)
      log.debug("tiempoAsignado = $tiempoAsignado")
      def inicio = solicitud.fechaVb ?: solicitud.fechaAutoriza
      def fin = solicitud.fechaRevisa ?: new Date()
      def tiempoReal = firmadoService.diff(inicio, fin)
      def tiempoRealString = firmadoService.diffString(inicio, fin)
      log.debug("tiempoRealString = $tiempoRealString")
      def renglon = new rptNiveles (
        folio: solicitud,
        nivel: "gestoria",
        area: firmadoService.areaNombre(solicitud.idSolicitante),
        nombre: Usuario.get(solicitud.idSolicitante),
        categoria: it.idServcat,
        subcategoria: it.idServ.servSub,
        tercerNivel: it,
        descripcion: it?.descripcion,
        fechaInicio: inicio.format("YYYY-MM-dd HH:mm"),
        fechaFinal: fin.format("YYYY-MM-dd HH:mm"),
        tiempoPrometido: tiempoAsignadoString,
        tiempoReal: tiempoRealString,
        cumple: tiempoAsignado >= tiempoReal ? "SI" : "NO",
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
        tiempoRealString = firmadoService.diffString(inicio, fin)
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
          fechaFinal: fin.format("YYYY-MM-dd HH:mm"),
          tiempoPrometido: tiempoAsignadoString,
          tiempoReal: tiempoRealString,
          cumple: tiempoAsignado >= tiempoReal ? "SI" : "NO",
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
      " where fechaIncidente between ? and ?      "
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
        def tiempoRealString = firmadoService.diffString(inicio, solucion)
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
          fechaFinal: solucion.format("YYYY-MM-dd HH:mm"),
          tiempoPrometido: tiempoAsignadoString,
          tiempoReal: tiempoRealString,
          cumple: tiempoAsignado >= tiempoReal ? "SI" : "NO",
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
    log.debug("detalles = ${detalles.collect {it.id}}")

    detalles.each {
      def solicitud = it.idSolicitud
      def tiempoAsignado = firmadoService.tiempoAsignadoNivel(it.idServ, 2)
      log.debug("tiempoAsignado = $tiempoAsignado")
      def tiempoAsignadoString = firmadoService.minutesToString(tiempoAsignado)
      log.debug("tiempoAsignado = $tiempoAsignado")
      def tiempoReal = firmadoService.diff(solicitud.fechaRevisa, it.fechaSolucion)
      def tiempoRealString = firmadoService.diffString(solicitud.fechaRevisa, it.fechaSolucion)
      log.debug("tiempoRealString = $tiempoRealString")
      def renglon = new rptNiveles (
        folio: solicitud,
        nivel: "segundo",
        area: firmadoService.areaNombre(solicitud.id),
        nombre: Usuario.get(solicitud.idSolicitante),
        categoria: it.idServcat,
        subcategoria: it.idServ.servSub,
        tercerNivel: it,
        descripcion: it?.descripcion,
        fechaInicio: (solicitud.fechaRevisa).format("YYYY-MM-dd HH:mm"),
        fechaFinal: (it.fechaSolucion).format("YYYY-MM-dd HH:mm"),
        tiempoPrometido: tiempoAsignadoString,
        tiempoReal: tiempoRealString,
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
