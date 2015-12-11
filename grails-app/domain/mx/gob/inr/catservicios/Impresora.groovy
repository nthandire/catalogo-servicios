package mx.gob.inr.catservicios

class Impresora {

  Long idResguardoentregadetalle
	Date fechaVenceGarantia

	static mapping = {
		id column: "id_impresora", generator: "increment"
		version false
    datasource "almacen"
	}

  String toString() {
    fechaVenceGarantia.toString()
  }

}
