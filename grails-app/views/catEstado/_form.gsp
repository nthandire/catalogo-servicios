<%@ page import="mx.gob.inr.catservicios.CatEstado" %>


<style type="text/css">
  textArea { width: 412px; }
</style>


<div class="fieldtablecontain ${hasErrors(bean: catEstadoInstance, field: 'desEstado', 'error')} ">
	<label for="desEstado">
		<g:message code="catEstado.desEstado.label" default="Des Estado" />
		
	</label>
	<g:textField name="desEstado" maxlength="50" value="${catEstadoInstance?.desEstado}"/>
</div>

