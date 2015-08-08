package mx.gob.inr.catservicios

class Cat_servResp {

  String descripcion

  static constraints = {
    descripcion(maxSize:100, blank:false)
  }

  static mapping = {
    table 'cat_servresp'
    id column:'id_servresp'
    version false
    datasource "catServ"
  }

  String toString() {
    descripcion
  }

}
