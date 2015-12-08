<%@ page import="mx.gob.inr.catservicios.*" %>
<ol class="property-list solicitudDetalle">

  <g:if test="${solicitudDetalleInstance?.idSolicitud}">
  <li class="fieldcontain">
    <span id="idSolicitud-label" class="property-label"><g:message code="solicitudDetalle.idSolicitud.label" default="Id Solicitud" /></span>
      <span class="property-value" aria-labelledby="idSolicitud-label">${solicitudDetalleInstance?.idSolicitud?.encodeAsHTML()}</span>
  </li>
  </g:if>

  <g:if test="${solicitudDetalleInstance?.descripcion}">
  <li class="fieldcontain">
    <span id="descripcion-label" class="property-label"><g:message code="solicitudDetalle.descripcion.label" default="Descripcion" /></span>
      <span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${solicitudDetalleInstance}" field="descripcion"/></span>
  </li>
  </g:if>

  <li class="fieldcontain">
    <span id="justificacion-label" class="property-label">
      <g:message code="solicitudDetalle.justificacion.label" default="Justificación" />
    </span>
    <span class="property-value" aria-labelledby="justificacion-label">
      <g:fieldValue bean="${solicitudDetalleInstance.idSolicitud}" field="justificacion"/>
    </span>
  </li>

  <g:if test="${solicitudDetalleInstance.idSolicitud?.idAutoriza}">
  <li class="fieldcontain">
    <span id="autorizador-label" class="property-label">
      <g:message code="solicitudDetalle.autorizador.label" default="Autorizador" />
    </span>
    <span class="property-value" aria-labelledby="autorizador-label">
      ${Usuario.get(solicitudDetalleInstance?.idSolicitud?.idAutoriza)}
    </span>
  </li>
  </g:if>

  <g:if test="${solicitudDetalleInstance.idSolicitud?.fechaAutoriza}">
  <li class="fieldcontain">
    <span id="fecha-autorizacion-label" class="property-label">
      <g:message code="solicitudDetalle.fecha-autorizacion.label" default="Fecha Autorizacion" />
    </span>
    <span class="property-value" aria-labelledby="fecha-autorizacion-label">
      ${solicitudDetalleInstance?.idSolicitud?.fechaAutoriza}
    </span>
  </li>
  </g:if>

  <g:if test="${solicitudDetalleInstance.idSolicitud?.idVb}">
  <li class="fieldcontain">
    <span id="vobo-label" class="property-label">
      <g:message code="solicitudDetalle.vobo.label" default="Visto bueno" />
    </span>
    <span class="property-value" aria-labelledby="vobo-label">
      ${Usuario.get(solicitudDetalleInstance?.idSolicitud?.idVb)}
    </span>
  </li>
  </g:if>

  <g:if test="${solicitudDetalleInstance.idSolicitud?.fechaVb}">
  <li class="fieldcontain">
    <span id="fecha-vobo-label" class="property-label">
      <g:message code="solicitudDetalle.fecha-vobo.label" default="Fecha de visto bueno" />
    </span>
    <span class="property-value" aria-labelledby="fecha-vobo-label">
      ${solicitudDetalleInstance?.idSolicitud?.fechaVb}
    </span>
  </li>
  </g:if>

  <g:if test="${solicitudDetalleInstance?.idAprobador}">
  <li class="fieldcontain">
    <span id="aprobador-label" class="property-label">
      <g:message code="solicitudDetalle.aprobador.label" default="Aprobador" />
    </span>
    <span class="property-value" aria-labelledby="aprobador-label">
      ${Usuario.get(solicitudDetalleInstance?.idAprobador)}
    </span>
  </li>
  </g:if>

  <g:if test="${solicitudDetalleInstance?.fechaAprobador}">
  <li class="fieldcontain">
    <span id="fecha-aprobacion-label" class="property-label">
      <g:message code="solicitudDetalle.fecha-aprobacion.label" default="Fecha de Aprobación" />
    </span>
    <span class="property-value" aria-labelledby="fecha-aprobacion-label">
      ${solicitudDetalleInstance?.fechaAprobador}
    </span>
  </li>
  </g:if>

  <g:if test="${solicitudDetalleInstance?.idTecnico}">
  <li class="fieldcontain">
    <span id="tecnico-label" class="property-label">
      <g:message code="solicitudDetalle.tecnico.label" default="Técnico" />
    </span>
    <span class="property-value" aria-labelledby="tecnico-label">
      ${Usuario.get(solicitudDetalleInstance?.idTecnico)}
    </span>
  </li>
  </g:if>

  <li class="fieldcontain">
    <span id="prioridad-label" class="property-label">
      <g:message code="solicitudDetalle.prioridad.label" default="Prioridad" />
    </span>
    <span class="property-value" aria-labelledby="prioridad-label">
      <g:message code="intensidad.valor.${solicitudDetalleInstance?.prioridad}" default="Error" />
    </span>
  </li>

  <g:if test="${solicitudDetalleInstance?.idPrograma}">
  <li class="fieldcontain">
    <span id="idPrograma-label" class="property-label">
      <g:message code="solicitudDetalle.idPrograma.label" default="Estado de cierre" />
    </span>
    <span class="property-value" aria-labelledby="idPrograma-label">
      ${solicitudDetalleInstance?.idPrograma?.encodeAsHTML()}
    </span>
  </li>
  </g:if>

  <g:if test="${solicitudDetalleInstance?.solucion}">
  <li class="fieldcontain">
    <span id="solucion-label" class="property-label">
      <g:message code="solicitudDetalle.solucion.label" default="Solución Entregada" />
    </span>
    <span class="property-value" aria-labelledby="solucion-label">
      ${solicitudDetalleInstance?.solucion}
    </span>
  </li>
  </g:if>

  <g:if test="${solicitudDetalleInstance?.fechaSolucion}">
  <li class="fieldcontain">
    <span id="fecha-solucion-label" class="property-label">
      <g:message code="solicitudDetalle.fecha-solucion.label" default="Fecha de Solución Entregada" />
    </span>
    <span class="property-value" aria-labelledby="fecha-solucion-label">
      ${solicitudDetalleInstance?.fechaSolucion}
    </span>
  </li>
  </g:if>

</ol>
