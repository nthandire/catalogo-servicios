package mx.gob.inr.catservicios

import grails.plugins.springsecurity.Secured
import org.springframework.dao.DataIntegrityViolationException
import groovy.time.TimeCategory

@Secured(['ROLE_SAST_COORDINADOR_DE_GESTION','ROLE_SAST_TECNICO',
          'ROLE_SAST_COORDINADOR'])
class IncidenteController {
    def springSecurityService
    def firmadoService
    def messageSource
    static nombreMenu = "Incidentes"
    static ordenMenu = 70

    static allowedMethods = [save: "POST", update: "POST", x_delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def userID = springSecurityService.principal.id
        def area = area()
        def areaDesc = area.descripcion
        log.debug("area = ${area}, area.id = ${area?.id}")

        def incidentes = Incidente.findAllByEstado('A' as char)
        log.debug("list incidentes = ${incidentes}")

        def incidentesFiltrados =
          incidentes.findAll {it?.idServresp?.descripcion?.contains areaDesc}
        log.debug("incidentesFiltrados = ${incidentesFiltrados}")


        def incidenteInstanceList = isTecnico() ?
            incidentesFiltrados.findAll {it."idNivel${it.nivel}" == userID}
          :
            incidentesFiltrados
        log.debug("incidenteInstanceList = ${incidenteInstanceList}")
        [incidenteInstanceList: incidenteInstanceList,
          incidenteInstanceTotal: incidenteInstanceList.size()]
    }

    Boolean isTecnico() {
      firmadoService.isTecnico(session, springSecurityService.principal.id)
    }

    Boolean isGestor() {
      firmadoService.isGestor(session, springSecurityService.principal.id)
    }

    Boolean isCoordinador() {
      Boolean coordinador =
        firmadoService.isCoordinador(session, springSecurityService.principal.id)
      coordinador ?: isGestor()
    }

    Cat_servResp area() {
      def area = firmadoService.area(session, springSecurityService.principal.id)
      if (!area)
        flash.error ="Error de configuración de usuario, reporte a sistemas(SAST 1023)"
      area
    }

    def create() {
        [incidenteInstance: new Incidente(params)]
    }

    def save() {
        def incidenteInstance = new Incidente(params)
        incidenteInstance.fechaIncidente = new Date()

        // Asignarle el siguiente folio dentro del año
        // TODO: Pasarlo a un servicio
      def startDate = new Date().clearTime()
      startDate[Calendar.MONTH] = 0
      startDate[Calendar.DATE] = 1
      def endDate = startDate.clone()
      use(TimeCategory) {
        endDate = endDate + 1.years - 1.seconds
      }
      log.debug("startDate = $startDate, endDate = $endDate")

      def maxID = Incidente.withCriteria { // TODO: un test para ver si este algoritmo sique funcionando
        between("fechaIncidente", startDate, endDate)
        projections {
          max "numeroIncidente"
        }
      }[0] ?: 0
      log.debug("maxID = $maxID")
      incidenteInstance.numeroIncidente = ++maxID

      if (!incidenteInstance.idServ) {
          flash.error = "Debe capturar la categoría de tercer nivel."
          render(view: "create", model: [incidenteInstance: incidenteInstance])
          return
      }

      incidenteInstance.idServresp = incidenteInstance.idServ.servResp1
      incidenteInstance.estado = 'A' as char
      incidenteInstance.idCaptura = springSecurityService.principal.id
      incidenteInstance.ipTerminal = request.getRemoteAddr()
      incidenteInstance.idNivel1 = springSecurityService.principal.id
      incidenteInstance.fechaNivel1 = new Date()

      if (!incidenteInstance.save(flush: true)) {
          render(view: "create", model: [incidenteInstance: incidenteInstance])
          return
      }

      flash.message = message(code: 'default.created.message', args: [message(code: 'incidente.label', default: 'Incidente'), incidenteInstance.toString()])
      redirect(action: "edit", id: incidenteInstance.id)
    }

