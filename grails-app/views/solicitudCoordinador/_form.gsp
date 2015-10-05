<%@ page import="mx.gob.inr.catservicios.*" %>


<style type="text/css">
  textArea { width: 412px; }
</style>


<div class="fieldtablecontain ${hasErrors(bean: solicitudDetalleInstance, field: 'idServcat', 'error')} ">
	<label for="idServcat">
		<g:message code="solicitudDetalle.idServcat.label" default="Categoría" />
	</label>
	<g:select id="idServcat" name="idServcat.id" from="${mx.gob.inr.catservicios.Cat_servCat.list()}" optionKey="id" value="${solicitudDetalleInstance?.idServcat?.id}" class="many-to-one" noSelection="['': '']" disabled="true"/>
</div>

<div class="fieldtablecontain">
  <label for="nombre-label">
    <g:message code="solicitud.nombre.label" default="Solicitante"/>
  </label>
  <g:field type="text" name="nombre.no" disabled="true"
    value="${Usuario.get(solicitudDetalleInstance?.idSolicitud?.idSolicitante)}"/>
</div>

<div class="fieldtablecontain">
  <label for="telefono-label">
    <g:message code="solicitud.telefono.label" default="Extensión" />
  </label>
  <g:field type="text" name="telefono.no" disabled="true"
    value="solicitudDetalleInstance?.idSolicitud?.idSolicitante.extensión"/>
</div>

<div class="fieldtablecontain">
  <label for="nombre-label">
    <g:message code="solicitud.nombre.label" default="Autorizante"/>
  </label>
  <g:field type="text" name="autoriza.no" disabled="true"
    value="${Usuario.get(solicitudDetalleInstance?.idSolicitud?.idAutoriza)}"/>
</div>

<div class="fieldtablecontain">
  <label for="telefono-label">
    <g:message code="solicitud.telefono.label" default="Extensión" />
  </label>
  <g:field type="text" name="telefonoAutoriza.no" disabled="true"
    value="solicitudDetalleInstance?.idSolicitud?.idAutoriza.extensión"/>
</div>

<g:if test="${solicitudDetalleInstance?.idSolicitud?.idVb}">
  <div class="fieldtablecontain">
    <label for="nombre-label">
      <g:message code="solicitud.nombre.label" default="Visto Bueno"/>
    </label>
    <g:field type="text" name="vobo.no" disabled="true"
      value="${Usuario.get(solicitudDetalleInstance?.idSolicitud?.idVb)}"/>
  </div>

  <div class="fieldtablecontain">
    <label for="telefono-label">
      <g:message code="solicitud.telefono.label" default="Extensión" />
    </label>
    <g:field type="text" name="telefonoVobo.no" disabled="true"
      value="solicitudDetalleInstance?.idSolicitud?.idVb.extensión"/>
  </div>
</g:if>

<div class="fieldtablecontain ${hasErrors(bean: solicitudDetalleInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="solicitudDetalle.descripcion.label" default="Descripcion" />
	</label>
	<g:textArea name="descripcion" cols="40" rows="5" maxlength="3000" value="${solicitudDetalleInstance?.descripcion}" disabled="true"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: solicitudDetalleInstance, field: 'idPrograma', 'error')} ">
  <label for="idPrograma">
    <g:message code="solicitudDetalle.idPrograma.label" default="Estado de cierre" />
  </label>
  <g:select id="idPrograma" name="idPrograma.id" from="${mx.gob.inr.catservicios.CatPrograma.list()}" optionKey="id" value="${solicitudDetalleInstance?.idPrograma?.id}" class="many-to-one" noSelection="['': '']" disabled="true"/>
</div>

<g:if test="${solicitudDetalleInstance?.idResguardoentregadetalle}">
<div class="fieldtablecontain">
  <label for="resguardo">
    <g:message code="solicitudDetalle.idResguardoentregadetalle.label" default="Equipo" />
  </label>
  <g:select id="idResguardoentregadetalle" name="idResguardoentregadetalle"
    from="${ResguardoEntregaDetalle.executeQuery('from ResguardoEntregaDetalle d where exists( from ResguardoEntrega r where r.id = d.idResguardo and r.codigo like ?)', "515%")}"
      optionKey="id" value="${solicitudDetalleInstance?.idResguardoentregadetalle}"
      class="many-to-one" disabled="true"/>
</div>
</g:if>

<div class="fieldtablecontain ${hasErrors(bean: solicitudDetalleInstance, field: 'idTecnico', 'error')} ">
  <label for="idTecnico">
    <g:message code="solicitudDetalle.idTecnico.label" default="Tecnico" />
  </label>
  <g:select id="idTecnico" name="idTecnico" required="true"
    from="${tecnicos}"
      optionKey="id" value="${solicitudDetalleInstance?.idTecnico}" class="many-to-one"
      noSelection="['': '']"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: solicitudDetalleInstance, field: 'descripcionTecnica', 'error')} ">
  <label for="descripcionTecnica">
    <g:message code="solicitudDetalle.descripcionTecnica.label" default="Descripción Técnica" />
  </label>
  <g:textArea name="descripcionTecnica" cols="40" rows="5" maxlength="3000"
    value="${solicitudDetalleInstance?.descripcionTecnica}" disabled="true"/>
</div>

<g:if test="${solicitudDetalleInstance?.prioridad}">
<div class="fieldtablecontain ${hasErrors(bean: solicitudDetalleInstance, field: 'prioridad', 'error')}">
  <label for="prioridad">
    <g:message code="solicitudDetalle.prioridad.label" default="Prioridad" />
  </label>
  <g:select id="prioridad" name="prioridad" disabled="false" from="${[0, 1, 2, 3]}"
    valueMessagePrefix="intensidad.valor"
    value="${solicitudDetalleInstance.prioridad}" disabled="true"/>
</div>
</g:if>
