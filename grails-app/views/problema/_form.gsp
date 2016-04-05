<%@ page import="mx.gob.inr.catservicios.Problema" %>


<style type="text/css">
  textArea { width: 412px; }
</style>


<div class="fieldtablecontain ${hasErrors(bean: problemaInstance, field: 'observaciones', 'error')} ">
	<label for="observaciones">
		<g:message code="problema.observaciones.label" default="Observaciones" />

	</label>
	<g:textArea name="observaciones" cols="40" rows="5" maxlength="3000" value="${problemaInstance?.observaciones}"/>
</div>

