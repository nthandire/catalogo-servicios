
<%@ page import="mx.gob.inr.catservicios.IncidenteLaboratorio" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'incidenteLaboratorio.label', default: 'IncidenteLaboratorio')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-incidenteLaboratorio" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-incidenteLaboratorio" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="idIncidente" title="${message(code: 'incidenteLaboratorio.idIncidente.label', default: 'Id Incidente')}" />
					
						<g:sortableColumn property="fechaLaboratorio" title="${message(code: 'incidenteLaboratorio.fechaLaboratorio.label', default: 'Fecha Laboratorio')}" />
					
						<g:sortableColumn property="numeroLaboratorio" title="${message(code: 'incidenteLaboratorio.numeroLaboratorio.label', default: 'Numero Laboratorio')}" />
					
						<g:sortableColumn property="idEstado" title="${message(code: 'incidenteLaboratorio.idEstado.label', default: 'Id Estado')}" />
					
						<g:sortableColumn property="idTiposervicio" title="${message(code: 'incidenteLaboratorio.idTiposervicio.label', default: 'Id Tiposervicio')}" />
					
						<g:sortableColumn property="fallaTecnica" title="${message(code: 'incidenteLaboratorio.fallaTecnica.label', default: 'Falla Tecnica')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${incidenteLaboratorioInstanceList}" status="i" var="incidenteLaboratorioInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${incidenteLaboratorioInstance.id}">${fieldValue(bean: incidenteLaboratorioInstance, field: "idIncidente")}</g:link></td>
					
						<td><g:formatDate date="${incidenteLaboratorioInstance.fechaLaboratorio}" /></td>
					
						<td>${fieldValue(bean: incidenteLaboratorioInstance, field: "numeroLaboratorio")}</td>
					
						<td>${fieldValue(bean: incidenteLaboratorioInstance, field: "idEstado")}</td>
					
						<td>${fieldValue(bean: incidenteLaboratorioInstance, field: "idTiposervicio")}</td>
					
						<td>${fieldValue(bean: incidenteLaboratorioInstance, field: "fallaTecnica")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${incidenteLaboratorioInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
