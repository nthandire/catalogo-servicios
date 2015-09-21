<%@ page import="mx.gob.inr.catservicios.*" %>


<style type="text/css">
  textArea { width: 712px; }
</style>


<div class="fieldtablecontain ${hasErrors(bean: solicitudDetalleInstance, field: 'idServcat', 'error')} ">
	<label for="idServcat">
		<g:message code="solicitudDetalle.idServcat.label" default="Categoría" />
	</label>
	<g:select id="idServcat" name="idServcat.id" from="${Cat_servCat.list()}"
    optionKey="id" class="many-to-one" noSelection="['null': '']"
    value="${solicitudDetalleInstance?.idServcat?.id}" disabled="true"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'idServ', 'error')} required">
  <label for="idServ">
    <g:message code="cat_serv.servSub.label" default="Serv Sub" />
    <span class="required-indicator">*</span>
  </label>
  <g:select id='servSub' name='servSub' required=''
    onchange="subcategoryChanged(this.value)"
    from="${Cat_servSub.findAllByServCat(solicitudDetalleInstance?.idServcat)}"
    value="${solicitudDetalleInstance?.idServ?.servSub?.id}"
    optionKey='id' noSelection="['':'Seleccione una...']"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'idServ', 'error')} required">
  <label for="idServ">
    <g:message code="cat_bitacora.servicio.label" default="Servicio" />
    <span class="required-indicator">*</span>
  </label>
  <span id="serviciosContainer">
    <g:if test="${solicitudDetalleInstance?.idServ}">
      <g:select id='idServ' name='idServ.id' required=''
        from="${Cat_serv.findAllByServSub(solicitudDetalleInstance?.idServ?.servSub, [order:'id'])}"
        value="${solicitudDetalleInstance?.idServ?.id}"
        optionKey='id' noSelection="['':'Seleccione una...']"/>
    </g:if>
  </span>
</div>

<script>
    function subcategoryChanged(subcategoryId) {
        <g:remoteFunction controller="incidente" action="subcategoryChanged"
            update="serviciosContainer"
            params="'subcategoryId='+subcategoryId"/>
    }
</script>

<div class="fieldtablecontain ${hasErrors(bean: solicitudDetalleInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="solicitudDetalle.descripcion.label" default="Descripción" />
	</label>
	<g:textArea name="descripcion" cols="40" rows="5" maxlength="3000" value="${solicitudDetalleInstance?.descripcion}" disabled="true"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: solicitudDetalleInstance, field: 'idPrograma', 'error')} ">
	<label for="idPrograma">
		<g:message code="solicitudDetalle.idPrograma.label" default="Estado de cierre" />
	</label>
	<g:select id="idPrograma" name="idPrograma.id" from="${CatPrograma.list()}" optionKey="id" value="${solicitudDetalleInstance?.idPrograma?.id}" class="many-to-one" noSelection="['null': '']" disabled="true"/>
</div>

<div class="fieldtablecontain">
  <label for="resguardo">
    <g:message code="solicitudDetalle.idResguardoentregadetalle.label" default="Equipo" />
  </label>
  <g:select id="idResguardoentregadetalle" name="idResguardoentregadetalle"
		from="${ResguardoEntregaDetalle.executeQuery('from ResguardoEntregaDetalle d where exists( from ResguardoEntrega r where r.id = d.idResguardo and r.codigo like ?)', "515%")}" optionKey="id" value="${solicitudDetalleInstance?.idResguardoentregadetalle}" class="many-to-one" disabled="true"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: solicitudDetalleInstance, field: 'descripcionTecnica', 'error')} ">
  <label for="descripcionTecnica">
    <g:message code="solicitudDetalle.descripcionTecnica.label" default="Descripción Técnica" />
  </label>
  <g:textArea name="descripcionTecnica" cols="40" rows="5" maxlength="3000"
    value="${solicitudDetalleInstance?.descripcionTecnica}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: solicitudDetalleInstance, field: 'prioridad', 'error')}">
  <label for="prioridad">
    <g:message code="solicitudDetalle.prioridad.label" default="Prioridad" />
  </label>
  <g:select id="prioridad" name="prioridad" disabled="false" from="${[0, 1, 2, 3]}"
    valueMessagePrefix="intensidad.valor" value="${solicitudDetalleInstance.prioridad}" />
</div>
