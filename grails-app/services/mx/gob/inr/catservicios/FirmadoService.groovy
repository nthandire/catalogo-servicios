package mx.gob.inr.catservicios

import javax.servlet.http.HttpSession
import groovy.time.*
import groovy.time.TimeCategory

class FirmadoService {
  def grailsApplication
  static transactional = false

  Boolean isGestor(HttpSession sessionFirmado, Long userID) {
    isRol("gestor", "ROLE_SAST_GESTOR", sessionFirmado, userID)
  }

  Boolean isTecnico(HttpSession sessionFirmado, Long userID) {
    isRol("técnico", "ROLE_SAST_TECNICO", sessionFirmado, userID)
  }

  Boolean isCoordinador(HttpSession sessionFirmado, Long userID) {
    isRol("coordinador", "ROLE_SAST_APROBADOR", sessionFirmado, userID)
  }

  Boolean isRol(String rol, String rolCadena, HttpSession sessionFirmado, Long userID) {
    if (sessionFirmado[rol] == null) {
      log.debug("no lo tengo, busco el $rol")
      def rolAsignado = UsuarioRol.withNewSession { session ->
        UsuarioRol.findAllByUsuario(Usuario.get(userID)).any {
          it.rol.authority == rolCadena
        }
      }
      sessionFirmado[rol] = rolAsignado
    }
    log.debug("isrol: is${rol} = ${sessionFirmado[rol]}")

    sessionFirmado[rol]
  }

  Boolean thisIsGestor(Long userID) {
    thisIsRol("gestor", "ROLE_SAST_GESTOR", userID)
  }

  Boolean thisIsCoordinador(Long userID) {
    thisIsRol("coordinador", "ROLE_SAST_APROBADOR", userID)
  }

  Boolean thisIsRol(String rol, String rolCadena, Long userID) {
    def rolAsignado = UsuarioRol.withNewSession { session ->
      UsuarioRol.findAllByUsuario(Usuario.get(userID)).any {
        it.rol.authority == rolCadena
      }
    }
    log.debug("thisIsRol: is${rol} = ${rolAsignado}")

    rolAsignado
  }

  Cat_servResp area(HttpSession sessionFirmado, Long userID) {
    log.debug("area: userID = ${userID}")
    if (sessionFirmado["area"] == null) {
      log.debug("no lo tengo, busco el área")
      def area = _area(userID)
      sessionFirmado["area"] = area
    }
    log.debug("area = ${sessionFirmado["area"]}")

    sessionFirmado["area"]
  }

  // para usarlo en console
  Cat_servResp _area (Long userID) {
    def areaString = UsuarioAutorizado.get(userID)
    if (!areaString)
      return null
    Cat_servResp.findByDescripcion(areaString.area)
  }

  def usuarioNombre (Long usuario) {
    Usuario.get(usuario)
  }

  def nombreEmpleado (Long empleado) {
    Usuario.findByIdEmpleado(empleado)
  }

  def areaNombre (Long usuario) {
    log.debug("usuario = ${usuario}")
    def area = ""
    if (usuario) {
      area = UsuarioAutorizado.get(usuario)?.area
      if (!area || area.length() == 1) {
        def idArea = area ?: Usuario.get(usuario)?.idUnidadMedica
        log.debug("idArea = ${idArea}")
        if (idArea) {
          def unidMed = UnidadMedica.get(idArea as char)
          log.debug("unidMed = ${unidMed}")
          area = unidMed.descripcion
          log.debug("area = ${area}")
          } else
            area = "Error, revisar con desarrollo de sistemas"
      }
    }
    area
  }

  String estadoDescriptivo(Solicitud caso) {
    def descripcion = ""
    switch (caso.estado) {
      case 'A' as char:
        if (caso.idVb) {
          descripcion = "solicita VoBo"
        } else {
          descripcion = "aceptado"
        }
        break
      case 'V' as char:
        descripcion = "visto bueno"
        break
      case 'R' as char:
        descripcion = "revisado"
        break
      case 'C' as char:
        descripcion = "cancelado"
        break
      case 'E' as char:
        descripcion = "encuesta"
        break
      case 'T' as char:
        descripcion = "terminado"
        break
    }
    descripcion
  }

