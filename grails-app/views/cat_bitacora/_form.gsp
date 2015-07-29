<%@ page import="mx.gob.inr.catservicios.Cat_bitacora" %>


<style type="text/css">
  textArea { width: 412px; }
</style>


<div class="fieldtablecontain ${hasErrors(bean: cat_bitacoraInstance, field: 'no_solicitud', 'error')} required">
	<label for="no_solicitud">
		<g:message code="cat_bitacora.no_solicitud.label" default="Nosolicitud" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="no_solicitud" type="number" value="${cat_bitacoraInstance.no_solicitud}" required=""/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: cat_bitacoraInstance, field: 'servicio', 'error')} required">
	<label for="servicio">
		<g:message code="cat_bitacora.servicio.label" default="Servicio" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="servicio" name="servicio.id" from="${mx.gob.inr.catservicios.Cat_serv.list()}" optionKey="id" required="" value="${cat_bitacoraInstance?.servicio?.id}" class="many-to-one"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: cat_bitacoraInstance, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="cat_bitacora.descripcion.label" default="Descripcion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="descripcion" cols="40" rows="5" maxlength="1000" required="" value="${cat_bitacoraInstance?.descripcion}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: cat_bitacoraInstance, field: 'observaciones', 'error')} ">
	<label for="observaciones">
		<g:message code="cat_bitacora.observaciones.label" default="Observaciones" />
		
	</label>
	<g:textArea name="observaciones" cols="40" rows="5" maxlength="3000" value="${cat_bitacoraInstance?.observaciones}"/>
</div>

