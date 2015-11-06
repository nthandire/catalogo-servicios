
<%@ page import="mx.gob.inr.catservicios.SolicitudDetalle" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'solicitudDetalle.label', default: 'SolicitudDetalle')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-solicitudDetalle" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
    <g:set var="firmado" bean="firmadoService"/>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="back" action="show" id="${solicitudDetalleInstance?.idSolicitud?.id}">Regresar al requerimiento</g:link></li>
			</ul>
		</div>
		<div id="show-solicitudDetalle" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			  <div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:if test="${flash.error}">
			  <div class="errors" role="status">${flash.error}</div>
			</g:if>
			<ol class="property-list solicitudDetalle">

				<g:if test="${solicitudDetalleInstance?.idSolicitud}">
				<li class="fieldcontain">
					<span id="idSolicitud-label" class="property-label"><g:message code="solicitudDetalle.idSolicitud.label" default="Nùmero de Solicitud" /></span>
					<span class="property-value" aria-labelledby="idSolicitud-label">
            ${solicitudDetalleInstance?.idSolicitud?.encodeAsHTML()}
          </span>
				</li>
				</g:if>

        <g:if test="${solicitudDetalleInstance?.idServcat}">
        <li class="fieldcontain">
          <span id="idServcat-label" class="property-label"><g:message code="solicitudDetalle.idServcat.label" default="Categoría" /></span>
            <span class="property-value" aria-labelledby="idServcat-label">${solicitudDetalleInstance?.idServcat?.encodeAsHTML()}</span>
        </li>
        </g:if>

        <g:if test="${solicitudDetalleInstance?.idServ}">
        <li class="fieldcontain">
          <span id="idServcat-label" class="property-label"><g:message code="solicitudDetalle.idServcat.label" default="Subategoría" /></span>
            <span class="property-value" aria-labelledby="idServcat-label">${solicitudDetalleInstance?.idServ?.servSub.encodeAsHTML()}</span>
        </li>

        <li class="fieldcontain">
          <span id="idServ-label" class="property-label"><g:message code="solicitudDetalle.idServ.label" default="Categoría de Tercer nivel" /></span>
            <span class="property-value" aria-labelledby="idServ-label">${solicitudDetalleInstance?.idServ?.encodeAsHTML()}</span>
        </li>
        </g:if>

        <g:if test="${solicitudDetalleInstance?.idResguardoentregadetalle}">
        <li class="fieldcontain">
          <span id="idResguardoentregadetalle-label" class="property-label"><g:message code="solicitudDetalle.idResguardoentregadetalle.label" default="Equipo" /></span>
            <span class="property-value" aria-labelledby="idResguardoentregadetalle-label">
              ${firmado.equipo(solicitudDetalleInstance.idResguardoentregadetalle)}
            </span>
        </li>
        </g:if>

        <g:if test="${solicitudDetalleInstance?.descripcion}">
        <li class="fieldcontain">
          <span id="descripcion-label" class="property-label"><g:message code="solicitudDetalle.descripcion.label" default="Descripcion" /></span>
            <span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${solicitudDetalleInstance}" field="descripcion"/></span>
        </li>
        </g:if>

        <g:if test="${solicitudDetalleInstance?.descripcionTecnica}">
        <li class="fieldcontain">
          <span id="descripcionTecnica-label" class="property-label">
            <g:message code="solicitudDetalle.descripcionTecnica.label"
              default="Descripción Técnica" />
          </span>
          <span class="property-value" aria-labelledby="descripcionTecnica-label">
            <g:fieldValue bean="${solicitudDetalleInstance}" field="descripcionTecnica"/>
          </span>
        </li>
        </g:if>

        <g:if test="${solicitudDetalleInstance?.idPrograma}">
        <li class="fieldcontain">
          <span id="idPrograma-label" class="property-label"><g:message code="solicitudDetalle.idPrograma.label" default="Estado de cierre" /></span>
            <span class="property-value" aria-labelledby="idPrograma-label">${solicitudDetalleInstance?.idPrograma?.encodeAsHTML()}</span>
        </li>
        </g:if>

			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${solicitudDetalleInstance?.id}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
