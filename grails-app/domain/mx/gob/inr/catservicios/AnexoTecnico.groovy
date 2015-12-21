package mx.gob.inr.catservicios

class AnexoTecnico {

	String descripcion

	static mapping = {
    table 'cat_tipoanexotecnico'
    id column: "id_tipoanexotecnico", generator: "increment"
		descripcion column: "des_tipoanexotecnico"
		version false
    datasource "almacen"
	}

	static constraints = {
		descripcion nullable: true, maxSize: 15
	}

  String toString() {
    descripcion
  }

}
