package catalogo.servicios

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
  Integer valoracion
  Integer disponibilidad
  char estado
  Cat_servCob servCob

  static hasMany = [servSubs: Cat_servSub]

  static constraints = {
    categoria(maxSize:255, blank:false)
    descripcion(maxSize:1000, blank:false)
    servResp()
    valoracion blank:false, min: 1, max: 3, defaultValue: "1"
    disponibilidad min: 0, defaultValue: "0"
    estado blank:false, defaultValue: "'A'", inList: ["A","I"] // (blank:false, inList: ['a','i']) // inList: ['d','s', 'p']
    servCob()
  }

  static mapping = {
    table 'cat_servcat'
    id column:'id_servcat'
    servResp column:'id_servresp'
    estado length: 1, columnDefinition: 'char(1)'
    servCob column:'id_servcob'
    version false
  }

  String toString() {
    categoria
  }
}
