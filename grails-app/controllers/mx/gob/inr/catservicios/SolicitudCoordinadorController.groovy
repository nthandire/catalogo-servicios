package mx.gob.inr.catservicios

import grails.plugins.springsecurity.Secured
import org.springframework.dao.DataIntegrityViolationException
import groovy.time.TimeCategory

@Secured(['ROLE_COORDINADOR'])
class SolicitudCoordinadorController {
    def springSecurityService
    static nombreMenu = "Mesa de servicio: Coordinación"
    static ordenMenu = 84

    static allowedMethods = [save: "POST", update: "POST", x_delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        log.debug("params = $params")
        def userID = springSecurityService.principal.id
        log.debug("userID = $userID")

        def query =
          "from Solicitud s                                     " +
          " where s.fechaSolicitud is Not Null                  " +
          "   and fechaAutoriza is Not Null                     " +
          "   and exists(select id from SolicitudDetalle d      " +
          "               where s.id = d.idSolicitud            " +
          "                 and idTecnico is null)              "

        def autorizados = Solicitud.executeQuery(
            "select count(*) " + query
        )

        log.debug("numero de autorizados = ${autorizados}")
        [autorizadosInstanceList: Solicitud.executeQuery(query, [:], params),
            autorizadosInstanceTotal: autorizados]
    }

    def listAsignados(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        log.debug("params = $params")
        def userID = springSecurityService.principal.id
        log.debug("userID = $userID")

        def query =
          "from Solicitud s                                     " +
          " where s.fechaSolicitud is Not Null                  " +
          "   and fechaAutoriza is Not Null                     " +
          "   and not exists(select id from SolicitudDetalle d      " +
          "               where s.id = d.idSolicitud            " +
          "                 and idTecnico is null)              "

        def asignados = Solicitud.executeQuery(
            "select count(*) " + query
        )

        log.debug("numero de asignados = ${asignados}")
        [asignadosInstanceList: Solicitud.executeQuery(query, [:], params),
            asignadosInstanceTotal: asignados]
    }

    def listTerminadas(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        log.debug("params = $params")
        def userID = springSecurityService.principal.id
        log.debug("userID = $userID")

        def query =
          "from Solicitud s          " +
          " where s.estado = 'C'     "

        def terminadas = Solicitud.executeQuery(
            "select count(*) " + query
        )

        log.debug("numero de terminadas = ${terminadas}")
        [terminadasInstanceList: Solicitud.executeQuery(query, [:], params),
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

    def showNoFirma(Long id) {
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

        solicitudInstance.fechaAutoriza = new Date()

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
            render(view: "create", model: [solicitudInstance: solicitudInstance])
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

        if (!solicitudInstance.save(flush: true)) {
            render(view: "show", model: [solicitudInstance: solicitudInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'solicitud.label', default: 'Solicitud'), solicitudInstance.toString()])
        redirect(action: "show", id: solicitudInstance.id)
    }

}
