package mx.gob.inr.catservicios

import grails.plugins.springsecurity.Secured

@Secured(['ROLE_SAST_ADMIN'])
class Cat_tiempoController {
    static nombreMenu = "Catálogo de Unidades de Tiempo"
    static ordenMenu = 6

    static scaffold = Cat_tiempo
}
