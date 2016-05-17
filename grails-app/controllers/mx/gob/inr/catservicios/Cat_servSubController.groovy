package mx.gob.inr.catservicios

import grails.plugins.springsecurity.Secured
import org.springframework.dao.DataIntegrityViolationException

@Secured(['ROLE_SAST_ADMIN'])
class Cat_servSubController {
    static nombreMenu = "Catálogo de Subcategorías de Servicios"
    static ordenMenu = 2

    static allowedMethods = [save: "POST", update: "POST", x_delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
      params.max = Math.min(max ?: 100, 100)
      def lista = Cat_servSub.list()
      log.debug("lista = ${lista}")

      switch (params['sort']) {
        case null:
        case "servCat":
          log.debug("servCat")
          lista.sort{it.servCat.toString()}
        break
        case "descripcion":
          log.debug("descripcion")
          lista.sort{it.descripcion}
        break
      }
      log.debug("lista = ${lista}")

      if (params['order'] == 'desc') {
        lista = lista.reverse()
      }
      log.debug("lista = ${lista}")

      def paramMax = (params['max']?:'0').toInteger()
      def paramOffset = (params['offset']?:'0').toInteger()
      lista = lista.size() ?
        lista[paramOffset..
          Math.min(paramOffset+paramMax-1, lista.size()-1)] :
        []
      log.debug("lista = ${lista}")
      [cat_servSubInstanceList: lista,
        cat_servSubInstanceTotal: Cat_servSub.count()]
    }

    def create() {
        [cat_servSubInstance: new Cat_servSub(params)]
    }

    def save() {
        def cat_servSubInstance = new Cat_servSub(params)
        if (!cat_servSubInstance.save(flush: true)) {
            render(view: "create", model: [cat_servSubInstance: cat_servSubInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'cat_servSub.label', default: 'Cat_servSub'), cat_servSubInstance.toString()])
        redirect(action: "show", id: cat_servSubInstance.id)
    }

    def show(Long id) {
        def cat_servSubInstance = Cat_servSub.get(id)
        if (!cat_servSubInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cat_servSub.label', default: 'Cat_servSub'), id])
            redirect(action: "list")
            return
        }

        [cat_servSubInstance: cat_servSubInstance]
    }

    def edit(Long id) {
        def cat_servSubInstance = Cat_servSub.get(id)
        if (!cat_servSubInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cat_servSub.label', default: 'Cat_servSub'), id])
            redirect(action: "list")
            return
        }

        [cat_servSubInstance: cat_servSubInstance]
    }

    def update(Long id, Long version) {
        def cat_servSubInstance = Cat_servSub.get(id)
        if (!cat_servSubInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cat_servSub.label', default: 'Cat_servSub'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (cat_servSubInstance.version > version) {
                cat_servSubInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'cat_servSub.label', default: 'Cat_servSub')] as Object[],
                          "Another user has updated this Cat_servSub while you were editing")
                render(view: "edit", model: [cat_servSubInstance: cat_servSubInstance])
                return
            }
        }

        cat_servSubInstance.properties = params

        if (!cat_servSubInstance.save(flush: true)) {
            render(view: "edit", model: [cat_servSubInstance: cat_servSubInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'cat_servSub.label', default: 'Cat_servSub'), cat_servSubInstance.toString()])
        redirect(action: "show", id: cat_servSubInstance.id)
    }

    def x_delete(Long id) {
        def cat_servSubInstance = Cat_servSub.get(id)
        if (!cat_servSubInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cat_servSub.label', default: 'Cat_servSub'), id])
            redirect(action: "list")
            return
        }

        try {
            cat_servSubInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'cat_servSub.label', default: 'Cat_servSub'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'cat_servSub.label', default: 'Cat_servSub'), id])
            redirect(action: "show", id: id)
        }
    }
}
