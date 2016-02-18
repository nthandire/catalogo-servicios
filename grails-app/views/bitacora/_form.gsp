<%@ page import="mx.gob.inr.catservicios.Bitacora" %>


<style type="text/css">
  textArea { width: 412px; }
</style>


<div class="fieldtablecontain ${hasErrors(bean: bitacoraInstance, field: 'tipoBitacora', 'error')} required">
	<label for="tipoBitacora">
		<g:message code="bitacora.tipoBitacora.label" default="Tipo" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="tipoBitacora" maxlength="100" required="" value="${bitacoraInstance?.tipoBitacora}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: bitacoraInstance, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="bitacora.descripcion.label" default="Descripción" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="descripcion" cols="40" rows="5" maxlength="255" required="" value="${bitacoraInstance?.descripcion}"/>
</div>

<g:if test="${bitacoraInstance.id}">

<div class="fieldtablecontain ${hasErrors(bean: bitacoraInstance, field: 'estado', 'error')} required">
  <label for="estado">
    <g:message code="bitacora.estado.label" default="Estado" />
  </label>
  <g:select name="estado" from="${bitacoraInstance.constraints.estado.inList}" required="" value="${fieldValue(bean: bitacoraInstance, field: 'estado')}" valueMessagePrefix="cat_servCat.estado"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: bitacoraInstance, field: 'bitacoraDetalles', 'error')} ">
	<label for="bitacoraDetalles">
		<g:message code="bitacora.bitacoraDetalles.label" default="Detalles" />
	</label>
  <ul class="one-to-many">
    <g:each in="${bitacoraInstance?.bitacoraDetalles}" var="b">
        <li><g:link controller="bitacoraDetalle" action="show" id="${b.id}">${b?.encodeAsHTML()}</g:link></li>
    </g:each>
    <li class="add">
    <g:link controller="bitacoraDetalle" action="create" params="['bitacora.id': bitacoraInstance?.id]">${message(code: 'default.add.label', args: ['Detalles'])}</g:link>
    </li>
  </ul>
</div>

<div class="fieldtablecontain ${hasErrors(bean: bitacoraInstance, field: 'monitoreos', 'error')} ">
	<label for="monitoreos">
		<g:message code="bitacora.monitoreos.label" default="Monitoreos" />
	</label>
  <ul class="one-to-many">
    <g:each in="${bitacoraInstance?.monitoreos}" var="m">
        <li><g:link controller="monitoreo" action="show" id="${m.id}">${m?.encodeAsHTML()}</g:link></li>
    </g:each>
    <li class="add">
    <g:link controller="monitoreo" action="create" params="['bitacora.id': bitacoraInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'monitoreo.label', default: 'Monitoreo')])}</g:link>
    </li>
  </ul>
</div>

</g:if>


