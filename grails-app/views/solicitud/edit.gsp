<%@ page import="mx.gob.inr.catservicios.Solicitud" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'solicitud.label', default: 'Solicitud')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
    <style type="text/css">
      ul.ui-autocomplete {
          z-index: 1100;
      }
    </style>
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
      <h1>Descripción del requerimiento</h1>
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
        <fieldset class="form">
          <g:render template="form"/>
        </fieldset>


        <div id="responsive" class="modal hide fade" tabindex="-1"
          data-width="512" style="width:90%; position: fixed; left: 350px;">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"
              aria-hidden="true">×</button>
            <h3 id="titulo">Captura detalle de la solicitud.</h3>
          </div>
          <div class="modal-body">
            <div class="row-fluid">
              <div class="span12">
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


        <!-- Modal para firmar -->
        <div id="myModalFirmar" class="modal hide fade" tabindex="-1"
          data-keyboard="true" role="dialog" aria-labelledby="myModalLabel"
          aria-hidden="true">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            <h3 id="myModalLabel">Firma digital</h3>
          </div>
          <div class="modal-body">
            <fieldset class="form">
              <g:render template="formFirmar"/>
            </fieldset>
          </div>
          <fieldset class="buttons">
            <g:actionSubmit class="save" action="firmarUpdate"
              value="${message(code: 'default.button.update.label',
                               default: 'Update')}" />
          </fieldset>
        </div>


<script>
  function detalleNuevo() {
    $("#idServcat").prop("required", true);
    $("#descripcion").prop("required", true);
    $("#justificacion").prop("required", false);
    $("#idAutoriza").prop("required", false);
    $("#passwordfirma").prop("required", false);
    $("#idDetalle").val(null);
    $("#idServcat").val(null);
    $("#descripcion").text('');
    $("#idResguardoentregadetalle").val(null);
    $("#panelEstado").css("display", "none");
    $("#estado").val(null);
    ponerDetalles("", "", "", "", "", "", "", "", "")
  }
  function ponerDetalles(serie, marca, modelo, economico, equipo, ubicacion, cuerpo, empleado, garantia) {
    $("#serie").val(serie);
    $("#marca").val(marca);
    $("#modelo").val(modelo);
    $("#economico").val(economico);
    $("#equipo").val(equipo);
    $("#ubicacion").val(ubicacion);
    $("#cuerpo").val(cuerpo);
    $("#empleado").val(empleado);
    $("#garantia").val(garantia);
  }
  function detalle(id, serv, descripcion, serie, marca, modelo, economico, equipo, ubicacion, cuerpo, empleado, garantia, estado) {
    $("#idServcat").prop("required", true);
    $("#descripcion").prop("required", true);
    $("#justificacion").prop("required", false);
    $("#idAutoriza").prop("required", false);
    $("#passwordfirma").prop("required", false);
    $("#idDetalle").val(id);
    $("#idServcat").val(serv);
    $("#descripcion").text(descripcion);
    // $("#cpuauto").val(equipo);

    ponerDetalles(serie, marca, modelo, economico, equipo, ubicacion, cuerpo, empleado, garantia)

    $("#panelEstado").css("display", "block");
    $("#estado").val(estado);
  }
  function maestro() {
    $("#idServcat").prop("required", false);
    $("#descripcion").prop("required", false);
    $("#justificacion").prop("required", true);
    $("#idAutoriza").prop("required", true);
    $("#passwordfirma").prop("required", false);
  }
  function firma() {
    $("#idServcat").prop("required", false);
    $("#descripcion").prop("required", false);
    $("#justificacion").prop("required", true);
    $("#idAutoriza").prop("required", true);
    $("#passwordfirma").prop("required", true);
  }
</script>


				<fieldset class="buttons">
          <g:if test="${!solicitudInstance?.estado || solicitudInstance?.estado == 'F' as char}">
					  <g:actionSubmit class="save" action="update" onclick="maestro()"
              value="${message(code: 'default.button.update.label', default: 'Update')}"/>
            <g:if test="${!solicitudInstance?.estado && solicitudInstance?.justificacion && solicitudInstance?.idAutoriza && solicitudInstance?.extension}">
              <a href="#myModalFirmar" class="edit" data-toggle="modal"
                onclick="firma()">Firmar</a>
            </g:if>
          </g:if>
        </fieldset>
      </g:form>
    </div>
    <dev><a href="${resource(dir: 'docs', file: 'SAST-Manual-de-Usuario.docx')}" download>Manual de captura de Requerimientos</a></dev>
  </body>
</html>