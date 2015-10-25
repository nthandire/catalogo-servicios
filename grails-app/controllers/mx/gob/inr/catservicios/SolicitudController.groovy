package mx.gob.inr.catservicios

import grails.plugins.springsecurity.Secured
import org.springframework.dao.DataIntegrityViolationException
import groovy.time.TimeCategory

@Secured(['ROLE_SAST_USUARIO'])
class SolicitudController {
    def springSecurityService
    def grailsApplication
    static nombreMenu = "Solicitud"
    static ordenMenu = 80

    static allowedMethods = [save: "POST", update: "POST", x_delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        log.debug("params = $params")
        def userID = springSecurityService.principal.id
        log.debug("userID = $userID")
        def solicitudes = Solicitud.withCriteria {
            projections {count()}
            eq('idSolicitante',(Integer)userID)
            or {
                and {
                    ne('estado','T' as char)
                    ne('estado','C' as char) }
                isNull('estado') }
        }[0]
        log.debug("numero de solicitudes = $solicitudes")
        if (!solicitudes)
          redirect(action: "create")
        def criterio = Solicitud.createCriteria()
        def solicitudesList = criterio.list(max:params.max, offset:params.offset,
                                            sort:"fechaSolicitud", order:"desc") {
            eq('idSolicitante',(Integer)userID)
            or {
                and {
                    ne('estado','T' as char)
                    ne('estado','C' as char)
                    }
                isNull('estado')
                }
        }
        [solicitudInstanceList: solicitudesList,
            solicitudInstanceTotal: solicitudes]
    }

    def create() {
      log.debug("params = $params")

      [solicitudInstance: new Solicitud(params),
        solicitudDetalleInstance: new SolicitudDetalle(params),
        autorizadores: listaDeAutorizadores(),
        categorias: categorias(),
        equipos: equipos()]
    }

    def categorias() {
      def query =
          "  from Cat_servCat c               \n" +
          " where exists                      \n" +
          "      ( from Cat_servSub s,        \n" +
          "             Cat_serv t            \n" +
          "       where s.id = t.servSub      \n" +
          "         and t.incidente = 'f'     \n" +
          "      )                            \n"
      log.debug("query = \n${query}")

      def categorias = Cat_servCat.executeQuery(query)
      log.debug("numero de categorias = ${categorias.size()}")
      categorias
    }

    def equipos() {
      ResguardoEntregaDetalle.executeQuery(
        "   from ResguardoEntregaDetalle d " +
        "  where exists( from ResguardoEntrega r " +
        "               where r.id = d.idResguardo " +
        "                 and r.codigo like ?)",
        "515%")
    }

    def save() {
        log.debug("params = $params")
        def paramsFiltrado = params.findAll {it.key != 'idSolicitud' &&
                          (it.key != 'idResguardoentregadetalle' || it.value )}
        log.debug("paramsFiltrado = $paramsFiltrado")
        def solicitudDetalleInstance = new SolicitudDetalle(paramsFiltrado)
        def paramsFiltradoSolicitud = params.findAll {it.key != 'estado'}
        log.debug("paramsFiltradoSolicitud = $paramsFiltradoSolicitud")
        def solicitudInstance = new Solicitud(paramsFiltradoSolicitud)
        // Checar si el detalle esta bien
        if (!solicitudDetalleInstance?.idServcat?.id) {
            flash.error = "Debe capturar la categoría de su solicitud"
            render(view: "create", model: [solicitudInstance: solicitudInstance,
                   solicitudDetalleInstance: solicitudDetalleInstance,
                   autorizadores: listaDeAutorizadores(),
                   categorias: categorias(), equipos: equipos()])
            return
        }
        if (!solicitudDetalleInstance?.descripcion) {
            flash.error = "Debe capturar la descripción del servicio solicitado"
            render(view: "create", model: [solicitudInstance: solicitudInstance,
                   solicitudDetalleInstance: solicitudDetalleInstance,
                   autorizadores:listaDeAutorizadores(),
                   categorias: categorias(), equipos: equipos()])
            return
        }
        // Tratar de salvar el maestro
        solicitudInstance.idSolicitante = springSecurityService.principal.id
        solicitudInstance.ipTerminal = request.getRemoteAddr()

        log.debug("solicitudInstance.estado = ${solicitudInstance.estado}")
        if (!solicitudInstance.save(flush: true)) {
            render(view: "create", model: [solicitudInstance: solicitudInstance,
                                   solicitudDetalleInstance: solicitudDetalleInstance,
                                   autorizadores:listaDeAutorizadores(),
                                   categorias: categorias(), equipos: equipos()])
            return
        }
        // Tratar de salvar el detalle
        def solicitud = solicitudInstance
        log.debug("solicitud.id = ${solicitud.id}")
        solicitudDetalleInstance.idSolicitud = solicitud
        if (!solicitudDetalleInstance.save(flush: true)) {
            render(view: "create", model: [solicitudInstance: solicitudInstance,
                                   solicitudDetalleInstance: solicitudDetalleInstance,
                                   autorizadores:listaDeAutorizadores(),
                                   categorias: categorias(), equipos: equipos()])
            return
        }
        // Ir a edit maestro
        flash.message = message(code: 'default.created.message', args: [message(code: 'solicitud.label', default: 'Solicitud'), solicitudInstance.toString()])
        redirect(action: "edit", id: solicitudInstance.id)
    }

