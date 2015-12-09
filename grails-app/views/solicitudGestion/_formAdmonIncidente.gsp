<%@ page import="mx.gob.inr.catservicios.*" %>
<ol class="property-list incidente">

  <li class="fieldcontain">
    <span id="idIncidente-label" class="property-label"><g:message code="incidente.idSolicitud.label" default="Incidente" /></span>
      <span class="property-value" aria-labelledby="idIncidente-label">${incidente?.toString()?.encodeAsHTML()}</span>
  </li>

  <g:if test="${incidente?.idResguardoentregadetalle}">
  <g:set var="equipo"
      value="${ResguardoEntregaDetalle.get(incidente.idResguardoentregadetalle)}" />
  <g:set var="servicios" bean="serviciosService"/>
  <li class="fieldcontain">
    <span class="property-label">Equipo</span>
    <span class="property-value">${equipo.descripcion.tokenize(' ')[0]}</span>
  </li>

  <li class="fieldcontain">
    <span class="property-label">Marca</span>
    <span class="property-value">${servicios.descMarca(equipo['idMarca'])}</span>
  </li>

  <li class="fieldcontain">
    <span class="property-label">Modelo</span>
    <span class="property-value">${equipo['desModelo']}</span>
  </li>

  <li class="fieldcontain">
    <span class="property-label">Serie</span>
    <span class="property-value">${equipo['serie']}</span>
  </li>

  <li class="fieldcontain">
    <span class="property-label">Inventario</span>
    <span class="property-value">${equipo['inventario']}</span>
  </li>
  </g:if>

  <li class="fieldcontain">
    <span class="property-label">Prioridad</span>
    <span class="property-value">
      <g:message code="intensidad.valor.${incidente?.idServ?.impacto}" default="" />
    </span>
  </li>

<g:each var="nivel" in="${1..incidente.nivel}">
  <li class="fieldcontain">
    <span class="property-label">Nivel ${nivel}</span>
    <span class="property-value">
      ${Usuario.get(incidente["idNivel"+nivel])}
    </span>
  </li>

  <li class="fieldcontain">
    <span class="property-label"> Comentarios</span>
    <span class="property-value">
      ${incidente["solucionNivel"+nivel]}
    </span>
  </li>
</g:each>

  <li class="fieldcontain">
    <span class="property-label">Estatus del Servico </span>
    <span class="property-value">
      ${incidente.estado.toString() in ['T','E','C'] ? 'Cerrado' : "Abierto"}
    </span>
  </li>

