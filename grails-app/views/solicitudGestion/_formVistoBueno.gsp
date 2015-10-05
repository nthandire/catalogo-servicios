<%@ page import="mx.gob.inr.catservicios.*" %>


<style type="text/css">
  textArea { width: 412px; }
</style>


<div class="fieldtablecontain ${hasErrors(bean: solicitudInstance, field: 'justificacion', 'error')} ">
	<label for="justificacion">
		<g:message code="solicitud.justificacion.label" default="Justificacion" />
		
	</label>
	<g:textArea name="justificacion" cols="40" rows="5" maxlength="1500"
    value="${solicitudInstance?.justificacion}" disabled="true"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: solicitudInstance, field: 'idVb', 'error')} ">
  <label for="idVb">
    <g:message code="solicitudDetalle.idVb.label" default="Responsable" />
    
  </label>
  <g:select id="idVb" name="idVb"  from="${autorizadores}" optionKey="id"
     required="" value="${solicitudInstance?.idVb}" class="many-to-one"
     noSelection="['': 'Debe seleccionar uno ...']"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: firmadigitalInstance, field: 'passwordfirma', 'error')} ">
  <label for="passwordfirma">
    <g:message code="firmadigital.passwordfirma.label" default="Passwordfirma" />

  </label>
  <g:field name="passwordfirma" type="password" value="${firmadigitalInstance?.passwordfirma}" required="true"/>
</div>

