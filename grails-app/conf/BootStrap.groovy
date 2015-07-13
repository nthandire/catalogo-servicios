import catalogo.servicios.Role
import catalogo.servicios.User
import catalogo.servicios.UserRole
import catalogo.servicios.Requestmap

class BootStrap {

  def init = { servletContext ->
    if (User.count() == 0) {
      def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
      def userRole = new Role(authority: 'ROLE_USER').save(flush: true)
  /*
      def adminRole = Role.findByAuthority('ROLE_ADMIN')
      def userRole = Role.findByAuthority('ROLE_USER')
  */
      def yo = new User(username: 'yo', enabled: true, password: 'password')
      yo.save(flush: true)
      def testAdmin = new User(username: 'admin', enabled: true, password: 'password')
      testAdmin.save(flush: true)
      def testUser = new User(username: 'usuario', enabled: true, password: 'password')
      testUser.save(flush: true)
  /*
      def yo = User.findByUsername('yo')
      def testAdmin = User.findByUsername('admin')
      def testUser = User.findByUsername('usuario')
  */
      UserRole.create yo, adminRole, true
      UserRole.create testAdmin, adminRole, true
      UserRole.create testUser, userRole, true

      new Requestmap(url: '/login/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()
      new Requestmap(url: '/logout/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()
      new Requestmap(url: '/user/**', configAttribute: 'ROLE_ADMIN').save()
      new Requestmap(url: '/role/**', configAttribute: 'ROLE_ADMIN').save()
      // TODO: quitar el acceso a la BD
      new Requestmap(url: '/dbconsole/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()
      new Requestmap(url: '/', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()
      new Requestmap(url: '/**', configAttribute: 'IS_AUTHENTICATED_FULLY').save()

      assert User.count() == 3
      assert Role.count() == 2
      assert UserRole.count() == 3
    }
  }

  def destroy = {
  }
}
