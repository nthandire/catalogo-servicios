import mx.gob.inr.catservicios.*

//Solicitud.list()
/*
def autorizados = Solicitud.executeQuery(
//  "select id, fechaSolicitud,numeroSolicitud from Solicitud s" +
  "from Solicitud s" +
  " where s.fechaSolicitud is Not Null                  " +
  "   and fechaAutoriza is Not Null                     " +
  "   and exists(select id from SolicitudDetalle d      " +
  "               where s.id = d.idSolicitud " +
  "                 and idTecnico is null) " 
)
*/


        def query =
          "from Solicitud s                                     " +
          " where s.fechaSolicitud is Not Null                  " +
          "   and fechaAutoriza is Not Null                     " +
          "   and exists(select id from SolicitudDetalle d      " +
          "               where s.id = d.idSolicitud            " +
          "                 and idTecnico is null)              "


        def autorizados = Solicitud.executeQuery(
            "select count(*) " + query
        )[0]

println "autorizados = $autorizados"

def autorizadosList = Solicitud.executeQuery(query)

println "autorizadosList = $autorizadosList"