  String estadoDescriptivo(Incidente caso) {
    def nivel = caso.nivel
    def descripcion = ""
    switch (caso.estado) {
      case 'A' as char:
        descripcion = nivel == 1 ? "1er Nivel" : nivel == 2 ? "2do Nivel" : "3er Nivel"
        if (caso."idNivel$nivel") {
          descripcion += ", asignado"
        }
        break
      case 'C' as char:
        descripcion = "cancelado"
        break
      case 'E' as char:
        descripcion = "encuesta"
        break
      case 'T' as char:
        descripcion = "terminado"
        break
    }
    descripcion
  }

  def ubicacion (Long idEquipo) {
    _ubicacion(idEquipo)?.descripcion
  }

  def cuerpoNivel (Long idEquipo) {
    def ubicacion = _ubicacion(idEquipo)
    if (!ubicacion)
      return null
    CuerpoNivel.get(ubicacion.idCuerpoNivel)?.toString()
  }

  def _ubicacion (Long idEquipo) {
    def idUbicacion = ResguardoEntregaDetalle.get(idEquipo)?.idUbicacion
    log.debug("idUbicacion = $idUbicacion")
    if (!idUbicacion)
      return null
   Ubicacion.get(idUbicacion)
  }

  def categoriasIncidentes() {
    def query =
        "  from Cat_servCat c               \n" +
        " where exists                      \n" +
        "      ( from Cat_servSub s,        \n" +
        "             Cat_serv t            \n" +
        "       where s.servCat = c.id      \n" +
        "         and s.id = t.servSub      \n" +
        "         and t.incidente = 't'     \n" +
        "      )                            \n" +
        " order by c.categoria              \n"
    log.debug("query = \n${query}")

    def categorias = Cat_servCat.executeQuery(query)
    log.debug("numero de categorias = ${categorias.size()}")
    categorias
  }

  def subcategoriasIncidentes(Cat_servCat categoria) {
    def query =
        "  from Cat_servSub s                   \n" +
        " where s.servCat.id = ${categoria.id}  \n" +
        "   and exists                          \n" +
        "      ( from Cat_serv t                \n" +
        "       where s.id = t.servSub          \n" +
        "         and t.incidente = 't'         \n" +
        "      )                                \n" +
        " order by s.descripcion                \n"
    log.debug("query = \n${query}")

    def subcategorias = Cat_servSub.executeQuery(query)
    log.debug("numero de subcategorias = ${subcategorias.size()}")
    subcategorias
  }

  def tercerNivelIncidentes(Cat_servSub subcategoria) {
    def query =
        "  from Cat_serv t                         \n" +
        " where t.servSub.id = ${subcategoria.id}  \n" +
        "   and t.incidente = 't'                  \n" +
        " order by t.descripcion                   \n"
    log.debug("query = \n${query}")

    def tercerosNiveles = Cat_serv.executeQuery(query)
    log.debug("numero de tercerosNiveles = ${tercerosNiveles.size()}")
    tercerosNiveles
  }

  def categoriasSolicitudes() {
    def query =
        "  from Cat_servCat c               \n" +
        " where exists                      \n" +
        "      ( from Cat_servSub s,        \n" +
        "             Cat_serv t            \n" +
        "       where s.servCat = c.id      \n" +
        "         and s.id = t.servSub      \n" +
        "         and t.portal = 't'        \n" +
        "         and t.solicitud = 't'     \n" +
        "      )                            \n" +
        " order by c.categoria              \n"
    log.debug("query = \n${query}")

    def categorias = Cat_servCat.executeQuery(query)
    log.debug("numero de categorias = ${categorias.size()}")
    categorias
  }

  def subcategoriasSolicitudes(Cat_servCat categoria) {
    def query =
        "  from Cat_servSub s                   \n" +
        " where s.servCat.id = ${categoria.id}  \n" +
        "   and exists                          \n" +
        "      ( from Cat_serv t                \n" +
        "       where s.id = t.servSub          \n" +
        "         and t.solicitud = 't'         \n" +
        "      )                                \n" +
        " order by s.descripcion                \n"
    log.debug("query = \n${query}")

    def subcategorias = Cat_servSub.executeQuery(query)
    log.debug("numero de subcategorias = ${subcategorias.size()}")
    subcategorias
  }

