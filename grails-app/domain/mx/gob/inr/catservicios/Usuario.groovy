package mx.gob.inr.catservicios

class Usuario {

	transient springSecurityService

	String username
	String password
	char estatus
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	// boolean enabled
	boolean getEnabled() {
		estatus == 'A'
	}
	
	private void setEnabled(boolean val) {
		if (val)
			estatus = 'A'
		else
			estatus = 'B'
	}


	static constraints = {
		username blank: false, unique: true, nullable: true
		password blank: false, nullable: true
		estatus nullable: true
		accountExpired nullable: true
		accountLocked nullable: true
		passwordExpired nullable: true
	}

	static mapping = {
		id column:'idusuario', generator: "increment"    //, type:'integer'
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
    username
  }

}
