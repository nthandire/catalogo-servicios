<%@ page import="mx.gob.inr.catservicios.Bitacora" %>


<style type="text/css">
  textArea { width: 412px; }
</style>


<div class="fieldtablecontain ${hasErrors(bean: bitacoraInstance, field: 'tipoBitacora', 'error')} required">
	<label for="tipoBitacora">
		<g:message code="bitacora.tipoBitacora.label" default="Tipo Bitacora" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="tipoBitacora" maxlength="100" required="" value="${bitacoraInstance?.tipoBitacora}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: bitacoraInstance, field: 'desBitacora', 'error')} ">
	<label for="desBitacora">
		<g:message code="bitacora.desBitacora.label" default="Des Bitacora" />
		
	</label>
	<g:textArea name="desBitacora" cols="40" rows="5" maxlength="255" value="${bitacoraInstance?.desBitacora}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: bitacoraInstance, field: 'estadoBitacora', 'error')} required">
	<label for="estadoBitacora">
		<g:message code="bitacora.estadoBitacora.label" default="Estado Bitacora" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="estadoBitacora" from="${bitacoraInstance.constraints.estadoBitacora.inList}" required="" value="${fieldValue(bean: bitacoraInstance, field: 'estadoBitacora')}" valueMessagePrefix="bitacora.estadoBitacora"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: bitacoraInstance, field: 'bitacoraDetalles', 'error')} ">
	<label for="bitacoraDetalles">
		<g:message code="bitacora.bitacoraDetalles.label" default="Bitacora Detalles" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${bitacoraInstance?.bitacoraDetalles?}" var="b">
    <li><g:link controller="bitacoraDetalle" action="show" id="${b.id}">${b?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="bitacoraDetalle" action="create" params="['bitacora.id': bitacoraInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'bitacoraDetalle.label', default: 'BitacoraDetalle')])}</g:link>
</li>
</ul>

</div>

<div class="fieldtablecontain ${hasErrors(bean: bitacoraInstance, field: 'monitoreos', 'error')} ">
	<label for="monitoreos">
		<g:message code="bitacora.monitoreos.label" default="Monitoreos" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${bitacoraInstance?.monitoreos?}" var="m">
    <li><g:link controller="monitoreo" action="show" id="${m.id}">${m?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="monitoreo" action="create" params="['bitacora.id': bitacoraInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'monitoreo.label', default: 'Monitoreo')])}</g:link>
</li>
</ul>

</div>

