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


def inicio = Date.parse('d/M/yy HH:mm', '  1/7/15  '.trim() + ' 00:00' )
println "inicio ${inicio}"
def fin = Date.parse('d/M/yy HH:mm', '31/7/15 23:59' )
println "fin ${fin}"

def lista = Cat_bitacora.findByDateCreatedGreaterThanEqualsAndDateCreatedLessThanEquals(inicio, fin)





lista.each{it ->
def bitacora = new RptBitacora(
    num_solicitud: it.no_solicitud,
    fecha: it.dateCreated,
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