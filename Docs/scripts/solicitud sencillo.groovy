import mx.gob.inr.catservicios.*

Solicitud.list().each {
  println "$it.id $it.justificacion $it.estado"
  if (it?.estado == 'F' as char) {
      println "el estado si es 'F'"
  }
}