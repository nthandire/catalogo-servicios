<%@ page import="mx.gob.inr.catservicios.*" %>


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
  <g:select name="estado" from="${bitacoraInstance.constraints.estado.inList}"
    required="" value="${fieldValue(bean: bitacoraInstance, field: 'estado')}"
    valueMessagePrefix="cat_servCat.estado"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: bitacoraInstance, field: 'detalles', 'error')} ">
	<label for="detalles">
		<g:message code="bitacora.detalles.label" default="Detalles" />
	</label>
  <ul class="one-to-many" style="text-align: left;">
    <g:each in="${bitacoraInstance?.detalles.sort{it.id}}" var="b">
      <li><g:link controller="bitacoraDetalle" action="edit" id="${b.id}">
        ${b?.encodeAsHTML()}
      </g:link></li>
    </g:each>
    <li class="add">
      <g:link class="btn" controller="bitacoraDetalle" action="create"
        params="['bitacora.id': bitacoraInstance?.id]">
          ${message(code: 'default.add.label', args: ['Detalles'])}
      </g:link>
    </li>
  </ul>
</div>

<g:if test="${bitacoraInstance?.monitoreos}">
<div class="fieldtablecontain ${hasErrors(bean: bitacoraInstance, field: 'monitoreos', 'error')} ">
	<label for="monitoreos">
		<g:message code="bitacora.monitoreos.label" default="Bitacoras" />
	</label>
  <ul class="one-to-many">
    <g:each in="${bitacoraInstance?.monitoreos}" var="m">
        <li><g:link controller="monitoreo" action="show" id="${m.id}">${m?.encodeAsHTML()}</g:link></li>
    </g:each>
  </ul>
</div>
</g:if>

</g:if>


