
<%@ page import="mx.gob.inr.catservicios.Solicitud" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'solicitud.label', default: 'Solicitud')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-solicitud" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-solicitud" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			  <div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:if test="${flash.error}">
			  <div class="errors" role="status">${flash.error}</div>
			</g:if>
			<ol class="property-list solicitud">

				<g:if test="${solicitudInstance?.numeroSolicitud}">
				<li class="fieldcontain">
					<span id="numeroSolicitud-label" class="property-label"><g:message code="solicitud.numeroSolicitud.label" default="Numero Solicitud" /></span>

						<span class="property-value" aria-labelledby="numeroSolicitud-label"><g:fieldValue bean="${solicitudInstance}" field="numeroSolicitud"/></span>

				</li>
				</g:if>

				<g:if test="${solicitudInstance?.fechaSolicitud}">
				<li class="fieldcontain">
					<span id="fechaSolicitud-label" class="property-label"><g:message code="solicitud.fechaSolicitud.label" default="Fecha Solicitud" /></span>

						<span class="property-value" aria-labelledby="fechaSolicitud-label"><g:formatDate date="${solicitudInstance?.fechaSolicitud}" /></span>

				</li>
				</g:if>

				<g:if test="${solicitudInstance?.estado}">
				<li class="fieldcontain">
					<span id="estado-label" class="property-label"><g:message code="solicitud.estado.label" default="Estado" /></span>

						<span class="property-value" aria-labelledby="estado-label"><g:fieldValue bean="${solicitudInstance}" field="estado"/></span>

				</li>
				</g:if>

				<g:if test="${solicitudInstance?.justificacion}">
				<li class="fieldcontain">
					<span id="justificacion-label" class="property-label"><g:message code="solicitud.justificacion.label" default="Justificacion" /></span>

						<span class="property-value" aria-labelledby="justificacion-label"><g:fieldValue bean="${solicitudInstance}" field="justificacion"/></span>

				</li>
				</g:if>

				<g:if test="${solicitudInstance?.idSolicitante}">
				<li class="fieldcontain">
					<span id="idSolicitante-label" class="property-label"><g:message code="solicitud.idSolicitante.label" default="Id Solicitante" /></span>

						<span class="property-value" aria-labelledby="idSolicitante-label"><g:fieldValue bean="${solicitudInstance}" field="idSolicitante"/></span>

				</li>
				</g:if>

				<g:if test="${solicitudInstance?.idAutoriza}">
				<li class="fieldcontain">
					<span id="idAutoriza-label" class="property-label"><g:message code="solicitud.idAutoriza.label" default="Id Autoriza" /></span>

						<span class="property-value" aria-labelledby="idAutoriza-label"><g:fieldValue bean="${solicitudInstance}" field="idAutoriza"/></span>

				</li>
				</g:if>

				<g:if test="${solicitudInstance?.fechaAutoriza}">
				<li class="fieldcontain">
					<span id="fechaAutoriza-label" class="property-label"><g:message code="solicitud.fechaAutoriza.label" default="Fecha Autoriza" /></span>

						<span class="property-value" aria-labelledby="fechaAutoriza-label"><g:formatDate date="${solicitudInstance?.fechaAutoriza}" /></span>

				</li>
				</g:if>

				<g:if test="${solicitudInstance?.idVb}">
				<li class="fieldcontain">
					<span id="idVb-label" class="property-label"><g:message code="solicitud.idVb.label" default="Id Vb" /></span>
						<span class="property-value" aria-labelledby="idVb-label"><g:fieldValue bean="${solicitudInstance}" field="idVb"/></span>

				</li>
				</g:if>

				<g:if test="${solicitudInstance?.fechaVb}">
				<li class="fieldcontain">
					<span id="fechaVb-label" class="property-label"><g:message code="solicitud.fechaVb.label" default="Fecha Vb" /></span>
						<span class="property-value" aria-labelledby="fechaVb-label"><g:formatDate date="${solicitudInstance?.fechaVb}" /></span>

				</li>
				</g:if>

				<g:if test="${solicitudInstance?.p01}">
				<li class="fieldcontain">
					<span id="p01-label" class="property-label"><g:message code="solicitud.p01.label" default="P01" /></span>
          <span class="property-value" aria-labelledby="p01-label">
            <g:select name="p01" disabled="true"
              from="${[1,2]}" style="width: 60px"
              valueMessagePrefix="encuesta.valor" value="${solicitudInstance.p01}" />
          </span>
				</li>
				</g:if>

				<g:if test="${solicitudInstance?.p02}">
				<li class="fieldcontain">
					<span id="p02-label" class="property-label"><g:message code="solicitud.p02.label" default="P02" /></span>
          <span class="property-value" aria-labelledby="p02-label">
            <g:select name="p02" disabled="true"
              from="${[1,2]}" style="width: 60px"
              valueMessagePrefix="encuesta.valor" value="${solicitudInstance.p02}" />
          </span>
				</li>
				</g:if>

				<g:if test="${solicitudInstance?.p03}">
				<li class="fieldcontain">
					<span id="p03-label" class="property-label"><g:message code="solicitud.p03.label" default="P03" /></span>
          <span class="property-value" aria-labelledby="p03-label">
            <g:select name="p03" disabled="true"
              from="${[1,2]}" style="width: 60px"
              valueMessagePrefix="encuesta.valor" value="${solicitudInstance.p03}" />
          </span>
				</li>
				</g:if>

				<g:if test="${solicitudInstance?.p04}">
				<li class="fieldcontain">
					<span id="p04-label" class="property-label"><g:message code="solicitud.p04.label" default="P04" /></span>
          <span class="property-value" aria-labelledby="p04-label">
            <g:select name="p04" disabled="true"
              from="${[1,2]}" style="width: 60px"
              valueMessagePrefix="encuesta.valor" value="${solicitudInstance.p04}" />
          </span>
				</li>
				</g:if>

				<g:if test="${solicitudInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="solicitud.lastUpdated.label" default="Last Updated" /></span>

						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${solicitudInstance?.lastUpdated}" /></span>

				</li>
				</g:if>

				<g:if test="${solicitudInstance?.ipTerminal}">
				<li class="fieldcontain">
					<span id="ipTerminal-label" class="property-label"><g:message code="solicitud.ipTerminal.label" default="Ip Terminal" /></span>

						<span class="property-value" aria-labelledby="ipTerminal-label"><g:fieldValue bean="${solicitudInstance}" field="ipTerminal"/></span>

				</li>
				</g:if>

				<g:if test="${solicitudInstance?.archivos}">
				<li class="fieldcontain">
					<span id="archivos-label" class="property-label"><g:message code="solicitud.archivos.label" default="Archivos" /></span>

						<g:each in="${solicitudInstance.archivos}" var="a">
						<span class="property-value" aria-labelledby="archivos-label"><g:link controller="solicitudArchivoadjunto" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></span>
						</g:each>

				</li>
				</g:if>

				<g:if test="${solicitudInstance?.detalles}">
				<li class="fieldcontain">
					<span id="detalles-label" class="property-label"><g:message code="solicitud.detalles.label" default="Detalles" /></span>
						<g:each in="${solicitudInstance.detalles}" var="d">
            <%--
            <span class="property-value" aria-labelledby="detalles-label"><g:link controller="solicitudDetalle" action="show" id="${d.id}">${d?.encodeAsHTML()}</g:link></span>
            --%>
						<span class="property-value" aria-labelledby="detalles-label">${d?.encodeAsHTML()}</span>
						</g:each>
				</li>
				</g:if>

			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${solicitudInstance?.id}" />
					<g:link class="edit" action="edit" id="${solicitudInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
