package mx.gob.inr.catservicios

class Rol {

	String authority
  boolean perfil_bi = false

  // // Long version
  // Long getVersion() {
  //   0
  // }
  
	static mapping = {
    table 'perfil'
    id column:'idperfil' //, type:'integer'
    authority column:'desc_perfil', maxSize:50
		cache true
    version false
		datasource "seguridad"
	}

	static constraints = {
		authority blank: false, unique: true
    perfil_bi display:false, editable:false, nullable: true
	}

  String toString() {
    authority
  }

}
