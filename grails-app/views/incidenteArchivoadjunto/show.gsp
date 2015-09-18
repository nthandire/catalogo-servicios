
<%@ page import="mx.gob.inr.catservicios.IncidenteArchivoadjunto" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'incidenteArchivoadjunto.label', default: 'IncidenteArchivoadjunto')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-incidenteArchivoadjunto" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-incidenteArchivoadjunto" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			  <div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:if test="${flash.error}">
			  <div class="errors" role="status">${flash.error}</div>
			</g:if>
			<ol class="property-list incidenteArchivoadjunto">
			
				<g:if test="${incidenteArchivoadjuntoInstance?.idIncidente}">
				<li class="fieldcontain">
					<span id="idIncidente-label" class="property-label"><g:message code="incidenteArchivoadjunto.idIncidente.label" default="Id Incidente" /></span>
					
						<span class="property-value" aria-labelledby="idIncidente-label"><g:fieldValue bean="${incidenteArchivoadjuntoInstance}" field="idIncidente"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${incidenteArchivoadjuntoInstance?.datos}">
				<li class="fieldcontain">
					<span id="datos-label" class="property-label"><g:message code="incidenteArchivoadjunto.datos.label" default="Datos" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${incidenteArchivoadjuntoInstance?.nombre}">
				<li class="fieldcontain">
					<span id="nombre-label" class="property-label"><g:message code="incidenteArchivoadjunto.nombre.label" default="Nombre" /></span>
					
						<span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${incidenteArchivoadjuntoInstance}" field="nombre"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${incidenteArchivoadjuntoInstance?.tamanio}">
				<li class="fieldcontain">
					<span id="tamanio-label" class="property-label"><g:message code="incidenteArchivoadjunto.tamanio.label" default="Tamanio" /></span>
					
						<span class="property-value" aria-labelledby="tamanio-label"><g:fieldValue bean="${incidenteArchivoadjuntoInstance}" field="tamanio"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${incidenteArchivoadjuntoInstance?.tipo}">
				<li class="fieldcontain">
					<span id="tipo-label" class="property-label"><g:message code="incidenteArchivoadjunto.tipo.label" default="Tipo" /></span>
					
						<span class="property-value" aria-labelledby="tipo-label"><g:fieldValue bean="${incidenteArchivoadjuntoInstance}" field="tipo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${incidenteArchivoadjuntoInstance?.idUsuario}">
				<li class="fieldcontain">
					<span id="idUsuario-label" class="property-label"><g:message code="incidenteArchivoadjunto.idUsuario.label" default="Id Usuario" /></span>
					
						<span class="property-value" aria-labelledby="idUsuario-label"><g:fieldValue bean="${incidenteArchivoadjuntoInstance}" field="idUsuario"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${incidenteArchivoadjuntoInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="incidenteArchivoadjunto.lastUpdated.label" default="Fecha Modificacion" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${incidenteArchivoadjuntoInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${incidenteArchivoadjuntoInstance?.ipTerminal}">
				<li class="fieldcontain">
					<span id="ipTerminal-label" class="property-label"><g:message code="incidenteArchivoadjunto.ipTerminal.label" default="Ip Terminal" /></span>
					
						<span class="property-value" aria-labelledby="ipTerminal-label"><g:fieldValue bean="${incidenteArchivoadjuntoInstance}" field="ipTerminal"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${incidenteArchivoadjuntoInstance?.id}" />
					<g:link class="edit" action="edit" id="${incidenteArchivoadjuntoInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<!--g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /-->
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
