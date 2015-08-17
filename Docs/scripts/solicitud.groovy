import mx.gob.inr.catservicios.*
/*
      new Cat_tiempo(descripcion: 'Minutos').save()
      new Cat_tiempo(descripcion: 'Horas').save()
      new Cat_tiempo(descripcion: 'Días').save()
      new Cat_tiempo(descripcion: 'Semanas').save(flush: true)
*/

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
      def servSubBocinas = new Cat_servSub(descripcion: 'Bocinas', servCat: servCatVoceoGeneral)
      servSubBocinas.save(flush: true)


      def serv = new Cat_serv(servSub:servSubBocinas,
        descripcion: 'El sonido tiene el volumen muy alto', portal:true,
        incidente:true, servResp1:servRespMS, servResp2:respDGAIT, servResp3:respDGAIT,
        tiempo1:30, tiempo2:60, tiempo3:1,
        unidades1:tiempoMinutos, unidades2:tiempoMinutos, unidades3:tiempoDias,
        impacto:3, plantilla: 'plantilla general', idUsuario:4, estadoServ:'A', 
        ipTerminal:'192.168.16.59', lastUpdated: new Date())
      serv.save(flush: true)


println Cat_tiempo.list()
println Cat_servResp.list()
println Cat_servCob.list()
println Cat_servCat.list()
println Cat_servSub.list()


println servSubBocinas
println servRespMS
println respDGAIT
println tiempoMinutos
println tiempoDias

println new Date()


println serv