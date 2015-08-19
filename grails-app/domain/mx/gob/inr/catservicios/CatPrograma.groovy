package mx.gob.inr.catservicios

class CatPrograma {

	String desPrograma
	Character estadoPrograma

	static mapping = {
		id column: "id_programa", generator: "increment"
		version false
	}

	static constraints = {
		desPrograma nullable: true
		estadoPrograma nullable: true, maxSize: 1, inList: [(char)'A',(char)'I']
	}

  String toString() {
    desPrograma
  }

}
