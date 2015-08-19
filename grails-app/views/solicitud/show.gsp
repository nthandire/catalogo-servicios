
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
					
						<span class="property-value" aria-labelledby="numeroSolicitud-label"><g:fieldValue bean="${solicitudInstance}" field="numeroSolicitud"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudInstance?.fechaSolicitud}">
				<li class="fieldcontain">
					<span id="fechaSolicitud-label" class="property-label"><g:message code="solicitud.fechaSolicitud.label" default="Fecha Solicitud" /></span>
					
						<span class="property-value" aria-labelledby="fechaSolicitud-label"><g:formatDate date="${solicitudInstance?.fechaSolicitud}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudInstance?.estadoSolicitud}">
				<li class="fieldcontain">
					<span id="estadoSolicitud-label" class="property-label"><g:message code="solicitud.estadoSolicitud.label" default="Estado Solicitud" /></span>
					
						<span class="property-value" aria-labelledby="estadoSolicitud-label"><g:fieldValue bean="${solicitudInstance}" field="estadoSolicitud"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudInstance?.justificacion}">
				<li class="fieldcontain">
					<span id="justificacion-label" class="property-label"><g:message code="solicitud.justificacion.label" default="Justificacion" /></span>
					
						<span class="property-value" aria-labelledby="justificacion-label"><g:fieldValue bean="${solicitudInstance}" field="justificacion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudInstance?.idSolicitante}">
				<li class="fieldcontain">
					<span id="idSolicitante-label" class="property-label"><g:message code="solicitud.idSolicitante.label" default="Id Solicitante" /></span>
					
						<span class="property-value" aria-labelledby="idSolicitante-label"><g:fieldValue bean="${solicitudInstance}" field="idSolicitante"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudInstance?.idAutoriza}">
				<li class="fieldcontain">
					<span id="idAutoriza-label" class="property-label"><g:message code="solicitud.idAutoriza.label" default="Id Autoriza" /></span>
					
						<span class="property-value" aria-labelledby="idAutoriza-label"><g:fieldValue bean="${solicitudInstance}" field="idAutoriza"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudInstance?.fechaAutoriza}">
				<li class="fieldcontain">
					<span id="fechaAutoriza-label" class="property-label"><g:message code="solicitud.fechaAutoriza.label" default="Fecha Autoriza" /></span>
					
						<span class="property-value" aria-labelledby="fechaAutoriza-label"><g:formatDate date="${solicitudInstance?.fechaAutoriza}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudInstance?.idVb}">
				<li class="fieldcontain">
					<span id="idVb-label" class="property-label"><g:message code="solicitud.idVb.label" default="Id Vb" /></span>
					
						<span class="property-value" aria-labelledby="idVb-label"><g:fieldValue bean="${solicitudInstance}" field="idVb"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudInstance?.fechaVb}">
				<li class="fieldcontain">
					<span id="fechaVb-label" class="property-label"><g:message code="solicitud.fechaVb.label" default="Fecha Vb" /></span>
					
						<span class="property-value" aria-labelledby="fechaVb-label"><g:formatDate date="${solicitudInstance?.fechaVb}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudInstance?.p01}">
				<li class="fieldcontain">
					<span id="p01-label" class="property-label"><g:message code="solicitud.p01.label" default="P01" /></span>
					
						<span class="property-value" aria-labelledby="p01-label"><g:fieldValue bean="${solicitudInstance}" field="p01"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudInstance?.p02}">
				<li class="fieldcontain">
					<span id="p02-label" class="property-label"><g:message code="solicitud.p02.label" default="P02" /></span>
					
						<span class="property-value" aria-labelledby="p02-label"><g:fieldValue bean="${solicitudInstance}" field="p02"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudInstance?.p03}">
				<li class="fieldcontain">
					<span id="p03-label" class="property-label"><g:message code="solicitud.p03.label" default="P03" /></span>
					
						<span class="property-value" aria-labelledby="p03-label"><g:fieldValue bean="${solicitudInstance}" field="p03"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudInstance?.p04}">
				<li class="fieldcontain">
					<span id="p04-label" class="property-label"><g:message code="solicitud.p04.label" default="P04" /></span>
					
						<span class="property-value" aria-labelledby="p04-label"><g:fieldValue bean="${solicitudInstance}" field="p04"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudInstance?.fechaModificacion}">
				<li class="fieldcontain">
					<span id="fechaModificacion-label" class="property-label"><g:message code="solicitud.fechaModificacion.label" default="Fecha Modificacion" /></span>
					
						<span class="property-value" aria-labelledby="fechaModificacion-label"><g:formatDate date="${solicitudInstance?.fechaModificacion}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudInstance?.ipTerminal}">
				<li class="fieldcontain">
					<span id="ipTerminal-label" class="property-label"><g:message code="solicitud.ipTerminal.label" default="Ip Terminal" /></span>
					
						<span class="property-value" aria-labelledby="ipTerminal-label"><g:fieldValue bean="${solicitudInstance}" field="ipTerminal"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${solicitudInstance?.id}" />
					<g:link class="edit" action="edit" id="${solicitudInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<!--g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /-->
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
