package mx.gob.inr.catservicios

class Cat_servCob {

  String descripcion

  static constraints = {
    descripcion(maxSize:100, blank:false)
  }

  static mapping = {
    table 'borrar_cat_servcob'
    id column:'id_servcob'
    id generator: 'increment'
    version false
  } 

  String toString() {
    descripcion
  }

}
