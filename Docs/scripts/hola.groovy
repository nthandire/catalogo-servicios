println "hola mundo"

import groovy.time.TimeCategory


def fecha1 = null
def fecha2 = new Date()
println (fecha2 > fecha1)
println (fecha2 < fecha1)
println (fecha1 < fecha2)
println (fecha2 == fecha1)




def fecha = new Date()
println fecha
println fecha.format("YYYY")

use(TimeCategory) {
  fecha += 10.months
  fecha += 6.days
  // fecha += 5.hours
}

println fecha
println fecha.format("YYYY")

fecha[Calendar.HOUR_OF_DAY] = 23
fecha[Calendar.MINUTE] = 59
fecha[Calendar.SECOND] = 59

fecha[Calendar.YEAR] = 2015
fecha[Calendar.MONTH] = 11
fecha[Calendar.DAY_OF_MONTH] = 29

println fecha
println fecha.format("YYYY")

def lista = [1,2,3,4,5,6]
lista.sort{ -it }
println lista
