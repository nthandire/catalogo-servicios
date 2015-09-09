package mx.gob.inr.catservicios

class Incidente {

	Integer idSistema
	Long idResguardoentregadetalle
	Date fechaIncidente
	Integer numeroIncidente
	Character estadoIncidente
	Integer idReporta
	Integer idServ
	Integer idServfinal
	String descripcion
	Integer nivel
	Integer idServresp
	Integer idCaptura
	Integer idNivel1
	Date fechaNivel1
	Boolean firmaNivel1
	String solucionNivel1
	Date fechaSolnivel1
	Integer idAsignanivel2
	Integer idNivel2
	Date fechaNivel2
	Boolean firmaNivel2
	String solucionNivel2
	Date fechaSolnivel2
	Integer idAsignanivel3
	Integer idNivel3
	Date fechaNivel3
	Boolean firmaNivel3
	String solucionNivel3
	Date fechaSolnivel3
	Integer p01
	Integer p02
	Integer p03
	Integer p04
	Integer idPrograma
	Date lastUpdated
	String ipTerminal

	static mapping = {
		id column: "id_incidente", generator: "increment"
		lastUpdated column: "fecha_modificacion"
		version "modificacion"
	}

	static constraints = {
		idSistema nullable: true
		idResguardoentregadetalle nullable: true
		fechaIncidente nullable: true
		numeroIncidente nullable: true
		estadoIncidente nullable: true, maxSize: 1
		idReporta nullable: true
		idServ nullable: true
		idServfinal nullable: true
		descripcion nullable: true, maxSize: 3000
		nivel nullable: true
		idServresp nullable: true
		idCaptura nullable: true
		idNivel1 nullable: true
		fechaNivel1 nullable: true
		firmaNivel1 nullable: true
		solucionNivel1 nullable: true, maxSize: 3000
		fechaSolnivel1 nullable: true
		idAsignanivel2 nullable: true
		idNivel2 nullable: true
		fechaNivel2 nullable: true
		firmaNivel2 nullable: true
		solucionNivel2 nullable: true, maxSize: 3000
		fechaSolnivel2 nullable: true
		idAsignanivel3 nullable: true
		idNivel3 nullable: true
		fechaNivel3 nullable: true
		firmaNivel3 nullable: true
		solucionNivel3 nullable: true, maxSize: 3000
		fechaSolnivel3 nullable: true
		p01 nullable: true
		p02 nullable: true
		p03 nullable: true
		p04 nullable: true
		idPrograma nullable: true
		lastUpdated nullable: true
		ipTerminal nullable: true, maxSize: 15
	}
}
