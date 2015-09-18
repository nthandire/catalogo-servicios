
<%@ page import="mx.gob.inr.catservicios.CatEstado" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'catEstado.label', default: 'CatEstado')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-catEstado" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-catEstado" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
  			<div class="message" role="status">${flash.message}</div>
			</g:if>
      <g:if test="${flash.error}">
        <div class="errors" role="status">${flash.error}</div>
      </g:if>
			<ol class="property-list catEstado">
			
				<g:if test="${catEstadoInstance?.desEstado}">
				<li class="fieldcontain">
					<span id="desEstado-label" class="property-label"><g:message code="catEstado.desEstado.label" default="Des Estado" /></span>
					
						<span class="property-value" aria-labelledby="desEstado-label"><g:fieldValue bean="${catEstadoInstance}" field="desEstado"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${catEstadoInstance?.id}" />
					<g:link class="edit" action="edit" id="${catEstadoInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<!--g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /-->
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
