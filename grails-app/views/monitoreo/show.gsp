
<%@ page import="mx.gob.inr.catservicios.Monitoreo" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'monitoreo.label', default: 'Monitoreo')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-monitoreo" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-monitoreo" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			  <div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:if test="${flash.error}">
			  <div class="errors" role="status">${flash.error}</div>
			</g:if>
			<ol class="property-list monitoreo">
			
				<g:if test="${monitoreoInstance?.fechaMonitoreo}">
				<li class="fieldcontain">
					<span id="fechaMonitoreo-label" class="property-label"><g:message code="monitoreo.fechaMonitoreo.label" default="Fecha Monitoreo" /></span>
					
						<span class="property-value" aria-labelledby="fechaMonitoreo-label"><g:formatDate date="${monitoreoInstance?.fechaMonitoreo}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${monitoreoInstance?.numeroMonitoreo}">
				<li class="fieldcontain">
					<span id="numeroMonitoreo-label" class="property-label"><g:message code="monitoreo.numeroMonitoreo.label" default="Numero Monitoreo" /></span>
					
						<span class="property-value" aria-labelledby="numeroMonitoreo-label"><g:fieldValue bean="${monitoreoInstance}" field="numeroMonitoreo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${monitoreoInstance?.bitacora}">
				<li class="fieldcontain">
					<span id="bitacora-label" class="property-label"><g:message code="monitoreo.bitacora.label" default="Bitacora" /></span>
					
						<span class="property-value" aria-labelledby="bitacora-label"><g:link controller="bitacora" action="show" id="${monitoreoInstance?.bitacora?.id}">${monitoreoInstance?.bitacora?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${monitoreoInstance?.estadoMonitoreo}">
				<li class="fieldcontain">
					<span id="estadoMonitoreo-label" class="property-label"><g:message code="monitoreo.estadoMonitoreo.label" default="Estado Monitoreo" /></span>
					
						<span class="property-value" aria-labelledby="estadoMonitoreo-label"><g:fieldValue bean="${monitoreoInstance}" field="estadoMonitoreo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${monitoreoInstance?.semaforo}">
				<li class="fieldcontain">
					<span id="semaforo-label" class="property-label"><g:message code="monitoreo.semaforo.label" default="Semaforo" /></span>
					
						<span class="property-value" aria-labelledby="semaforo-label"><g:fieldValue bean="${monitoreoInstance}" field="semaforo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${monitoreoInstance?.nota}">
				<li class="fieldcontain">
					<span id="nota-label" class="property-label"><g:message code="monitoreo.nota.label" default="Nota" /></span>
					
						<span class="property-value" aria-labelledby="nota-label"><g:fieldValue bean="${monitoreoInstance}" field="nota"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${monitoreoInstance?.modificacion}">
				<li class="fieldcontain">
					<span id="modificacion-label" class="property-label"><g:message code="monitoreo.modificacion.label" default="Modificacion" /></span>
					
						<span class="property-value" aria-labelledby="modificacion-label"><g:fieldValue bean="${monitoreoInstance}" field="modificacion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${monitoreoInstance?.ipTerminal}">
				<li class="fieldcontain">
					<span id="ipTerminal-label" class="property-label"><g:message code="monitoreo.ipTerminal.label" default="Ip Terminal" /></span>
					
						<span class="property-value" aria-labelledby="ipTerminal-label"><g:fieldValue bean="${monitoreoInstance}" field="ipTerminal"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${monitoreoInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="monitoreo.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${monitoreoInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${monitoreoInstance?.monitoreoDetalles}">
				<li class="fieldcontain">
					<span id="monitoreoDetalles-label" class="property-label"><g:message code="monitoreo.monitoreoDetalles.label" default="Monitoreo Detalles" /></span>
					
						<g:each in="${monitoreoInstance.monitoreoDetalles}" var="m">
						<span class="property-value" aria-labelledby="monitoreoDetalles-label"><g:link controller="monitoreoDetalle" action="show" id="${m.id}">${m?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${monitoreoInstance?.id}" />
					<g:link class="edit" action="edit" id="${monitoreoInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<!--g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /-->
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
