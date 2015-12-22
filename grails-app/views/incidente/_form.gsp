<%@ page import="mx.gob.inr.catservicios.*" %>

<g:javascript src="servicios.js" />

<style type="text/css">
  textArea { width: 712px; }
</style>


<g:set var="firmado" bean="firmadoService"/>
<g:set var="servicios" bean="serviciosService"/>
<table class="table table-condensed">
  <g:if test="${!incidenteInstance?.id}">
    <tr>
      <td>
        <label for="cpuauto">Inventario o No. de Serie o Usuario</label>
        <g:textField id="cpuauto" name="idResguardoentregadetalle" style="width: 250px;" />
      </td>
      <td>
        <div id="area" style="visibility: hidden">
          <label for="nombreArea">Area</label>
          <g:textField name="nombreArea" style="width: 250px;text-transform: uppercase;" />
        </div>
      </td>
    </tr>
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
      <td>
        <label for="empleado">
          <g:message code="servicios.empleado.label" default="Usuario que resguarda el equipo" />
        </label>
        <g:textField name="empleado" value="${serviciosInstance?.empleado}"
          readonly="true" style="width: 250px;text-transform: uppercase;" />
      </td>
      %{--
      <td>
          <label for="area">
            <g:message code="servicios.economico.label" default="Área" />
          </label>
          <g:textField name="economico" value="${serviciosInstance?.economico}" readonly="true" style="width: 250px;"/>
      </td>
      --}%
      <td>
      </td>
    </tr>
  </g:if>
  <g:elseif test="${incidenteInstance.idResguardoentregadetalle}">
    <g:set var="equipo"
      value="${ResguardoEntregaDetalle.get(incidenteInstance.idResguardoentregadetalle)}" />
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
    <tr>
      <td>
        <label for="empleado">
          <g:message code="servicios.empleado.label" default="Usuario que resguarda el equipo" />
        </label>
        <g:textField name="empleado" value="${firmado.nombreEmpleado(equipo?.idEmpleado)}"
          readonly="true" style="width: 250px;text-transform: uppercase;" />
      </td>
      %{--
      <td>
          <label for="area">
            <g:message code="servicios.economico.label" default="Área" />
          </label>
          <g:textField name="economico" value="${serviciosInstance?.economico}" readonly="true" style="width: 250px;"/>
      </td>
      --}%
      <td>
      </td>
    </tr>
  </g:elseif>
</table>

<g:if test="${(incidenteInstance?.idResguardoentregadetalle)}">
  <div class="row-fluid">
    <div class="span7">
      <div class="fieldtablecontain">
        <label for="ubicacion-label">
          <g:message code="solicitud.ubicacion.label" default="Ubicación"/>
        </label>
        <g:field type="text" name="ubicacion" disabled="true" style="width:600px"
          value="${firmado.ubicacion(incidenteInstance?.idResguardoentregadetalle)}"/>
      </div>
    </div>

    <div class="span5">
      <div class="fieldtablecontain">
        <label for="cuerpo-label">
          <g:message code="solicitud.cuerpo.label" default="Cuerpo : Nivel"/>
        </label>
        <g:field type="text" name="cuerpo" disabled="true" style="width:400px"
          value="${firmado.cuerpoNivel(incidenteInstance?.idResguardoentregadetalle)}"/>
      </div>
    </div>
  </div>
</g:if>

<g:if test="${!incidenteInstance.id}">
  <div class="row-fluid">
    <div class="span4">
      <div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'idReporta', 'error')} ">
        <label for="idReporta">
          <g:message code="incidente.idReporta.label" default="Quien Reporta" />
        </label>
          <%-- TODO: mejorar el select, solo los usuarios SAST --%>
          <g:select id="idReporta" name="idReporta"
            from="${Usuario.findAllEnabled().sort { it?.nombreMostrar }}"
            required="" value="${incidenteInstance?.idReporta}" class="many-to-one"
            noSelection="${['':'Seleccione una...']}" optionKey="id"
            optionValue="nombreMostrar"/>
      </div>
    </div>
  </div>
</g:if>

<g:if test="${incidenteInstance.id && incidenteInstance.idReporta}">
  <div class="row-fluid">
    <div class="span4">
      <div class="fieldtablecontain">
        <label for="nombre-label">
          <g:message code="incidente.idReporta.label" default="Quien Reporta" />
        </label>
        <g:field type="text" name="nombre.no" disabled="true"
          value="${Usuario.get(incidenteInstance.idReporta)}"/>
      </div>
    </div>
    <div class="span4">
      <div class="fieldtablecontain">
        <label for="telefono-label">
          <g:message code="solicitud.telefono.label" default="Extensión" />
        </label>
        <g:field type="text" name="telefono.no" disabled="true"
          value="${Usuario.get(incidenteInstance.idReporta).extension}"/>
      </div>
    </div>
    <div class="span4">
      <div class="fieldtablecontain">
        <label for="area-label">
          <g:message code="solicitud.area.label" default="Área" />
        </label>
        <g:field type="text" name="area.no" disabled="true"
          value="${areaReporta}"/>
      </div>
    </div>
  </div>
