import mx.gob.inr.catservicios.*

class BootStrap {

  def init = { servletContext ->
    if (Usuario.count() == 0) {
      def adminRole = new Rol(authority: 'ROLE_ADMIN').save(flush: true)
      def userRole = new Rol(authority: 'ROLE_USER').save(flush: true)
  /*
      def adminRole = Rol.findByAuthority('ROLE_ADMIN')
      def userRole = Rol.findByAuthority('ROLE_USER')
  */
      def yo = new Usuario(username: 'yo', enabled: true, password: 'password')
      yo.save(flush: true)
      def testAdmin = new Usuario(username: 'admin', enabled: true, password: 'password')
      testAdmin.save(flush: true)
      def testUser = new Usuario(username: 'usuario', enabled: true, password: 'password')
      testUser.save(flush: true)
  /*
      def yo = User.findByUsername('yo')
      def testAdmin = User.findByUsername('admin')
      def testUser = User.findByUsername('usuario')
  */
      UsuarioRol.create yo, adminRole, true
      UsuarioRol.create yo, userRole, true
      UsuarioRol.create testAdmin, adminRole, true
      UsuarioRol.create testUser, userRole, true

      new Requestmap(url: '/login/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()
      new Requestmap(url: '/logout/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()
      new Requestmap(url: '/usuario/**', configAttribute: 'ROLE_ADMIN').save()
      new Requestmap(url: '/rol/**', configAttribute: 'ROLE_ADMIN').save()
      // TODO: quitar el acceso a la BD
      new Requestmap(url: '/dbconsole/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()
      new Requestmap(url: '/', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()
      new Requestmap(url: '/**', configAttribute: 'IS_AUTHENTICATED_FULLY').save()

      assert Usuario.count() == 3
      assert Rol.count() == 2
      assert UsuarioRol.count() == 4

      def laBitacora = new Bitacora(tipoBitacora: "Monitoreo",
        desBitacora: "IBM BladeCenter H Advanced Management Module").save(flush: true)

      assert Bitacora.count() == 1

      new BitacoraDetalle(desBitacoradetalle: "IP 192.168.10.163",
        bitacora: laBitacora).save(flush: true)

      assert BitacoraDetalle.count() == 1
    }
  }

  def destroy = {
  }
}
