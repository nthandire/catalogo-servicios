<%@ page import="mx.gob.inr.catservicios.Solicitud" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'solicitud.label', default: 'Solicitud')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#create-solicitud" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="create-solicitud" class="content scaffold-create" role="main">
			<h1><g:message code="default.create.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			 <div class="message" role="status">${flash.message}</div>
			</g:if>
      <g:if test="${flash.error}">
        <div class="errors" role="status">${flash.error}</div>
      </g:if>
			<g:hasErrors bean="${solicitudInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${solicitudInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form action="save" >
				<fieldset class="form">
          <g:render template="formDetalle"/>
				</fieldset>

        <script>
          function detalle() {
            $("#idServcat").prop("required", true);
            $("#descripcion").prop("required", true);
          }
          function maestro() {
            $("#idServcat").prop("required", false);
            $("#descripcion").prop("required", false);
          }
        </script>


				<fieldset class="buttons">
					<g:submitButton name="create" class="save"  onclick="detalle()"
            value="${message(code: 'default.button.create.label', default: 'Create')}" />
				</fieldset>
			</g:form>
		</div>
    <dev><a href="${resource(dir: 'docs', file: 'SAST-Manual-de-Usuario.docx')}" download>Manual de captura de Requerimientos</a></dev>
	</body>
</html>
