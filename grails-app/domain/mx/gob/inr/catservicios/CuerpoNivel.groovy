package mx.gob.inr.catservicios

class CuerpoNivel {

  String cuerpo
  String nivel

	static constraints = {
    cuerpo nullable: true, maxSize: 10
		nivel nullable: true, maxSize: 50
	}

	static mapping = {
    table 'cat_cuerponivel'
		id column: "id_cuerponivel", generator: "assigned"
		version false
		datasource "almacen"
	}

  String toString() {
    "$cuerpo : $nivel"
  }

}
