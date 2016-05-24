<%@ page import="mx.gob.inr.catservicios.*" %>


<style type="text/css">
  textArea { width: 412px; }
</style>


<g:set var="firmado" bean="firmadoService"/>
<g:set var="solicitudInstance" value="${solicitudDetalleInstance.idSolicitud}" />
<div class="row-fluid">
  <div class="span4">
    <div class="fieldtablecontain ${hasErrors(bean: solicitudDetalleInstance,
                                              field: 'idServcat', 'error')} ">
      <label for="idServcat">
        <g:message code="solicitudDetalle.idServcat.label" default="Categoría" />
      </label>
      <g:select id="servCat" name="servCat.id" required="true"
        from="${firmado.categoriasSolicitudes()}"
        optionKey="id" class="many-to-one"
        value="${solicitudDetalleInstance.idServcat}"
        onchange="categoryChanged(this.value)"/>
    </div>
  </div>
  <div class="span4">
    <div class="fieldtablecontain ${hasErrors(bean: solicitudDetalleInstance,
                                          field: 'idServ', 'error')} required">
      <label for="idServ">
        <g:message code="cat_serv.servSub.label" default="Serv Sub" />
        <span class="required-indicator">*</span>
      </label>
      <span id="subContainer">
        <g:select id='servSub' name='servSub' required=''
          onchange="subcategoryChanged(this.value)"
          from="${firmado.subcategoriasSolicitudes(solicitudDetalleInstance.idServcat)}"
          value="${solicitudDetalleInstance?.idServ?.servSub?.id}"
          optionKey='id' noSelection="['':'Seleccione una...']"/>
      </span>
    </div>
  </div>
  <div class="span4">
    <div class="fieldtablecontain ${hasErrors(bean: solicitudDetalleInstance,
                                         field: 'idServ', 'error')} required">
      <label for="idServ">
        <g:message code="cat_bitacora.servicio.label" default="Categoría de tercer nivel" />
        <span class="required-indicator">*</span>
      </label>
      <span id="serviciosContainer">
        <g:if test="${solicitudDetalleInstance?.idServ}">
          <g:select id='idServ' name='idServ.id' required=''
            from="${firmado.tercerNivelSolicitudes(solicitudDetalleInstance.idServ.servSub)}"
            value="${solicitudDetalleInstance?.idServ?.id}"
            onchange="servicesChanged(this.value)"
            optionKey='id' noSelection="['':'Seleccione una...']"/>
        </g:if>
      </span>
    </div>
  </div>
</div>

<script>
    function categoryChanged(categoryId) {
      <g:remoteFunction action="categoryChanged"
          update="subContainer"
          params="'categoryId='+categoryId"/>
      <g:remoteFunction action="categoryChanged2"
          update="serviciosContainer"
          params="'categoryId='+categoryId"/>
    }
    function subcategoryChanged(subcategoryId) {
      <g:remoteFunction action="subcategoryChanged"
          update="serviciosContainer"
          params="'subcategoryId='+subcategoryId"/>
    }
    function servicesChanged(servicesId) {
      <g:remoteFunction action="servicesChanged"
          update="prioridadContainer"
          params="'servicesId='+servicesId"/>
    }
</script>

<div class="row-fluid">
  <div class="span4">
    <div class="fieldtablecontain">
      <label for="nombre-label">
        <g:message code="solicitud.nombre.label" default="Solicitante"/>
      </label>
      <g:field type="text" name="nombre.no" disabled="true" style="width:400px"
        value="${Usuario.get(solicitudInstance.idSolicitante)}"/>
    </div>
  </div>
  <div class="span3">
    <div class="fieldtablecontain">
      <label for="telefono-label">
        Fecha de solicitud
      </label>
      <g:field type="datetime" disabled="true"
        name="solicitudInstance.fechaSolicitud"
        value="$solicitudInstance.fechaSolicitud"/>
    </div>
  </div>
  <div class="span2">
    <div class="fieldtablecontain">
      <label for="telefono-label">
        <g:message code="solicitud.telefono.label" default="Extensión" />
      </label>
      <g:field type="text" name="telefono.no" disabled="true"
        value="${Usuario.get(solicitudInstance.idSolicitante).extension}"/>
    </div>
  </div>
  <div class="span3">
    <div class="fieldtablecontain">
      <label for="area-label">
        <g:message code="solicitud.area.label" default="Área" />
      </label>
      <g:field type="text" name="area.no" disabled="true" style="width:280px;"
        value="${firmado.areaDetalladaNombre(solicitudInstance.idSolicitante)}"/>
    </div>
  </div>
</div>

