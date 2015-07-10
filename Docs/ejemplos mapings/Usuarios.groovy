package inr.gob.mx

class Usuarios {

	transient springSecurityService

	String username
	String password
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired
	String nombre
	String apellidoPaterno
	String apellidoMaterno
	
	static constraints = {	
		username blank: false, unique: true
		password blank: false		
	}

	static mapping = {
		id column:'id_user'
		username column: 'login'
		password column: 'passwordc'
		version false
		table "usuarios"
		datasource 'soptec'
	}

	Set<Perfil> getAuthorities() {
		UsuariosPerfil.findAllByUsuarios(this).collect { it.perfil } as Set
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
		"$nombre $apellidoPaterno $apellidoMaterno"
	}
}
