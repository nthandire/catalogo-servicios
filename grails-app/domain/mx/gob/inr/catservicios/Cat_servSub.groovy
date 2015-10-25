package mx.gob.inr.catservicios

class Cat_servSub {

  String descripcion
  char estado = (char)'A'

  static belongsTo = [servCat:Cat_servCat]
  static hasMany = [servs: Cat_serv]

  static constraints = {
    servCat()
    descripcion(maxSize:255, blank:false)
    estado blank:false, inList: [(char)'A',(char)'I']
  }

  static mapping = {
    table 'cat_servsub'
    id column:'id_servsub'
    id generator: 'increment'
    servCat column:'id_servcat'
    estado length: 1, columnDefinition: 'char(1)', defaultValue: "'A'"
    version false
  }

  String toString() {
    descripcion
  }

}
