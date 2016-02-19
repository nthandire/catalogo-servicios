
<%@ page import="mx.gob.inr.catservicios.BitacoraDetalle" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'bitacoraDetalle.label', default: 'BitacoraDetalle')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-bitacoraDetalle" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-bitacoraDetalle" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list bitacoraDetalle">
			
				<g:if test="${bitacoraDetalleInstance?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="bitacoraDetalle.descripcion.label" default="Descripcion" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${bitacoraDetalleInstance}" field="descripcion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${bitacoraDetalleInstance?.estado}">
				<li class="fieldcontain">
					<span id="estado-label" class="property-label"><g:message code="bitacoraDetalle.estado.label" default="Estado" /></span>
					
						<span class="property-value" aria-labelledby="estado-label"><g:fieldValue bean="${bitacoraDetalleInstance}" field="estado"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${bitacoraDetalleInstance?.bitacora}">
				<li class="fieldcontain">
					<span id="bitacora-label" class="property-label"><g:message code="bitacoraDetalle.bitacora.label" default="Bitacora" /></span>
					
						<span class="property-value" aria-labelledby="bitacora-label"><g:link controller="bitacora" action="show" id="${bitacoraDetalleInstance?.bitacora?.id}">${bitacoraDetalleInstance?.bitacora?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${bitacoraDetalleInstance?.id}" />
					<g:link class="edit" action="edit" id="${bitacoraDetalleInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<!--g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /-->
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
