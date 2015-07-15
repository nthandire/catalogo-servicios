package mx.gob.inr.catservicios

class Cat_serv {

  Cat_servSub servSub
  String descripcion
  Boolean portal
  Boolean incidente
  Boolean solicitud
  Boolean problema
  Cat_servResp servResp1
  Cat_servResp servResp2
  Cat_servResp servResp3
  Integer tiempo1
  Integer tiempo2
  Integer tiempo3
  Cat_tiempo unidades1 // = Cat_tiempo.findByTitleDescripcion("Hora")
  Cat_tiempo unidades2
  Cat_tiempo unidades3
  Integer impacto = 1
  String authoriza // TODO: ¿se necesita?
  Cat_servResp servResp
  String plantilla
  String observaciones

  static constraints = {
    servSub()
    descripcion(maxSize:255, blank:false)
    portal widget: 'checkbox'
    incidente widget: 'checkbox'
    solicitud widget: 'checkbox'
    problema widget: 'checkbox'
    servResp1()
    tiempo1 min: 0
    unidades1()
    servResp2 nullable:true
    tiempo2 min: 0, nullable:true
    unidades2()
    servResp3 nullable:true
    tiempo3 min: 0, nullable:true
    unidades3()
    impacto min: 0, max:3
    authoriza(nullable:true)
    servResp()
    plantilla(maxSize:500, nullable:true)
    observaciones(maxSize:1000, nullable:true)
  }

  static mapping = {
    id column:'id_serv'
    servSub column:'id_servSub'
    servResp1 column:'id_servresp1'
    servResp2 column:'id_servresp2'
    servResp3 column:'id_servresp3'
    unidades1 column:'id_tiempo1'
    unidades2 column:'id_tiempo2'
    unidades3 column:'id_tiempo3'
    impacto defaultValue: "1"
    servResp column:'id_servresp'
    version false
  }

  String toString() {
    descripcion
  }

}
// TODO: quitar el boton de "Eliminar" en todas las pantallas 