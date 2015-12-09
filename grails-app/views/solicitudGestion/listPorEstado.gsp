<%@ page import="mx.gob.inr.catservicios.*" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'autoriza.label', default: 'Autoriza')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
    <g:render template="stiloLigas"/>
	</head>
	<body>
		<a href="#list-autoriza" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
    <g:set var="firmado" bean="firmadoService"/>
		<div class="nav" role="navigation">
			<ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <g:render template="ligas"/>
			</ul>
		</div>
		<div id="list-autoriza" class="content scaffold-list" role="main">
			<h1>Requerimientos por estado</h1>
			<g:if test="${flash.message}">
			  <div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:if test="${flash.error}">
			  <div class="errors" role="status">${flash.error}</div>
			</g:if>
			<table>
				<thead>
					<tr>

            <th>
            ${message(code: 'solicitudDetalle.numeroSolicitud.label', default: 'Requerimiento')}
            </th>

            <th>
            ${message(code: 'solicitudDetalle.err..inicioatencion.label', default: 'Inicio de atención')}
            </th>

            <th>Solicitante</th>

            <th>Área</th>

            <th>Extensión</th>

            <th>Categoría</th>

            <th>Subcategoría</th>

            <th>Categoría de Tercer nivel</th>

            <th>${message(code: 'solicitud.estado.label', default: 'Estado')}</th>

            <th>Correo</th>

					</tr>
				</thead>
				<tbody>
				<g:each in="${detallesInstanceList}" status="i" var="detalleInstance">
					<tr style="background-color: ${detalleInstance.color};">

						<td><g:link action="showDetallePorEstado" id="${detalleInstance.caso.id}" params="[offset: bOffset?:0]">
              ${detalleInstance.caso.idSolicitud.toString()}
            </g:link></td>

            <td>
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

            <td>
              <g:if test="${detalleInstance.orden == 0}">
                <g:link class="btn" action="correoEstado" id="${detalleInstance.caso.id}" params="[offset: bOffset?:0]">
                  enviar
                </g:link>
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