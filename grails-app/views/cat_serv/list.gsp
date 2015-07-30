
<%@ page import="mx.gob.inr.catservicios.Cat_serv" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'cat_serv.label', default: 'Cat_serv')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-cat_serv" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-cat_serv" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="cat_serv.servCat.label" default="Serv Sub" /></th>

						<th><g:message code="cat_serv.servSub.label" default="Serv Sub" /></th>
					
						<g:sortableColumn property="descripcion" title="${message(code: 'cat_serv.descripcion.label', default: 'Descripcion')}" />
					
						<g:sortableColumn property="portal" title="${message(code: 'cat_serv.portal.label', default: 'Portal')}" />
					
						<g:sortableColumn property="incidente" title="${message(code: 'cat_serv.incidente.label', default: 'Incidente')}" />
					
						<g:sortableColumn property="solicitud" title="${message(code: 'cat_serv.solicitud.label', default: 'Solicitud')}" />
					
						<g:sortableColumn property="problema" title="${message(code: 'cat_serv.problema.label', default: 'Problema')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${cat_servInstanceList}" status="i" var="cat_servInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${cat_servInstance.id}">${fieldValue(bean: cat_servInstance, field: "servSub.servCat")}</g:link></td>

						<td><g:link action="show" id="${cat_servInstance.id}">${fieldValue(bean: cat_servInstance, field: "servSub")}</g:link></td>
					
						<td>${fieldValue(bean: cat_servInstance, field: "descripcion")}</td>
					
						<td><g:checkBox name="portal" value="${cat_servInstance?.portal}" /></td>
					
						<td><g:checkBox name="incidente" value="${cat_servInstance.incidente}" /></td>
					
						<td><g:checkBox name="solicitud" value="${cat_servInstance.solicitud}" /></td>
					
						<td><g:checkBox name="problema" value="${cat_servInstance.problema}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${cat_servInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
