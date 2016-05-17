package mx.gob.inr.catservicios

import grails.plugins.springsecurity.SpringSecurityService;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional

class ServiciosService {
  static transactional = true
  def firmadoService

  @Transactional(readOnly = true)
  def listarEquipo(params) {
    def term = params.term.toUpperCase().trim()
    // TODO: quitar en todo este archivo log.debug("en listarEquipo, term = $term")

    def codigos = ResguardoEntrega.findAllByCodigoLike("515%").collect {it.id}
    // log.debug("número de codigos = ${codigos.size()}")

    def empleados = null
    if (term && !(term =~ /[0-9]/)) {
      def queryEmpleado =
        "  from Usuario as u     " +
        " where upper(nombre) || ' ' || upper(nvl(paterno,''))  || ' ' || upper(nvl(materno,'')) like '%${term}%'   "
      // log.debug("queryEmpleado = $queryEmpleado")
      empleados = Usuario.findAll(queryEmpleado, [],
                      [max: 8]).collect {it.idEmpleado as Integer}.findAll{it}
      // log.debug("empleados = $empleados")
    }

    def MAX_EQUIPOS = 2 // Es el consecutivo maximo a considerar,

      // TODO. aplicar un orden -  order("inventario","asc")

    def paramQuery = [codigos: codigos]
    def query =
        "  from ResguardoEntregaDetalle as d     " +
        " where d.idResguardo.id in (:codigos)   " +
        "   and d.consecutivo <= ${MAX_EQUIPOS}  " +
        "   and d.idTipoanexotecnico is not null "
    if (empleados) {
      // log.debug("primer empleado es de tipo ${empleados[0].getClass()}")
      query +=
        "   and d.idEmpleado in (:empleados)    "
        paramQuery << [empleados: empleados]
    } else {
      query +=
        "   and (serie like :serie               " +
        "        or inventario = :inventario)    "
        def inventario = term.isNumber() ? term.toLong() : new Long(0)
        // log.debug("inventario = ${inventario}")
        paramQuery << [serie: "%$term%", inventario: inventario]
    }
    log.debug("query = $query")

    def clist = ResguardoEntregaDetalle.findAll(query, paramQuery, [max: 15])
    // log.debug("clist = ${clist}")

    def cSelectList = [] // cada uno de los resultados
    clist.each {
      cSelectList.add(detallesEquipo(it))
    }
    return cSelectList
  }

  @Transactional(readOnly = true)
  def listarUsuario(params) {
    def term = params.term.toUpperCase().trim()
    // TODO: quitar en todo este archivo log.debug("en listarUsuario, term = $term")

    def paramQuery = [nombre: "%$term%"]
    def query =
        "  from Usuario              " +
        " where Enabled = 't'        " +
        "   and nombre like :nombre  " +
        " order by nombre "
    log.debug("query = $query")

    def clist = Usuario.findAll(query, paramQuery, [max: 15])
    // log.debug("clist = ${clist}")

    def cSelectList = [] // cada uno de los resultados
    clist.each {
      def eqMap = [:]
      eqMap.put("nombre", it['nombre'])
      eqMap.put("label", it['nombre'])
      eqMap.put("id", it['id'])
      def extension = extension(['reporta': it.id])
      eqMap.put("extension", extension)
      log.debug("eqMap = $eqMap")
      cSelectList.add(eqMap)
    }
    cSelectList
  }

  @Transactional(readOnly = true)
  def detallesById(id) {
    id ? detallesEquipo(ResguardoEntregaDetalle.get(id)) : 0
  }

