package mx.gob.inr.catservicios

import groovy.time.TimeCategory

import org.springframework.dao.DataIntegrityViolationException

import grails.plugins.springsecurity.Secured
import grails.converters.JSON


@Secured(['ROLE_SAST_USUARIO'])
class SolicitudController {
    def springSecurityService
    def grailsApplication
    def firmadoService
    def serviciosService

    static nombreMenu = "Requerimientos"
    static ordenMenu = 80

    static allowedMethods = [save: "POST", update: "POST", x_delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 100, 100)
        log.debug("params = $params")
        def userID = springSecurityService.principal.id
        log.debug("userID = $userID")
        def solicitudes = Solicitud.findAllByEstadoNotEqualAndEstadoNotEqualAndIdSolicitante(
          'T' as char, 'C' as char, (Integer)userID)
        log.debug("lista solicitudes = ${solicitudes}")

        log.debug("params['sort'] = ${params['sort']}")
        switch (params['sort']) {
          case null:
          case "numeroSolicitud":
            log.debug("numeroSolicitud")
            solicitudes.sort{it.toString()}
          break
          case "lastUpdated":
            log.debug("fechaSolicitud")
            solicitudes.sort{it.fechaSolicitud ?: it.lastUpdated}
          break
          case "justificacion":
            log.debug("justificacion")
            solicitudes.sort{it?.justificacion}
          break
          case "estado":
            log.debug("estado")
            solicitudes.sort{it?.estado}
          break
        }
        log.debug("solicitudes = ${solicitudes}")

        // def incidenteInstanceList = solicitudes
        // def incidenteInstanceList = isCoordinador() || isGestor() ?
        //     solicitudes
        //   :
        //     solicitudes.findAll {it."idNivel${it.nivel}" == userID}

        def paramMax = (params['max']?:'0').toInteger()
        def paramOffset = (params['offset']?:'0').toInteger()

        log.debug("paramOffset = ${paramOffset}")
        log.debug("paramOffset+paramMax-1 = ${paramOffset+paramMax-1}")
        log.debug("solicitudes.size()-1 = ${solicitudes.size()-1}")
        log.debug("Math.max(solicitudes.size()-1, 0) = ${Math.max(solicitudes.size()-1, 0)}")
        log.debug("Math.min(paramOffset+paramMax-1, Math.max(solicitudes.size()-1, 0)) = ${Math.min(paramOffset+paramMax-1, Math.max(solicitudes.size()-1, 0))}")

        if (params['order'] == 'asc') {
          solicitudes = solicitudes.reverse()
        }
        def total = solicitudes.size()
        def solicitudesPaginación = total ?
          solicitudes[paramOffset..
            Math.min(paramOffset+paramMax-1, solicitudes.size()-1)] :
          []
        log.debug("solicitudesPaginación = ${solicitudesPaginación}")

