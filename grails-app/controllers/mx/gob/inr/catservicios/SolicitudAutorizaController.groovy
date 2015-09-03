package mx.gob.inr.catservicios

import grails.plugins.springsecurity.Secured
import org.springframework.dao.DataIntegrityViolationException
import groovy.time.TimeCategory

@Secured(['ROLE_USUARIO'])
class SolicitudAutorizaController {
    def springSecurityService
    static nombreMenu = "Autoriza"
    static ordenMenu = 83

    // TODO: intento de condicionar el display del controlador, borrar
    // static ordenMenuInterna = 83

    // static Integer getOrdenMenu() {
    //   def springSecurityService
    //   def userID = springSecurityService?.principal?.id
    //   println ("userID = $userID")
    //   if (userID) {
    //     def criteria = Solicitud.createCriteria()
    //     def autorizables = criteria.list {
    //         projections {count()}
    //         eq ('idAutoriza', (Integer)userID)
    //     }
    //     if (autorizables > 0)
    //       ordenMenuInterna
    //     else
    //       -ordenMenuInterna
    //     } else 
    //       -ordenMenuInterna
    // }

    static allowedMethods = [save: "POST", update: "POST", x_delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        log.debug("params = $params")
        def userID = springSecurityService.principal.id
        log.debug("userID = $userID")
        def autorizables = Solicitud.withCriteria {
            projections {count()}
            eq ('idAutoriza', (Integer)userID)
            eq ('estado', 'F' as char)
        }
        log.debug("numero de autorizables = $autorizables")
        [autorizablesInstanceList: Solicitud.findAllByIdAutorizaAndEstado((Integer)userID, 'F' as char, params),
          autorizablesInstanceTotal: autorizables]
    }

    def listAutorizados(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        log.debug("params = $params")
        def userID = springSecurityService.principal.id
        log.debug("userID = $userID")
        def autorizados = Solicitud.withCriteria {
            projections {count()}
            eq ('idAutoriza', (Integer)userID)
            eq ('estado', 'A' as char)
        }
        log.debug("numero de autorizados = $autorizados")
        [autorizadosInstanceList: Solicitud.findAllByIdAutorizaAndEstado((Integer)userID, 'A' as char, params),
          autorizadosInstanceTotal: autorizados]
    }

    def listTerminadas(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        log.debug("params = $params")
        def userID = springSecurityService.principal.id
        log.debug("userID = $userID")
        def terminadas = Solicitud.withCriteria {
            projections {count()}
            eq ('idAutoriza', (Integer)userID)
            eq ('estado', 'T' as char)

        }
        log.debug("numero de terminadas = $terminadas")
        [terminadasInstanceList: Solicitud.
            findAllByIdAutorizaAndEstado((Integer)userID, 'T' as char, params),
          terminadasInstanceTotal: terminadas]
    }

    def show(Long id) {
        def solicitudInstance = Solicitud.get(id)
        if (!solicitudInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitud.label', default: 'Solicitud'), id])
            redirect(action: "list")
            return
        }

        [solicitudInstance: solicitudInstance]
    }

    def showDetalle(Long id) {
        def solicitudDetalleInstance = SolicitudDetalle.get(id)
        if (!solicitudDetalleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitudDetalle.label', default: 'SolicitudDetalle'), id])
            redirect(action: "list")
            return
        }

        [solicitudDetalleInstance: solicitudDetalleInstance]
    }

    def firmar(Long id) {
        def solicitudInstance = Solicitud.get(id)
        if (!solicitudInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitud.label', default: 'Solicitud'), id])
            redirect(action: "list")
            return
        }

        if (solicitudInstance?.detalles?.size()) {
        } else {
            flash.error = "La solicitud debe tener al menos un detalle para poderla firmar"
            render(view: "show", model: [solicitudInstance: solicitudInstance])
            return
        }

        [solicitudInstance: solicitudInstance]
    }

    def firmarUpdate(Long id) {
        log.debug("params = $params")
        def solicitudInstance = Solicitud.get(id)
        if (!solicitudInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitud.label', default: 'Solicitud'), id])
            redirect(action: "list")
            return
        }

        def userID = springSecurityService.principal.id
        log.debug("userID = $userID")

        def firmaTeclada = params['passwordfirma']
        log.debug("firmaTeclada = $firmaTeclada")
        def firma = Firmadigital.findById(userID)?.passwordfirma
        log.debug("firma = $firma")

        if (firmaTeclada != firma) {
            flash.error = "Error en contaseña"
            render(view: "show", model: [solicitudInstance: solicitudInstance])
            return
        }

        solicitudInstance.fechaAutoriza = new Date()
        solicitudInstance.estado = 'A' as char

        // Asignarle el siguiente folio dentro del año
        def startDate = new Date().clearTime()
        startDate[Calendar.MONTH] = 0
        startDate[Calendar.DATE] = 1
        log.debug("startDate = $startDate")
        def endDate = startDate.clone()
        use(TimeCategory) {
          endDate = endDate + 1.years - 1.seconds
        }
        log.debug("endDate = $endDate")

        def maxID = Solicitud.withCriteria { // TODO: un test para ver si este algoritmo sique funcionando
          between("fechaAutoriza", startDate, endDate)
          projections {
            max "numeroSolicitud"
          }
        }[0] ?: 0
        log.debug("maxID = $maxID")

        solicitudInstance.numeroSolicitud = ++maxID

        if (!solicitudInstance.save(flush: true)) {
            render(view: "show", model: [solicitudInstance: solicitudInstance])
            return
        }

/* TODO: habilitar cuando no este en desarrollo
        def idUsuario = springSecurityService.principal.id
        def personasInstance = Usuario.get(idUsuario)
        sendMail {
          to 'dzamora@inr.gob.mx' // TODO: mandar el correo al que solicito       personasInstance.correo
          subject "Solicitud ${solicitudInstance.toString()} registrada en el sistema"
          body "Hola ${personasInstance.username}\n\nSu solicitud folio " + 
            "${solicitudInstance.toString()} ya esta registrada en el sistema, " +
            "pronto seras contactado con relación a el\n"
        }
*/

        flash.message = message(code: 'default.updated.message', args: [message(code: 'solicitud.label', default: 'Solicitud'), solicitudInstance.toString()])
        redirect(action: "show", id: solicitudInstance.id)
    }

}
