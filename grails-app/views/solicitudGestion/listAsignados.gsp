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
    <g:set var="firmado" bean="firmadoService"/>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <g:render template="ligas"/>
			</ul>
		</div>
		<div id="list-autoriza" class="content scaffold-list" role="main">
			<h1>Requerimientos asignados</h1>
			<g:if test="${flash.message}">
			  <div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:if test="${flash.error}">
			  <div class="errors" role="status">${flash.error}</div>
			</g:if>
			<table>
				<thead>
					<tr>

						<g:sortableColumn property="numeroSolicitud" title="${message(code: 'solicitud.numeroSolicitud.label', default: 'Requerimiento')}" />

						<g:sortableColumn property="nombre" title="Solicitante" />

						<g:sortableColumn property="lastUpdated" title="${message(code: 'solicitud.fechaSolicitud.label', default: 'Fecha de Revisión')}" />

						<g:sortableColumn property="justificacion" title="${message(code: 'solicitud.justificacion.label', default: 'Justificacion')}" />

					</tr>
				</thead>
				<tbody>
				<g:each in="${asignadosInstanceList}" status="i" var="solicitudInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

						<td><g:link action="show" id="${solicitudInstance.id}">${solicitudInstance.toString()}</g:link></td>

						<td>${firmado.usuarioNombre(solicitudInstance.idSolicitante)}</td>

						<td><g:formatDate date="${solicitudInstance.fechaRevisa}" /></td>

						<td>${fieldValue(bean: solicitudInstance, field: "justificacion")}</td>

					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${asignadosInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