    def createArchivo() {
      log.debug("params = $params")
      def incidenteArchivoadjuntoInstance = new IncidenteArchivoadjunto()
      incidenteArchivoadjuntoInstance.idIncidente =
        (params.incidente['id']).toInteger()
      log.debug("idIncidente = ${incidenteArchivoadjuntoInstance.idIncidente}")
      [incidenteArchivoadjuntoInstance: incidenteArchivoadjuntoInstance]
    }

    def saveArchivo() {
      log.debug("params = $params")
      def file = request.getFile('file')
      if(file.empty) {
        flash.message = "Debe enviar algún archivo"
        render(view: "createArchivo")
        return
      } else {
        def incidenteArchivoadjuntoInstance = new IncidenteArchivoadjunto()
        incidenteArchivoadjuntoInstance.idIncidente =
          (params.idIncidente).toInteger()
        def nombre = file.originalFilename
        incidenteArchivoadjuntoInstance.nombre = nombre
        incidenteArchivoadjuntoInstance.datos = file.getBytes()
        incidenteArchivoadjuntoInstance.tamaño =
          incidenteArchivoadjuntoInstance.datos.size()
        incidenteArchivoadjuntoInstance.idUsuario = springSecurityService.principal.id
        incidenteArchivoadjuntoInstance.ipTerminal = request.getRemoteAddr()
        def dot = nombre.lastIndexOf('.');
        if (dot > 0)
          incidenteArchivoadjuntoInstance.tipo = nombre.substring(dot + 1).
            toUpperCase()
        else
          incidenteArchivoadjuntoInstance.tipo = ""
        if (!incidenteArchivoadjuntoInstance.save(flush: true)) {
            render(view: "create", model: [incidenteArchivoadjuntoInstance: incidenteArchivoadjuntoInstance])
            return
        }
        redirect (action:'edit', id: params.idIncidente)
      }
    }

    def download(long id) {
        IncidenteArchivoadjunto incidenteArchivoadjuntoInstance =
          IncidenteArchivoadjunto.get(id)
        if ( IncidenteArchivoadjunto == null) {
            flash.message = "Documento no encontrado."
            redirect (controller: "incidente", action:'list')
        } else {
            response.setContentType("APPLICATION/OCTET-STREAM")
            response.setHeader("Content-Disposition",
              "Attachment;Filename=\"${incidenteArchivoadjuntoInstance.nombre}\"")

            def outputStream = response.getOutputStream()
            outputStream << incidenteArchivoadjuntoInstance.datos
            outputStream.flush()
            outputStream.close()
        }
    }

