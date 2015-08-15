package mx.gob.inr.catservicios

class MonitoreoArchivoadjunto {

	Long idMonitoreo
	byte[] datos
	String nombre
	Integer tamanio
	String tipo
	Integer idUsuario
	Date fechaModificacion
	String ipTerminal

	static mapping = {
		id generator: "increment"
		version "modificacion"
	}

	static constraints = {
		idMonitoreo nullable: true
		datos nullable: true
		nombre nullable: true
		tamanio nullable: true
		tipo nullable: true, maxSize: 20
		idUsuario nullable: true
		fechaModificacion nullable: true
		ipTerminal nullable: true, maxSize: 15
	}
}
