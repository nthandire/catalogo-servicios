<%@ page import="mx.gob.inr.catservicios.Solicitud" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'solicitud.label', default: 'Solicitud')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#edit-solicitud" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="edit-solicitud" class="content scaffold-edit" role="main">
      <h1>Descripción de la solicitud</h1>
      <g:if test="${flash.message}">
      <div class="message" role="status">${flash.message}</div>
      </g:if>
      <g:if test="${flash.error}">
        <div class="errors" role="status">${flash.error}</div>
      </g:if>
      <g:hasErrors bean="${solicitudInstance}">
      <ul class="errors" role="alert">
        <g:eachError bean="${solicitudInstance}" var="error">
        <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
        </g:eachError>
      </ul>
      </g:hasErrors>
      <g:form method="post" >
        <g:hiddenField name="id" value="${solicitudInstance?.id}" />
        <g:hiddenField name="version" value="${solicitudInstance?.version}" />
        <g:hiddenField name="idDetalle" value="${solicitudDetalleInstance?.id}" />
        <g:hiddenField name="versionDetalle" value="${solicitudDetalleInstance?.version}" />
        <fieldset class="form">
          <g:render template="form"/>
        </fieldset>


        <div id="responsive" class="modal hide fade" tabindex="-1"
          data-width="512" style="width:90%; position: fixed; left: 350px;">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            <h3 id="titulo">Captura detalle de la solicitud.</h3>
          </div>
          <div class="modal-body">
            <div class="row-fluid">
              <div class="span12">
                <h4>Solución del Incidente</h4>
                <fieldset class="form">
                  <g:render template="formDetalle"/>
                </fieldset>
              </div>
            </div>
          </div>
          <div class="modal-footer">
            <fieldset class="buttons">
              <a data-dismiss="modal" class="cancel">Cancelar</a>
              <input type="submit" name="_action_updateDetalle" id="respButton"
                value="${message(code: 'default.button.update.label',
                  default: 'Update')}"
                class="save">
            </fieldset>
          </div>
        </div>


        <script>
          function detalle() {
            $("#idServcat").prop("required", true);
            $("#descripcion").prop("required", true);
          }
          function maestro() {
            $("#idServcat").prop("required", false);
            $("#descripcion").prop("required", false);
          }
        </script>


				<fieldset class="buttons">
					<g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
          <g:if test="${!solicitudInstance?.estado || solicitudInstance?.estado == 'F' as char}">
            <g:link class="edit" action="edit" id="${solicitudInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
            <a href="#myModal" class="edit" data-toggle="modal">
                ${message(code: 'default.add.label',
                  args: [message(code: 'solicitudDetalle.label',
                    default: 'SolicitudDetalle')])}</a>
            <g:if test="${!solicitudInstance?.estado}">
              <!-- Button to trigger modal -->
              <a href="#myModal" class="edit" data-toggle="modal">Firmar</a>
            </g:if>
          </g:if>
        </fieldset>
      </g:form>
    </div>
  </body>
</html>
<%--
            <g:link class="btn" controller="solicitudDetalle" action="create"
              params="['solicitud.id': solicitudInstance?.id]">
            </g:link>
--%>
