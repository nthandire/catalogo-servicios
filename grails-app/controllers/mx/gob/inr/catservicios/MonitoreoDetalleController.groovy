package mx.gob.inr.catservicios

import grails.plugins.springsecurity.Secured

@Secured(['ROLE_SAST_ADMIN'])
class MonitoreoDetalleController {
    static nombreMenu = "Detalle de Monitoreos"
    static ordenMenu = 34

    static scaffold = MonitoreoDetalle
}
