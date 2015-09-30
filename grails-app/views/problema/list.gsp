
<%@ page import="mx.gob.inr.catservicios.Problema" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'problema.label', default: 'Problema')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-problema" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-problema" class="content scaffold-list" role="main">
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

						<g:sortableColumn property="fuente" title="${message(code: 'problema.fuente.label', default: 'Fuente')}" />

						<g:sortableColumn property="idFuente" title="${message(code: 'problema.idFuente.label', default: 'Id Fuente')}" />

						<g:sortableColumn property="fechaProblema" title="${message(code: 'problema.fechaProblema.label', default: 'Fecha Problema')}" />

						<g:sortableColumn property="folio" title="${message(code: 'problema.folio.label', default: 'Folio')}" />

						<g:sortableColumn property="observaciones" title="${message(code: 'problema.observaciones.label', default: 'Observaciones')}" />

						<g:sortableColumn property="solucion" title="${message(code: 'problema.solucion.label', default: 'Solucion')}" />

					</tr>
				</thead>
				<tbody>
				<g:each in="${problemaInstanceList}" status="i" var="problemaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

						<td><g:link action="show" id="${problemaInstance.id}">${fieldValue(bean: problemaInstance, field: "fuente")}</g:link></td>

						<td>${fieldValue(bean: problemaInstance, field: "idFuente")}</td>

						<td><g:formatDate date="${problemaInstance.fechaProblema}" /></td>

						<td>${fieldValue(bean: problemaInstance, field: "folio")}</td>

						<td>${fieldValue(bean: problemaInstance, field: "observaciones")}</td>

						<td>${fieldValue(bean: problemaInstance, field: "solucion")}</td>

					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${problemaInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
