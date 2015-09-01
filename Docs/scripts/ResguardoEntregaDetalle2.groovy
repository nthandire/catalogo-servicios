import mx.gob.inr.catservicios.*



def resguardos = ResguardoEntrega.findAllByCodigoLike("515%")

resguardos.each{
  println "$it.id, $it.codigo, $it.comentarios, ${it.desModelo?:" "}   "
}



        def query =
          "from ResguardoEntregaDetalle d,                 " +
          "     ResguardoEntrega r                         " +
          " where r.id = d.idResguardo                     "
          //+
          //"   and r.codigo like '515%'                     "

println query

def detalles = ResguardoEntregaDetalle.executeQuery(query)
println "detalles = $detalles"

        def equipos = ResguardoEntregaDetalle.executeQuery(
            "select count(*) " + query
        )

println "equipos = $equipos"