  def tercerNivelSolicitudes(Cat_servSub subcategoria) {
    def query =
        "  from Cat_serv t                         \n" +
        " where t.servSub.id = ${subcategoria.id}  \n" +
        "   and t.solicitud = 't'                  \n" +
        " order by t.descripcion                   \n"
    log.debug("query = \n${query}")

    def tercerosNiveles = Cat_serv.executeQuery(query)
    log.debug("numero de tercerosNiveles = ${tercerosNiveles.size()}")
    tercerosNiveles
  }

  def equipo(Long resguardo) {
    ResguardoEntregaDetalle.get(resguardo).descripcion
  }

  def sendMail(String correo, String asunto, String msg) {
    log.debug("correo = $correo")
    log.debug("asunto = $asunto")
    log.debug("msg = $msg")
    if (grailsApplication.config.enviar.correos.toBoolean()) {
      log.debug("si envio el correo")
      sendMail {
        to correo
        subject asunto
        body msg
      }
    }
  }

  def sendMailHTML(String correo, String asunto, String msg) {
    log.debug("correo = $correo")
    log.debug("asunto = $asunto")
    log.debug("msg = $msg")
    log.debug("enviar.correos = ${grailsApplication.config.enviar.correos.toBoolean()}")
    if (grailsApplication.config.enviar.correos.toBoolean()) {
      log.debug("si envio el correo")
      sendMail {
        to correo
        subject asunto
        html msg
      }
    }
  }

  Integer retraso(List semaforo, SolicitudDetalle caso) {
    def fecha = new Date()
    def minutos = 0
    def plazoMinutos = 0
    def unidades = 0
    def estado = caso.idSolicitud.estado
    if (!(estado.toString() in ['R','V','A'])) {
      return semaforo.size()
    } else if (estado == 'A') {
      if (caso.idSolicitud.idVb) {
        return semaforo.size()
      } else {
        fecha = caso.idSolicitud.fechaAutoriza
        plazoMinutos = caso?.idServ?.tiempo1
        unidades = caso?.idServ?.unidades1?.id
      }
    } else if (estado == 'V') {
      fecha = caso.idSolicitud.fechaVb
      plazoMinutos = caso?.idServ?.tiempo1
      unidades = caso?.idServ?.unidades1?.id
    } else { // estado == 'R'
      fecha = caso.idSolicitud.fechaRevisa
      plazoMinutos = caso?.idServ?.tiempo2
      unidades = caso?.idServ?.unidades2?.id
    }
    log.debug "requerimiento = $caso.idSolicitud, fecha = $fecha"
    switch ( unidades ) {
      case 2: // Horas
        plazoMinutos *= 60
        break
      case 3: // Días
        plazoMinutos *= 60 * 24
        break
      case 4: // Semanas
        plazoMinutos *= 60 * 24 * 7
        break
    }
    log.debug "tercer nivel = ${caso?.idServ}"
    log.debug "unidades = ${unidades}"
    log.debug "plazoMinutos = $plazoMinutos"

    def ahora = new Date()
    log.debug "ahora = $ahora"

    def diff = 0
    use ( TimeCategory ) {
      diff = ahora - fecha
      log.debug "dias = $diff.days"
      minutos = diff.minutes + diff.hours * 60 + diff.days * 24 * 60
    }
    log.debug "minutos = $minutos"

    Integer idxSemaforo = 0
    while (idxSemaforo < semaforo.size()) {
      if (minutos >= (plazoMinutos * (semaforo[idxSemaforo].min) / 100)) break
      ++idxSemaforo
    }
    log.debug("color = ${semaforo[idxSemaforo].color}")
    return idxSemaforo
  }

  Boolean asignado(Incidente caso) {
    caso."idNivel${caso.nivel}"
  }

