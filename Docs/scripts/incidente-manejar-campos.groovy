import mx.gob.inr.catservicios.*

def incidente = Incidente.get(2)

def cadena = "prueba"
def nivel = 2

def campo = "solucionNivel" + nivel
println campo

incidente."$campo" = cadena

incidente.save(flush: true)

println "id = $incidente.id, solucionNivel2 = $incidente.solucionNivel2 "
