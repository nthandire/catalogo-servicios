<g:javascript src="servicios.js" />

<style type="text/css">
  textArea { width: 412px; }
</style>

<div class="row-fluid">
  <div class="span4">
    <div class="fieldtablecontain ${hasErrors(bean: solicitudDetalleInstance, field: 'idServcat', 'error')} ">
      <label for="idServcat">
        <g:message code="solicitudDetalle.idServcat.label" default="Categoría" />
      </label>
      <g:select id="idServcat" name="idServcat.id" from="${categorias}"
        optionKey="id" value="${solicitudDetalleInstance?.idServcat?.id}"
        class="many-to-one" noSelection="['': '']"/>
    </div>
  </div>

  <div class="span4">
    <div class="fieldtablecontain ${hasErrors(bean: solicitudDetalleInstance, field: 'descripcion', 'error')} ">
      <label for="descripcion">
        <g:message code="solicitudDetalle.descripcion.label" default="Descripcion" />

      </label>
      <g:textArea id="descripcion" name="descripcion" cols="40" rows="5"
        maxlength="3000" value="${solicitudDetalleInstance?.descripcion}"/>
    </div>
  </div>

  <div class="span4">
    <div class="fieldtablecontain">
      <label for="resguardo">
        Inventario o No. de Serie o Usuario
      </label>
      <g:textField id="cpuauto" name="idResguardoentregadetalle"
        style="width: 250px;" />
    </div>
  </div>
</div>

<div class="row-fluid" id="panelEstado" style="display:none;">
  <div class="span4">
    <div class="fieldtablecontain">
      <label for="estado">
        <g:message code="solicitudDetalle.estado.label" default="Estado" />
      </label>
      <g:select name="estado" id="estado" from="['A','I']"
        valueMessagePrefix="cat_servCat.estado" value='A'/>
    </div>
  </div>
</div>