  Integer retrasoIncidente(List semaforo, Incidente caso) {
    def fecha = new Date()
    def minutos = 0
    def plazoMinutos = 0
    def unidades = 0
    def estado = caso.estado
    def nivel = caso.nivel
    if (!(estado.toString() in ['A'])) {
      return semaforo.size()
    } else { // estado == 'A'
    log.debug("nivel = $nivel")
    if (nivel == 1) {
      fecha = caso.fechaIncidente
    } else if (caso."idNivel$nivel") { // asignado
      fecha = caso."fechaNivel$nivel"
    } else { // no asignado
      fecha = caso."fechaSolnivel${nivel - 1}"
    }

    plazoMinutos = caso?.idServ?."tiempo$nivel"
    unidades = caso?.idServ?."unidades$nivel"?.id
    }

    log.debug "Incidente = ${caso}, fecha = ${fecha}"
    switch ( unidades ) {
      case 2: // Horas
        plazoMinutos *= 60
        break
      case 3: // Días
        plazoMinutos *= 60 * 24
        break
      case 4: // Semanas
        plazoMinutos *= 60 * 24 * 7
        break
    }
    log.debug "tercer nivel = ${caso?.idServ}"
    log.debug "unidades = ${unidades}"
    log.debug "plazoMinutos = $plazoMinutos"

    def ahora = new Date()
    log.debug "ahora = $ahora"

    def diff = 0
    use ( TimeCategory ) {
      diff = ahora - fecha
      log.debug "dias = $diff.days"
      minutos = diff.minutes + diff.hours * 60 + diff.days * 24 * 60
    }
    log.debug "minutos = $minutos"

    Integer idxSemaforo = 0
    while (idxSemaforo < semaforo.size()) {
      if (minutos >= (plazoMinutos * (semaforo[idxSemaforo].min) / 100)) break
      ++idxSemaforo
    }
    log.debug("color = ${semaforo[idxSemaforo].color}")
    return idxSemaforo
  }

  def diffString(Date startDate, Date endDate) {
    def diff = null
    def string = ''
    use ( TimeCategory ) {
      diff = endDate - startDate
      def minutos = diff.minutes
      def horas = diff.hours
      def dias = diff.days
      string = "${dias ? dias.toString() + (dias == 1 ? ' dia ' : ' dias ') : ''}"
      string += "${horas ? horas.toString() + (horas == 1 ? ' hora ' : ' horas ') : ''}"
      string += "${minutos ? minutos.toString() + (minutos == 1 ? ' minuto ' : ' minutos ') : ''}"
      if (!string && minutos == 0) {
        string = "0 minutos"
      }
    }
    string
  }

  def minutesToString(Integer minutos) {
    def string = ''

    Integer horas = minutos / 60
    minutos %= 60

    Integer dias = horas / 24
    horas %= 24

    string = "${dias ? dias.toString() + (dias == 1 ? ' dia ' : ' dias ') : ''}"
    string += "${horas ? horas.toString() + (horas == 1 ? ' hora ' : ' horas ') : ''}"
    string += "${minutos ? minutos.toString() + (minutos == 1 ? ' minuto ' : ' minutos ') : ''}"

    string
  }

    def aprobadores(Cat_servResp area) {
        def areasExpandidas = []
        areasExpandidas += area.descripcion.split("/")
        areasExpandidas = areasExpandidas.flatten()
        log.debug("areasExpandidas = ${areasExpandidas}")
        def usuariosDelArea = []
        areasExpandidas.each{
          usuariosDelArea += UsuarioAutorizado.findAllByArea(it)["id"]
        }
        log.debug("usuariosDelArea = ${usuariosDelArea}")

        def usuarios = Usuario.withNewSession { session ->
          Usuario.findAllEnabledByIdInList(usuariosDelArea)
        }
        log.debug("usuarios = ${usuarios}")

        def aprobadores = usuarios.findAll{thisIsCoordinador(it.id)}
        log.debug("aprobadores = ${aprobadores}")
        aprobadores
    }

    def iniciarSemaforos(HttpSession sessionFirmado) {
      if (!(sessionFirmado["semaforo"])) {
        log.debug("Inicio semaforo")
        sessionFirmado["semaforo"] = Semaforo.list().sort{-it.min}
        log.debug("sessionFirmado['semaforo'] = ${sessionFirmado['semaforo']}")
      }
    }

  def tiempoDeAtencionFormateado(Incidente caso) {
    def valor = tiempoDeAtencion(caso)
    valor.format("yyyy/MM/dd HH:mm")
  }

