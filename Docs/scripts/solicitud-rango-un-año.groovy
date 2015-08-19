import groovy.time.TimeCategory
def fecha = new Date().clearTime()
fecha[Calendar.MONTH] = 0
fecha[Calendar.DATE] = 1
def fecha2 = fecha.clone()

use(TimeCategory) {
  fecha2 = fecha2 + 1.years - 1.seconds
}
println fecha
println fecha2


