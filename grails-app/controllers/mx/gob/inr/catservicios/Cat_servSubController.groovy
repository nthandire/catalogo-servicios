package mx.gob.inr.catservicios

import grails.plugins.springsecurity.Secured

@Secured(['ROLE_COORDINADOR'])
class Cat_servSubController {
    static nombreMenu = "Catálogo de Subcategorías de Servicios"
    static ordenMenu = 2

    static scaffold = Cat_servSub
}
