<%@ page import="mx.gob.inr.catservicios.*" %>

<g:javascript src="servicios.js" />

<style type="text/css">
  textArea { width: 712px; }
</style>



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
  <tr>
    <td>
        <label for="reporto">
          <g:message code="servicios.reporto.label" default="Reporto" />
        </label>
        <g:textField name="reporto" value="${serviciosInstance?.reporto}" required="" style="width: 250px;text-transform: uppercase;"/>

    </td>
    <td>
        <label for="extension">
          <g:message code="servicios.extension.label" default="Extension" />
        </label>
        <g:field type="number" name="extension" value="${serviciosInstance?.extension}" min="10000" max="99999" size="5" required="" style="width: 250px;"/>
    </td>
    <td>
    </td>
  </tr>
</table>













<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'idSistema', 'error')} ">
	<label for="idSistema">
		<g:message code="incidente.idSistema.label" default="Id Sistema" />

	</label>
	<g:select id="idSistema" name="idSistema.id" from="${CatSistema.list()}" optionKey="id" value="${incidenteInstance?.idSistema?.id}" class="many-to-one" noSelection="['': '']"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'idResguardoentregadetalle', 'error')} ">
	<label for="idResguardoentregadetalle">
		<g:message code="incidente.idResguardoentregadetalle.label" default="Equipo" />
	</label>
  <g:select id="idResguardoentregadetalle" name="idResguardoentregadetalle"
		from="${ResguardoEntregaDetalle.executeQuery(
      'from ResguardoEntregaDetalle d where exists( from ResguardoEntrega r where r.id = d.idResguardo and r.codigo like ?)', "515%")}"
      optionKey="id" class="many-to-one" noSelection="['': '']"
      value="${solicitudDetalleInstance?.idResguardoentregadetalle}"/>
</div>

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

<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'idServ', 'error')} required">
	<label for="idServ">
		<g:message code="cat_serv.servCat.label" default="Cat" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="servCat" name="idServ.servSub.servCat.id"
    from="${Cat_servCat.list()}" optionKey="id" required=""
    value="${incidenteInstance?.idServ?.servSub?.servCat?.id}"
    class="many-to-one" onchange="categoryChanged(this.value)"
    noSelection="${['':'Seleccione una...']}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'idServ', 'error')} required">
	<label for="idServ">
		<g:message code="cat_serv.servSub.label" default="Serv Sub" />
		<span class="required-indicator">*</span>
	</label>
	<span id="subContainer">
    <g:if test="${incidenteInstance?.idServ}">
      <g:select id='servSub' name='servSub.id' required=''
        onchange='subcategoryChanged(this.value)' optionKey='id'
        from="${Cat_servSub.findAllByServCat(incidenteInstance?.idServ?.servSub?.servCat, [order:'id'])}"
        value="${incidenteInstance?.idServ?.servSub?.id}" noSelection="['':' ']"/>
    </g:if>
  </span>
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'idServ', 'error')} required">
	<label for="idServ">
		<g:message code="cat_bitacora.servicio.label" default="Servicio" />
		<span class="required-indicator">*</span>
	</label>
	<span id="serviciosContainer">
    <g:if test="${incidenteInstance?.idServ}">
      <g:select id='idServ' name='idServ.id' required=''
        optionKey='id' value="${incidenteInstance?.idServ?.id}"
        from="${Cat_serv.findAllByServSub(incidenteInstance?.idServ?.servSub, [order:'id'])}"
        noSelection="['':' ']"/>
    </g:if>
  </span>
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
<%--
<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'idServfinal', 'error')}">
	<label for="idServfinal">
		<g:message code="cat_serv.servCat.final.label" default="Categoría final" />
	</label>
	<g:select id="servCat" name="idServfinal.servSub.servCat.id" from="${Cat_servCat.list()}" optionKey="id" value="${incidenteInstance?.idServfinal?.servSub?.servCat?.id}" class="many-to-one" onchange="categoryChangedFinal(this.value)" noSelection="${['':'Seleccione una...']}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'idServfinal', 'error')} required">
	<label for="idServfinal">
		<g:message code="cat_serv.servSub.final.label" default="Subcategoría final" />
		<span class="required-indicator">*</span>
	</label>
	<span id="subContainerFinal"></span>
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'idServfinal', 'error')} required">
	<label for="idServfinal">
		<g:message code="cat_bitacora.servicio.final.label" default="Servicio final" />
		<span class="required-indicator">*</span>
	</label>
	<span id="serviciosContainerFinal"></span>
</div>

<script>
    function categoryChangedFinal(categoryId) {
        <g:remoteFunction controller="incidente" action="categoryChangedFinal"
            update="subContainerFinal"
            params="'categoryId='+categoryId"/>
    }
    function subcategoryChangedFinal(subcategoryId) {
        <g:remoteFunction controller="incidente" action="subcategoryChangedFinal"
            update="serviciosContainerFinal"
            params="'subcategoryId='+subcategoryId"/>
    }
</script>
--%>


<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="incidente.descripcion.label" default="Descripcion" />
	</label>
	<g:textArea name="descripcion" cols="40" rows="5" maxlength="3000"
    value="${incidenteInstance?.descripcion}" required="true"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'nivel', 'error')} ">
	<label for="nivel">
		<g:message code="incidente.nivel.label" default="Nivel" />
	</label>
	<g:field name="nivel" type="number" min="1" max="3"
    value="${incidenteInstance.nivel}" disabled="true"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'idServresp', 'error')} ">
	<label for="idServresp">
		<g:message code="incidente.idServresp.label" default="Responsable" />
	</label>
	<g:select id="idServresp" name="idServresp.id" from="${Cat_servResp.list()}"
    optionKey="id" value="${incidenteInstance?.idServresp?.id}" class="many-to-one"
    noSelection="['': '']" disabled="true"/>
</div>

<g:if test="${tecnicos}">
  <div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'idNivel', 'error')} ">
    <label for="idNivel">
      <g:message code="incidente.idNivel.label" default="Técnico" />
    </label>
    <g:select id="idNivel" name="idNivel" required="true"
      from="${tecnicos}"
        optionKey="id" value="${idNivel}" class="many-to-one"
        noSelection="['': '']"/>
  </div>
</g:if>