  def tiempoDeAtencion(Incidente caso) {
    def tiempoAsignado = 0
    for (i in 1..caso.nivel) {
      tiempoAsignado += tiempoAsignadoNivel(caso.idServ, i)
    }
    def tiempoDeAtencion = caso.fechaIncidente.clone()
    use ( TimeCategory ) {
      tiempoDeAtencion += tiempoAsignado.minutes
    }
    tiempoDeAtencion
  }

    // Reportes

  def requerimientosConEncuesta(Date startDate, Date endDate) {
    Solicitud.findAllByEstadoAndLastUpdatedBetween('T' as char, startDate, endDate)
  }

  def incidentesConEncuesta(Date startDate, Date endDate) {
    Incidente.findAllByEstadoAndLastUpdatedBetween('T' as char, startDate, endDate)
  }

  def recibidas(Date startDate, Date endDate) {
    Solicitud.countByEstadoIsNotNullAndFechaAutorizaBetween(startDate, endDate)
  }

  def resueltas(Date startDate, Date endDate) {
    def estados = ['T' as char, 'E' as char]
    Solicitud.countByEstadoInListAndFechaAutorizaBetween(estados,startDate, endDate)
  }

  def pendientes(Date startDate, Date endDate) {
    def estados = ['A' as char, 'V' as char, 'R' as char]
    Solicitud.countByEstadoInListAndFechaAutorizaBetween(estados,startDate, endDate)
  }

  def canceladas(Date startDate, Date endDate) {
    def estados = ['C' as char]
    Solicitud.countByEstadoInListAndFechaAutorizaBetween(estados,startDate, endDate)
  }

  Integer revisarEncuestas(Date startDate, Date endDate) {
    Solicitud.countByEstadoAndLastUpdatedBetween('E' as char, startDate, endDate)
  }

  Integer satisfechos(Date startDate, Date endDate) {
    def requerimientos =
      Solicitud.findAllByEstadoAndLastUpdatedBetween('T' as char, startDate, endDate)
    def cuantos = 0
    requerimientos.each {
      cuantos += it.p01 == 1 && it.p02 == 1 && it.p03 == 1 && it.p04 == 1 ? 1 : 0
    }
    cuantos
  }

  Integer insatisfechos(Date startDate, Date endDate) {
    def requerimientos = Solicitud.findAllByEstadoAndLastUpdatedBetween('T' as char,
                                                            startDate, endDate)
    def cuantos = 0
    requerimientos.each {
      cuantos += it.p01 == 2 || it.p02 == 2 || it.p03 == 2 || it.p04 == 2 ? 1 : 0
    }
    cuantos
  }

  def inciRecibidas(Date startDate, Date endDate) {
    Incidente.countByFechaIncidenteBetween(startDate, endDate)
  }

  def inciResueltas(Date startDate, Date endDate) {
    def estados = ['T' as char, 'E' as char]
    Incidente.countByEstadoInListAndLastUpdatedBetween(estados,startDate, endDate)
  }

  def inciPendientes(Date startDate, Date endDate) {
    Incidente.countByEstadoAndLastUpdatedBetween('A' as char,startDate, endDate)
  }

  def inciCanceladas(Date startDate, Date endDate) {
    Incidente.countByEstadoAndLastUpdatedBetween('C' as char,startDate, endDate)
  }

  Integer inciSatisfechos(Date startDate, Date endDate) {
    def incidentes =
      Incidente.findAllByEstadoAndLastUpdatedBetween('T' as char, startDate, endDate)
    def cuantos = 0
    incidentes.each {
      cuantos += it.p01 == 1 && it.p02 == 1 && it.p03 == 1 && it.p04 == 1 ? 1 : 0
    }
    cuantos
  }

  Integer inciInsatisfechos(Date startDate, Date endDate) {
    def incidentes = Incidente.findAllByEstadoAndLastUpdatedBetween('T' as char,
                                                            startDate, endDate)
    def cuantos = 0
    incidentes.each {
      cuantos += it.p01 == 2 || it.p02 == 2 || it.p03 == 2 || it.p04 == 2 ? 1 : 0
    }
    cuantos
  }

