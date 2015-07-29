package mx.gob.inr.catservicios

import org.springframework.dao.DataIntegrityViolationException

class Cat_bitacoraController {
  def springSecurityService
  static nombreMenu = "Bitácora de cambios del Portafolio de Servicios"
  static ordenMenu = 4

  static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

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
    cat_bitacoraInstance.responsable = springSecurityService.principal.id
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

    flash.message = message(code: 'default.updated.message', args: [message(code: 'cat_bitacora.label', default: 'Cat_bitacora'), cat_bitacoraInstance.id])
    redirect(action: "show", id: cat_bitacoraInstance.id)
  }

  def delete(Long id) {
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
}
