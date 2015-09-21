package mx.gob.inr.catservicios

import grails.plugins.springsecurity.Secured

@Secured(['ROLE_SAST_ADMIN'])
class Cat_servRespController {
    static nombreMenu = "Catálogo de Responsables"
    static ordenMenu = 5

    static scaffold = Cat_servResp
}
