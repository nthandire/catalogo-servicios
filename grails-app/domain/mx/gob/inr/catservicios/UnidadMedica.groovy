package mx.gob.inr.catservicios

class UnidadMedica {

  Character idUnidadMedica
  String descripcion
  Character estatus

	static constraints = {
    idUnidadMedica nullable: false
    descripcion nullable: true, maxSize: 150
		estatus nullable: true
	}

	static mapping = {
    table 'unidadmedica'
		id name: "idUnidadMedica", generator: "assigned"
    idUnidadMedica column:'idunidadmedica', length: 1, columnDefinition: 'char(1)'
    descripcion column:'descunidadmedica'
    estatus length: 1, columnDefinition: 'char(1)'
		version false
		datasource "seguridad"
	}

  String toString() {
    descripcion
  }

}
