package mx.gob.inr.catservicios

class MonitoreoDetalle {

  Integer estado = 0
  String observaciones
  static belongsTo = [monitoreo:Monitoreo, bitacoradetalle:BitacoraDetalle] // TODO: en la desc dice monitoreo

  static constraints = {
    estado blank:false
    observaciones maxSize:255, widget: 'textarea', nullable: true
  }

  static mapping = {
    id column:'id_monitoredetalle', generator: "increment"
    monitoreo column:'id_monitoreo'
    bitacoradetalle column:'id_bitacoradetalle', editable:false
    estado defaultValue: 0, editable:false
    version false
  } 

  String toString() {
    "$bitacoradetalle"
  }
}