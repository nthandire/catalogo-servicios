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
where rfc like "PEC%";


