
<%@ page import="mx.gob.inr.catservicios.CatTiposervcio" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'catTiposervcio.label', default: 'CatTiposervcio')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-catTiposervcio" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-catTiposervcio" class="content scaffold-list" role="main">
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
					
						<g:sortableColumn property="desTiposervicio" title="${message(code: 'catTiposervcio.desTiposervicio.label', default: 'Des Tiposervicio')}" />
					
						<g:sortableColumn property="estado" title="${message(code: 'catTiposervcio.estado.label', default: 'Estado')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${catTiposervcioInstanceList}" status="i" var="catTiposervcioInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${catTiposervcioInstance.id}">${fieldValue(bean: catTiposervcioInstance, field: "desTiposervicio")}</g:link></td>
					
						<td>${fieldValue(bean: catTiposervcioInstance, field: "estado")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${catTiposervcioInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
