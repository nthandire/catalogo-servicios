package mx.gob.inr.catservicios

class Firmadigital {

	String passwordfirma
	String nombre
	String tipo
	Long tamanio
	byte[] datos
	Integer bloqueada
	Integer intentosfallidos

	static mapping = {
		id column: "idusuario", generator: "assigned"
		version false
		datasource "seguridad"
	}

	static constraints = {
		passwordfirma nullable: true, maxSize: 25
		nombre maxSize: 200
		tipo maxSize: 50
		intentosfallidos nullable: true
	}
}
