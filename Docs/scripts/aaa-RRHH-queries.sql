SELECT idperfil,desc_perfil,perfil_bi
  FROM perfil
 order by idperfil desc;

insert into perfil
 (idperfil,desc_perfil,perfil_bi)
values
 (63, 'ROLE_SAST_BITACORAS', 'f');
 
 
 SELECT idusuario,idperfil
   FROM usuario_perfil
  where idusuario = 10041;--10033; --9586 ;


SELECT idusuario,rfc,nombre,paterno,materno,password,idunidadmedica,idservicio,cedula,estatus,fecha_nacimiento,lugar_nacimiento,fecha_ingreso_inr,direccion,colonia,cp,municipio,ciudad,estado,telefono,turno,estadocivil,cargo,sexo,titulo,idperfilagenda,enabled,account_expired,account_locked,password_expired,passwordc,idperfilautorizacion,idusuariotipo,id_empleado,extension,correo,categoria,idusuariocapturo,idusuariomodifico 
  FROM usuario
 where idusuario = 10033;

update usuario
   set passwordc = '1326e38796b7a63b1c088b54ddff8d18e87b073955d1e5fefc9b015ec032ff0a'
 where idusuario = 10033;


SELECT idusuario,idperfil
  FROM usuario_perfil
 where idusuario = 9579;

 
 
insert into usuario_perfil
 (idusuario,idperfil)
values
 (9579, 63);

insert into usuario_perfil
 (idusuario,idperfil)
values
 (9584, 63);

 

SELECT idusuario,rfc,nombre,paterno,materno,password,idunidadmedica,idservicio,cedula,estatus,fecha_nacimiento,lugar_nacimiento,fecha_ingreso_inr,direccion,colonia,cp,municipio,ciudad,estado,telefono,turno,estadocivil,cargo,sexo,titulo,idperfilagenda,enabled,account_expired,account_locked,password_expired,passwordc,idperfilautorizacion,idusuariotipo,id_empleado,extension,correo,categoria
FROM usuario
where rfc like "PECR%"; -- "EIVA721207" ; -- "VENR690106"; -- 

PECR790224

SELECT idusuario,id_empleado,rfc,nombre,paterno,materno,password,idunidadmedica,idservicio,cedula,estatus,fecha_nacimiento,lugar_nacimiento,fecha_ingreso_inr,direccion,colonia,cp,municipio,ciudad,estado,telefono,turno,estadocivil,cargo,sexo,titulo,idperfilagenda,enabled,account_expired,account_locked,password_expired,passwordc,idperfilautorizacion,idusuariotipo,extension,correo,categoria
FROM usuario
where idusuario in (10041);--(10051); --(8456); --(6558,7607,8456,9574,9575,9576,9577,9578,9579,9580,9581,9582,9583,9584,9585,9586,9587,9588);

SELECT idusuario,id_empleado,rfc,nombre,paterno,materno,password,idunidadmedica,idservicio,cedula,estatus,fecha_nacimiento,lugar_nacimiento,fecha_ingreso_inr,direccion,colonia,cp,municipio,ciudad,estado,telefono,turno,estadocivil,cargo,sexo,titulo,idperfilagenda,enabled,account_expired,account_locked,password_expired,passwordc,idperfilautorizacion,idusuariotipo,extension,correo,categoria
FROM usuario
where nombre like "%CANO%";--"%MARIA DEL SOCORRO%";-- "%HERNANDEZ PEREZ ALICIA%";

SELECT idusuario,correo,id_empleado,rfc,nombre,paterno,materno,password,idunidadmedica,idservicio,cedula,estatus,fecha_nacimiento,lugar_nacimiento,fecha_ingreso_inr,direccion,colonia,cp,municipio,ciudad,estado,telefono,turno,estadocivil,cargo,sexo,titulo,idperfilagenda,enabled,account_expired,account_locked,password_expired,passwordc,idperfilautorizacion,idusuariotipo,extension,categoria
FROM usuario
where nombre like "%POPOV%"; --"DEMANOS%"; --"%FERNANDO%";--"%DORIAN%";--"%SANCHEZ NU%";-- "%HERNANDEZ PEREZ ALICIA%";

SELECT idusuario,correo,id_empleado,rfc,nombre,paterno,materno,password,idunidadmedica,idservicio,cedula,estatus,fecha_nacimiento,lugar_nacimiento,fecha_ingreso_inr,direccion,colonia,cp,municipio,ciudad,estado,telefono,turno,estadocivil,cargo,sexo,titulo,idperfilagenda,enabled,account_expired,account_locked,password_expired,passwordc,idperfilautorizacion,idusuariotipo,extension,categoria
FROM usuario
where correo is not null; -- like "%FERNANDO%";--"%DORIAN%";--"%SANCHEZ NU%";-- "%HERNANDEZ PEREZ ALICIA%";

Maria del Socorro Zarazua Perez


SELECT idusuario,correo,id_empleado,rfc,nombre,paterno,materno,password,idunidadmedica,idservicio,cedula,estatus,fecha_nacimiento,lugar_nacimiento,fecha_ingreso_inr,direccion,colonia,cp,municipio,ciudad,estado,telefono,turno,estadocivil,cargo,sexo,titulo,idperfilagenda,enabled,account_expired,account_locked,password_expired,passwordc,idperfilautorizacion,idusuariotipo,extension,categoria
  from Usuario as u
 where upper(nombre) || ' ' || upper(nvl(paterno,''))  || ' ' || upper(nvl(materno,'')) like '%DORIAN%';
     

