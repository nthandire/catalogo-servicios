package mx.gob.inr.catservicios

class ResguardoEntregaDetalle {

	Long inventario
	Integer consecutivo
	String descripcion
	String observaciones
	String serie
	Character estatusBien
	Character estatusAsignacion
	Integer idBaja
	Character statusSoptec
	Integer idTipoanexotecnico
	String obsBaja
	Date fechaBaja
	Integer idEmpleado
	Integer idUsuario
	Date lastUpdated
	String ipTerminal
	Long idMarca
	String desModelo

	static belongsTo = [idResguardo:ResguardoEntrega]

	static mapping = {
		id column: "id_resguardoentregadetalle", generator: "increment"
		idResguardo column: "id_resguardo"
		lastUpdated column: "fecha_modificacion"
		version "modificacion"
		datasource "almacen"
	}

	static constraints = {
		descripcion nullable: true, maxSize: 250
		observaciones nullable: true, maxSize: 250
		serie nullable: true, maxSize: 50
		estatusBien nullable: true, maxSize: 1
		estatusAsignacion nullable: true, maxSize: 1
		idBaja nullable: true
		statusSoptec nullable: true, maxSize: 1
		idTipoanexotecnico nullable: true
		obsBaja nullable: true, maxSize: 250
		fechaBaja nullable: true
		idEmpleado nullable: true
		idUsuario nullable: true
		lastUpdated nullable: true
		ipTerminal nullable: true, maxSize: 15
		idMarca nullable: true
		desModelo nullable: true, maxSize: 50
	}

  String toString() {
    "$inventario : $descripcion"
  }

}
