package mx.gob.inr.catservicios

class BitacoraDetalle {

  String descripcion
  char estado = (char)'A'
  static belongsTo = [bitacora:Bitacora]

  static constraints = {
    descripcion maxSize:255, widget: 'textarea'
    estado blank:false, inList: [(char)'A',(char)'I']
  }

  static mapping = {
    id column:'id_bitacoradetalle'
    descripcion column:'des_bitacoradetalle'
    estado column: "estado_bitacoradetalle"
    estado length: 1, columnDefinition: 'char(1)',
      defaultValue: "'A'"
    bitacora column:'id_bitacora'
    version false
  }

  String toString() {
    descripcion
  }
}