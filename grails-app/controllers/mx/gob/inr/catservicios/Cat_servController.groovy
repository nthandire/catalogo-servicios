package mx.gob.inr.catservicios

import grails.plugins.springsecurity.Secured
import org.springframework.dao.DataIntegrityViolationException

@Secured(['ROLE_SAST_ADMIN'])
class Cat_servController {
    def springSecurityService
    static nombreMenu = "Catálogo de Categorías de Tercer Nivel"
    static ordenMenu = 1


    static String naturalName() {
        nombreMenu
    }

    static allowedMethods = [save: "POST", update: "POST", x_delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [cat_servInstanceList: Cat_serv.list(params), cat_servInstanceTotal: Cat_serv.count()]
    }

    def create() {
        [cat_servInstance: new Cat_serv(params)]
    }

    def save() {
        def cat_servInstance = new Cat_serv(params)
        cat_servInstance.idUsuario = springSecurityService.principal.id
        cat_servInstance.ipTerminal = request.getRemoteAddr()
        if (!cat_servInstance.save(flush: true)) {
            render(view: "create", model: [cat_servInstance: cat_servInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'cat_serv.label', default: 'Cat_serv'), cat_servInstance.toString()])
        redirect(action: "show", id: cat_servInstance.id)
    }

    def show(Long id) {
        def cat_servInstance = Cat_serv.get(id)
        if (!cat_servInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cat_serv.label', default: 'Cat_serv'), id])
            redirect(action: "list")
            return
        }

        [cat_servInstance: cat_servInstance]
    }

    def edit(Long id) {
        def cat_servInstance = Cat_serv.get(id)
        if (!cat_servInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cat_serv.label', default: 'Cat_serv'), id])
            redirect(action: "list")
            return
        }

        [cat_servInstance: cat_servInstance]
    }

    def update(Long id, Long version) {
        def cat_servInstance = Cat_serv.get(id)
        cat_servInstance.idUsuario = springSecurityService.principal.id
        cat_servInstance.ipTerminal = request.getRemoteAddr()
        if (!cat_servInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cat_serv.label', default: 'Cat_serv'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (cat_servInstance.version > version) {
                cat_servInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'cat_serv.label', default: 'Cat_serv')] as Object[],
                          "Another user has updated this Cat_serv while you were editing")
                render(view: "edit", model: [cat_servInstance: cat_servInstance])
                return
            }
        }

        cat_servInstance.properties = params

        if (!cat_servInstance.save(flush: true)) {
            render(view: "edit", model: [cat_servInstance: cat_servInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'cat_serv.label', default: 'Cat_serv'), cat_servInstance.toString()])
        redirect(action: "show", id: cat_servInstance.id)
    }

    def x_delete(Long id) {
        def cat_servInstance = Cat_serv.get(id)
        if (!cat_servInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cat_serv.label', default: 'Cat_serv'), id])
            redirect(action: "list")
            return
        }

        try {
            cat_servInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'cat_serv.label', default: 'Cat_serv'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'cat_serv.label', default: 'Cat_serv'), id])
            redirect(action: "show", id: id)
        }
    }

  def categoryChanged(long categoryId) {
      Cat_servCat category = Cat_servCat.get(categoryId)
      def subCategories = []
      if ( category != null ) {
          subCategories = Cat_servSub.findAllByServCat(category, [sort:'descripcion'])
      }
      render g.select(id:'servSub', name:'servSub.id',
          from:subCategories, optionKey:'id', noSelection:[null:' ']
      )
  }

}