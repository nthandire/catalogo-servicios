package mx.gob.inr.catservicios

class Usuario {

	transient springSecurityService

  String username
  String password
  char estatus
  boolean enabled
  boolean accountExpired
  boolean accountLocked
  boolean passwordExpired
  String nombre
  String paterno
  String materno
	String extension

  String getNombreMostrar() {
    toString()
  }

	static constraints = {
		username blank: false, unique: true, nullable: true
		password blank: false, nullable: true
		estatus nullable: true, inList: [(char)'A',(char)'B']
		accountExpired nullable: true
		accountLocked nullable: true
    passwordExpired nullable: true
    nombre nullable: true
    paterno nullable: true
		materno nullable: true
    extension nullable: true, maxSize: 6
	}

	static mapping = {
		id column:'idusuario', generator: "increment"
		username column:'rfc'
		//password column: '`password`'
		estatus length: 1, columnDefinition: 'char(1)'
		version false
		datasource "seguridad"
	}

	Set<Rol> getAuthorities() {
		UsuarioRol.findAllByUsuario(this).collect { it.rol } as Set
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService.encodePassword(password)
	}

  String toString() {
    nombre? nombre + (paterno ? " " + paterno : "") + (materno ? " " + materno : "") : username
  }

}
