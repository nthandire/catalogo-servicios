
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
