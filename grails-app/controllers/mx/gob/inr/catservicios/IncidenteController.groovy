package mx.gob.inr.catservicios

import grails.plugins.springsecurity.Secured
import org.springframework.dao.DataIntegrityViolationException
import groovy.time.TimeCategory

@Secured(['ROLE_SAST_COORDINADOR_DE_GESTION','ROLE_SAST_TECNICO_MESA_SERVICIO'])
class IncidenteController {
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

        if (!incidenteInstance.save(flush: true)) {
            render(view: "create", model: [incidenteInstance: incidenteInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'incidente.label', default: 'Incidente'), incidenteInstance.toString()])
        redirect(action: "show", id: incidenteInstance.id)
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

        [incidenteInstance: incidenteInstance]
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
