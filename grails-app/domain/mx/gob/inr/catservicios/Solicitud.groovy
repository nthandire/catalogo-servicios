package mx.gob.inr.catservicios

class Solicitud {

  Date fechaSolicitud
  Integer numeroSolicitud
  Character estado
  String justificacion
  Integer idSolicitante
  Integer idAutoriza
  Date fechaAutoriza
  Integer idRevisa
  Date fechaRevisa
  Integer idVb
  Date fechaVb
  Integer p01
  Integer p02
  Integer p03
  Integer p04
  Date lastUpdated
  String ipTerminal
  String comentarioVb
  Character encuesta
  Date fechaEncuesta

  static hasMany = [detalles: SolicitudDetalle, archivos: SolicitudArchivoadjunto]

	static mapping = {
		id column: "id_solicitud", generator: "increment"
		estado column: "estado_solicitud"
		lastUpdated column: "fecha_modificacion"
		version "modificacion"
	}

	static constraints = {
		numeroSolicitud nullable: true, editable:false
    fechaSolicitud nullable: true, editable:false
    estado nullable: true, maxSize: 1, editable:false,
      inList: [(char)'F', (char)'A', (char)'R', (char)'V', (char)'E', (char)'T', (char)'C']
    justificacion nullable: true, maxSize: 1500
    comentarioVb nullable: true, maxSize: 1500
    idSolicitante nullable: true, editable:false
    idAutoriza nullable: true
		fechaAutoriza nullable: true, editable:false
    idRevisa nullable: true
    fechaRevisa nullable: true, editable:false
		idVb nullable: true
		fechaVb nullable: true, editable:false
		p01 nullable: true
		p02 nullable: true
		p03 nullable: true
		p04 nullable: true
		lastUpdated nullable: true, editable:false
		ipTerminal nullable: true, maxSize: 15, display:false, editable:false
    encuesta nullable: true, maxSize: 1, editable:false,
      inList: [(char)'U', (char)'S']
    fechaEncuesta nullable: true, editable:false
	}

  String toString() {
    numeroSolicitud ? "${numeroSolicitud}/${(fechaSolicitud?:lastUpdated)[Calendar.YEAR]}" :
    	"${justificacion?justificacion.substring(0, Math.min(30, justificacion.length())):""}"
  }

}