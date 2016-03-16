package mx.gob.inr.catservicios

class TipoMonitoreo {
  String descripcion

  static constraints = {
    descripcion maxSize:100, blank:false
  }

  static mapping = {
    id column:'id_tipomonitoreo', generator: 'increment'
    descripcion column:'des_tipomonitoreo'
    version false
  }

  String toString() {
    descripcion
  }

}
