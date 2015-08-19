import mx.gob.inr.catservicios.*

println Cat_serv.list()

      def tiempoMinutos = new Cat_tiempo(descripcion: 'Minutos')
      tiempoMinutos.save(flush: true)
      new Cat_tiempo(descripcion: 'Horas').save()
      def tiempoDias = new Cat_tiempo(descripcion: 'Días')
      tiempoDias.save(flush: true)
      new Cat_tiempo(descripcion: 'Semanas').save(flush: true)
      def respDGAIT = new Cat_servResp(descripcion: 'DGAIT')
      respDGAIT.save(flush: true)
      def servRespMS = new Cat_servResp(descripcion: 'MS')
      servRespMS.save(flush: true)
      def servRespWM = new Cat_servResp(descripcion: 'WM')
      servRespWM.save(flush: true)
      def servRespPROV = new Cat_servResp(descripcion: 'PROV')
      servRespPROV.save(flush: true)
      def servRespSubdireccion = new Cat_servResp(descripcion: 'Subdireccion')
      servRespSubdireccion.save(flush: true)
      def servRespDireccion = new Cat_servResp(descripcion: 'Dirección/ Subdireccion')
      servRespDireccion.save(flush: true)
      def cobTODAS = new Cat_servCob(descripcion: 'TODAS LAS AREAS')
      cobTODAS.save(flush: true)
      new Cat_servCob(descripcion: 'HOSPITALIZACION').save()
      new Cat_servCob(descripcion: 'ADMINISTRATIVAS').save()
      new Cat_servCob(descripcion: 'SUSTANTIVAS').save(flush: true)
      def servCatVoceoGeneral = new Cat_servCat(categoria: 'Voceo General',
        descripcion: 'Reparación de bocinas y  control de volumen así como Comunicados mediante el sistema de voceo general',
        servResp:respDGAIT, valoracion: 2, disponibilidad: 90, estado: 'A',
        servCob:cobTODAS)
      servCatVoceoGeneral.save(flush: true)
println "primera"
println servCatVoceoGeneral
      def servSubBocinas = new Cat_servSub(descripcion: 'Bocinas', servCat: servCatVoceoGeneral)
      servSubBocinas.save(flush: true)
println "segunda"
println (servSubBocinas)
      def serv = new Cat_serv(servSub:servSubBocinas,
        descripcion: 'El sonido tiene el volumen muy alto', portal:true,
        incidente:true, servResp1:servRespMS, servResp2:respDGAIT, servResp3:respDGAIT,
        tiempo1:30, tiempo2:60, tiempo3:1,
        unidades1:tiempoMinutos, unidades2:tiempoMinutos, unidades3:tiempoDias,
        impacto:3, plantilla: 'plantilla general', idUsuario:3, estadoServ:'A', 
        ipTerminal:'192.168.16.59', lastUpdated: new Date())
      serv.save(flush: true, failOnError: true)
println "tercera"
println (serv)

      new CatPrograma(desPrograma: 'Mantenimiento', estadoPrograma:'A').save()
      new CatPrograma(desPrograma: 'Aprovisionamiento', estadoPrograma:'A').save()

println CatPrograma.list()

//def solicitudInstancia = new Solicitud(numeroSolicitud:23, fechaSolicitud:new Date())
//solicitudInstancia.numeroSolicitud = 23
//solicitudInstancia.save(flush: true, failOnError: true)

//println Solicitud.list()

/*
    Date fechaSolicitud
    Integer numeroSolicitud
    Character estadoSolicitud
    String justificacion
    Integer idSolicitante
    Integer idAutoriza
    Date fechaAutoriza
    Integer idVb
    Date fechaVb
    Integer p01
    Integer p02
    Integer p03
    Integer p04
    Date fechaModificacion
    String ipTerminal

*/