<%@ page import="mx.gob.inr.catservicios.*" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'solicitudArchivoadjunto.label', default: 'SolicitudArchivoadjunto')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#create-incidenteArchivoadjunto" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="back" action="edit" id="${incidenteArchivoadjuntoInstance?.idIncidente}">Regresar al incidente</g:link></li>
			</ul>
		</div>
		<div id="create-incidenteArchivoadjunto" class="content scaffold-create" role="main">
			<h1><g:message code="default.create.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
      <g:if test="${flash.error}">
        <div class="errors" role="status">${flash.error}</div>
      </g:if>
			<g:hasErrors bean="${incidenteArchivoadjuntoInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${incidenteArchivoadjuntoInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form action="saveArchivo"  enctype="multipart/form-data">
				<g:hiddenField name="idIncidente" value="${incidenteArchivoadjuntoInstance?.idIncidente}" />
				<fieldset class="form">
					<div class="fieldtablecontain ">
						<br /><br /><br />
	          <input type="file" name="file" style="width: 400px" />
						<br /><br /><br />
					</div>
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="create" class="save" value="${message(code: 'default.button.upload.label', default: 'Subir')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
