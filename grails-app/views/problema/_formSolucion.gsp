<%@ page import="mx.gob.inr.catservicios.Problema" %>


<style type="text/css">
  textArea { width: 412px; }
</style>


<div class="fieldtablecontain ${hasErrors(bean: problemaInstance, field: 'folio', 'error')} ">
  <label for="folio">
    <g:message code="problema.folio.label" default="Folio" />
  </label>
  <g:field name="folio" value="${problemaInstance}"
    disabled="true"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: problemaInstance, field: 'fuente', 'error')} ">
	<label for="fuente">
		<g:message code="problema.fuente.label" default="Fuente" />

	</label>
	<g:textField name="fuente" maxlength="50" value="${problemaInstance?.fuente}"
    disabled="true"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: problemaInstance, field: 'idFuente', 'error')} ">
	<label for="idFuente">
		<g:message code="problema.idFuente.label" default="Id Fuente" />
	</label>
	<g:field name="idFuente" type="number" value="${problemaInstance.idFuente}"
    disabled="true"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: problemaInstance, field: 'observaciones', 'error')} ">
	<label for="observaciones">
		<g:message code="problema.observaciones.label" default="Observaciones" />
	</label>
	<g:textArea name="observaciones" cols="40" rows="5" maxlength="3000" value="${problemaInstance?.observaciones}" disabled="true"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: problemaInstance, field: 'solucion', 'error')} ">
	<label for="solucion">
		<g:message code="problema.solucion.label" default="Solucion" />
	</label>
	<g:textArea name="solucion" cols="40" rows="5" maxlength="3000"
    value="${problemaInstance?.solucion}" required="true" />
</div>

<div class="fieldtablecontain ${hasErrors(bean: problemaInstance, field: 'resolvio', 'error')} ">
	<label for="resolvio">
		<g:message code="problema.resolvio.label" default="Quien Resolvio el problema" />
	</label>
	<g:textField name="resolvio" value="${problemaInstance?.resolvio}"
    required="true" />
</div>

<div class="fieldtablecontain">
  <label for="passwordfirma">
    <g:message code="firmadigital.passwordfirma.label" default="Passwordfirma" />
  </label>
  <g:field name="passwordfirma" id="passwordfirma" type="password"
    required="true" />
</div>
