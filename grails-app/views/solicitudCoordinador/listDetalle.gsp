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
				<li><g:link class="list" action="listDetalle">Asignación de servicio</g:link></li>
        <%--
				<li><g:link class="list" action="listAsignados">Solicitudes asignadas</g:link></li>
				<li><g:link class="list" action="listTerminadas">Solicitudes terminadas</g:link></li>
        --%>
			</ul>
		</div>
		<div id="list-solicitudDetalle" class="content scaffold-list" role="main">
			<h1>Asignación de servicio</h1>
			<g:if test="${flash.message}">
			  <div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:if test="${flash.error}">
			  <div class="errors" role="status">${flash.error}</div>
			</g:if>
			<table>
				<thead>
					<tr>

						<g:sortableColumn property="numeroSolicitud"
              title="${message(code: 'solicitud.numeroSolicitud.label', default: 'Requerimiento')}" />

            <g:sortableColumn property="lastUpdated"
              title="${message(code: 'solicitud.fechaSolicitud.label',
                default: 'Fecha inicio Atención')}" />

						<g:sortableColumn property="tiempo" title="Tiempo de atención" />

						<g:sortableColumn property="categoria" title="Categoría" />

            <g:sortableColumn property="servicio" title="Categoría de Tercer nivel" />

						<g:sortableColumn property="descripcion"
              title="${message(code: 'solicitudDetalle.descripcion.label',
                default: 'Descripcion')}" />

					</tr>
				</thead>
				<tbody>
				<g:each in="${solicitudDetalleInstanceList}" status="i"
          var="solicitudDetalleInstance">

					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

						<td><g:link action="edit" id="${solicitudDetalleInstance.id}">
              ${fieldValue(bean: solicitudDetalleInstance, field: "idSolicitud")}
            </g:link></td>

						<td>
              <g:formatDate date="${solicitudDetalleInstance.idSolicitud.fechaRevisa}" /></td>

            <td>
              ${solicitudDetalleInstance?.idServ?.tiempo2}
              ${solicitudDetalleInstance?.idServ?.unidades2?.descripcion}
            </td>

            <td>
              ${fieldValue(bean: solicitudDetalleInstance, field: "idServcat")}
            </td>

						<td>
              ${fieldValue(bean: solicitudDetalleInstance, field: "idServ")}
            </td>

						<td>
              ${fieldValue(bean: solicitudDetalleInstance, field: "descripcion")}
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
