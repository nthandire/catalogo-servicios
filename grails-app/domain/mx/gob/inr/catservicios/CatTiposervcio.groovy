package mx.gob.inr.catservicios

class CatTiposervcio {

	String desTiposervicio
	Character estado

	static mapping = {
		id column: "id_tiposervicio", generator: "increment"
		version false
	}

	static constraints = {
		desTiposervicio nullable: true, maxSize: 100
		estado nullable: true, maxSize: 1
	}
}
