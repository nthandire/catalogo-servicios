package catalogo.servicios

class Cat_servCat {

enum Estado {
    Activo, Inactivo
  }


  String categoria
  String descripcion
  Cat_servResp servResp
  Integer valoracion // TODO: ¿que significa?
  Integer disponibilidad // TODO: ¿que significa?
  Estado estado
  Cat_servCob servCob

  static hasMany = [servSubs: Cat_servSub]

  static constraints = {
    categoria(maxSize:255, blank:false)
    descripcion(maxSize:1000, blank:false)
    servResp()
    valoracion min: 1, max: 3, defaultValue: "1"
    disponibilidad min: 0, defaultValue: "0"
    estado blank:false, defaultValue: "'Inactivo'" // (blank:false, inList: ['a','i']) // inList: ['d','s', 'p']
    servCob()
  }

  static mapping = {
    table 'cat_servcat'
    id column:'id_servcat'
    servResp column:'id_servresp'
    // estado length: 1, columnDefinition: 'char(1)'  // TODO: default a   , permite a o i  (activo inactivo)
    servCob column:'id_servcob'
    version false
  }

  String toString() {
    categoria
  }
}
