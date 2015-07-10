import catalogo.servicios.Role
import catalogo.servicios.User
import catalogo.servicios.UserRole
import catalogo.servicios.Requestmap

class BootStrap {

  def init = { servletContext ->
    def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
    def userRole = new Role(authority: 'ROLE_USER').save(flush: true)

    def testUser = new User(username: 'me', enabled: true, password: 'password')
    testUser.save(flush: true)

    UserRole.create testUser, adminRole, true

    new Requestmap(url: '/login/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()
    new Requestmap(url: '/logout/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()
    // TODO: quitar el acceso a la BD
    new Requestmap(url: '/dbconsole/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()
    new Requestmap(url: '/', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()
    new Requestmap(url: '/**', configAttribute: 'IS_AUTHENTICATED_FULLY').save()

    assert User.count() == 1
    assert Role.count() == 2
    assert UserRole.count() == 1
  }

  def destroy = {
  }
}
