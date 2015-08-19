package mx.gob.inr.catservicios

class SolicitudDetalle {

	Cat_serv idServ
	Cat_servCat idServcat
	CatPrograma idPrograma
	Long idResguardoentregadetalle
	Character estadoSolictuddetalle
	String descripcion
	String solucion
	Integer idTecnico
	Date fechaSolucion
	String descripcionTecnica

	static belongsTo = [idSolicitud:Solicitud]

	static mapping = {
		id column: "id_solicituddetalle", generator: "increment"
		version false
	}

	static constraints = {
		idSolicitud nullable: true, editable:false
		idServ nullable: true
		idResguardoentregadetalle nullable: true
		estadoSolictuddetalle nullable: true, maxSize: 1, inList: [(char)'A',(char)'I']
		descripcion nullable: true, maxSize: 3000
		solucion nullable: true, maxSize: 3000
		idTecnico nullable: true, editable:false
		fechaSolucion nullable: true, editable:false
		idPrograma nullable: true
		idServcat nullable: true
		descripcionTecnica nullable: true, maxSize: 2500
	}
}
