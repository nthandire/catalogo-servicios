package mx.gob.inr.catservicios

class BitacoraDetalle {

  String descripcion
  Character estado = 'A' as char

  static belongsTo = [bitacora:Bitacora]

  static constraints = {
    descripcion maxSize:255, blank:false, size: 5..255, widget: 'textarea'
    estado blank:false, inList: ['A' as char,'I' as char]
  }

  static mapping = {
    id column:'id_bitacoradetalle', generator: "increment"
    descripcion column:'des_bitacoradetalle'
    estado column: "estado_bitacoradetalle",
      length: 1, columnDefinition: 'char(1)', defaultValue: "'A'"
    bitacora column:'id_bitacora'
    version false
  }

  String toString() {
    descripcion
  }
}