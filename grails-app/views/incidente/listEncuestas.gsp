
<%@ page import="mx.gob.inr.catservicios.*" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'incidente.label', default: 'Incidente')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
    <g:set var="firmado" bean="firmadoService"/>
		<a href="#list-incidente" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<g:render template="ligas"/>
			</ul>
		</div>
		<div id="list-incidente" class="content scaffold-list" role="main">
			<h1>Incidentes en encuesta</h1>
			<g:if test="${flash.message}">
  			<div class="message" role="status">${flash.message}</div>
			</g:if>
      <g:if test="${flash.error}">
        <div class="errors" role="status">${flash.error}</div>
      </g:if>
			<table>
				<thead>
					<tr>

						<g:sortableColumn property="numeroIncidente" title="${message(code: 'incidente.numeroIncidente.label', default: 'Incidente')}" />

            <g:sortableColumn property="idResguardoentregadetalle" title="${message(code: 'incidente.idResguardoentregadetalle.label', default: 'Equipo')}" />

            <g:sortableColumn property="fechaIncidente" title="${message(code: 'incidente.fechaIncidente.label', default: 'Fecha Incidente')}" />

            <g:sortableColumn property="nivel"
              title="${message(code: 'incidente.nivel.label', default: 'Nivel')}" />

						<g:sortableColumn property="estado" title="${message(code: 'incidente.estado.label', default: 'Estado')}" />

						<g:sortableColumn property="idReporta" title="${message(code: 'incidente.idReporta.label', default: 'Reporta')}" />

					</tr>
				</thead>
				<tbody>
				<g:each in="${incidenteInstanceList}" status="i" var="incidenteInstance">
          <% def liga = createLink(action: "showIncidenteEncuesta", id: incidenteInstance.id) %>
          <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

            <td>
              <a href="${liga}">
                ${incidenteInstance.toString()}
              </a>
            </td>

            <td>
                ${firmado.tipoEquipo(incidenteInstance.idResguardoentregadetalle)}
            </td>

            <td><g:formatDate date="${incidenteInstance.fechaIncidente}" /></td>

            <td>${incidenteInstance.nivel}</td>

            <td>${fieldValue(bean: incidenteInstance, field: "estado")}</td>

            <td>${Usuario.get(incidenteInstance.idReporta)}</td>

          </tr>
          </a>
        </g:each>
        </tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${incidenteInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
