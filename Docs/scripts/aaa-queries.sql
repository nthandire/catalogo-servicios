
SELECT id_solicitud,fecha_solicitud,numero_solicitud,estado_solicitud,justificacion,id_solicitante,id_autoriza,fecha_autoriza,id_vb,fecha_vb,p01,p02,p03,p04,fecha_modificacion,modificacion,ip_terminal,comentario_vb,id_revisa,fecha_revisa,encuesta,fecha_encuesta
  from Solicitud
 where fecha_autoriza between to_date('2016-01-01', '%Y-%m-%d') and to_date('2016-01-31', '%Y-%m-%d');
 
SELECT id_solicitud,fecha_solicitud,numero_solicitud,estado_solicitud,justificacion,id_solicitante,id_autoriza,fecha_autoriza,id_vb,fecha_vb,p01,p02,p03,p04,fecha_modificacion,modificacion,ip_terminal,comentario_vb,id_revisa,fecha_revisa,encuesta,fecha_encuesta
  from Solicitud
 where fecha_autoriza >= to_date('2016-04-26', '%Y-%m-%d');
 
SELECT id_solicitud,fecha_solicitud,fecha_modificacion,extension,numero_solicitud,estado_solicitud,justificacion,id_solicitante,id_autoriza,fecha_autoriza,id_vb,fecha_vb,p01,p02,p03,p04,modificacion,ip_terminal,comentario_vb,id_revisa,fecha_revisa,encuesta,fecha_encuesta
  from Solicitud
 where id_solicitud = 151;
 
SELECT id_solicitud,fecha_solicitud,extension,numero_solicitud,estado_solicitud,justificacion,id_solicitante,id_autoriza,fecha_autoriza,id_vb,fecha_vb,p01,p02,p03,p04,fecha_modificacion,modificacion,ip_terminal,comentario_vb,id_revisa,fecha_revisa,encuesta,fecha_encuesta
  from Solicitud
 where numero_solicitud = 35;
 
SELECT id_solicitud,fecha_solicitud,numero_solicitud,extension,id_solicitante,estado_solicitud,justificacion,id_autoriza,fecha_autoriza,id_vb,fecha_vb,p01,p02,p03,p04,fecha_modificacion,modificacion,ip_terminal,comentario_vb,id_revisa,fecha_revisa,encuesta,fecha_encuesta
  from Solicitud
 where id_solicitante = 10053;--8301;
 
SELECT id_solicitud,fecha_modificacion,fecha_encuesta,fecha_solicitud,numero_solicitud,extension,id_solicitante,estado_solicitud,justificacion,id_autoriza,fecha_autoriza,id_vb,fecha_vb,p01,p02,p03,p04,modificacion,ip_terminal,comentario_vb,id_revisa,fecha_revisa,encuesta
  from Solicitud
 where estado_solicitud = 'T';
 
   and id_solicitante = 10041;
 
update Solicitud
   set estado_solicitud = 'E' --'T' --'F'
   , id_solicitante = 10041
 where id_solicitud = 140;

update Solicitud
   set estado_solicitud = 'A' --'T' --'F'
 where id_solicitud = 181;

update Solicitud
   set numero_solicitud = 24
 where id_solicitud = 138;


SELECT id_incidente,id_sistema,id_resguardoentregadetalle,fecha_incidente,numero_incidente,estado_incidente,id_reporta,id_serv,id_servfinal,descripcion,nivel,id_servresp,id_captura,id_nivel1,fecha_nivel1,firma_nivel1,solucion_nivel1,fecha_solnivel1,id_asignanivel2,id_nivel2,fecha_nivel2,firma_nivel2,solucion_nivel2,fecha_solnivel2,id_asignanivel3,id_nivel3,fecha_nivel3,firma_nivel3,solucion_nivel3,fecha_solnivel3,p01,p02,p03,p04,id_programa,fecha_modificacion,modificacion,ip_terminal,encuesta,fecha_encuesta
  FROM incidente
 where fecha_incidente between to_date('2016-04-18', '%Y-%m-%d') and to_date('2016-04-19', '%Y-%m-%d')
   and estado_incidente = 'A'
   and id_tecnico is null  ;

SELECT id_incidente,id_sistema,nivel,estado_incidente,id_serv,id_resguardoentregadetalle,fecha_incidente,numero_incidente,id_reporta,id_servfinal,descripcion,id_servresp,id_captura,id_nivel1,fecha_nivel1,firma_nivel1,solucion_nivel1,fecha_solnivel1,id_asignanivel2,id_nivel2,fecha_nivel2,firma_nivel2,solucion_nivel2,fecha_solnivel2,id_asignanivel3,id_nivel3,fecha_nivel3,firma_nivel3,solucion_nivel3,fecha_solnivel3,p01,p02,p03,p04,id_programa,fecha_modificacion,modificacion,ip_terminal,encuesta,fecha_encuesta
  FROM incidente
 where id_serv in (4)
   and estado_incidente = 'A'; --(85, 101,112,131,144, 160, 174, 191, 204, 216);

