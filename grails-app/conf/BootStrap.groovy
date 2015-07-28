import mx.gob.inr.catservicios.*
import net.sf.jasperreports.engine.util.JRProperties
 
class BootStrap {

  def init = { servletContext ->
    JRProperties.setProperty("net.sf.jasperreports.awt.ignore.missing.font", "true")
    JRProperties.setProperty("net.sf.jasperreports.default.font.name", "Helvetica")
    JRProperties.setProperty("net.sf.jasperreports.default.pdf.font.name", "Helvetica")
    JRProperties.setProperty("net.sf.jasperreports.default.pdf.encoding", "UTF-8")
    JRProperties.setProperty("net.sf.jasperreports.default.pdf.embedded", "false")

    if (Usuario.count() == 0) {
      def adminRole = new Rol(authority: 'ROLE_ADMIN').save(flush: true)
      def userRole = new Rol(authority: 'ROLE_USER').save(flush: true)
      def serviciosRol = new Rol(authority: 'ROLE_SERVICES').save(flush: true)
  /*
      def adminRole = Rol.findByAuthority('ROLE_ADMIN')
      def userRole = Rol.findByAuthority('ROLE_USER')
  */
      def yo = new Usuario(username: 'yo', enabled: true, password: 'password')
        .save(flush: true)
      def testAdmin = new Usuario(username: 'admin', enabled: true, password: 'password')
        .save(flush: true)
      def testUser = new Usuario(username: 'usuario', enabled: true, password: 'password')
        .save(flush: true)
      def serviciosUser = new Usuario(username: 'servicios', enabled: true, password: 'servicios')
        .save(flush: true)
  /*
      def yo = Usuario.findByUsername('yo')
      def testAdmin = Usuario.findByUsername('admin')
      def testUser = Usuario.findByUsername('usuario')
  */
      UsuarioRol.create yo, adminRole, true
      UsuarioRol.create yo, userRole, true
      UsuarioRol.create testAdmin, adminRole, true
      UsuarioRol.create testUser, userRole, true
      UsuarioRol.create serviciosUser, serviciosRol, true

      new Requestmap(url: '/login/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()
      new Requestmap(url: '/logout/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()
      new Requestmap(url: '/usuario/**', configAttribute: 'ROLE_ADMIN').save()
      new Requestmap(url: '/rol/**', configAttribute: 'ROLE_ADMIN').save()
      // TODO: quitar el acceso a la BD
      new Requestmap(url: '/dbconsole/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()
      new Requestmap(url: '/', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()
      new Requestmap(url: '/cat_tiempo/**', configAttribute: 'ROLE_ADMIN').save()
      new Requestmap(url: '/**', configAttribute: 'IS_AUTHENTICATED_FULLY').save()
      new Requestmap(url: '/bitacora/**', configAttribute: 'ROLE_USER').save()
      new Requestmap(url: '/bitacoraDetalle/**', configAttribute: 'ROLE_USER').save()
      new Requestmap(url: '/monitoreo/**', configAttribute: 'ROLE_USER').save()
      new Requestmap(url: '/monitoreoDetalle/**', configAttribute: 'ROLE_USER').save()
      new Requestmap(url: '/cat_servCob/**', configAttribute: 'ROLE_USER').save()
      new Requestmap(url: '/cat_servResp/**', configAttribute: 'ROLE_USER').save()

      assert Usuario.count() == 4
      assert Rol.count() == 3
      assert UsuarioRol.count() == 5

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
