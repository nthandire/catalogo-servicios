
<%@ page import="mx.gob.inr.catservicios.Cat_servCat" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'cat_servCat.label', default: 'Cat_servCat')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-cat_servCat" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-cat_servCat" class="content scaffold-list" role="main">
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
					
						<g:sortableColumn property="categoria" title="${message(code: 'cat_servCat.categoria.label', default: 'Categoria')}" />
					
						<g:sortableColumn property="descripcion" title="${message(code: 'cat_servCat.descripcion.label', default: 'Descripcion')}" />
					
						<g:sortableColumn property="servResp" title="${message(code: 'cat_servCat.servResp.label', default: 'Serv Resp')}" />
					
						<g:sortableColumn property="valoracion" title="${message(code: 'cat_servCat.valoracion.label', default: 'Valoracion')}" />
					
						<g:sortableColumn property="disponibilidad" title="${message(code: 'cat_servCat.disponibilidad.label', default: 'Disponibilidad')}" />
					
						<g:sortableColumn property="estado" title="${message(code: 'cat_servCat.estado.label', default: 'Estado')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${cat_servCatInstanceList}" status="i" var="cat_servCatInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${cat_servCatInstance.id}">${fieldValue(bean: cat_servCatInstance, field: "categoria")}</g:link></td>
					
						<td>${fieldValue(bean: cat_servCatInstance, field: "descripcion")}</td>
					
						<td>${fieldValue(bean: cat_servCatInstance, field: "servResp")}</td>
					
						<td><g:message code="intensidad.valor.${cat_servCatInstance.valoracion}" default="Valora..." /></td>
					
						<td>${fieldValue(bean: cat_servCatInstance, field: "disponibilidad")}</td>
					
						<td><g:message code="cat_servCat.estado.${cat_servCatInstance.estado}" default="Valora..." /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${cat_servCatInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
