<%@ page import="mx.gob.inr.catservicios.SolicitudDetalle" %>


<style type="text/css">
  textArea { width: 412px; }
</style>


<div class="fieldtablecontain ${hasErrors(bean: solicitudDetalleInstance, field: 'idSolicitud', 'error')} ">
	<label for="idSolicitud">
		<g:message code="solicitudDetalle.idSolicitud.label" default="Id Solicitud" />
		
	</label>
	<g:select id="idSolicitud" name="idSolicitud.id" from="${mx.gob.inr.catservicios.Solicitud.list()}" optionKey="id" value="${solicitudDetalleInstance?.idSolicitud?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: solicitudDetalleInstance, field: 'idServ', 'error')} ">
	<label for="idServ">
		<g:message code="solicitudDetalle.idServ.label" default="Id Serv" />
		
	</label>
	<g:select id="idServ" name="idServ.id" from="${mx.gob.inr.catservicios.Cat_serv.list()}" optionKey="id" value="${solicitudDetalleInstance?.idServ?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<!--
<div class="fieldtablecontain ${hasErrors(bean: solicitudDetalleInstance, field: 'idResguardoentregadetalle', 'error')} ">
	<label for="idResguardoentregadetalle">
		<g:message code="solicitudDetalle.idResguardoentregadetalle.label" default="Id Resguardoentregadetalle" />
		
	</label>
	<g:field name="idResguardoentregadetalle" type="number" value="${solicitudDetalleInstance.idResguardoentregadetalle}"/>
</div>
-->

<div class="fieldtablecontain ${hasErrors(bean: solicitudDetalleInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="solicitudDetalle.descripcion.label" default="Descripcion" />
		
	</label>
	<g:textArea name="descripcion" cols="40" rows="5" maxlength="3000" value="${solicitudDetalleInstance?.descripcion}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: solicitudDetalleInstance, field: 'idPrograma', 'error')} ">
	<label for="idPrograma">
		<g:message code="solicitudDetalle.idPrograma.label" default="Id Programa" />
		
	</label>
	<g:select id="idPrograma" name="idPrograma.id" from="${mx.gob.inr.catservicios.CatPrograma.list()}" optionKey="id" value="${solicitudDetalleInstance?.idPrograma?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

