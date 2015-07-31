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
    
    def rpthojaIncidente  = new RptHojaIncidente(
      num_solicitud: 333,
      fecha: new Date(),
      categoria: "Servicios de TIC para personal de nuevo ingreso",
      subcategoria: "Equipo informático  ",
      servicio: "Servicio de CCTV",
      descripcion: "una nueva Descripción: This document is taken from Luke Hoban's excellent es6features repo. Go give it a star on ",
      observaciones: 'Life hacks are pretty handy. People have managed to figure out all kinds of ways of making everyday tasks just that little bit easier. Many of the life hacks are so clever, you’ll wish that you had thought of them first. On the whole, most of them tend to work. But what happens if life hacks are put into the wrong hands, like the hands of someone a little bit dumb? Or what happens if the inventor had good intentions, but the result is just totally ridiculous? Well buckle up, as we’re about to find out with 15 examples of total life hack fails…',
    )
    data.add(rpthojaIncidente)
    chain (controller:"jasper", action:"index", model:[data:data], params:params)
  }
}

class RptHojaIncidente {
    Integer num_solicitud
    Date fecha
    String servicio
    String categoria
    String subcategoria
    String descripcion
    String observaciones
}