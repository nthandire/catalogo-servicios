package mx.gob.inr.catservicios

class Cat_bitacoraController {
    static nombreMenu = "Bitácora de cambios del Portafolio de Servicios"
    static ordenMenu = 4

    static scaffold = Cat_bitacora

  def categoryChanged(long categoryId) {
      Cat_servCat category = Cat_servCat.get(categoryId)
      def subCategories = []
      if ( category != null ) {
          subCategories = Cat_servSub.findAllByServCat(category, [order:'id'])
      }
      render g.select(id:'servSub', name:'servSub.id',
          from:subCategories, optionKey:'id', noSelection:[null:'Seleccione una...'],
          class:"many-to-one", onchange:"subcategoryChanged(this.value)"
      )
  }

  def subcategoryChanged(long subcategoryId) {
      Cat_servSub subcategory = Cat_servSub.get(subcategoryId)
      def servicios = []
      if ( subcategory != null ) {
          servicios = Cat_serv.findAllByServSub(subcategory, [order:'id'])
      }
      render g.select(id:'servicio', name:'servicio.id',
          from:servicios, optionKey:'id', noSelection:[null:'Seleccione una...']
      )
  }

  def reporteHojaIncidente() {
    def data = []
    params.image_dir = "${servletContext.getRealPath('/images')}/"
    params.titulo = "REPORTE DE LA BITÁCORA DE CAMBIOS AL PORTAFOLIO DE SERVICIOS"
    
    println "startDate ${params.startDate}"
    println "endDate ${params.endDate}"
    def startDate = Date.parse( 'd/M/yy HH:mm', params.startDate.trim() + ' 00:00' )
    def endDate = Date.parse( 'd/M/yy HH:mm', params.endDate.trim() + ' 23:59' )
    println "startDate ${startDate}"
    println "endDate ${endDate}"

    def lista = Cat_bitacora.findByDateCreatedGreaterThanEqualsAndDateCreatedLessThanEquals(startDate, endDate)

    lista.each { it ->
      def rowBitacora = new RptBitacora (
        num_solicitud: it.no_solicitud,
        fecha: it.dateCreated,
        servicio:it.servicio?.descripcion,
        subcategoria:it.servicio?.servSub?.descripcion,
        categoria:it.servicio?.servSub?.servCat?.categoria,
        descripcion:it.descripcion,
        observaciones:it.observaciones,
      )
      data.add(rowBitacora)
    }

    chain (controller:"jasper", action:"index", model:[data:data], params:params)
  }
}

class RptBitacora {
    Integer num_solicitud
    Date fecha
    String servicio
    String categoria
    String subcategoria
    String descripcion
    String observaciones
}