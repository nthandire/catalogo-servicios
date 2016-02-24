package mx.gob.inr.catservicios

class Bitacora {
  String tipoBitacora
  String descripcion
  Character estado = 'A' as char

  static hasMany = [detalles: BitacoraDetalle, monitoreos: Monitoreo]

  static constraints = {
    tipoBitacora maxSize:100, blank:false, widget: 'text'
    descripcion maxSize:255, blank:false, size: 5..255, widget: 'textarea'
    estado blank:false, inList: ['A' as char,'I' as char]
  }

  static mapping = {
    id column: "id_bitacora", generator: "increment"
    descripcion column:'des_bitacora'
    estado column: "estado_bitacora"
    estado length: 1, columnDefinition: 'char(1)', defaultValue: "'A'"
    version false
  }

  String toString() {
    "${id}: $tipoBitacora"
  }
}
