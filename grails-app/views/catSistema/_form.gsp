<%@ page import="mx.gob.inr.catservicios.CatSistema" %>


<style type="text/css">
  textArea { width: 412px; }
</style>


<div class="fieldtablecontain ${hasErrors(bean: catSistemaInstance, field: 'desSistema', 'error')} ">
	<label for="desSistema">
		<g:message code="catSistema.desSistema.label" default="Des Sistema" />
		
	</label>
	<g:textField name="desSistema" maxlength="50" value="${catSistemaInstance?.desSistema}"/>
</div>

