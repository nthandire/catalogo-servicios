SELECT id_solicitud,fecha_solicitud,numero_solicitud,estado_solicitud,justificacion,id_solicitante,id_autoriza,fecha_autoriza,id_vb,fecha_vb,p01,p02,p03,p04,fecha_modificacion,modificacion,ip_terminal,comentario_vb,id_revisa,fecha_revisa,encuesta,fecha_encuesta
  FROM solicitud
 where id_solicitud > 80;


SELECT id_solicitud,fecha_solicitud,numero_solicitud,estado_solicitud,justificacion,id_solicitante,id_autoriza,fecha_autoriza,id_vb,fecha_vb,p01,p02,p03,p04,fecha_modificacion,modificacion,ip_terminal,comentario_vb,id_revisa,fecha_revisa,encuesta,fecha_encuesta
  from Solicitud
 where fecha_autoriza between to_date('2016-01-01', '%Y-%m-%d') and to_date('2016-01-31', '%Y-%m-%d');