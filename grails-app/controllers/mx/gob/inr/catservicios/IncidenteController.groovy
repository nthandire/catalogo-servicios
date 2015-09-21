package mx.gob.inr.catservicios

import grails.plugins.springsecurity.Secured
import org.springframework.dao.DataIntegrityViolationException
import groovy.time.TimeCategory

@Secured(['ROLE_SAST_COORDINADOR_DE_GESTION','ROLE_SAST_TECNICO_MESA_SERVICIO'])
class IncidenteController {
    def springSecurityService
    static nombreMenu = "Incidentes"
    static ordenMenu = 70

    static allowedMethods = [save: "POST", update: "POST", x_delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [incidenteInstanceList: Incidente.list(params), incidenteInstanceTotal: Incidente.count()]
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

      incidenteInstance.estado = 'A' as char
      incidenteInstance.idCaptura = springSecurityService.principal.id
      incidenteInstance.ipTerminal = request.getRemoteAddr()


        if (!incidenteInstance.save(flush: true)) {
            render(view: "create", model: [incidenteInstance: incidenteInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'incidente.label', default: 'Incidente'), incidenteInstance.toString()])
        redirect(action: "show", id: incidenteInstance.id)
    }

    def createArchivo() {
      log.debug("params = $params")
      def incidenteArchivoadjuntoInstance = new IncidenteArchivoadjunto()
      def incidente = Incidente.get(params.incidente['id'])
      incidenteArchivoadjuntoInstance.idIncidente = incidente
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
        def incidente = Incidente.get(params.idIncidente)
        incidenteArchivoadjuntoInstance.idIncidente = incidente
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
        redirect (action:'edit', id: incidente.id)
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

    def show(Long id) {
        def incidenteInstance = Incidente.get(id)
        if (!incidenteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'incidente.label', default: 'Incidente'), id])
            redirect(action: "list")
            return
        }

        [incidenteInstance: incidenteInstance]
    }

    def edit(Long id) {
        def incidenteInstance = Incidente.get(id)
        if (!incidenteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'incidente.label', default: 'Incidente'), id])
            redirect(action: "list")
            return
        }

        [incidenteInstance: incidenteInstance,
          tecnicos:listaDeTecnicos()]
    }

    def listaDeTecnicos() {
      def userID = springSecurityService.principal.id
      log.debug("userID = ${userID}")
      // Saber si es gestor o no
      def gestor = false
      UsuarioRol.withNewSession { session ->
        def rolUsuarios = UsuarioRol.findAllByUsuario(Usuario.get(userID)).each{
          log.debug("UsuarioRol = $it")
          if(it.rol.authority == 'ROLE_SAST_COORDINADOR_DE_GESTION') {
            gestor = true
          }
        }
      }
      log.debug("gestor = $gestor")

      def tecnicos = null
      if (gestor) {
        def rolUsuarios = null
        Rol.withNewSession { session ->
          rolUsuarios = Rol.findByAuthority('ROLE_SAST_TECNICO_MESA_SERVICIO')
        }
        log.debug("rolUsuarios = $rolUsuarios")
        def usuariosRolesIds = []
        UsuarioRol.withNewSession { sessionUR ->
          def lista = UsuarioRol.findAllByRol(rolUsuarios).
            collect {it.usuario.id}
          usuariosRolesIds = (userID in lista) ? lista : lista + [userID]
        }
        log.debug("usuariosRolesIds = $usuariosRolesIds")

         Usuario.withNewSession { sessionU ->
          tecnicos = Usuario.findAllByIdInList(usuariosRolesIds)
         }
        log.debug("numero de tecnicos = ${tecnicos.size()}")
      }
      return tecnicos
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
        incidenteInstance.idCaptura = springSecurityService.principal.id
        incidenteInstance.ipTerminal = request.getRemoteAddr()

        if (!incidenteInstance.save(flush: true)) {
            render(view: "edit", model: [incidenteInstance: incidenteInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'incidente.label', default: 'Incidente'), incidenteInstance.toString()])
        redirect(action: "show", id: incidenteInstance.id)
    }

    def x_delete(Long id) {
        def incidenteInstance = Incidente.get(id)
        if (!incidenteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'incidente.label', default: 'Incidente'), id])
            redirect(action: "list")
            return
        }

        try {
            incidenteInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'incidente.label', default: 'Incidente'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'incidente.label', default: 'Incidente'), id])
            redirect(action: "show", id: id)
        }
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

}
