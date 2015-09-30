<%@ page import="mx.gob.inr.catservicios.Problema" %>


<style type="text/css">
  textArea { width: 412px; }
</style>


<div class="fieldtablecontain ${hasErrors(bean: problemaInstance, field: 'fuente', 'error')} ">
	<label for="fuente">
		<g:message code="problema.fuente.label" default="Fuente" />
		
	</label>
	<g:textField name="fuente" maxlength="50" value="${problemaInstance?.fuente}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: problemaInstance, field: 'idFuente', 'error')} ">
	<label for="idFuente">
		<g:message code="problema.idFuente.label" default="Id Fuente" />
		
	</label>
	<g:field name="idFuente" type="number" value="${problemaInstance.idFuente}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: problemaInstance, field: 'fechaProblema', 'error')} ">
	<label for="fechaProblema">
		<g:message code="problema.fechaProblema.label" default="Fecha Problema" />
		
	</label>
	<g:datePicker name="fechaProblema" precision="day"  value="${problemaInstance?.fechaProblema}" default="none" noSelection="['': '']" />
</div>

<div class="fieldtablecontain ${hasErrors(bean: problemaInstance, field: 'folio', 'error')} ">
	<label for="folio">
		<g:message code="problema.folio.label" default="Folio" />
		
	</label>
	<g:field name="folio" type="number" value="${problemaInstance.folio}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: problemaInstance, field: 'observaciones', 'error')} ">
	<label for="observaciones">
		<g:message code="problema.observaciones.label" default="Observaciones" />
		
	</label>
	<g:textArea name="observaciones" cols="40" rows="5" maxlength="3000" value="${problemaInstance?.observaciones}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: problemaInstance, field: 'solucion', 'error')} ">
	<label for="solucion">
		<g:message code="problema.solucion.label" default="Solucion" />
		
	</label>
	<g:textArea name="solucion" cols="40" rows="5" maxlength="3000" value="${problemaInstance?.solucion}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: problemaInstance, field: 'fechaSolucion', 'error')} ">
	<label for="fechaSolucion">
		<g:message code="problema.fechaSolucion.label" default="Fecha Solucion" />
		
	</label>
	<g:datePicker name="fechaSolucion" precision="day"  value="${problemaInstance?.fechaSolucion}" default="none" noSelection="['': '']" />
</div>

<div class="fieldtablecontain ${hasErrors(bean: problemaInstance, field: 'resolvio', 'error')} ">
	<label for="resolvio">
		<g:message code="problema.resolvio.label" default="Resolvio" />
		
	</label>
	<g:textField name="resolvio" value="${problemaInstance?.resolvio}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: problemaInstance, field: 'idUsuario', 'error')} ">
	<label for="idUsuario">
		<g:message code="problema.idUsuario.label" default="Id Usuario" />
		
	</label>
	<g:field name="idUsuario" type="number" value="${problemaInstance.idUsuario}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: problemaInstance, field: 'ipTerminal', 'error')} ">
	<label for="ipTerminal">
		<g:message code="problema.ipTerminal.label" default="Ip Terminal" />
		
	</label>
	<g:textField name="ipTerminal" maxlength="15" value="${problemaInstance?.ipTerminal}"/>
</div>

