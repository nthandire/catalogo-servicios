package mx.gob.inr.catservicios

class Proveedor {

	String desProveedor
	String representante
	String telefono

	static mapping = {
		id column: "id_proveedor", generator: "increment"
		version false
	}

	static constraints = {
		desProveedor nullable: true
		representante nullable: true
		telefono nullable: true, maxSize: 50
	}
}
