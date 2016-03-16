
<%@ page import="mx.gob.inr.catservicios.Bitacora" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'bitacora.label', default: 'Bitacora')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-bitacora" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label.femenino" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-bitacora" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>

						<g:sortableColumn property="tipoBitacora" title="${message(code: 'bitacora.tipoBitacora.label', default: 'Sistema')}" />

						<g:sortableColumn property="descripcion" title="${message(code: 'bitacora.descripcion.label', default: 'Descripción')}" />

						<g:sortableColumn property="estado" title="${message(code: 'bitacora.estado.label', default: 'Estado')}" />

					</tr>
				</thead>
				<tbody>
				<g:each in="${bitacoraInstanceList}" status="i" var="bitacoraInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

						<td><g:link action="show" id="${bitacoraInstance.id}">${fieldValue(bean: bitacoraInstance, field: "tipoBitacora")}</g:link></td>

						<td>${fieldValue(bean: bitacoraInstance, field: "descripcion")}</td>

						<td><g:message code="cat_servCat.estado.${bitacoraInstance.estado}" /></td>

					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${bitacoraInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
