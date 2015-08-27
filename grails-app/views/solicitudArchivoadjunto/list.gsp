
<%@ page import="mx.gob.inr.catservicios.SolicitudArchivoadjunto" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'solicitudArchivoadjunto.label', default: 'SolicitudArchivoadjunto')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-solicitudArchivoadjunto" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-solicitudArchivoadjunto" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="nombre" title="${message(code: 'solicitudArchivoadjunto.nombre.label', default: 'Nombre')}" />
					
						<g:sortableColumn property="tamaño" title="${message(code: 'solicitudArchivoadjunto.tamaño.label', default: 'Tamaño')}" />
					
						<g:sortableColumn property="tipo" title="${message(code: 'solicitudArchivoadjunto.tipo.label', default: 'Tipo')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${solicitudArchivoadjuntoInstanceList}" status="i" var="solicitudArchivoadjuntoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="download" id="${solicitudArchivoadjuntoInstance.id}">${fieldValue(bean: solicitudArchivoadjuntoInstance, field: "nombre")}</g:link></td>
					
						<td>${fieldValue(bean: solicitudArchivoadjuntoInstance, field: "tamaño")}</td>
					
						<td>${fieldValue(bean: solicitudArchivoadjuntoInstance, field: "tipo")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${solicitudArchivoadjuntoInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
