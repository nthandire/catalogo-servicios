package mx.gob.inr.catservicios

class SolicitudDetalle {

	Cat_serv idServ
	Cat_servCat idServcat
	CatPrograma idPrograma
	Long idResguardoentregadetalle
	Character estado
	String descripcion
	String solucion
	Integer idTecnico
  Integer impacto = 0
	Date fechaSolucion
	String descripcionTecnica

	static belongsTo = [idSolicitud:Solicitud]

	static mapping = {
		id column: "id_solicituddetalle", generator: "increment"
		idServ column: "id_serv"
		idServcat column: "id_servcat"
		idPrograma column: "id_programa"
		idSolicitud column: "id_solicitud"
		estado column: "estado_solictuddetalle"
		version false
	}

	static constraints = {
		idSolicitud nullable: true, editable:false
		idServ nullable: true
		idResguardoentregadetalle nullable: true
		estado nullable: true, maxSize: 1, inList: [(char)'A',(char)'I']
		descripcion nullable: true, maxSize: 3000
		solucion nullable: true, maxSize: 3000
		idTecnico nullable: true, editable:false
    impacto nullable: true, inList: [0, 1, 2, 3]
		fechaSolucion nullable: true, editable:false
		idPrograma nullable: true
		idServcat nullable: true
		descripcionTecnica nullable: true, maxSize: 2500
	}

  String toString() {
    idServcat
  }

}
