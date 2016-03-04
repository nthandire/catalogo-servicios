
<%@ page import="mx.gob.inr.catservicios.MonitoreoDetalle" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'monitoreoDetalle.label', default: 'MonitoreoDetalle')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-monitoreoDetalle" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-monitoreoDetalle" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="estado" title="${message(code: 'monitoreoDetalle.estado.label', default: 'Estado')}" />
					
						<g:sortableColumn property="observaciones" title="${message(code: 'monitoreoDetalle.observaciones.label', default: 'Observaciones')}" />
					
						<th><g:message code="monitoreoDetalle.bitacoradetalle.label" default="Bitacoradetalle" /></th>
					
						<th><g:message code="monitoreoDetalle.monitoreo.label" default="Monitoreo" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${monitoreoDetalleInstanceList}" status="i" var="monitoreoDetalleInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${monitoreoDetalleInstance.id}">${fieldValue(bean: monitoreoDetalleInstance, field: "estado")}</g:link></td>
					
						<td>${fieldValue(bean: monitoreoDetalleInstance, field: "observaciones")}</td>
					
						<td>${fieldValue(bean: monitoreoDetalleInstance, field: "bitacoradetalle")}</td>
					
						<td>${fieldValue(bean: monitoreoDetalleInstance, field: "monitoreo")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${monitoreoDetalleInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
