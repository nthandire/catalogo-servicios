<%@ page import="mx.gob.inr.catservicios.SolicitudArchivoadjunto" %>


<style type="text/css">
  textArea { width: 412px; }
</style>


<div class="fieldtablecontain ${hasErrors(bean: solicitudArchivoadjuntoInstance, field: 'idSolicitud', 'error')} ">
	<label for="idSolicitud">
		<g:message code="solicitudArchivoadjunto.idSolicitud.label" default="Id Solicitud" />
		
	</label>
	<g:select id="idSolicitud" name="idSolicitud.id" from="${mx.gob.inr.catservicios.Solicitud.list()}" optionKey="id" value="${solicitudArchivoadjuntoInstance?.idSolicitud?.id}" class="many-to-one" noSelection="['null': '']"/>
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

