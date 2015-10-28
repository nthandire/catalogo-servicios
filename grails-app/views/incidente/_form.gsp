<%@ page import="mx.gob.inr.catservicios.*" %>

<g:javascript src="servicios.js" />

<style type="text/css">
  textArea { width: 712px; }
</style>


<%--
<table class="table table-condensed">
  <tr>
    <td width="290px">
      &nbsp;
    </td>
    <td>
      <label for="cpuauto">Inventario o Número de Serie</label>
      <g:textField id="cpuauto" name="cpuauto" style="width: 250px;" />
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
        <g:textField name="equipo" value="${serviciosInstance?.equipo}" required="" readonly="true" style="width: 250px;text-transform: uppercase;"/>
    </td>
    <td>
        <label for="marca">
          <g:message code="servicios.marca.label" default="Marca" />
        </label>
        <g:textField name="marca" value="${serviciosInstance?.marca}" required="" readonly="true" style="width: 250px;text-transform: uppercase;"/>
    </td>
    <td>
        <label for="modelo">
          <g:message code="servicios.modelo.label" default="Modelo" />
        </label>
        <g:textField name="modelo" value="${serviciosInstance?.modelo}" required="" readonly="true" style="width: 250px;text-transform: uppercase;"/>
    </td>
  </tr>
  <tr>
    <td>
        <label for="serie">
          <g:message code="servicios.serie.label" default="Serie" />
          <span class="required-indicator">*</span>
        </label>
        <g:textField name="serie" value="${serviciosInstance?.serie}" required="" readonly="true" style="width: 250px;text-transform: uppercase;" />
    </td>
    <td>
        <label for="economico">
          <g:message code="servicios.economico.label" default="Economico" />
        </label>
        <g:textField name="economico" value="${serviciosInstance?.economico}" readonly="true" style="width: 250px;"/>
    </td>
    <td>
    </td>
  </tr>
</table>
--%>



<div class="row-fluid">
  <div class="span4">
    <div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'idSistema', 'error')} ">
      <label for="idSistema">
        <g:message code="incidente.idSistema.label" default="Sistema" />
      </label>
      <g:select id="idSistema" name="idSistema.id" from="${CatSistema.list()}" optionKey="id" value="${incidenteInstance?.idSistema?.id}" class="many-to-one" noSelection="['': '']"/>
    </div>
  </div>

  <div class="span4">
    <div class="fieldtablecontain ${hasErrors(bean: incidenteInstance,
        field: 'idResguardoentregadetalle', 'error')} ">
      <label for="idResguardoentregadetalle">
        <g:message code="incidente.idResguardoentregadetalle.label" default="Equipo" />
      </label>
      <g:select id="idResguardoentregadetalle" name="idResguardoentregadetalle"
        from="${ResguardoEntregaDetalle.executeQuery(
          'from ResguardoEntregaDetalle d where exists( from ResguardoEntrega r where r.id = d.idResguardo and r.codigo like ?)', "515%")}"
          optionKey="id" class="many-to-one" noSelection="['': '']"
          value="${solicitudDetalleInstance?.idResguardoentregadetalle}"/>
    </div>
  </div>

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

<g:set var="firmado" bean="firmadoService"/>
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
        <g:message code="cat_bitacora.servicio.label" default="Servicio" />
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

<g:if test="${incidenteInstance.id}">
  <div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'nivel', 'error')} ">
    <label for="nivel">
      <g:message code="incidente.nivel.label" default="Nivel" />
    </label>
    <g:field name="nivel" type="number" min="1" max="3"
      value="${incidenteInstance.nivel}" disabled="true"/>
  </div>
</g:if>

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

<g:if test="${tecnicos}">
  <div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'idNivel', 'error')} ">
    <label for="idNivel">
      <g:message code="incidente.idNivel.label" default="Atendido por ..." />
    </label>
    <g:field id="idNivel-show" name="idNivel-show"
        value="${Usuario.get(idNivel)}" disabled="true"/>
  </div>
</g:if>
