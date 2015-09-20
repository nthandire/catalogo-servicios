import mx.gob.inr.catservicios.*

//println UsuarioAutorizado.findAllAutorizaByAreaAndEstado(area,'A' as char)

println UsuarioAutorizado.findAllAutorizaByAreaAndEstado("DGAIT",'A' as char).collect{it.id}

println UsuarioAutorizado.findAllByAreaAndEstado("DGAIT",'A' as char)