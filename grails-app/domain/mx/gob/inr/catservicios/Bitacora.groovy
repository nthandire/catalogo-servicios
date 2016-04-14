package mx.gob.inr.catservicios

class Bitacora {
  Date fecha
  Integer numeroMonitoreo
  Character estado = 'A' as char
  Integer semaforo = 1
  Integer idTipomonitoreo
  String nota
  Long idUsuario
  Date lastUpdated
  String ipTerminal
  Long idSeguimiento

  static belongsTo = [bitacora:Sistema]
  static hasMany = [detalles: MonitoreoDetalle]

  static constraints = {
    fecha editable:false
    numeroMonitoreo editable:false
    bitacora column:'id_bitacora'
    estado column:'estado_monitoreo', blank:false,
      inList: [(char)'A', (char)'I', (char)'P'], editable:false
    semaforo blank:false, min: 1, max: 3
    idTipomonitoreo blank:false
    nota maxSize:3000
    idUsuario display:false, editable:false
    ipTerminal editable:false
    idSeguimiento nullable: true
  }

  static mapping = {
    table 'monitoreo'
    id column:'id_monitoreo', generator: "increment"
    fecha column:'fecha_monitoreo'
    bitacora column:'id_bitacora'
    estado column: "estado_monitoreo", length: 1,
      columnDefinition: 'char(1)', defaultValue: "'A'"
    semaforo defaultValue: "1"
    lastUpdated column:'fecha_modificacion'
    version column:'modificacion'
  }

  String toString() {
    "${numeroMonitoreo}/${fecha[Calendar.YEAR]}"
  }
}