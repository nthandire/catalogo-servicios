
SELECT idusuario,idperfil FROM "informix"."usuario_perfil";

SELECT idperfil, count(*) FROM usuario_perfil GROUP BY idperfil;

