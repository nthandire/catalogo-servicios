package mx.gob.inr.catservicios

class MonitoreoDetalle {

  char estado = (char)'A' // TODO: la descripción dice que es integer
  String observaciones
  static belongsTo = [monitoreo:Monitoreo, bitacoradetalle:BitacoraDetalle] // TODO: en la desc dice monitoreo

  static constraints = {
    estado blank:false, inList: [(char)'A',(char)'I']
    observaciones maxSize:255, widget: 'textarea'
  }

  static mapping = {
    id column:'id_monitoreodetalle'
    monitoreo column:'id_monitoreo'
    bitacoradetalle column:'id_bitacoradetalle'
    estado length: 1, columnDefinition: 'char(1)',
      defaultValue: "'A'"
    version false
  } 

  String toString() {
    observaciones
  }
}