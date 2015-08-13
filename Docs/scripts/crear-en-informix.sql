CREATE TABLE "informix"."bitacora"
(
   id_bitacora integer PRIMARY KEY,
   tipo_bitacora varchar(100),
   des_bitacora varchar(255),
   estado_bitacora char(1)
)
;
CREATE UNIQUE INDEX  100_2 ON "informix"."bitacora"(id_bitacora)
;




----------------------------------


CREATE TABLE "informix"."bitacora_detalle"
(
   id_bitacoradetalle integer PRIMARY KEY,
   id_bitacora integer,
   des_bitacoradetalle varchar(255),
   estado_bitacoradetalle char(1)
)
;
ALTER TABLE "informix"."bitacora_detalle"
ADD CONSTRAINT r_163
FOREIGN KEY (id_bitacora)
REFERENCES "informix"."bitacora"(id_bitacora)
;
CREATE INDEX  101_49 ON "informix"."bitacora_detalle"(id_bitacora)
;
CREATE UNIQUE INDEX  101_4 ON "informix"."bitacora_detalle"(id_bitacoradetalle)
;


-----------------------------------


CREATE TABLE "informix"."cat_estado"
(
   id_estado integer PRIMARY KEY,
   des_estado varchar(50)
)
;
CREATE UNIQUE INDEX  102_6 ON "informix"."cat_estado"(id_estado)
;


--------------------------------



CREATE TABLE "informix"."cat_programa"
(
   id_programa integer PRIMARY KEY,
   des_programa varchar(255),
   estado_programa char(1)
)
;
CREATE UNIQUE INDEX  103_8 ON "informix"."cat_programa"(id_programa)
;




----------------------------------




CREATE TABLE "informix"."cat_tiposervcio"
(
   id_tiposervicio integer PRIMARY KEY,
   des_tiposervicio varchar(100),
   estado char(1)
)
;
CREATE UNIQUE INDEX  112_26 ON "informix"."cat_tiposervcio"(id_tiposervicio)
;



----------------------------------------


CREATE TABLE "informix"."incidente"
(
   id_incidente integer PRIMARY KEY,
   id_sistema integer,
   id_resguardoentregadetalle integer,
   fecha_incidente datetime year to fraction(5),
   numero_incidente integer,
   estado_incidente char(1),
   id_reporta integer,
   id_serv integer,
   id_servfinal integer,
   descripcion text,
   nivel integer,
   id_servresp integer,
   id_captura integer,
   id_nivel1 integer,
   fecha_nivel1 datetime year to fraction(5),
   firma_nivel1 smallint,
   solucion_nivel1 text,
   fecha_solnivel1 datetime year to fraction(5),
   id_asignanivel2 integer,
   id_nivel2 integer,
   fecha_nivel2 datetime year to fraction(5),
   firma_nivel2 smallint,
   solucion_nivel2 text,
   fecha_solnivel2 datetime year to fraction(5),
   id_asignanivel3 integer,
   id_nivel3 integer,
   fecha_nivel3 datetime year to fraction(5),
   firma_nivel3 smallint,
   solucion_nivel3 text,
   fecha_solnivel3 datetime year to fraction(5),
   p01 integer,
   p02 integer,
   p03 integer,
   p04 integer,
   id_programa integer,
   fecha_modificacion datetime year to fraction(5),
   modificacion integer,
   ip_terminal char(15)
)
;
ALTER TABLE "informix"."incidente"
ADD CONSTRAINT r_177
FOREIGN KEY (id_servfinal)
REFERENCES "informix"."cat_serv"(id_serv)
;
ALTER TABLE "informix"."incidente"
ADD CONSTRAINT r_162
FOREIGN KEY (id_servresp)
REFERENCES "informix"."cat_servresp"(id_servresp)
;
ALTER TABLE "informix"."incidente"
ADD CONSTRAINT r_161
FOREIGN KEY (id_serv)
REFERENCES "informix"."cat_serv"(id_serv)
;
ALTER TABLE "informix"."incidente"
ADD CONSTRAINT r_175
FOREIGN KEY (id_programa)
REFERENCES "informix"."cat_programa"(id_programa)
;
ALTER TABLE "informix"."incidente"
ADD CONSTRAINT r_180
FOREIGN KEY (id_sistema)
REFERENCES "informix"."cat_sistema"(id_sistema)
;
CREATE INDEX  113_64 ON "informix"."incidente"
(
  descripcion,
  firma_nivel1,
  firma_nivel2,
  firma_nivel3,
  solucion_nivel1,
  solucion_nivel2,
  solucion_nivel3,
  id_servresp
)
;
CREATE INDEX  113_63 ON "informix"."incidente"
(
  descripcion,
  firma_nivel1,
  firma_nivel2,
  firma_nivel3,
  solucion_nivel1,
  solucion_nivel2,
  solucion_nivel3,
  id_serv
)
;
CREATE INDEX  113_67 ON "informix"."incidente"
(
  descripcion,
  firma_nivel1,
  firma_nivel2,
  firma_nivel3,
  solucion_nivel1,
  solucion_nivel2,
  solucion_nivel3,
  id_sistema
)
;
CREATE INDEX  113_66 ON "informix"."incidente"
(
  descripcion,
  firma_nivel1,
  firma_nivel2,
  firma_nivel3,
  solucion_nivel1,
  solucion_nivel2,
  solucion_nivel3,
  id_servfinal
)
;
CREATE INDEX  113_65 ON "informix"."incidente"
(
  descripcion,
  firma_nivel1,
  firma_nivel2,
  firma_nivel3,
  solucion_nivel1,
  solucion_nivel2,
  solucion_nivel3,
  id_programa
)
;
CREATE UNIQUE INDEX  113_28 ON "informix"."incidente"
(
  descripcion,
  firma_nivel1,
  firma_nivel2,
  firma_nivel3,
  solucion_nivel1,
  solucion_nivel2,
  solucion_nivel3,
  id_incidente
)
;




