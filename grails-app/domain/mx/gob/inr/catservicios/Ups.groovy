package mx.gob.inr.catservicios

class Ups {

  Long idResguardoentregadetalle
	Date fechaVenceGarantia

	static mapping = {
		id column: "id_ups", generator: "increment"
		version false
    datasource "almacen"
	}

  String toString() {
    fechaVenceGarantia.toString()
  }

}