SELECT id_incidente,id_sistema,nivel,estado_incidente,id_serv,id_resguardoentregadetalle,fecha_incidente,numero_incidente,id_reporta,id_servfinal,descripcion,id_servresp,id_captura,id_nivel1,fecha_nivel1,firma_nivel1,solucion_nivel1,fecha_solnivel1,id_asignanivel2,id_nivel2,fecha_nivel2,firma_nivel2,solucion_nivel2,fecha_solnivel2,id_asignanivel3,id_nivel3,fecha_nivel3,firma_nivel3,solucion_nivel3,fecha_solnivel3,p01,p02,p03,p04,id_programa,fecha_modificacion,modificacion,ip_terminal,encuesta,fecha_encuesta
  FROM incidente
 where id_incidente = 116; --numero_incidente = 30;

update incidente
   set estado_incidente = 'A'
 where id_incidente = 103;

SELECT id_incidente,id_sistema,id_resguardoentregadetalle,fecha_incidente,numero_incidente,estado_incidente,id_reporta,id_serv,id_servfinal,descripcion,nivel,id_servresp,id_captura,id_nivel1,fecha_nivel1,firma_nivel1,solucion_nivel1,fecha_solnivel1,id_asignanivel2,id_nivel2,fecha_nivel2,firma_nivel2,solucion_nivel2,fecha_solnivel2,id_asignanivel3,id_nivel3,fecha_nivel3,firma_nivel3,solucion_nivel3,fecha_solnivel3,p01,p02,p03,p04,id_programa,fecha_modificacion,modificacion,ip_terminal,encuesta,fecha_encuesta
  FROM incidente
 where fecha_incidente >= to_date('2016-05-02', '%Y-%m-%d');

SELECT id_incidente,id_sistema,id_resguardoentregadetalle,fecha_incidente,numero_incidente,estado_incidente,id_reporta,id_serv,id_servfinal,descripcion,nivel,id_servresp,id_captura,id_nivel1,fecha_nivel1,firma_nivel1,solucion_nivel1,fecha_solnivel1,id_asignanivel2,id_nivel2,fecha_nivel2,firma_nivel2,solucion_nivel2,fecha_solnivel2,id_asignanivel3,id_nivel3,fecha_nivel3,firma_nivel3,solucion_nivel3,fecha_solnivel3,p01,p02,p03,p04,id_programa,fecha_modificacion,modificacion,ip_terminal,encuesta,fecha_encuesta
  FROM incidente
 where estado_incidente = 'E'
   and id_reporta = 10041;

SELECT id_incidente,id_sistema,id_resguardoentregadetalle,fecha_incidente,numero_incidente,estado_incidente,id_reporta,id_serv,id_servfinal,descripcion,nivel,id_servresp,id_captura,id_nivel1,fecha_nivel1,firma_nivel1,solucion_nivel1,fecha_solnivel1,id_asignanivel2,id_nivel2,fecha_nivel2,firma_nivel2,solucion_nivel2,fecha_solnivel2,id_asignanivel3,id_nivel3,fecha_nivel3,firma_nivel3,solucion_nivel3,fecha_solnivel3,p01,p02,p03,p04,id_programa,fecha_modificacion,modificacion,ip_terminal,encuesta,fecha_encuesta
  FROM incidente
 where id_incidente = 204;


SELECT id_incidente,id_sistema,id_resguardoentregadetalle,fecha_incidente,numero_incidente,estado_incidente,id_reporta,id_serv,id_servfinal,descripcion,nivel,id_servresp,id_captura,id_nivel1,fecha_nivel1,firma_nivel1,solucion_nivel1,fecha_solnivel1,id_asignanivel2,id_nivel2,fecha_nivel2,firma_nivel2,solucion_nivel2,fecha_solnivel2,id_asignanivel3,id_nivel3,fecha_nivel3,firma_nivel3,solucion_nivel3,fecha_solnivel3,p01,p02,p03,p04,id_programa,fecha_modificacion,modificacion,ip_terminal,encuesta,fecha_encuesta
  FROM incidente
 where numero_incidente = 100;

update incidente
   set estado_incidente = 'E'
 where id_incidente = 204;

SELECT id_incidente,id_sistema,id_reporta,id_resguardoentregadetalle,fecha_incidente,numero_incidente,estado_incidente,id_serv,id_servfinal,descripcion,nivel,id_servresp,id_captura,id_nivel1,fecha_nivel1,firma_nivel1,solucion_nivel1,fecha_solnivel1,id_asignanivel2,id_nivel2,fecha_nivel2,firma_nivel2,solucion_nivel2,fecha_solnivel2,id_asignanivel3,id_nivel3,fecha_nivel3,firma_nivel3,solucion_nivel3,fecha_solnivel3,p01,p02,p03,p04,id_programa,fecha_modificacion,modificacion,ip_terminal,encuesta,fecha_encuesta
  FROM incidente
 where id_reporta = 10051;


