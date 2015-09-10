package mx.gob.inr.catservicios

class CatSistema {

	String desSistema

  static hasMany = [incidentes: Incidente]

	static mapping = {
		id column: "id_sistema", generator: "increment"
		version false
	}

	static constraints = {
		desSistema nullable: true, maxSize: 50
	}
}
