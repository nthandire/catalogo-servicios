package mx.gob.inr.catservicios


class EnviarCorreoEncuestaJob {
//     def FirmadoService
    static triggers = {
      //      vacio
      // simple name: 'Nuevo correo de encuesta', repeatInterval: 2000, repeatCount: 5
    }

    def execute(context) {
      // log.debug("va el mensaje desde el job ${new Date()}")
//       log.debug("context = ${context}")
//       def data = context.getMergedJobDataMap()
//       log.debug("data = ${data}")
//       FirmadoService.mandarMensaje(data)
    }
}
