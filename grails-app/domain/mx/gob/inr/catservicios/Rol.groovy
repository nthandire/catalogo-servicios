package mx.gob.inr.catservicios

class Rol {

	String authority

	static mapping = {
		cache true
		datasource "seguridad"
	}

	static constraints = {
		authority blank: false, unique: true
	}

  String toString() {
    authority
  }

}
