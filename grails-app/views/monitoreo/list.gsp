<%@ page import="mx.gob.inr.catservicios.*" %>
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

            <th><g:message code="monitoreo.bitacora.label" default="Sistema" /></th>

						<th><g:message code="monitoreo.bitacora.label" default="Tipo" /></th>

						<g:sortableColumn property="estado" title="${message(code: 'monitoreo.estado.label', default: 'Estado')}" />

						<g:sortableColumn property="semaforo" title="${message(code: 'monitoreo.semaforo.label', default: 'Semaforo')}" />

            <g:sortableColumn property="idSeguimiento" title="${message(code: 'monitoreo.semaforo.label', default: 'Nota de Seguimiento')}" />

						<th><g:message code="monitoreo.nota.label" default="Nota" /></th>

					</tr>
				</thead>
				<tbody>
				<g:each in="${monitoreoInstanceList}" status="i" var="monitoreoInstance">
					<% def liga = createLink(action: "show", id: monitoreoInstance.id) %>
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

						<td><a href="${liga}">
							${monitoreoInstance}
						</a></td>

						<td>${monitoreoInstance.fecha.format("YY/MM/dd HH:mm")}</td>

            <td>${fieldValue(bean: monitoreoInstance, field: "bitacora")}</td>

						<td>${TipoMonitoreo.get(monitoreoInstance.idTipomonitoreo)}</td>

						<td><g:message code="monitoreos.estado.${monitoreoInstance.estado}" /></td>

						<td><g:message code="intensidad.valor.${monitoreoInstance.semaforo}" /></td>

            <td>
              ${monitoreoInstance.idSeguimiento ?Monitoreo.get(monitoreoInstance.idSeguimiento) : ""}
            </td>

						<td>${fieldValue(bean: monitoreoInstance, field: "nota")}</td>

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
