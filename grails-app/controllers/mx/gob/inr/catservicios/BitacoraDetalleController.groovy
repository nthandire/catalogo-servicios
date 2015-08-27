package mx.gob.inr.catservicios

import grails.plugins.springsecurity.Secured

@Secured(['ROLE_ADMIN'])
class BitacoraDetalleController {
    static nombreMenu = "Detalles de las Bitacoras"
    static ordenMenu = 32

    static scaffold = BitacoraDetalle
}
