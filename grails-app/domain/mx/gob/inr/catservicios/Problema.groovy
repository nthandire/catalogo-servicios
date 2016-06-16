package mx.gob.inr.catservicios

import java.text.DecimalFormat

class Problema {

  String fuente
  Long idFuente
  Date fechaProblema
  Integer folio
  String observaciones
  String solucion
  Date fechaSolucion
  String resolvio
  Integer idUsuario
  Date lastUpdated
  String ipTerminal

  static mapping = {
    id column: "id_problema", generator: "increment"
    lastUpdated column: "fecha_modificacion"
    version "modificacion"
  }

  static constraints = {
    fuente nullable: true, maxSize: 50
    idFuente nullable: true
    fechaProblema nullable: true
    folio nullable: true
    observaciones nullable: true, maxSize: 3000
    solucion nullable: true, maxSize: 3000
    fechaSolucion nullable: true
    resolvio nullable: true
    idUsuario nullable: true
    lastUpdated nullable: true
    ipTerminal nullable: true, maxSize: 15
  }

  String toString() {
    "${folio}/${fechaProblema[Calendar.YEAR]}"
  }

  static moneyform = new DecimalFormat("00000")

  String paraOrdenar() {
    "${fechaProblema[Calendar.YEAR]}${moneyform.format(folio)}"
  }

}
