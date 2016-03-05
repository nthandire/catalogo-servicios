<%@ page import="mx.gob.inr.catservicios.*" %>

<style type="text/css">
  textArea { width: 412px; }
</style>


<g:if test="${monitoreoInstance.id}">
<div class="fieldtablecontain ${hasErrors(bean: monitoreoInstance, field: 'fechaMonitoreo', 'error')} required">
	<label for="fechaMonitoreo">
		<g:message code="monitoreo.fechaMonitoreo.label" default="Fecha" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="fechaMonitoreo" type="text" disabled="true"
    value="${monitoreoInstance?.fechaMonitoreo.format("dd/MMM/YYYY")}"
    style="text-align: center;" />
</div>

<div class="fieldtablecontain ${hasErrors(bean: monitoreoInstance, field: 'numeroMonitoreo', 'error')} required">
  <label for="numeroMonitoreo">
    <g:message code="monitoreo.numeroMonitoreo.label" default="Número" />
  </label>
  <g:field name="numeroMonitoreo" type="text" value="${monitoreoInstance}"
    disabled="true" style="text-align: center;" />
</div>

<div class="fieldtablecontain ${hasErrors(bean: monitoreoInstance, field: 'bitacora', 'error')} required">
  <label for="bitacora">
    <g:message code="monitoreo.bitacora.label" default="Bitacora" />
  </label>
  <g:field id="bitacora" name="bitacora.id" type="text" disabled="true" value="${monitoreoInstance.bitacora}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: monitoreoInstance, field: 'estado', 'error')} required">
  <label for="estado">
    <g:message code="monitoreo.estado.label" default="Estado" />
  </label>
  <g:select name="estado" 
    from="${monitoreoInstance.constraints.estado.inList}" required=""
    value="${fieldValue(bean: monitoreoInstance, field: 'estado')}"
    valueMessagePrefix="cat_servCat.estado"/>
</div>

</g:if>
<g:else>

<div class="fieldtablecontain ${hasErrors(bean: monitoreoInstance, field: 'bitacora', 'error')} required">
  <label for="bitacora">
    <g:message code="monitoreo.bitacora.label" default="Bitacora" />
  </label>
  <g:select id="bitacora" name="bitacora.id" from="${Bitacora.findAllByEstado('A' as char).sort{it.id}}"
    optionKey="id" optionValue="${monitoreoInstance.bitacora}" />
</div>

</g:else>

<div class="fieldtablecontain ${hasErrors(bean: monitoreoInstance, field: 'semaforo', 'error')} required">
  <label for="semaforo">
    <g:message code="monitoreo.semaforo.label" default="Semaforo" />
    <span class="required-indicator">*</span>
  </label>
  <g:select name="semaforo" from="${[1,2,3]}" valueMessagePrefix="intensidad.valor"
    value="${monitoreoInstance.semaforo}" required="" />
</div>

<div class="fieldtablecontain ${hasErrors(bean: monitoreoInstance, field: 'nota', 'error')} ">
	<label for="nota">
		<g:message code="monitoreo.nota.label" default="Nota" />
	</label>
	<g:textArea name="nota" cols="40" rows="5" maxlength="3000"
    value="${monitoreoInstance?.nota}" required="" />
</div>

<g:if test="${monitoreoInstance.detalles}">
<div class="fieldtablecontain ${hasErrors(bean: monitoreoInstance, field: 'detalles', 'error')} ">
	<label for="detalles">
		<g:message code="monitoreo.detalles.label" default="Detalles" />
	</label>

  <div style="margin: auto; width: 40%;" >
    <table>
      <g:each in="${monitoreoInstance?.detalles.sort{it.id}}" var="m">
          <tr style="text-align: left;">
            <td>
              <g:checkBox name="det[${m.id}]" value="${m.estado}" />
            </td>
            <td>
              <g:link controller="monitoreoDetalle" action="show" id="${m.id}">
                ${m?.encodeAsHTML()}
              </g:link>
            </td>
            <td>
              <g:field type="text" name="observ[${m.id}]" value="${m.observaciones}" />
            </td>
          </tr>
      </g:each>
    </table>
  </div>
</div>
</g:if>

