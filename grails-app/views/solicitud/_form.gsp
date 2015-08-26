<%@ page import="mx.gob.inr.catservicios.Solicitud" %>


<style type="text/css">
  textArea { width: 812px; }
</style>


<div class="fieldtablecontain ${hasErrors(bean: solicitudInstance, field: 'justificacion', 'error')} ">
	<label for="justificacion">
		<g:message code="solicitud.justificacion.label" default="Justificacion" />
		
	</label>
	<g:textArea name="justificacion" cols="40" rows="5" maxlength="1500" value="${solicitudInstance?.justificacion}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: solicitudInstance, field: 'archivos', 'error')} ">
	<label for="archivos">
		<g:message code="solicitud.archivos.label" default="Archivos" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${solicitudInstance?.archivos?}" var="a">
    <li><g:link controller="solicitudArchivoadjunto" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="solicitudArchivoadjunto" action="create" params="['solicitud.id': solicitudInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'solicitudArchivoadjunto.label', default: 'SolicitudArchivoadjunto')])}</g:link>
</li>
</ul>

</div>

<div class="fieldtablecontain ${hasErrors(bean: solicitudInstance, field: 'detalles', 'error')} ">
	<label for="detalles">
		<g:message code="solicitud.detalles.label" default="Detalles" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${solicitudInstance?.detalles?}" var="d">
    <li><g:link controller="solicitudDetalle" action="show" id="${d.id}">${d?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="solicitudDetalle" action="create" params="['solicitud.id': solicitudInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'solicitudDetalle.label', default: 'SolicitudDetalle')])}</g:link>
</li>
</ul>

</div>