-----------------------------------



CREATE TABLE "informix"."incidente_archivoadjunto"
(
   id integer PRIMARY KEY,
   id_incidente integer,
   datos byte,
   nombre varchar(255),
   tamanio integer,
   tipo varchar(20),
   id_usuario integer,
   fecha_modificacion datetime year to fraction(5),
   modificacion integer,
   ip_terminal char(15)
)
;
ALTER TABLE "informix"."incidente_archivoadjunto"
ADD CONSTRAINT r_171
FOREIGN KEY (id_incidente)
REFERENCES "informix"."incidente"(id_incidente)
;
CREATE INDEX  114_68 ON "informix"."incidente_archivoadjunto"(id_incidente)
;
CREATE UNIQUE INDEX  114_30 ON "informix"."incidente_archivoadjunto"(id)
;



------------------------------



CREATE TABLE "informix"."incidente_laboratorio"
(
   id_laboratorio integer PRIMARY KEY,
   id_incidente integer,
   fecha_laboratorio datetime year to fraction(5),
   numero_laboratorio integer,
   id_estado integer,
   id_tiposervicio integer,
   falla_tecnica varchar(255),
   solucion text,
   refacciones text,
   id_usuario integer,
   id_proveedor integer,
   fecha_reporte datetime year to fraction(5),
   numero_reporte varchar(30),
   fecha_salida date,
   fecha_entrega date,
   fecha_reparacion date,
   tecnico varchar(255),
   observaciones text,
   fecha_modificacion datetime year to fraction(5),
   modificacion integer,
   ip_terminal char(15)
)
;
ALTER TABLE "informix"."incidente_laboratorio"
ADD CONSTRAINT r_169
FOREIGN KEY (id_incidente)
REFERENCES "informix"."incidente"(id_incidente)
;
ALTER TABLE "informix"."incidente_laboratorio"
ADD CONSTRAINT r_168
FOREIGN KEY (id_proveedor)
REFERENCES "informix"."proveedor"(id_proveedor)
;
ALTER TABLE "informix"."incidente_laboratorio"
ADD CONSTRAINT r_179
FOREIGN KEY (id_estado)
REFERENCES "informix"."cat_estado"(id_estado)
;
ALTER TABLE "informix"."incidente_laboratorio"
ADD CONSTRAINT r_167
FOREIGN KEY (id_tiposervicio)
REFERENCES "informix"."cat_tiposervcio"(id_tiposervicio)
;
CREATE INDEX  115_70 ON "informix"."incidente_laboratorio"
(
  observaciones,
  refacciones,
  solucion,
  id_proveedor
)
;
CREATE INDEX  115_69 ON "informix"."incidente_laboratorio"
(
  observaciones,
  refacciones,
  solucion,
  id_tiposervicio
)
;
CREATE UNIQUE INDEX  115_32 ON "informix"."incidente_laboratorio"
(
  observaciones,
  refacciones,
  solucion,
  id_laboratorio
)
;
CREATE INDEX  115_72 ON "informix"."incidente_laboratorio"
(
  observaciones,
  refacciones,
  solucion,
  id_estado
)
;
CREATE INDEX  115_71 ON "informix"."incidente_laboratorio"
(
  observaciones,
  refacciones,
  solucion,
  id_incidente
)
;





