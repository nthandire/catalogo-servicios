package mx.gob.inr.catservicios

import grails.plugins.springsecurity.Secured
import org.springframework.dao.DataIntegrityViolationException
import groovy.time.TimeCategory

@Secured(['ROLE_SAST_COORDINADOR_DE_GESTION'])
class SolicitudGestionController {
    def springSecurityService
    def firmadoService
    static nombreMenu = "Mesa de servicio"
    static ordenMenu = 88

    static allowedMethods = [save: "POST", update: "POST", x_delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        log.debug("params = $params")
        def query =
            "  from Solicitud              " +
            " where (estado = 'A'          " +
            "        and idVb is null)     " +
            "    or  estado = 'V'          "
        def autorizados = Solicitud.executeQuery (
          "select count (*) " + query
        )[0]
        log.debug("numero de autorizados = ${autorizados}")
        [autorizadosInstanceList: Solicitud.executeQuery(query, [], params),
            autorizadosInstanceTotal: autorizados]
    }

    def listAsignados(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        log.debug("params = $params")

        def query =
          "from Solicitud s                                     " +
          " where s.estado = 'R'                                " +
          "   and not exists(select id from SolicitudDetalle d  " +
          "               where s.id = d.idSolicitud            " +
          "                 and idTecnico is null)              "
        def asignados = Solicitud.executeQuery(
            "select count(*) " + query
        )[0]
        log.debug("numero de asignados = ${asignados}")
        [asignadosInstanceList: Solicitud.executeQuery(query, [], params),
            asignadosInstanceTotal: asignados]
    }

    def listEncuestas(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        log.debug("params = $params")

        def query =
          "from Solicitud s          " +
          " where s.estado = 'E'     "
        def enEncuestas = Solicitud.executeQuery(
            "select count(*) " + query
        )[0]
        log.debug("numero de enEncuestas = ${enEncuestas}")
        [enEncuestasInstanceList: Solicitud.executeQuery(query, [], params),
            enEncuestasInstanceTotal: enEncuestas]
    }

    def listTerminadas(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        log.debug("params = $params")

        def query =
          "from Solicitud s          " +
          " where s.estado = 'T'     "
        def terminadas = Solicitud.executeQuery(
            "select count(*) " + query
        )[0]
        log.debug("numero de terminadas = ${terminadas}")
        [terminadasInstanceList: Solicitud.executeQuery(query, [], params),
            terminadasInstanceTotal: terminadas]
    }

    def create() {
      log.debug("params = $params")
      def solicitudArchivoadjuntoInstance = new SolicitudArchivoadjunto()
      def solicitud = Solicitud.get(params.solicitud["id"])
      solicitudArchivoadjuntoInstance.idSolicitud = solicitud
      [solicitudArchivoadjuntoInstance: solicitudArchivoadjuntoInstance, back:params.detalle["id"]]
    }

    def save() {
      log.debug("params = $params")
      def file = request.getFile('file')
      if(file.empty) {
        flash.message = "Debe enviar algún archivo"
        render(view: "create")
        return
      } else {
        def solicitudArchivoadjuntoInstance = new SolicitudArchivoadjunto()
        def solicitud = Solicitud.get(params.idSolicitud)
        solicitudArchivoadjuntoInstance.idSolicitud = solicitud
        def nombre = file.originalFilename
        solicitudArchivoadjuntoInstance.nombre = nombre
        solicitudArchivoadjuntoInstance.datos = file.getBytes()
        solicitudArchivoadjuntoInstance.tamaño =
          solicitudArchivoadjuntoInstance.datos.size()
        solicitudArchivoadjuntoInstance.idUsuario = springSecurityService.principal.id
        solicitudArchivoadjuntoInstance.ipTerminal = request.getRemoteAddr()
        def dot = nombre.lastIndexOf('.');
        if (dot > 0)
          solicitudArchivoadjuntoInstance.tipo = nombre.substring(dot + 1).
            toUpperCase()
        else
          solicitudArchivoadjuntoInstance.tipo = ""
        if (!solicitudArchivoadjuntoInstance.save(flush: true)) {
            render(view: "create", model: [solicitudArchivoadjuntoInstance: solicitudArchivoadjuntoInstance])
            return
        }
        redirect (action:'edit', id: solicitud.id)
      }
    }

    def show(Long id) {
        def solicitudInstance = Solicitud.get(id)
        if (!solicitudInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitud.label', default: 'Solicitud'), id])
            redirect(action: "list")
            return
        }

        [solicitudInstance: solicitudInstance,
          autorizadores:listaDeVobos()]
    }

