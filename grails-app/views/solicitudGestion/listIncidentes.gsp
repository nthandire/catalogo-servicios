<%@ page import="mx.gob.inr.catservicios.*" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'autoriza.label', default: 'Autoriza')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
    <g:render template="estiloLigas"/>
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

            <g:sortableColumn property="folio" title="${message(code: 'incidente.folio.label', default: 'No. Incidente')}" />

            <g:sortableColumn property="semaforo" title="${message(code: 'solicitudDetalle.err.inicioatencion.label', default: 'Inicio de atención')}" />

            <th>Solicitante</th>

            <th>Área</th>

            <th>Cuerpo : Nivel</th>

            <th>Categoría</th>

            <th>Subcategoría</th>

            <th>Categoría de Tercer nivel</th>

            <g:sortableColumn property="estado" title="${message(code: 'solicitud.estado.label', default: 'Estado')}" />

            <th>Correo</th>

					</tr>
				</thead>
				<tbody>
				<g:each in="${incidentesInstanceList}" status="i" var="incidente">
					<tr style="background-color: ${incidente.color};">

						<td><g:link action="showIncidente" id="${incidente.caso.id}" params="[offset: bOffset?:0]">
              ${incidente.caso.toString()}
            </g:link></td>

            <td style="background-color: ${incidente.color};">
              <g:formatDate date="${incidente.caso.fechaIncidente}" />
            </td>

            <td>
              ${firmado.usuarioNombre(incidente.caso.idReporta)}
            </td>

            <td>${firmado.areaNombre(incidente.caso.idReporta)}</td>

            <td>${firmado.cuerpoNivel(incidente.caso.idResguardoentregadetalle)}</td>

            <td>${incidente.caso.idServ?.servSub.servCat.categoria}</td>

            <td>${incidente.caso.idServ?.servSub}</td>

            <td>${incidente.caso.idServ}</td>

            <td>
              <g:if test="${incidente.caso?.estado}">
                <span class="property-value" aria-labelledby="estado-label">
                  ${incidente.caso.estado == 'A' as char ? firmado.asignado(incidente.caso) ? "Asignado" : "Abierto" : Cerrado}
                </span>
              </g:if>
            </td>

            <td>
              <g:if test="${incidente.orden == 0}">
                <g:link class="btn" action="correoIncidente" id="${incidente.caso.id}" params="[offset: bOffset?:0]">
                  enviar
                </g:link>
              </g:if>
            </td>

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
