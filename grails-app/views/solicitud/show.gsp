
<%@ page import="mx.gob.inr.catservicios.Solicitud" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'solicitud.label', default: 'Solicitud')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-solicitud" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-solicitud" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list solicitud">
			
				<g:if test="${solicitudInstance?.numeroSolicitud}">
				<li class="fieldcontain">
					<span id="numeroSolicitud-label" class="property-label"><g:message code="solicitud.numeroSolicitud.label" default="Numero Solicitud" /></span>
					
						<span class="property-value" aria-labelledby="numeroSolicitud-label">${solicitudInstance.toString()}</span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudInstance?.justificacion}">
				<li class="fieldcontain">
					<span id="justificacion-label" class="property-label"><g:message code="solicitud.justificacion.label" default="Justificacion" /></span>
					
						<span class="property-value" aria-labelledby="justificacion-label"><g:fieldValue bean="${solicitudInstance}" field="justificacion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="solicitud.lastUpdated.label" default="Fecha Modificacion" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${solicitudInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudInstance?.detalles}">
				<li class="fieldcontain">
					<span id="detalles-label" class="property-label"><g:message code="solicitud.detalles.label" default="Detalles" /></span>
					
						<g:each in="${solicitudInstance.detalles}" var="d">
						<span class="property-value" aria-labelledby="detalles-label"><g:link controller="solicitudDetalle" action="show" id="${d.id}">${d?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudInstance?.archivos}">
				<li class="fieldcontain">
					<span id="archivos-label" class="property-label"><g:message code="solicitud.archivos.label" default="Archivos" /></span>
					
						<g:each in="${solicitudInstance.archivos}" var="d">
						<span class="property-value" aria-labelledby="archivos-label"><g:link controller="solicitudDetalle" action="show" id="${d.id}">${d?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>

<h1></h1>

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




			<g:form>
				<fieldset class="buttons">
					<g:if test="${!solicitudInstance?.fechaSolicitud}">
						<g:hiddenField name="id" value="${solicitudInstance?.id}" />
						<g:link class="edit" action="edit" id="${solicitudInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
						<g:link class="edit" action="firmar" id="${solicitudInstance?.id}"><g:message code="default.button.firmar.label" default="Firmar" /></g:link>
					</g:if>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