    def showDetalle(Long id) {
        def solicitudDetalleInstance = SolicitudDetalle.get(id)
        if (!solicitudDetalleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudDetalle.label', default: 'SolicitudDetalle'), id])
            redirect(action: "list")
            return
        }

        [solicitudDetalleInstance: solicitudDetalleInstance]
    }

    def showArchivo(Long id) {
        def solicitudArchivoadjuntoInstance = SolicitudArchivoadjunto.get(id)
        if (!solicitudArchivoadjuntoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudArchivoadjunto.label', default: 'SolicitudArchivoadjunto'), id])
            redirect(action: "list")
            return
        }

        [solicitudArchivoadjuntoInstance: solicitudArchivoadjuntoInstance]
    }

    def showNoFirma(Long id) {
        def solicitudInstance = Solicitud.get(id)
        if (!solicitudInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitud.label', default: 'Solicitud'), id])
            redirect(action: "list")
            return
        }

        [solicitudInstance: solicitudInstance]
    }

    def edit(Long id) {
        def solicitudDetalleInstance = SolicitudDetalle.get(id)
        if (!solicitudDetalleInstance) {
            flash.message = message(code: 'default.not.found.message',
              args: [message(code: 'solicitudDetalle.label',
                             default: 'SolicitudDetalle'), id])
            redirect(action: "listDetalle")
            return
        }

        def query =
          "    from Cat_servSub s            \n" +
          "   where s.servCat = ?            \n" +
          "     and exists (                 \n" +
          "       from Cat_serv t            \n" +
          "      where s.id = t.servSub      \n" +
          "        and t.incidente = 'f'     \n" +
          "     )                            \n"

        def subcategorias = Cat_servSub.executeQuery(query,
          [solicitudDetalleInstance.idServcat])
        log.debug("numero de subcategorias = ${subcategorias.size()}")

        [solicitudDetalleInstance: solicitudDetalleInstance,
            subcategorias: subcategorias]
    }

    def listaDeVobos() {
      def miembros = UsuarioAutorizado.findAllVoboByEstado('A' as char).collect{it.id}

      def autorizadores = []
      Usuario.withNewSession { sessionU ->
        autorizadores = Usuario.findAllByIdInList(miembros)
      }

      log.debug("numero de autorizadores = ${autorizadores.size()}")
      return autorizadores
    }

