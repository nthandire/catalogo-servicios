<%@ page import="mx.gob.inr.catservicios.IncidenteLaboratorio" %>


<style type="text/css">
  textArea { width: 412px; }
</style>


<div class="fieldtablecontain ${hasErrors(bean: incidenteLaboratorioInstance, field: 'idIncidente', 'error')} ">
	<label for="idIncidente">
		<g:message code="incidenteLaboratorio.idIncidente.label" default="Id Incidente" />
		
	</label>
	<g:field name="idIncidente" type="number" value="${incidenteLaboratorioInstance.idIncidente}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteLaboratorioInstance, field: 'fechaLaboratorio', 'error')} ">
	<label for="fechaLaboratorio">
		<g:message code="incidenteLaboratorio.fechaLaboratorio.label" default="Fecha Laboratorio" />
		
	</label>
	<g:datePicker name="fechaLaboratorio" precision="day"  value="${incidenteLaboratorioInstance?.fechaLaboratorio}" default="none" noSelection="['': '']" />
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteLaboratorioInstance, field: 'numeroLaboratorio', 'error')} ">
	<label for="numeroLaboratorio">
		<g:message code="incidenteLaboratorio.numeroLaboratorio.label" default="Numero Laboratorio" />
		
	</label>
	<g:field name="numeroLaboratorio" type="number" value="${incidenteLaboratorioInstance.numeroLaboratorio}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteLaboratorioInstance, field: 'idEstado', 'error')} ">
	<label for="idEstado">
		<g:message code="incidenteLaboratorio.idEstado.label" default="Id Estado" />
		
	</label>
	<g:field name="idEstado" type="number" value="${incidenteLaboratorioInstance.idEstado}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteLaboratorioInstance, field: 'idTiposervicio', 'error')} ">
	<label for="idTiposervicio">
		<g:message code="incidenteLaboratorio.idTiposervicio.label" default="Id Tiposervicio" />
		
	</label>
	<g:field name="idTiposervicio" type="number" value="${incidenteLaboratorioInstance.idTiposervicio}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteLaboratorioInstance, field: 'fallaTecnica', 'error')} ">
	<label for="fallaTecnica">
		<g:message code="incidenteLaboratorio.fallaTecnica.label" default="Falla Tecnica" />
		
	</label>
	<g:textField name="fallaTecnica" value="${incidenteLaboratorioInstance?.fallaTecnica}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteLaboratorioInstance, field: 'solucion', 'error')} ">
	<label for="solucion">
		<g:message code="incidenteLaboratorio.solucion.label" default="Solucion" />
		
	</label>
	<g:textArea name="solucion" cols="40" rows="5" maxlength="2000" value="${incidenteLaboratorioInstance?.solucion}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteLaboratorioInstance, field: 'refacciones', 'error')} ">
	<label for="refacciones">
		<g:message code="incidenteLaboratorio.refacciones.label" default="Refacciones" />
		
	</label>
	<g:textArea name="refacciones" cols="40" rows="5" maxlength="2000" value="${incidenteLaboratorioInstance?.refacciones}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteLaboratorioInstance, field: 'idUsuario', 'error')} ">
	<label for="idUsuario">
		<g:message code="incidenteLaboratorio.idUsuario.label" default="Id Usuario" />
		
	</label>
	<g:field name="idUsuario" type="number" value="${incidenteLaboratorioInstance.idUsuario}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteLaboratorioInstance, field: 'idProveedor', 'error')} ">
	<label for="idProveedor">
		<g:message code="incidenteLaboratorio.idProveedor.label" default="Id Proveedor" />
		
	</label>
	<g:field name="idProveedor" type="number" value="${incidenteLaboratorioInstance.idProveedor}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteLaboratorioInstance, field: 'fechaReporte', 'error')} ">
	<label for="fechaReporte">
		<g:message code="incidenteLaboratorio.fechaReporte.label" default="Fecha Reporte" />
		
	</label>
	<g:datePicker name="fechaReporte" precision="day"  value="${incidenteLaboratorioInstance?.fechaReporte}" default="none" noSelection="['': '']" />
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteLaboratorioInstance, field: 'numeroReporte', 'error')} ">
	<label for="numeroReporte">
		<g:message code="incidenteLaboratorio.numeroReporte.label" default="Numero Reporte" />
		
	</label>
	<g:textField name="numeroReporte" maxlength="30" value="${incidenteLaboratorioInstance?.numeroReporte}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteLaboratorioInstance, field: 'fechaSalida', 'error')} ">
	<label for="fechaSalida">
		<g:message code="incidenteLaboratorio.fechaSalida.label" default="Fecha Salida" />
		
	</label>
	<g:datePicker name="fechaSalida" precision="day"  value="${incidenteLaboratorioInstance?.fechaSalida}" default="none" noSelection="['': '']" />
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteLaboratorioInstance, field: 'fechaEntrega', 'error')} ">
	<label for="fechaEntrega">
		<g:message code="incidenteLaboratorio.fechaEntrega.label" default="Fecha Entrega" />
		
	</label>
	<g:datePicker name="fechaEntrega" precision="day"  value="${incidenteLaboratorioInstance?.fechaEntrega}" default="none" noSelection="['': '']" />
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteLaboratorioInstance, field: 'fechaReparacion', 'error')} ">
	<label for="fechaReparacion">
		<g:message code="incidenteLaboratorio.fechaReparacion.label" default="Fecha Reparacion" />
		
	</label>
	<g:datePicker name="fechaReparacion" precision="day"  value="${incidenteLaboratorioInstance?.fechaReparacion}" default="none" noSelection="['': '']" />
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteLaboratorioInstance, field: 'tecnico', 'error')} ">
	<label for="tecnico">
		<g:message code="incidenteLaboratorio.tecnico.label" default="Tecnico" />
		
	</label>
	<g:textField name="tecnico" value="${incidenteLaboratorioInstance?.tecnico}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteLaboratorioInstance, field: 'observaciones', 'error')} ">
	<label for="observaciones">
		<g:message code="incidenteLaboratorio.observaciones.label" default="Observaciones" />
		
	</label>
	<g:textArea name="observaciones" cols="40" rows="5" maxlength="2000" value="${incidenteLaboratorioInstance?.observaciones}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteLaboratorioInstance, field: 'lastUpdated', 'error')} ">
	<label for="lastUpdated">
		<g:message code="incidenteLaboratorio.lastUpdated.label" default="Fecha Modificacion" />
		
	</label>
	<g:datePicker name="lastUpdated" precision="day"  value="${incidenteLaboratorioInstance?.lastUpdated}" default="none" noSelection="['': '']" />
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteLaboratorioInstance, field: 'ipTerminal', 'error')} ">
	<label for="ipTerminal">
		<g:message code="incidenteLaboratorio.ipTerminal.label" default="Ip Terminal" />
		
	</label>
	<g:textField name="ipTerminal" maxlength="15" value="${incidenteLaboratorioInstance?.ipTerminal}"/>
</div>

