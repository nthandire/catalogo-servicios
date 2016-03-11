<%@ page import="mx.gob.inr.catservicios.*" %>

<div class="fieldtablecontain">
  <label>
    observaciones del problema
  </label>
  <g:textArea style="width:412px;" name="observaciones" id="observaciones"
    cols="30" rows="5" maxlength="3000" />
</div>

<div class="fieldtablecontain ${hasErrors(bean: firmadigitalInstance, field: 'passwordfirma', 'error')}">
  <label for="passwordfirma">
    <g:message code="firmadigital.passwordfirma.label" default="Passwordfirma" />
  </label>
  <g:field name="passwordfirma" id="passwordfirma" type="password"
    value="${firmadigitalInstance?.passwordfirma}"/>
</div>
