package mx.gob.inr.catservicios

class IncidenteArchivoadjunto {

	byte[] datos
	String nombre
	Integer tamaño
	String tipo
	Integer idUsuario
	Date lastUpdated
	String ipTerminal

  static belongsTo = [idIncidente:Incidente]

	static mapping = {
		id generator: "increment"
    tamaño column: "tamanio"
		lastUpdated column: "fecha_modificacion"
		version "modificacion"
	}

	static constraints = {
		idIncidente nullable: true
		datos nullable: true, display:false, editable:false, maxSize:1073741824
		nombre nullable: true
		tamaño nullable: true
		tipo nullable: true, maxSize: 20
		idUsuario nullable: true
		lastUpdated nullable: true
		ipTerminal nullable: true, maxSize: 15
	}

  String toString() {
    nombre
  }

}
