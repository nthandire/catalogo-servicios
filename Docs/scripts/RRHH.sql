
SELECT idusuario,idperfil FROM "informix"."usuario_perfil";

SELECT idperfil, count(*) FROM usuario_perfil GROUP BY idperfil;

SELECT idusuario,rfc,nombre,paterno,materno,password,idunidadmedica,idservicio,cedula,estatus,fecha_nacimiento,lugar_nacimiento,fecha_ingreso_inr,direccion,colonia,cp,municipio,ciudad,estado,telefono,turno,estadocivil,cargo,sexo,titulo,idperfilagenda,enabled,account_expired,account_locked,password_expired,passwordc,idperfilautorizacion,idusuariotipo FROM "informix"."usuario";


SELECT idusuario, rfc,nombre,paterno,materno,password,fecha_nacimiento, idusuariotipo FROM usuario where rfc = 'SAIH940101';

SELECT * FROM usuario where rfc = 'SAIH940101';
SELECT * FROM usuario where idusuario = 6558;


-- en H2
INSERT INTO USUARIO
               ( IDUSUARIO , RFC ,              ESTATUS , password  ,                                                                                                             account_expired, account_locked, password_expired )
VALUES (6558,               'SAIH940101', 'A',              '3f79bb7b435b05321651daefd374cdc681dc06faa65e374e38337b88ca046dea' , false,                   false,                    false);


INSERT INTO perfil
        (idperfil, desc_perfil)
 VALUES (57, "ServTICs_ADMIN");

INSERT INTO perfil
        (idperfil, desc_perfil)
 VALUES (56, "ServTICs_COORDINADOR");

INSERT INTO perfil
        (idperfil, desc_perfil)
 VALUES (58, "ROLE_USUARIO");

INSERT INTO perfil
        (idperfil, desc_perfil)
 VALUES (59, "ROLE_TECNICO");

-- en H2
INSERT INTO perfil
        (idperfil, desc_perfil, perfil_bi)
 VALUES (57, 'ServTICs_ADMIN', false);
INSERT INTO perfil
        (idperfil, desc_perfil, perfil_bi)
 VALUES (56, 'ServTICs_COORDINADOR', false);


INSERT INTO table_name (column1,column2,column3,...)
VALUES (value1,value2,value3,...);

SELECT MAX(idperfil) FROM perfil;

SELECT * FROM perfil where idperfil = 57;


SELECT idusuario,idperfil FROM usuario_perfil WHERE idperfil = 57;

INSERT INTO usuario_perfil
        (idusuario, idperfil)
 VALUES (6558, 57);


SELECT idusuario,passwordfirma,nombre,tipo,tamanio,datos,bloqueada,intentosfallidos FROM "informix"."firmadigital";

SELECT count(*) FROM firmadigital;

SELECT * FROM firmadigital where idusuario = 6558;



select * from perfil where idperfil > 50;

update perfil set desc_perfil = 'ROLE_ADMIN' where idperfil = 57;
select * from perfil where idperfil = 57;


update perfil set desc_perfil = 'ROLE_COORDINADOR' where idperfil = 56;
select * from perfil where idperfil = 56;

select * from perfil where desc_perfil like 'ROLE_%';

select * from perfil where idperfil = 56;


SELECT idusuario,rfc,nombre,paterno,materno,password,idunidadmedica,idservicio,cedula,estatus,fecha_nacimiento,lugar_nacimiento,fecha_ingreso_inr,direccion,colonia,cp,municipio,ciudad,estado,telefono,turno,estadocivil,cargo,sexo,titulo,idperfilagenda,enabled,account_expired,account_locked,password_expired,passwordc,idperfilautorizacion,idusuariotipo FROM "informix"."usuario";

SELECT * FROM usuario where rfc like 'usu%';

SELECT * FROM usuario where idusuario = 6558;

SELECT * FROM usuario where idusuario >= 9574;

SELECT * FROM firmadigital where idusuario >= 9574;

SELECT max(idusuario) FROM usuario;


insert into firmadigital (idusuario,passwordfirma,nombre,tipo,          tamanio,   datos,bloqueada,intentosfallidos)
     select               u.idusuario, 1234,      u.rfc, 'image/bmp', f.tamanio, f.datos, 0,          0
       from usuario u, firmadigital f
      where f.idusuario = 7209
        and u.idusuario >= 9574;


SELECT idusuario,passwordfirma,nombre,tipo,tamanio,datos,bloqueada,intentosfallidos FROM "informix"."firmadigital";

SELECT * FROM usuario where idusuario >= 9580;



SELECT idusuario,rfc,nombre,paterno,materno,password,idunidadmedica,idservicio,cedula,estatus,fecha_nacimiento,lugar_nacimiento,fecha_ingreso_inr,direccion,colonia,cp,municipio,ciudad,estado,telefono,turno,estadocivil,cargo,sexo,titulo,idperfilagenda,enabled,account_expired,account_locked,password_expired,passwordc,idperfilautorizacion,idusuariotipo FROM usuario;



SELECT * FROM firmadigital where idusuario >= 9574





---------------------------------


SELECT * FROM usuario where idusuario >= 9574

SELECT * FROM usuario where idusuario = 6558

select * from perfil where idperfil >= 56


update perfil set desc_perfil = 'ROLE_SAST_APROBADOR' where idperfil = 56;

update perfil set desc_perfil = 'ROLE_SAST_ADMIN' where idperfil = 57;
update perfil set desc_perfil = 'ROLE_SAST_USUARIO' where idperfil = 58;
update perfil set desc_perfil = 'ROLE_SAST_TECNICO' where idperfil = 59;

insert into perfil (idperfil,desc_perfil,perfil_bi)
            values (60, 'ROLE_SAST_GESTOR', 'f')

insert into perfil (idperfil,desc_perfil,perfil_bi)
            values (61, 'ROLE_SAST_TECNICO_MESA_SERVICIO', 'f')


SELECT idperfil,desc_perfil,perfil_bi FROM "informix"."perfil";


select * from usuario_perfil where idperfil >= 56

select idusuario, rfc from usuario u
 where exists (
   select * from usuario_perfil up
    where up.idusuario = u.idusuario
      and exists (
        select * from perfil p
         where p.idperfil = up.idperfil
           and p.idperfil >= 56))




select * from usuario_perfil where idusuario = 9583

update usuario_perfil set idperfil = 60 where idusuario = 9583


INSERT INTO usuario_perfil
        (idusuario, idperfil)
 VALUES (9580, 56);
INSERT INTO usuario_perfil
        (idusuario, idperfil)
 VALUES (9581, 56);




select u.idusuario, u.rfc, p.desc_perfil, p.idperfil
  from usuario u,
       usuario_perfil up,
       perfil p
 where p.idperfil >= 56
   --and u.idusuario in (9584,9585,6558,9586,9583)
   and p.idperfil = up.idperfil
   and up.idusuario = u.idusuario


select *
  from usuario u
 where u.idusuario in (9584,9585,6558,9586,9583)


select * from usuario_perfil where idusuario = 9584;

INSERT INTO usuario_perfil
        (idusuario, idperfil)
 VALUES (6558, 58);

INSERT INTO usuario_perfil
        (idusuario, idperfil)
 VALUES (9584, 58);

INSERT INTO usuario_perfil
        (idusuario, idperfil)
 VALUES (9585, 58);
INSERT INTO usuario_perfil
        (idusuario, idperfil)
 VALUES (9586, 58);
INSERT INTO usuario_perfil
        (idusuario, idperfil)
 VALUES (9583, 58);

INSERT INTO usuario_perfil
        (idusuario, idperfil)
 VALUES (9582, 61);
