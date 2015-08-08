package mx.gob.inr.catservicios

class Cat_servSub {

  String descripcion

  static belongsTo = [servCat:Cat_servCat]
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
    datasource "catServ"
  }

  String toString() {
    descripcion
  }

}