SELECT id_incidente,id_sistema,id_resguardoentregadetalle,fecha_incidente,numero_incidente,estado_incidente,id_reporta,id_serv,id_servfinal,descripcion,nivel,id_servresp,id_captura,id_nivel1,fecha_nivel1,firma_nivel1,solucion_nivel1,fecha_solnivel1,id_asignanivel2,id_nivel2,fecha_nivel2,firma_nivel2,solucion_nivel2,fecha_solnivel2,id_asignanivel3,id_nivel3,fecha_nivel3,firma_nivel3,solucion_nivel3,fecha_solnivel3,p01,p02,p03,p04,id_programa,fecha_modificacion,modificacion,ip_terminal,encuesta,fecha_encuesta
  FROM incidente
 where estado_incidente = 'T'
   and p01 is null;

SELECT id_incidente,fecha_modificacion,fecha_encuesta,id_sistema,id_resguardoentregadetalle,fecha_incidente,numero_incidente,estado_incidente,id_reporta,id_serv,id_servfinal,descripcion,nivel,id_servresp,id_captura,id_nivel1,fecha_nivel1,firma_nivel1,solucion_nivel1,fecha_solnivel1,id_asignanivel2,id_nivel2,fecha_nivel2,firma_nivel2,solucion_nivel2,fecha_solnivel2,id_asignanivel3,id_nivel3,fecha_nivel3,firma_nivel3,solucion_nivel3,fecha_solnivel3,p01,p02,p03,p04,id_programa,modificacion,ip_terminal,encuesta
  FROM incidente
 where estado_incidente = 'E' 
   and id_incidente > 190;

 
update incidente
   set estado_incidente = 'E'
 where estado_incidente = 'T'
   and p01 is null;
;

SELECT id_incidente,numero_incidente,nivel,estado_incidente,fecha_nivel1,fecha_solnivel1,fecha_nivel2,fecha_solnivel2,fecha_nivel3,fecha_solnivel3,firma_nivel1,solucion_nivel1,firma_nivel2,solucion_nivel2,solucion_nivel3,id_sistema,id_resguardoentregadetalle,fecha_incidente,estado_incidente,id_reporta,id_serv,id_servfinal,descripcion,id_servresp,id_captura,id_nivel1,id_asignanivel2,id_nivel2,id_asignanivel3,id_nivel3,firma_nivel3,p01,p02,p03,p04,id_programa,fecha_modificacion,modificacion,ip_terminal,encuesta,fecha_encuesta
  FROM incidente
 where numero_incidente in (81, 62, 64);

update incidente
   set nivel = 1
 where id_incidente = 135;

update incidente
   set estado_incidente = 'A'
 where id_incidente = 135;


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
 where id_usuario = 10053; --10102; --10052; --10071; -- 9427; --10041;


SELECT id_usuario,area,autoriza,vobo,estado 
  FROM usuario_autorizado;

SELECT id_usuario,area,autoriza,vobo,estado
   FROM usuario_autorizado--nada 
  where area = 'MS';

SELECT id_usuario,area,autoriza,vobo,estado
   FROM usuario_autorizado--nada 
  where autoriza = 't';

SELECT id_usuario
   FROM usuario_autorizado--nada 
  where autoriza = 't';

10041


update usuario_autorizado
   set autoriza = 't',vobo = 't'
 where id_usuario = 10041;

update usuario_autorizado
   set area = null --'V'
 where id_usuario = 10052;

insert into usuario_autorizado
  (id_usuario,area,autoriza,vobo,estado)
 values
  (6648, 'MS', 't', 't', 'A');
  --(9427, 'MS', 't', 't', 'A');


SELECT id_serv,id_servsub,descripcion,portal,incidente,solicitud,problema,id_servresp1,id_servresp2,id_servresp3,tiempo1,tiempo2,tiempo3,id_tiempo1,id_tiempo2,id_tiempo3,impacto,id_servrespautoriza,id_servrespaprueba,plantilla,observaciones,estado_serv,id_usuario,fecha_modificacion,modificacion,ip_terminal 
  FROM cat_serv
 where id_serv in (137,174,4,42,111);

SELECT id_serv,id_servsub,descripcion,portal,incidente,solicitud,problema,id_servresp1,id_servresp2,id_servresp3,tiempo1,tiempo2,tiempo3,id_tiempo1,id_tiempo2,id_tiempo3,impacto,id_servrespautoriza,id_servrespaprueba,plantilla,observaciones,estado_serv,id_usuario,fecha_modificacion,modificacion,ip_terminal 
  FROM cat_serv
 where id_servresp3 is null;

