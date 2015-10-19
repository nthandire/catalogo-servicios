package mx.gob.inr.catservicios

import grails.plugins.springsecurity.Secured

@Secured(['ROLE_SAST_ADMIN'])
class RolController {
    static nombreMenu = "Roles"
    static ordenMenu = -161

    static scaffold = Rol
}
