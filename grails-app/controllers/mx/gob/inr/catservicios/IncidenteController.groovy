package mx.gob.inr.catservicios

import grails.plugins.springsecurity.Secured
import org.springframework.dao.DataIntegrityViolationException
import groovy.time.TimeCategory


@Secured(['ROLE_SAST_GESTOR','ROLE_SAST_TECNICO',
          'ROLE_SAST_APROBADOR'])
class IncidenteController {
    def springSecurityService
    def firmadoService
    def serviciosService
    static nombreMenu = "Incidentes"
    static ordenMenu = 81

    static allowedMethods = [save: "POST", update: "POST", x_delete: "POST"]

    def index() {
      firmadoService.iniciarSemaforos(session)
      redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 100, 100)
        def userID = springSecurityService.principal.id
        def area = area()
        def areaDesc = area?.descripcion ?: "No tengo el área"
        log.debug("area = ${area}, area.id = ${area?.id}")

        def incidentes = Incidente.findAllByEstado('A' as char)
        log.debug("list incidentes = ${incidentes}")

        def incidentesFiltrados =
          incidentes.findAll {it?.idServresp?.descripcion?.contains areaDesc}

        log.debug("params['sort'] = ${params['sort']}")
        switch (params['sort']) {
          case null:
          case "numeroIncidente":
            log.debug("numeroIncidente")
            incidentesFiltrados.sort{a,b -> a.fechaIncidente[Calendar.YEAR] == b.fechaIncidente[Calendar.YEAR] ?
              b.numeroIncidente <=> a.numeroIncidente :
              b.fechaIncidente[Calendar.YEAR] <=> a.fechaIncidente[Calendar.YEAR]}
          break
          case "idResguardoentregadetalle":
            log.debug("idResguardoentregadetalle")
            incidentesFiltrados.sort{it?.idResguardoentregadetalle}
          break
          case "fechaIncidente":
            log.debug("fechaIncidente")
            incidentesFiltrados.sort{it?.fechaIncidente}
          break
          case "nivel":
            log.debug("nivel")
            incidentesFiltrados.sort{it?.nivel}
          break
          case "estado":
            log.debug("estado")
            incidentesFiltrados.sort{it?.estado}
          break
          case "idReporta":
            log.debug("idReporta")
            incidentesFiltrados.sort{it?.idReporta ? Usuario.get(it.idReporta).toString() : ""}
          break
        }
        log.debug("incidentesFiltrados = ${incidentesFiltrados}")

        def incidenteInstanceList = isCoordinador() || isGestor() ?
            incidentesFiltrados
          :
            incidentesFiltrados.findAll {it."idNivel${it.nivel}" == userID}

        def paramMax = (params['max']?:'0').toInteger()
        def paramOffset = (params['offset']?:'0').toInteger()

        log.debug("paramOffset = ${paramOffset}")
        log.debug("paramOffset+paramMax-1 = ${paramOffset+paramMax-1}")
        log.debug("incidenteInstanceList.size()-1 = ${incidenteInstanceList.size()-1}")
        log.debug("Math.max(incidenteInstanceList.size()-1, 0) = ${Math.max(incidenteInstanceList.size()-1, 0)}")
        log.debug("Math.min(paramOffset+paramMax-1, Math.max(incidenteInstanceList.size()-1, 0)) = ${Math.min(paramOffset+paramMax-1, Math.max(incidenteInstanceList.size()-1, 0))}")

