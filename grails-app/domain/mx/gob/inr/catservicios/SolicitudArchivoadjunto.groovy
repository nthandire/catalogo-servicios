package mx.gob.inr.catservicios

class SolicitudArchivoadjunto {

	byte[] datos
	String nombre
	Integer tamaño
	String tipo
	Integer idUsuario
	Date lastUpdated
	String ipTerminal

	static belongsTo = [idSolicitud:Solicitud]

	static mapping = {
		id generator: "increment"
		tamaño column: "tamanio"
		idSolicitud column: "id_solicitud"
		lastUpdated column: "fechaModificacion"
		version "modificacion"
	}

	static constraints = {
		idSolicitud nullable: true
		datos nullable: true, display:false, editable:false, maxSize:1073741824
		nombre nullable: true, display:false, editable:false
		tamaño nullable: true, display:false, editable:false
		tipo nullable: true, maxSize: 20
		idUsuario nullable: true
		lastUpdated nullable: true, display:false, editable:false
		ipTerminal nullable: true, maxSize: 15, display:false, editable:false
	}

  String toString() {
    nombre
  }

}