SELECT id_serv,id_servsub,descripcion,portal,incidente,solicitud,problema,id_servresp1,id_servresp2,id_servresp3,tiempo1,tiempo2,tiempo3,id_tiempo1,id_tiempo2,id_tiempo3,impacto,id_servrespautoriza,id_servrespaprueba,plantilla,observaciones,estado_serv,id_usuario,fecha_modificacion,modificacion,ip_terminal 
  FROM cat_serv
 where id_serv = 74;

SELECT id_serv,id_servsub,descripcion,portal,incidente,solicitud,problema,id_servresp1,id_servresp2,id_servresp3,tiempo1,tiempo2,tiempo3,id_tiempo1,id_tiempo2,id_tiempo3,impacto,id_servrespautoriza,id_servrespaprueba,plantilla,observaciones,estado_serv,id_usuario,fecha_modificacion,modificacion,ip_terminal 
  FROM cat_serv
 where id_servresp2 = 13
    or id_servresp3 = 13;

SELECT id_servresp,descripcion 
  FROM cat_servresp
 where id_servresp in (2, 24, 13,14);
 

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
 where id_servsub = 33 ;

SELECT *
  FROM cat_servcat
 where id_servcat = 2 ;

select id_serv, *
  from incidente
 where id_incidente = 168;
 
update incidente
   set id_serv = 2 -- 100
 where id_incidente = 119;
 
update solicitud_detalle
   set id_tecnico = null
 where id_solicitud = 181;

SELECT id_usuario,area,autoriza,vobo,estado
  FROM usuario_autorizado
 WHERE id_usuario in (10041, 10033); --10091;

INSERT INTO usuario_autorizado
  (id_usuario,area,autoriza,vobo,estado)
 VALUES
     (10091,'DGDST','f','f','A')
 ;


 
SELECT id_problema,fuente,id_fuente,fecha_problema,folio,observaciones,solucion,fecha_solucion,resolvio,id_usuario,fecha_modificacion,modificacion,ip_terminal,id_resolvio
  FROM problema
 where id_problema = 16;

delete from problema
 where id_problema = 16;


SELECT id_resguardoentregadetalle

SELECT id_solicituddetalle,id_solicitud,id_serv,id_resguardoentregadetalle,estado_solictuddetalle,descripcion,solucion,id_tecnico,fecha_solucion,id_programa,id_servcat,descripcion_tecnica,prioridad,id_aprobador,fecha_aprobador 
  FROM solicitud_detalle
 where id_resguardoentregadetalle is not null;


select s.id_solicitud, id_solicituddetalle, id_serv, id_resguardoentregadetalle
  from solicitud s,
       solicitud_detalle d
 where s.estado_solicitud = 'R'
   and s.id_solicitud = d.id_solicitud
   and id_resguardoentregadetalle is not null;

select s.id_solicitud, id_solicituddetalle, id_serv, id_resguardoentregadetalle, s.estado_solicitud
  from solicitud s,
       solicitud_detalle d
 where s.id_solicitud = d.id_solicitud
   and id_vb is not null;

SELECT id_solicituddetalle,id_solicitud,id_serv,id_resguardoentregadetalle,estado_solictuddetalle,descripcion,solucion,id_tecnico,fecha_solucion,id_programa,id_servcat,descripcion_tecnica,prioridad,id_aprobador,fecha_aprobador 
  FROM solicitud_detalle
 where id_solicitud = 165;

SELECT id_solicitud,fecha_solicitud,numero_solicitud,estado_solicitud,justificacion,id_solicitante,id_autoriza,fecha_autoriza,id_vb,fecha_vb,p01,p02,p03,p04,fecha_modificacion,modificacion,ip_terminal,comentario_vb,id_revisa,fecha_revisa,encuesta,fecha_encuesta
  FROM solicitud
 where id_solicitante = 10062
   and ((estado_solicitud <> 'T'
        and estado_solicitud <> 'C')
         or estado_solicitud is null);
 
 where id_solicitud = 157;

SELECT id_solicitud,fecha_solicitud,numero_solicitud,estado_solicitud,justificacion,id_solicitante,id_autoriza,fecha_autoriza,id_vb,fecha_vb,p01,p02,p03,p04,fecha_modificacion,modificacion,ip_terminal,comentario_vb,id_revisa,fecha_revisa,encuesta,fecha_encuesta
  from Solicitud
 order by fecha_modificacion desc;
 
 where fecha_solicitud between to_date('2016-04-20', '%Y-%m-%d') and to_date('2016-04-22', '%Y-%m-%d');
 
SELECT id_servhist,id_serv,folio,descripcion,id_usuario,fecha_modificacion,ip_terminal
  FROM cat_serv_hist;

