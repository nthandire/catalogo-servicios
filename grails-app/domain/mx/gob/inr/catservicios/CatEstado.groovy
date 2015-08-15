package mx.gob.inr.catservicios

class CatEstado {

	String desEstado

	static mapping = {
		id column: "id_estado", generator: "increment"
		version false
	}

	static constraints = {
		desEstado nullable: true, maxSize: 50
	}
}
