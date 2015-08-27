package mx.gob.inr.catservicios

import grails.plugins.springsecurity.Secured

@Secured(['ROLE_ADMIN'])
class UsuarioController {
    static nombreMenu = "Usuarios"
    static ordenMenu = 162

    static scaffold = Usuario
}
