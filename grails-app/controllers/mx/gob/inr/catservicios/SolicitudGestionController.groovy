package mx.gob.inr.catservicios

import grails.plugins.springsecurity.Secured
import org.springframework.dao.DataIntegrityViolationException
import groovy.time.TimeCategory

@Secured(['ROLE_SAST_GESTOR'])
class SolicitudGestionController {
    def springSecurityService
    def firmadoService
    def grailsApplication
    static nombreMenu = "Mesa de servicio"
    static ordenMenu = 88

    static allowedMethods = [save: "POST", update: "POST", x_delete: "POST"]

    def index() {
      firmadoService.iniciarSemaforos(session)
      redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 100, 100)
        log.debug("params = $params")
        def query =
            "  from Solicitud              " +
            " where (estado = 'A'          " +
            "        and idVb is null)     " +
            "    or  estado = 'V'          "
        def solicitudes = Solicitud.executeQuery(query)
        log.debug("lista solicitudes = ${solicitudes}")

        log.debug("params['sort'] = ${params['sort']}")
        switch (params['sort']) {
          case null:
            params['order'] = 'desc'
          case "numeroSolicitud":
            log.debug("numeroSolicitud")
            solicitudes.sort{it.toString()}
          break
          case "nombre":
            log.debug("nombre")
            solicitudes.sort{Usuario.get(it.idSolicitante).toString()}
          break
          case "lastUpdated":
            log.debug("fechaSolicitud")
            solicitudes.sort{it.fechaVb?:it.fechaAutoriza}
          break
          case "justificacion":
            log.debug("justificacion")
            solicitudes.sort{it?.justificacion?.toUpperCase()}
          break
          case "estado":
            log.debug("estado")
            solicitudes.sort{it?.estado}
          break
        }
        log.debug("solicitudes = ${solicitudes}")

        def paramMax = (params['max']?:'0').toInteger()
        def paramOffset = (params['offset']?:'0').toInteger()

        log.debug("paramOffset = ${paramOffset}")
        log.debug("paramOffset+paramMax-1 = ${paramOffset+paramMax-1}")
        log.debug("solicitudes.size()-1 = ${solicitudes.size()-1}")
        log.debug("Math.max(solicitudes.size()-1, 0) = ${Math.max(solicitudes.size()-1, 0)}")
        log.debug("Math.min(paramOffset+paramMax-1, Math.max(solicitudes.size()-1, 0)) = ${Math.min(paramOffset+paramMax-1, Math.max(solicitudes.size()-1, 0))}")

        if (params['order'] == 'desc') {
          solicitudes = solicitudes.reverse()
        }
        def total = solicitudes.size()
        def solicitudesPaginación = total ?
          solicitudes[paramOffset..
            Math.min(paramOffset+paramMax-1, solicitudes.size()-1)] :
          []
        log.debug("solicitudesPaginación = ${solicitudesPaginación}")

