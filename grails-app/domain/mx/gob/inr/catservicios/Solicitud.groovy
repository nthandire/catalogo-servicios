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
	Date lastUpdated
	String ipTerminal

  static hasMany = [detalles: SolicitudDetalle, archivos: SolicitudArchivoadjunto]

	static mapping = {
		id column: "id_solicitud", generator: "increment"
		lastUpdated column: "fechaModificacion"
		version "modificacion"
	}

	static constraints = {
		numeroSolicitud nullable: true, editable:false
		fechaSolicitud nullable: true, editable:false
		estadoSolicitud nullable: true, maxSize: 1, editable:false,
			inList: [(char)'A',(char)'I']
		justificacion nullable: true, maxSize: 1500
		idSolicitante nullable: true, editable:false
		idAutoriza nullable: true
		fechaAutoriza nullable: true, editable:false
		idVb nullable: true
		fechaVb nullable: true, editable:false
		p01 nullable: true
		p02 nullable: true
		p03 nullable: true
		p04 nullable: true
		lastUpdated nullable: true, editable:false
		ipTerminal nullable: true, maxSize: 15, display:false, editable:false
	}

  String toString() {
    numeroSolicitud ? "${numeroSolicitud}/${lastUpdated[Calendar.YEAR]}" : 
    	"${justificacion.substring(0, Math.min(20, justificacion.length()))}"
  }

}