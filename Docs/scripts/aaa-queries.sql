SELECT id_solicitud,fecha_solicitud,numero_solicitud,estado_solicitud,justificacion,id_solicitante,id_autoriza,fecha_autoriza,id_vb,fecha_vb,p01,p02,p03,p04,fecha_modificacion,modificacion,ip_terminal,comentario_vb,id_revisa,fecha_revisa,encuesta,fecha_encuesta
  FROM solicitud
 where id_solicitud > 210;


SELECT id_solicitud,fecha_solicitud,numero_solicitud,estado_solicitud,justificacion,id_solicitante,id_autoriza,fecha_autoriza,id_vb,fecha_vb,p01,p02,p03,p04,fecha_modificacion,modificacion,ip_terminal,comentario_vb,id_revisa,fecha_revisa,encuesta,fecha_encuesta
  from Solicitud
 where fecha_autoriza between to_date('2016-01-01', '%Y-%m-%d') and to_date('2016-01-31', '%Y-%m-%d');
 
 
SELECT id_incidente,id_sistema,id_resguardoentregadetalle,fecha_incidente,numero_incidente,estado_incidente,id_reporta,id_serv,id_servfinal,descripcion,nivel,id_servresp,id_captura,id_nivel1,fecha_nivel1,firma_nivel1,solucion_nivel1,fecha_solnivel1,id_asignanivel2,id_nivel2,fecha_nivel2,firma_nivel2,solucion_nivel2,fecha_solnivel2,id_asignanivel3,id_nivel3,fecha_nivel3,firma_nivel3,solucion_nivel3,fecha_solnivel3,p01,p02,p03,p04,id_programa,fecha_modificacion,modificacion,ip_terminal,encuesta,fecha_encuesta
  FROM incidente
 where fecha_incidente between to_date('2016-04-18', '%Y-%m-%d') and to_date('2016-04-19', '%Y-%m-%d')
   and estado_incidente = 'A'
   and id_tecnico is null  ;

SELECT id_incidente,id_sistema,id_resguardoentregadetalle,fecha_incidente,numero_incidente,estado_incidente,id_reporta,id_serv,id_servfinal,descripcion,nivel,id_servresp,id_captura,id_nivel1,fecha_nivel1,firma_nivel1,solucion_nivel1,fecha_solnivel1,id_asignanivel2,id_nivel2,fecha_nivel2,firma_nivel2,solucion_nivel2,fecha_solnivel2,id_asignanivel3,id_nivel3,fecha_nivel3,firma_nivel3,solucion_nivel3,fecha_solnivel3,p01,p02,p03,p04,id_programa,fecha_modificacion,modificacion,ip_terminal,encuesta,fecha_encuesta
  FROM incidente
 where estado_incidente = 'E';


SELECT id_incidente,id_sistema,id_resguardoentregadetalle,fecha_incidente,numero_incidente,estado_incidente,id_reporta,id_serv,id_servfinal,descripcion,nivel,id_servresp,id_captura,id_nivel1,fecha_nivel1,firma_nivel1,solucion_nivel1,fecha_solnivel1,id_asignanivel2,id_nivel2,fecha_nivel2,firma_nivel2,solucion_nivel2,fecha_solnivel2,id_asignanivel3,id_nivel3,fecha_nivel3,firma_nivel3,solucion_nivel3,fecha_solnivel3,p01,p02,p03,p04,id_programa,fecha_modificacion,modificacion,ip_terminal,encuesta,fecha_encuesta
  FROM incidente
 where estado_incidente = 'T'
   and p01 is null;

 
update incidente
   set estado_incidente = 'E'
 where estado_incidente = 'T'
   and p01 is null;
;

SELECT id_incidente,numero_incidente,nivel,estado_incidente,fecha_nivel1,fecha_solnivel1,fecha_nivel2,fecha_solnivel2,fecha_nivel3,fecha_solnivel3,firma_nivel1,solucion_nivel1,firma_nivel2,solucion_nivel2,solucion_nivel3,id_sistema,id_resguardoentregadetalle,fecha_incidente,estado_incidente,id_reporta,id_serv,id_servfinal,descripcion,id_servresp,id_captura,id_nivel1,id_asignanivel2,id_nivel2,id_asignanivel3,id_nivel3,firma_nivel3,p01,p02,p03,p04,id_programa,fecha_modificacion,modificacion,ip_terminal,encuesta,fecha_encuesta
  FROM incidente
 where numero_incidente = 46;
 
SELECT id_incidente,numero_incidente,nivel,estado_incidente,fecha_nivel1,fecha_solnivel1,fecha_nivel2,fecha_solnivel2,fecha_nivel3,fecha_solnivel3,firma_nivel1,solucion_nivel1,firma_nivel2,solucion_nivel2,solucion_nivel3,id_sistema,id_resguardoentregadetalle,fecha_incidente,estado_incidente,id_reporta,id_serv,id_servfinal,descripcion,id_servresp,id_captura,id_nivel1,id_asignanivel2,id_nivel2,id_asignanivel3,id_nivel3,firma_nivel3,p01,p02,p03,p04,id_programa,fecha_modificacion,modificacion,ip_terminal,encuesta,fecha_encuesta
  FROM incidente
 where id_incidente = ;
 
   and id_incidente > 50;

