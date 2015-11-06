
<%@ page import="mx.gob.inr.catservicios.*" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'solicitud.label', default: 'Solicitud')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>

    <style type="text/css">
      textArea { width: 412px; }
    </style>

	</head>
	<body>
		<a href="#show-solicitud" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="back" action="${params.back}">Regresar a la lista</g:link></li>
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

						<span class="property-value" aria-labelledby="numeroSolicitud-label">${solicitudInstance.toString()}</span>

        </li>
        </g:if>

        <g:set var="firmado" bean="firmadoService"/>
        <div class="row-fluid">
          <div class="span4">

            <li class="fieldcontain">
              <span id="nombre-label" class="property-label"><g:message code="solicitud.nombre.label" default="Solicitante" /></span>
              <span class="property-value" aria-labelledby="nombre-label">${Usuario.get(solicitudInstance?.idSolicitante)}</span>
            </li>

          </div>
          <div class="span3">

            <li class="fieldcontain">
              <span id="telefono-label" class="property-label">
                <g:message code="solicitud.telefono.label" default="Extensión" />
              </span>
                <span class="property-value" aria-labelledby="telefono-label">
                  ${Usuario.get(solicitudInstance?.idSolicitante).extension}
                </span>
            </li>

          </div>
          <div class="span3">

            <li class="fieldcontain">
              <span id="area-label" class="property-label">
                <g:message code="solicitud.area.label" default="Área" />
              </span>
                <span class="property-value" aria-labelledby="area-label">
                  ${firmado.areaNombre(solicitudInstance?.idSolicitante)}
                </span>
            </li>

          </div>
        </div>

        <div class="row-fluid">
          <div class="span4">

            <li class="fieldcontain">
              <span id="nombre-label" class="property-label"><g:message code="solicitud.nombre.label" default="Autorizador" /></span>
              <span class="property-value" aria-labelledby="nombre-label">${Usuario.get(solicitudInstance?.idAutoriza)}</span>
            </li>

          </div>
          <div class="span3">

            <li class="fieldcontain">
              <span id="telefono-label" class="property-label">
                <g:message code="solicitud.telefono.label" default="Extensión" />
              </span>
                <span class="property-value" aria-labelledby="telefono-label">
                  ${Usuario.get(solicitudInstance?.idAutoriza).extension}
                </span>
            </li>

          </div>

          <div class="span3">

            <li class="fieldcontain">
              <span id="area-label" class="property-label">
                <g:message code="solicitud.area.label" default="Área" />
              </span>
                <span class="property-value" aria-labelledby="area-label">
                  ${firmado.areaNombre(solicitudInstance?.idAutoriza)}
                </span>
            </li>

          </div>
        </div>

        <g:if test="${solicitudInstance?.idVb}">
          <div class="row-fluid">
            <div class="span4">

              <li class="fieldcontain">
                <span id="nombre-label" class="property-label"><g:message code="solicitud.nombre.label" default="Visto Bueno" /></span>
                <span class="property-value" aria-labelledby="nombre-label">${Usuario.get(solicitudInstance?.idVb)}</span>
              </li>

            </div>
            <div class="span3">

              <li class="fieldcontain">
                <span id="telefono-label" class="property-label">
                  <g:message code="solicitud.telefono.label" default="Extensión" />
                </span>
                  <span class="property-value" aria-labelledby="telefono-label">
                    ${Usuario.get(solicitudInstance?.idVb).extension}
                  </span>
              </li>

            </div>
            <div class="span3">

              <li class="fieldcontain">
                <span id="area-label" class="property-label">
                  <g:message code="solicitud.area.label" default="Área" />
                </span>
                  <span class="property-value" aria-labelledby="area-label">
                  ${firmado.areaNombre(solicitudInstance?.idVb)}
                  </span>
              </li>

            </div>
          </div>
        </g:if>

				<g:if test="${solicitudInstance?.justificacion}">
				<li class="fieldcontain">
					<span id="justificacion-label" class="property-label"><g:message code="solicitud.justificacion.label" default="Justificacion" /></span>

						<span class="property-value" aria-labelledby="justificacion-label"><g:fieldValue bean="${solicitudInstance}" field="justificacion"/></span>

				</li>
				</g:if>

				<g:if test="${solicitudInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="solicitud.lastUpdated.label" default="Fecha de solicitud" /></span>
						<span class="property-value" aria-labelledby="fechaSolicitud-label"><g:formatDate date="${solicitudInstance?.fechaSolicitud}" /></span>

				</li>
				</g:if>

				<g:if test="${solicitudInstance?.estado}">
				<li class="fieldcontain">
					<span id="estado-label" class="property-label">Estado</span>

					<span class="property-value" aria-labelledby="estado-label">
						<g:select name="estado" disabled="true"
							from="${['F' as char, 'A' as char, 'R' as char, 'V' as char, 'E' as char, 'T' as char, 'C' as char]}"
							valueMessagePrefix="solicitud.estado" value="${solicitudInstance.estado}" />
					</span>
				</li>
				</g:if>

			</ol>


			<H1>
				<g:message code="solicitud.detalles.label" default="Descripción del requerimiento" />
			</H1>

			<div class="row-fluid">
