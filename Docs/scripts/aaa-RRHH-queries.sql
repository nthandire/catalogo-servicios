SELECT idperfil,desc_perfil,perfil_bi
  FROM perfil
 order by idperfil desc;

insert into perfil
 (idperfil,desc_perfil,perfil_bi)
values
 (63, 'ROLE_SAST_BITACORAS', 'f');
 
 
 SELECT idusuario,idperfil
   FROM usuario_perfil
  where idusuario = 9586 ;


  

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
where idusuario in (10051); --(8456); --(6558,7607,8456,9574,9575,9576,9577,9578,9579,9580,9581,9582,9583,9584,9585,9586,9587,9588);

SELECT idusuario,id_empleado,rfc,nombre,paterno,materno,password,idunidadmedica,idservicio,cedula,estatus,fecha_nacimiento,lugar_nacimiento,fecha_ingreso_inr,direccion,colonia,cp,municipio,ciudad,estado,telefono,turno,estadocivil,cargo,sexo,titulo,idperfilagenda,enabled,account_expired,account_locked,password_expired,passwordc,idperfilautorizacion,idusuariotipo,extension,correo,categoria
FROM usuario
where nombre like "%MARIA DEL SOCORRO%";-- "%HERNANDEZ PEREZ ALICIA%";

SELECT idusuario,correo,id_empleado,rfc,nombre,paterno,materno,password,idunidadmedica,idservicio,cedula,estatus,fecha_nacimiento,lugar_nacimiento,fecha_ingreso_inr,direccion,colonia,cp,municipio,ciudad,estado,telefono,turno,estadocivil,cargo,sexo,titulo,idperfilagenda,enabled,account_expired,account_locked,password_expired,passwordc,idperfilautorizacion,idusuariotipo,extension,categoria
FROM usuario
where nombre like "DEMANOS%"; --"%FERNANDO%";--"%DORIAN%";--"%SANCHEZ NU%";-- "%HERNANDEZ PEREZ ALICIA%";

SELECT idusuario,correo,id_empleado,rfc,nombre,paterno,materno,password,idunidadmedica,idservicio,cedula,estatus,fecha_nacimiento,lugar_nacimiento,fecha_ingreso_inr,direccion,colonia,cp,municipio,ciudad,estado,telefono,turno,estadocivil,cargo,sexo,titulo,idperfilagenda,enabled,account_expired,account_locked,password_expired,passwordc,idperfilautorizacion,idusuariotipo,extension,categoria
FROM usuario
where correo is not null; -- like "%FERNANDO%";--"%DORIAN%";--"%SANCHEZ NU%";-- "%HERNANDEZ PEREZ ALICIA%";

Maria del Socorro Zarazua Perez



SELECT idusuario,idperfil
  FROM usuario_perfil
 where idusuario = 10071; --6648; --9427; --10041;


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
 where idusuario = 10053;

SELECT idusuario,rfc,nombre,paterno,materno,password,idunidadmedica,idservicio,cedula,estatus,fecha_nacimiento,lugar_nacimiento,fecha_ingreso_inr,direccion,colonia,cp,municipio,ciudad,estado,telefono,turno,estadocivil,cargo,sexo,titulo,idperfilagenda,enabled,account_expired,account_locked,password_expired,passwordc,idperfilautorizacion,idusuariotipo,id_empleado,extension,correo,categoria,idusuariocapturo,idusuariomodifico 
  FROM usuario
 where rfc = 'CARL740622';

