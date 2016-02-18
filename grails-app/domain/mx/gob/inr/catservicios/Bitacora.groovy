package mx.gob.inr.catservicios

class Bitacora {
  String tipoBitacora
  String descripcion
  char estado = (char)'A'

  static hasMany = [bitacoraDetalles: BitacoraDetalle, monitoreos: Monitoreo]

  static constraints = {
    tipoBitacora maxSize:100, blank:false, widget: 'text'
    descripcion maxSize:255, blank:false, size: 5..255, widget: 'textarea'
    estado blank:false, inList: [(char)'A',(char)'I']
  }

  static mapping = {
    id column:'id_bitacora'
    descripcion column:'des_bitacora'
    estado column: "estado_bitacora"
    estado length: 1, columnDefinition: 'char(1)', defaultValue: "'A'"
    version false
  }

  String toString() {
    "Tipo: $tipoBitacora {${id}}"
  }
}
