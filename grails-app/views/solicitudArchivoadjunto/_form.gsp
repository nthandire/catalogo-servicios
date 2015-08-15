<%@ page import="mx.gob.inr.catservicios.SolicitudArchivoadjunto" %>


<style type="text/css">
  textArea { width: 412px; }
</style>


<div class="fieldtablecontain ${hasErrors(bean: solicitudArchivoadjuntoInstance, field: 'idSolicitud', 'error')} ">
	<label for="idSolicitud">
		<g:message code="solicitudArchivoadjunto.idSolicitud.label" default="Id Solicitud" />
		
	</label>
	<g:field name="idSolicitud" type="number" value="${solicitudArchivoadjuntoInstance.idSolicitud}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: solicitudArchivoadjuntoInstance, field: 'datos', 'error')} ">
	<label for="datos">
		<g:message code="solicitudArchivoadjunto.datos.label" default="Datos" />
		
	</label>
	<input type="file" id="datos" name="datos" />
</div>

<div class="fieldtablecontain ${hasErrors(bean: solicitudArchivoadjuntoInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="solicitudArchivoadjunto.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" value="${solicitudArchivoadjuntoInstance?.nombre}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: solicitudArchivoadjuntoInstance, field: 'tamanio', 'error')} ">
	<label for="tamanio">
		<g:message code="solicitudArchivoadjunto.tamanio.label" default="Tamanio" />
		
	</label>
	<g:field name="tamanio" type="number" value="${solicitudArchivoadjuntoInstance.tamanio}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: solicitudArchivoadjuntoInstance, field: 'tipo', 'error')} ">
	<label for="tipo">
		<g:message code="solicitudArchivoadjunto.tipo.label" default="Tipo" />
		
	</label>
	<g:textField name="tipo" maxlength="20" value="${solicitudArchivoadjuntoInstance?.tipo}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: solicitudArchivoadjuntoInstance, field: 'idUsuario', 'error')} ">
	<label for="idUsuario">
		<g:message code="solicitudArchivoadjunto.idUsuario.label" default="Id Usuario" />
		
	</label>
	<g:field name="idUsuario" type="number" value="${solicitudArchivoadjuntoInstance.idUsuario}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: solicitudArchivoadjuntoInstance, field: 'fechaModificacion', 'error')} ">
	<label for="fechaModificacion">
		<g:message code="solicitudArchivoadjunto.fechaModificacion.label" default="Fecha Modificacion" />
		
	</label>
	<g:datePicker name="fechaModificacion" precision="day"  value="${solicitudArchivoadjuntoInstance?.fechaModificacion}" default="none" noSelection="['': '']" />
</div>

<div class="fieldtablecontain ${hasErrors(bean: solicitudArchivoadjuntoInstance, field: 'ipTerminal', 'error')} ">
	<label for="ipTerminal">
		<g:message code="solicitudArchivoadjunto.ipTerminal.label" default="Ip Terminal" />
		
	</label>
	<g:textField name="ipTerminal" maxlength="15" value="${solicitudArchivoadjuntoInstance?.ipTerminal}"/>
</div>

