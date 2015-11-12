package mx.gob.inr.catservicios

class Marca {

	String desMarca

	static mapping = {
		id column: "id_marca", generator: "increment"
		version false
    datasource "almacen"
	}

	static constraints = {
		desMarca nullable: true, maxSize: 50
	}

  String toString() {
    desMarca
  }

}
