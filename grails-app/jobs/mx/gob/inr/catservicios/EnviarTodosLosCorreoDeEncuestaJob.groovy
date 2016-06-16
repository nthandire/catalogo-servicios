package mx.gob.inr.catservicios


class EnviarTodosLosCorreoDeEncuestaJob {
    def FirmadoService
    static triggers = {
      // desarrollo
      cron name: 'miTrigger', cronExpression: "0 0/10 * ? * *"
      // cron name: 'miTrigger', cronExpression: "0 0/5 * ? * MON-FRI"
      // un día = 1000 ms * 60 s * 60 m * 24 h = 86,400 * 1000
      // cron name: 'miTrigger', cronExpression: "45 45 5 ? * mon-fri" // produccion
      // simple name: 'Nuevo correo de encuesta', repeatInterval: 2000, repeatCount: 5
      // startDelay: 60000,
      //: 86400 * 1000 // cada día
    }

    def execute() {
      log.debug("va el mensaje desde el job ${new Date()}")
      FirmadoService.mandarTodosLosMensajeDeEncuestas()
    }
}
