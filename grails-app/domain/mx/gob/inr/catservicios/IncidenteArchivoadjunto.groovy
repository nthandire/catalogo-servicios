package mx.gob.inr.catservicios

class IncidenteArchivoadjunto {

	Integer idIncidente
  byte[] datos
  String nombre
  Integer tamanio
	String tipo
	Integer idUsuario
	Date lastUpdated
	String ipTerminal

	static mapping = {
		id generator: "increment"
		lastUpdated column: "fecha_modificacion"
		version "modificacion"
	}

	static constraints = {
		idIncidente nullable: true
		datos nullable: true, display:false, editable:false, maxSize:1073741824
		nombre nullable: true
		tamanio nullable: true
		tipo nullable: true, maxSize: 20
		idUsuario nullable: true
		lastUpdated nullable: true
		ipTerminal nullable: true, maxSize: 15
	}

  String toString() {
    nombre
  }

}
