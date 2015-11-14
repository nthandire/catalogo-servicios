package mx.gob.inr.catservicios

import org.grails.plugin.easygrid.Easygrid
import org.grails.plugin.easygrid.Filter
import grails.plugins.springsecurity.Secured
import org.springframework.dao.DataIntegrityViolationException
import groovy.time.TimeCategory

@Easygrid
@Secured(['ROLE_SAST_COORDINADOR_DE_GESTION'])
class MonitoreoMSController {
    def firmadoService
    static nombreMenu = "Administración de servicio"
    static ordenMenu = 89

    static allowedMethods = [save: "POST", update: "POST", x_delete: "POST"]



    def requerimientosGrid = {
      dataSourceType 'gorm'
      domainClass SolicitudDetalle
      gridImpl 'jqgrid'
      inlineEdit false
      columns {
        numeroSolicitud {
          value { det ->
            det.idSolicitud.toString()
          }
        }
        inicioatencion { value { it.idSolicitud.fechaAutoriza } }
        solicitante {
          value { Usuario.get(it.idSolicitud.idSolicitante).toString() }
        }
        area {
          value { firmadoService.areaNombre(it.idSolicitud.idSolicitante) }
        }
        extension {
          value { Usuario.get(it.idSolicitud.idSolicitante).extension }
        }
        categoria { value { it.idServcat.toString() } }
        subcategoria { value { it?.idServ?.servSub?.toString() } }
        idServ { value { it?.idServ?.toString() } }
        estado {
          value { it.idSolicitud.estado ?
            g.message(code:"solicitud.estado.${it.idSolicitud.estado}") :
            "Pendiente de firmar por el solicitante"}
        }
      }
    }

            // justificacion
            // estado



    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
      params.max = 15
    }

    def show(Long id) {
      def solicitudInstance = Solicitud.get(id)
      if (!solicitudInstance) {
          flash.message = message(code: 'default.not.found.message',
                                  args: [message(code: 'solicitud.label',
                                                 default: 'Solicitud'), id])
          redirect(action: "list")
          return
      }

      [solicitudInstance: solicitudInstance]
    }

    def edit(Long id) {
      def solicitudInstance = Solicitud.get(id)
      if (!solicitudInstance) {
          flash.message = message(code: 'default.not.found.message',
                                  args: [message(code: 'solicitud.label',
                                                 default: 'Solicitud'), id])
          redirect(action: "list")
          return
      }

      [solicitudInstance: solicitudInstance]
    }

}