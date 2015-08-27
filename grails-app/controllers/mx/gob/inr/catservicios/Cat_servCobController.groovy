package mx.gob.inr.catservicios

import grails.plugins.springsecurity.Secured

@Secured(['ROLE_COORDINADOR'])
class Cat_servCobController {
    static nombreMenu = "Catálogo de Coberturas"
    static ordenMenu = 4

    static scaffold = Cat_servCob
}
