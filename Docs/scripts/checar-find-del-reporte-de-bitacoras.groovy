import mx.gob.inr.catservicios.*

def inicio = Date.parse('yy/MM/dd', '15/08/01')
inicio[Calendar.HOUR_OF_DAY] = 0
inicio[Calendar.MINUTE] = 0
println inicio
def fin = Date.parse('yy/MM/dd', '15/08/03')
fin[Calendar.HOUR_OF_DAY] = 23
fin[Calendar.MINUTE] = 59
println fin


def lista = Cat_bitacora.findAllByDateCreatedGreaterThanEqualsAndDateCreatedLessThanEquals(inicio, fin)

lista.each {
  println "${it.descripcion} - ${it.lastUpdated}"
}

println "-----------------------------------------------"

def lista2 = Cat_bitacora.list()

lista2.each {
  println "${it.descripcion} - ${it.lastUpdated}"
}