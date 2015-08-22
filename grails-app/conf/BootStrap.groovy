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
      new Requestmap(url: '/usuarioRol/**', configAttribute: 'ROLE_ADMIN').save()
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

      def tiempoMinutos = new Cat_tiempo(descripcion: 'Minutos')
      tiempoMinutos.save(flush: true)
      new Cat_tiempo(descripcion: 'Horas').save()
      def tiempoDias = new Cat_tiempo(descripcion: 'Días')
      tiempoDias.save(flush: true)
      new Cat_tiempo(descripcion: 'Semanas').save()
    } else { // TODO: borrar el else cuando no se use BD en memoria

      /*
      def tiempoMinutos = new Cat_tiempo(descripcion: 'Minutos')
      tiempoMinutos.save(flush: true)
      new Cat_tiempo(descripcion: 'Horas').save()
      def tiempoDias = new Cat_tiempo(descripcion: 'Días')
      tiempoDias.save(flush: true)
      new Cat_tiempo(descripcion: 'Semanas').save(flush: true)
      def respDGAIT = new Cat_servResp(descripcion: 'DGAIT')
      respDGAIT.save(flush: true)
      def servRespMS = new Cat_servResp(descripcion: 'MS')
      servRespMS.save(flush: true)
      def servRespWM = new Cat_servResp(descripcion: 'WM')
      servRespWM.save(flush: true)
      def servRespPROV = new Cat_servResp(descripcion: 'PROV')
      servRespPROV.save(flush: true)
      def servRespSubdireccion = new Cat_servResp(descripcion: 'Subdireccion')
      servRespSubdireccion.save(flush: true)
      def servRespDireccion = new Cat_servResp(descripcion: 'Dirección/ Subdireccion')
      servRespDireccion.save(flush: true)
      def cobTODAS = new Cat_servCob(descripcion: 'TODAS LAS AREAS')
      cobTODAS.save(flush: true)
      new Cat_servCob(descripcion: 'HOSPITALIZACION').save()
      new Cat_servCob(descripcion: 'ADMINISTRATIVAS').save()
      new Cat_servCob(descripcion: 'SUSTANTIVAS').save(flush: true)
      */
      /*
      def servCatVoceoGeneral = new Cat_servCat(categoria: 'Voceo General',
        descripcion: 'Reparación de bocinas y  control de volumen así como Comunicados mediante el sistema de voceo general',
        servResp:respDGAIT, valoracion: 2, disponibilidad: 90, estado: 'A',
        servCob:cobTODAS)
      servCatVoceoGeneral.save(flush: true)
      def servSubBocinas = new Cat_servSub(descripcion: 'Bocinas', servCat: servCatVoceoGeneral)
      servSubBocinas.save(flush: true)
      log.info(servSubBocinas)
      def serv = new Cat_serv(servSub:servSubBocinas,
        descripcion: 'El sonido tiene el volumen muy alto', portal:true,
        incidente:true, servResp1:servRespMS, servResp2:respDGAIT, servResp3:respDGAIT,
        tiempo1:30, tiempo2:60, tiempo3:1,
        unidades1:tiempoMinutos, unidades2:tiempoMinutos, unidades3:tiempoDias,
        impacto:3, plantilla: 'plantilla general', idUsuario:3, estadoServ:'A', 
        ipTerminal:'192.168.16.59', lastUpdated: new Date())
      serv.save(flush: true, failOnError: true)
      log.info(serv)
      */
      /*
      new CatPrograma(desPrograma: 'Mantenimiento', estadoPrograma:'A').save()
      new CatPrograma(desPrograma: 'Aprovisionamiento', estadoPrograma:'A').save()
      */
      
      /*
      // deben quedar en el definitivo
      Rol.withNewSession { session ->
        new Rol(authority: 'ROLE_USUARIO').save(flush: true)
        new Rol(authority: 'ROLE_MESA').save(flush: true)
        new Rol(authority: 'ROLE_COORDINADOR').save(flush: true)
      }
      // def normalUser
      Usuario.withNewSession { session ->
        new Usuario(username: 'usuario1', enabled: true, password: 'usuario1').save(flush: true)
        new Usuario(username: 'usuario2', enabled: true, password: 'usuario2').save(flush: true)
        new Usuario(username: 'mesaservicio', enabled: true, password: 'mesaservicio').save(flush: true)
        new Usuario(username: 'mesaservicio2', enabled: true, password: 'mesaservicio2').save(flush: true)
        new Usuario(username: 'coordinador', enabled: true, password: 'coordinador').save(flush: true)
      }


      UsuarioRol.withNewSession { session ->
        UsuarioRol.create Usuario.findByUsername('usuario1'), Rol.findByAuthority('ROLE_USUARIO'), true
        UsuarioRol.create Usuario.findByUsername('usuario2'), Rol.findByAuthority('ROLE_USUARIO'), true
        UsuarioRol.create Usuario.findByUsername('mesaservicio'), Rol.findByAuthority('ROLE_MESA'), true
        UsuarioRol.create Usuario.findByUsername('mesaservicio2'), Rol.findByAuthority('ROLE_MESA'), true
        UsuarioRol.create Usuario.findByUsername('coordinador'), Rol.findByAuthority('ROLE_COORDINADOR'), true
      }

      Requestmap.withNewSession { session ->

        new Requestmap(url: '/solicitud/**', configAttribute: 'ROLE_USUARIO').save()
        new Requestmap(url: '/solicituddetalle/**', configAttribute: 'ROLE_USUARIO').save()
        new Requestmap(url: '/solicitudArchivoadjunto/**', configAttribute: 'ROLE_USUARIO').save()
        new Requestmap(url: '/incidente/**', configAttribute: 'ROLE_MESA').save()
        new Requestmap(url: '/incidenteLaboratorio/**', configAttribute: 'ROLE_MESA').save()
        new Requestmap(url: '/incidenteArchivoadjunto/**', configAttribute: 'ROLE_MESA').save()
        new Requestmap(url: '/problema/**', configAttribute: 'ROLE_MESA').save()
        new Requestmap(url: '/proveedor/**', configAttribute: 'ROLE_MESA').save()
        new Requestmap(url: '/catEstado/**', configAttribute: 'ROLE_MESA').save()
        new Requestmap(url: '/catSistema/**', configAttribute: 'ROLE_MESA').save()
        new Requestmap(url: '/catPrograma/**', configAttribute: 'ROLE_MESA').save()
        new Requestmap(url: '/catTiposervcio/**', configAttribute: 'ROLE_MESA').save()


        //  error new Requestmap(url: '/**', configAttribute: 'IS_AUTHENTICATED_FULLY').save()
        //  error new Requestmap(url: '/', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()
        new Requestmap(url: '/cat_serv/**', configAttribute: 'ROLE_ADMIN').save()
        new Requestmap(url: '/cat_servCat/**', configAttribute: 'ROLE_ADMIN').save()
        new Requestmap(url: '/cat_servSub/**', configAttribute: 'ROLE_ADMIN').save()
        new Requestmap(url: '/cat_bitacora/**', configAttribute: 'ROLE_ADMIN').save()
      }
      */
    }
  }

  def destroy = {
  }
}
