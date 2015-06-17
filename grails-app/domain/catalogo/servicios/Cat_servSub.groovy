package catalogo.servicios

class Cat_servSub {

  Cat_servCat servCat
  String descripcion

  static hasMany = [servs: Cat_serv]

  static constraints = {
    servCat()
    descripcion(maxSize:255, blank:false)
  }

  static mapping = {
    table 'cat_servsub'
    id column:'id_servsub'
    servCat column:'id_servcat'
    version false
  }

  String toString() {
    descripcion
  }

}
