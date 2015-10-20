<%@ page import="mx.gob.inr.catservicios.*" %>


<style type="text/css">
  textArea { width: 412px; }
</style>


<div class="fieldtablecontain ${hasErrors(bean: solicitudDetalleInstance, field: 'idServcat', 'error')} ">
	<label for="idServcat">
		<g:message code="solicitudDetalle.idServcat.label" default="Categoría" />

	</label>
	<g:select id="idServcat" name="idServcat.id" from="${categorias}"
    optionKey="id" value="${solicitudDetalleInstance?.idServcat?.id}"
    class="many-to-one" noSelection="['': '']" required=""/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: solicitudDetalleInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="solicitudDetalle.descripcion.label" default="Descripcion" />

	</label>
	<g:textArea name="descripcion" cols="40" rows="5" maxlength="3000"
    value="${solicitudDetalleInstance?.descripcion}" required=""/>
</div>

<div class="fieldtablecontain">
  <label for="resguardo">
    <g:message code="solicitudDetalle.idResguardoentregadetalle.label" default="Equipo" />
  </label>
  <g:select id="idResguardoentregadetalle" name="idResguardoentregadetalle"
    from="${ResguardoEntregaDetalle.executeQuery('from ResguardoEntregaDetalle d where exists( from ResguardoEntrega r where r.id = d.idResguardo and r.codigo like ?)', "515%")}" optionKey="id" value="${solicitudDetalleInstance?.idResguardoentregadetalle}" class="many-to-one" noSelection="${['':'Seleccione una...']}"/>
</div>

<g:if test="${solicitudDetalleInstance.estado}">
  <div class="fieldtablecontain">
    <label for="estado">
      <g:message code="solicitudDetalle.estado.label" default="Estado" />
    </label>
    <g:select name="estado" from="${solicitudDetalleInstance.constraints.estado.inList}"
      valueMessagePrefix="cat_servCat.estado" value="${solicitudDetalleInstance.estado}"/>
  </div>
</g:if>