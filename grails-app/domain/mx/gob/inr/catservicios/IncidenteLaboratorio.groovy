package mx.gob.inr.catservicios

class IncidenteLaboratorio {

	Long idIncidente
	Date fechaLaboratorio
	Integer numeroLaboratorio
	Integer idEstado
	Integer idTiposervicio
	String fallaTecnica
	String solucion
	String refacciones
	Integer idUsuario
	Integer idProveedor
	Date fechaReporte
	String numeroReporte
	Date fechaSalida
	Date fechaEntrega
	Date fechaReparacion
	String tecnico
	String observaciones
	Date lastUpdated
	String ipTerminal

	static mapping = {
		id column: "id_laboratorio", generator: "increment"
		lastUpdated column: "fecha_modificacion"
		version "modificacion"
	}

	static constraints = {
		idIncidente nullable: true
		fechaLaboratorio nullable: true
		numeroLaboratorio nullable: true
		idEstado nullable: true
		idTiposervicio nullable: true
		fallaTecnica nullable: true
		solucion nullable: true, maxSize: 2000
		refacciones nullable: true, maxSize: 2000
		idUsuario nullable: true
		idProveedor nullable: true
		fechaReporte nullable: true
		numeroReporte nullable: true, maxSize: 30
		fechaSalida nullable: true
		fechaEntrega nullable: true
		fechaReparacion nullable: true
		tecnico nullable: true
		observaciones nullable: true, maxSize: 2000
		lastUpdated nullable: true
		ipTerminal nullable: true, maxSize: 15
	}
}
