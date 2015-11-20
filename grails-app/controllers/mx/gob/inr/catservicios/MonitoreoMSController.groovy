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
          value { it.idSolicitud.toString() }
          enableFilter false
          // filterClosure { Filter filter ->
          //   eq('idSolicitud.numeroSolicitud', "%${filter.paramValue}%")
          // }
          jqgrid {
            sortable false
          }
        }
        inicioatencion { value { it.idSolicitud.fechaAutoriza }
          label """<div style="height: auto; padding: 4px 0;"><span>Fecha del inicio</span><br/><span>de la Atención</span></div>"""
          enableFilter false
          jqgrid {
              sortable false
          }
        }
        solicitante {
          value { Usuario.get(it.idSolicitud.idSolicitante).toString() }
          enableFilter false
          jqgrid {
              sortable false
          }
        }
        area {
          value { firmadoService.areaNombre(it.idSolicitud.idSolicitante) }
          enableFilter false
          jqgrid {
              sortable false
          }
        }
        extension {
          value { Usuario.get(it.idSolicitud.idSolicitante).extension }
          enableFilter false
          jqgrid {
              sortable false
          }
        }
        categoria { value { it.idServcat.toString() }
          enableFilter false
          jqgrid {
              sortable false
          }
        }
        subcategoria { value { it?.idServ?.servSub?.toString() }
          enableFilter false
          jqgrid {
              sortable false
          }
        }
        idServ {
          label """<div style="height: auto; padding: 4px 0;"><span>Categoría de</span><br/><span>Tercer nivel</span></div>"""
          value { it?.idServ?.toString() }
          enableFilter false
          jqgrid {
              sortable false
          }
        }
        estado {
          label """<div style="height: auto; padding: 4px 0;"><span>Estado del</span><br/><span>requerimiento</span></div>"""
          value { it.idSolicitud.estado ?
            g.message(code:"solicitud.estado.${it.idSolicitud.estado}") :
            "Pendiente de firmar por el solicitante"}
          enableFilter false
          jqgrid {
              sortable false
          }
        }
      }
    }



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