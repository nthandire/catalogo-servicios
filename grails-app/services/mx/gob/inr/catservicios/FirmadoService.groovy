package mx.gob.inr.catservicios

import javax.servlet.http.HttpSession

class FirmadoService {
  static transactional = false

  Boolean isGestor(HttpSession sessionFirmado, Long userID) {
    isRol("gestor", "ROLE_SAST_COORDINADOR_DE_GESTION", sessionFirmado, userID)
  }

  Boolean isTecnico(HttpSession sessionFirmado, Long userID) {
    isRol("técnico", "ROLE_SAST_TECNICO", sessionFirmado, userID)
  }

  Boolean isCoordinador(HttpSession sessionFirmado, Long userID) {
    isRol("coordinador", "ROLE_SAST_COORDINADOR", sessionFirmado, userID)
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
    thisIsRol("gestor", "ROLE_SAST_COORDINADOR_DE_GESTION", userID)
  }

  Boolean thisIsCoordinador(Long userID) {
    thisIsRol("coordinador", "ROLE_SAST_COORDINADOR", userID)
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

}
