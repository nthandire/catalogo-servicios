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
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="edit-solicitudDetalle" class="content scaffold-edit" role="main">
			<h1><g:message code="default.edit.label" args="[entityName]" /></h1>
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

      <!-- Modal para Autorizar -->
      <div id="myModal" class="modal hide fade" tabindex="-1" data-keyboard="true" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
          <h3 id="myModalLabel">Firma digital</h3>
        </div>
        <g:form method="post" >
          <div class="modal-body">

            <fieldset class="form">
              <g:render template="formFirmar"/>
            </fieldset>

          </div>
            <fieldset class="buttons">
              <g:actionSubmit class="save" action="solucionar"
                value="${message(code: 'default.button.update.label',
                  default: 'Update')}" />
            </fieldset>
        </g:form>
      </div>

				<fieldset class="buttons">
					<g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
          <g:if test="${solicitudDetalleInstance?.idPrograma && solicitudDetalleInstance?.solucion}">
          <a href="#myModal" class="edit" data-toggle="modal">Marcar como solucionado</a>
          </g:if>
          <g:link class="create" action="create"
            params="['solicitud.id': solicitudDetalleInstance?.idSolicitud?.id, 'detalle.id': solicitudDetalleInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'solicitudArchivoadjunto.label', default: 'SolicitudArchivoadjunto')])}</g:link>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
