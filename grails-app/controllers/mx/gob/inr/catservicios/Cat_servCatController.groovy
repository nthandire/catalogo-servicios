package mx.gob.inr.catservicios

import grails.plugins.springsecurity.Secured
import org.springframework.dao.DataIntegrityViolationException

@Secured(['ROLE_SAST_ADMIN'])
class Cat_servCatController {
    static nombreMenu = "Catálogo de Categorías de Servicios"
    static ordenMenu = 3

    static allowedMethods = [save: "POST", update: "POST", x_delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
      def lista = Cat_servCat.list()
      log.debug("lista = ${lista}")

      switch (params['sort']) {
        case null:
        case "categoria":
          log.debug("categoria")
          lista.sort{it.categoria.toString()}
        break
        case "descripcion":
          log.debug("descripcion")
          lista.sort{it.descripcion}
        break
        case "servResp":
          log.debug("servResp")
          lista.sort{it.servResp.toString()}
        break
        case "valoracion":
          log.debug("valoracion")
          lista.sort{it?.valoracion}
        break
        case "disponibilidad":
          log.debug("disponibilidad")
          lista.sort{it?.disponibilidad}
        break
        case "estado":
          log.debug("estado")
          lista.sort{it?.estado}
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
      [cat_servCatInstanceList: lista,
        cat_servCatInstanceTotal: Cat_servCat.count()]
    }

    def create() {
        [cat_servCatInstance: new Cat_servCat(params)]
    }

    def save() {
        def cat_servCatInstance = new Cat_servCat(params)
        cat_servCatInstance.categoria = cat_servCatInstance.categoria.trim()
        if (!cat_servCatInstance.save(flush: true)) {
            render(view: "create", model: [cat_servCatInstance: cat_servCatInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'cat_servCat.label', default: 'Cat_servCat'), cat_servCatInstance.toString()])
        redirect(action: "show", id: cat_servCatInstance.id)
    }

    def show(Long id) {
        def cat_servCatInstance = Cat_servCat.get(id)
        if (!cat_servCatInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cat_servCat.label', default: 'Cat_servCat'), id])
            redirect(action: "list")
            return
        }

        [cat_servCatInstance: cat_servCatInstance]
    }

    def edit(Long id) {
        def cat_servCatInstance = Cat_servCat.get(id)
        if (!cat_servCatInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cat_servCat.label', default: 'Cat_servCat'), id])
            redirect(action: "list")
            return
        }

        [cat_servCatInstance: cat_servCatInstance]
    }

    def update(Long id, Long version) {
        def cat_servCatInstance = Cat_servCat.get(id)
        if (!cat_servCatInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cat_servCat.label', default: 'Cat_servCat'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (cat_servCatInstance.version > version) {
                cat_servCatInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'cat_servCat.label', default: 'Cat_servCat')] as Object[],
                          "Another user has updated this Cat_servCat while you were editing")
                render(view: "edit", model: [cat_servCatInstance: cat_servCatInstance])
                return
            }
        }

        cat_servCatInstance.properties = params
        cat_servCatInstance.categoria = cat_servCatInstance.categoria.trim()

        if (!cat_servCatInstance.save(flush: true)) {
            render(view: "edit", model: [cat_servCatInstance: cat_servCatInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'cat_servCat.label', default: 'Cat_servCat'), cat_servCatInstance.toString()])
        redirect(action: "show", id: cat_servCatInstance.id)
    }

    def x_delete(Long id) {
        def cat_servCatInstance = Cat_servCat.get(id)
        if (!cat_servCatInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cat_servCat.label', default: 'Cat_servCat'), id])
            redirect(action: "list")
            return
        }

        try {
            cat_servCatInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'cat_servCat.label', default: 'Cat_servCat'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'cat_servCat.label', default: 'Cat_servCat'), id])
            redirect(action: "show", id: id)
        }
    }
}
