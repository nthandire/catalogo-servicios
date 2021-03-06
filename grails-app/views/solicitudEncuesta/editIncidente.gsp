<%@ page import="mx.gob.inr.catservicios.*" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'incidente.label', default: 'Incidente')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#edit-incidente" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list">Lista de Encuestas</g:link></li>
			</ul>
		</div>
		<div id="edit-incidente" class="content scaffold-edit" role="main">
			<h1>Incidente</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
      <g:if test="${flash.error}">
        <div class="errors" role="status">${flash.error}</div>
      </g:if>
			<g:hasErrors bean="${incidenteInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${incidenteInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form method="post" >
				<g:hiddenField name="id" value="${incidenteInstance?.id}" />
				<g:hiddenField name="version" value="${incidenteInstance?.version}" />
				<fieldset class="form">
					<g:render template="formIncidente"/>
				</fieldset>
				<fieldset class="buttons">
					<g:actionSubmit class="save" action="updateIncidente" value="${message(code: 'default.button.update.label', default: 'UpdateIncidente')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
