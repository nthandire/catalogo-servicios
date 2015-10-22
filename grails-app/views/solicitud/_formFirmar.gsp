<%@ page import="mx.gob.inr.catservicios.Firmadigital" %>


<div class="fieldtablecontain ${hasErrors(bean: firmadigitalInstance, field: 'passwordfirma', 'error')} ">
	<label for="passwordfirma">
		<g:message code="firmadigital.passwordfirma.label" default="Passwordfirma" />

	</label>
	<g:field name="passwordfirma" id="passwordfirma" type="password" value="${firmadigitalInstance?.passwordfirma}"/>
</div>

