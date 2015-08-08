package mx.gob.inr.catservicios

class BitacoraDetalle {

  String desBitacoradetalle
  char estado_bitacoradetalle = (char)'A'
  static belongsTo = [bitacora:Bitacora]

  static constraints = {
    desBitacoradetalle maxSize:255, widget: 'textarea'
    estado_bitacoradetalle blank:false, inList: [(char)'A',(char)'I']
  }

  static mapping = {
    id column:'id_bitacoradetalle'
    bitacora column:'id_bitacora'
    estado_bitacoradetalle length: 1, columnDefinition: 'char(1)',
      defaultValue: "'A'"
    version false
    datasource "catServ"
  } 

  String toString() {
    desBitacoradetalle
  }
}