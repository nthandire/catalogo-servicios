<%@ page import="mx.gob.inr.catservicios.*" %>


<style type="text/css">
  textArea { width: 712px; }
</style>


<div class="fieldtablecontain ${hasErrors(bean: solicitudInstance, field: 'numeroSolicitud', 'error')} ">
	<label for="numeroSolicitud">
		<g:message code="solicitud.numeroSolicitud.label" default="Solicitud" />
	</label>
	<g:field name="numeroSolicitud" type="text" value="${solicitudInstance.toString()}"
		required="" disabled="true"/>
</div>

<h1>Solicitudes y soluciones</h1>
<g:each in="${SolicitudDetalle.findAllByIdSolicitudAndEstado(solicitudInstance,
                            'A' as char)}" var="d">
  <div class="fieldtablecontainLeft">
    <b style='color:blue;'>Solicitud:</b> ${" ${d?.encodeAsHTML()}  : ${d?.descripcion.encodeAsHTML()}"}
  </div>
  <div class="fieldtablecontainLeft">
    ${"<b style='color:blue;'>Solución:</b> " + d?.solucion.encodeAsHTML()}
  </div>
  <div class="fieldtablecontain">
    -------------------------------------------
  </div>
</g:each>
<br/>
<h1>Encuestas</h1>
<div class="fieldtablecontain ${hasErrors(bean: solicitudInstance, field: 'p01', 'error')} ">
  <label for="p01">
    <g:message code="solicitud.p01.label" default="Numero Solicitud" />
  </label>
  <g:select name="p01" disabled="false"
    from="${[1,2]}" style="width: 60px"
    valueMessagePrefix="encuesta.valor" value="${solicitudInstance.p01}" />
</div>
<br/>

<div class="fieldtablecontain ${hasErrors(bean: solicitudInstance, field: 'p02', 'error')} ">
	<label for="p02">
		<g:message code="solicitud.p02.label" default="Numero Solicitud" />

	</label>
	<g:select name="p02" disabled="false"
		from="${[1,2]}" style="width: 60px"
		valueMessagePrefix="encuesta.valor" value="${solicitudInstance.p02}" />
</div>

<div class="fieldtablecontain ${hasErrors(bean: solicitudInstance, field: 'p03', 'error')} ">
	<label for="p03">
		<g:message code="solicitud.p03.label" default="Numero Solicitud" />

	</label>
	<g:select name="p03" disabled="false"
		from="${[1,2]}" style="width: 60px"
		valueMessagePrefix="encuesta.valor" value="${solicitudInstance.p03}" />
</div>

<div class="fieldtablecontain ${hasErrors(bean: solicitudInstance, field: 'p04', 'error')} ">
	<label for="p04">
		<g:message code="solicitud.p04.label" default="Numero Solicitud" />

	</label>
	<g:select name="p04" disabled="false"
		from="${[1,2]}" style="width: 60px"
		valueMessagePrefix="encuesta.valor" value="${solicitudInstance.p04}" />
</div>


