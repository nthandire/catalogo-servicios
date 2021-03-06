<%@ page import="mx.gob.inr.catservicios.*" %>


<style type="text/css">
  textArea { width: 412px; }
</style>


<div class="fieldtablecontain ${hasErrors(bean: bitacoraDetalleInstance, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="bitacoraDetalle.descripcion.label" default="Descripcion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="descripcion" cols="40" rows="5" maxlength="255" required="" value="${bitacoraDetalleInstance?.descripcion}"/>
</div>

<g:if test="${bitacoraDetalleInstance.id}">
<div class="fieldtablecontain ${hasErrors(bean: bitacoraDetalleInstance, field: 'estado', 'error')} required">
	<label for="estado">
		<g:message code="bitacoraDetalle.estado.label" default="Estado" />
		<span class="required-indicator">*</span>
	</label>
  <g:select name="estado" from="${bitacoraDetalleInstance.constraints.estado.inList}" required="" value="${fieldValue(bean: bitacoraDetalleInstance, field: 'estado')}" valueMessagePrefix="cat_servCat.estado"/>
</div>

</g:if>

<div class="fieldtablecontain ${hasErrors(bean: bitacoraDetalleInstance, field: 'bitacora', 'error')} required">
	<label for="bitacora">
		<g:message code="bitacoraDetalle.bitacora.label" default="Sistema" />
	</label>
	<g:select id="bitacora" name="bitacora.id" from="${Sistema.list()}" optionKey="id" required="" value="${bitacoraDetalleInstance?.bitacora?.id}" class="many-to-one"/>
</div>