update incidente
   set estado_incidente = 'E'
 where id_incidente = 96;
 
SELECT id_incidente,numero_incidente,estado_incidente,fecha_nivel1,fecha_solnivel1,fecha_nivel2,fecha_solnivel2,fecha_nivel3,fecha_solnivel3,firma_nivel1,solucion_nivel1,firma_nivel2,solucion_nivel2,solucion_nivel3,id_sistema,id_resguardoentregadetalle,fecha_incidente,estado_incidente,id_reporta,id_serv,id_servfinal,descripcion,nivel,id_servresp,id_captura,id_nivel1,id_asignanivel2,id_nivel2,id_asignanivel3,id_nivel3,firma_nivel3,p01,p02,p03,p04,id_programa,fecha_modificacion,modificacion,ip_terminal,encuesta,fecha_encuesta
  FROM incidente
 where id_incidente > 80;


SELECT id_incidente,id_sistema,id_resguardoentregadetalle,fecha_incidente,numero_incidente,estado_incidente,id_reporta,id_serv,id_servfinal,descripcion,nivel,id_servresp,id_captura,id_nivel1,fecha_nivel1,firma_nivel1,solucion_nivel1,fecha_solnivel1,id_asignanivel2,id_nivel2,fecha_nivel2,firma_nivel2,solucion_nivel2,fecha_solnivel2,id_asignanivel3,id_nivel3,fecha_nivel3,firma_nivel3,solucion_nivel3,fecha_solnivel3,p01,p02,p03,p04,id_programa,fecha_modificacion,modificacion,ip_terminal,encuesta,fecha_encuesta,extension 
  FROM incidente
 where id_incidente = 94;

update incidente
      set estado_incidente = 'A'
 where id_incidente = 94;
  




SELECT id_monitoreo,fecha_monitoreo,numero_monitoreo,id_bitacora,estado_monitoreo,semaforo,nota,id_usuario,fecha_modificacion,modificacion,ip_terminal,id_tipomonitoreo
  FROM monitoreo;

SELECT id_monitoreo,fecha_monitoreo,numero_monitoreo,id_bitacora,estado_monitoreo,semaforo,nota,id_usuario,fecha_modificacion,modificacion,ip_terminal
  FROM monitoreo
 where id_monitoreo = 4;

update monitoreo
   set id_tipomonitoreo = 1;

update monitoreo
   set numero_monitoreo = 4
 where id_monitoreo = 4;

delete from monitoreo ;


SELECT id_problema,fuente,id_fuente,fecha_problema,folio,observaciones,solucion,fecha_solucion,resolvio,id_usuario,fecha_modificacion,modificacion,ip_terminal
  FROM problema
 where id_problema >= 0;

delete from problema
 where id_problema >= 9;

SELECT id_monitoreo,fecha_monitoreo,numero_monitoreo,id_bitacora,estado_monitoreo,semaforo,nota,id_usuario,fecha_modificacion,modificacion,ip_terminal
  FROM monitoreo
 where id_monitoreo >= 4;

delete FROM monitoreo
 where id_monitoreo >= 3;

SELECT id_monitoredetalle,id_monitoreo,id_bitacoradetalle,estado,observaciones
  FROM monitoreo_detalle
 where id_monitoreo >= 3;
 
delete FROM monitoreo_detalle
 where id_monitoreo >= 3;
 

select *
  from Solicitud
 where estado_solicitud is not null
   and estado_solicitud <> 'F'
   and fecha_autoriza between to_date('2016/03/01', '%Y/%m/%d') and to_date('2016/03/11', '%Y/%m/%d');

select *
  from Problema
 where id_fuente is not null
   and fecha_problema between to_date('2016/03/01 00:00', '%Y/%m/%d %H:%M') and to_date('2016/03/11 23:59', '%Y/%m/%d %H:%M');
   
   
SELECT *
  FROM cat_serv
 where descripcion like "No coincide el nivel%" ;




SELECT id_problema,fuente,id_fuente,fecha_problema,folio,observaciones,solucion,fecha_solucion,resolvio,id_usuario,fecha_modificacion,modificacion,ip_terminal
  FROM problema
 where fuente = 'Bitacora';

SELECT id_bitacoradetalle,id_bitacora,des_bitacoradetalle,estado_bitacoradetalle
  FROM bitacora_detalle
 where id_bitacora = 7;

delete from bitacora_detalle
 where id_bitacora = 7;

SELECT id_bitacora,tipo_bitacora,des_bitacora,estado_bitacora
  FROM bitacora
 where id_bitacora = 7;

delete FROM bitacora
 where id_bitacora = 7;

SELECT id_tipomonitoreo,des_tipomonitoreo
  FROM tipo_monitoreo;

insert into tipo_monitoreo
  (id_tipomonitoreo,des_tipomonitoreo)
 values
  (1, 'Programado');

