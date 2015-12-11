package mx.gob.inr.catservicios

class Cpu {

  Long idResguardoentregadetalle
	Date fechaVenceGarantia

	static mapping = {
		id column: "id_cpu", generator: "increment"
		version false
    datasource "almacen"
	}

  String toString() {
    fechaVenceGarantia.toString()
  }

}
