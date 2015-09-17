<%@ page import="mx.gob.inr.catservicios.*" %>


<style type="text/css">
  textArea { width: 412px; }
</style>


<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'idSistema', 'error')} ">
	<label for="idSistema">
		<g:message code="incidente.idSistema.label" default="Id Sistema" />

	</label>
	<g:select id="idSistema" name="idSistema.id" from="${CatSistema.list()}" optionKey="id" value="${incidenteInstance?.idSistema?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'idResguardoentregadetalle', 'error')} ">
	<label for="idResguardoentregadetalle">
		<g:message code="incidente.idResguardoentregadetalle.label" default="Equipo" />
	</label>
  <g:select id="idResguardoentregadetalle" name="idResguardoentregadetalle"
		from="${ResguardoEntregaDetalle.executeQuery('from ResguardoEntregaDetalle d where exists( from ResguardoEntrega r where r.id = d.idResguardo and r.codigo like ?)', "515%")}" optionKey="id" value="${solicitudDetalleInstance?.idResguardoentregadetalle}" class="many-to-one"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'idReporta', 'error')} ">
	<label for="idReporta">
		<g:message code="incidente.idReporta.label" default="Id Reporta" />
	</label>
	<g:field name="idReporta" type="number" value="${incidenteInstance.idReporta}" required=""/> <!-- // TODO: poner un select -->
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'idServ', 'error')} required">
	<label for="idServ">
		<g:message code="cat_serv.servCat.label" default="Cat" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="servCat" name="idServ.servSub.servCat.id" from="${Cat_servCat.list()}" optionKey="id" required="" value="${incidenteInstance?.idServ?.servSub?.servCat?.id}" class="many-to-one" onchange="categoryChanged(this.value)" noSelection="${['':'Seleccione una...']}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'idServ', 'error')} required">
	<label for="idServ">
		<g:message code="cat_serv.servSub.label" default="Serv Sub" />
		<span class="required-indicator">*</span>
	</label>
	<span id="subContainer"></span>
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'idServ', 'error')} required">
	<label for="idServ">
		<g:message code="cat_bitacora.servicio.label" default="Servicio" />
		<span class="required-indicator">*</span>
	</label>
	<span id="serviciosContainer"></span>
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


