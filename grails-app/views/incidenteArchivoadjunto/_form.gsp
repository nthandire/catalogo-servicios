<%@ page import="mx.gob.inr.catservicios.IncidenteArchivoadjunto" %>


<style type="text/css">
  textArea { width: 412px; }
</style>


<div class="fieldtablecontain ${hasErrors(bean: incidenteArchivoadjuntoInstance, field: 'idIncidente', 'error')} ">
	<label for="idIncidente">
		<g:message code="incidenteArchivoadjunto.idIncidente.label" default="Id Incidente" />
		
	</label>
	<g:field name="idIncidente" type="number" value="${incidenteArchivoadjuntoInstance.idIncidente}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteArchivoadjuntoInstance, field: 'datos', 'error')} ">
	<label for="datos">
		<g:message code="incidenteArchivoadjunto.datos.label" default="Datos" />
		
	</label>
	<input type="file" id="datos" name="datos" />
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteArchivoadjuntoInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="incidenteArchivoadjunto.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" value="${incidenteArchivoadjuntoInstance?.nombre}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteArchivoadjuntoInstance, field: 'tamanio', 'error')} ">
	<label for="tamanio">
		<g:message code="incidenteArchivoadjunto.tamanio.label" default="Tamanio" />
		
	</label>
	<g:field name="tamanio" type="number" value="${incidenteArchivoadjuntoInstance.tamanio}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteArchivoadjuntoInstance, field: 'tipo', 'error')} ">
	<label for="tipo">
		<g:message code="incidenteArchivoadjunto.tipo.label" default="Tipo" />
		
	</label>
	<g:textField name="tipo" maxlength="20" value="${incidenteArchivoadjuntoInstance?.tipo}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteArchivoadjuntoInstance, field: 'idUsuario', 'error')} ">
	<label for="idUsuario">
		<g:message code="incidenteArchivoadjunto.idUsuario.label" default="Id Usuario" />
		
	</label>
	<g:field name="idUsuario" type="number" value="${incidenteArchivoadjuntoInstance.idUsuario}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteArchivoadjuntoInstance, field: 'fechaModificacion', 'error')} ">
	<label for="fechaModificacion">
		<g:message code="incidenteArchivoadjunto.fechaModificacion.label" default="Fecha Modificacion" />
		
	</label>
	<g:datePicker name="fechaModificacion" precision="day"  value="${incidenteArchivoadjuntoInstance?.fechaModificacion}" default="none" noSelection="['': '']" />
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteArchivoadjuntoInstance, field: 'ipTerminal', 'error')} ">
	<label for="ipTerminal">
		<g:message code="incidenteArchivoadjunto.ipTerminal.label" default="Ip Terminal" />
		
	</label>
	<g:textField name="ipTerminal" maxlength="15" value="${incidenteArchivoadjuntoInstance?.ipTerminal}"/>
</div>

