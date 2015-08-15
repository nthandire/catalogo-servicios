<%@ page import="mx.gob.inr.catservicios.Solicitud" %>


<style type="text/css">
  textArea { width: 412px; }
</style>


<div class="fieldtablecontain ${hasErrors(bean: solicitudInstance, field: 'fechaSolicitud', 'error')} ">
	<label for="fechaSolicitud">
		<g:message code="solicitud.fechaSolicitud.label" default="Fecha Solicitud" />
		
	</label>
	<g:datePicker name="fechaSolicitud" precision="day"  value="${solicitudInstance?.fechaSolicitud}" default="none" noSelection="['': '']" />
</div>

<div class="fieldtablecontain ${hasErrors(bean: solicitudInstance, field: 'numeroSolicitud', 'error')} ">
	<label for="numeroSolicitud">
		<g:message code="solicitud.numeroSolicitud.label" default="Numero Solicitud" />
		
	</label>
	<g:field name="numeroSolicitud" type="number" value="${solicitudInstance.numeroSolicitud}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: solicitudInstance, field: 'estadoSolicitud', 'error')} ">
	<label for="estadoSolicitud">
		<g:message code="solicitud.estadoSolicitud.label" default="Estado Solicitud" />
		
	</label>
	
</div>

<div class="fieldtablecontain ${hasErrors(bean: solicitudInstance, field: 'justificacion', 'error')} ">
	<label for="justificacion">
		<g:message code="solicitud.justificacion.label" default="Justificacion" />
		
	</label>
	<g:textArea name="justificacion" cols="40" rows="5" maxlength="1500" value="${solicitudInstance?.justificacion}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: solicitudInstance, field: 'idSolicitante', 'error')} ">
	<label for="idSolicitante">
		<g:message code="solicitud.idSolicitante.label" default="Id Solicitante" />
		
	</label>
	<g:field name="idSolicitante" type="number" value="${solicitudInstance.idSolicitante}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: solicitudInstance, field: 'idAutoriza', 'error')} ">
	<label for="idAutoriza">
		<g:message code="solicitud.idAutoriza.label" default="Id Autoriza" />
		
	</label>
	<g:field name="idAutoriza" type="number" value="${solicitudInstance.idAutoriza}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: solicitudInstance, field: 'fechaAutoriza', 'error')} ">
	<label for="fechaAutoriza">
		<g:message code="solicitud.fechaAutoriza.label" default="Fecha Autoriza" />
		
	</label>
	<g:datePicker name="fechaAutoriza" precision="day"  value="${solicitudInstance?.fechaAutoriza}" default="none" noSelection="['': '']" />
</div>

<div class="fieldtablecontain ${hasErrors(bean: solicitudInstance, field: 'idVb', 'error')} ">
	<label for="idVb">
		<g:message code="solicitud.idVb.label" default="Id Vb" />
		
	</label>
	<g:field name="idVb" type="number" value="${solicitudInstance.idVb}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: solicitudInstance, field: 'fechaVb', 'error')} ">
	<label for="fechaVb">
		<g:message code="solicitud.fechaVb.label" default="Fecha Vb" />
		
	</label>
	<g:datePicker name="fechaVb" precision="day"  value="${solicitudInstance?.fechaVb}" default="none" noSelection="['': '']" />
</div>

<div class="fieldtablecontain ${hasErrors(bean: solicitudInstance, field: 'p01', 'error')} ">
	<label for="p01">
		<g:message code="solicitud.p01.label" default="P01" />
		
	</label>
	<g:field name="p01" type="number" value="${solicitudInstance.p01}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: solicitudInstance, field: 'p02', 'error')} ">
	<label for="p02">
		<g:message code="solicitud.p02.label" default="P02" />
		
	</label>
	<g:field name="p02" type="number" value="${solicitudInstance.p02}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: solicitudInstance, field: 'p03', 'error')} ">
	<label for="p03">
		<g:message code="solicitud.p03.label" default="P03" />
		
	</label>
	<g:field name="p03" type="number" value="${solicitudInstance.p03}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: solicitudInstance, field: 'p04', 'error')} ">
	<label for="p04">
		<g:message code="solicitud.p04.label" default="P04" />
		
	</label>
	<g:field name="p04" type="number" value="${solicitudInstance.p04}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: solicitudInstance, field: 'fechaModificacion', 'error')} ">
	<label for="fechaModificacion">
		<g:message code="solicitud.fechaModificacion.label" default="Fecha Modificacion" />
		
	</label>
	<g:datePicker name="fechaModificacion" precision="day"  value="${solicitudInstance?.fechaModificacion}" default="none" noSelection="['': '']" />
</div>

<div class="fieldtablecontain ${hasErrors(bean: solicitudInstance, field: 'ipTerminal', 'error')} ">
	<label for="ipTerminal">
		<g:message code="solicitud.ipTerminal.label" default="Ip Terminal" />
		
	</label>
	<g:textField name="ipTerminal" maxlength="15" value="${solicitudInstance?.ipTerminal}"/>
</div>

