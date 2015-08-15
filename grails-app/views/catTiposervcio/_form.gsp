<%@ page import="mx.gob.inr.catservicios.CatTiposervcio" %>


<style type="text/css">
  textArea { width: 412px; }
</style>


<div class="fieldtablecontain ${hasErrors(bean: catTiposervcioInstance, field: 'desTiposervicio', 'error')} ">
	<label for="desTiposervicio">
		<g:message code="catTiposervcio.desTiposervicio.label" default="Des Tiposervicio" />
		
	</label>
	<g:textField name="desTiposervicio" maxlength="100" value="${catTiposervcioInstance?.desTiposervicio}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: catTiposervcioInstance, field: 'estado', 'error')} ">
	<label for="estado">
		<g:message code="catTiposervcio.estado.label" default="Estado" />
		
	</label>
	
</div>

