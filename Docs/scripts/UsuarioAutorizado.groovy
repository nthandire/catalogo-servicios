import mx.gob.inr.catservicios.*

//println UsuarioAutorizado.findAllAutorizaByAreaAndEstado(area,'A' as char)

//println UsuarioAutorizado.findAllAutorizaByAreaAndEstado("DGAIT",'A' as char).collect{it.id}

//println UsuarioAutorizado.findAllByAreaAndEstado("DGAIT",'A' as char)

//println UsuarioAutorizado.findAllByArea("DGAIT")

//println UsuarioAutorizado.list()

//println UsuarioAutorizado.get(9574)

def area = 'DGAIT'

//def miembros = UsuarioAutorizado.findAllByAreaAndEstado(area,'A' as char).collect{it.id}

println UsuarioAutorizado.list()