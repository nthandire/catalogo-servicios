<%@ page import="mx.gob.inr.catservicios.*" %>


<style type="text/css">
  textArea { width: 412px; }
</style>


<g:set var="firmado" bean="firmadoService"/>
<g:set var="solicitudInstance" value="${solicitudDetalleInstance.idSolicitud}" />
<div class="row-fluid">
  <div class="span4">
    <div class="fieldtablecontain ${hasErrors(bean: solicitudDetalleInstance,
                                              field: 'idServcat', 'error')} ">
      <label for="idServcat">
        <g:message code="solicitudDetalle.idServcat.label" default="Categoría" />
      </label>
      <g:select id="servCat" name="servCat.id" required="true"
        from="${firmado.categoriasSolicitudes()}"
        optionKey="id" class="many-to-one"
        value="${solicitudDetalleInstance.idServcat}"
        onchange="categoryChanged(this.value)"/>
    </div>
  </div>
  <div class="span4">
    <div class="fieldtablecontain ${hasErrors(bean: solicitudDetalleInstance,
                                          field: 'idServ', 'error')} required">
      <label for="idServ">
        <g:message code="cat_serv.servSub.label" default="Serv Sub" />
        <span class="required-indicator">*</span>
      </label>
      <span id="subContainer">
        <g:select id='servSub' name='servSub' required=''
          onchange="subcategoryChanged(this.value)"
          from="${firmado.subcategoriasSolicitudes(solicitudDetalleInstance.idServcat)}"
          value="${solicitudDetalleInstance?.idServ?.servSub?.id}"
          optionKey='id' noSelection="['':'Seleccione una...']"/>
      </span>
    </div>
  </div>
  <div class="span4">
    <div class="fieldtablecontain ${hasErrors(bean: solicitudDetalleInstance,
                                         field: 'idServ', 'error')} required">
      <label for="idServ">
        <g:message code="cat_bitacora.servicio.label" default="Categoría de tercer nivel" />
        <span class="required-indicator">*</span>
      </label>
      <span id="serviciosContainer">
        <g:if test="${solicitudDetalleInstance?.idServ}">
          <g:select id='idServ' name='idServ.id' required=''
            from="${firmado.tercerNivelSolicitudes(solicitudDetalleInstance.idServ.servSub)}"
            value="${solicitudDetalleInstance?.idServ?.id}"
            onchange="servicesChanged(this.value)"
            optionKey='id' noSelection="['':'Seleccione una...']"/>
        </g:if>
      </span>
    </div>
  </div>
</div>

<script>
    function categoryChanged(categoryId) {
      <g:remoteFunction action="categoryChanged"
          update="subContainer"
          params="'categoryId='+categoryId"/>
      <g:remoteFunction action="categoryChanged2"
          update="serviciosContainer"
          params="'categoryId='+categoryId"/>
    }
    function subcategoryChanged(subcategoryId) {
      <g:remoteFunction action="subcategoryChanged"
          update="serviciosContainer"
          params="'subcategoryId='+subcategoryId"/>
    }
    function servicesChanged(servicesId) {
      <g:remoteFunction action="servicesChanged"
          update="prioridadContainer"
          params="'servicesId='+servicesId"/>
    }
</script>

<div class="row-fluid">
  <div class="span4">
    <div class="fieldtablecontain">
      <label for="nombre-label">
        <g:message code="solicitud.nombre.label" default="Solicitante"/>
      </label>
      <g:field type="text" name="nombre.no" disabled="true"
        value="${Usuario.get(solicitudInstance.idSolicitante)}"/>
    </div>
  </div>
  <div class="span3">
    <div class="fieldtablecontain">
      <label for="telefono-label">
        Fecha de solicitud
      </label>
      <%--
      <input name="solicitudInstance.fechaSolicitud" disabled="true"
        value="${solicitudInstance.fechaSolicitud}"/>
      --%>
      <g:field type="datetime" disabled="true"
        name="solicitudInstance.fechaSolicitud"
        value="$solicitudInstance.fechaSolicitud"/>
    </div>
  </div>
  <div class="span2">
    <div class="fieldtablecontain">
      <label for="telefono-label">
        <g:message code="solicitud.telefono.label" default="Extensión" />
      </label>
      <g:field type="text" name="telefono.no" disabled="true"
        value="${Usuario.get(solicitudInstance.idSolicitante).extension}"/>
    </div>
  </div>
  <div class="span3">
    <div class="fieldtablecontain">
      <label for="area-label">
        <g:message code="solicitud.area.label" default="Área" />
      </label>
      <g:field type="text" name="area.no" disabled="true"
        value="${firmado.areaNombre(solicitudInstance.idSolicitante)}"/>
    </div>
  </div>
</div>

<div class="row-fluid">
  <div class="span4">
    <div class="fieldtablecontain">
      <label for="nombre-label">
        <g:message code="solicitud.nombre.label" default="Autorizador"/>
      </label>
      <g:field type="text" name="nombre.no" disabled="true"
        value="${Usuario.get(solicitudInstance.idAutoriza)}"/>
    </div>
  </div>
  <div class="span3">
    <div class="fieldtablecontain">
      <label for="fAutoriza-label">
        Fecha de autorización
      </label>
      <g:field type="datetime" name="fAutoriza.no" disabled="true"
        value="${solicitudInstance.fechaAutoriza}"/>
    </div>
  </div>
  <div class="span2">
    <div class="fieldtablecontain">
      <label for="telefono-label">
        <g:message code="solicitud.telefono.label" default="Extensión" />
      </label>
      <g:field type="text" name="telefono.no" disabled="true"
        value="${Usuario.get(solicitudInstance.idAutoriza).extension}"/>
    </div>
  </div>
  <div class="span3">
    <div class="fieldtablecontain">
      <label for="area-label">
        <g:message code="solicitud.area.label" default="Área" />
      </label>
      <g:field type="text" name="area.no" disabled="true"
        value="${firmado.areaNombre(solicitudInstance?.idAutoriza)}"/>
    </div>
  </div>
