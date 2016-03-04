<%@ page import="mx.gob.inr.catservicios.MonitoreoDetalle" %>


<style type="text/css">
  textArea { width: 412px; }
</style>


<div class="fieldtablecontain ${hasErrors(bean: monitoreoDetalleInstance, field: 'estado', 'error')} required">
	<label for="estado">
		<g:message code="monitoreoDetalle.estado.label" default="Estado" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="estado" from="${monitoreoDetalleInstance.constraints.estado.inList}" required="" value="${fieldValue(bean: monitoreoDetalleInstance, field: 'estado')}" valueMessagePrefix="monitoreoDetalle.estado"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: monitoreoDetalleInstance, field: 'observaciones', 'error')} ">
	<label for="observaciones">
		<g:message code="monitoreoDetalle.observaciones.label" default="Observaciones" />
		
	</label>
	<g:textArea name="observaciones" cols="40" rows="5" maxlength="255" value="${monitoreoDetalleInstance?.observaciones}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: monitoreoDetalleInstance, field: 'bitacoradetalle', 'error')} required">
	<label for="bitacoradetalle">
		<g:message code="monitoreoDetalle.bitacoradetalle.label" default="Bitacoradetalle" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="bitacoradetalle" name="bitacoradetalle.id" from="${mx.gob.inr.catservicios.BitacoraDetalle.list()}" optionKey="id" required="" value="${monitoreoDetalleInstance?.bitacoradetalle?.id}" class="many-to-one"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: monitoreoDetalleInstance, field: 'monitoreo', 'error')} required">
	<label for="monitoreo">
		<g:message code="monitoreoDetalle.monitoreo.label" default="Monitoreo" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="monitoreo" name="monitoreo.id" from="${mx.gob.inr.catservicios.Monitoreo.list()}" optionKey="id" required="" value="${monitoreoDetalleInstance?.monitoreo?.id}" class="many-to-one"/>
</div>

