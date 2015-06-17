package catalogo.servicios

class Cat_tiempo {

  String descripcion

  static constraints = {
    descripcion(maxSize:20, blank:false)
  }

  static mapping = {
    id column:'id_tiempo'
    version false
  } 

  String toString() {
    descripcion
  }

}
