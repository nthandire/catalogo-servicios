package mx.gob.inr.catservicios

import javax.servlet.http.HttpSession
import groovy.time.*

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
        def idArea = area ?: Usuario.get(usuario).idUnidadMedica
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

}