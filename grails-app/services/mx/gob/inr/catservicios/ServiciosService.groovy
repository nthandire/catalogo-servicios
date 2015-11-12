package mx.gob.inr.catservicios

import grails.plugins.springsecurity.SpringSecurityService;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional

class ServiciosService {
  static transactional = true
  def serviciosService

  @Transactional(readOnly = true)
  def listarEquipo(params){
    def term = params.term.toUpperCase()
    log.debug("en listarEquipo, term = $term")

    def codigos = ResguardoEntrega.findAllByCodigoLike("515%").collect {it.id}
    log.debug("número de codigos = ${codigos.size()}")

      // TODO. aplicar un orden -  order("inventario","asc")
    def query =
       "  from ResguardoEntregaDetalle as d   " +
       " where d.id in (:codigos)             " +
       "   and (serie like :serie             " +
       "        or inventario = :inventario)  "
    log.debug("query = $query")

    log.debug("inventario = ${((term.isNumber())?term.toLong():0)}")
    def clist = ResguardoEntregaDetalle.
      findAll(query, [codigos: codigos, serie: "%$term%",
                      inventario:term.isNumber()? term.toLong() : new Long(0)],
                      [max: 15])
    log.debug("clist = ${clist}")

    def cSelectList = [] // cada uno de los resultados
    clist.each {
      def eqMap = [:] // add to map. jQuery autocomplete expects the JSON object to be with id/label/value.
      eqMap.put("serie", it['serie'])
      eqMap.put("label", "${it['inventario']} : ${it['serie']} : ${it['descripcion']}")
      eqMap.put("value", it['id'])
      def marca = serviciosService.descMarca(it['idMarca'])
      eqMap.put("marca", marca)
      eqMap.put("modelo", it['desModelo'])
      eqMap.put("economico", it['inventario'])
      eqMap.put("equipo", it['descripcion'])
      cSelectList.add(eqMap)
    }
    return cSelectList
  }

  def nombreEquipo(id){
    log.debug("en nombreEquipo")

    def equipo = ""
    if (id) {
      equipo = ResguardoEntregaDetalle.get(id).toString()
    }
    log.debug("equipo = $equipo")
    equipo
  }

  def descMarca(id){
    def marca = ""
    if (id) {
      marca = Marca.get(id).toString()
    }
    marca
  }

}
