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
    params.titulo = "SOLICITUD DE SERVICIO - INCIDENTE"
    
    def rpthojaIncidente  = new RptHojaIncidente()
        
    rpthojaIncidente.noIncidente = 54877
    rpthojaIncidente.fecha = new Date()
    rpthojaIncidente.atendidoPor = "Ingeniero 04"
    rpthojaIncidente.equipo = "Una laptop"
    rpthojaIncidente.marca = "Dell"
    rpthojaIncidente.modelo = "Inspire 3400"
    rpthojaIncidente.serie = "7845485834"
    rpthojaIncidente.reporto = "Secretaria 38"
    rpthojaIncidente.area = "Qirofano 03"
    rpthojaIncidente.cuerpo = "Urgencias"
    rpthojaIncidente.extension = "10248"
    rpthojaIncidente.garantia = 'SI'
    rpthojaIncidente.economico = "84756384"
    rpthojaIncidente.falla = 'teclado' + ', ' +
                              'no funciona la barra espaciadora'
    
    data.add(rpthojaIncidente)
    chain (controller:"jasper", action:"index", model:[data:data], params:params)
  }
}

class RptHojaIncidente {
    
    Integer noIncidente
    Date fecha
    String atendidoPor
    String equipo
    String marca
    String modelo
    String serie
    String reporto
    String area
    String cuerpo
    String extension
    String garantia
    String economico
    String falla
    String servicio
    String atendio
}