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

  Cat_servResp area(HttpSession sessionFirmado, Long userID) {
    log.debug("area: userID = ${userID}")
    if (sessionFirmado["area"] == null) {
      log.debug("no lo tengo, busco el área")
      def area = _area(userID)
      sessionFirmado["area"] = area
    }
    log.debug("en FirmadoService area = ${sessionFirmado["area"]}")

    sessionFirmado["area"]
  }

  // para usarlo en console
  Cat_servResp _area (Long userID) {
    def areaString = UsuarioAutorizado.get(userID)
    if (!areaString)
      return null
    Cat_servResp.findByDescripcion(areaString.area)
  }
}
