<%@ page import="mx.gob.inr.catservicios.*" %>


<style type="text/css">
  textArea { width: 412px; }
</style>


<g:set var="firmado" bean="firmadoService"/>
<div class="row-fluid">
  <div class="span4">
    <div class="fieldtablecontain ${hasErrors(bean: solicitudDetalleInstance, field: 'idServcat', 'error')} ">
      <label for="idServcat">
        <g:message code="solicitudDetalle.idServcat.label" default="Categoría" />
      </label>
      <g:field type="text" id="idServcat" name="idServcat.id"
        value="${solicitudDetalleInstance?.idServcat}" disabled="true"/>
    </div>
  </div>
  <div class="span4">
    <div class="fieldtablecontain ${hasErrors(bean: solicitudDetalleInstance, field: 'idServ', 'error')} ">
      <label for="idServsub">
        <g:message code="solicitudDetalle.idServsub.label" default="Subcategoría" />
      </label>
      <g:field type="text" id="idServsub" name="idServsub.id"
        value="${solicitudDetalleInstance?.idServ?.servSub}" disabled="true"/>
    </div>
  </div>
  <div class="span4">
    <div class="fieldtablecontain ${hasErrors(bean: solicitudDetalleInstance, field: 'idServ', 'error')} ">
      <label for="idServ">
        <g:message code="solicitudDetalle.idServ.label" default="Tercer nivel" />
      </label>
      <g:field type="text" id="idServ" name="idServ.id"
        value="${solicitudDetalleInstance?.idServ}" disabled="true"/>
    </div>
  </div>
</div>

<div class="row-fluid">
  <div class="span4">
    <div class="fieldtablecontain" style="widht:100%">
      <label for="nombre-label">
        <g:message code="solicitud.nombre.label" default="Solicitante"/>
      </label>
      <g:field type="text" name="nombre.no" disabled="true" style="width:350px;"
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
    <div class="fieldtablecontain" style="width:100%;">
      <label for="area-label">
        <g:message code="solicitud.area.label" default="Área" />
      </label>
      <g:field type="text" name="area.no" disabled="true" style="width:280px;"
        value="${firmado.areaDetalladaNombre(solicitudDetalleInstance?.idSolicitud?.idSolicitante)}"/>
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
    <div class="fieldtablecontain" style="widht:100%">
      <label for="nombre-label">
        <g:message code="solicitud.nombre.label" default="Autorizador"/>
      </label>
      <g:field type="text" name="nombre.no" disabled="true" style="width:400px"
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
    <div class="fieldtablecontain" style="width:100%;">
      <label for="area-label">
        <g:message code="solicitud.area.label" default="Área" />
      </label>
      <g:field type="text" name="area.no" disabled="true" style="width:280px;"
        value="${firmado.areaDetalladaNombre(solicitudDetalleInstance?.idSolicitud?.idAutoriza)}"/>
    </div>
  </div>
</div>

<g:if test="${solicitudDetalleInstance?.idSolicitud?.idVb}">
  <div class="row-fluid">
    <div class="span4">
      <div class="fieldtablecontain" style="widht:100%">
        <label for="nombre-label">
          <g:message code="solicitud.nombre.label" default="Visto Bueno"/>
        </label>
        <g:field type="text" name="nombre.no" disabled="true" style="width:400px"
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
      <div class="fieldtablecontain" style="width:100%;">
        <label for="area-label">
          <g:message code="solicitud.area.label" default="Área" />
        </label>
        <g:field type="text" name="area.no" disabled="true" style="width:280px;"
          value="${firmado.areaDetalladaNombre(solicitudDetalleInstance?.idSolicitud?.idVb)}"/>
      </div>
    </div>
  </div>
</g:if>

<div class="row-fluid">
  <div class="span4 offset2">
    <div class="fieldtablecontain">
      Fecha inicio Atención
    </div>
  </div>
  <div class="span4">
    <div class="fieldtablecontain">
      Tiempo de atención
    </div>
  </div>
</div>
<div class="row-fluid">
  <div class="span4 offset2">
    <div class="fieldtablecontain">
      <g:field name="tiempoInicio" type="text" disabled="true"
        value="${(solicitudDetalleInstance.idSolicitud.fechaVb?:
        solicitudDetalleInstance.idSolicitud.fechaAutoriza).format("dd/MM/yyyy HH:mm")}" />
    </div>
  </div>
  <div class="span4">
    <div class="fieldtablecontain">
    <g:field name="tiempo" type="text" disabled="true"
      value="${solicitudDetalleInstance?.idServ?.tiempo2} ${solicitudDetalleInstance?.idServ?.unidades2?.descripcion}" />
    </div>
  </div>
</div>

