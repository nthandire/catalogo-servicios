SELECT id_resguardo,fecha_minuta,comentarios,id_usuario,ip_terminal,id_area,id_recibe,id_entrega,id_evatecno,nombre_entrega,recibe,id_entradadetalle,numero_resguardoentrega,modificacion,fecha_modificacion,umis,id_pais,id_bienes,codigo,id_almacen FROM "informix"."resguardo_entrega";

SELECT count(*) FROM resguardo_entrega where codigo like '515%';

select count(*)
  FROM Resguardo_Entrega_Detalle d
 where exists(
   select *
     from Resguardo_Entrega r
    where r.id_resguardo = d.id_resguardo
      and r.codigo like '515%');


SELECT id_resguardo,inventario,consecutivo,descripcion,observaciones,serie,estatus_bien,estatus_asignacion,id_baja,status_soptec,id_tipoanexotecnico,id_resguardoentregadetalle,obs_baja,fecha_baja,id_empleado,id_usuario,fecha_modificacion,ip_terminal,modificacion,id_marca,des_modelo FROM "informix"."resguardo_entrega_detalle";

SELECT first 27 to_char(inventario) as inve ,id_resguardo,inventario,consecutivo,descripcion,observaciones,serie,estatus_bien,estatus_asignacion,id_baja,status_soptec,id_tipoanexotecnico,id_resguardoentregadetalle,obs_baja,fecha_baja,id_empleado,id_usuario,fecha_modificacion,ip_terminal,modificacion,id_marca,des_modelo
  FROM resguardo_entrega_detalle
  where to_char(inventario) like '11%';

SELECT first 7 to_char(inventario) as inve ,id_resguardo,inventario,consecutivo,descripcion,observaciones,serie,estatus_bien,estatus_asignacion,id_baja,status_soptec,id_tipoanexotecnico,id_resguardoentregadetalle,obs_baja,fecha_baja,id_empleado,id_usuario,fecha_modificacion,ip_terminal,modificacion,id_marca,des_modelo
  FROM resguardo_entrega_detalle
  where inventario like '11%';

select distinct des_modelo
  FROM Resguardo_Entrega_Detalle d
 where exists(
   select *
     from Resguardo_Entrega r
    where r.id_resguardo = d.id_resguardo
      and d.id_marca = 2
      and r.codigo like '515%');

SELECT id_resguardo,fecha_minuta,comentarios,id_usuario,ip_terminal,id_area,id_recibe,id_entrega,id_evatecno,nombre_entrega,recibe,id_entradadetalle,numero_resguardoentrega,modificacion,fecha_modificacion,umis,id_pais,id_bienes,codigo,id_almacen
  FROM resguardo_entrega
 where id_resguardo >= 1568;

