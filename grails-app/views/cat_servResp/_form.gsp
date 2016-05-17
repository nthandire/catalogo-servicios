<%@ page import="mx.gob.inr.catservicios.Cat_servResp" %>


<style type="text/css">
  textArea { width: 412px; }
</style>


<div class="fieldtablecontain ${hasErrors(bean: cat_servRespInstance, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="cat_servResp.descripcion.label" default="Descripcion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="descripcion" maxlength="100" required="" value="${cat_servRespInstance?.descripcion}"/>
</div>