<div class="row-fluid">
  <div class="span4">
    <div class="fieldtablecontain ${hasErrors(bean: solicitudDetalleInstance, field: 'descripcion', 'error')} ">
      <label for="descripcion">
        <g:message code="solicitudDetalle.descripcion.label" default="Descripcion" />
      </label>
      <g:textArea name="descripcion" cols="40" rows="5" maxlength="3000" value="${solicitudDetalleInstance?.descripcion}" disabled="true"/>
    </div>
  </div>

  <div class="span4 offset2">
    <div class="fieldtablecontain ${hasErrors(bean: solicitudDetalleInstance, field: 'descripcionTecnica', 'error')} ">
      <label for="descripcionTecnica">
        <g:message code="solicitudDetalle.descripcionTecnica.label" default="Descripción Técnica" />
      </label>
      <g:textArea name="descripcionTecnica" cols="40" rows="5" maxlength="3000"
        value="${solicitudDetalleInstance?.descripcionTecnica}" disabled="true"/>
    </div>
  </div>
</div>

<div class="row-fluid">
  <div class="span4">
    <div class="fieldtablecontain ${hasErrors(bean: solicitudDetalleInstance, field: 'idPrograma', 'error')} ">
      <label for="idPrograma">
        <g:message code="solicitudDetalle.idPrograma.label" default="Estado de cierre" />
      </label>
      <g:select id="idPrograma" name="idPrograma.id" from="${programas}"
         optionKey="id" value="${solicitudDetalleInstance?.idPrograma?.id}"
         class="many-to-one" noSelection="['': '']" required="true" />
    </div>
  </div>

</div>


<table class="table table-condensed">
  <g:if test="${solicitudDetalleInstance?.idResguardoentregadetalle}">
    <g:set var="equipo"
      value="${ResguardoEntregaDetalle.get(solicitudDetalleInstance.idResguardoentregadetalle)}" />
    <g:set var="servicios" bean="serviciosService"/>
    <tr>
      <td>
          <label for="equipo"><g:message code="servicios.equipo.label" default="Equipo" /></label>
          <g:textField name="equipo" value="${AnexoTecnico.get(equipo.idTipoanexotecnico)}"
            readonly="true" style="width: 250px;text-transform: uppercase;"/>
      </td>
      <td>
          <label for="marca">
            <g:message code="servicios.marca.label" default="Marca" />
          </label>
          <g:textField name="marca" value="${servicios.descMarca(equipo?.idMarca)}"
            readonly="true" style="width: 250px;text-transform: uppercase;"/>
      </td>
      <td>
          <label for="modelo">
            <g:message code="servicios.modelo.label" default="Modelo" />
          </label>
          <g:textField name="modelo" value="${equipo?.desModelo}"
            readonly="true" style="width: 250px;text-transform: uppercase;"/>
      </td>
    </tr>
    <tr>
      <td>
          <label for="serie">
            <g:message code="servicios.serie.label" default="Serie" />
          </label>
          <g:textField name="serie" value="${equipo?.serie}"
            readonly="true" style="width: 250px;text-transform: uppercase;" />
      </td>
      <td>
          <label for="economico">
            <g:message code="servicios.economico.label" default="Inventario" />
          </label>
          <g:textField name="economico" value="${equipo?.inventario}" readonly="true" style="width: 250px;"/>
      </td>
      <td>
      </td>
    </tr>
    <g:if test="${firmado.ubicacion(solicitudDetalleInstance?.idResguardoentregadetalle) || firmado.cuerpoNivel(solicitudDetalleInstance?.idResguardoentregadetalle)}">
      <tr>
        <td colspan="2">
          <label for="ubicacion">Ubicación</label>
          <g:field type="text" name="ubicacion" disabled="true" style="width:600px"
            value="${firmado.ubicacion(solicitudDetalleInstance?.idResguardoentregadetalle)}"/>
        </td>
        <td>
          <label for="garantia">Cuerpo : Nivel</label>
          <g:field type="text" name="cuerpo" disabled="true" style="width:400px"
            value="${firmado.cuerpoNivel(solicitudDetalleInstance?.idResguardoentregadetalle)}"/>
        </td>
      </tr>
    </g:if>
    <tr>
      <td>
        <label for="empleado">
          <g:message code="servicios.empleado.label" default="Usuario que resguarda el equipo" />
        </label>
        <g:textField name="empleado" value="${firmado.nombreEmpleado(equipo?.idEmpleado)}"
          readonly="true" style="width: 250px;text-transform: uppercase;" />
      </td>
      <td>
        <label for="garantia">Garantía</label>
        <g:textField name="garantia" value="${servicios.garantia(equipo)}"
          readonly="true" style="width: 250px;text-transform: uppercase;" />
      </td>
    </tr>
  </g:if>
</table>


<div class="row-fluid">
  <div class="span4">
    <div class="fieldtablecontain ${hasErrors(bean: solicitudDetalleInstance, field: 'solucion', 'error')} ">
      <label for="solucion">
        <g:message code="solicitudDetalle.solucion.label" default="Solución" />
      </label>
      <g:textArea name="solucion" cols="40" rows="5" maxlength="3000"
        value="${solicitudDetalleInstance?.solucion}"/>
    </div>
  </div>
  <div class="span4 offset2">
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
