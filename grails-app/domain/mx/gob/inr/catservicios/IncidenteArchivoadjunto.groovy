package mx.gob.inr.catservicios

class IncidenteArchivoadjunto {

	Long idIncidente
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
		datos nullable: true
		nombre nullable: true
		tamanio nullable: true
		tipo nullable: true, maxSize: 20
		idUsuario nullable: true
		lastUpdated nullable: true
		ipTerminal nullable: true, maxSize: 15
	}
}
