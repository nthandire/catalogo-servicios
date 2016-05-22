package mx.gob.inr.catservicios


class EnviarCorreoEncuestaJob {
    def FirmadoService
    // un día = 1000 ms * 60 s * 60 m * 24 h = 86,400 * 1000
    static triggers = {
      // simple name: 'Nuevo correo de encuesta', repeatInterval: 2000, repeatCount: 5
      // startDelay: 60000,
      //: 86400 * 1000 // cada día
    }

    def execute(context) {
      log.debug("va el mensaje desde el job ${new Date()}")
      log.debug("context = ${context}")
      def data = context.getMergedJobDataMap()
      log.debug("data = ${data}")
      FirmadoService.mandarMensaje(data)
    }
}
