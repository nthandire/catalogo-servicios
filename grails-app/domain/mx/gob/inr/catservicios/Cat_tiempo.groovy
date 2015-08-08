package mx.gob.inr.catservicios

class Cat_tiempo {

  String descripcion

  static constraints = {
    descripcion(maxSize:20, blank:false)
  }

  static mapping = {
    id column:'id_tiempo'
    version false
    datasource "catServ"
  } 

  String toString() {
    descripcion
  }

}
