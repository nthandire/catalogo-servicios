package mx.gob.inr.catservicios

class Bitacora {
  String tipoBitacora
  String desBitacora
  char estadoBitacora = (char)'A'

  static hasMany = [bitacoraDetalles: BitacoraDetalle, monitoreos: Monitoreo]

  static constraints = {
    tipoBitacora maxSize:100, blank:false, widget: 'text'
    desBitacora maxSize:255, widget: 'textarea'
    estadoBitacora blank:false, inList: [(char)'A',(char)'I']
  }
  
  static mapping = {
    id column:'id_bitacora'
    estadoBitacora length: 1, columnDefinition: 'char(1)', defaultValue: "'A'"
    version false
    datasource "catServ"
  } 

  String toString() {
    "Tipo: $tipoBitacora {${id}}"
  }
}
