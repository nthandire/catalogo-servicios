package mx.gob.inr.catservicios

class Requestmap {

	String url
	String configAttribute

	static mapping = {
		cache true
//		datasource "seguridad"
	}

	static constraints = {
		url blank: false, unique: true
		configAttribute blank: false
	}
}
