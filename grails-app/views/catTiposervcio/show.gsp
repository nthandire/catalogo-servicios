
<%@ page import="mx.gob.inr.catservicios.CatTiposervcio" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'catTiposervcio.label', default: 'CatTiposervcio')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-catTiposervcio" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-catTiposervcio" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list catTiposervcio">
			
				<g:if test="${catTiposervcioInstance?.desTiposervicio}">
				<li class="fieldcontain">
					<span id="desTiposervicio-label" class="property-label"><g:message code="catTiposervcio.desTiposervicio.label" default="Des Tiposervicio" /></span>
					
						<span class="property-value" aria-labelledby="desTiposervicio-label"><g:fieldValue bean="${catTiposervcioInstance}" field="desTiposervicio"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${catTiposervcioInstance?.estado}">
				<li class="fieldcontain">
					<span id="estado-label" class="property-label"><g:message code="catTiposervcio.estado.label" default="Estado" /></span>
					
						<span class="property-value" aria-labelledby="estado-label"><g:fieldValue bean="${catTiposervcioInstance}" field="estado"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${catTiposervcioInstance?.id}" />
					<g:link class="edit" action="edit" id="${catTiposervcioInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<!--g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /-->
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
