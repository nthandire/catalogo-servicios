
<%@ page import="mx.gob.inr.catservicios.SolicitudArchivoadjunto" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'solicitudArchivoadjunto.label', default: 'SolicitudArchivoadjunto')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-solicitudArchivoadjunto" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-solicitudArchivoadjunto" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list solicitudArchivoadjunto">
			
				<g:if test="${solicitudArchivoadjuntoInstance?.idSolicitud}">
				<li class="fieldcontain">
					<span id="idSolicitud-label" class="property-label"><g:message code="solicitudArchivoadjunto.idSolicitud.label" default="Id Solicitud" /></span>
					
						<span class="property-value" aria-labelledby="idSolicitud-label"><g:fieldValue bean="${solicitudArchivoadjuntoInstance}" field="idSolicitud"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudArchivoadjuntoInstance?.datos}">
				<li class="fieldcontain">
					<span id="datos-label" class="property-label"><g:message code="solicitudArchivoadjunto.datos.label" default="Datos" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudArchivoadjuntoInstance?.nombre}">
				<li class="fieldcontain">
					<span id="nombre-label" class="property-label"><g:message code="solicitudArchivoadjunto.nombre.label" default="Nombre" /></span>
					
						<span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${solicitudArchivoadjuntoInstance}" field="nombre"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudArchivoadjuntoInstance?.tamanio}">
				<li class="fieldcontain">
					<span id="tamanio-label" class="property-label"><g:message code="solicitudArchivoadjunto.tamanio.label" default="Tamanio" /></span>
					
						<span class="property-value" aria-labelledby="tamanio-label"><g:fieldValue bean="${solicitudArchivoadjuntoInstance}" field="tamanio"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudArchivoadjuntoInstance?.tipo}">
				<li class="fieldcontain">
					<span id="tipo-label" class="property-label"><g:message code="solicitudArchivoadjunto.tipo.label" default="Tipo" /></span>
					
						<span class="property-value" aria-labelledby="tipo-label"><g:fieldValue bean="${solicitudArchivoadjuntoInstance}" field="tipo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudArchivoadjuntoInstance?.idUsuario}">
				<li class="fieldcontain">
					<span id="idUsuario-label" class="property-label"><g:message code="solicitudArchivoadjunto.idUsuario.label" default="Id Usuario" /></span>
					
						<span class="property-value" aria-labelledby="idUsuario-label"><g:fieldValue bean="${solicitudArchivoadjuntoInstance}" field="idUsuario"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudArchivoadjuntoInstance?.fechaModificacion}">
				<li class="fieldcontain">
					<span id="fechaModificacion-label" class="property-label"><g:message code="solicitudArchivoadjunto.fechaModificacion.label" default="Fecha Modificacion" /></span>
					
						<span class="property-value" aria-labelledby="fechaModificacion-label"><g:formatDate date="${solicitudArchivoadjuntoInstance?.fechaModificacion}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudArchivoadjuntoInstance?.ipTerminal}">
				<li class="fieldcontain">
					<span id="ipTerminal-label" class="property-label"><g:message code="solicitudArchivoadjunto.ipTerminal.label" default="Ip Terminal" /></span>
					
						<span class="property-value" aria-labelledby="ipTerminal-label"><g:fieldValue bean="${solicitudArchivoadjuntoInstance}" field="ipTerminal"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${solicitudArchivoadjuntoInstance?.id}" />
					<g:link class="edit" action="edit" id="${solicitudArchivoadjuntoInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<!--g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /-->
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
