
<%@ page import="mx.gob.inr.catservicios.IncidenteArchivoadjunto" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'incidenteArchivoadjunto.label', default: 'IncidenteArchivoadjunto')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-incidenteArchivoadjunto" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-incidenteArchivoadjunto" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="idIncidente" title="${message(code: 'incidenteArchivoadjunto.idIncidente.label', default: 'Id Incidente')}" />
					
						<g:sortableColumn property="datos" title="${message(code: 'incidenteArchivoadjunto.datos.label', default: 'Datos')}" />
					
						<g:sortableColumn property="nombre" title="${message(code: 'incidenteArchivoadjunto.nombre.label', default: 'Nombre')}" />
					
						<g:sortableColumn property="tamanio" title="${message(code: 'incidenteArchivoadjunto.tamanio.label', default: 'Tamanio')}" />
					
						<g:sortableColumn property="tipo" title="${message(code: 'incidenteArchivoadjunto.tipo.label', default: 'Tipo')}" />
					
						<g:sortableColumn property="idUsuario" title="${message(code: 'incidenteArchivoadjunto.idUsuario.label', default: 'Id Usuario')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${incidenteArchivoadjuntoInstanceList}" status="i" var="incidenteArchivoadjuntoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${incidenteArchivoadjuntoInstance.id}">${fieldValue(bean: incidenteArchivoadjuntoInstance, field: "idIncidente")}</g:link></td>
					
						<td>${fieldValue(bean: incidenteArchivoadjuntoInstance, field: "datos")}</td>
					
						<td>${fieldValue(bean: incidenteArchivoadjuntoInstance, field: "nombre")}</td>
					
						<td>${fieldValue(bean: incidenteArchivoadjuntoInstance, field: "tamanio")}</td>
					
						<td>${fieldValue(bean: incidenteArchivoadjuntoInstance, field: "tipo")}</td>
					
						<td>${fieldValue(bean: incidenteArchivoadjuntoInstance, field: "idUsuario")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${incidenteArchivoadjuntoInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
