import mx.gob.inr.catservicios.*

println SolicitudDetalle.count()
println SolicitudDetalle.list()

def solicitudInstance = Solicitud.get(1)
SolicitudDetalle.findAllByIdSolicitud(solicitudInstance).each{
    println it.descripcion
}