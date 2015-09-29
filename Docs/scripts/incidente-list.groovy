import mx.gob.inr.catservicios.*

        // filtrar los de mi area
        // si soy tecnico, filtrar los que tenga asignados en este nivel

def incidentes = Incidente.findAllByEstado('A' as char)

println incidentes

def userID = 9583
def firmadoService = new FirmadoService()
def area = firmadoService._area(userID)

println area

def incidentesFiltrados = incidentes.findAll {it.idServresp == area}

println incidentesFiltrados


//incidenteInstanceList