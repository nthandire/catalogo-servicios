package mx.gob.inr.catservicios

class Cat_bitacora {
  Integer folio
  String descripcion
  Date lastUpdated
  Integer idUsuario
  String ipTerminal

  static belongsTo = [servicio:Cat_serv]

  static constraints = {
    folio blank:false
    servicio()
    descripcion maxSize:1000, blank:false
    lastUpdated display:false, editable:false
    idUsuario display:false, editable:false
    ipTerminal display:false, editable:false, maxSize:15
  }

  static mapping = {
    table 'cat_serv_hist'
    id column:'id_servhist', generator: 'increment'
    servicio column:'id_serv'
    lastUpdated column:'fecha_modificacion'
    version false
  }

  String toString() {
    "${folio}/${lastUpdated[Calendar.YEAR]}"
  }

}