        [solicitudInstanceList: solicitudesPaginación,
            solicitudInstanceTotal: total]
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
      firmadoService.categoriasSolicitudes()
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
        def userID = springSecurityService.principal.id
        solicitudInstance.idSolicitante = userID
        solicitudInstance.ipTerminal = request.getRemoteAddr()
        solicitudInstance.extension = serviciosService.extension([reporta:userID])

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
        flash.message = message(code: 'default.created.message',
          args: [message(code: 'solicitud.label', default: 'Solicitud'),
                 solicitudInstance.toString()]).minus(/""/)
        redirect(action: "edit", id: solicitudInstance.id)
    }

    def listaDeAutorizadores() {
      def userID = springSecurityService.principal.id
      log.debug("areauserID = $userID")
      def area = Usuario.get(userID)?.idUnidadMedica
      log.debug("area = $area")
      def usuariosAutorizadores = UsuarioAutorizado.findAllAutorizaByEstado('A' as char).
        collect{it.id}

      def autorizadores = Usuario.findAllByIdInListAndIdUnidadMedica(usuariosAutorizadores,
        area, [sort:'nombre'])

      log.debug("numero de autorizadores = ${autorizadores.size()}")
      autorizadores
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
                render(view: "edit", model: [solicitudInstance: solicitudInstance,
                                             autorizadores:listaDeAutorizadores(),
                                             categorias: categorias(),
                                             equipos: equipos()])
                return
            }
        }

        def userID = springSecurityService.principal.id
        log.debug("userID = $userID")

        def firmaTeclada = params['passwordfirma']
        // log.debug("firmaTeclada = $firmaTeclada")
        def firma = Firmadigital.findById(userID)?.passwordfirma?.reverse()
        // log.debug("firma = $firma")
        if (firmaTeclada != firma) {
            flash.error = "Error en contaseña"
            render(view: "edit", model: [solicitudInstance: solicitudInstance,
                                         autorizadores:listaDeAutorizadores(),
                                         categorias: categorias(),
                                         equipos: equipos()])
            return
        }

        solicitudInstance.fechaSolicitud = new Date()
        solicitudInstance.estado = 'F' as char

        if (!solicitudInstance.save(flush: true)) {
            render(view: "edit", model: [solicitudInstance: solicitudInstance,
                                         autorizadores:listaDeAutorizadores(),
                                         categorias: categorias(),
                                         equipos: equipos()])
            return
        }

        def personasInstance = Usuario.get(solicitudInstance.idAutoriza)
        def liga = createLink(controller:"solicitudAutoriza", action: "show",
                   id: solicitudInstance.id, absolute: "true")
        log.debug("liga = $liga")
        def correo = firmadoService.correo(personasInstance.idEmpleado)
        def cuerpoCorreo = """Hola ${personasInstance}<br/><br/>

                           Usted tiene una Solicitud de Servicio de Tecnologías
                           de la Información por autorizar.<br/><br/>

                           Solicitante: ${Usuario.get(solicitudInstance.idSolicitante)}.
                           <br/><br/>

                           Utilice la liga siguiente para revisarla y autorizarla.
                           <br/><br/>

                           <a href='${liga}'>Solicitud: ${solicitudInstance}</a>
                           """
        def asunto = "La solicitud '${solicitudInstance}' requiere autorización"
        firmadoService.sendMailHTML(correo, asunto, cuerpoCorreo)

        flash.message = message(code: 'default.updated.message',
                                args: [message(code: 'solicitud.label',
                                               default: 'Solicitud'),
                                solicitudInstance.toString()])
        redirect(action: "list")
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
                render(view: "edit", model: [solicitudInstance: solicitudInstance,
                                             autorizadores:listaDeAutorizadores(),
                                             categorias: categorias(),
                                             equipos: equipos()])
                return
            }
        }

        log.debug("params = $params")
        def paramsFiltrado = params.findAll {it.key != 'estado'}
        log.debug("paramsFiltrado = $paramsFiltrado")
        solicitudInstance.properties = paramsFiltrado

        if (solicitudInstance.justificacion) {
          solicitudInstance.justificacion = solicitudInstance.justificacion.trim()
        }
        if (!(solicitudInstance.justificacion)) {
          flash.error = "Debe capturar la justificación"
          render(view: "edit", model: [solicitudInstance: solicitudInstance,
                                       autorizadores:listaDeAutorizadores(),
                                       categorias: categorias(),
                                       equipos: equipos()])
          return
        }

        if (!solicitudInstance.save(flush: true)) {
            render(view: "edit", model: [solicitudInstance: solicitudInstance,
                                         autorizadores:listaDeAutorizadores(),
                                         categorias: categorias(),
                                         equipos: equipos()])
            return
        }

        flash.message = message(code: 'default.updated.message',
          args: [message(code: 'solicitud.label', default: 'Solicitud'),
                 solicitudInstance.toString()]).minus(/""/)
        redirect(action: "edit", id: solicitudInstance.id)
    }

    def deleteArch(Long id, Long version) {
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
                render(view: "edit", model: [solicitudInstance: solicitudInstance,
                                             autorizadores:listaDeAutorizadores(),
                                             categorias: categorias(),
                                             equipos: equipos()])
                return
            }
        }

        log.debug("params = $params")
        log.debug("params['arch.id'] = $params['arch.id']")

        def archivo = SolicitudArchivoadjunto.get(params["arch.id"])

        if (!archivo.delete(flush: true)) {
            render(view: "edit", model: [solicitudInstance: solicitudInstance,
                                         autorizadores:listaDeAutorizadores(),
                                         categorias: categorias(),
                                         equipos: equipos()])
            return
        }

        flash.message = message(code: 'default.updated.message',
          args: [message(code: 'solicitud.label', default: 'Solicitud'),
                 solicitudInstance.toString()]).minus(/""/)
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

  def listarEquipo() {
    def codigo = message(code: "codigo.de.equipos.en.resguado").toLong()
    log.debug("en listarEquipo, codigo = $codigo")
    params << ['codigo': codigo]
    render serviciosService.listarEquipo(params) as JSON
  }

  def listarUsuario() {
    log.debug("en listarEquipo")
    render serviciosService.listarUsuario(params) as JSON
  }

}
