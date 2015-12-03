package mx.gob.inr.catservicios

class Semaforo {

  String color
	Integer min

	static mapping = {
		id column: "id_semaforo", generator: "increment"
		version false
	}

	static constraints = {
		color nullable: true, maxSize: 25
    min min: 0
	}

  String toString() {
    "$color : $min"
  }

}
