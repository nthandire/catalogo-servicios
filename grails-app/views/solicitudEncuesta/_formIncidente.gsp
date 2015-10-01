<%@ page import="mx.gob.inr.catservicios.*" %>


<style type="text/css">
  textArea { width: 712px; }
</style>


<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'numeroIncidente', 'error')} ">
	<label for="numeroIncidente">
		<g:message code="incidente.numeroIncidente.label" default="Incidente" />
	</label>
	<g:field name="numeroIncidente" type="text" value="${incidenteInstance.toString()}"
		required="" disabled="true"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="incidente.descripcion.label" default="Descripcion" />
	</label>
	<g:textArea name="descripcion" cols="40" rows="5" maxlength="1500"
		value="${incidenteInstance?.descripcion}" disabled="true"/>
</div>

<h1>Soluciones</h1>

<div class="fieldtablecontain">
  ${"Solución nivel 1: " + incidenteInstance?.solucionNivel1.encodeAsHTML()}
</div>
<g:if test="${incidenteInstance?.solucionNivel2}">
  <div class="fieldtablecontain">
    -------------------------------------------
  </div>
  <div class="fieldtablecontain">
    ${"Solución nivel 2: " + incidenteInstance?.solucionNivel2.encodeAsHTML()}
  </div>
</g:if>
<g:if test="${incidenteInstance?.solucionNivel3}">
  <div class="fieldtablecontain">
    -------------------------------------------
  </div>
  <div class="fieldtablecontain">
    ${"Solución nivel 3: " + incidenteInstance?.solucionNivel3.encodeAsHTML()}
  </div>
</g:if>

<br/>
<h1>Encuestas</h1>
<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'p01', 'error')} ">
  <label for="p01">
    <g:message code="solicitud.p01.label" default="Pregunta 1" />
  </label>
  <g:select name="p01" disabled="false"
    from="${[1,2]}" style="width: 60px"
    valueMessagePrefix="encuesta.valor" value="${incidenteInstance.p01}" />
</div>
<br/>

<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'p02', 'error')} ">
	<label for="p02">
		<g:message code="solicitud.p02.label" default="Pregunta 2" />

	</label>
	<g:select name="p02" disabled="false"
		from="${[1,2]}" style="width: 60px"
		valueMessagePrefix="encuesta.valor" value="${incidenteInstance.p02}" />
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'p03', 'error')} ">
	<label for="p03">
		<g:message code="solicitud.p03.label" default="Pregunta 3" />

	</label>
	<g:select name="p03" disabled="false"
		from="${[1,2]}" style="width: 60px"
		valueMessagePrefix="encuesta.valor" value="${incidenteInstance.p03}" />
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'p04', 'error')} ">
	<label for="p04">
		<g:message code="solicitud.p04.label" default="Pregunta 4" />

	</label>
	<g:select name="p04" disabled="false"
		from="${[1,2]}" style="width: 60px"
		valueMessagePrefix="encuesta.valor" value="${incidenteInstance.p04}" />
</div>


