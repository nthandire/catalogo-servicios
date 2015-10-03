package mx.gob.inr.catservicios

import grails.plugins.springsecurity.SpringSecurityService;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional

class ServiciosService {

  static transactional = true

  @Transactional(readOnly = true)
  def listarEquipo(params){
    log.debug("en listarEquipo")
    def term = "${params.term}%"

    def codigos = ResguardoEntrega.findAllByCodigoLike("515%").collect {it.id}
    log.debug("número de codigos = ${codigos.size()}")

    def query = {
      'in'("idResguardo", codigos)
      or {
        like("to_char(inventario)", "%" + term) // term is the parameter send by jQuery autocomplete
        like("serie", term)
      }
      order("inventario","asc")
      maxResults(50)
      projections { // good to select only the required columns.
        property("id")
        property("idMarca")
        property("desModelo")   // 2
        property("inventario")  // 3
        property("descripcion") // 4
        property("serie")
      }
    }

    def query3 = """
      from ResguardoEntregaDetalle
     where idResguardo in (?)
       and (serie like ?
            or to_char(inventario) like ?)
    """
    def query2 = """
      from ResguardoEntregaDetalle as d where d.observaciones = ?
    """
    // def clist = ResguardoEntregaDetalle.createCriteria().list(query)
    // def clist = ResguardoEntregaDetalle.find(query2,[codigos, "%" + term, term],
    //                                          [max: 50])
    def clist = ResguardoEntregaDetalle.find(query2, ['CARGA IRI'],
                                             [max: 50])

    def cSelectList = [] // to add each company details
    clist.each {
      def eqMap = [:] // add to map. jQuery autocomplete expects the JSON object to be with id/label/value.
      eqMap.put("serie", it[5])
      eqMap.put("label", "${it[3]} : ${it[4]}")
      eqMap.put("value", it[0])
      eqMap.put("marca", it[1]) // will use this to pre-populate the Emp Id
      eqMap.put("modelo", it[2])
      eqMap.put("economico", it[3])
      cSelectList.add(eqMap) // add to the arraylist
    }
    return cSelectList
  }

}