-				<div class="span10 offset1">
					<ul class="one-to-many">
						<g:each in="${solicitudInstance?.detalles}" var="d">
              <li>
                <g:link action="showDetalle" id="${d.id}">${d?.encodeAsHTML()}</g:link>
                ${d?.idServ?.servSub?.descripcion}
                ${d?.idServ?.descripcion}
              </li>
						</g:each>
					</ul>
				</div>
			</div>


			<H1>
				<g:message code="solicitud.archivos.label" default="Archivos" />
			</H1>

			<div class="row-fluid">
-				<div class="span10 offset1">
					<ul class="one-to-many">
            <g:if test="${solicitudInstance?.archivos}">
              <g:each in="${solicitudInstance.archivos.sort{it.id}}" var="a">
                  <li><g:link controller="solicitudArchivoadjunto"
                              action="download" id="${a.id}">
                        ${a?.encodeAsHTML()}
                  </g:link></li>
              </g:each>
            </g:if>
					</ul>
				</div>
			</div>

			<!-- Modal para dar Visto Bueno -->
			<div id="myModal" class="modal hide fade" tabindex="-1" data-keyboard="true" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			  <div class="modal-header">
			    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			    <h3 id="myModalLabel">Firma digital</h3>
			  </div>
				<g:form method="post" >
				  <div class="modal-body">

						<g:hiddenField name="id" value="${solicitudInstance?.id}" />
						<g:hiddenField name="version" value="${solicitudInstance?.version}" />
						<fieldset class="form">
							<g:render template="formFirmar"/>
						</fieldset>

				  </div>
						<fieldset class="buttons">
							<g:actionSubmit class="save" action="firmarUpdate" value="${message(code: 'default.button.update.label', default: 'Update')}" />
						</fieldset>
				</g:form>
			</div>

			<!-- Modal para Cancelar -->
			<div id="myModalCancelar" class="modal hide fade" tabindex="-1" data-keyboard="true" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			  <div class="modal-header">
			    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			    <h3 id="myModalLabel">Firma digital</h3>
			  </div>
				<g:form method="post" >
				  <div class="modal-body">

						<g:hiddenField name="id" value="${solicitudInstance?.id}" />
						<g:hiddenField name="version" value="${solicitudInstance?.version}" />
						<fieldset class="form">
							<g:render template="formFirmar"/>

              <div class="fieldtablecontain ">
                <label for="razon_de_cancelar">
                  Motivo de la cancelación
                </label>
                <g:textArea name="comentarioVb" cols="40" rows="5" maxlength="1000" required="" value=""/>
              </div>
						</fieldset>

				  </div>
						<fieldset class="buttons">
							<g:actionSubmit class="save" action="cancelaUpdate" value="Cancelar solicitud" />
						</fieldset>
				</g:form>
			</div>

			<g:form>
				<fieldset class="buttons">
					<g:if test="${solicitudInstance?.estado == 'A' as char}">
						<!-- Button to trigger modal -->
						<a href="#myModal" class="edit" data-toggle="modal">Visto Bueno</a>
						<a href="#myModalCancelar" class="edit" data-toggle="modal">Cancelar</a>
					</g:if>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
