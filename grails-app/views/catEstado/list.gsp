
<%@ page import="mx.gob.inr.catservicios.CatEstado" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'catEstado.label', default: 'CatEstado')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-catEstado" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-catEstado" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
  			<div class="message" role="status">${flash.message}</div>
			</g:if>
      <g:if test="${flash.error}">
        <div class="errors" role="status">${flash.error}</div>
      </g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="desEstado" title="${message(code: 'catEstado.desEstado.label', default: 'Des Estado')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${catEstadoInstanceList}" status="i" var="catEstadoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${catEstadoInstance.id}">${fieldValue(bean: catEstadoInstance, field: "desEstado")}</g:link></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${catEstadoInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
