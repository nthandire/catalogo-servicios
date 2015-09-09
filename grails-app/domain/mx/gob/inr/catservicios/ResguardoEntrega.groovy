package mx.gob.inr.catservicios

class ResguardoEntrega {

	Date fechaMinuta
	String comentarios
	Integer idUsuario
	String ipTerminal
	Integer idArea
	Integer idRecibe
	Integer idEntrega
	Integer idEvatecno
	String nombreEntrega
	String recibe
	Long idEntradadetalle
	Integer numeroResguardoentrega
	Date lastUpdated
	Integer umis
	Integer idPais
	Long idBienes
	String codigo
	String idAlmacen
	String desModelo
	Long idMarca

	static hasMany = [detalles: ResguardoEntregaDetalle]

	static mapping = {
		id column: "id_resguardo", generator: "increment"
		lastUpdated column: "fecha_modificacion"
		version "modificacion"
		datasource "almacen"
	}

	static constraints = {
		fechaMinuta nullable: true
		comentarios nullable: true
		idUsuario nullable: true
		ipTerminal nullable: true, maxSize: 15
		idArea nullable: true
		idRecibe nullable: true
		idEntrega nullable: true
		idEvatecno nullable: true
		nombreEntrega nullable: true, maxSize: 150
		recibe nullable: true, maxSize: 250
		lastUpdated nullable: true
		umis nullable: true
		idPais nullable: true
		idBienes nullable: true
		codigo nullable: true, maxSize: 30
		idAlmacen nullable: true, maxSize: 2
		desModelo nullable: true, maxSize: 50
		idMarca nullable: true
	}
}
