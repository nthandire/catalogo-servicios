<%@ page import="mx.gob.inr.catservicios.*" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'autoriza.label', default: 'Autoriza')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-autoriza" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list">Solicitudes a revisar</g:link></li>
        <li><g:link class="list" action="listTodas">Todas</g:link></li>
				<li><g:link class="list" action="listAsignados">Solicitudes asignadas</g:link></li>
        <li><g:link class="list" action="listEncuestas">Solicitudes en encuesta</g:link></li>
				<li><g:link class="list" action="listTerminadas">Solicitudes termindadas</g:link></li>
			</ul>
		</div>
		<div id="list-autoriza" class="content scaffold-list" role="main">
			<h1>Solicitudes a revisar</h1>
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

						<g:sortableColumn property="nombre" title="Solicitante" />

						<g:sortableColumn property="lastUpdated" title="${message(code: 'solicitud.fechaSolicitud.label', default: 'Fecha autorización/Vobo')}" />

						<g:sortableColumn property="justificacion" title="${message(code: 'solicitud.justificacion.label', default: 'Justificacion')}" />

						<g:sortableColumn property="estado" title="${message(code: 'solicitud.estado.label', default: 'Estado')}" />

					</tr>
				</thead>
				<tbody>
				<g:each in="${autorizadosInstanceList}" status="i" var="solicitudInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

						<td><g:link action="show" id="${solicitudInstance.id}">${solicitudInstance.toString()}</g:link></td>

						<td>${Usuario.get(solicitudInstance.idSolicitante)}</td>

						<td><g:formatDate date="${solicitudInstance.fechaVb?:solicitudInstance.fechaAutoriza}" /></td>

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
				<g:paginate total="${autorizadosInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
