
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
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-solicitud" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="numeroSolicitud" title="${message(code: 'solicitud.numeroSolicitud.label', default: 'Numero Solicitud')}" />
					
						<g:sortableColumn property="lastUpdated" title="${message(code: 'solicitud.fechaSolicitud.label', default: 'Fecha')}" />
					
						<g:sortableColumn property="justificacion" title="${message(code: 'solicitud.justificacion.label', default: 'Justificacion')}" />
					
						<g:sortableColumn property="estado" title="${message(code: 'solicitud.estado.label', default: 'Estado')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${solicitudInstanceList}" status="i" var="solicitudInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${solicitudInstance.id}">${solicitudInstance.toString()}</g:link></td>
					
						<td><g:formatDate date="${solicitudInstance.fechaSolicitud ?: solicitudInstance.lastUpdated}" /></td>
					
						<td>${fieldValue(bean: solicitudInstance, field: "justificacion")}</td>
					
						<td>
							<g:if test="${solicitudInstance?.estado}">
								<span class="property-value" aria-labelledby="estado-label">
									<g:select name="estado" disabled="true"
										from="${['F' as char, 'A' as char, 'R' as char, 'V' as char, 'E' as char, 'T' as char, 'C' as char]}"
										valueMessagePrefix="solicitud.estado" value="${solicitudInstance.estado}" />
								</span>
							</g:if>
						</td>
					
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
