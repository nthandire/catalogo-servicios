
<%@ page import="mx.gob.inr.catservicios.SolicitudArchivoadjunto" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'solicitudArchivoadjunto.label', default: 'SolicitudArchivoadjunto')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-solicitudArchivoadjunto" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-solicitudArchivoadjunto" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="idSolicitud" title="${message(code: 'solicitudArchivoadjunto.idSolicitud.label', default: 'Id Solicitud')}" />
					
						<g:sortableColumn property="datos" title="${message(code: 'solicitudArchivoadjunto.datos.label', default: 'Datos')}" />
					
						<g:sortableColumn property="nombre" title="${message(code: 'solicitudArchivoadjunto.nombre.label', default: 'Nombre')}" />
					
						<g:sortableColumn property="tamanio" title="${message(code: 'solicitudArchivoadjunto.tamanio.label', default: 'Tamanio')}" />
					
						<g:sortableColumn property="tipo" title="${message(code: 'solicitudArchivoadjunto.tipo.label', default: 'Tipo')}" />
					
						<g:sortableColumn property="idUsuario" title="${message(code: 'solicitudArchivoadjunto.idUsuario.label', default: 'Id Usuario')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${solicitudArchivoadjuntoInstanceList}" status="i" var="solicitudArchivoadjuntoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${solicitudArchivoadjuntoInstance.id}">${fieldValue(bean: solicitudArchivoadjuntoInstance, field: "idSolicitud")}</g:link></td>
					
						<td>${fieldValue(bean: solicitudArchivoadjuntoInstance, field: "datos")}</td>
					
						<td>${fieldValue(bean: solicitudArchivoadjuntoInstance, field: "nombre")}</td>
					
						<td>${fieldValue(bean: solicitudArchivoadjuntoInstance, field: "tamanio")}</td>
					
						<td>${fieldValue(bean: solicitudArchivoadjuntoInstance, field: "tipo")}</td>
					
						<td>${fieldValue(bean: solicitudArchivoadjuntoInstance, field: "idUsuario")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${solicitudArchivoadjuntoInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
