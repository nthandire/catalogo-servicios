<%@ page import="mx.gob.inr.catservicios.Incidente" %>


<style type="text/css">
  textArea { width: 412px; }
</style>


<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'idSistema', 'error')} ">
	<label for="idSistema">
		<g:message code="incidente.idSistema.label" default="Id Sistema" />
		
	</label>
	<g:field name="idSistema" type="number" value="${incidenteInstance.idSistema}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'idResguardoentregadetalle', 'error')} ">
	<label for="idResguardoentregadetalle">
		<g:message code="incidente.idResguardoentregadetalle.label" default="Id Resguardoentregadetalle" />
		
	</label>
	<g:field name="idResguardoentregadetalle" type="number" value="${incidenteInstance.idResguardoentregadetalle}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'fechaIncidente', 'error')} ">
	<label for="fechaIncidente">
		<g:message code="incidente.fechaIncidente.label" default="Fecha Incidente" />
		
	</label>
	<g:datePicker name="fechaIncidente" precision="day"  value="${incidenteInstance?.fechaIncidente}" default="none" noSelection="['': '']" />
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'numeroIncidente', 'error')} ">
	<label for="numeroIncidente">
		<g:message code="incidente.numeroIncidente.label" default="Numero Incidente" />
		
	</label>
	<g:field name="numeroIncidente" type="number" value="${incidenteInstance.numeroIncidente}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'estadoIncidente', 'error')} ">
	<label for="estadoIncidente">
		<g:message code="incidente.estadoIncidente.label" default="Estado Incidente" />
		
	</label>
	
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'idReporta', 'error')} ">
	<label for="idReporta">
		<g:message code="incidente.idReporta.label" default="Id Reporta" />
		
	</label>
	<g:field name="idReporta" type="number" value="${incidenteInstance.idReporta}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'idServ', 'error')} ">
	<label for="idServ">
		<g:message code="incidente.idServ.label" default="Id Serv" />
		
	</label>
	<g:field name="idServ" type="number" value="${incidenteInstance.idServ}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'idServfinal', 'error')} ">
	<label for="idServfinal">
		<g:message code="incidente.idServfinal.label" default="Id Servfinal" />
		
	</label>
	<g:field name="idServfinal" type="number" value="${incidenteInstance.idServfinal}"/>
</div>

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
	<g:field name="nivel" type="number" value="${incidenteInstance.nivel}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'idServresp', 'error')} ">
	<label for="idServresp">
		<g:message code="incidente.idServresp.label" default="Id Servresp" />
		
	</label>
	<g:field name="idServresp" type="number" value="${incidenteInstance.idServresp}"/>
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
	<g:field name="idPrograma" type="number" value="${incidenteInstance.idPrograma}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'fechaModificacion', 'error')} ">
	<label for="fechaModificacion">
		<g:message code="incidente.fechaModificacion.label" default="Fecha Modificacion" />
		
	</label>
	<g:datePicker name="fechaModificacion" precision="day"  value="${incidenteInstance?.fechaModificacion}" default="none" noSelection="['': '']" />
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'ipTerminal', 'error')} ">
	<label for="ipTerminal">
		<g:message code="incidente.ipTerminal.label" default="Ip Terminal" />
		
	</label>
	<g:textField name="ipTerminal" maxlength="15" value="${incidenteInstance?.ipTerminal}"/>
</div>

