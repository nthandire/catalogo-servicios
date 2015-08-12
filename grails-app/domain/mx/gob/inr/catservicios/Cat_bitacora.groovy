package mx.gob.inr.catservicios

class Cat_bitacora {
  Integer no_solicitud // TODO: El sistema la debe generar, reinicio por año. Se debe mostrar con relación al año ##/2015
  String descripcion
  Date lastUpdated
  Date dateCreated
  String observaciones  // TODO: Eliminar

  static belongsTo = [servicio:Cat_serv, responsable:Cat_servResp]

  static constraints = {
    no_solicitud blank:false
    servicio()
    descripcion maxSize:1000, blank:false
    responsable()
    observaciones maxSize:3000
    lastUpdated display:false, editable:false
    dateCreated display:false, editable:false
  }

  static mapping = {
    table 'borrar_cat_bitacora'
    servicio column:'id_serv'
    id generator: 'increment'
    lastUpdated column:'fecha_modificacion'
    dateCreated column:'fecha_creacion'
    version false
  }

  String toString() {
    "Solicitud de Cambio ${no_solicitud}"
  }

}