SELECT idusuario,idperfil
  FROM usuario_perfil
 where idusuario in (10041, 10053);--10071; --6648; --9427; --10041;


SELECT idusuario,idperfil
  FROM usuario_perfil
 where idusuario = 10041
   and idperfil = 65;

DELETE FROM usuario_perfil
 where idusuario = 10041
   and idperfil = 65;

SELECT idusuario,idperfil
  FROM usuario_perfil
 where idusuario in (9577,9578,9580,8456,10039,10040,10041,10042,10044,10045,10046,10047)
   and idperfil = 60;
 

insert into usuario_perfil
  (idusuario,idperfil)
 values
  (6648, 65);
--  (10041, 65);

insert into usuario_perfil
  (idusuario,idperfil)
 values
  (9427, 65);

SELECT idusuario,rfc,nombre,paterno,materno,password,idunidadmedica,idservicio,cedula,estatus,fecha_nacimiento,lugar_nacimiento,fecha_ingreso_inr,direccion,colonia,cp,municipio,ciudad,estado,telefono,turno,estadocivil,cargo,sexo,titulo,idperfilagenda,enabled,account_expired,account_locked,password_expired,passwordc,idperfilautorizacion,idusuariotipo,id_empleado,extension,correo,categoria,idusuariocapturo,idusuariomodifico 
  FROM usuario
 where idusuario = 10091; -- 8301; --10053;



SELECT idusuario,rfc,nombre,paterno,materno,password,idunidadmedica,idservicio,cedula,estatus,fecha_nacimiento,lugar_nacimiento,fecha_ingreso_inr,direccion,colonia,cp,municipio,ciudad,estado,telefono,turno,estadocivil,cargo,sexo,titulo,idperfilagenda,enabled,account_expired,account_locked,password_expired,passwordc,idperfilautorizacion,idusuariotipo,id_empleado,extension,correo,categoria,idusuariocapturo,idusuariomodifico 
  FROM usuario
 where id_empleado in
(10,51,71,97,107,173,222,270,271,332,343,350,398,416,502,676,700,774,778,820,964,1018,1057,1095,1266,1280,1318,1362,1443,1465,1528,1538,1607,1623,1628,1708,1886,1911,1930,2662,2849,3016,3021,3035,3094,3323,3519,3647,3673,3718,3753,3870,3905,3928,3947,3963,4017,4020,4156,4174,4252,4507,4739,4778,4797,4812,4912);

SELECT idusuario,rfc,nombre,paterno,materno
  FROM usuario
 where id_empleado in
(10,51,71,97,107,173,222,270,271,332,343,350,398,416,502,676,700,774,778,820,964,1018,1057,1095,1266,1280,1318,1362,1443,1465,1528,1538,1607,1623,1628,1708,1886,1911,1930,2662,2849,3016,3021,3035,3094,3323,3519,3647,3673,3718,3753,3870,3905,3928,3947,3963,4017,4020,4156,4174,4252,4507,4739,4778,4797,4812,4912);

SELECT idusuario,rfc,nombre,paterno,materno,passwordc,password,idunidadmedica,idservicio,cedula,estatus,fecha_nacimiento,lugar_nacimiento,fecha_ingreso_inr,direccion,colonia,cp,municipio,ciudad,estado,telefono,turno,estadocivil,cargo,sexo,titulo,idperfilagenda,enabled,account_expired,account_locked,password_expired,idperfilautorizacion,idusuariotipo,id_empleado,extension,correo,categoria,idusuariocapturo,idusuariomodifico 
  FROM usuario
 where rfc like '%MAHA850708%';--'%ANABEL%';-- rfc = 'CARL740622';

SELECT idusuario,rfc,nombre,id_empleado,paterno,materno,passwordc,password,idunidadmedica,idservicio,cedula,estatus,fecha_nacimiento,lugar_nacimiento,fecha_ingreso_inr,direccion,colonia,cp,municipio,ciudad,estado,telefono,turno,estadocivil,cargo,sexo,titulo,idperfilagenda,enabled,account_expired,account_locked,password_expired,idperfilautorizacion,idusuariotipo,extension,correo,categoria,idusuariocapturo,idusuariomodifico 
  FROM usuario
 where  rfc in ('ZAPS730120','DERA791215', 'EIVA721207','MAHA850708','PECR790224', 'CARL740622');

SELECT id_empleado
  FROM usuario
 where  rfc in ('ZAPS730120','DERA791215', 'EIVA721207','MAHA850708','PECR790224', 'CARL740622');

UPDATE usuario
   SET passwordc = 'bf43f82c084847ce52ac7f16af242e052538b8e47bed759d9f65284d3653b1b8'
 where rfc like '%PECR790224%';


SELECT idusuario,passwordfirma,nombre,tipo,tamanio,datos,bloqueada,intentosfallidos 
  FROM firmadigital
 where idusuario = 10055;

update firmadigital
   set passwordfirma = '100evalC'
 where idusuario = 10041;
