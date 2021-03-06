
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
				<li><g:link class="back" controller="solicitud" action="show" id="${solicitudArchivoadjuntoInstance?.idSolicitud?.id}">Regresar al requerimiento</g:link></li>
			</ul>
		</div>
		<div id="show-solicitudArchivoadjunto" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			  <div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:if test="${flash.error}">
			  <div class="errors" role="status">${flash.error}</div>
			</g:if>
			<ol class="property-list solicitudArchivoadjunto">

				<g:if test="${solicitudArchivoadjuntoInstance?.idSolicitud}">
				<li class="fieldcontain">
					<span id="idSolicitud-label" class="property-label"><g:message code="solicitudArchivoadjunto.idSolicitud.label" default="Id Solicitud" /></span>

						<span class="property-value" aria-labelledby="idSolicitud-label"><g:link controller="solicitud" action="show" id="${solicitudArchivoadjuntoInstance?.idSolicitud?.id}">${solicitudArchivoadjuntoInstance?.idSolicitud?.encodeAsHTML()}</g:link></span>

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
					<span id="tamanio-label" class="property-label"><g:message code="solicitudArchivoadjunto.tamanio.label" default="Tamaño" /></span>

						<span class="property-value" aria-labelledby="tamanio-label"><g:fieldValue bean="${solicitudArchivoadjuntoInstance}" field="tamanio"/></span>

				</li>
				</g:if>

				<g:if test="${solicitudArchivoadjuntoInstance?.tipo}">
				<li class="fieldcontain">
					<span id="tipo-label" class="property-label"><g:message code="solicitudArchivoadjunto.tipo.label" default="Tipo" /></span>

						<span class="property-value" aria-labelledby="tipo-label"><g:fieldValue bean="${solicitudArchivoadjuntoInstance}" field="tipo"/></span>

				</li>
				</g:if>

				<g:if test="${solicitudArchivoadjuntoInstance?.lastUpdated}">
					<li class="fieldcontain">
						<span id="lastUpdated-label" class="property-label"><g:message code="solicitudArchivoadjunto.lastUpdated.label" default="Last Updated" /></span>

						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${solicitudArchivoadjuntoInstance?.lastUpdated}" /></span>
					</li>
				</g:if>

			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${solicitudArchivoadjuntoInstance?.id}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
