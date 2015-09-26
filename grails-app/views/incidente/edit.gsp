<%@ page import="mx.gob.inr.catservicios.*" %>
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
      <g:if test="${flash.error}">
        <div class="errors" role="status">${flash.error}</div>
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
              <g:each in="${IncidenteArchivoadjunto.findAllByIdIncidente(incidenteInstance?.id)}" var="a">
                <li>
                  <g:link action="download" id="${a.id}">${a?.encodeAsHTML()}</g:link>
                </li>
              </g:each>
              <li class="add">
                <g:link class="create" action="createArchivo"
                  params="['incidente.id': incidenteInstance?.id]">
                  ${message(code: 'default.add.label',
                    args: [message(code: 'solicitudArchivoadjunto.label',
                                    default: 'Archivo')])}
                </g:link>
              </li>
            </ul>
          </div>
        </div>

        <div id="responsive" class="modal hide fade" tabindex="-1" data-width="512">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            <h3 id="titulo">error Escalar incidente error</h3>
          </div>
          <div class="modal-body">
            <div class="row-fluid">
              <div class="span12">
                <h4>Solución del Incidente</h4>
                <fieldset class="form">
                  <g:render template="formSolucion1"/>
                </fieldset>
              </div>
            </div>
          </div>
          <div class="modal-footer">
            <fieldset class="buttons">
              <a data-dismiss="modal" class="cancel">Cancelar</a>
              <input type="submit" name="_action_resp" id="respButton"
                value="${message(code: 'default.button.update.label',
                  default: 'Update')}"
                class="save">
            </fieldset>
          </div>
        </div>

        <script>
          function escala() {
            $("#titulo").text('Escalar incidente');
            $("#idPrograma").prop("required", false);
            $("#solucionNivel1").prop("required", true);
            $("#respButton").attr('name','_action_escalaUpdate');
            $("#passwordfirma").prop("required", true);
          }
          function soluciona() {
            $("#titulo").text('Soluciona incidente');
            $("#idPrograma").prop("required", true);
            $("#solucionNivel1").prop("required", true);
            $("#respButton").attr('name','_action_soluciónUpdate');
            $("#passwordfirma").prop("required", true);
          }
          function update() {
            $("#idPrograma").prop("required", false);
            $("#solucionNivel1").prop("required", false);
            $("#passwordfirma").prop("required", false);
          }
        </script>


        <fieldset class="buttons">
          <g:actionSubmit class="edit" action="update"
            value="${message(code: 'default.button.update.label',
                             default: 'Update')}"
            onclick="update()"/>
          <a class="save" data-toggle="modal" href="#responsive"
            onclick="soluciona()">Solucionar Incidente</a>
          <a class="escala" data-toggle="modal" href="#responsive"
            onclick="escala()">Escalar Incidente</a>
        </fieldset>
      </g:form>
    </div>
  </body>
</html>