        [autorizadosInstanceList: solicitudesPaginación,
            autorizadosInstanceTotal: total]
    }

    def listTodas(Integer max) {
        params.max = Math.min(max ?: 100, 100)
        log.debug("params = $params")
        def query =
            "  from Solicitud              " +
            " where estado <> 'F'          " +
            "   and estado is not null     "
        def solicitudes = Solicitud.executeQuery (query)
        log.debug("lista solicitudes = ${solicitudes}")

        log.debug("params['sort'] = ${params['sort']}")
        switch (params['sort']) {
          case null:
            params['order'] = 'desc'
          case "numeroSolicitud":
            log.debug("numeroSolicitud")
            solicitudes.sort{it.paraOrdenar()}
          break
          case "nombre":
            log.debug("nombre")
            solicitudes.sort{Usuario.get(it.idSolicitante).toString()}
          break
          case "lastUpdated":
            log.debug("fechaSolicitud")
            solicitudes.sort{it.fechaRevisa?:it.fechaVb?:it.fechaAutoriza}
          break
          case "justificacion":
            log.debug("justificacion")
            solicitudes.sort{it?.justificacion?.toUpperCase()}
          break
          case "estado":
            log.debug("estado")
            solicitudes.sort{it?.estado}
          break
        }
        log.debug("solicitudes = ${solicitudes}")

        def paramMax = (params['max']?:'0').toInteger()
        def paramOffset = (params['offset']?:'0').toInteger()

        log.debug("paramOffset = ${paramOffset}")
        log.debug("paramOffset+paramMax-1 = ${paramOffset+paramMax-1}")
        log.debug("solicitudes.size()-1 = ${solicitudes.size()-1}")
        log.debug("Math.max(solicitudes.size()-1, 0) = ${Math.max(solicitudes.size()-1, 0)}")
        log.debug("Math.min(paramOffset+paramMax-1, Math.max(solicitudes.size()-1, 0)) = ${Math.min(paramOffset+paramMax-1, Math.max(solicitudes.size()-1, 0))}")

        if (params['order'] == 'desc') {
          solicitudes = solicitudes.reverse()
        }
        def total = solicitudes.size()
        def solicitudesPaginación = total ?
          solicitudes[paramOffset..
            Math.min(paramOffset+paramMax-1, solicitudes.size()-1)] :
          []
        log.debug("solicitudesPaginación = ${solicitudesPaginación}")

        [autorizadosInstanceList: solicitudesPaginación,
            autorizadosInstanceTotal: total]
    }

    def listAsignados(Integer max) {
        params.max = Math.min(max ?: 100, 100)
        log.debug("params = $params")

        def query =
          "from Solicitud s                                     " +
          " where s.estado = 'R'                                " +
          "   and not exists(select id from SolicitudDetalle d  " +
          "               where s.id = d.idSolicitud            " +
          "                 and idTecnico is null)              "
        def solicitudes = Solicitud.executeQuery (query)
        log.debug("lista solicitudes = ${solicitudes}")

        log.debug("params['sort'] = ${params['sort']}")
        switch (params['sort']) {
          case null:
            params['order'] = 'desc'
          case "numeroSolicitud":
            log.debug("numeroSolicitud")
            solicitudes.sort{it.paraOrdenar()}
          break
          case "nombre":
            log.debug("nombre")
            solicitudes.sort{Usuario.get(it.idSolicitante).toString()}
          break
          case "lastUpdated":
            log.debug("fechaRevisa")
            solicitudes.sort{it.fechaRevisa}
          break
          case "justificacion":
            log.debug("justificacion")
            solicitudes.sort{it?.justificacion?.toUpperCase()}
          break
        }
        log.debug("solicitudes = ${solicitudes}")

        def paramMax = (params['max']?:'0').toInteger()
        def paramOffset = (params['offset']?:'0').toInteger()

        log.debug("paramOffset = ${paramOffset}")
        log.debug("paramOffset+paramMax-1 = ${paramOffset+paramMax-1}")
        log.debug("solicitudes.size()-1 = ${solicitudes.size()-1}")
        log.debug("Math.max(solicitudes.size()-1, 0) = ${Math.max(solicitudes.size()-1, 0)}")
        log.debug("Math.min(paramOffset+paramMax-1, Math.max(solicitudes.size()-1, 0)) = ${Math.min(paramOffset+paramMax-1, Math.max(solicitudes.size()-1, 0))}")

        if (params['order'] == 'desc') {
          solicitudes = solicitudes.reverse()
        }
        def total = solicitudes.size()
        def solicitudesPaginación = total ?
          solicitudes[paramOffset..
            Math.min(paramOffset+paramMax-1, solicitudes.size()-1)] :
          []
        log.debug("solicitudesPaginación = ${solicitudesPaginación}")

        [asignadosInstanceList: solicitudesPaginación,
            asignadosInstanceTotal: total]
    }

    def listEncuestas(Integer max) {
        params.max = Math.min(max ?: 100, 100)
        log.debug("params = $params")

        def query =
          "from Solicitud s          " +
          " where s.estado = 'E'     "
        def solicitudes = Solicitud.executeQuery (query)
        log.debug("lista solicitudes = ${solicitudes}")

        log.debug("params['sort'] = ${params['sort']}")
        switch (params['sort']) {
          case null:
            params['order'] = 'desc'
          case "numeroSolicitud":
            log.debug("numeroSolicitud")
            solicitudes.sort{it.paraOrdenar()}
          break
          case "nombre":
            log.debug("nombre")
            solicitudes.sort{Usuario.get(it.idSolicitante).toString()}
          break
          case "lastUpdated":
            log.debug("fechaRevisa")
            solicitudes.sort{it.fechaRevisa}
          break
          case "justificacion":
            log.debug("justificacion")
            solicitudes.sort{it?.justificacion?.toUpperCase()}
          break
        }
        log.debug("solicitudes = ${solicitudes}")

        def paramMax = (params['max']?:'0').toInteger()
        def paramOffset = (params['offset']?:'0').toInteger()

        log.debug("paramOffset = ${paramOffset}")
        log.debug("paramOffset+paramMax-1 = ${paramOffset+paramMax-1}")
        log.debug("solicitudes.size()-1 = ${solicitudes.size()-1}")
        log.debug("Math.max(solicitudes.size()-1, 0) = ${Math.max(solicitudes.size()-1, 0)}")
        log.debug("Math.min(paramOffset+paramMax-1, Math.max(solicitudes.size()-1, 0)) = ${Math.min(paramOffset+paramMax-1, Math.max(solicitudes.size()-1, 0))}")

        if (params['order'] == 'desc') {
          solicitudes = solicitudes.reverse()
        }
        def total = solicitudes.size()
        def solicitudesPaginación = total ?
          solicitudes[paramOffset..
            Math.min(paramOffset+paramMax-1, solicitudes.size()-1)] :
          []
        log.debug("solicitudesPaginación = ${solicitudesPaginación}")

        [enEncuestasInstanceList: solicitudesPaginación,
            enEncuestasInstanceTotal: total]
    }

    def listTerminadas(Integer max) {
        params.max = Math.min(max ?: 100, 100)
        log.debug("params = $params")

        def query =
          "from Solicitud s               " +
          " where s.estado in ('T', 'C')  "
        def solicitudes = Solicitud.executeQuery (query)
        log.debug("lista solicitudes = ${solicitudes}")

        log.debug("params['sort'] = ${params['sort']}")
        switch (params['sort']) {
          case null:
            params['order'] = 'desc'
          case "numeroSolicitud":
            log.debug("numeroSolicitud")
            solicitudes.sort{it.paraOrdenar()}
          break
          case "nombre":
            log.debug("nombre")
            solicitudes.sort{Usuario.get(it.idSolicitante).toString()}
          break
          case "lastUpdated":
            log.debug("lastUpdated")
            solicitudes.sort{it.lastUpdated}
          break
          case "justificacion":
            log.debug("justificacion")
            solicitudes.sort{it?.justificacion?.toUpperCase()}
          break
        }
        log.debug("solicitudes = ${solicitudes}")

        def paramMax = (params['max']?:'0').toInteger()
        def paramOffset = (params['offset']?:'0').toInteger()

        log.debug("paramOffset = ${paramOffset}")
        log.debug("paramOffset+paramMax-1 = ${paramOffset+paramMax-1}")
        log.debug("solicitudes.size()-1 = ${solicitudes.size()-1}")
        log.debug("Math.max(solicitudes.size()-1, 0) = ${Math.max(solicitudes.size()-1, 0)}")
        log.debug("Math.min(paramOffset+paramMax-1, Math.max(solicitudes.size()-1, 0)) = ${Math.min(paramOffset+paramMax-1, Math.max(solicitudes.size()-1, 0))}")

        if (params['order'] == 'desc') {
          solicitudes = solicitudes.reverse()
        }
        def total = solicitudes.size()
        def solicitudesPaginación = total ?
          solicitudes[paramOffset..
            Math.min(paramOffset+paramMax-1, solicitudes.size()-1)] :
          []
        log.debug("solicitudesPaginación = ${solicitudesPaginación}")

        [terminadasInstanceList: solicitudesPaginación,
            terminadasInstanceTotal: total]
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
        solicitudArchivoadjuntoInstance.tamanio =
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
        redirect (action:'edit', id: params.detalle)
      }
    }

    def show(Long id) {
        log.debug("id = ${id}")
        def solicitudInstance = Solicitud.get(id)
        if (!solicitudInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitud.label', default: 'Solicitud'), id])
            redirect(action: "list")
            return
        }

        def area = firmadoService.areaDetalladaNombre(solicitudInstance?.idSolicitante)
        def areaAutoriza = firmadoService.areaDetalladaNombre(solicitudInstance?.idAutoriza)
        def areaVb = firmadoService.areaDetalladaNombre(solicitudInstance?.idVb)
        [solicitudInstance: solicitudInstance,
          autorizadores:listaDeVobos(), area: area, areaAutoriza: areaAutoriza,
          areaVb: areaVb]
    }

    def showNoFirma(Long id) { // TODO: Checar si esto se utiliza aún
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

        [solicitudDetalleInstance: solicitudDetalleInstance]
    }

    def listaDeVobos() {
      def miembros = UsuarioAutorizado.findAllVoboByEstado('A' as char).collect{it.id}

      def autorizadores = []
      Usuario.withNewSession { sessionU ->
        autorizadores = Usuario.findAllByIdInList(miembros)
      }
      autorizadores.sort{it.toString()}

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

        def detallesPendientes = SolicitudDetalle.
          countByIdSolicitudAndEstadoAndIdServIsNull(solicitudInstance,
                                                     'A' as char)
        log.debug("detallesPendientes = $detallesPendientes")
        if (detallesPendientes) {
            flash.error = "Debe capturar antes todos los servicios de tercer nivel."
            render(view: "show", model: [solicitudInstance: solicitudInstance])
            return
        }

        def userID = springSecurityService.principal.id
        def firmaTeclada = params['passwordfirma']
        def firma = Firmadigital.findById(userID)?.passwordfirma?.reverse()

        if (firmaTeclada != firma) {
          flash.error = "Error en contaseña"
          render(view: "show", model: [solicitudInstance: solicitudInstance])
          return
        }

        solicitudInstance.estado = 'R' as char
        solicitudInstance.fechaRevisa = new Date()
        solicitudInstance.idRevisa = userID

        if (!solicitudInstance.save(flush: true)) {
            render(view: "show", model: [solicitudInstance: solicitudInstance])
            return
        }

        def fecha = solicitudInstance.fechaSolicitud

        solicitudInstance.detalles.findAll{it.estado = 'A' as char}.each {

          firmadoService.aprobadores(it.idServ.servResp2).each { coord ->
            def asunto = "Solicitud ${solicitudInstance} requiere procesarse"
            def liga = createLink(controller: "SolicitudCoordinador",
                                  action: "edit", id: it.id,
                                  absolute: "true")
            log.debug("liga = $liga")
            def cuerpoCorreo = """Hola ${coord}<br/><br/>
Tiene una nueva solicitud para su aprobación:
<br/><br/>
Folio: ${solicitudInstance}<br/>
Fecha de Acuse: ${solicitudInstance.fechaRevisa.format('dd/MM/yy hh:mm')} hrs.<br/>
Tiempo de Atención: ${it.idServ.tiempo2} ${it.idServ.unidades2.descripcion}<br/>
Prioridad: ${message(code:"intensidad.valor.${it.prioridad}")}
<br/><br/>
<a href='${liga}'>${solicitudInstance}</a>
"""
            def correo = firmadoService.correo(coord.idEmpleado)
            firmadoService.sendMailHTML(correo, asunto, cuerpoCorreo)
          }

          def asunto = "Acuse de la Solicitud realizada el ${solicitudInstance.fechaSolicitud}"
          def correo = firmadoService.correo(Usuario.get(solicitudInstance.idSolicitante).idEmpleado)
          def msg = """Acuse de la Solicitud de Servicio de Tecnologías de la Información realizada el ${fecha.format('dd')} de ${fecha.format('MMMMM')} a las ${fecha.format('hh:mm')} :

Folio: ${solicitudInstance}
Fecha de Acuse: ${solicitudInstance.fechaRevisa.format('dd/MM/yy hh:mm')} hrs.
Tiempo de Atención: ${it.idServ.tiempo2} ${it.idServ.unidades2.descripcion}
"""
          log.debug("msg = $msg")
          firmadoService.sendMail(correo, asunto, msg)
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'solicitud.label', default: 'Solicitud'), solicitudInstance.toString()])
        redirect(action: "list")
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
        def firma = Firmadigital.findById(userID)?.passwordfirma?.reverse()

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
        def persona = Usuario.get(solicitudInstance.idVb)
        def liga = createLink(controller:"solicitudVB", action: "show",
                              id: solicitudInstance.id, absolute: "true")
        log.debug("liga = $liga")
        def correo = firmadoService.correo(persona.idEmpleado)
        def asunto = "Solicitud ${solicitudInstance.toString()} requiere un visto bueno"
        def cuerpoCorreo = "Hola ${persona}<br/><br/>La solicitud folio " +
          "${solicitudInstance} requiere que le de su visto bueno, " +
          "utilice la liga siguiente para revisarla y autorizarla. <br/><br/>" +
          "<a href='${liga}'>${solicitudInstance}</a>"
        firmadoService.sendMailHTML(correo, asunto, cuerpoCorreo)

        flash.message = message(code: 'default.updated.message', args: [message(code: 'solicitud.label', default: 'Solicitud'), solicitudInstance.toString()])
        redirect(action: "list")
    }

    def update(Long id, Long version) {
        log.debug("params = $params")
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
        log.debug("soli det cat = ${(solicitudDetalleInstance.idServ)}")
        log.debug("soli det idServ = ${(solicitudDetalleInstance.idServcat)}")

        if (!solicitudDetalleInstance.save(flush: true)) {
            render(view: "edit", model: [solicitudDetalleInstance: solicitudDetalleInstance])
            return
        }

        flash.message = /"$solicitudDetalleInstance.idSolicitud:${solicitudDetalleInstance.toString()}" actualizado/
        redirect(action: "show", id: solicitudDetalleInstance.idSolicitud.id)
    }

  def categoryChanged(long categoryId) {
      Cat_servCat category = Cat_servCat.get(categoryId)
      def subCategories = []
      if ( category != null ) {
          subCategories = firmadoService.subcategoriasSolicitudes(category)
      }
      render g.select(id:'servSub', name:'servSub', required: true,
          from:subCategories, optionKey:'id', noSelection:['':'Seleccione una...'],
          class:"many-to-one", onchange:"subcategoryChanged(this.value)"
      )
  }

  def categoryChanged2(long categoryId) {
    render g.select(id: 'idServ', name:'idServ.id', required:true,
      onchange:"servicesChanged(this.value)",
      from:[], optionKey:'id', noSelection:['':'Seleccione una...']
    )
  }

  def subcategoryChanged(long subcategoryId) {
    log.debug("subcategoryId = $subcategoryId")
    Cat_servSub subcategory = Cat_servSub.get(subcategoryId)
    def servicios = []
    if ( subcategory != null ) {
      servicios = firmadoService.tercerNivelSolicitudes(subcategory)
      log.debug("servicios = $servicios")
    }
    render g.select(id: 'idServ', name:'idServ.id', required:true,
      onchange:"servicesChanged(this.value)",
      from:servicios, optionKey:'id', noSelection:['':'Seleccione una...']
    )
  }

  def servicesChanged(long servicesId) {
    log.debug("servicesId = $servicesId")
    render g.select(id: 'prioridad', name:'prioridad',
      from:[0, 1, 2, 3], valueMessagePrefix:"intensidad.valor",
      value:"${Cat_serv.get(servicesId).impacto}")
  }

