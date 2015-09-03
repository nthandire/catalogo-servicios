<%@ page import="mx.gob.inr.catservicios.Firmadigital" %>


<style type="text/css">
  textArea { width: 412px; }
</style>


<div class="fieldtablecontain ${hasErrors(bean: firmadigitalInstance, field: 'passwordfirma', 'error')} ">
	<label for="passwordfirma">
		<g:message code="firmadigital.passwordfirma.label" default="Passwordfirma" />
		
	</label>
	<g:field name="passwordfirma" type="password" value="${firmadigitalInstance?.passwordfirma}" required="true"/>
</div>

