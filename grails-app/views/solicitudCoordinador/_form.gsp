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

<g:set var="firmado" bean="firmadoService"/>
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
        value="${firmado.areaNombre(solicitudDetalleInstance?.idSolicitud?.idSolicitante)}"/>
    </div>
  </div>
</div>

<g:if test="${(solicitudDetalleInstance?.idResguardoentregadetalle)}">
  <div class="row-fluid">
    <div class="span7">
      <div class="fieldtablecontain">
        <label for="ubicacion-label">
          <g:message code="solicitud.ubicacion.label" default="Ubicación"/>
        </label>
        <g:field type="text" name="ubicacion" disabled="true" style="width:600px"
          value="${firmado.ubicacion(solicitudDetalleInstance?.idResguardoentregadetalle)}"/>
      </div>
    </div>

    <div class="span5">
      <div class="fieldtablecontain">
        <label for="cuerpo-label">
          <g:message code="solicitud.cuerpo.label" default="Cuerpo : Nivel"/>
        </label>
        <g:field type="text" name="cuerpo" disabled="true" style="width:400px"
          value="${firmado.cuerpoNivel(solicitudDetalleInstance?.idResguardoentregadetalle)}"/>
      </div>
    </div>
  </div>
</g:if>

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
        value="${firmado.areaNombre(solicitudDetalleInstance?.idSolicitud?.idAutoriza)}"/>
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
          value="${firmado.areaNombre(solicitudDetalleInstance?.idSolicitud?.idVb)}"/>
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

  <div class="span4 offset4">
    <g:if test="${solicitudDetalleInstance?.idResguardoentregadetalle}">
    <div class="fieldtablecontain">
      <label for="resguardo">
        <g:message code="solicitudDetalle.idResguardoentregadetalle.label" default="Equipo" />
      </label>
      <g:select id="idResguardoentregadetalle" name="idResguardoentregadetalle"
        from="${ResguardoEntregaDetalle.executeQuery('from ResguardoEntregaDetalle d where exists( from ResguardoEntrega r where r.id = d.idResguardo and r.codigo like ?)', "515%")}"
          optionKey="id" value="${solicitudDetalleInstance?.idResguardoentregadetalle}"
          class="many-to-one" disabled="true"/>
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
    <div class="fieldtablecontain ${hasErrors(bean: solicitudDetalleInstance, field: 'idTecnico', 'error')} ">
      <label for="idTecnico">
        <g:message code="solicitudDetalle.idTecnico.label" default="Tecnico" />
      </label>
      <g:select id="idTecnico" name="idTecnico" required="true"
        from="${tecnicos}"
          optionKey="id" value="${solicitudDetalleInstance?.idTecnico}" class="many-to-one"
          noSelection="['': '']"/>
    </div>
  </div>
  <div class="span4">
    <div class="fieldtablecontain ${hasErrors(bean: solicitudDetalleInstance, field: 'prioridad', 'error')}">
      <label for="prioridad">
        <g:message code="solicitudDetalle.prioridad.label" default="Prioridad" />
      </label>
      <g:field id="prioridad" name="prioridad" disabled="true"
        value="${message(code: "intensidad.valor." +
          solicitudDetalleInstance.prioridad?:solicitudDetalleInstance.idServ.impacto)}"/>
    </div>
  </div>
</div>
