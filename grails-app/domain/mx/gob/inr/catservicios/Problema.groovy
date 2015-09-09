package mx.gob.inr.catservicios

class Problema {

	String idProblema
	String fuente
	Long idFuente
	Date fechaProblema
	Integer folio
	String observaciones
	String solucion
	Date fechaSolucion
	String resolvio
	Integer idUsuario
	Date lastUpdated
	String ipTerminal

	static mapping = {
		id name: "idProblema", generator: "increment"
		lastUpdated column: "fecha_modificacion"
		version "modificacion"
	}

	static constraints = {
		idProblema maxSize: 18, unique: ["solucion", "observaciones"]
		fuente nullable: true, maxSize: 50
		idFuente nullable: true
		fechaProblema nullable: true
		folio nullable: true
		observaciones nullable: true, maxSize: 3000
		solucion nullable: true, maxSize: 3000
		fechaSolucion nullable: true
		resolvio nullable: true
		idUsuario nullable: true
		lastUpdated nullable: true
		ipTerminal nullable: true, maxSize: 15
	}
}
