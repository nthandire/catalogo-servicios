import mx.gob.inr.catservicios.*

def nombre = "usuario10"
Usuario.withNewSession { session ->
  new Usuario(username: nombre, enabled: true, password: nombre, estatus: 'A',
              ACCOUNT_EXPIRED: false, ACCOUNT_LOCKED: false, PASSWORD_EXPIRED: false).save(flush: true)
}

      UsuarioRol.withNewSession { session ->
        new UsuarioRol(usuario:Usuario.findByUsername('usuario3'), rol: Rol.findByAuthority('ROLE_USUARIO')).save(flush:true)
      }

/*
      UsuarioRol.withNewSession { session ->
        UsuarioRol.create Usuario.findByUsername('usuario3'), Rol.findByAuthority('ROLE_USUARIO'), true
        UsuarioRol.create Usuario.findByUsername('usuario4'), Rol.findByAuthority('ROLE_USUARIO'), true
        UsuarioRol.create Usuario.findByUsername('usuario5'), Rol.findByAuthority('ROLE_USUARIO'), true
        UsuarioRol.create Usuario.findByUsername('usuario6'), Rol.findByAuthority('ROLE_USUARIO'), true
        UsuarioRol.create Usuario.findByUsername('usuario7'), Rol.findByAuthority('ROLE_USUARIO'), true
        UsuarioRol.create Usuario.findByUsername('usuario8'), Rol.findByAuthority('ROLE_USUARIO'), true
        UsuarioRol.create Usuario.findByUsername('usuario9'), Rol.findByAuthority('ROLE_USUARIO'), true
      }
*/
/*
      UsuarioRol.withNewSession { session ->
        UsuarioRol.list().each {it.flush()}
        flush()
      }
*/


println Usuario.list()
Usuario.withNewSession { session ->
Usuario.list().each {
  println "$it.id, $it.username"
}
}

UsuarioRol.withNewSession { session ->
UsuarioRol.list().each {
  println "$it.usuario, $it.rol"
}
}
