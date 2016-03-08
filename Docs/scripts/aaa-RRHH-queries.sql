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

 


