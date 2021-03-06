<%@ page import="mx.gob.inr.catservicios.*" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'monitoreo.label', default: 'Bitacora')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#edit-monitoreo" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="edit-monitoreo" class="content scaffold-edit" role="main">
			<h1><g:message code="default.edit.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
      <g:if test="${flash.error}">
        <div class="errors" role="status">${flash.error}</div>
      </g:if>
			<g:hasErrors bean="${monitoreoInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${monitoreoInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form method="post" >
				<g:hiddenField name="id" value="${monitoreoInstance?.id}" />
				<g:hiddenField name="version" value="${monitoreoInstance?.version}" />
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>

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
              <li class="add">
                <g:link class="btn" action="createArchivo"
                  params="['monitoreo.id': monitoreoInstance?.id]">
                  ${message(code: 'default.subir.label',
                    args: [message(code: 'solicitudArchivoadjunto.label',
                                    default: 'Archivo')])}
                </g:link>
                (Debe guardar sus cambios antes de subir archivos o los perderá. El tamaño maximo de un archivo es de 5 MB)
              </li>
            </ul>
          </div>
        </div>

        <div id="responsive" class="modal hide fade" tabindex="-1" data-width="512">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            <h3 id="titulo">Marcar Bitacora como problema</h3>
          </div>
          <div class="modal-body">
            <div class="row-fluid">
              <div class="span12">
                <fieldset class="form">
                  <g:render template="formSolucion"/>
                </fieldset>
              </div>
            </div>
          </div>
          <div class="modal-footer">
            <fieldset class="buttons">
              <a data-dismiss="modal" class="cancel">Cancelar</a>
              <input type="submit" name="_action_problema" id="respButton"
                value="Guardar"
                class="save">
            </fieldset>
          </div>
        </div>

				<fieldset class="buttons">
          <g:actionSubmit class="save" action="update" value="Guardar" />
					<a class="cancel" data-toggle="modal" href="#responsive">Marcar como problema</a>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
