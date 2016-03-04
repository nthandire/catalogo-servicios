SELECT id_solicitud,fecha_solicitud,numero_solicitud,estado_solicitud,justificacion,id_solicitante,id_autoriza,fecha_autoriza,id_vb,fecha_vb,p01,p02,p03,p04,fecha_modificacion,modificacion,ip_terminal,comentario_vb,id_revisa,fecha_revisa,encuesta,fecha_encuesta
  FROM solicitud
 where id_solicitud > 80;


SELECT id_solicitud,fecha_solicitud,numero_solicitud,estado_solicitud,justificacion,id_solicitante,id_autoriza,fecha_autoriza,id_vb,fecha_vb,p01,p02,p03,p04,fecha_modificacion,modificacion,ip_terminal,comentario_vb,id_revisa,fecha_revisa,encuesta,fecha_encuesta
  from Solicitud
 where fecha_autoriza between to_date('2016-01-01', '%Y-%m-%d') and to_date('2016-01-31', '%Y-%m-%d');
 
 
SELECT id_incidente,id_sistema,id_resguardoentregadetalle,fecha_incidente,numero_incidente,estado_incidente,id_reporta,id_serv,id_servfinal,descripcion,nivel,id_servresp,id_captura,id_nivel1,fecha_nivel1,firma_nivel1,solucion_nivel1,fecha_solnivel1,id_asignanivel2,id_nivel2,fecha_nivel2,firma_nivel2,solucion_nivel2,fecha_solnivel2,id_asignanivel3,id_nivel3,fecha_nivel3,firma_nivel3,solucion_nivel3,fecha_solnivel3,p01,p02,p03,p04,id_programa,fecha_modificacion,modificacion,ip_terminal,encuesta,fecha_encuesta
  FROM incidente
 where fecha_incidente between to_date('2016-02-01', '%Y-%m-%d') and to_date('2016-02-29', '%Y-%m-%d');


SELECT id_incidente,id_sistema,id_resguardoentregadetalle,fecha_incidente,numero_incidente,estado_incidente,id_reporta,id_serv,id_servfinal,descripcion,nivel,id_servresp,id_captura,id_nivel1,fecha_nivel1,firma_nivel1,solucion_nivel1,fecha_solnivel1,id_asignanivel2,id_nivel2,fecha_nivel2,firma_nivel2,solucion_nivel2,fecha_solnivel2,id_asignanivel3,id_nivel3,fecha_nivel3,firma_nivel3,solucion_nivel3,fecha_solnivel3,p01,p02,p03,p04,id_programa,fecha_modificacion,modificacion,ip_terminal,encuesta,fecha_encuesta
  FROM incidente
 where estado_incidente = 'T'
   and p01 is null;

 
update incidente
   set estado_incidente = 'E'
 where estado_incidente = 'T'
   and p01 is null;
;

SELECT id_monitoreo,fecha_monitoreo,numero_monitoreo,id_bitacora,estado_monitoreo,semaforo,nota,id_usuario,fecha_modificacion,modificacion,ip_terminal
  FROM monitoreo;

delete from monitoreo ;


