package mx.gob.inr.catservicios

class VArea {

  String descripcion

	static mapping = {
    table 'v_area'
    id column: "id_area", generator: "increment"
    descripcion column: "des_area"
		version false
    datasource "almacen"
	}

	static constraints = {
    descripcion nullable: true
	}

  String toString() {
    descripcion
  }

}
