package mx.gob.inr.catservicios

class Cat_bitacora {
  Integer no_solicitud // TODO: ¿es numerica?
  String descripcion
  Date lastUpdated
  String observaciones

  static belongsTo = [servicio:Cat_serv, responsable:Cat_servResp]

  static constraints = {
    no_solicitud blank:false
    servicio()
    descripcion maxSize:1000, blank:false
    responsable()
    observaciones maxSize:3000
    lastUpdated display:false, editable:false
  }

  static mapping = {
    servicio column:'id_serv'
    lastUpdated column:'fecha_modificacion'
    version false
  }

  String toString() {
    "Solicitud de Cambio ${no_solicitud}"
  }

}
