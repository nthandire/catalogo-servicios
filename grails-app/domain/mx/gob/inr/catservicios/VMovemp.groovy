package mx.gob.inr.catservicios

class VMovemp {

  Long idArea

	static mapping = {
    table 'v_movemp'
    id column: "id_empleado", generator: "increment"
		version false
    datasource "almacen"
	}

	static constraints = {
    idArea nullable: true
	}

  String toString() {
    "$id:idArea"
  }

}
