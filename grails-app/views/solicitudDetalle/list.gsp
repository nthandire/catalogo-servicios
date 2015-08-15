
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
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-solicitudDetalle" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="solicitudDetalle.idSolicitud.label" default="Id Solicitud" /></th>
					
						<th><g:message code="solicitudDetalle.idServ.label" default="Id Serv" /></th>
					
						<g:sortableColumn property="idResguardoentregadetalle" title="${message(code: 'solicitudDetalle.idResguardoentregadetalle.label', default: 'Id Resguardoentregadetalle')}" />
					
						<g:sortableColumn property="estadoSolictuddetalle" title="${message(code: 'solicitudDetalle.estadoSolictuddetalle.label', default: 'Estado Solictuddetalle')}" />
					
						<g:sortableColumn property="descripcion" title="${message(code: 'solicitudDetalle.descripcion.label', default: 'Descripcion')}" />
					
						<g:sortableColumn property="solucion" title="${message(code: 'solicitudDetalle.solucion.label', default: 'Solucion')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${solicitudDetalleInstanceList}" status="i" var="solicitudDetalleInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${solicitudDetalleInstance.id}">${fieldValue(bean: solicitudDetalleInstance, field: "idSolicitud")}</g:link></td>
					
						<td>${fieldValue(bean: solicitudDetalleInstance, field: "idServ")}</td>
					
						<td>${fieldValue(bean: solicitudDetalleInstance, field: "idResguardoentregadetalle")}</td>
					
						<td>${fieldValue(bean: solicitudDetalleInstance, field: "estadoSolictuddetalle")}</td>
					
						<td>${fieldValue(bean: solicitudDetalleInstance, field: "descripcion")}</td>
					
						<td>${fieldValue(bean: solicitudDetalleInstance, field: "solucion")}</td>
					
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