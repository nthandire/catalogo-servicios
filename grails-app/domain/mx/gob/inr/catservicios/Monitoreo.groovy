package mx.gob.inr.catservicios

class Monitoreo {
  Date fechaMonitoreo
  Integer numeroMonitoreo
  char estadoMonitoreo = (char)'A'
  Integer semaforo = 1
  String nota
  // usuario TODO: ligarlo a la BD
  Date lastUpdated
  Integer modificacion = 1
  String ipTerminal

  static belongsTo = [bitacora:Bitacora]
  static hasMany = [monitoreoDetalles: MonitoreoDetalle]

  static constraints = {
    fechaMonitoreo()
    numeroMonitoreo()
    bitacora column:'id_bitacora'
    estadoMonitoreo blank:false, inList: [(char)'A',(char)'I']
    semaforo blank:false, min: 1, max: 3
    nota maxSize:3000
    modificacion()
    ipTerminal editable:false
  }

  static mapping = {
    id column:'id_monitoreo'
    bitacora column:'id_bitacora'
    estadoMonitoreo length: 1, columnDefinition: 'char(1)',
      defaultValue: "'A'"
    semaforo defaultValue: "1"
    lastUpdated column:'fecha_modificacion'
    version false
  }

  String toString() {
    "monitoreo: $numeroMonitoreo"
  }
}