    def revisar(Long id) {
        def solicitudInstance = Solicitud.get(id)
        if (!solicitudInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitud.label', default: 'Solicitud'), id])
            redirect(action: "list")
            return
        }

        def detalles = SolicitudDetalle.countByIdSolicitudAndIdServIsNull(solicitudInstance)
        log.debug("detalles = $detalles")
        if (detalles) {
            flash.error = "Debe capturar antes todos los servicios de tercer nivel."
            render(view: "show", model: [solicitudInstance: solicitudInstance])
            return
        }

        def userID = springSecurityService.principal.id
        def firmaTeclada = params['passwordfirma']
        def firma = Firmadigital.findById(userID)?.passwordfirma

        if (firmaTeclada != firma) {
          flash.error = "Error en contaseña"
          render(view: "show", model: [solicitudInstance: solicitudInstance])
          return
        }

        solicitudInstance.estado = 'R' as char
        solicitudInstance.idRevisa = userID

        if (!solicitudInstance.save(flush: true)) {
            render(view: "show", model: [solicitudInstance: solicitudInstance])
            return
        }

        def areas = []
        solicitudInstance.detalles.each {
          if (!areas.contains(it.idServcat.servResp))
            areas << it.idServcat.servResp
        }
        log.debug("areas = ${areas}")
        def areasExpandidas = []
        areas.each {areasExpandidas += it.descripcion.split("/")}
        areasExpandidas = areasExpandidas.flatten()
        log.debug("areasExpandidas = ${areasExpandidas}")
        def usuariosDelArea = []
        areasExpandidas.each{
          usuariosDelArea += UsuarioAutorizado.findAllByArea(it)["id"]
        }
        log.debug("usuariosDelArea = ${usuariosDelArea}")

        def usuarios = Usuario.withNewSession { session ->
          Usuario.findAllEnabledByIdInList(usuariosDelArea)
        }
        log.debug("usuarios = ${usuarios}")

        def coordinadores = usuarios.findAll{firmadoService.thisIsCoordinador(it.id)}
        log.debug("coordinadores = ${coordinadores}")

        def liga = createLink(controller: "SolicitudCoordinador",
                              action: "edit", id: solicitudInstance.id,
                              absolute: "true")
        log.debug("liga = $liga")
        def asunto = "Solicitud ${solicitudInstance} requiere procesarse"

        coordinadores.each {
          def msg = "Hola ${it}<br/><br/>La solicitud folio " +
            "${solicitudInstance} (${solicitudInstance.justificacion}) " +
            "ya fue revisada, debe atenderla a la brevedad. <br/><br/>" +
            "<a href='${liga}'>${solicitudInstance}</a>"
          sendMail {
            to 'dzamora@inr.gob.mx' // TODO: mandar el correo al que lo solicito       coordinadores.email
            subject asunto
            html msg
          }
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'solicitud.label', default: 'Solicitud'), solicitudInstance.toString()])
        redirect(action: "show", id: solicitudInstance.id)
    }

    def updateVB(Long id, Long version) {
        def solicitudInstance = Solicitud.get(id)
        if (!solicitudInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitud.label', default: 'Solicitud'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (solicitudInstance.version > version) {
                solicitudInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'solicitud.label', default: 'Solicitud')] as Object[],
                          "Another user has updated this Solicitud while you were editing")
                render(view: "show", model: [solicitudInstance: solicitudInstance])
                return
            }
        }

        def userID = springSecurityService.principal.id
        def firmaTeclada = params['passwordfirma']
        def firma = Firmadigital.findById(userID)?.passwordfirma

        if (firmaTeclada != firma) {
          flash.error = "Error en contaseña"
          render(view: "show", model: [solicitudInstance: solicitudInstance])
          return
        }

        solicitudInstance.properties = params

        if (!solicitudInstance.save(flush: true)) {
            render(view: "show", model: [solicitudInstance: solicitudInstance])
            return
        }

        def idUsuario = springSecurityService.principal.id
        def personasInstance = Usuario.get(solicitudInstance.idVb)
        def liga = createLink(controller:"solicitudVB", action: "show",
                              id: solicitudInstance.id, absolute: "true")
        log.debug("liga = $liga")
        sendMail {
          to 'dzamora@inr.gob.mx' // TODO: mandar el correo al que solicito       personasInstance.correo
          subject "Solicitud ${solicitudInstance.toString()} requiere un visto bueno"
          html "Hola ${personasInstance}<br/><br/>La solicitud folio " +
            "${solicitudInstance} requiere que le de su visto bueno, " +
            "utilice la liga siguiente para revisarla y autorizarla. <br/><br/>" +
            "<a href='${liga}'>${solicitudInstance}</a>"
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'solicitud.label', default: 'Solicitud'), solicitudInstance.toString()])
        redirect(action: "list")
    }

    def update(Long id, Long version) {
        def solicitudDetalleInstance = SolicitudDetalle.get(id)
        if (!solicitudDetalleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudDetalle.label', default: 'SolicitudDetalle'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (solicitudDetalleInstance.version > version) {
                solicitudDetalleInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'solicitudDetalle.label', default: 'SolicitudDetalle')] as Object[],
                          "Another user has updated this SolicitudDetalle while you were editing")
                render(view: "edit", model: [solicitudDetalleInstance: solicitudDetalleInstance])
                return
            }
        }

        solicitudDetalleInstance.properties = params

        if (!solicitudDetalleInstance.save(flush: true)) {
            render(view: "edit", model: [solicitudDetalleInstance: solicitudDetalleInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'solicitudDetalle.label', default: 'SolicitudDetalle'), solicitudDetalleInstance.toString()])
        redirect(action: "edit", id: solicitudDetalleInstance.id)
    }

    def download(long id) {
        SolicitudArchivoadjunto solicitudArchivoadjuntoInstance =
          SolicitudArchivoadjunto.get(id)
        if ( SolicitudArchivoadjunto == null) {
            flash.message = "Documento no encontrado."
            redirect (controller: "solicitud", action:'list')
        } else {
            response.setContentType("APPLICATION/OCTET-STREAM")
            response.setHeader("Content-Disposition",
              "Attachment;Filename=\"${solicitudArchivoadjuntoInstance.nombre}\"")

            def outputStream = response.getOutputStream()
            outputStream << solicitudArchivoadjuntoInstance.datos
            outputStream.flush()
            outputStream.close()
        }
    }

  def subcategoryChanged(long subcategoryId) {
    log.debug("subcategoryId = $subcategoryId")
    Cat_servSub subcategory = Cat_servSub.get(subcategoryId)
    def servicios = []
    if ( subcategory != null ) {
      servicios = Cat_serv.findAllNotIncidenteByServSub(subcategory, [order:'id'])
    }
    render g.select(id: 'idServ', name:'idServ.id', required:'',
      from:servicios, optionKey:'id', noSelection:['':'Seleccione una...']
    )
  }

  def servicesChanged(long servicesId) {
    log.debug("servicesId = $servicesId")
    render g.select(id: 'prioridad', name:'prioridad',
      from:[0, 1, 2, 3], valueMessagePrefix:"intensidad.valor",
      value:"${Cat_serv.get(servicesId).impacto}")
  }

}