  @Transactional(readOnly = true)
  def detallesEquipo(ResguardoEntregaDetalle it) {
      def eqMap = [:] // add to map. jQuery autocomplete expects the JSON object to be with id/label/value.
      eqMap.put("serie", it['serie'])
      def empleado = Usuario.findByIdEmpleado(it['idEmpleado'])?.toString() ?: "Error en dato de empleado"
      // TODO: quitar log.debug("it['idTipoanexotecnico'] = ${it['idTipoanexotecnico']}")
      def tipoEquipo = AnexoTecnico.get(it['idTipoanexotecnico'] as Long)
      // log.debug("it['id'] = ${it['id']}")
      eqMap.put("value", it['id'])
      def marca = descMarca(it['idMarca'])
      def ubicacion = firmadoService.ubicacion(it.id)
      def cuerpo = firmadoService.cuerpoNivel(it.id)
      eqMap.put("label", "${it['inventario']} : ${it['serie']} : $tipoEquipo : " +
        "$empleado : $marca : ${it['desModelo']}${ubicacion ? ' : ' + ubicacion + ' : ' + cuerpo : ''}")
      eqMap.put("marca", marca)
      eqMap.put("modelo", it['desModelo'])
      eqMap.put("economico", it['inventario'])
      eqMap.put("equipo", tipoEquipo.descripcion)
      eqMap.put("empleado", empleado)
      eqMap.put("garantia", garantiaFormateada(it))
      eqMap.put("ubicacion", ubicacion)
      eqMap.put("cuerpo", cuerpo)
      eqMap
  }

  @Transactional(readOnly = true)
  def extension(params) { // un mapa con el par reporta:id de Usuario
    def reporta = params.reporta.toLong()
    log.debug("en extension, reporta = $reporta")

    def ext = Usuario.get(reporta).extension
    def extInt = ext ? ext.isInteger() ? ext.toInteger() : 0 : 0
    log.debug("extInt = $extInt")

    Incidente incidente = Incidente.find("from Incidente where idReporta = $reporta order by fechaIncidente desc")
    log.debug("incidente = $incidente")
    Solicitud solicitud = Solicitud.find("from Solicitud where idSolicitante = $reporta order by fechaSolicitud desc")
    log.debug("solicitud = $solicitud")

    // tengo que encontrar cual es el más nuevo que tiene extención
    def encontradoIncidente = incidente && incidente.extension ? incidente.extension : 0
    log.debug("encontradoIncidente = $encontradoIncidente")
    def encontradoSolicitud = solicitud && solicitud.extension ? solicitud.extension : 0
    log.debug("encontradoSolicitud = $encontradoSolicitud")

    def encontrado = encontradoIncidente ?: encontradoSolicitud
    if (incidente && solicitud && incidente.fechaIncidente < solicitud.fechaSolicitud && encontradoSolicitud) {
      encontrado = encontradoSolicitud
    }
    log.debug("encontrado = $encontrado")

    encontrado ?: extInt
  }

  def nombreEquipo(id){
    // log.debug("en nombreEquipo")

    def equipo = ""
    if (id) {
      equipo = ResguardoEntregaDetalle.get(id).toString()
    }
    // log.debug("equipo = $equipo")
    equipo
  }

  def descMarca(id){
    def marca = ""
    if (id) {
      marca = Marca.get(id).toString()
    }
    marca
  }

  String garantiaFormateada(ResguardoEntregaDetalle equipo) {
    def fecha = garantia(equipo)
    fecha?.format("yyyy/MM/dd HH:mm") ?: ""
  }

  Date garantia(ResguardoEntregaDetalle equipo) {
    // log.debug("equipo = $equipo")
    Date garantia = null
    if (equipo.idTipoanexotecnico in [1, 2]) {
    // log.debug("CPU o Monitor")
      def cpu = Cpu.findByIdResguardoentregadetalle(equipo.id)
      // log.debug("cpu = ${cpu?.id}")
      garantia = cpu?.fechaVenceGarantia
    } else if (equipo.idTipoanexotecnico == 5) {
    // log.debug("Impresora")
      def impresora = Impresora.findByIdResguardoentregadetalle(equipo.id)
      // log.debug("impresora = ${impresora?.id}")
      garantia = impresora?.fechaVenceGarantia
    } else if (equipo.idTipoanexotecnico == 6) {
    // TODO: quitar log.debug("UPS")
      def ups = Ups.findByIdResguardoentregadetalle(equipo.id)
      // log.debug("ups = ${ups?.id}")
      garantia = ups?.fechaVenceGarantia
    }

    garantia
  }

}
