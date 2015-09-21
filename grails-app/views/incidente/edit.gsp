<%@ page import="mx.gob.inr.catservicios.Incidente" %>
<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'incidente.label', default: 'Incidente')}" />
    <title><g:message code="default.edit.label" args="[entityName]" /></title>
  </head>
  <body>
    <a href="#edit-incidente" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
    <div class="nav" role="navigation">
      <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
      </ul>
    </div>
    <div id="edit-incidente" class="content scaffold-edit" role="main">
      <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
      <g:if test="${flash.message}">
      <div class="message" role="status">${flash.message}</div>
      </g:if>
      <g:hasErrors bean="${incidenteInstance}">
      <ul class="errors" role="alert">
        <g:eachError bean="${incidenteInstance}" var="error">
        <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
        </g:eachError>
      </ul>
      </g:hasErrors>
      <g:form method="post" >
        <g:hiddenField name="id" value="${incidenteInstance?.id}" />
        <g:hiddenField name="version" value="${incidenteInstance?.version}" />
        <fieldset class="form">
          <g:render template="form"/>
        </fieldset>
      <H1>
        <g:message code="solicitud.archivos.label" default="Archivos" />
      </H1>

      <div class="row-fluid">
        <div class="span10 offset1">
          <ul class="one-to-many">
            <g:each in="${incidenteInstance?.archivos}" var="a">
              <li>
                <g:link action="download" id="${a.id}">${a?.encodeAsHTML()}</g:link>
              </li>
            </g:each>
            <li class="add">
              <g:link class="create" action="createArchivo"
                params="['incidente.id': incidenteInstance?.id]">
                ${message(code: 'default.add.label',
                  args: [message(code: 'solicitudArchivoadjunto.label',
                                  default: 'SolicitudArchivoadjunto')])}
              </g:link>
            </li>
          </ul>
        </div>
      </div>


        <fieldset class="buttons">
          <g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
        </fieldset>
      </g:form>
    </div>
  </body>
</html>
