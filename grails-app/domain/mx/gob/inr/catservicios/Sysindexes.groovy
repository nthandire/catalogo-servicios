package mx.gob.inr.catservicios

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder

class Sysindexes implements Serializable {

	String idxname
	String owner
	Integer tabid
	Character idxtype
	Character clustered
	Short part1
	Short part2
	Short part3
	Short part4
	Short part5
	Short part6
	Short part7
	Short part8
	Short part9
	Short part10
	Short part11
	Short part12
	Short part13
	Short part14
	Short part15
	Short part16
	Short levels
	Integer leaves
	Integer nunique
	Integer clust

	int hashCode() {
		def builder = new HashCodeBuilder()
		builder.append idxname
		builder.append owner
		builder.append tabid
		builder.append idxtype
		builder.append clustered
		builder.append part1
		builder.append part2
		builder.append part3
		builder.append part4
		builder.append part5
		builder.append part6
		builder.append part7
		builder.append part8
		builder.append part9
		builder.append part10
		builder.append part11
		builder.append part12
		builder.append part13
		builder.append part14
		builder.append part15
		builder.append part16
		builder.append levels
		builder.append leaves
		builder.append nunique
		builder.append clust
		builder.toHashCode()
	}

	boolean equals(other) {
		if (other == null) return false
		def builder = new EqualsBuilder()
		builder.append idxname, other.idxname
		builder.append owner, other.owner
		builder.append tabid, other.tabid
		builder.append idxtype, other.idxtype
		builder.append clustered, other.clustered
		builder.append part1, other.part1
		builder.append part2, other.part2
		builder.append part3, other.part3
		builder.append part4, other.part4
		builder.append part5, other.part5
		builder.append part6, other.part6
		builder.append part7, other.part7
		builder.append part8, other.part8
		builder.append part9, other.part9
		builder.append part10, other.part10
		builder.append part11, other.part11
		builder.append part12, other.part12
		builder.append part13, other.part13
		builder.append part14, other.part14
		builder.append part15, other.part15
		builder.append part16, other.part16
		builder.append levels, other.levels
		builder.append leaves, other.leaves
		builder.append nunique, other.nunique
		builder.append clust, other.clust
		builder.isEquals()
	}

	static mapping = {
		id composite: ["idxname", "owner", "tabid", "idxtype", "clustered", "part1", "part2", "part3", "part4", "part5", "part6", "part7", "part8", "part9", "part10", "part11", "part12", "part13", "part14", "part15", "part16", "levels", "leaves", "nunique", "clust"]
		version false
	}

	static constraints = {
		idxname nullable: true, maxSize: 128
		owner nullable: true, maxSize: 32
		tabid nullable: true
		idxtype nullable: true, maxSize: 1
		clustered nullable: true, maxSize: 1
		part1 nullable: true
		part2 nullable: true
		part3 nullable: true
		part4 nullable: true
		part5 nullable: true
		part6 nullable: true
		part7 nullable: true
		part8 nullable: true
		part9 nullable: true
		part10 nullable: true
		part11 nullable: true
		part12 nullable: true
		part13 nullable: true
		part14 nullable: true
		part15 nullable: true
		part16 nullable: true
		levels nullable: true
		leaves nullable: true
		nunique nullable: true
		clust nullable: true
	}
}
