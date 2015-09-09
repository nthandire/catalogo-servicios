package mx.gob.inr.catservicios

import grails.plugins.springsecurity.Secured
import org.springframework.dao.DataIntegrityViolationException

@Secured(['ROLE_SAST_ADMIN'])
class Cat_bitacoraController {
    def springSecurityService
    static nombreMenu = "Bitácora de cambios del Portafolio de Servicios"
    static ordenMenu = 7

    static allowedMethods = [save: "POST", update: "POST", x_delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [cat_bitacoraInstanceList: Cat_bitacora.list(params), cat_bitacoraInstanceTotal: Cat_bitacora.count()]
    }

    def create() {
        [cat_bitacoraInstance: new Cat_bitacora(params)]
    }

    def save() {
        def cat_bitacoraInstance = new Cat_bitacora(params)
        cat_bitacoraInstance.idUsuario = springSecurityService.principal.id
        cat_bitacoraInstance.ipTerminal = request.getRemoteAddr()
        if (!cat_bitacoraInstance.save(flush: true)) {
            render(view: "create", model: [cat_bitacoraInstance: cat_bitacoraInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'cat_bitacora.label', default: 'Cat_bitacora'), cat_bitacoraInstance.toString()])
        redirect(action: "show", id: cat_bitacoraInstance.id)
    }

    def show(Long id) {
        def cat_bitacoraInstance = Cat_bitacora.get(id)
        if (!cat_bitacoraInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cat_bitacora.label', default: 'Cat_bitacora'), id])
            redirect(action: "list")
            return
        }

        [cat_bitacoraInstance: cat_bitacoraInstance]
    }

    def edit(Long id) {
        def cat_bitacoraInstance = Cat_bitacora.get(id)
        if (!cat_bitacoraInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cat_bitacora.label', default: 'Cat_bitacora'), id])
            redirect(action: "list")
            return
        }

        [cat_bitacoraInstance: cat_bitacoraInstance]
    }

    def update(Long id, Long version) {
        def cat_bitacoraInstance = Cat_bitacora.get(id)
        cat_bitacoraInstance.idUsuario = springSecurityService.principal.id
        cat_bitacoraInstance.ipTerminal = request.getRemoteAddr()
        if (!cat_bitacoraInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cat_bitacora.label', default: 'Cat_bitacora'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (cat_bitacoraInstance.version > version) {
                cat_bitacoraInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'cat_bitacora.label', default: 'Cat_bitacora')] as Object[],
                          "Another user has updated this Cat_bitacora while you were editing")
                render(view: "edit", model: [cat_bitacoraInstance: cat_bitacoraInstance])
                return
            }
        }

        cat_bitacoraInstance.properties = params

        if (!cat_bitacoraInstance.save(flush: true)) {
            render(view: "edit", model: [cat_bitacoraInstance: cat_bitacoraInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'cat_bitacora.label', default: 'Cat_bitacora'), cat_bitacoraInstance.toString()])
        redirect(action: "show", id: cat_bitacoraInstance.id)
    }

    def x_delete(Long id) {
        def cat_bitacoraInstance = Cat_bitacora.get(id)
        if (!cat_bitacoraInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cat_bitacora.label', default: 'Cat_bitacora'), id])
            redirect(action: "list")
            return
        }

        try {
            cat_bitacoraInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'cat_bitacora.label', default: 'Cat_bitacora'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'cat_bitacora.label', default: 'Cat_bitacora'), id])
            redirect(action: "show", id: id)
        }
    }

  def categoryChanged(long categoryId) {
      Cat_servCat category = Cat_servCat.get(categoryId)
      def subCategories = []
      if ( category != null ) {
          subCategories = Cat_servSub.findAllByServCat(category, [order:'id'])
      }
      render g.select(id:'servSub', name:'servSub.id',
          from:subCategories, optionKey:'id', noSelection:[null:'Seleccione una...'],
          class:"many-to-one", onchange:"subcategoryChanged(this.value)"
      )
  }

  def subcategoryChanged(long subcategoryId) {
      Cat_servSub subcategory = Cat_servSub.get(subcategoryId)
      def servicios = []
      if ( subcategory != null ) {
          servicios = Cat_serv.findAllByServSub(subcategory, [order:'id'])
      }
      render g.select(id:'servicio', name:'servicio.id',
          from:servicios, optionKey:'id', noSelection:[null:'Seleccione una...']
      )
  }

  def reporteBitacoraSolicitudesDeCambio() {
    def data = []
    params.image_dir = "${servletContext.getRealPath('/images')}/"
    params.titulo = "REPORTE DE LA BITÁCORA DE CAMBIOS AL PORTAFOLIO DE SERVICIOS"
    
    def startDate = params.startDate
    startDate[Calendar.HOUR_OF_DAY] = 0
    startDate[Calendar.MINUTE] = 0
    def endDate = params.endDate
    endDate[Calendar.HOUR_OF_DAY] = 23
    endDate[Calendar.MINUTE] = 59

    def lista = Cat_bitacora.findAllByLastUpdatedGreaterThanEqualsAndLastUpdatedLessThanEquals(startDate, endDate)

    lista.each { it ->
      def rowBitacora = new RptBitacora (
        num_solicitud: it.folio,
        fecha: it.lastUpdated,
        servicio:it.servicio?.descripcion,
        subcategoria:it.servicio?.servSub?.descripcion,
        categoria:it.servicio?.servSub?.servCat?.categoria,
        descripcion:it.descripcion,
        observaciones:it.descripcion,
      )
      data.add(rowBitacora)
    }

    chain (controller:"jasper", action:"index", model:[data:data], params:params)
  }
}

class RptBitacora {
    Integer num_solicitud
    Date fecha
    String servicio
    String categoria
    String subcategoria
    String descripcion
    String observaciones
}