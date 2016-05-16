<%@ page import="mx.gob.inr.catservicios.Cat_servCob" %>


<style type="text/css">
  textArea { width: 412px; }
</style>


<div class="fieldtablecontain ${hasErrors(bean: cat_servCobInstance, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="cat_servCob.descripcion.label" default="Descripcion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="descripcion" maxlength="100" required="" value="${cat_servCobInstance?.descripcion}"/>
</div>