        if (params['order'] == 'asc') {
          incidenteInstanceList = incidenteInstanceList.reverse()
        }
        def incidentesPaginación = incidenteInstanceList.size() ?
          incidenteInstanceList[paramOffset..
            Math.min(paramOffset+paramMax-1, incidenteInstanceList.size()-1)] :
          []
        log.debug("incidentesPaginación = ${incidentesPaginación}")
        [incidenteInstanceList: incidentesPaginación,
          incidenteInstanceTotal: incidenteInstanceList.size(),
          miArea: firmadoService.areaNombre(userID)]
    }

    def listTerminadas(Integer max) {
      params.max = Math.min(max ?: 100, 100)
      def userID = springSecurityService.principal.id
      def area = area()
      def areaDesc = area.descripcion
      log.debug("area = ${area}, area.id = ${area?.id}")

      def incidenteInstanceTotal = Incidente.countByEstadoOrEstado('T' as char, 'C' as char)
      def incidenteInstanceList = Incidente.findAllByEstadoOrEstado('T' as char, 'C' as char)
      switch (params['sort']) {
        case null:
        case "numeroIncidente":
          log.debug("numeroIncidente")
          incidenteInstanceList.sort{a,b -> a.fechaIncidente[Calendar.YEAR] == b.fechaIncidente[Calendar.YEAR] ?
            b.numeroIncidente <=> a.numeroIncidente :
            b.fechaIncidente[Calendar.YEAR] <=> a.fechaIncidente[Calendar.YEAR]}
        break
        case "idResguardoentregadetalle":
          log.debug("idResguardoentregadetalle")
          incidenteInstanceList.sort{it?.idResguardoentregadetalle}
        break
        case "fechaIncidente":
          log.debug("fechaIncidente")
          incidenteInstanceList.sort{it?.fechaIncidente}
        break
        case "nivel":
          log.debug("nivel")
          incidenteInstanceList.sort{it?.nivel}
        break
        case "estado":
          log.debug("estado")
          incidenteInstanceList.sort{it?.estado}
        break
        case "idReporta":
          log.debug("idReporta")
          incidenteInstanceList.sort{it?.idReporta ? Usuario.get(it.idReporta).toString() : ""}
        break
      }
      log.debug("incidenteInstanceList = ${incidenteInstanceList}")

      if (params['order'] == 'desc') {
        incidenteInstanceList = incidenteInstanceList.reverse()
      }
      log.debug("incidenteInstanceList = ${incidenteInstanceList}")

      def paramMax = (params['max']?:'0').toInteger()
      def paramOffset = (params['offset']?:'0').toInteger()
      incidenteInstanceList = incidenteInstanceList.size() ?
        incidenteInstanceList[paramOffset..
          Math.min(paramOffset+paramMax-1, incidenteInstanceList.size()-1)] :
        []
      log.debug("incidenteInstanceList = ${incidenteInstanceList}")
      [incidenteInstanceList: incidenteInstanceList,
        incidenteInstanceTotal: incidenteInstanceTotal,
        miArea: firmadoService.areaNombre(userID)]
    }

    def listEncuestas(Integer max) {
      params.max = Math.min(max ?: 100, 100)
      def userID = springSecurityService.principal.id
      def area = area()
      def areaDesc = area.descripcion
      log.debug("area = ${area}, area.id = ${area?.id}")

      def incidenteInstanceTotal = Incidente.countByEstado('E' as char)
      def incidenteInstanceList = Incidente.findAllByEstado('E' as char)
      log.debug("incidenteInstanceList = ${incidenteInstanceList}")

      switch (params['sort']) {
        case null:
        case "numeroIncidente":
          log.debug("numeroIncidente")
          incidenteInstanceList.sort{a,b -> a.fechaIncidente[Calendar.YEAR] == b.fechaIncidente[Calendar.YEAR] ?
            b.numeroIncidente <=> a.numeroIncidente :
            b.fechaIncidente[Calendar.YEAR] <=> a.fechaIncidente[Calendar.YEAR]}
        break
        case "idResguardoentregadetalle":
          log.debug("idResguardoentregadetalle")
          incidenteInstanceList.sort{it?.idResguardoentregadetalle}
        break
        case "fechaIncidente":
          log.debug("fechaIncidente")
          incidenteInstanceList.sort{it?.fechaIncidente}
        break
        case "nivel":
          log.debug("nivel")
          incidenteInstanceList.sort{it?.nivel}
        break
        case "estado":
          log.debug("estado")
          incidenteInstanceList.sort{it?.estado}
        break
        case "idReporta":
          log.debug("idReporta")
          incidenteInstanceList.sort{it?.idReporta ? Usuario.get(it.idReporta).toString() : ""}
        break
      }
      log.debug("incidenteInstanceList = ${incidenteInstanceList}")

      if (params['order'] == 'desc') {
        incidenteInstanceList = incidenteInstanceList.reverse()
      }
      log.debug("incidenteInstanceList = ${incidenteInstanceList}")

      def paramMax = (params['max']?:'0').toInteger()
      def paramOffset = (params['offset']?:'0').toInteger()
      incidenteInstanceList = incidenteInstanceList.size() ?
        incidenteInstanceList[paramOffset..
          Math.min(paramOffset+paramMax-1, incidenteInstanceList.size()-1)] :
        []
      log.debug("incidenteInstanceList = ${incidenteInstanceList}")
      [incidenteInstanceList: incidenteInstanceList,
        incidenteInstanceTotal: incidenteInstanceTotal,
        miArea: firmadoService.areaNombre(userID)]
    }

  def listIncidentes(Integer max) {
    params.max = Math.min(max ?: 100, 100)
    if (!params.offset) {
      params["offset"] = 0
    } else {
      params.offset = params.offset.toLong()
    }
    log.debug("params = $params")

    def query =
      "  from Incidente                " +
      " where estado = 'A'             "
    def incidentesList = Incidente.executeQuery(query)
    def incidentes = incidentesList.size()
    log.debug("numero de incidentes = ${incidentes}")
    def semaforo = session["semaforo"]
    def listaOrdenar = incidentesList.collect{new IncidenteOrdenado(caso: it,
                          orden: firmadoService.retrasoIncidente(semaforo, it))}
    listaOrdenar.each{it.color = semaforo[it.orden]? semaforo[it.orden].color :"white"}

    def listaOrdenada = []
    if (!params.sort || params.sort == "semaforo") {
      listaOrdenada = listaOrdenar.sort{a,b -> a.orden == b.orden ?
        a.caso.fechaIncidente <=> b.caso.fechaIncidente :
        !params.order || params.order == "asc" ? a.orden <=> b.orden : b.orden <=> a.orden}
    } else if (params.sort == "folio") {
      listaOrdenada = listaOrdenar.sort{!params.order || params.order == "asc" ?
        it.caso.id : -it.caso.id}
    } else { // params.sort == "estado"
      listaOrdenada = listaOrdenar.sort{firmadoService.asignado(it.caso)}
      if (params.order == "desc") {
        listaOrdenada = listaOrdenada.reverse()
      }
    }

    log.debug("listaOrdenada[0] = ${listaOrdenada[0]}, color = ${listaOrdenada[0].color}")

    [incidentesInstanceList: listaOrdenada[params.offset..Math.min(params.offset+params.max-1,listaOrdenada.size()-1)],
      incidentesInstanceTotal: incidentes, bOffset: params.offset,
      miArea: firmadoService.areaNombre(springSecurityService.principal.id)]
  }

  def showIncidente(Long id) {
    log.debug("params = $params")
    def incidente = Incidente.get(id)
    if (!incidente) {
        flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudDetalle.label', default: 'incidente'), id])
        redirect(action: "listIncidentes")
        return
    }

    log.debug("bOffset = $params.offset")
    [incidente: incidente, bOffset: params.offset]
  }

  def correoIncidente(Long id) {
    log.debug("params = $params, id = $id")
    def asunto = "Aviso de servicio retrasado"
    def caso = Incidente.get(id)
    def nivel = caso.nivel
    def usuarios = []
    if (firmadoService.asignado(caso)) {
      usuarios << Usuario.get(caso."idNivel$nivel")
    } else {
      usuarios = firmadoService.aprobadores(caso.idServresp)
    }

    usuarios.each {
      def correo = firmadoService.correo(it.idEmpleado)
      def msg = """Hola ${it},

      El incidente con No. de folio ${caso} a rebasado el tiempo de atención acordado, por lo que se solicita se atienda a la brevedad"""
      firmadoService.sendMail(correo, asunto, msg)
    }

    redirect(action: "listIncidentes", params: params)
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
        [incidenteInstance: new Incidente(params),
          miArea: firmadoService.areaNombre(springSecurityService.principal.id)]
    }

    def save() {
        def incidenteInstance = new Incidente(params)
        log.debug("incidenteInstance.idServ = ${incidenteInstance.idServ}")
        log.debug("incidenteInstance.idServ.servSub = ${incidenteInstance.idServ.servSub}")
        log.debug("incidenteInstance.idServ.servSub.servCat = ${incidenteInstance.idServ.servSub.servCat}")
        log.debug("incidenteInstance.idResguardoentregadetalle = ${incidenteInstance.idResguardoentregadetalle}")

        def userID = springSecurityService.principal.id
        def firmaTeclada = params['passwordfirma']
        def firma = Firmadigital.findById(userID)?.passwordfirma?.reverse()

        if (incidenteInstance.idResguardoentregadetalle &&
            !(ResguardoEntregaDetalle.get(incidenteInstance.idResguardoentregadetalle))) {
          flash.error = "No escogio el equipo de la lista"
          render(view: "create", model: [incidenteInstance: incidenteInstance,
                  miArea: firmadoService.areaNombre(userID)])
          return
        }

        if (incidenteInstance.idResguardoentregadetalle) {
          def caso = incidenteResguardo(incidenteInstance.idResguardoentregadetalle)
          if (caso) {
            flash.error = "Ese equipo ya tiene un incidente ($caso)"
            render(view: "create", model: [incidenteInstance: incidenteInstance,
                    miArea: firmadoService.areaNombre(userID)])
            return
          }
        }

        if (firmaTeclada != firma) {
          flash.error = "Error en contaseña"
          render(view: "create", model: [incidenteInstance: incidenteInstance,
                    miArea: firmadoService.areaNombre(userID)])
          return
        }

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
      incidenteInstance.idServfinal = incidenteInstance.idServ

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

    def incidenteResguardo(Long usuario) {
      Incidente.findByIdResguardoentregadetalleAndEstado(usuario, 'A' as char)
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
        incidenteArchivoadjuntoInstance.tamanio =
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
            render(view: "create", model: [incidenteArchivoadjuntoInstance: incidenteArchivoadjuntoInstance,
                    miArea: firmadoService.areaNombre(springSecurityService.principal.id)])
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
          flash.message = message(code: 'default.not.found.message',
            args: [message(code: 'incidente.label', default: 'Incidente'), id])
          redirect(action: "list")
          return
      }

      preparaEdit(id, incidenteInstance)
    }

    def preparaEdit(Long id, Incidente incidenteInstance) {
        def userID = springSecurityService.principal.id
        log.debug("userID = ${userID}")
        def area = area()
        log.debug("area = <${area}>")
        def areaLab = message(code: "area.de.laboratorio")
        log.debug("areaLab = <${areaLab}>")
        def minPrograma = message(code: "laboratorio.programa.normal").toInteger()
        if (area?.descripcion == areaLab) {
          log.debug("Es DGAIT")
          minPrograma = message(code: "laboratorio.programa.DGAIT").toInteger()
        }
        log.debug("minPrograma = ${minPrograma}")
        def programas = CatPrograma.findAllByIdGreaterThanEquals(minPrograma)
        log.debug("programas = ${programas}")
        def programasProblema = CatPrograma.findAllByIdBetween(
          message(code: "programa.problema.menor"),
          message(code: "programa.problema.mayor"))
        log.debug("programasProblema = ${programasProblema}")

        def tecnicos =
          isCoordinador() ? listaDeTecnicos(incidenteInstance.idServresp) : []
        log.debug("tecnicos = ${tecnicos}")
        def nivel = incidenteInstance.nivel
        def idNivel = incidenteInstance."idNivel${nivel}"
        log.debug("incidente = $incidenteInstance, idNivel = ${idNivel}")
        def areaReporta = firmadoService.areaNombre(incidenteInstance.idReporta)
        log.debug("areaReporta = ${areaReporta}")
        def areaAtendio1 = (incidenteInstance.fechaNivel1) ?
          firmadoService.areaNombre(incidenteInstance.idNivel1) : ""
        log.debug("areaAtendio1 = ${areaAtendio1}")
        def areaAtendio2 = (incidenteInstance.fechaNivel2) ?
          firmadoService.areaNombre(incidenteInstance.idNivel2) : ""
        log.debug("areaAtendio2 = ${areaAtendio2}")
        def areaAtendio3 = (incidenteInstance.fechaNivel3) ?
          firmadoService.areaNombre(incidenteInstance.idNivel3) : ""
        log.debug("areaAtendio3 = ${areaAtendio3}")
        [incidenteInstance: incidenteInstance, tecnicos:tecnicos,
          idNivel: idNivel, yo: userID, areaReporta: areaReporta,
          areaAtendio1: areaAtendio1, areaAtendio2: areaAtendio2,
          areaAtendio3: areaAtendio3, programas:programas,
          programasProblema: programasProblema,
          solucionNivel: incidenteInstance."solucionNivel${nivel}",
          miArea: firmadoService.areaNombre(userID)]
    }

  def listaDeTecnicos(Cat_servResp servresp) {
    log.debug("servresp = $servresp")
    def rolTecnicos = Rol.withNewSession { session ->
      Rol.findByAuthority('ROLE_SAST_TECNICO')
    }
    log.debug("rolTecnicos = $rolTecnicos")

    def usuariosRolesTecnicosIds = UsuarioRol.withNewSession { session ->
      UsuarioRol.findAllByRol(rolTecnicos).collect {it.usuario.id}
    }
    log.debug("usuariosRolesTecnicosIds = $usuariosRolesTecnicosIds")

    def areaDesc = servresp.descripcion
    log.debug("areaDesc = $areaDesc")

    def userID = springSecurityService.principal.id
    def miArea = firmadoService.areaNombre(userID)
    def tecnicosDelAreaIds = UsuarioAutorizado.
      findAllByIdInListAndEstado(usuariosRolesTecnicosIds, 'A' as char).
        findAll{miArea == it.area ||
            (areaDesc.contains('PROV') &&
             'PROV' == it.area.toUpperCase())}.collect {it.id}

    Boolean agregarCoordinador = (isCoordinador() &&
                                  !tecnicosDelAreaIds.contains(userID))
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
                render(view: "edit", model: preparaEdit(id, incidenteInstance))
                return
            }
        }

      incidenteInstance.properties = params

      def nivel = incidenteInstance.nivel
      def userID = springSecurityService.principal.id
      incidenteInstance.properties = params
      incidenteInstance."solucionNivel${nivel}" =
        params["solucionNivel"]

        incidenteInstance.idCaptura = springSecurityService.principal.id
        incidenteInstance.ipTerminal = request.getRemoteAddr()

        if (!incidenteInstance.save(flush: true)) {
            render(view: "edit", model: preparaEdit(id, incidenteInstance))
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
            subCategories = firmadoService.subcategoriasIncidentes(category)
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
            servicios = firmadoService.tercerNivelIncidentes(subcategory)
        }
        render g.select(id:campoAActualizar, name:campoAActualizar + '.id', required:'',
            from:servicios, optionKey:'id', noSelection:['':'Seleccione una...']
        )
    }

    def solucionUpdate(Long id, Long version) {
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
          render(view: "edit", model: preparaEdit(id, incidenteInstance))
          return
        }
      }

      def userID = springSecurityService.principal.id
      def firmaTeclada = params['passwordfirma']
      def firma = Firmadigital.findById(userID)?.passwordfirma?.reverse()

      if (firmaTeclada != firma) {
        flash.error = "Error en contaseña"
        render(view: "edit", model: preparaEdit(id, incidenteInstance))
        return
      }

      def nivel = incidenteInstance.nivel

      if (incidenteInstance?."idNivel${nivel}" != userID) {
        flash.error = "Este incidente no esta asignado a Usted"
        render(view: "edit", model: preparaEdit(id, incidenteInstance))
        return
      }

      incidenteInstance.properties = params
      incidenteInstance."solucionNivel${nivel}" =
        params["solucionNivel"]
      incidenteInstance.idCaptura = userID
      incidenteInstance.ipTerminal = request.getRemoteAddr()
      incidenteInstance."fechaSolnivel${nivel}" = new Date()
      incidenteInstance."firmaNivel${nivel}" = true

      incidenteInstance.estado = nivel == 1 ? 'T' as char : 'E' as char

      if (!incidenteInstance.save(flush: true)) {
          render(view: "edit", model: [incidenteInstance: incidenteInstance])
          return
      }

            def solicitante = Usuario.get(incidenteInstance.idReporta)
            def liga = createLink(controller:"SolicitudEncuesta", action: "editIncidente",
                                  id: incidenteInstance.id, absolute: "true")
            log.debug("liga = ${liga}")
            def asunto = "El incidente ${incidenteInstance} ya ha sido atendida"
            def cuerpoCorreo = """Hola ${solicitante}<br/><br/>

Su solicitud ${incidenteInstance} ya ha sido atendida, para mejorar la calidad del servicio se solicita conteste la siguiente encuesta, usando la siguiente liga:<br/><br/>

<a href='${liga}'>${incidenteInstance}</a>
              """

            def correo = firmadoService.correo(solicitante.idEmpleado)
            firmadoService.sendMailHTML(correo, asunto, cuerpoCorreo)


      flash.message = message(code: 'default.updated.message', args: [message(code: 'incidente.label', default: 'Incidente'), incidenteInstance.toString()])
      redirect(action: "list")
    }

    def tecnicoUpdate(Long id, Long version) {
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
          redirect(action: "edit", id: id)
          return
        }
      }

      def userID = springSecurityService.principal.id
      def firmaTeclada = params['passwordfirmaTec']
      def firma = Firmadigital.findById(userID)?.passwordfirma?.reverse()

      if (firmaTeclada != firma) {
        flash.error = "Error en contaseña"
        redirect(action: "edit", id: id)
        return
      }

      def nivel = incidenteInstance.nivel
      def idTecnico = 0
      if (params["idNivel"] != incidenteInstance."idNivel${nivel}") {
        idTecnico = params["idNivel"].toLong()
        incidenteInstance."idNivel${nivel}" = idTecnico
        incidenteInstance."fechaNivel${nivel}" = new Date()
        if (nivel > 1) {
          incidenteInstance."idAsignanivel${nivel}" = userID
        }

        incidenteInstance.idCaptura = userID
        incidenteInstance.ipTerminal = request.getRemoteAddr()

        if (!incidenteInstance.save(flush: true)) {
            redirect(action: "edit", id: id)
            return
        }
      } else {
        flash.error = "No cambio el técnico"
        redirect(action: "edit", id: id)
        return
      }

      def tecnico = Usuario.get(idTecnico)
      def liga = createLink(controller:"Incidente", action: "edit",
                            id: incidenteInstance.id, absolute: "true")
      log.debug("liga = ${liga}")
      def asunto = "El incidente ${incidenteInstance} ha sido asignado a usted"
      def msg = "Hola ${tecnico}<br/><br/>El incidente folio: " +
        "${incidenteInstance} requiere atención. " +
        "Atiendela utilizando la siguiente liga: <br/><br/>" +
        "<a href='${liga}'>${incidenteInstance}</a>"
      def correo = firmadoService.correo(tecnico.idEmpleado)
      firmadoService.sendMailHTML(correo, asunto, msg)

      flash.message = message(code: 'default.updated.message', args: [message(code: 'incidente.label', default: 'Incidente'), incidenteInstance.toString()])
      redirect(action: "edit", id: id)
    }

    def problemaUpdate(Long id, Long version) {
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
          render(view: "edit", model: preparaEdit(id, incidenteInstance))
          return
        }
      }

      def userID = springSecurityService.principal.id
      def firmaTeclada = params['passwordfirma']
      def firma = Firmadigital.findById(userID)?.passwordfirma?.reverse()
      if (firmaTeclada != firma) {
        flash.error = "Error en contaseña"
        render(view: "edit", model: preparaEdit(id, incidenteInstance))
        return
      }

      def nivel = incidenteInstance.nivel

      if (incidenteInstance?."idNivel${nivel}" != userID) {
        flash.error = "Este incidente no esta asignado a Usted"
        render(view: "edit", model: preparaEdit(id, incidenteInstance))
        return
      }

      params.idPrograma["id"] = params.idProgramaProblema["id"]
      incidenteInstance.properties = params
      incidenteInstance."solucionNivel${nivel}" =
        params["solucionNivel"]
      incidenteInstance.idCaptura = userID
      incidenteInstance.ipTerminal = request.getRemoteAddr()
      incidenteInstance."fechaSolnivel${nivel}" = new Date()
      incidenteInstance."firmaNivel${nivel}" = true

      incidenteInstance.estado = 'P' as char

      if (!incidenteInstance.save(flush: true)) {
          render(view: "edit", model: preparaEdit(id, incidenteInstance))
          return
      }

      def problema = new Problema()

      problema.fuente = "Incidente"
      problema.idFuente = incidenteInstance.id

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

      def maxID = Problema.withCriteria { // TODO: un test para ver si este algoritmo sique funcionando
        between("fechaProblema", startDate, endDate)
        projections {
          max "folio"
        }
      }[0] ?: 0
      log.debug("maxID = $maxID")
      problema.folio = ++maxID
      problema.fechaProblema = new Date()
      problema.observaciones = params["solucionNivel"]
      problema.idUsuario = incidenteInstance.idReporta
      problema.ipTerminal = request.getRemoteAddr()

      if (!problema.save(flush: true)) {
          render(view: "edit", model: preparaEdit(id, incidenteInstance))
          return
      }

      flash.message = message(code: 'default.updated.message', args: [message(code: 'incidente.label', default: 'Incidente'), incidenteInstance.toString()])
      redirect(action: "list")
    }

    def cancelarUpdate(Long id, Long version) {
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
          render(view: "edit", model: preparaEdit(id, incidenteInstance))
          return
        }
      }

      def userID = springSecurityService.principal.id
      def firmaTeclada = params['passwordfirma']
      def firma = Firmadigital.findById(userID)?.passwordfirma?.reverse()
      if (firmaTeclada != firma) {
        flash.error = "Error en contaseña"
        render(view: "edit", model: preparaEdit(id, incidenteInstance))
        return
      }

      def nivel = incidenteInstance.nivel

      if (incidenteInstance?."idNivel${nivel}" != userID) {
        flash.error = "Este incidente no esta asignado a Usted"
        render(view: "edit", model: preparaEdit(id, incidenteInstance))
        return
      }

      params.idPrograma["id"] = params.idProgramaProblema["id"]
      incidenteInstance.properties = params
      incidenteInstance."solucionNivel${nivel}" =
        params["solucionNivel"]
      incidenteInstance.idCaptura = userID
      incidenteInstance.ipTerminal = request.getRemoteAddr()
      incidenteInstance."fechaSolnivel${nivel}" = new Date()
      incidenteInstance."firmaNivel${nivel}" = true

      incidenteInstance.estado = 'C' as char

      if (!incidenteInstance.save(flush: true)) {
          render(view: "edit", model: preparaEdit(id, incidenteInstance))
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
          render(view: "edit", model: preparaEdit(id, incidenteInstance))
          return
        }
      }

      def userID = springSecurityService.principal.id
      def firmaTeclada = params['passwordfirma']
      def firma = Firmadigital.findById(userID)?.passwordfirma?.reverse()

      if (firmaTeclada != firma) {
        flash.error = "Error en contaseña"
        render(view: "edit", model: preparaEdit(id, incidenteInstance))
        return
      }

      def nivel = incidenteInstance.nivel

      if (incidenteInstance?."idNivel${nivel}" != userID) {
        flash.error = "Este incidente no esta asignado a Usted"
        render(view: "edit", model: preparaEdit(id, incidenteInstance))
        return
      }

      incidenteInstance.properties = params
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
          render(view: "edit", model: preparaEdit(id, incidenteInstance))
          return
      }

      flash.message = message(code: 'default.updated.message', args: [message(code: 'incidente.label', default: 'Incidente'), incidenteInstance.toString()])
      redirect(action: "list")
    }

  def extension() {
    log.debug("params = $params")
    render serviciosService.extension(params)
  }

}
