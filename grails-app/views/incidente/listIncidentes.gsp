<%@ page import="mx.gob.inr.catservicios.*" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main1250">
    <meta http-equiv="refresh" content="120">
		<g:set var="entityName" value="${message(code: 'incidente.label', default: 'Incidente')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
    <style>
      a:link, a:visited {
        color: black;
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
        <g:render template="ligas"/>
			</ul>
		</div>
		<div id="list-autoriza" class="content scaffold-list" role="main">
			<h1>Semáforo de Incidentes</h1>
			<g:if test="${flash.message}">
			  <div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:if test="${flash.error}">
			  <div class="errors" role="status">${flash.error}</div>
			</g:if>
			<table>
				<thead>
					<tr>

            <g:sortableColumn property="semaforo" title="Semaforo" />

            <g:sortableColumn property="numeroIncidente" title="${message(code: 'incidente.folio.label', default: 'Incidente')}" />


            <g:sortableColumn property="inicio" title="inicio" />

            <g:sortableColumn property="nombre" title="Solicitante" />

            <g:sortableColumn property="area" title="Área" />

            <g:sortableColumn property="nivel" title="Cuerpo : Nivel" />

            <g:sortableColumn property="categoria" title="Categoría" />

            <g:sortableColumn property="subcategoria" title="Subcategoría" />

            <g:sortableColumn property="servicio" title="Tercer nivel" />

            <g:sortableColumn property="estado" title="${message(code: 'solicitud.estado.label', default: 'Estado')}" />

            <th>Correo</th>

					</tr>
				</thead>
				<tbody>
				<g:each in="${incidentesInstanceList}" status="i" var="incidente">
					<tr>

            <td style="background-color: ${incidente.color};">&nbsp;</td>

						<td><g:link action="showIncidenteSemaforo" id="${incidente.caso.id}" params="[offset: bOffset?:0]">
              ${incidente.caso.toString()}
            </g:link></td>

            <td>
              <g:formatDate date="${incidente.caso.fechaIncidente}" />
            </td>

            <td>
              ${firmado.usuarioNombre(incidente.caso.idReporta)}
            </td>

            <td>${firmado.areaDetalladaNombre(incidente.caso.idReporta)}</td>

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