-----------------------------------
       Monitoreo
-----------------------------------










CREATE TABLE "informix"."monitoreo"
(
   id_monitoreo integer PRIMARY KEY,
   fecha_monitoreo datetime year to fraction(5),
   numero_monitoreo char(18),
   id_bitacora integer,
   estado_monitoreo char(1),
   semaforo integer,
   nota text,
   id_usuario integer,
   fecha_modificacion datetime year to fraction(5),
   modificacion integer,
   ip_terminal char(15)
)
;
ALTER TABLE "informix"."monitoreo"
ADD CONSTRAINT r_164
FOREIGN KEY (id_bitacora)
REFERENCES "informix"."bitacora"(id_bitacora)
;
CREATE INDEX  124_73 ON "informix"."monitoreo"
(
  nota,
  id_bitacora
)
;
CREATE UNIQUE INDEX  116_34 ON "informix"."monitoreo"
(
  nota,
  id_monitoreo
)
;






-----------------------------





CREATE TABLE "informix"."monitoreo_archivoadjunto"
(
   id integer PRIMARY KEY,
   id_monitoreo integer,
   datos byte,
   nombre varchar(255),
   tamanio integer,
   tipo varchar(20),
   id_usuario integer,
   fecha_modificacion datetime year to fraction(5),
   modificacion integer,
   ip_terminal char(15)
)
;
ALTER TABLE "informix"."monitoreo_archivoadjunto"
ADD CONSTRAINT r_170
FOREIGN KEY (id_monitoreo)
REFERENCES "informix"."monitoreo"(id_monitoreo)
;
CREATE INDEX  117_74 ON "informix"."monitoreo_archivoadjunto"(id_monitoreo)
;
CREATE UNIQUE INDEX  117_36 ON "informix"."monitoreo_archivoadjunto"(id)
;






------------------------------------




CREATE TABLE "informix"."monitoreo_detalle"
(
   id_monitoredetalle integer PRIMARY KEY,
   id_monitoreo integer,
   id_bitacoradetalle integer,
   estado integer,
   observaciones varchar(255)
)
;
ALTER TABLE "informix"."monitoreo_detalle"
ADD CONSTRAINT r_165
FOREIGN KEY (id_monitoreo)
REFERENCES "informix"."monitoreo"(id_monitoreo)
;
ALTER TABLE "informix"."monitoreo_detalle"
ADD CONSTRAINT r_166
FOREIGN KEY (id_bitacoradetalle)
REFERENCES "informix"."bitacora_detalle"(id_bitacoradetalle)
;
CREATE INDEX  118_76 ON "informix"."monitoreo_detalle"(id_bitacoradetalle)
;
CREATE INDEX  118_75 ON "informix"."monitoreo_detalle"(id_monitoreo)
;
CREATE UNIQUE INDEX  118_38 ON "informix"."monitoreo_detalle"(id_monitoredetalle)
;



------------------------------------




CREATE TABLE "informix"."problema"
(
   id_problema char(18) PRIMARY KEY,
   fuente varchar(50),
   id_fuente integer,
   fecha_problema datetime year to fraction(5),
   folio integer,
   observaciones text,
   solucion text,
   fecha_solucion datetime year to fraction(5),
   resolvio varchar(255),
   id_usuario integer,
   fecha_modificacion datetime year to fraction(5),
   modificacion integer,
   ip_terminal char(15)
)
;
CREATE UNIQUE INDEX  119_40 ON "informix"."problema"
(
  observaciones,
  solucion,
  id_problema
)
;




