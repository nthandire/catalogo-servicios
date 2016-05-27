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


select * 
  from ResguardoEntregaDetalle as d
 where d.idResguardo.id in (:codigos)
   and d.consecutivo <= 2     
   and d.idTipoanexotecnico is not null    
   and d.idEmpleado in (:empleados)  ;

select FIRST 10 * 
  from Resguardo_Entrega_Detalle
 where id_empleado = 3631;

select id_resguardo,codigo,fecha_minuta,comentarios,id_usuario,ip_terminal,id_area,id_recibe,id_entrega,id_evatecno,nombre_entrega,recibe,id_entradadetalle,numero_resguardoentrega,modificacion,fecha_modificacion,umis,id_pais,id_bienes,id_almacen
  from resguardo_entrega
 where id_resguardo = 304;



SELECT id_resguardo,id_resguardoentregadetalle,id_tipoanexotecnico,inventario,consecutivo,descripcion,observaciones,serie,estatus_bien,estatus_asignacion,id_baja,status_soptec,obs_baja,fecha_baja,id_empleado,id_usuario,fecha_modificacion,ip_terminal,modificacion,id_marca,des_modelo,id_ubicacion 
  FROM resguardo_entrega_detalle
 where id_resguardoentregadetalle in (15,16,2,4,10,27,4,14,2,4,10,7,3,11,1,1,1,1,183,141,2485,242,182,88,11,11,3215,7,16,3,2,3215,7,1993,45,78,707,1518,25,85,48,2157,1742,112,9963,185,3209,389,3013,1191,2463,389,1,3209,513,216,1563,456,456,425,143,143,29,12,666,555,555,3209,3209,521,3365,3365,3234,3563,3456,1288,76789890,3209,3621,5555,45885,5,5,1312,432,843,456,1731,3188,588,3188,3177,2171,384,1808,557,21,1571,1974,1560);

SELECT id_resguardo,id_resguardoentregadetalle,id_tipoanexotecnico,inventario,consecutivo,descripcion,observaciones,serie,estatus_bien,estatus_asignacion,id_baja,status_soptec,obs_baja,fecha_baja,id_empleado,id_usuario,fecha_modificacion,ip_terminal,modificacion,id_marca,des_modelo,id_ubicacion 
  FROM resguardo_entrega_detalle
 where id_resguardoentregadetalle in (1);

SELECT id_empleado,id_plaza,id_area,id_horario,id_especialidad,cenres,nomina,idrusp,strusp,consecutivo,cons_jefe,direccion,subdireccion,division,servicio,vacancia,t_plaza,status_plaza,rama,sinerhias,apsinerhias,espsinerhias,titulo,puesto_func,nivel,sueldo,s_sueldo,a_g,motivo,id_tit_plaza,cod_anterior,puesto_ant,ocup_ant,dias_lab,a_issste,a_sar,a_vivienda,actividades,observaciones,fech_ini,fech_fin,tarjeta,tolerancia,statusp,tipo_plaza,fecha_cap,fecha_um,terminal,id_usuario
  FROM v_movemp
 where id_empleado = 216;

 

SELECT id_area,num_area,des_area,cuerpo,piso,subdireccion,division,fecha_cap,id_usuario 
  FROM v_area
 where id_area = 60;

