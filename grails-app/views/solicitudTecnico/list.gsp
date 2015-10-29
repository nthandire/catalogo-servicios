
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
    <g:set var="firmado" bean="firmadoService"/>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			</ul>
		</div>
		<div id="list-solicitudDetalle" class="content scaffold-list" role="main">
			<h1>Servicios por atender</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:if test="${flash.error}">
			<div class="errors" role="status">${flash.error}</div>
			</g:if>
			<table>
				<thead>
					<tr>

						<th><g:message code="solicitudDetalle.idSolicitud.label" default="Solicitud" /></th>

            <th><g:message code="solicitudDetalle.idServcat.label" default="Categoría" /></th>

						<th><g:message code="solicitudDetalle.idServ.label" default="Tercer nivel" /></th>

            <g:sortableColumn property="descripcion" title="${message(code: 'solicitudDetalle.descripcion.label', default: 'Descripcion')}" />

						<g:sortableColumn property="prioridad" title="${message(code: 'solicitudDetalle.prioridad.label', default: 'Prioridad')}" />

            <g:sortableColumn property="lastUpdated"
              title="${message(code: 'solicitud.fechaSolicitud.label',
                default: 'Fecha inicio Atención')}" />

            <g:sortableColumn property="tiempo" title="Tiempo de atención" />

					</tr>
				</thead>
				<tbody>
				<g:each in="${solicitudDetalleInstanceList.sort{ it.prioridad?:(it?.idServ?.impacto?:3) }}" status="i" var="solicitudDetalleInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

						<td><g:link action="edit" id="${solicitudDetalleInstance.id}">${fieldValue(bean: solicitudDetalleInstance, field: "idSolicitud")}</g:link></td>

            <td>${fieldValue(bean: solicitudDetalleInstance, field: "idServcat")}</td>

						<td>${fieldValue(bean: solicitudDetalleInstance, field: "idServ")}</td>

            <td>${fieldValue(bean: solicitudDetalleInstance, field: "descripcion")}</td>

						<td><g:message code="intensidad.valor.${solicitudDetalleInstance.prioridad?:(solicitudDetalleInstance?.idServ?.impacto)}" default="" /></td>

            <td>
              <g:formatDate date="${solicitudDetalleInstance.idSolicitud.fechaRevisa}" /></td>

            <td>
              ${solicitudDetalleInstance?.idServ?.tiempo2}
              ${solicitudDetalleInstance?.idServ?.unidades2?.descripcion}
            </td>

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