insert into tipo_monitoreo
  (id_tipomonitoreo,des_tipomonitoreo)
 values
  (2, 'Extraordinario');

insert into tipo_monitoreo
  (id_tipomonitoreo,des_tipomonitoreo)
 values
  (3, 'Revisión');
  

SELECT id_monitoreo,fecha_monitoreo,numero_monitoreo,id_bitacora,estado_monitoreo,semaforo,nota,id_usuario,fecha_modificacion,modificacion,ip_terminal,id_tipomonitoreo
  FROM monitoreo
  ;


SELECT max(numero_monitoreo)
  FROM monitoreo
  ;

SELECT id_monitoreo,numero_monitoreo
  FROM monitoreo
 where numero_monitoreo = 10 ;


update monitoreo
   set numero_monitoreo = id_monitoreo
 where numero_monitoreo = 10 ;


SELECT id_usuario,area,autoriza,vobo,estado 
  FROM usuario_autorizado
 where id_usuario = 10071; -- 9427; --10041;


SELECT id_usuario,area,autoriza,vobo,estado 
  FROM usuario_autorizado;

 SELECT id_usuario,area,autoriza,vobo,estado FROM "informix"."usuario_autorizado";

10041


update usuario_autorizado
   set autoriza = 't',vobo = 't'
 where id_usuario = 10041;

insert into usuario_autorizado
  (id_usuario,area,autoriza,vobo,estado)
 values
  (6648, 'MS', 't', 't', 'A');
  --(9427, 'MS', 't', 't', 'A');


SELECT id_serv,id_servsub,descripcion,portal,incidente,solicitud,problema,id_servresp1,id_servresp2,id_servresp3,tiempo1,tiempo2,tiempo3,id_tiempo1,id_tiempo2,id_tiempo3,impacto,id_servrespautoriza,id_servrespaprueba,plantilla,observaciones,estado_serv,id_usuario,fecha_modificacion,modificacion,ip_terminal 
  FROM cat_serv
 where id_serv in (4,42,111);

SELECT id_servresp,descripcion 
  FROM cat_servresp
 where id_servresp in (13,14);
 

DGDST

select id_Serv
  from Solicitud_Detalle d                    
 where id_tecnico is null                     
   and d.estado_solictuddetalle = 'A'
   and exists                                
      (select * from Cat_serv s,                     
                     Cat_servResp r                  
       where d.id_Serv = s.id_serv                 
         and s.id_servresp2 = r.id_servresp              
         and r.descripcion like '%DGDST%') ;


select d.id_Serv, s.id_servresp2
  from Solicitud_Detalle d,
       cat_serv s                    
 where d.id_tecnico is null                     
   and d.id_Serv is not null                     
   and d.estado_solictuddetalle = 'A'
   and s.id_serv = d.id_serv;
   
         
   and exists                                
      ( from Solicitud s                     
       where s.id_solicitud = d.id_solicitud            
         and s.estado = 'R')     ;
         
select * from Cat_servResp r                  
       where r.descripcion like '%DGDST%';



SELECT id_problema,fuente,id_fuente,fecha_problema,folio,observaciones,solucion,fecha_solucion,resolvio,id_usuario,fecha_modificacion,modificacion,ip_terminal,id_resolvio 
  FROM problema;

SELECT count(*)
  FROM problema;

SELECT id_problema,fuente,id_fuente,fecha_problema,folio,observaciones,solucion,fecha_solucion,resolvio,id_usuario,fecha_modificacion,modificacion,ip_terminal,id_resolvio 
  FROM problema
 where id_problema = 15;

DELETE problema
 where id_problema = 15;




SELECT count(*)
  FROM incidente;

SELECT id_solicitud,fecha_solicitud,numero_solicitud,estado_solicitud,justificacion,id_solicitante,id_autoriza,fecha_autoriza,id_vb,fecha_vb,p01,p02,p03,p04,fecha_modificacion,modificacion,ip_terminal,comentario_vb,id_revisa,fecha_revisa,encuesta,fecha_encuesta 
  FROM solicitud
 where id_solicitud > 150;
 ;


  
SELECT id_usuario,area,autoriza,vobo,estado 
  FROM usuario_autorizado
  ;

insert into usuario_autorizado
  (id_usuario,area,autoriza,vobo,estado)
 values
  (8456, 'DGDST', 't',);

SELECT *
  FROM cat_serv
 where id_servresp3 is null ;

SELECT id_servsub,id_servcat,descripcion,estado
  FROM cat_servsub
 where id_servsub = 2 ;

SELECT *
  FROM cat_servcat
 where id_servcat = 2 ;

select id_serv, *
  from incidente
 where id_incidente = 119;
 
update incidente
   set id_serv = 2 -- 100
 where id_incidente = 119;
 
SELECT id_solicituddetalle,id_solicitud,id_serv,id_resguardoentregadetalle,estado_solictuddetalle,descripcion,solucion,id_tecnico,fecha_solucion,id_programa,id_servcat,descripcion_tecnica,prioridad,id_aprobador,fecha_aprobador 
  FROM solicitud_detalle
 where id_solicitud = 220;

