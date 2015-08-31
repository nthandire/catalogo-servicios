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
  /*
      assert Usuario.count() == 4
      assert Rol.count() == 3
      assert UsuarioRol.count() == 5
  */
      Rol.withNewSession { session ->
        new Rol(authority: 'ROLE_ADMIN').save(flush: true)
        new Rol(authority: 'ROLE_USUARIO').save(flush: true)
        new Rol(authority: 'ROLE_TECNICO').save(flush: true)
        new Rol(authority: 'ROLE_COORDINADOR').save(flush: true)
      }

      Usuario.withNewSession { session ->
        new Usuario(username: 'admin', enabled: true, password: 'password', estatus: 'A',
        ACCOUNT_EXPIRED: false, ACCOUNT_LOCKED: false, PASSWORD_EXPIRED: false).save(flush: true)
        new Usuario(username: 'usuario1', enabled: true, password: 'usuario1', estatus: 'A',
        ACCOUNT_EXPIRED: false, ACCOUNT_LOCKED: false, PASSWORD_EXPIRED: false).save(flush: true)
        new Usuario(username: 'usuario2', enabled: true, password: 'usuario2', estatus: 'A',
        ACCOUNT_EXPIRED: false, ACCOUNT_LOCKED: false, PASSWORD_EXPIRED: false).save(flush: true)
        new Usuario(username: 'mesaservicio', enabled: true, password: 'mesaservicio', estatus: 'A',
        ACCOUNT_EXPIRED: false, ACCOUNT_LOCKED: false, PASSWORD_EXPIRED: false).save(flush: true)
        new Usuario(username: 'mesaservicio2', enabled: true, password: 'mesaservicio2', estatus: 'A',
        ACCOUNT_EXPIRED: false, ACCOUNT_LOCKED: false, PASSWORD_EXPIRED: false).save(flush: true)
        new Usuario(username: 'coordinador', enabled: true, password: 'coordinador', estatus: 'A',
        ACCOUNT_EXPIRED: false, ACCOUNT_LOCKED: false, PASSWORD_EXPIRED: false).save(flush: true)
      }

      UsuarioRol.withNewSession { session ->
        UsuarioRol.create Usuario.findByUsername('admin'), Rol.findByAuthority('ROLE_ADMIN'), true
        UsuarioRol.create Usuario.findByUsername('usuario1'), Rol.findByAuthority('ROLE_USUARIO'), true
        UsuarioRol.create Usuario.findByUsername('usuario2'), Rol.findByAuthority('ROLE_USUARIO'), true
        UsuarioRol.create Usuario.findByUsername('mesaservicio'), Rol.findByAuthority('ROLE_TECNICO'), true
        UsuarioRol.create Usuario.findByUsername('mesaservicio2'), Rol.findByAuthority('ROLE_TECNICO'), true
        UsuarioRol.create Usuario.findByUsername('coordinador'), Rol.findByAuthority('ROLE_COORDINADOR'), true
      }




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
      
      
      new CatPrograma(desPrograma: 'Mantenimiento', estadoPrograma:'A').save()
      new CatPrograma(desPrograma: 'Aprovisionamiento', estadoPrograma:'A').save()
*/
    }
  }

  def destroy = {
  }
}