</div>

<g:if test="${solicitudInstance.idVb}">
  <div class="row-fluid">
    <div class="span4">
      <div class="fieldtablecontain">
        <label for="nombre-label">
          <g:message code="solicitud.nombre.label" default="Visto Bueno"/>
        </label>
        <g:field type="text" name="nombre.no" disabled="true"
          value="${Usuario.get(solicitudInstance.idVb)}"/>
      </div>
    </div>
    <div class="span3">
      <div class="fieldtablecontain">
        <label for="fVoBo-label">
          Fecha de Visto Bueno
        </label>
        <g:field type="datetime" name="fVoBo.no" disabled="true"
          value="${solicitudInstance.fechaVb}"/>
      </div>
    </div>
    <div class="span2">
      <div class="fieldtablecontain">
        <label for="telefono-label">
          <g:message code="solicitud.telefono.label" default="Extensión" />
        </label>
        <g:field type="text" name="telefono.no" disabled="true"
          value="${Usuario.get(solicitudInstance.idVb).extension}"/>
      </div>
    </div>
    <div class="span3">
      <div class="fieldtablecontain">
        <label for="area-label">
          <g:message code="solicitud.area.label" default="Área" />
        </label>
        <g:field type="text" name="area.no" disabled="true"
          value="${firmado.areaNombre(solicitudInstance?.idVb)}"/>
      </div>
    </div>
  </div>
</g:if>

<div class="row-fluid">
  <div class="span4">
    <div class="fieldtablecontain ${hasErrors(bean: solicitudDetalleInstance, field: 'descripcion', 'error')} ">
      <label for="descripción">
        <g:message code="solicitudDetalle.descripcion.label" default="Descripcion" />
      </label>
      <g:textArea name="descripcion" cols="40" rows="5" maxlength="3000"
      value="${solicitudDetalleInstance?.descripcion}" disabled="true"/>
    </div>
  </div>
  <div class="span4">
    <div class="fieldtablecontain ${hasErrors(bean: solicitudDetalleInstance,
                                              field: 'idPrograma', 'error')} ">
      <label for="idPrograma">
        <g:message code="solicitudDetalle.idPrograma.label" default="Estado de cierre" />
      </label>
      <g:field id="idPrograma" name="idPrograma.id" type="text"
        value="${solicitudDetalleInstance?.idPrograma}" disabled="true"/>
    </div>
  </div>
  <div class="span4">
    <g:if test="${solicitudDetalleInstance?.idResguardoentregadetalle}">
      <div class="fieldtablecontain">
        <label for="resguardo">
          <g:message code="solicitudDetalle.idResguardoentregadetalle.label" default="Equipo" />
        </label>
        <g:select id="idResguardoentregadetalle" name="idResguardoentregadetalle"
          from="${ResguardoEntregaDetalle.executeQuery('from ResguardoEntregaDetalle d where exists( from ResguardoEntrega r where r.id = d.idResguardo and r.codigo like ?)', "515%")}"
            optionKey="id" value="${solicitudDetalleInstance?.idResguardoentregadetalle}"
            class="many-to-one" disabled="true"/>
      </div>
    </g:if>
  </div>
</div>

<div class="row-fluid">
  <div class="span4">
    <div class="fieldtablecontain ${hasErrors(bean: solicitudDetalleInstance, field: 'descripcionTecnica', 'error')} ">
      <label for="descripcionTecnica">
        <g:message code="solicitudDetalle.descripcionTecnica.label" default="Descripción Técnica" />
      </label>
      <g:textArea name="descripcionTecnica" cols="40" rows="5" maxlength="3000"
        value="${solicitudDetalleInstance?.descripcionTecnica}"/>
    </div>
  </div>
  <div class="span4">
    <g:if test="${solicitudDetalleInstance?.idTecnico}">
      <div class="fieldtablecontain ${hasErrors(bean: solicitudDetalleInstance,
                                                field: 'idTecnico', 'error')} ">
        <label for="idTecnico">
          <g:message code="solicitudDetalle.idTecnico.label" default="Tecnico" />
        </label>
        <g:field  id="idTecnico" name="idTecnico" disabled="true"
          value="${firmado.usuarioNombre(solicitudDetalleInstance?.idTecnico)}"/>
      </div>
    </g:if>
  </div>
  <div class="span4">
    <div class="fieldtablecontain ${hasErrors(bean: solicitudDetalleInstance, field: 'prioridad', 'error')}">
      <label for="prioridad">
        <g:message code="solicitudDetalle.prioridad.label" default="Prioridad" />
      </label>
      <span id="prioridadContainer">
        <g:select id="prioridad" name="prioridad" from="${[0, 1, 2, 3]}"
          valueMessagePrefix="intensidad.valor"
          value="${solicitudDetalleInstance.prioridad?:solicitudDetalleInstance?.idServ?.impacto?:0}"/>
      </span>
    </div>
  </div>
</div>
