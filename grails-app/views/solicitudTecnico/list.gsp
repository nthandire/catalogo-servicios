
<%@ page import="mx.gob.inr.catservicios.SolicitudDetalle" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'solicitudDetalle.label', default: 'SolicitudDetalle')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-solicitudDetalle" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			</ul>
		</div>
		<div id="list-solicitudDetalle" class="content scaffold-list" role="main">
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
					
						<th><g:message code="solicitudDetalle.idSolicitud.label" default="Id Solicitud" /></th>
					
						<th><g:message code="solicitudDetalle.idServcat.label" default="Categoría" /></th>
					
						<g:sortableColumn property="descripcion" title="${message(code: 'solicitudDetalle.descripcion.label', default: 'Descripcion')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${solicitudDetalleInstanceList}" status="i" var="solicitudDetalleInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${solicitudDetalleInstance.id}">${fieldValue(bean: solicitudDetalleInstance, field: "idSolicitud")}</g:link></td>
					
						<td>${fieldValue(bean: solicitudDetalleInstance, field: "idServcat")}</td>
					
						<td>${fieldValue(bean: solicitudDetalleInstance, field: "descripcion")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${solicitudDetalleInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