  Integer aMinutos(Long unidades, Integer cantidad) { // TODO: Usarlo 2 veces más en este mismo archivo
    switch ( unidades ) {
      case 1: // Horas
        cantidad
        break
      case 2: // Horas
        cantidad * 60
        break
      case 3: // Días
        cantidad * 60 * 24
        break
      case 4: // Semanas
        cantidad * 60 * 24 * 7
        break
    }
  }

  Integer tiempoAsignado(Cat_serv serv) {
    def minutos = aMinutos(serv.unidades1.id, serv.tiempo1) +
      aMinutos(serv.unidades2.id, serv.tiempo2)
    log.debug("servicio = $serv, minutos = $minutos")
    minutos
  }

  Integer tiempoAsignadoNivel(Cat_serv serv, Integer nivel) {
    aMinutos(serv."unidades$nivel".id, serv."tiempo$nivel")
  }

  Integer cuantosEnTiempoNivel(ArrayList lista, Integer nivel) {
    Integer cuantos = 0
    lista.each {
      // calcular tiempo asignado
      Integer tiempoAsignado = 0
      for (i in 1..nivel) {
        tiempoAsignado += tiempoAsignadoNivel(it.idServ, i)
      }
      log.debug("tiempoAsignado = $tiempoAsignado")
      // calcular tiempo gastado
      def tiempoGastado = diff(it.fechaIncidente, it."fechaSolnivel$nivel")
      log.debug("tiempoGastado = $tiempoGastado")
      // decidir
      cuantos += tiempoGastado <= tiempoAsignado ? 1 : 0
    }
    cuantos
  }

  Integer cuantosReqEnTiempoNivel(ArrayList lista, Integer nivel) {
    Integer cuantos = 0
    lista.each {
      def fechaInicio = it.fechaVb?:it.fechaAutoriza
      it.detalles.each { det ->
        if (det.estado == 'A' as char) {
          Integer tiempoAsignado = 0
          for (i in 1..nivel) {
            tiempoAsignado += tiempoAsignadoNivel(det.idServ, i)
          }
          def tiempoGastado = diff(fechaInicio, det.fechaSolucion)
          cuantos += tiempoGastado <= tiempoAsignado ? 1 : 0
        }
      }
    }
    cuantos
  }

  Integer diff(Date inicio, Date fin) { // TODO: Usarlo 2 veces más en este mismo archivo
    def minutos = 0
    log.debug("inicio = $inicio, fin = $fin")
    use ( TimeCategory ) {
      def diff = fin - inicio
      log.debug "dias = $diff.days"
      minutos = diff.minutes + diff.hours * 60 + diff.days * 24 * 60
    }
    log.debug "minutos = $minutos"
    minutos
  }

  Integer enTiempo(Date startDate, Date endDate) {
    def estados = ['T' as char, 'E' as char]
    def requerimientos = Solicitud.findAllByEstadoInListAndFechaAutorizaBetween(estados,startDate, endDate)
    def casos = 0
    requerimientos.each {
      def buscando = true
      def incremento = 1
      def inicio = it.fechaVb ?: it.fechaAutoriza
      it.detalles.each { det ->
        if (buscando) {
          def tiempoPermitido = tiempoAsignado(det.idServ)
          def fin = det.fechaSolucion
          def tiempoUsado = diff(inicio, fin)
          if (tiempoUsado > tiempoPermitido) {
            incremento = 0
            buscando = false
          }
        }
      }
      casos += incremento
    }
    casos
  }

  Integer enTiempoInci(Date startDate, Date endDate) {
    def estados = ['T' as char, 'E' as char]
    def incidentes = Incidente.findAllByEstadoInListAndLastUpdatedBetween(estados,startDate, endDate)
    def casos = 0
    incidentes.each {
      log.debug("incidente = $it")
      def inicio = it.fechaIncidente
      def nivel = it.nivel

      def tiempoPermitido = tiempoAsignadoNivel(it.idServ, 1)
      if (nivel > 1) {
        tiempoPermitido += tiempoAsignadoNivel(it.idServ, 2)
      }
      if (nivel == 3) {
        tiempoPermitido += tiempoAsignadoNivel(it.idServ, 3)
      }
      def fin = it."fechaSolnivel$nivel"
      def tiempoUsado = diff(inicio, fin)
      if (tiempoUsado <= tiempoPermitido) {
        casos++
      }
    }
    casos
  }

}