</g:if>

<g:if test="${incidenteInstance.fechaSolnivel1}">
  <div class="row-fluid">
    <div class="span4">
      <div class="fieldtablecontain">
        <label for="nombre-label">
          <g:message code="incidente.idNivel1.label" default="Quien Atendió nivel 1" />
        </label>
        <g:field type="text" name="nombre.no" disabled="true"
          value="${Usuario.get(incidenteInstance.idNivel1)}"/>
      </div>
    </div>
    <div class="span4">
      <div class="fieldtablecontain">
        <label for="telefono-label">
          <g:message code="solicitud.telefono.label" default="Extensión" />
        </label>
        <g:field type="text" name="telefono.no" disabled="true"
          value="${Usuario.get(incidenteInstance.idNivel1).extension}"/>
      </div>
    </div>
    <div class="span4">
      <div class="fieldtablecontain">
        <label for="area-label">
          <g:message code="solicitud.area.label" default="Área" />
        </label>
        <g:field type="text" name="area.no" disabled="true"
          value="${areaAtendio1}"/>
      </div>
    </div>
  </div>
</g:if>

<g:if test="${incidenteInstance.fechaSolnivel2}">
  <div class="row-fluid">
    <div class="span4">
      <div class="fieldtablecontain">
        <label for="nombre-label">
          <g:message code="incidente.idNivel2.label" default="Quien Atendió nivel 2" />
        </label>
        <g:field type="text" name="nombre.no" disabled="true"
          value="${Usuario.get(incidenteInstance.idNivel2)}"/>
      </div>
    </div>
    <div class="span4">
      <div class="fieldtablecontain">
        <label for="telefono-label">
          <g:message code="solicitud.telefono.label" default="Extensión" />
        </label>
        <g:field type="text" name="telefono.no" disabled="true"
          value="${Usuario.get(incidenteInstance.idNivel2).extension}"/>
      </div>
    </div>
    <div class="span4">
      <div class="fieldtablecontain">
        <label for="area-label">
          <g:message code="solicitud.area.label" default="Área" />
        </label>
        <g:field type="text" name="area.no" disabled="true"
          value="${areaAtendio2}"/>
      </div>
    </div>
  </div>
</g:if>

<g:if test="${incidenteInstance.fechaSolnivel3}">
  <div class="row-fluid">
    <div class="span4">
      <div class="fieldtablecontain">
        <label for="nombre-label">
          <g:message code="incidente.idNivel3.label" default="Quien Atendió nivel 3" />
        </label>
        <g:field type="text" name="nombre.no" disabled="true"
          value="${Usuario.get(incidenteInstance.idNivel3)}"/>
      </div>
    </div>
    <div class="span4">
      <div class="fieldtablecontain">
        <label for="telefono-label">
          <g:message code="solicitud.telefono.label" default="Extensión" />
        </label>
        <g:field type="text" name="telefono.no" disabled="true"
          value="${Usuario.get(incidenteInstance.idNivel3).extension}"/>
      </div>
    </div>
    <div class="span4">
      <div class="fieldtablecontain">
        <label for="area-label">
          <g:message code="solicitud.area.label" default="Área" />
        </label>
        <g:field type="text" name="area.no" disabled="true"
          value="${areaAtendio3}"/>
      </div>
    </div>
  </div>
</g:if>

<div class="row-fluid">
  <div class="span4">
    <div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'idServ', 'error')} required">
      <label for="idServ">
        <g:message code="cat_serv.servCat.label" default="Cat" />
        <span class="required-indicator">*</span>
      </label>
      <g:if test="${incidenteInstance?.id && incidenteInstance.idServ}">
        <g:field id="servCat" name="idServ.servSub.servCat.id"
          value="${incidenteInstance?.idServ?.servSub?.servCat}"
          disabled="true"/>
      </g:if>
      <g:else>
        <g:select id="servCat" name="idServ.servSub.servCat.id"
          from="${firmado.categoriasIncidentes()}" optionKey="id" required=""
          value="${incidenteInstance?.idServ?.servSub?.servCat?.id}"
          class="many-to-one" onchange="categoryChanged(this.value)"
          noSelection="${['':'Seleccione una...']}"/>
      </g:else>
    </div>
  </div>

  <div class="span4">
    <div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'idServ', 'error')} required">
      <label for="idServ">
        <g:message code="cat_serv.servSub.label" default="Serv Sub" />
        <span class="required-indicator">*</span>
      </label>
      <span id="subContainer">
        <g:if test="${incidenteInstance.id && incidenteInstance.idServ}">
          <g:field id="servSub" name="servSub.id"
            value="${incidenteInstance?.idServ?.servSub}"
            disabled="true"/>
        </g:if>
        <g:elseif test="${incidenteInstance.idServ}">
          <g:select id='servSub' name='servSub.id' required=''
            onchange='subcategoryChanged(this.value)' optionKey='id'
            from="${firmado.subcategoriasIncidentes(incidenteInstance.idServ.servSub.servCat)}"
            value="${incidenteInstance?.idServ?.servSub?.id}" noSelection="['':' ']"/>
        </g:elseif>
        <g:else>
          <g:select id='servSub' name='servSub.id' required=''
            onchange='subcategoryChanged(this.value)' optionKey='id'
            from="${[]}"
            value="${incidenteInstance?.idServ?.servSub?.id}" noSelection="['':' ']"/>
        </g:else>
      </span>
    </div>
  </div>

  <div class="span4">
    <div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'idServ', 'error')} required">
      <label for="idServ">
        <g:message code="cat_serv.label" default="Servicio" />
        <span class="required-indicator">*</span>
      </label>
      <span id="serviciosContainer">
        <g:if test="${incidenteInstance.id && incidenteInstance.idServ}">
          <g:field id="idServ" name="idServ.id"
            value="${incidenteInstance?.idServ}"
            disabled="true"/>
        </g:if>
        <g:elseif test="${incidenteInstance.idServ}">
          <g:select id='idServ' name='idServ.id' required=''
            optionKey='id' value="${incidenteInstance?.idServ?.id}"
            from="${firmado.tercerNivelIncidentes(incidenteInstance.idServ.servSub)}"
            noSelection="['':' ']"/>
        </g:elseif>
        <g:else>
          <g:select id='idServ' name='idServ.id' required=''
            optionKey='id' value="${incidenteInstance?.idServ?.id}"
            from="${[]}"
            noSelection="['':' ']"/>
        </g:else>
      </span>
    </div>
  </div>
