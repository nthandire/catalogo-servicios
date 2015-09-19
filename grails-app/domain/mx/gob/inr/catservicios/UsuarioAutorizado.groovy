package mx.gob.inr.catservicios

class UsuarioAutorizado {

  String area
  Boolean autoriza
  Boolean vobo
  Character estado

  static mapping = {
    id column: "id_usuario", generator: "assigned"
    version false
  }

  static constraints = {
    area nullable: true, maxSize: 50
    autoriza nullable: true, widget: 'checkbox'
    vobo nullable: true, widget: 'checkbox'
    estado nullable: true, maxSize: 1
  }

  String toString() {
    "$id : $area"
  }

}
