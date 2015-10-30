package mx.gob.inr.catservicios

class Ubicacion {

  String descripcion
  Integer idCuerpoNivel

	static constraints = {
    descripcion nullable: true
		idCuerpoNivel nullable: true
	}

	static mapping = {
    table 'cat_ubicacion'
		id column: "id_ubicacion", generator: "assigned"
    descripcion column:'des_ubicacion'
    idCuerpoNivel column:'id_cuerponivel'
		version false
		datasource "almacen"
	}

  String toString() {
    descripcion
  }

}
