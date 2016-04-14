
<%@ page import="mx.gob.inr.catservicios.BitacoraDetalle" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'bitacoraDetalle.label', default: 'BitacoraDetalle')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-bitacoraDetalle" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-bitacoraDetalle" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>

						<g:sortableColumn property="descripcion" title="${message(code: 'bitacoraDetalle.descripcion.label', default: 'Descripcion')}" />

						<g:sortableColumn property="estado" title="${message(code: 'bitacoraDetalle.estado.label', default: 'Estado')}" />

						<th><g:message code="bitacoraDetalle.bitacora.label" default="Sistema" /></th>

					</tr>
				</thead>
				<tbody>
				<g:each in="${bitacoraDetalleInstanceList}" status="i" var="bitacoraDetalleInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

						<td><g:link action="show" id="${bitacoraDetalleInstance.id}">${fieldValue(bean: bitacoraDetalleInstance, field: "descripcion")}</g:link></td>

						<td>${fieldValue(bean: bitacoraDetalleInstance, field: "estado")}</td>

						<td>${fieldValue(bean: bitacoraDetalleInstance, field: "bitacora")}</td>

					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${bitacoraDetalleInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
