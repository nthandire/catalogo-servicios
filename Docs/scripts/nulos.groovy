import mx.gob.inr.catservicios.*

def userID = 9574
Solicitud.list().each {
  println "$it.id, $it.estado"
}

println "------------------- List -------------------"

Solicitud.findAllByIdSolicitanteAndEstadoNotEqual((Integer)userID, 'T' as char).each {
  println "$it.id, $it.estado"
}

println "------------------- findAllBy -------------------"

//def criterioTotal = Solicitud.createCriteria()
def solicitudes = Solicitud.withCriteria {
    projections {count()}
    eq('idSolicitante',(Integer)userID)
    or {
        and {
            ne('estado','T' as char)
            ne('estado','C' as char)
            }
        isNull('estado')
        }
}[0]
println "solicitudes = $solicitudes"

//def criterio = Solicitud.createCriteria()
def lista = Solicitud.withCriteria {
    eq('idSolicitante',(Integer)userID)
    or {
        and {
            ne('estado','T' as char)
            ne('estado','C' as char)
            }
        isNull('estado')
        }
}

lista.each {
  println "$it.id, $it.estado"
}
