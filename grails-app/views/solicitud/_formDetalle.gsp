<g:javascript src="servicios.js" />

<style type="text/css">
  textArea { width: 412px; }
</style>

<div class="row-fluid">
  <div class="span6">
    <div class="fieldtablecontain ${hasErrors(bean: solicitudDetalleInstance, field: 'idServcat', 'error')} " style="width: 95%;">
      <label for="idServcat">
        <g:message code="solicitudDetalle.idServcat.label" default="Categoría" />
      </label>
      <g:select id="idServcat" name="idServcat.id" from="${categorias}"
        optionKey="id" value="${solicitudDetalleInstance?.idServcat?.id}"
        class="many-to-one" noSelection="['': '']"
        style="width: 450px;" />
    </div>
  </div>

  <div class="span6">
    <div class="fieldtablecontain ${hasErrors(bean: solicitudDetalleInstance, field: 'descripcion', 'error')} ">
      <label for="descripcion">
        <g:message code="solicitudDetalle.descripcion.label" default="Descripción" />
      </label>
      <g:textArea id="descripcion" name="descripcion" cols="40" rows="5"
        maxlength="3000" value="${solicitudDetalleInstance?.descripcion}"/>
    </div>
  </div>
</div>

<div class="row-fluid">
  <g:if test="${!(solicitudDetalleInstance?.id)}">
    <div class="span6">
      <div class="fieldtablecontain">
        <label for="resguardo">Inventario o No. de Serie o Usuario</label>
        <g:textField id="cpuauto" name="resguardo" value=""/>
        <g:hiddenField id="idResguardoentregadetalle" name="idResguardoentregadetalle"
           value="${incidenteInstance?.idResguardoentregadetalle}" />
      </div>
    </div>
  </g:if>
</div>

<div style="display:none;">
  <g:select name="estado" id="estado" from="['A','I']"
    valueMessagePrefix="cat_servCat.estado" value='A'/>
</div>

<table class="table table-condensed">
  <g:if test="${!solicitudDetalleInstance?.idResguardoentregadetalle}">
    <tr>
      <td>
          <label for="equipo"><g:message code="servicios.equipo.label" default="Equipo" /></label>
          <g:textField name="equipo" value="${serviciosInstance?.equipo}"
            readonly="true" style="width: 250px;text-transform: uppercase;"/>
      </td>
      <td>
          <label for="marca">
            <g:message code="servicios.marca.label" default="Marca" />
          </label>
          <g:textField name="marca" value="${serviciosInstance?.marca}"
            readonly="true" style="width: 250px;text-transform: uppercase;"/>
      </td>
      <td>
          <label for="modelo">
            <g:message code="servicios.modelo.label" default="Modelo" />
          </label>
          <g:textField name="modelo" value="${serviciosInstance?.modelo}"
            readonly="true" style="width: 250px;text-transform: uppercase;"/>
      </td>
    </tr>
    <tr>
      <td>
          <label for="serie">
            <g:message code="servicios.serie.label" default="Serie" />
          </label>
          <g:textField name="serie" value="${serviciosInstance?.serie}"
            readonly="true" style="width: 250px;text-transform: uppercase;" />
      </td>
      <td>
          <label for="economico">
            <g:message code="servicios.economico.label" default="Inventario" />
          </label>
          <g:textField name="economico" value="${serviciosInstance?.economico}" readonly="true" style="width: 250px;"/>
      </td>
      <td>
      </td>
    </tr>
    <tr>
      <td colspan="2">
        <label for="ubicacion">Ubicación</label>
        <g:textField name="ubicacion" value="${serviciosInstance?.ubicacion}" readonly="true" style="width: 600px;"/>
      </td>
      <td>
        <label for="garantia">Cuerpo : Nivel</label>
        <g:textField name="cuerpo" value="${serviciosInstance?.cuerpo}" readonly="true" style="width: 250px;"/>
      </td>
    </tr>
    <tr>
      <td>
        <label for="empleado">
          <g:message code="servicios.empleado.label" default="Usuario que resguarda el equipo" />
        </label>
        <g:textField name="empleado" value="${serviciosInstance?.empleado}"
          readonly="true" style="width: 250px;text-transform: uppercase;" />
      </td>
      <td>
        <label for="garantia">Garantía</label>
        <g:textField name="garantia" value="${serviciosInstance?.garantia}"
          readonly="true" style="width: 250px;text-transform: uppercase;" />
      </td>
    </tr>
  </g:if>
  <g:else>
    <g:set var="equipo"
      value="${ResguardoEntregaDetalle.get(solicitudDetalleInstance.idResguardoentregadetalle)}" />
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
  </g:else>
</table>
