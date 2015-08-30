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
        def criteria = Solicitud.createCriteria()
        def autorizables = criteria.list {
            projections {count()}
            eq ('idAutoriza', (Integer)userID)
            isNull('fechaAutoriza')
        }
        log.debug("numero de autorizables = $autorizables")
        def criteria2 = Solicitud.createCriteria()
        def autorizados = criteria2.list {
            projections {count()}
            eq ('idAutoriza', (Integer)userID)
            isNotNull('fechaAutoriza')
        }
        log.debug("numero de autorizados = $autorizados")
        [autorizablesInstanceList: Solicitud.findAllByIdAutorizaAndFechaAutorizaIsNull((Integer)userID, params),
            autorizablesInstanceTotal: autorizables, 
            autorizadosInstanceList: Solicitud.findAllByIdAutorizaAndFechaAutorizaIsNotNull((Integer)userID, params),
            autorizadosInstanceTotal: autorizados]
    }

/* TODO: pasar a donde se autorice
        def startDate = new Date().clearTime()
        startDate[Calendar.MONTH] = 0
        startDate[Calendar.DATE] = 1
        log.debug("startDate = $startDate")
        def endDate = startDate.clone()
        use(TimeCategory) {
          endDate = endDate + 1.years - 1.seconds
        }
        log.debug("endDate = $endDate")

        def criterio = Solicitud.createCriteria()
        def maxID = criterio.get { // TODO: un test para ver si este algoritmo sique funcionando
          between("fechaAutoriza", startDate, endDate)
          projections {
            max "numeroSolicitud"
          }
        } ?: 0
        log.debug("maxID = $maxID")

        solicitudInstance.numeroSolicitud = ++maxID
*/

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

    def show(Long id) {
        def solicitudInstance = Solicitud.get(id)
        if (!solicitudInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitud.label', default: 'Solicitud'), id])
            redirect(action: "list")
            return
        }

        [solicitudInstance: solicitudInstance]
    }

    def firmar(Long id) {
        def solicitudInstance = Solicitud.get(id)
        if (!solicitudInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitud.label', default: 'Solicitud'), id])
            redirect(action: "list")
            return
        }

        solicitudInstance.properties = params
        solicitudInstance.fechaSolicitud = new Date()

        if (!solicitudInstance.save(flush: true)) {
            render(view: "show", model: [solicitudInstance: solicitudInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'solicitud.label', default: 'Solicitud'), solicitudInstance.toString()])
        redirect(action: "show", id: solicitudInstance.id)
    }

}
