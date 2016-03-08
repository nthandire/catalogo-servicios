
<%@ page import="mx.gob.inr.catservicios.Monitoreo" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'monitoreo.label', default: 'Monitoreo')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-monitoreo" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-monitoreo" class="content scaffold-list" role="main">
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

						<g:sortableColumn property="numeroMonitoreo" title="${message(code: 'monitoreo.numeroMonitoreo.label', default: 'Numero')}" />

						<g:sortableColumn property="fecha" title="${message(code: 'monitoreo.fecha.label', default: 'Fecha')}" />

						<th><g:message code="monitoreo.bitacora.label" default="Bitacora" /></th>

						<g:sortableColumn property="estado" title="${message(code: 'monitoreo.estado.label', default: 'Estado')}" />

						<g:sortableColumn property="semaforo" title="${message(code: 'monitoreo.semaforo.label', default: 'Semaforo')}" />

						<g:sortableColumn property="nota" title="${message(code: 'monitoreo.nota.label', default: 'Nota')}" />

					</tr>
				</thead>
				<tbody>
				<g:each in="${monitoreoInstanceList}" status="i" var="monitoreoInstance">
					<% def liga = createLink(action: "show", id: monitoreoInstance.id) %>
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

						<td><a href="${liga}">
							${monitoreoInstance}
						</a></td>

						<td><a href="${liga}">
							${fieldValue(bean: monitoreoInstance, field: "fecha")}
						</a></td>

						<td><a href="${liga}">
							${fieldValue(bean: monitoreoInstance, field: "bitacora")}
						</a></td>

						<td><a href="${liga}">
							<g:message code="cat_servCat.estado.${monitoreoInstance.estado}" />
						</a></td>

						<td><a href="${liga}">
							<g:message code="intensidad.valor.${monitoreoInstance.semaforo}" />
						</a></td>

						<td><a href="${liga}">
							${fieldValue(bean: monitoreoInstance, field: "nota")}
						</a></td>

					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${monitoreoInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
