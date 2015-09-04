import mx.gob.inr.catservicios.*

println Solicitud.list()
def params = [:]
params['max'] = 3
params['offset'] = 3
println params


def userID = 9574

def lista = Solicitud.findAllByIdSolicitante((Integer)userID, params)
//def lista = Solicitud.list()

println lista

params['offset'] = 4

println Solicitud.findAllByIdSolicitante((Integer)userID, params)
