package mx.gob.inr.catservicios

class Cat_servCat {

/*
  enum Estado {
    Activo, Inactivo
  }

  enum Valoracion {
    Alto, Medio, Bajo
  }
*/

  String categoria
  String descripcion
  Cat_servResp servResp
  Integer valoracion = 1
  Integer disponibilidad = 0
  char estado = (char)'A'
  Cat_servCob servCob

  static hasMany = [servSubs: Cat_servSub]

  static constraints = {
    categoria(maxSize:255, blank:false)
    descripcion(maxSize:1000, blank:false)
    servResp()
    valoracion blank:false, min: 1, max: 3
    disponibilidad min: 0
    estado blank:false, inList: [(char)'A',(char)'I']
    servCob()
  }

  static mapping = {
    table 'cat_servcat'
    id column:'id_servcat'
    servResp column:'id_servresp'
    valoracion defaultValue: "1"
    disponibilidad defaultValue: "0"
    estado length: 1, columnDefinition: 'char(1)', defaultValue: "'A'"
    servCob column:'id_servcob'
    version false
  }

  String toString() {
    categoria
  }
}
