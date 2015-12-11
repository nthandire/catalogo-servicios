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
        <g:render template="ligas"/>
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
                <g:link class="btn" action="createArchivo"
                  params="['incidente.id': incidenteInstance?.id]">
                  ${message(code: 'default.subir.label',
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
                <fieldset class="form">
                  <g:render template="formSolucion"/>
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

        <div id="responsiveTecnico" class="modal hide fade" tabindex="-1" data-width="512">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            <h3 id="tituloTec">Asignar Técnico</h3>
          </div>
          <div class="modal-body">
            <div class="row-fluid">
              <div class="span12">
                <fieldset class="form">
                  <g:render template="formTecnico"/>
                </fieldset>
              </div>
            </div>
          </div>
          <div class="modal-footer">
            <fieldset class="buttons">
              <a data-dismiss="modal" class="cancel">Cancelar</a>
              <input type="submit" name="_action_tecnicoUpdate" id="respButtonTec"
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
            $("#solucionNivel").prop("required", true);
            $("#respButton").attr('name','_action_escalaUpdate');
            $("#passwordfirma").prop("required", true);
            $("#passwordfirmaTec").prop("required", false);
            $("#solServCat").css("display", "none");
            $("#solServSub").css("display", "none");
            $("#solServ").css("display", "none");
            $("#solEstadoCierre").css("display", "none");
            $("#solEstadoCierreProblema").css("display", "none");
            $("#solSolucion").text('Acciones realizadas');
          }
          function soluciona() {
            $("#titulo").text('Soluciona incidente');
            $("#idPrograma").prop("required", true);
            $("#solucionNivel").prop("required", true);
            $("#respButton").attr('name','_action_solucionUpdate');
            $("#passwordfirma").prop("required", true);
            $("#passwordfirmaTec").prop("required", false);
            $("#solServCat").css("display", "block");
            $("#solServSub").css("display", "block");
            $("#solServ").css("display", "block");
            $("#solEstadoCierre").css("display", "block");
            $("#solEstadoCierreProblema").css("display", "none");
            $("#solSolucion").text('Solución');
          }
          function problema() {
            $("#titulo").text('Soluciona incidente');
            $("#idPrograma").prop("required", false);
            $("#solucionNivel").prop("required", true);
            $("#respButton").attr('name','_action_problemaUpdate');
            $("#passwordfirma").prop("required", true);
            $("#passwordfirmaTec").prop("required", false);
            $("#solServCat").css("display", "none");
            $("#solServSub").css("display", "none");
            $("#solServ").css("display", "none");
            $("#solEstadoCierre").css("display", "none");
            $("#solEstadoCierreProblema").css("display", "block");
            $("#solSolucion").text('Justificación');
          }
          function cancelar() {
            $("#titulo").text('Cancelar incidente');
            $("#idPrograma").prop("required", false);
            $("#solucionNivel").prop("required", true);
            $("#respButton").attr('name','_action_cancelarUpdate');
            $("#passwordfirma").prop("required", true);
            $("#passwordfirmaTec").prop("required", false);
            $("#solServCat").css("display", "none");
            $("#solServSub").css("display", "none");
            $("#solServ").css("display", "none");
            $("#solEstadoCierre").css("display", "none");
            $("#solEstadoCierreProblema").css("display", "block");
            $("#solSolucion").text('Motivo de cancelación');
          }
          function update() {
            $("#idPrograma").prop("required", false);
            $("#solucionNivel").prop("required", false);
            $("#passwordfirma").prop("required", false);
            $("#passwordfirmaTec").prop("required", false);
          }
          function tecnico() {
            $("#idPrograma").prop("required", false);
            $("#solucionNivel").prop("required", false);
            $("#passwordfirma").prop("required", false);
            $("#passwordfirmaTec").prop("required", true);
          }
        </script>


        <fieldset class="buttons">
          <g:actionSubmit class="edit" action="update"
            value="${message(code: 'default.button.update.label',
                             default: 'Update')}"
            onclick="update()"/>
          <sec:access expression="hasAnyRole('ROLE_SAST_GESTOR','ROLE_SAST_APROBADOR')">
            <a class="save" data-toggle="modal" href="#responsiveTecnico"
              onclick="tecnico()">Asignar técnico</a>
          </sec:access>
          <g:if test="${idNivel == yo}">
            <a class="save" data-toggle="modal" href="#responsive"
              onclick="soluciona()">Solucionar Incidente</a>
            <g:if test="${incidenteInstance.nivel < 3}">
              <a class="escala" data-toggle="modal" href="#responsive"
                onclick="escala()">Escalar Incidente</a>
            </g:if>
          </g:if>
          <g:if test="${incidenteInstance.nivel == 3 && idNivel == yo}">
            <a class="cancel" data-toggle="modal" href="#responsive"
              onclick="problema()">Marcarlo como problema</a>
          </g:if>
          <a class="cancel" data-toggle="modal" href="#responsive"
            onclick="cancelar()">Cancelar</a>
        </fieldset>
      </g:form>
    </div>
  </body>
</html>