/*****
*
*   Administración de servicios.
*
*****/

    def listMonitoreo(Integer max) {
        params.max = Math.min(max ?: 100, 100)
        if (!params.offset) {
          params["offset"] = 0
        } else {
          params.offset = params.offset.toLong()
        }
        log.debug("params = $params")

        def query =
            "  from Solicitud                " +
            " where estado in ('A','V','R')  "
        def solicitudes = Solicitud.executeQuery(query)
        log.debug("numero de solicitudes = ${solicitudes.size()}")
        def queryDetalle =
            "  from SolicitudDetalle                 " +
            " where idSolicitud in (:solicitudes)    "
        def detallesList = Solicitud.executeQuery(queryDetalle, [solicitudes: solicitudes])
        def detalles = detallesList.size()
        log.debug("numero de detalles = ${detalles}")
        def semaforo = session["semaforo"]
        def listaOrdenar = detallesList.collect{new Ordenado(caso: it,
                              orden: firmadoService.retraso(semaforo, it))}
        listaOrdenar.each{it.color = semaforo[it.orden]? semaforo[it.orden].color :"green"}

        def listaOrdenada = listaOrdenar
        switch (params['sort']) {
          case null:
          case "semaforo":
            log.debug("semaforo")
            listaOrdenar.sort{a,b -> a.orden == b.orden ?
              a.caso.idSolicitud.fechaSolicitud <=> b.caso.idSolicitud.fechaSolicitud :
              a.orden <=> b.orden}
          break
          case "numeroSolicitud":
            log.debug("numeroSolicitud")
            listaOrdenar.sort{it.caso.idSolicitud.paraOrdenar()}
          break
          case "inicio":
            log.debug("numeroSolicitud")
            listaOrdenar.sort{it.caso.idSolicitud.fechaRevisa?:
              it.caso.idSolicitud.fechaVb?:it.caso.idSolicitud.fechaAutoriza}
          break
          case "nombre":
            log.debug("nombre")
            listaOrdenar.sort{Usuario.get(it.caso.idSolicitud.idSolicitante).toString()}
          break
          case "area":
            log.debug("area")
            listaOrdenar.sort{firmadoService.areaDetalladaNombre(it.caso.idSolicitud.idSolicitante)}
          break
          case "extension":
            log.debug("extension")
            listaOrdenar.sort{it.caso.idSolicitud.extension}
          break
          case "categoria":
            log.debug("categoria")
            listaOrdenar.sort{it.caso.idServcat.categoria.toString().toUpperCase()}
          break
          case "subcategoria":
            log.debug("subcategoria")
            listaOrdenar.sort{it.caso.idServ?.servSub ? it.caso.idServ?.servSub?.toString()?.toUpperCase()?.trim() : ""}
          break
          case "servicio":
            log.debug("servicio")
            listaOrdenar.sort{it.caso.idServ?.toString()?.toUpperCase()}
          break
          case "estado":
            log.debug("estado")
            listaOrdenar.sort{a,b -> a.caso.idSolicitud.estado == b.caso.idSolicitud.estado ?
            a.caso.idSolicitud.fechaSolicitud <=> b.caso.idSolicitud.fechaSolicitud :
            a.caso.idSolicitud.estado <=> b.caso.idSolicitud.estado}
          break
        }

        if (params.order == "desc") {
          listaOrdenada = listaOrdenada.reverse()
        }
        log.debug("listaOrdenada[0] = ${listaOrdenada[0]}, color = ${listaOrdenada[0].color}")

        [detallesInstanceList: listaOrdenada[params.offset..Math.min(params.offset+params.max-1,listaOrdenada.size()-1)],
          detallesInstanceTotal: detalles, bOffset: params.offset]
    }

  def showDetalle(Long id) {
    log.debug("params = $params")
    def solicitudDetalleInstance = SolicitudDetalle.get(id)
    if (!solicitudDetalleInstance) {
        flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudDetalle.label', default: 'SolicitudDetalle'), id])
        redirect(action: "listMonitoreo")
        return
    }

    log.debug("bOffset = $params.offset")
    [solicitudDetalleInstance: solicitudDetalleInstance, bOffset: params.offset]
  }

  def correo(Long id) {
    log.debug("params = $params, id = $id")
    def asunto = "Aviso de servicio retrasado"
    def caso = SolicitudDetalle.get(id)
    def usuarios = []
    if (caso.idSolicitud.estado == 'R' as char) {
      if (caso.idTecnico) {
        usuarios << Usuario.get(caso.idTecnico)
      } else {
        usuarios = firmadoService.aprobadores(caso.idServ?.servResp2)
      }
    } else if (caso.idSolicitud.estado == 'V' as char) {
      usuarios = firmadoService.aprobadores(caso.idServ?.servResp2)
    } else /* estado == 'A' */ if (caso.idSolicitud.idVb) {
      usuarios << Usuario.get(caso.idSolicitud.idVb)
    } else {
      usuarios = firmadoService.aprobadores(caso.idServ?.servResp1)
    }

    usuarios.each {
      def correo = firmadoService.correo(it.idEmpleado)
      def msg = """Hola ${it},

      El requerimiento con No. de folio ${caso.idSolicitud} a rebasado el tiempo de atención acordado, por lo que se solicita se atienda a la brevedad"""
      firmadoService.sendMail(correo, asunto, msg)
    }

    redirect(action: "listMonitoreo", params: params)
  }

}

class Ordenado {
  SolicitudDetalle caso
  Integer orden
  String color

  String toString() {
    "$orden : $color : $caso"
  }
}

class IncidenteOrdenado {
  Incidente caso
  Integer orden
  String color

  String toString() {
    "$orden : $color : $caso"
  }
}
