package mx.gob.inr.catservicios

import javax.servlet.http.HttpSession

class FirmadoService {
  static transactional = false

  Boolean isGestor(HttpSession sessionFirmado, Long userID) {
    if (sessionFirmado["gestor"] == null) {
      log.debug("no lo tengo, busco el gestor")
      def gestor = false
      UsuarioRol.withNewSession { session ->
        def rolUsuarios = UsuarioRol.findAllByUsuario(Usuario.get(userID)).each{
          log.debug("UsuarioRol = $it")
          if(it.rol.authority == 'ROLE_SAST_COORDINADOR_DE_GESTION') {
            gestor = true
          }
        }
      }
      sessionFirmado["gestor"] = gestor
    }
    log.debug("en FirmadoService isGestor: gestor = ${sessionFirmado["gestor"]}")

    return sessionFirmado["gestor"]
  }

}
