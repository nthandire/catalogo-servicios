package mx.gob.inr.catservicios

class Solicitud {

	Date fechaSolicitud
	Integer numeroSolicitud
	Character estadoSolicitud
	String justificacion
	Integer idSolicitante
	Integer idAutoriza
	Date fechaAutoriza
	Integer idVb
	Date fechaVb
	Integer p01
	Integer p02
	Integer p03
	Integer p04
	Date fechaModificacion
	String ipTerminal

	static mapping = {
		id column: "id_solicitud", generator: "increment"
		version "modificacion"
	}

	static constraints = {
		fechaSolicitud nullable: true
		numeroSolicitud nullable: true
		estadoSolicitud nullable: true, maxSize: 1
		justificacion nullable: true, maxSize: 1500
		idSolicitante nullable: true
		idAutoriza nullable: true
		fechaAutoriza nullable: true
		idVb nullable: true
		fechaVb nullable: true
		p01 nullable: true
		p02 nullable: true
		p03 nullable: true
		p04 nullable: true
		fechaModificacion nullable: true
		ipTerminal nullable: true, maxSize: 15
	}
}
