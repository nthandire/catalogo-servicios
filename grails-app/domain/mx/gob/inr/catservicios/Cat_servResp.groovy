package mx.gob.inr.catservicios

class Cat_servResp {

  String descripcion

  static constraints = {
    descripcion(maxSize:100, blank:false)
  }

  static mapping = {
    table 'borrar_cat_servresp'
    id column:'id_servresp'
    id generator: 'increment'
    version false
  }

  String toString() {
    descripcion
  }

}