    def listaDeAutorizadores() {
      def userID = springSecurityService.principal.id
      def area = UsuarioAutorizado.get(userID)?.area
      if (!area) {
        area = Usuario.get(userID).idUnidadMedica
      }
      def miembros = UsuarioAutorizado.findAllAutorizaByAreaAndEstado(area,
                                                'A' as char).collect{it.id}

      def autorizadores = []
      Usuario.withNewSession { sessionU ->
        autorizadores = Usuario.findAllByIdInList(miembros)
      }

      log.debug("numero de autorizadores = ${autorizadores.size()}")
      return autorizadores
    }

    def edit(Long id) {
      def solicitudInstance = Solicitud.get(id)
      if (!solicitudInstance) {
          flash.message = message(code: 'default.not.found.message',
                                  args: [message(code: 'solicitud.label',
                                                 default: 'Solicitud'), id])
          redirect(action: "list")
          return
      }

      [solicitudInstance: solicitudInstance,
        autorizadores:listaDeAutorizadores(),
        categorias: categorias(),
        equipos: equipos()]
    }

    def firmarUpdate(Long id, Long version) {
        log.debug("En firmarUpdate: params = $params")
        def solicitudInstance = Solicitud.get(id)
        if (!solicitudInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitud.label', default: 'Solicitud'), id])
            redirect(action: "list")
            return
        }

        if (solicitudInstance?.detalles?.size() && (solicitudInstance.detalles.
                    findAll {it.estado == 'A' as char}.size())) {
          log.debug("solicitudInstance?.detalles = ${solicitudInstance?.detalles}")
          log.debug("solicitudInstance?.detalles activos = ${solicitudInstance.detalles.findAll {it.estado == 'A' as char}}")
        } else {
            flash.error = "Debe tener al menos un detalle activo para poderla firmar"
            render(view: "edit", model: [solicitudInstance: solicitudInstance])
            return
        }

        if (version != null) {
            if (solicitudInstance.version > version) {
                solicitudInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'solicitud.label', default: 'Solicitud')] as Object[],
                          "Alguien más ha modificado esta Solicitud mientras usted la estaba firmando")
                render(view: "edit", model: [solicitudInstance: solicitudInstance])
                return
            }
        }

        def userID = springSecurityService.principal.id
        log.debug("userID = $userID")

        def firmaTeclada = params['passwordfirma']
        def firma = Firmadigital.findById(userID)?.passwordfirma
        if (firmaTeclada != firma) {
            flash.error = "Error en contaseña"
            render(view: "edit", model: [solicitudInstance: solicitudInstance])
            return
        }

        solicitudInstance.fechaSolicitud = new Date()
        solicitudInstance.estado = 'F' as char

        if (!solicitudInstance.save(flush: true)) {
            render(view: "edit", model: [solicitudInstance: solicitudInstance])
            return
        }

        def personasInstance = Usuario.get(solicitudInstance.idAutoriza)
        def liga = createLink(controller:"solicitudAutoriza", action: "show",
                   id: solicitudInstance.id, absolute: "true") //base: "http://192.168.16.59:8080/catalogo-servicios")//
        log.debug("liga = $liga")
        def correo = grailsApplication.config.correo.general
        log.debug("correo = $correo")
        def cuerpoCorreo = "Hola ${personasInstance}<br/><br/>La solicitud folio " +
            "${solicitudInstance} requiere que la autorices, " +
            "utilice la liga siguiente para revisarla y autorizarla. <br/><br/>" +
            "<a href='${liga}'>Solicitud: ${solicitudInstance}</a>"
        log.debug("cuerpoCorreo = $cuerpoCorreo")
        sendMail {
          to correo // TODO: mandar el correo al que solicito       personasInstance.correo
          subject "La solicitud ${solicitudInstance} requiere autorización"
          html cuerpoCorreo
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'solicitud.label', default: 'Solicitud'), solicitudInstance.toString()])
        redirect(action: "edit", id: solicitudInstance.id)
    }

    def update(Long id, Long version) {
        def solicitudInstance = Solicitud.get(id)
        if (!solicitudInstance) {
            flash.message = message(code: 'default.not.found.message',
              args: [message(code: 'solicitud.label', default: 'Solicitud'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (solicitudInstance.version > version) {
                solicitudInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'solicitud.label', default: 'Solicitud')] as Object[],
                          "Alguien más ha modificado esta Solicitud mientras usted la estaba editando")
                render(view: "edit", model: [solicitudInstance: solicitudInstance])
                return
            }
        }

        log.debug("params = $params")
        def paramsFiltrado = params.findAll {it.key != 'estado'}
        log.debug("paramsFiltrado = $paramsFiltrado")
        solicitudInstance.properties = paramsFiltrado

        if (!solicitudInstance.save(flush: true)) {
            render(view: "edit", model: [solicitudInstance: solicitudInstance])
            return
        }

        flash.message = message(code: 'default.updated.message',
          args: [message(code: 'solicitud.label', default: 'Solicitud'), solicitudInstance.toString()])
        redirect(action: "edit", id: solicitudInstance.id)
    }

    def updateDetalle(Long id, Long version, Long idDetalle) {
      log.debug("id = $id")
      log.debug("version = $version")
      log.debug("idDetalle = $idDetalle")
      log.debug("params = $params")

        def solicitudInstance = Solicitud.get(id)
        if (!solicitudInstance) {
            flash.message = message(code: 'default.not.found.message',
              args: [message(code: 'solicitud.label', default: 'Solicitud'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (solicitudInstance.version > version) {
                solicitudInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'solicitud.label', default: 'Solicitud')] as Object[],
                          "Alguien más ha modificado esta Solicitud mientras usted la estaba editando")
                render(view: "edit", model: [solicitudInstance: solicitudInstance])
                return
            }
        }
        // Checar si es nuevo el detalle
        def solicitudDetalleInstance = new SolicitudDetalle()
        if (idDetalle)
          solicitudDetalleInstance = SolicitudDetalle.get(idDetalle)
        else
          solicitudDetalleInstance.estado = 'A' as char
        solicitudDetalleInstance.properties = params
        log.debug("solicitudDetalleInstance.properties = ${solicitudDetalleInstance.properties}")
        // Checar el nuevo el detalle
        if (!solicitudDetalleInstance?.idServcat?.id) {
            flash.error = "Debe capturar la categoría de su solicitud"
            render(view: "edit", model: [solicitudInstance: solicitudInstance,
                                         autorizadores:listaDeAutorizadores(),
                                         categorias: categorias(),
                                         equipos: equipos()])
            return
        }
        if (!solicitudDetalleInstance?.descripcion) {
            flash.error = "Debe capturar la descripción del servicio solicitado"
            render(view: "edit", model: [solicitudInstance: solicitudInstance,
                                         autorizadores:listaDeAutorizadores(),
                                         categorias: categorias(),
                                         equipos: equipos()])
            return
        }
        // Tratar de salvar el detalle
        def solicitud = solicitudInstance
        solicitudDetalleInstance.idSolicitud = solicitud
        if (!solicitudDetalleInstance.save(flush: true)) {
            render(view: "edit", model: [solicitudInstance: solicitudInstance,
                                         autorizadores:listaDeAutorizadores(),
                                         categorias: categorias(),
                                         equipos: equipos()])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'solicitud.label', default: 'Solicitud'), solicitudInstance.toString()])
        redirect(action: "edit", id: id)
    }

}
