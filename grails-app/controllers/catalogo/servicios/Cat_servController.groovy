package catalogo.servicios

import grails.plugins.springsecurity.Secured

@Secured(['ROLE_ADMIN'])
class Cat_servController {

    static scaffold = Cat_serv
}
