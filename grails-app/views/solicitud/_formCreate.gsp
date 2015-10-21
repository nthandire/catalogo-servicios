<style type="text/css">
  textArea { width: 412px; }
</style>




<div class="row-fluid">
  <div class="span6">
    <div class="fieldtablecontain ${hasErrors(bean: solicitudInstance, field: 'justificacion', 'error')} ">
      <label for="justificacion">
        <g:message code="solicitud.justificacion.label" default="Justificacion" />
      </label>
      <g:textArea name="justificacion" cols="40" rows="5" maxlength="1500"
        value="${solicitudInstance?.justificacion}"/>
    </div>
  </div>

  <div class="span6">
    <div class="fieldtablecontain ${hasErrors(bean: solicitudInstance, field: 'idAutoriza', 'error')} ">
      <label for="idAutoriza">
        <g:message code="solicitud.idAutoriza.label" default="Autoriza" />
      </label>
      <g:select id="idAutoriza" name="idAutoriza" from="${autorizadores}"
        optionKey="id" optionValue="nombreMostrar" value="${solicitudInstance?.idAutoriza}" class="many-to-one"
        noSelection="['': '']"/>
    </div>
  </div>
</div>
