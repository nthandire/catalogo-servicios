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

}