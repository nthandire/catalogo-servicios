<%@ page import="mx.gob.inr.catservicios.*" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'solicitud.label', default: 'Solicitud')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-solicitud" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
    <g:set var="firmado" bean="firmadoService"/>
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

        <div class="row-fluid">
          <div class="span5 offset1">
            <g:if test="${solicitudInstance?.numeroSolicitud}">
            <li class="fieldcontain">
              <span id="numeroSolicitud-label" class="property-label"><g:message code="solicitud.numeroSolicitud.label" default="Numero Solicitud" /></span>
                <span class="property-value" aria-labelledby="numeroSolicitud-label">
                  ${solicitudInstance}
                </span>
            </li>
            </g:if>
          </div>

          <div class="span5">
            <g:if test="${solicitudInstance?.fechaSolicitud}">
            <li class="fieldcontain">
              <span id="fechaSolicitud-label" class="property-label"><g:message code="solicitud.fechaSolicitud.label" default="Fecha Solicitud" /></span>
                <span class="property-value" aria-labelledby="fechaSolicitud-label"><g:formatDate date="${solicitudInstance?.fechaSolicitud}" /></span>
            </li>
            </g:if>
          </div>
        </div>

        <h1>Categoría del servicio</h1>
        <g:each in="${SolicitudDetalle.findAllByIdSolicitudAndEstado(solicitudInstance,
                            'A' as char)}" var="d">
          <div class="fieldtablecontain">
            ${"Solicitud: ${d?.encodeAsHTML()} : ${d?.descripcion.encodeAsHTML()}"}
          </div>
          <div class="fieldtablecontain">
            ${"Solución: " + d?.solucion.encodeAsHTML()}
          </div>
          <div class="fieldtablecontain">
            -------------------------------------------
          </div>
        </g:each>
        <br/>

        <div class="row-fluid">
          <div class="span4">
            <div class="fieldtablecontain">
              <label for="nombre-label">
                <g:message code="solicitud.nombre.label" default="Autorizador"/>
              </label>
              <g:field type="text" name="nombre.no" disabled="true"
                value="${firmado.usuarioNombre(solicitudInstance?.idAutoriza)}"/>
            </div>
          </div>
          <div class="span3">
            <div class="fieldtablecontain">
              <label for="telefono-label">
                <g:message code="solicitud.telefono.label" default="Extensión" />
              </label>
              <g:field type="text" name="telefono.no" disabled="true"
                value="${firmado.usuarioNombre(solicitudInstance?.idAutoriza).extension}"/>
            </div>
          </div>
          <div class="span3">
            <div class="fieldtablecontain">
              <label for="area-label">
                <g:message code="solicitud.area.label" default="Área" />
              </label>
              <g:field type="text" name="area.no" disabled="true"
                value="${firmado.areaNombre(solicitudInstance?.idAutoriza)}"/>
            </div>
          </div>
        </div>

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

				<g:if test="${solicitudInstance?.archivos}">
				<li class="fieldcontain">
					<span id="archivos-label" class="property-label"><g:message code="solicitud.archivos.label" default="Archivos" /></span>

					<g:each in="${solicitudInstance.archivos}" var="a">
						<span class="property-value" aria-labelledby="archivos-label">
              <g:link controller="solicitudArchivoadjunto" action="download" id="${a.id}">${a?.encodeAsHTML()}</g:link>
            </span>
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
