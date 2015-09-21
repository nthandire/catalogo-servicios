
<%@ page import="mx.gob.inr.catservicios.Solicitud" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'solicitud.label', default: 'Solicitud')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-solicitud" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			</ul>
		</div>
		<div id="list-solicitud" class="content scaffold-list" role="main">
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
					
						<g:sortableColumn property="numeroSolicitud" title="${message(code: 'solicitud.numeroSolicitud.label', default: 'Numero Solicitud')}" />
					
						<g:sortableColumn property="fechaSolicitud" title="${message(code: 'solicitud.fechaSolicitud.label', default: 'Fecha Solicitud')}" />
					
						<g:sortableColumn property="justificacion" title="${message(code: 'solicitud.justificacion.label', default: 'Justificacion')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${solicitudInstanceList}" status="i" var="solicitudInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="edit" id="${solicitudInstance.id}">${solicitudInstance.toString()}</g:link></td>
					
						<td><g:formatDate date="${solicitudInstance.fechaSolicitud}" /></td>
					
						<td>${fieldValue(bean: solicitudInstance, field: "justificacion")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${solicitudInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
