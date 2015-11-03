<%@ page import="mx.gob.inr.catservicios.CatPrograma" %>


<style type="text/css">
  textArea { width: 412px; }
</style>


<div class="fieldtablecontain ${hasErrors(bean: catProgramaInstance, field: 'desPrograma', 'error')} ">
	<label for="desPrograma">
		<g:message code="catPrograma.desPrograma.label" default="Descripción" />
	</label>
	<g:textField name="desPrograma" value="${catProgramaInstance?.desPrograma}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: catProgramaInstance, field: 'estadoPrograma', 'error')} ">
	<label for="estadoPrograma">
		<g:message code="catPrograma.estadoPrograma.label" default="Estado" />
	</label>
  <g:select name="estadoPrograma" from="['A','I']"
    valueMessagePrefix="cat_servCat.estado"
    value="${catProgramaInstance?.estadoPrograma}" />
</div>

