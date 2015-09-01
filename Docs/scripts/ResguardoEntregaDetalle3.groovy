import mx.gob.inr.catservicios.*

/*
def query = "  from ResguardoEntregaDetalle d            " +
            " where exists( from ResguardoEntrega r      " +
            "              where r.id = d.idResguardo    " +
            "                and r.codigo like ?)        "

def detalles = ResguardoEntregaDetalle.executeQuery(query, "515%")

detalles.each {
  println "$it.inventario, $it.consecutivo, $it.descripcion"
}
*/
/*
def detalles = ResguardoEntregaDetalle.executeQuery("from ResguardoEntregaDetalle d where exists( from ResguardoEntrega r where r.id = d.idResguardo and r.codigo like ?)", "515%")

detalles.each {
  println "$it.inventario, $it.consecutivo, $it.descripcion"
}
*/

/*
def query = "from SolicitudDetalle d where idTecnico is null and exists( from Solicitud s where s.id = d.idSolicitud and s.estado =  ?)"
        def detalles = SolicitudDetalle.executeQuery (
              "select count (*) " + query, 'A' as char
            )
println "numero de detalles = ${detalles}"
*/
/*
        [solicitudDetalleInstanceList: SolicitudDetalle.
            executeQuery(query, 'A' as char, params),
*/


//def query2 = "from Solicitud s where s.estado = 'A'"
//def solicitudes = SolicitudDetalle.executeQuery (
//  "select count (*) " + query2
//)
//println "numero de solicitudes = ${solicitudes}"



def query = "from SolicitudDetalle d where idTecnico is null and exists( from Solicitud s where s.id = d.idSolicitud and s.estado = 'A')"
//def query = "from SolicitudDetalle d, Solicitud s where s.id = d.idSolicitud and s.estado = 'A' and d.idTecnico is null"
def detalles = SolicitudDetalle.executeQuery (
  "select count (*) " + query
)
println "numero de detalles = ${detalles}"
/*
        [solicitudDetalleInstanceList: SolicitudDetalle.
            executeQuery(query, 'A' as char, params),
            solicitudDetalleInstanceTotal: detalles]
  */  