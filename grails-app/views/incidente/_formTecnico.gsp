<%@ page import="mx.gob.inr.catservicios.*" %>

<%--
<g:if test="${tecnicos}">
--%>
  <div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'idNivel', 'error')} ">
    <label for="idNivel">
      <g:message code="incidente.idNivel.label" default="Atendido por ..." />
    </label>
    <g:select id="idNivel" name="idNivel" required="true" from="${tecnicos}"
      optionKey="id" value="${idNivel}" class="many-to-one"/>
  </div>
<%--
</g:if>
--%>

<div class="fieldtablecontain ${hasErrors(bean: firmadigitalInstance, field: 'passwordfirma', 'error')} ">
  <label for="passwordfirma">
    <g:message code="firmadigital.passwordfirma.label" default="Passwordfirma" />

  </label>
  <g:field name="passwordfirmaTec" id="passwordfirmaTec" type="password"
    value="${firmadigitalInstance?.passwordfirma}"/>
</div>
