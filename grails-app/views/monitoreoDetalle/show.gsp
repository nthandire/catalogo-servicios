
<%@ page import="mx.gob.inr.catservicios.MonitoreoDetalle" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'monitoreoDetalle.label', default: 'MonitoreoDetalle')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-monitoreoDetalle" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-monitoreoDetalle" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list monitoreoDetalle">
			
				<g:if test="${monitoreoDetalleInstance?.estado}">
				<li class="fieldcontain">
					<span id="estado-label" class="property-label"><g:message code="monitoreoDetalle.estado.label" default="Estado" /></span>
					
						<span class="property-value" aria-labelledby="estado-label"><g:fieldValue bean="${monitoreoDetalleInstance}" field="estado"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${monitoreoDetalleInstance?.observaciones}">
				<li class="fieldcontain">
					<span id="observaciones-label" class="property-label"><g:message code="monitoreoDetalle.observaciones.label" default="Observaciones" /></span>
					
						<span class="property-value" aria-labelledby="observaciones-label"><g:fieldValue bean="${monitoreoDetalleInstance}" field="observaciones"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${monitoreoDetalleInstance?.bitacoradetalle}">
				<li class="fieldcontain">
					<span id="bitacoradetalle-label" class="property-label"><g:message code="monitoreoDetalle.bitacoradetalle.label" default="Bitacoradetalle" /></span>
					
						<span class="property-value" aria-labelledby="bitacoradetalle-label"><g:link controller="bitacoraDetalle" action="show" id="${monitoreoDetalleInstance?.bitacoradetalle?.id}">${monitoreoDetalleInstance?.bitacoradetalle?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${monitoreoDetalleInstance?.monitoreo}">
				<li class="fieldcontain">
					<span id="monitoreo-label" class="property-label"><g:message code="monitoreoDetalle.monitoreo.label" default="Monitoreo" /></span>
					
						<span class="property-value" aria-labelledby="monitoreo-label"><g:link controller="monitoreo" action="show" id="${monitoreoDetalleInstance?.monitoreo?.id}">${monitoreoDetalleInstance?.monitoreo?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${monitoreoDetalleInstance?.id}" />
					<g:link class="edit" action="edit" id="${monitoreoDetalleInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<!--g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /-->
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
