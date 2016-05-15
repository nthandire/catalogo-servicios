package mx.gob.inr.catservicios

class VEmpleado {

  String email

	static mapping = {
    table 'v_empleado'
    id column: "id_empleado", generator: "increment"
		version false
    datasource "almacen"
	}

	static constraints = {
    email nullable: true, maxSize: 35
	}

  String toString() {
    "$id:email"
  }

}
