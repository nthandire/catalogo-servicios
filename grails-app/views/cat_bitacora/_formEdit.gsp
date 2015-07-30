<%@ page import="mx.gob.inr.catservicios.Cat_bitacora" %>

<g:javascript library='jquery' />

<style type="text/css">
  textArea { width: 750px }
</style>

<table class="table table-condensed">
	<tr>
		<td>

<div class="fieldtablecontain ${hasErrors(bean: cat_bitacoraInstance, field: 'no_solicitud', 'error')} required">
	<label for="no_solicitud">
		<g:message code="cat_bitacora.no_solicitud.label" default="Nosolicitud" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="no_solicitud" type="number" value="${cat_bitacoraInstance.no_solicitud}" required=""/>
</div>

		</td>
		<td>

<div class="fieldtablecontain ${hasErrors(bean: cat_bitacoraInstance, field: 'responsable', 'error')} required">
	<label for="responsable">
		<g:message code="cat_bitacora.responsable.label" default="Responsable" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="responsable" name="responsable.id" from="${mx.gob.inr.catservicios.Cat_servResp.list()}" optionKey="id" required="" value="${cat_bitacoraInstance?.responsable?.id}" class="many-to-one"/>
</div>

		</td>
		<td>

		</td>
	</tr>
	<tr>
		<td>

<div class="fieldcontain ${hasErrors(bean: cat_servInstance, field: 'servicio', 'error')} required">
	<label for="servicio">
		<g:message code="cat_serv.servCat.label" default="Cat" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="servCat" name="servCat.id" from="${mx.gob.inr.catservicios.Cat_servCat.list()}" optionKey="id" required="" value="${cat_bitacoraInstance?.servicio?.servSub?.servCat?.id}" class="many-to-one" disabled="true"/><!-- TODO: efientizar el from -->
</div>

		</td>
		<td>

<div class="fieldtablecontain ${hasErrors(bean: cat_bitacoraInstance, field: 'servicio', 'error')} required">
	<label for="servicio">
		<g:message code="cat_serv.servSub.label" default="Serv Sub" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="servSub" name="servSub.id" from="${mx.gob.inr.catservicios.Cat_servSub.list()}" optionKey="id" required="" value="${cat_bitacoraInstance?.servicio?.servSub?.id}" class="many-to-one" disabled="true"/><!-- TODO: efientizar el from -->
</div>

		</td>
		<td>

<div class="fieldtablecontain ${hasErrors(bean: cat_bitacoraInstance, field: 'servicio', 'error')} required">
	<label for="servicio">
		<g:message code="cat_bitacora.servicio.label" default="Servicio" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="servicio" name="servicio.id" from="${mx.gob.inr.catservicios.Cat_serv.list()}" optionKey="id" required="" value="${cat_bitacoraInstance?.servicio?.id}" class="many-to-one" disabled="true"/><!-- TODO: efientizar el from -->
</div>

		</td>

	</tr>
	<tr>
		<td colspan="3">

<div class="fieldtablecontain ${hasErrors(bean: cat_bitacoraInstance, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="cat_bitacora.descripcion.label" default="Descripcion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="descripcion" cols="40" rows="5" maxlength="1000" required="" value="${cat_bitacoraInstance?.descripcion}"/>
</div>

		</td>
	</tr>
	<tr>
		<td colspan="3">

<div class="fieldtablecontain ${hasErrors(bean: cat_bitacoraInstance, field: 'observaciones', 'error')} ">
	<label for="observaciones">
		<g:message code="cat_bitacora.observaciones.label" default="Observaciones" />
		
	</label>
	<g:textArea name="observaciones" cols="40" rows="5" maxlength="3000" value="${cat_bitacoraInstance?.observaciones}"/>
</div>

		</td>
	</tr>
</table>