-------------------------





CREATE TABLE "informix"."proveedor"
(
   id_proveedor integer PRIMARY KEY,
   des_proveedor varchar(255),
   representante varchar(255),
   telefono varchar(50)
)
;
CREATE UNIQUE INDEX  120_42 ON "informix"."proveedor"(id_proveedor)
;










-------------------------



CREATE TABLE "informix"."solicitud"
(
   id_solicitud integer PRIMARY KEY,
   fecha_solicitud datetime year to fraction(5),
   numero_solicitud integer,
   estado_solicitud char(1),
   justificacion text,
   id_solicitante integer,
   id_autoriza integer,
   fecha_autoriza datetime year to fraction(5),
   id_vb integer,
   fecha_vb datetime year to fraction(5),
   p01 integer,
   p02 integer,
   p03 integer,
   p04 integer,
   fecha_modificacion datetime year to fraction(5),
   modificacion integer,
   ip_terminal char(15)
)
;
CREATE UNIQUE INDEX  121_44 ON "informix"."solicitud"
(
  justificacion,
  id_solicitud
)
;




-------------------------------------





CREATE TABLE "informix"."solicitud_archivoadjunto"
(
   id integer PRIMARY KEY,
   id_solicitud integer,
   datos byte,
   nombre varchar(255),
   tamanio integer,
   tipo varchar(20),
   id_usuario integer,
   fecha_modificacion datetime year to fraction(5),
   modificacion integer,
   ip_terminal char(15)
)
;
ALTER TABLE "informix"."solicitud_archivoadjunto"
ADD CONSTRAINT r_172
FOREIGN KEY (id_solicitud)
REFERENCES "informix"."solicitud"(id_solicitud)
;
CREATE UNIQUE INDEX  122_46 ON "informix"."solicitud_archivoadjunto"(id)
;
CREATE INDEX  122_77 ON "informix"."solicitud_archivoadjunto"(id_solicitud)
;






-------------------------------





CREATE TABLE "informix"."solicitud_detalle"
(
   id_solicituddetalle integer PRIMARY KEY,
   id_solicitud integer,
   id_serv integer,
   id_resguardoentregadetalle integer,
   estado_solictuddetalle char(1),
   descripcion text,
   solucion text,
   id_tecnico integer,
   fecha_solucion datetime year to fraction(5),
   id_programa integer,
   id_servcat integer,
   descripcion_tecnica text
)
;
ALTER TABLE "informix"."solicitud_detalle"
ADD CONSTRAINT r_160
FOREIGN KEY (id_solicitud)
REFERENCES "informix"."solicitud"(id_solicitud)
;
ALTER TABLE "informix"."solicitud_detalle"
ADD CONSTRAINT r_159
FOREIGN KEY (id_serv)
REFERENCES "informix"."cat_serv"(id_serv)
;
ALTER TABLE "informix"."solicitud_detalle"
ADD CONSTRAINT r_173
FOREIGN KEY (id_servcat)
REFERENCES "informix"."cat_servcat"(id_servcat)
;
ALTER TABLE "informix"."solicitud_detalle"
ADD CONSTRAINT r_174
FOREIGN KEY (id_programa)
REFERENCES "informix"."cat_programa"(id_programa)
;
CREATE INDEX  123_81 ON "informix"."solicitud_detalle"
(
  descripcion,
  descripcion_tecnica,
  solucion,
  id_programa
)
;
CREATE INDEX  123_80 ON "informix"."solicitud_detalle"
(
  descripcion,
  descripcion_tecnica,
  solucion,
  id_servcat
)
;
CREATE INDEX  123_79 ON "informix"."solicitud_detalle"
(
  descripcion,
  descripcion_tecnica,
  solucion,
  id_solicitud
)
;
CREATE INDEX  123_78 ON "informix"."solicitud_detalle"
(
  descripcion,
  descripcion_tecnica,
  solucion,
  id_serv
)
;
CREATE UNIQUE INDEX  123_48 ON "informix"."solicitud_detalle"
(
  descripcion,
  descripcion_tecnica,
  solucion,
  id_solicituddetalle
)
;







