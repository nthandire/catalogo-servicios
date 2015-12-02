<%@ page import="mx.gob.inr.catservicios.*" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'autoriza.label', default: 'Autoriza')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-autoriza" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
    <g:set var="firmado" bean="firmadoService"/>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			</ul>
		</div>
		<div id="list-autoriza" class="content scaffold-list" role="main">
			<h1>Requerimientos</h1>
			<g:if test="${flash.message}">
			  <div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:if test="${flash.error}">
			  <div class="errors" role="status">${flash.error}</div>
			</g:if>
			<table>
				<thead>
					<tr>

						<g:sortableColumn property="numeroSolicitud" title="${message(code: 'solicitudDetalle.numeroSolicitud.label', default: 'Requerimiento')}" />

						<g:sortableColumn property="lastUpdated" title="${message(code: 'solicitudDetalle.err..inicioatencion.label', default: 'Inicio de atención')}" />

            <g:sortableColumn property="nombre" title="Solicitante" />

            <g:sortableColumn property="nombre" title="Área" />

            <g:sortableColumn property="nombre" title="Extensión" />

            <g:sortableColumn property="nombre" title="Categoría" />

            <g:sortableColumn property="nombre" title="Subcategoría" />

            <g:sortableColumn property="nombre" title="Categoría de Tercer nivel" />

						<g:sortableColumn property="estado" title="${message(code: 'solicitud.estado.label', default: 'Estado')}" />

					</tr>
				</thead>
				<tbody>
				<g:each in="${detallesInstanceList}" status="i" var="detalleInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

						<td><g:link action="showDetalle" id="${detalleInstance.caso.id}" params="[offset: bOffset?:0]">
              ${detalleInstance.caso.idSolicitud.toString()}
            </g:link></td>

            <td style="background-color: ${detalleInstance.orden == 1 ? 'red' : detalleInstance.orden == 2 ? 'yellow' : detalleInstance.orden == 3 ? 'green' : 'white'};">
              <g:formatDate date="${detalleInstance.caso.idSolicitud.fechaRevisa?:detalleInstance.caso.idSolicitud.fechaVb?:detalleInstance.caso.idSolicitud.fechaAutoriza}" />
            </td>

            <td>
              ${firmado.usuarioNombre(detalleInstance.caso.idSolicitud.idSolicitante)}
            </td>

            <td>${firmado.areaNombre(detalleInstance.caso.idSolicitud.idSolicitante)}</td>

            <td>${Usuario.get(detalleInstance.caso.idSolicitud.idSolicitante).extension}</td>

            <td>${detalleInstance.caso.idServcat.categoria}</td>

            <td>${detalleInstance.caso.idServ?.servSub}</td>

            <td>${detalleInstance.caso.idServ}</td>

						<td>
							<g:if test="${detalleInstance.caso?.idSolicitud.estado}">
								<span class="property-value" aria-labelledby="estado-label">
									<g:message code="solicitud.estado.${detalleInstance.caso.idSolicitud.estado}" />
								</span>
							</g:if>
						</td>

					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${detallesInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
