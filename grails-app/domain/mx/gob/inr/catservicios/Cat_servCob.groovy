package mx.gob.inr.catservicios

class Cat_servCob {

  String descripcion

  static constraints = {
    descripcion(maxSize:100, blank:false)
  }

  static mapping = {
    table 'cat_servcob'
    id column:'id_servcob'
    version false
  } 

  String toString() {
    descripcion
  }

}
