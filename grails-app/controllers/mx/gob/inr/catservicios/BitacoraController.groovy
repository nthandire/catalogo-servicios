package mx.gob.inr.catservicios

import grails.plugins.springsecurity.Secured

@Secured(['ROLE_SAST_ADMIN'])
class BitacoraController {
    static nombreMenu = "Bitacoras"
    static ordenMenu = 31

    static scaffold = Bitacora
}
