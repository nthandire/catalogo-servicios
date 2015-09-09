package mx.gob.inr.catservicios

import grails.plugins.springsecurity.Secured

@Secured(['ROLE_SAST_ADMIN'])
class UsuarioRolController {
    static nombreMenu = "Usuarios-Roles"
    static ordenMenu = 163

    static scaffold = UsuarioRol
}