    def edit(Long id) {
        def incidenteInstance = Incidente.get(id)
        if (!incidenteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'incidente.label', default: 'Incidente'), id])
            redirect(action: "list")
            return
        }

        def tecnicos =
          isCoordinador() ? listaDeTecnicos(incidenteInstance.idServresp) : []
        log.debug("tecnicos = ${tecnicos}")
        def idNivel = incidenteInstance."idNivel${incidenteInstance.nivel}"
        log.debug("idNivel = ${idNivel}")
        [incidenteInstance: incidenteInstance,
          tecnicos:tecnicos, idNivel: idNivel,
          solucionNivel: incidenteInstance."solucionNivel${incidenteInstance.nivel}"]
    }

  def listaDeTecnicos(Cat_servResp servresp) {
    def rolUsuarios = Rol.withNewSession { session ->
      Rol.findByAuthority('ROLE_SAST_TECNICO')
    }
    log.debug("rolUsuarios = $rolUsuarios")

    def usuariosRolesIds = UsuarioRol.withNewSession { session ->
      UsuarioRol.findAllByRol(rolUsuarios).collect {it.usuario.id}
    }
    log.debug("usuariosRolesIds = $usuariosRolesIds")

    def areaDesc = servresp.descripcion
    log.debug("areaDesc = $areaDesc")

    def tecnicosDelAreaIds = UsuarioAutorizado.
      findAllByIdInListAndEstado(usuariosRolesIds, 'A' as char).
        findAll{areaDesc.contains(it.area)}.collect {it.id}

    def areaMS = g.message(code: "mesa.de.ayuda")
    log.debug("areaMS = $areaMS")

    def userID = springSecurityService.principal.id
    Boolean agregarCoordinador = (isCoordinador() &&
                                  !tecnicosDelAreaIds.contains(userID) &&
                                  areaDesc.contains(areaMS))
    log.debug("agregarCoordinador = $agregarCoordinador")
    def delAreaIds = tecnicosDelAreaIds + (agregarCoordinador
                                           ? [userID] : [])
    log.debug("delAreaIds = $delAreaIds")

    def tecnicos = Usuario.withNewSession { session ->
      Usuario.findAllByIdInList(delAreaIds)
    }
    log.debug("numero de tecnicos = ${tecnicos.size()}")

    tecnicos
  }

    def update(Long id, Long version) {
        def incidenteInstance = Incidente.get(id)
        if (!incidenteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'incidente.label', default: 'Incidente'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (incidenteInstance.version > version) {
                incidenteInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'incidente.label', default: 'Incidente')] as Object[],
                          "Another user has updated this Incidente while you were editing")
                render(view: "edit", model: [incidenteInstance: incidenteInstance])
                return
            }
        }

      incidenteInstance.properties = params

      def nivel = incidenteInstance.nivel
      def userID = springSecurityService.principal.id
      if (params["idNivel"] != incidenteInstance."idNivel${nivel}") {
        incidenteInstance."idNivel${nivel}" = params["idNivel"].toLong()
        incidenteInstance."fechaNivel${nivel}" = new Date()
      }

      incidenteInstance.properties = params
      incidenteInstance."solucionNivel${nivel}" =
        params["solucionNivel"]


        incidenteInstance.idCaptura = springSecurityService.principal.id
        incidenteInstance.ipTerminal = request.getRemoteAddr()

        if (!incidenteInstance.save(flush: true)) {
            render(view: "edit", model: [incidenteInstance: incidenteInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'incidente.label', default: 'Incidente'), incidenteInstance.toString()])
        redirect(action: "edit", id: incidenteInstance.id)
    }

    def categoryChanged(long categoryId) {
      categoryChangedProceso(categoryId, 'subcategoryChanged')
    }

    def categoryChangedFinal(long categoryId) {
      categoryChangedProceso(categoryId, 'subcategoryChangedFinal')
    }

    def categoryChangedProceso(long categoryId, String rutinaALlamar) {
      log.debug("categoryId = $categoryId")
        Cat_servCat category = Cat_servCat.get(categoryId)
        def subCategories = []
        if ( category != null ) {
            subCategories = Cat_servSub.findAllByServCat(category, [order:'id'])
        }
        render g.select(id:'servSub', name:'servSub.id', required:'',
          onchange:rutinaALlamar + '(this.value)',
          from:subCategories, optionKey:'id', noSelection:['':' ']
        )
    }

    def subcategoryChanged(long subcategoryId) {
      subcategoryChangedProceso(subcategoryId, 'idServ')
    }

    def subcategoryChangedFinal(long subcategoryId) {
      subcategoryChangedProceso(subcategoryId, 'idServfinal')
    }

    def subcategoryChangedProceso(long subcategoryId, String campoAActualizar) {
      log.debug("subcategoryId = $subcategoryId")
        Cat_servSub subcategory = Cat_servSub.get(subcategoryId)
        def servicios = []
        if ( subcategory != null ) {
            servicios = Cat_serv.findAllByServSub(subcategory, [order:'id'])
        }
        render g.select(id:campoAActualizar, name:campoAActualizar + '.id', required:'',
            from:servicios, optionKey:'id', noSelection:['':'Seleccione una...']
        )
    }

    def soluciónUpdate(Long id, Long version) {
      log.debug("params = $params")
      def incidenteInstance = Incidente.get(id)
      if (!incidenteInstance) {
          flash.message = message(code: 'default.not.found.message', args: [message(code: 'incidente.label', default: 'Incidente'), id])
          redirect(action: "list")
          return
      }

      if (version != null) {
        if (incidenteInstance.version > version) {
          incidenteInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                    [message(code: 'incidente.label', default: 'Incidente')] as Object[],
                    "Another user has updated this Incidente while you were editing")
          render(view: "edit", model: [incidenteInstance: incidenteInstance])
          return
        }
      }

      def userID = springSecurityService.principal.id
      def firmaTeclada = params['passwordfirma']
      def firma = Firmadigital.findById(userID)?.passwordfirma

      if (firmaTeclada != firma) {
        flash.error = "Error en contaseña"
        render(view: "edit", model: [incidenteInstance: incidenteInstance])
        return
      }

      def nivel = incidenteInstance.nivel

      if (!incidenteInstance?."idNivel${nivel}") {
        if(isGestor()) {
          incidenteInstance."fechaNivel${nivel}" = new Date()
        } else {
          flash.error = "Usted no puede modificar este incidente"
          render(view: "edit", model: [incidenteInstance: incidenteInstance])
          return
        }
      } else if (incidenteInstance?."idNivel${nivel}" != userID) {
        flash.error = "Esta incidencia no esta asignada a Usted"
        render(view: "edit", model: [incidenteInstance: incidenteInstance])
        return
      }

      incidenteInstance.properties = params
      incidenteInstance."idNivel${nivel}" = userID
      incidenteInstance."solucionNivel${nivel}" =
        params["solucionNivel"]
      incidenteInstance.idCaptura = userID
      incidenteInstance.ipTerminal = request.getRemoteAddr()
      incidenteInstance."fechaSolnivel${nivel}" = new Date()
      incidenteInstance."firmaNivel${nivel}" = true

      incidenteInstance.estado = 'E' as char

      if (!incidenteInstance.save(flush: true)) {
          render(view: "edit", model: [incidenteInstance: incidenteInstance])
          return
      }

      flash.message = message(code: 'default.updated.message', args: [message(code: 'incidente.label', default: 'Incidente'), incidenteInstance.toString()])
      redirect(action: "list")
    }

    def escalaUpdate(Long id, Long version) {
      log.debug("params = $params")
      def incidenteInstance = Incidente.get(id)
      if (!incidenteInstance) {
          flash.message = message(code: 'default.not.found.message', args: [message(code: 'incidente.label', default: 'Incidente'), id])
          redirect(action: "list")
          return
      }

      if (version != null) {
        if (incidenteInstance.version > version) {
          incidenteInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                    [message(code: 'incidente.label', default: 'Incidente')] as Object[],
                    "Another user has updated this Incidente while you were editing")
          render(view: "edit", model: [incidenteInstance: incidenteInstance])
          return
        }
      }

      def userID = springSecurityService.principal.id
      def firmaTeclada = params['passwordfirma']
      def firma = Firmadigital.findById(userID)?.passwordfirma

      if (firmaTeclada != firma) {
        flash.error = "Error en contaseña"
        render(view: "edit", model: [incidenteInstance: incidenteInstance])
        return
      }

      def nivel = incidenteInstance.nivel

      if (!incidenteInstance?."idNivel${nivel}") {
        if(isGestor()) {
          incidenteInstance."fechaNivel${nivel}" = new Date()
        } else {
          flash.error = "Usted no puede modificar este incidente"
          render(view: "edit", model: [incidenteInstance: incidenteInstance])
          return
        }
      } else if (incidenteInstance?."idNivel${nivel}" != userID) {
        flash.error = "Esta incidencia no esta asignada a Usted"
        render(view: "edit", model: [incidenteInstance: incidenteInstance])
        return
      }

      incidenteInstance.properties = params
      incidenteInstance."idNivel${nivel}" = userID
      incidenteInstance."solucionNivel${nivel}" =
        params["solucionNivel"]
      incidenteInstance.idCaptura = userID
      incidenteInstance.ipTerminal = request.getRemoteAddr()
      incidenteInstance."fechaSolnivel${nivel}" = new Date()
      incidenteInstance."firmaNivel${nivel}" = true

      ++incidenteInstance.nivel
      incidenteInstance.idServresp =
        incidenteInstance.idServ."servResp${incidenteInstance.nivel}"

      if (!incidenteInstance.save(flush: true)) {
          render(view: "edit", model: [incidenteInstance: incidenteInstance])
          return
      }

      flash.message = message(code: 'default.updated.message', args: [message(code: 'incidente.label', default: 'Incidente'), incidenteInstance.toString()])
      redirect(action: "list")
    }

}
