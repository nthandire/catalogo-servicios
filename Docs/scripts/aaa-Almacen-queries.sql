select *
  from ResguardoEntregaDetalle as d
 where d.idResguardo.id in (:codigos)      
   and d.consecutivo <= 2
   and d.idTipoanexotecnico is not null
   and d.idEmpleado in (4080);

select *
  from Resguardo_Entrega_Detalle as d
 where d.consecutivo <= 2
   and d.id_Tipoanexotecnico is not null
   and d.id_Empleado in (4080,8301);

select id_empleado, *
  from Resguardo_Entrega_Detalle as d
 where d.consecutivo <= 2
   and d.id_Tipoanexotecnico is not null
   and d.id_Empleado is not null;

select distinct id_empleado
  from Resguardo_Entrega_Detalle as d
 where d.consecutivo <= 2
   and d.id_Tipoanexotecnico is not null
   and d.id_Empleado is not null;



SELECT id_empleado,rfc,email,curp,nss,fecha_nac,paterno,materno,nombre,genero,edo_civil,sindicato,calle,n_ext,n_int,cp,colonia,id_mpio,id_estado,telefono,celular,fi_ssa,fi_gob,fi_inr,fi_plaza,status,tipo_emp,cta_bco,id_escolar,ext,fecha_reg,fecha_mod,id_usuario,ent_nac
  FROM v_empleado
 where email is null;

SELECT id_empleado,rfc,email,curp,nss,fecha_nac,paterno,materno,nombre,genero,edo_civil,sindicato,calle,n_ext,n_int,cp,colonia,id_mpio,id_estado,telefono,celular,fi_ssa,fi_gob,fi_inr,fi_plaza,status,tipo_emp,cta_bco,id_escolar,ext,fecha_reg,fecha_mod,id_usuario,ent_nac
  FROM v_empleado
 where rfc in ('MAHA850708','PECR790224', 'CARL740622');

SELECT id_empleado,rfc,email,curp,nss,fecha_nac,paterno,materno,nombre,genero,edo_civil,sindicato,calle,n_ext,n_int,cp,colonia,id_mpio,id_estado,telefono,celular,fi_ssa,fi_gob,fi_inr,fi_plaza,status,tipo_emp,cta_bco,id_escolar,ext,fecha_reg,fecha_mod,id_usuario,ent_nac
  FROM v_empleado
 where PATERNO in ('ESPINO','ZARAZUA', 'DEMANOS');

SELECT id_empleado,rfc,email,curp,nss,fecha_nac,paterno,materno,nombre,genero,edo_civil,sindicato,calle,n_ext,n_int,cp,colonia,id_mpio,id_estado,telefono,celular,fi_ssa,fi_gob,fi_inr,fi_plaza,status,tipo_emp,cta_bco,id_escolar,ext,fecha_reg,fecha_mod,id_usuario,ent_nac
  FROM v_empleado
 where id_empleado in (1759,370,413,3631,216,4353);

SELECT count(*)
  FROM v_empleado
 where email is not null;

SELECT count(*)
  FROM v_empleado;