<!--
<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="incidente.descripcion.label" default="Descripcion" />
	</label>
	<g:textArea name="descripcion" cols="40" rows="5" maxlength="3000" value="${incidenteInstance?.descripcion}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'nivel', 'error')} ">
	<label for="nivel">
		<g:message code="incidente.nivel.label" default="Nivel" />
	</label>
	<g:field name="nivel" type="number" min="1" max="3" value="${incidenteInstance.nivel}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'idServresp', 'error')} ">
	<label for="idServresp">
		<g:message code="incidente.idServresp.label" default="Id Servresp" />
	</label>
	<g:select id="idServresp" name="idServresp.id" from="${Cat_servResp.list()}" optionKey="id" value="${incidenteInstance?.idServresp?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'idCaptura', 'error')} ">
	<label for="idCaptura">
		<g:message code="incidente.idCaptura.label" default="Id Captura" />
	</label>
	<g:field name="idCaptura" type="number" value="${incidenteInstance.idCaptura}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'idNivel1', 'error')} ">
	<label for="idNivel1">
		<g:message code="incidente.idNivel1.label" default="Id Nivel1" />
	</label>
	<g:field name="idNivel1" type="number" value="${incidenteInstance.idNivel1}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'fechaNivel1', 'error')} ">
	<label for="fechaNivel1">
		<g:message code="incidente.fechaNivel1.label" default="Fecha Nivel1" />
	</label>
	<g:datePicker name="fechaNivel1" precision="day"  value="${incidenteInstance?.fechaNivel1}" default="none" noSelection="['': '']" />
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'firmaNivel1', 'error')} ">
	<label for="firmaNivel1">
		<g:message code="incidente.firmaNivel1.label" default="Firma Nivel1" />
	</label>
	<g:checkBox name="firmaNivel1" value="${incidenteInstance?.firmaNivel1}" />
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'solucionNivel1', 'error')} ">
	<label for="solucionNivel1">
		<g:message code="incidente.solucionNivel1.label" default="Solucion Nivel1" />
	</label>
	<g:textArea name="solucionNivel1" cols="40" rows="5" maxlength="3000" value="${incidenteInstance?.solucionNivel1}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'fechaSolnivel1', 'error')} ">
	<label for="fechaSolnivel1">
		<g:message code="incidente.fechaSolnivel1.label" default="Fecha Solnivel1" />
	</label>
	<g:datePicker name="fechaSolnivel1" precision="day"  value="${incidenteInstance?.fechaSolnivel1}" default="none" noSelection="['': '']" />
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'idAsignanivel2', 'error')} ">
	<label for="idAsignanivel2">
		<g:message code="incidente.idAsignanivel2.label" default="Id Asignanivel2" />
	</label>
	<g:field name="idAsignanivel2" type="number" value="${incidenteInstance.idAsignanivel2}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'idNivel2', 'error')} ">
	<label for="idNivel2">
		<g:message code="incidente.idNivel2.label" default="Id Nivel2" />
	</label>
	<g:field name="idNivel2" type="number" value="${incidenteInstance.idNivel2}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'fechaNivel2', 'error')} ">
	<label for="fechaNivel2">
		<g:message code="incidente.fechaNivel2.label" default="Fecha Nivel2" />
	</label>
	<g:datePicker name="fechaNivel2" precision="day"  value="${incidenteInstance?.fechaNivel2}" default="none" noSelection="['': '']" />
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'firmaNivel2', 'error')} ">
	<label for="firmaNivel2">
		<g:message code="incidente.firmaNivel2.label" default="Firma Nivel2" />
	</label>
	<g:checkBox name="firmaNivel2" value="${incidenteInstance?.firmaNivel2}" />
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'solucionNivel2', 'error')} ">
	<label for="solucionNivel2">
		<g:message code="incidente.solucionNivel2.label" default="Solucion Nivel2" />
	</label>
	<g:textArea name="solucionNivel2" cols="40" rows="5" maxlength="3000" value="${incidenteInstance?.solucionNivel2}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'fechaSolnivel2', 'error')} ">
	<label for="fechaSolnivel2">
		<g:message code="incidente.fechaSolnivel2.label" default="Fecha Solnivel2" />
	</label>
	<g:datePicker name="fechaSolnivel2" precision="day"  value="${incidenteInstance?.fechaSolnivel2}" default="none" noSelection="['': '']" />
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'idAsignanivel3', 'error')} ">
	<label for="idAsignanivel3">
		<g:message code="incidente.idAsignanivel3.label" default="Id Asignanivel3" />
	</label>
	<g:field name="idAsignanivel3" type="number" value="${incidenteInstance.idAsignanivel3}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'idNivel3', 'error')} ">
	<label for="idNivel3">
		<g:message code="incidente.idNivel3.label" default="Id Nivel3" />
	</label>
	<g:field name="idNivel3" type="number" value="${incidenteInstance.idNivel3}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'fechaNivel3', 'error')} ">
	<label for="fechaNivel3">
		<g:message code="incidente.fechaNivel3.label" default="Fecha Nivel3" />
	</label>
	<g:datePicker name="fechaNivel3" precision="day"  value="${incidenteInstance?.fechaNivel3}" default="none" noSelection="['': '']" />
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'firmaNivel3', 'error')} ">
	<label for="firmaNivel3">
		<g:message code="incidente.firmaNivel3.label" default="Firma Nivel3" />
	</label>
	<g:checkBox name="firmaNivel3" value="${incidenteInstance?.firmaNivel3}" />
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'solucionNivel3', 'error')} ">
	<label for="solucionNivel3">
		<g:message code="incidente.solucionNivel3.label" default="Solucion Nivel3" />
	</label>
	<g:textArea name="solucionNivel3" cols="40" rows="5" maxlength="3000" value="${incidenteInstance?.solucionNivel3}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'fechaSolnivel3', 'error')} ">
	<label for="fechaSolnivel3">
		<g:message code="incidente.fechaSolnivel3.label" default="Fecha Solnivel3" />
	</label>
	<g:datePicker name="fechaSolnivel3" precision="day"  value="${incidenteInstance?.fechaSolnivel3}" default="none" noSelection="['': '']" />
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'p01', 'error')} ">
	<label for="p01">
		<g:message code="incidente.p01.label" default="P01" />
	</label>
	<g:field name="p01" type="number" value="${incidenteInstance.p01}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'p02', 'error')} ">
	<label for="p02">
		<g:message code="incidente.p02.label" default="P02" />
	</label>
	<g:field name="p02" type="number" value="${incidenteInstance.p02}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'p03', 'error')} ">
	<label for="p03">
		<g:message code="incidente.p03.label" default="P03" />
	</label>
	<g:field name="p03" type="number" value="${incidenteInstance.p03}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'p04', 'error')} ">
	<label for="p04">
		<g:message code="incidente.p04.label" default="P04" />
	</label>
	<g:field name="p04" type="number" value="${incidenteInstance.p04}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'idPrograma', 'error')} ">
	<label for="idPrograma">
		<g:message code="incidente.idPrograma.label" default="Id Programa" />
	</label>
	<g:select id="idPrograma" name="idPrograma.id" from="${CatPrograma.list()}" optionKey="id" value="${incidenteInstance?.idPrograma?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'ipTerminal', 'error')} ">
	<label for="ipTerminal">
		<g:message code="incidente.ipTerminal.label" default="Ip Terminal" />
	</label>
	<g:textField name="ipTerminal" maxlength="15" value="${incidenteInstance?.ipTerminal}"/>
</div>

-->