<%@ page import="mx.gob.inr.catservicios.*" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'autoriza.label', default: 'Autoriza')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
    <style>
      a:link {
        color: black;
        background-color: transparent;
        text-decoration: none;
      }
      a:visited {
        color: pink;
        background-color: transparent;
        text-decoration: none;
      }
      a:hover {
        color: blue;
        background-color: transparent;
        text-decoration: underline;
      }
      a:active {
        color: orange;
        background-color: transparent;
        text-decoration: underline;
      }
    </style>
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
			<h1>Incidentes por semaforo</h1>
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
            No. Incidente
            </th>

            <th>
            ${message(code: 'solicitudDetalle.err.inicioatencion.label', default: 'Inicio de atención')}
            </th>

            <th>Solicitante</th>

            <th>Área</th>
%{--

            <th>Extensión</th>

            <th>Categoría</th>

            <th>Subcategoría</th>

            <th>Categoría de Tercer nivel</th>

            <th>${message(code: 'solicitud.estado.label', default: 'Estado')}</th>

            <th>Correo</th>
--}%
					</tr>
				</thead>
				<tbody>
				<g:each in="${incidentesInstanceList}" status="i" var="instance">
					<tr style="background-color: ${instance.color};">

						<td><g:link action="showIncidente" id="${instance.caso.id}" params="[offset: bOffset?:0]">
              ${instance.caso.toString()}
            </g:link></td>

            <td style="background-color: ${instance.color};">
              <g:formatDate date="${instance.caso.fechaIncidente}" />
            </td>

            <td>
              ${firmado.usuarioNombre(instance.caso.idReporta)}
            </td>

            <td>${firmado.areaNombre(instance.caso.idReporta)}</td>
%{--
            <td>${Usuario.get(instance.caso.idSolicitud.idSolicitante).extension}</td>

            <td>${instance.caso.idServcat.categoria}</td>

            <td>${instance.caso.idServ?.servSub}</td>

            <td>${instance.caso.idServ}</td>

            <td>
              <g:if test="${instance.caso?.idSolicitud.estado}">
                <span class="property-value" aria-labelledby="estado-label">
                  <g:message code="solicitud.estado.${instance.caso.idSolicitud.estado}" />
                </span>
              </g:if>
            </td>

            <td>
              <g:if test="${instance.orden == 0}">
                <g:link class="btn" action="correo" id="${instance.caso.id}" params="[offset: bOffset?:0]">
                  enviar
                </g:link>
              </g:if>
            </td>
--}%
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${incidentesInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
