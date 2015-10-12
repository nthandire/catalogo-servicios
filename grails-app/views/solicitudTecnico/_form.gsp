<%@ page import="mx.gob.inr.catservicios.*" %>


<style type="text/css">
  textArea { width: 412px; }
</style>


<div class="row-fluid">
  <div class="span4 offset1">
    <div class="fieldtablecontain ${hasErrors(bean: solicitudDetalleInstance, field: 'idServcat', 'error')} ">
      <label for="idServcat">
        <g:message code="solicitudDetalle.idServcat.label" default="Categoría" />
      </label>
      <g:field id="idServcat" name="idServcat.id"
        value="${solicitudDetalleInstance?.idServcat}" disabled="true"/>
    </div>
  </div>
  <div class="span6">
    <div class="fieldtablecontain ${hasErrors(bean: solicitudDetalleInstance, field: 'idServ', 'error')} ">
      <label for="idServ">
        <g:message code="solicitudDetalle.idServ.label" default="Tercer nivel" />
      </label>
      <g:field id="idServ" name="idServ.id"
        value="${solicitudDetalleInstance?.idServ}" disabled="true"/>
    </div>
  </div>
</div>

<div class="row-fluid">
  <div class="span4">
    <div class="fieldtablecontain">
      <label for="nombre-label">
        <g:message code="solicitud.nombre.label" default="Solicitante"/>
      </label>
      <g:field type="text" name="nombre.no" disabled="true"
        value="${Usuario.get(solicitudDetalleInstance?.idSolicitud?.idSolicitante)}"/>
    </div>
  </div>
  <div class="span3">
    <div class="fieldtablecontain">
      <label for="telefono-label">
        <g:message code="solicitud.telefono.label" default="Extensión" />
      </label>
      <g:field type="text" name="telefono.no" disabled="true"
        value="${Usuario.get(solicitudDetalleInstance?.idSolicitud?.idSolicitante).extension}"/>
    </div>
  </div>
  <div class="span3">
    <div class="fieldtablecontain">
      <label for="area-label">
        <g:message code="solicitud.area.label" default="Área" />
      </label>
      <g:field type="text" name="area.no" disabled="true"
        value="${UsuarioAutorizado.get(solicitudDetalleInstance?.idSolicitud?.idSolicitante).area}"/>
    </div>
  </div>
</div>

<div class="row-fluid">
  <div class="span4">
    <div class="fieldtablecontain">
      <label for="nombre-label">
        <g:message code="solicitud.nombre.label" default="Autorizador"/>
      </label>
      <g:field type="text" name="nombre.no" disabled="true"
        value="${Usuario.get(solicitudDetalleInstance?.idSolicitud?.idAutoriza)}"/>
    </div>
  </div>
  <div class="span3">
    <div class="fieldtablecontain">
      <label for="telefono-label">
        <g:message code="solicitud.telefono.label" default="Extensión" />
      </label>
      <g:field type="text" name="telefono.no" disabled="true"
        value="${Usuario.get(solicitudDetalleInstance?.idSolicitud?.idAutoriza).extension}"/>
    </div>
  </div>
  <div class="span3">
    <div class="fieldtablecontain">
      <label for="area-label">
        <g:message code="solicitud.area.label" default="Área" />
      </label>
      <g:field type="text" name="area.no" disabled="true"
        value="${UsuarioAutorizado.get(solicitudDetalleInstance?.idSolicitud?.idAutoriza).area}"/>
    </div>
  </div>
</div>

<g:if test="${solicitudDetalleInstance?.idSolicitud?.idVb}">
  <div class="row-fluid">
    <div class="span4">
      <div class="fieldtablecontain">
        <label for="nombre-label">
          <g:message code="solicitud.nombre.label" default="Visto Bueno"/>
        </label>
        <g:field type="text" name="nombre.no" disabled="true"
          value="${Usuario.get(solicitudDetalleInstance?.idSolicitud?.idVb)}"/>
      </div>
    </div>
    <div class="span3">
      <div class="fieldtablecontain">
        <label for="telefono-label">
          <g:message code="solicitud.telefono.label" default="Extensión" />
        </label>
        <g:field type="text" name="telefono.no" disabled="true"
          value="${Usuario.get(solicitudDetalleInstance?.idSolicitud?.idVb).extension}"/>
      </div>
    </div>
    <div class="span3">
      <div class="fieldtablecontain">
        <label for="area-label">
          <g:message code="solicitud.area.label" default="Área" />
        </label>
        <g:field type="text" name="area.no" disabled="true"
          value="${UsuarioAutorizado.get(solicitudDetalleInstance?.idSolicitud?.idVb).area}"/>
      </div>
    </div>
  </div>
</g:if>

<div class="row-fluid">
  <div class="span4">
    <div class="fieldtablecontain ${hasErrors(bean: solicitudDetalleInstance, field: 'descripcion', 'error')} ">
      <label for="descripcion">
        <g:message code="solicitudDetalle.descripcion.label" default="Descripcion" />
      </label>
      <g:textArea name="descripcion" cols="40" rows="5" maxlength="3000" value="${solicitudDetalleInstance?.descripcion}" disabled="true"/>
    </div>
  </div>
  <div class="span3">
    <div class="fieldtablecontain ${hasErrors(bean: solicitudDetalleInstance, field: 'idPrograma', 'error')} ">
      <label for="idPrograma">
        <g:message code="solicitudDetalle.idPrograma.label" default="Estado de cierre" />
      </label>
      <g:field id="idPrograma" name="idPrograma.id" type="text"
        value="${solicitudDetalleInstance?.idPrograma}" disabled="true"/>
    </div>
  </div>

  <div class="span5">
    <g:if test="${equipo}">
      <div class="fieldtablecontain">
        <label for="resguardo">
          <g:message code="solicitudDetalle.idResguardoentregadetalle.label" default="Equipo" />
        </label>
        <g:textArea name="equipo" cols="40" rows="1" maxlength="3000"
          value="${equipo}" disabled="true"/>
      </div>
    </g:if>
  </div>
</div>

<div class="row-fluid">
  <div class="span4">
    <div class="fieldtablecontain ${hasErrors(bean: solicitudDetalleInstance, field: 'descripcionTecnica', 'error')} ">
      <label for="descripcionTecnica">
        <g:message code="solicitudDetalle.descripcionTecnica.label" default="Descripción Técnica" />
      </label>
      <g:textArea name="descripcionTecnica" cols="40" rows="5" maxlength="3000"
        value="${solicitudDetalleInstance?.descripcionTecnica}" disabled="true"/>
    </div>
  </div>
  <div class="span4">
    <div class="fieldtablecontain ${hasErrors(bean: solicitudDetalleInstance, field: 'solucion', 'error')} ">
      <label for="solucion">
        <g:message code="solicitudDetalle.solucion.label" default="Solución" />
      </label>
      <g:textArea name="solucion" cols="40" rows="5" maxlength="3000"
        value="${solicitudDetalleInstance?.solucion}"/>
    </div>
  </div>
  <div class="span4">
    <g:if test="${solicitudDetalleInstance?.prioridad}">
    <div class="fieldtablecontain ${hasErrors(bean: solicitudDetalleInstance, field: 'prioridad', 'error')}">
      <label for="prioridad">
        <g:message code="solicitudDetalle.prioridad.label" default="Prioridad" />
      </label>
      <g:select id="prioridad" name="prioridad" disabled="false" from="${[0, 1, 2, 3]}"
        valueMessagePrefix="intensidad.valor"
        value="${solicitudDetalleInstance.prioridad}" disabled="true"/>
    </div>
    </g:if>
  </div>
</div>
