package catalogo.servicios

class Cat_servResp {

  String descripcion

  static constraints = {
    descripcion(maxSize:100, blank:false)
  }

  static mapping = {
    table 'cat_servresp'
    id column:'id_servresp'
    version false
  }

  String toString() {
    descripcion
  }

}
