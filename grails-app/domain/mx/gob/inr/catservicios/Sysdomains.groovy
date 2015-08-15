package mx.gob.inr.catservicios

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder

class Sysdomains implements Serializable {

	Integer id
	String owner
	String name
	Short type

	int hashCode() {
		def builder = new HashCodeBuilder()
		builder.append id
		builder.append owner
		builder.append name
		builder.append type
		builder.toHashCode()
	}

	boolean equals(other) {
		if (other == null) return false
		def builder = new EqualsBuilder()
		builder.append id, other.id
		builder.append owner, other.owner
		builder.append name, other.name
		builder.append type, other.type
		builder.isEquals()
	}

	static mapping = {
		id composite: ["id", "owner", "name", "type"]
		version false
	}

	static constraints = {
		owner nullable: true, maxSize: 32
		name nullable: true, maxSize: 128
		type nullable: true
	}
}
