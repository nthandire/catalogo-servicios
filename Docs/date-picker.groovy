import mx.gob.inr.catservicios.*

def fecha = new Date()
println "fecha ${fecha}"
def mes = fecha[Calendar.MONTH]
println "mes ${mes}"
def dia = fecha[Calendar.DATE]
println "dia ${dia}"
fecha[Calendar.DATE] = 1
dia = fecha[Calendar.DATE]
println "dia modificado ${dia}"

String newDate = fecha.format( 'M/d/yy' )
println "fecha formateada ${newDate}"