import mx.gob.inr.catservicios.*


class RptBitacora {
    Integer num_solicitud
    Date fecha
    String servicio
    String categoria
    String subcategoria
    String descripcion
    String observaciones
}


lista = Cat_bitacora.list()

lista.each{it ->
def bitacora = new RptBitacora(
    num_solicitud: it.no_solicitud,
    fecha: it.lastUpdated,
    servicio:it.servicio?.descripcion,
    subcategoria:it.servicio?.servSub?.descripcion,
    categoria:it.servicio?.servSub?.servCat?.categoria,
    descripcion:it.descripcion,
    observaciones:it.observaciones,
)
println "num_solicitud ${bitacora.num_solicitud}"
println "fecha ${bitacora.fecha}"
println "servicio ${bitacora.servicio}"
println "categoria ${bitacora.categoria}"
}
//println lista.descripcion
