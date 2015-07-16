<%@ page import="mx.gob.inr.catservicios.Monitoreo" %>



<div class="fieldcontain ${hasErrors(bean: monitoreoInstance, field: 'fechaMonitoreo', 'error')} required">
	<label for="fechaMonitoreo">
		<g:message code="monitoreo.fechaMonitoreo.label" default="Fecha Monitoreo" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaMonitoreo" precision="day"  value="${monitoreoInstance?.fechaMonitoreo}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: monitoreoInstance, field: 'numeroMonitoreo', 'error')} required">
	<label for="numeroMonitoreo">
		<g:message code="monitoreo.numeroMonitoreo.label" default="Numero Monitoreo" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="numeroMonitoreo" type="number" value="${monitoreoInstance.numeroMonitoreo}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: monitoreoInstance, field: 'bitacora', 'error')} required">
	<label for="bitacora">
		<g:message code="monitoreo.bitacora.label" default="Bitacora" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="bitacora" name="bitacora.id" from="${mx.gob.inr.catservicios.Bitacora.list()}" optionKey="id" required="" value="${monitoreoInstance?.bitacora?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: monitoreoInstance, field: 'estadoMonitoreo', 'error')} required">
	<label for="estadoMonitoreo">
		<g:message code="monitoreo.estadoMonitoreo.label" default="Estado Monitoreo" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="estadoMonitoreo" from="${monitoreoInstance.constraints.estadoMonitoreo.inList}" required="" value="${fieldValue(bean: monitoreoInstance, field: 'estadoMonitoreo')}" valueMessagePrefix="monitoreo.estadoMonitoreo"/>
</div>

<div class="fieldcontain ${hasErrors(bean: monitoreoInstance, field: 'semaforo', 'error')} required">
	<label for="semaforo">
		<g:message code="monitoreo.semaforo.label" default="Semaforo" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="semaforo" type="number" min="1" max="3" value="${monitoreoInstance.semaforo}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: monitoreoInstance, field: 'nota', 'error')} ">
	<label for="nota">
		<g:message code="monitoreo.nota.label" default="Nota" />
		
	</label>
	<g:textArea name="nota" cols="40" rows="5" maxlength="3000" value="${monitoreoInstance?.nota}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: monitoreoInstance, field: 'modificacion', 'error')} required">
	<label for="modificacion">
		<g:message code="monitoreo.modificacion.label" default="Modificacion" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="modificacion" type="number" value="${monitoreoInstance.modificacion}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: monitoreoInstance, field: 'ipTerminal', 'error')} ">
	<label for="ipTerminal">
		<g:message code="monitoreo.ipTerminal.label" default="Ip Terminal" />
		
	</label>
	<g:textField name="ipTerminal" readonly="readonly" value="${monitoreoInstance.ipTerminal = request.getRemoteAddr(); monitoreoInstance?.ipTerminal}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: monitoreoInstance, field: 'monitoreoDetalles', 'error')} ">
	<label for="monitoreoDetalles">
		<g:message code="monitoreo.monitoreoDetalles.label" default="Monitoreo Detalles" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${monitoreoInstance?.monitoreoDetalles?}" var="m">
    <li><g:link controller="monitoreoDetalle" action="show" id="${m.id}">${m?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="monitoreoDetalle" action="create" params="['monitoreo.id': monitoreoInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'monitoreoDetalle.label', default: 'MonitoreoDetalle')])}</g:link>
</li>
</ul>

</div>

