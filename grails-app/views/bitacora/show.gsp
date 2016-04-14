
<%@ page import="mx.gob.inr.catservicios.*" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'monitoreo.label', default: 'Bitacora')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-monitoreo" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-monitoreo" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			  <div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:if test="${flash.error}">
			  <div class="errors" role="status">${flash.error}</div>
			</g:if>
			<ol class="property-list monitoreo">

<div class="row-fluid">
  <div class="span3">

				<li class="fieldcontain">
					<span id="fecha-label" class="property-label"><g:message code="monitoreo.fecha.label" default="Fecha" /></span>
					<span class="property-value" aria-labelledby="fecha-label"><g:formatDate date="${monitoreoInstance?.fecha}" /></span>
				</li>

  </div>
  <div class="span3">

				<li class="fieldcontain">
					<span id="numeroMonitoreo-label" class="property-label">
						<g:message code="monitoreo.numeroMonitoreo.label" default="Número" />
					</span>
					<span class="property-value" aria-labelledby="numeroMonitoreo-label">
						${monitoreoInstance}
					</span>
				</li>

  </div>
  <div class="span3">

				<li class="fieldcontain">
					<span id="bitacora-label" class="property-label">
						<g:message code="monitoreo.bitacora.label" default="Sistema" />
					</span>
					<span class="property-value" aria-labelledby="bitacora-label">
						${monitoreoInstance?.bitacora?.encodeAsHTML()}
					</span>
				</li>

  </div>
  <div class="span3">

				<li class="fieldcontain">
					<span id="estado-label" class="property-label"><g:message code="monitoreo.estado.label" default="Estado" /></span>
					<span class="property-value" aria-labelledby="estado-label">
					<g:message code="monitoreos.estado.${monitoreoInstance.estado}" />
					</span>
				</li>

  </div>
</div>

<div class="row-fluid">
  <div class="span6">

        <li class="fieldcontain">
          <span id="semaforo-label" class="property-label"><g:message code="monitoreo.semaforo.label" default="Semaforo:" /></span>
          <span class="property-value" aria-labelledby="semaforo-label">
          <g:message code="intensidad.valor.${monitoreoInstance.semaforo}" />
          </span>
        </li>

  </div>
  <div class="span6">

        <li class="fieldcontain">
          <span id="idTipomonitoreo-label" class="property-label"><g:message code="monitoreo.idTipomonitoreo.label" default="Tipo:" /></span>
          <span class="property-value" aria-labelledby="idTipomonitoreo-label">
            ${TipoMonitoreo.get(monitoreoInstance.idTipomonitoreo)}
          </span>
        </li>

  </div>
</div>

<div class="row-fluid">
  <div class="span12">

				<li class="fieldcontain">
					<span id="nota-label" class="property-label"><g:message code="monitoreo.nota.label" default="Nota:" /></span>
					<span class="property-value" aria-labelledby="nota-label"><g:fieldValue bean="${monitoreoInstance}" field="nota"/></span>
				</li>

  </div>
</div>

				<g:if test="${monitoreoInstance?.detalles}">
					<div style="height:40px;"></div>
				  <div style="margin: auto; width: 80%;" >
				    <table style="width=800px;">
						  <tr>
						    <th colspan="2">Nodo</th>
						    <th>¿Respondio?</th>
						    <th>Observaciones</th>
						  </tr>
				      <g:each in="${monitoreoInstance?.detalles.sort{it.id}}" var="m">
				          <tr style="text-align: left;">
				            <td colspan="2">
			                ${m?.encodeAsHTML()}
				            </td>
				            <td style="text-align: left;">
				              <g:checkBox name="det[${m.id}]" value="${m.estado}" disabled="true" />
				            </td>
				            <td>
				              <g:field type="text" name="observ[${m.id}]"
				              	value="${m.observaciones}" disabled="true" />
				            </td>
				          </tr>
				      </g:each>
		          <tr style="max-height:10px;">
		            <td style="width:200px;">&nbsp;</td>
		            <td style="width:200px;">&nbsp;</td>
		            <td></td>
		            <td></td>
		          </tr>
				    </table>
				  </div>
				</g:if>

			</ol>

			<g:if test="${MonitoreoArchivoadjunto.countByIdMonitoreo(monitoreoInstance?.id)}">
        <H1>
          <g:message code="solicitud.archivos.label" default="Archivos" />
        </H1>
        <div class="row-fluid">
          <div class="span10 offset1">
            <ul class="one-to-many">
              <g:each in="${MonitoreoArchivoadjunto.findAllByIdMonitoreo(monitoreoInstance?.id)}" var="a">
                <li>
                  <g:link action="download" id="${a.id}">${a?.encodeAsHTML()}</g:link>
                </li>
              </g:each>
            </ul>
          </div>
        </div>
			</g:if>

			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${monitoreoInstance?.id}" />
          <g:if test="${monitoreoInstance.estado == 'A' as char && Bitacora.countByBitacoraAndIdGreaterThan(monitoreoInstance.bitacora, monitoreoInstance.id) == 0}">
            <g:link class="edit" action="edit" id="${monitoreoInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
          </g:if>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
