
<%@ page import="mx.gob.inr.catservicios.Problema" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'problema.label', default: 'Problema')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-problema" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-problema" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
      <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
      </g:if>
			<g:if test="${flash.error}">
			 <div class="errors" role="status">${flash.error}</div>
			</g:if>
			<ol class="property-list problema">

				<g:if test="${problemaInstance?.fuente}">
				<li class="fieldcontain">
					<span id="fuente-label" class="property-label">
            <g:message code="problema.fuente.label" default="Fuente" />
          </span>

					<span class="property-value" aria-labelledby="fuente-label">
            <g:link controller="$controlador" action="show" id="${problemaInstance?.idFuente}">
              <g:fieldValue bean="${problemaInstance}" field="fuente"/>
            </g:link>
          </span>

        </li>
        </g:if>

        <g:if test="${problemaInstance?.idFuente}">
        <li class="fieldcontain">
          <span id="idFuente-label" class="property-label"><g:message code="problema.idFuente.label" default="Id Fuente" /></span>

          <span class="property-value" aria-labelledby="idFuente-label">
            <g:link controller="$controlador" action="show" id="${problemaInstance?.idFuente}">
              <g:fieldValue bean="${problemaInstance}" field="idFuente"/>
            </g:link>
          </span>

				</li>
				</g:if>

				<g:if test="${problemaInstance?.fechaProblema}">
				<li class="fieldcontain">
					<span id="fechaProblema-label" class="property-label"><g:message code="problema.fechaProblema.label" default="Fecha Problema" /></span>

						<span class="property-value" aria-labelledby="fechaProblema-label"><g:formatDate date="${problemaInstance?.fechaProblema}" /></span>

				</li>
				</g:if>

				<g:if test="${problemaInstance?.folio}">
				<li class="fieldcontain">
					<span id="folio-label" class="property-label"><g:message code="problema.folio.label" default="Folio" /></span>

						<span class="property-value" aria-labelledby="folio-label"><g:fieldValue bean="${problemaInstance}" field="folio"/></span>

				</li>
				</g:if>

				<g:if test="${problemaInstance?.observaciones}">
				<li class="fieldcontain">
					<span id="observaciones-label" class="property-label"><g:message code="problema.observaciones.label" default="Observaciones" /></span>

						<span class="property-value" aria-labelledby="observaciones-label"><g:fieldValue bean="${problemaInstance}" field="observaciones"/></span>

				</li>
				</g:if>

				<g:if test="${problemaInstance?.solucion}">
				<li class="fieldcontain">
					<span id="solucion-label" class="property-label"><g:message code="problema.solucion.label" default="Solucion" /></span>

						<span class="property-value" aria-labelledby="solucion-label"><g:fieldValue bean="${problemaInstance}" field="solucion"/></span>

				</li>
				</g:if>

				<g:if test="${problemaInstance?.fechaSolucion}">
				<li class="fieldcontain">
					<span id="fechaSolucion-label" class="property-label"><g:message code="problema.fechaSolucion.label" default="Fecha Solucion" /></span>

						<span class="property-value" aria-labelledby="fechaSolucion-label"><g:formatDate date="${problemaInstance?.fechaSolucion}" /></span>

				</li>
				</g:if>

				<g:if test="${problemaInstance?.resolvio}">
				<li class="fieldcontain">
					<span id="resolvio-label" class="property-label"><g:message code="problema.resolvio.label" default="Resolvio" /></span>

						<span class="property-value" aria-labelledby="resolvio-label"><g:fieldValue bean="${problemaInstance}" field="resolvio"/></span>

				</li>
				</g:if>

				<g:if test="${problemaInstance?.idUsuario}">
				<li class="fieldcontain">
					<span id="idUsuario-label" class="property-label"><g:message code="problema.idUsuario.label" default="Id Usuario" /></span>

						<span class="property-value" aria-labelledby="idUsuario-label"><g:fieldValue bean="${problemaInstance}" field="idUsuario"/></span>

				</li>
				</g:if>

				<g:if test="${problemaInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="problema.lastUpdated.label" default="Fecha Modificacion" /></span>

						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${problemaInstance?.lastUpdated}" /></span>

				</li>
				</g:if>

				<g:if test="${problemaInstance?.ipTerminal}">
				<li class="fieldcontain">
					<span id="ipTerminal-label" class="property-label"><g:message code="problema.ipTerminal.label" default="Ip Terminal" /></span>

						<span class="property-value" aria-labelledby="ipTerminal-label"><g:fieldValue bean="${problemaInstance}" field="ipTerminal"/></span>

				</li>
				</g:if>

			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${problemaInstance?.id}" />
					<g:link class="edit" action="edit" id="${problemaInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<!--g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /-->
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
