
<%@ page import="mx.gob.inr.catservicios.CatPrograma" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'catPrograma.label', default: 'CatPrograma')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-catPrograma" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-catPrograma" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list catPrograma">
			
				<g:if test="${catProgramaInstance?.desPrograma}">
				<li class="fieldcontain">
					<span id="desPrograma-label" class="property-label"><g:message code="catPrograma.desPrograma.label" default="Des Programa" /></span>
					
						<span class="property-value" aria-labelledby="desPrograma-label"><g:fieldValue bean="${catProgramaInstance}" field="desPrograma"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${catProgramaInstance?.estadoPrograma}">
				<li class="fieldcontain">
					<span id="estadoPrograma-label" class="property-label"><g:message code="catPrograma.estadoPrograma.label" default="Estado Programa" /></span>
					
						<span class="property-value" aria-labelledby="estadoPrograma-label"><g:fieldValue bean="${catProgramaInstance}" field="estadoPrograma"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${catProgramaInstance?.id}" />
					<g:link class="edit" action="edit" id="${catProgramaInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<!--g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /-->
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
