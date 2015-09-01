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

def detalles = ResguardoEntregaDetalle.executeQuery("from ResguardoEntregaDetalle d where exists( from ResguardoEntrega r where r.id = d.idResguardo and r.codigo like ?)", "515%")

detalles.each {
  println "$it.inventario, $it.consecutivo, $it.descripcion"
}


