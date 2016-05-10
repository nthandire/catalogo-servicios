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
