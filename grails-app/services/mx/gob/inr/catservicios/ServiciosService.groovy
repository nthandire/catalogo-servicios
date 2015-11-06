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
      from ResguardoEntregaDetalle as d where d.observaciones = '?'
    """
    def query4 = """
      from ResguardoEntregaDetalle d where d.id = 2
    """
    def query5 = """
      from ResguardoEntregaDetalle
    """

    // def clist = ResguardoEntregaDetalle.createCriteria().list(query)
    // def clist = ResguardoEntregaDetalle.find(query2,[codigos, "%" + term, term],
    //                                          [max: 50])
    def clist = ResguardoEntregaDetalle.findAll(query5, [], [max: 15])
      // ResguardoEntregaDetalle.find(query5, [], [max: 50])
    log.debug("clist = ${clist}")

    def cSelectList = [] // to add each company details
    clist.each {
      def eqMap = [:] // add to map. jQuery autocomplete expects the JSON object to be with id/label/value.
      eqMap.put("serie", it['serie'])
      eqMap.put("label", "${it['inventario']} : ${it['descripcion']}")
      eqMap.put("value", it['id'])
      eqMap.put("marca", it['idMarca']) // will use this to pre-populate the Emp Id
      eqMap.put("modelo", it['desModelo'])
      eqMap.put("economico", it['inventario'])
      cSelectList.add(eqMap) // add to the arraylist
    }
    return cSelectList
  }

}
