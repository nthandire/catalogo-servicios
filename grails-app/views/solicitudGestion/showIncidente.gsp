<%@ page import="mx.gob.inr.catservicios.*" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'solicitudDetalle.label', default: 'SolicitudDetalle')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-solicitudDetalle" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li>
          <g:link class="back" action="listIncidentes" params="${[offset: bOffset]}">
            Regresar al requerimiento
          </g:link>
        </li>
			</ul>
		</div>
		<div id="show-solicitudDetalle" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			  <div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:if test="${flash.error}">
			  <div class="errors" role="status">${flash.error}</div>
			</g:if>

      <g:render template="formAdmon"/>

			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${solicitudDetalleInstance?.id}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
