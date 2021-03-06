<%@ page import="mx.gob.inr.catservicios.SolicitudDetalle" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'solicitudDetalle.label', default: 'SolicitudDetalle')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#edit-solicitudDetalle" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="list">Lista de servicios</g:link></li>
        <li><g:link class="back" action="show" id="${solicitudDetalleInstance?.idSolicitud?.id}">Regresar al servicio</g:link></li>
			</ul>
		</div>
		<div id="edit-solicitudDetalle" class="content scaffold-edit" role="main">
			<h1>Editar servicio</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
      <g:if test="${flash.error}">
        <div class="errors" role="status">${flash.error}</div>
      </g:if>
			<g:hasErrors bean="${solicitudDetalleInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${solicitudDetalleInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form method="post" >
				<g:hiddenField name="id" value="${solicitudDetalleInstance?.id}" />
				<g:hiddenField name="version" value="${solicitudDetalleInstance?.version}" />
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>


      <H1>
        <g:message code="solicitud.archivos.label" default="Archivos" />
      </H1>

      <div class="row-fluid">
        <div class="span10 offset1">
          <ul class="one-to-many">
            <g:if test="${solicitudDetalleInstance.idSolicitud?.archivos}">
              <g:each in="${solicitudDetalleInstance.idSolicitud.archivos.sort{it.id}}" var="a">
                <li><g:link controller="solicitudArchivoadjunto"
                            action="download" id="${a.id}">
                  ${a?.encodeAsHTML()}
                </g:link></li>
              </g:each>
            </g:if>
            <li class="add">
              <g:link class="create" action="create" class="btn"
                params="['solicitud.id': solicitudDetalleInstance?.idSolicitud?.id, 'detalle.id': solicitudDetalleInstance?.id]">${message(code: 'default.subir.label', args: [message(code: 'solicitudArchivoadjunto.label', default: 'SolicitudArchivoadjunto')])}</g:link>
            </li>
          </ul>
        </div>
      </div>


				<fieldset class="buttons">
          <g:if test="${"AV".contains(solicitudDetalleInstance.idSolicitud.estado.toString())}">
  					<g:actionSubmit class="save" action="update"
              value="${message(code: 'default.button.update.label',
                               default: 'Update')}" />
           </g:if>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