</div>

<script>
    function categoryChanged(categoryId) {
        <g:remoteFunction controller="incidente" action="categoryChanged"
            update="subContainer"
            params="'categoryId='+categoryId"/>
    }
    function subcategoryChanged(subcategoryId) {
        <g:remoteFunction controller="incidente" action="subcategoryChanged"
            update="serviciosContainer"
            params="'subcategoryId='+subcategoryId"/>
    }
</script>

<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="incidente.descripcion.label" default="Descripcion" />
	</label>
  <g:if test="${incidenteInstance.id && incidenteInstance?.descripcion}">
    <g:textArea name="descripcion" cols="40" rows="5" maxlength="3000"
      value="${incidenteInstance?.descripcion}" disabled="true"/>
  </g:if>
  <g:else>
    <g:textArea name="descripcion" cols="40" rows="5" maxlength="3000"
      value="${incidenteInstance?.descripcion}" required="true"/>
  </g:else>
</div>

%{-- Acciones anteriores --}%
<g:set var="nivel" value="${incidenteInstance.nivel}" />
<g:if test="${nivel > 1}">
  <div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'descripcion', 'error')} ">
    <label for="descripcion">Acciones en nivel 1</label>
    <g:textArea name="acciones1" cols="40" rows="5" maxlength="3000"
      value="${incidenteInstance?.solucionNivel1}" disabled="true"/>
  </div>
</g:if>

<g:if test="${nivel > 2}">
  <div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'descripcion', 'error')} ">
    <label for="descripcion">Acciones en nivel 2</label>
    <g:textArea name="acciones2" cols="40" rows="5" maxlength="3000"
      value="${incidenteInstance?.solucionNivel2}" disabled="true"/>
  </div>
</g:if>



<div class="row-fluid">
  <div class="span3">
    <g:if test="${incidenteInstance.id}">
      <div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'nivel', 'error')} ">
        <label for="nivel">
          <g:message code="incidente.nivel.label" default="Nivel" />
        </label>
        <g:field name="nivel" type="number" min="1" max="3"
          value="${incidenteInstance.nivel}" disabled="true"/>
      </div>
    </g:if>
  </div>

  <div class="span3">
    <g:if test="${incidenteInstance.id || incidenteInstance.idServresp}">
      <div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'idServresp', 'error')} ">
        <label for="idServresp">
          <g:message code="incidente.idServresp.label" default="Responsable" />
        </label>
        <g:select id="idServresp" name="idServresp.id" from="${Cat_servResp.list()}"
          optionKey="id" value="${incidenteInstance?.idServresp?.id}" class="many-to-one"
          noSelection="['': '']" disabled="true"/>
      </div>
    </g:if>
  </div>

  <div class="span3">
    <g:if test="${tecnicos}">
      <div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'idNivel', 'error')} ">
        <label for="idNivel">
          <g:message code="incidente.idNivel.label" default="Atendido por ..." />
        </label>
        <g:field id="idNivel-show" name="idNivel-show"
            value="${Usuario.get(idNivel)}" disabled="true"/>
      </div>
    </g:if>
  </div>

  <div class="span3">
    <g:if test="${incidenteInstance.id}">
      <div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'idNivel', 'error')} ">
        <label for="idNivel">Tiempo final de atención</label>
        <g:field id="tiempo" name="tiempo"
            value="${firmado.tiempoDeAtencionFormateado(incidenteInstance)}" disabled="true"/>
      </div>
    </g:if>
  </div>
</div>