%{--
Solución Entregada
  <g:if test="${incidente?.idResguardoentregadetalle}">
  <g:set var="equipo"
      value="${ResguardoEntregaDetalle.get(incidente.idResguardoentregadetalle)}" />
  <g:set var="servicios" bean="serviciosService"/>
  <li class="fieldcontain">
    <span class="property-label">Equipo</span>
    <span class="property-value">${equipo.descripcion.tokenize(' ')[0]}</span>
  </li>

  <li class="fieldcontain">
    <span id="justificacion-label" class="property-label">
      <g:message code="solicitudDetalle.justificacion.label" default="Justificación" />
    </span>
    <span class="property-value" aria-labelledby="justificacion-label">
      <g:fieldValue bean="${incidente.idSolicitud}" field="justificacion"/>
    </span>
  </li>

  <g:if test="${incidente.idSolicitud?.idAutoriza}">
  <li class="fieldcontain">
    <span id="autorizador-label" class="property-label">
      <g:message code="solicitudDetalle.autorizador.label" default="Autorizador" />
    </span>
    <span class="property-value" aria-labelledby="autorizador-label">
      ${Usuario.get(incidente?.idSolicitud?.idAutoriza)}
    </span>
  </li>
  </g:if>

  <g:if test="${incidente.idSolicitud?.fechaAutoriza}">
  <li class="fieldcontain">
    <span id="fecha-autorizacion-label" class="property-label">
      <g:message code="solicitudDetalle.fecha-autorizacion.label" default="Fecha Autorizacion" />
    </span>
    <span class="property-value" aria-labelledby="fecha-autorizacion-label">
      ${incidente?.idSolicitud?.fechaAutoriza}
    </span>
  </li>
  </g:if>

  <g:if test="${incidente.idSolicitud?.idVb}">
  <li class="fieldcontain">
    <span id="vobo-label" class="property-label">
      <g:message code="solicitudDetalle.vobo.label" default="Visto bueno" />
    </span>
    <span class="property-value" aria-labelledby="vobo-label">
      ${Usuario.get(incidente?.idSolicitud?.idVb)}
    </span>
  </li>
  </g:if>

  <g:if test="${incidente.idSolicitud?.fechaVb}">
  <li class="fieldcontain">
    <span id="fecha-vobo-label" class="property-label">
      <g:message code="solicitudDetalle.fecha-vobo.label" default="Fecha de visto bueno" />
    </span>
    <span class="property-value" aria-labelledby="fecha-vobo-label">
      ${incidente?.idSolicitud?.fechaVb}
    </span>
  </li>
  </g:if>

  <g:if test="${incidente?.idAprobador}">
  <li class="fieldcontain">
    <span id="aprobador-label" class="property-label">
      <g:message code="solicitudDetalle.aprobador.label" default="Aprobador" />
    </span>
    <span class="property-value" aria-labelledby="aprobador-label">
      ${Usuario.get(incidente?.idAprobador)}
    </span>
  </li>
  </g:if>

  <g:if test="${incidente?.fechaAprobador}">
  <li class="fieldcontain">
    <span id="fecha-aprobacion-label" class="property-label">
      <g:message code="solicitudDetalle.fecha-aprobacion.label" default="Fecha de Aprobación" />
    </span>
    <span class="property-value" aria-labelledby="fecha-aprobacion-label">
      ${incidente?.fechaAprobador}
    </span>
  </li>
  </g:if>

  <g:if test="${incidente?.idTecnico}">
  <li class="fieldcontain">
    <span id="tecnico-label" class="property-label">
      <g:message code="solicitudDetalle.tecnico.label" default="Técnico" />
    </span>
    <span class="property-value" aria-labelledby="tecnico-label">
      ${Usuario.get(incidente?.idTecnico)}
    </span>
  </li>
  </g:if>

  <li class="fieldcontain">
    <span id="prioridad-label" class="property-label">
      <g:message code="solicitudDetalle.prioridad.label" default="Prioridad" />
    </span>
    <span class="property-value" aria-labelledby="prioridad-label">
      <g:message code="intensidad.valor.${incidente?.prioridad}" default="Error" />
    </span>
  </li>

  <g:if test="${incidente?.idPrograma}">
  <li class="fieldcontain">
    <span id="idPrograma-label" class="property-label">
      <g:message code="solicitudDetalle.idPrograma.label" default="Estado de cierre" />
    </span>
    <span class="property-value" aria-labelledby="idPrograma-label">
      ${incidente?.idPrograma?.encodeAsHTML()}
    </span>
  </li>
  </g:if>

  <g:if test="${incidente?.solucion}">
  <li class="fieldcontain">
    <span id="solucion-label" class="property-label">
      <g:message code="solicitudDetalle.solucion.label" default="Solución Entregada" />
    </span>
    <span class="property-value" aria-labelledby="solucion-label">
      ${incidente?.solucion}
    </span>
  </li>
  </g:if>

  <g:if test="${incidente?.fechaSolucion}">
  <li class="fieldcontain">
    <span id="fecha-solucion-label" class="property-label">
      <g:message code="solicitudDetalle.fecha-solucion.label" default="Fecha de Solución Entregada" />
    </span>
    <span class="property-value" aria-labelledby="fecha-solucion-label">
      ${incidente?.fechaSolucion}
    </span>
  </li>
  </g:if>
--}%
</ol>