<div class="row-fluid">
  <div class="span4">
    <div class="fieldtablecontain">
      <label for="nombre-label">
        <g:message code="solicitud.nombre.label" default="Autorizador"/>
      </label>
      <g:field type="text" name="nombre.no" disabled="true" style="width:400px"
        value="${Usuario.get(solicitudInstance.idAutoriza)}"/>
    </div>
  </div>
  <div class="span3">
    <div class="fieldtablecontain">
      <label for="fAutoriza-label">
        Fecha de autorización
      </label>
      <g:field type="datetime" name="fAutoriza.no" disabled="true"
        value="${solicitudInstance.fechaAutoriza}"/>
    </div>
  </div>
  <div class="span2">
    <div class="fieldtablecontain">
      <label for="telefono-label">
        <g:message code="solicitud.telefono.label" default="Extensión" />
      </label>
      <g:field type="text" name="telefono.no" disabled="true"
        value="${Usuario.get(solicitudInstance.idAutoriza).extension}"/>
    </div>
  </div>
  <div class="span3">
    <div class="fieldtablecontain">
      <label for="area-label">
        <g:message code="solicitud.area.label" default="Área" />
      </label>
      <g:field type="text" name="area.no" disabled="true" style="width:280px;"
        value="${firmado.areaDetalladaNombre(solicitudInstance?.idAutoriza)}"/>
    </div>
  </div>
</div>

<g:if test="${solicitudInstance.idVb}">
  <div class="row-fluid">
    <div class="span4">
      <div class="fieldtablecontain">
        <label for="nombre-label">
          <g:message code="solicitud.nombre.label" default="Visto Bueno"/>
        </label>
        <g:field type="text" name="nombre.no" disabled="true" style="width:400px"
          value="${Usuario.get(solicitudInstance.idVb)}"/>
      </div>
    </div>
    <div class="span3">
      <div class="fieldtablecontain">
        <label for="fVoBo-label">
          Fecha de Visto Bueno
        </label>
        <g:field type="datetime" name="fVoBo.no" disabled="true"
          value="${solicitudInstance.fechaVb}"/>
      </div>
    </div>
    <div class="span2">
      <div class="fieldtablecontain">
        <label for="telefono-label">
          <g:message code="solicitud.telefono.label" default="Extensión" />
        </label>
        <g:field type="text" name="telefono.no" disabled="true"
          value="${Usuario.get(solicitudInstance.idVb).extension}"/>
      </div>
    </div>
    <div class="span3">
      <div class="fieldtablecontain">
        <label for="area-label">
          <g:message code="solicitud.area.label" default="Área" />
        </label>
        <g:field type="text" name="area.no" disabled="true" style="width:280px;"
          value="${firmado.areaDetalladaNombre(solicitudInstance?.idVb)}"/>
      </div>
    </div>
  </div>
</g:if>

<div class="row-fluid">
  <div class="span4">
    <div class="fieldtablecontain ${hasErrors(bean: solicitudDetalleInstance, field: 'descripcion', 'error')} ">
      <label for="descripción">
        <g:message code="solicitudDetalle.descripcion.label" default="Descripcion" />
      </label>
      <g:textArea name="descripcion" cols="40" rows="5" maxlength="3000"
      value="${solicitudDetalleInstance?.descripcion}" disabled="true"/>
    </div>
  </div>
  <div class="span4">
    <div class="fieldtablecontain ${hasErrors(bean: solicitudDetalleInstance,
                                              field: 'idPrograma', 'error')} ">
      <label for="idPrograma">
        <g:message code="solicitudDetalle.idPrograma.label" default="Estado de cierre" />
      </label>
      <g:field id="idPrograma" name="idPrograma.id" type="text"
        value="${solicitudDetalleInstance?.idPrograma}" disabled="true"/>
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
    <div class="fieldtablecontain ${hasErrors(bean: solicitudDetalleInstance, field: 'descripcionTecnica', 'error')} ">
      <label for="descripcionTecnica">
        <g:message code="solicitudDetalle.descripcionTecnica.label" default="Descripción Técnica" />
      </label>
      <g:textArea name="descripcionTecnica" cols="40" rows="5" maxlength="3000"
        value="${solicitudDetalleInstance?.descripcionTecnica}"/>
    </div>
  </div>
  <div class="span4">
    <g:if test="${solicitudDetalleInstance?.idTecnico}">
      <div class="fieldtablecontain ${hasErrors(bean: solicitudDetalleInstance,
                                                field: 'idTecnico', 'error')} ">
        <label for="idTecnico">
          <g:message code="solicitudDetalle.idTecnico.label" default="Tecnico" />
        </label>
        <g:field  id="idTecnico" name="idTecnico" disabled="true"
          value="${firmado.usuarioNombre(solicitudDetalleInstance?.idTecnico)}"/>
      </div>
    </g:if>
  </div>
  <div class="span4">
    <div class="fieldtablecontain ${hasErrors(bean: solicitudDetalleInstance, field: 'prioridad', 'error')}">
      <label for="prioridad">
        <g:message code="solicitudDetalle.prioridad.label" default="Prioridad" />
      </label>
      <span id="prioridadContainer">
        <g:select id="prioridad" name="prioridad" from="${[0, 1, 2, 3]}"
          valueMessagePrefix="intensidad.valor"
          value="${solicitudDetalleInstance.prioridad?:solicitudDetalleInstance?.idServ?.impacto?:0}"/>
      </span>
    </div>
  </div>
</div>
