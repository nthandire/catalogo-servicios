
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
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-solicitudDetalle" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list solicitudDetalle">
			
				<g:if test="${solicitudDetalleInstance?.idSolicitud}">
				<li class="fieldcontain">
					<span id="idSolicitud-label" class="property-label"><g:message code="solicitudDetalle.idSolicitud.label" default="Id Solicitud" /></span>
					
						<span class="property-value" aria-labelledby="idSolicitud-label"><g:link controller="solicitud" action="show" id="${solicitudDetalleInstance?.idSolicitud?.id}">${solicitudDetalleInstance?.idSolicitud?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudDetalleInstance?.idServ}">
				<li class="fieldcontain">
					<span id="idServ-label" class="property-label"><g:message code="solicitudDetalle.idServ.label" default="Id Serv" /></span>
					
						<span class="property-value" aria-labelledby="idServ-label"><g:link controller="cat_serv" action="show" id="${solicitudDetalleInstance?.idServ?.id}">${solicitudDetalleInstance?.idServ?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudDetalleInstance?.idResguardoentregadetalle}">
				<li class="fieldcontain">
					<span id="idResguardoentregadetalle-label" class="property-label"><g:message code="solicitudDetalle.idResguardoentregadetalle.label" default="Id Resguardoentregadetalle" /></span>
					
						<span class="property-value" aria-labelledby="idResguardoentregadetalle-label"><g:fieldValue bean="${solicitudDetalleInstance}" field="idResguardoentregadetalle"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudDetalleInstance?.estadoSolictuddetalle}">
				<li class="fieldcontain">
					<span id="estadoSolictuddetalle-label" class="property-label"><g:message code="solicitudDetalle.estadoSolictuddetalle.label" default="Estado Solictuddetalle" /></span>
					
						<span class="property-value" aria-labelledby="estadoSolictuddetalle-label"><g:fieldValue bean="${solicitudDetalleInstance}" field="estadoSolictuddetalle"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudDetalleInstance?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="solicitudDetalle.descripcion.label" default="Descripcion" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${solicitudDetalleInstance}" field="descripcion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudDetalleInstance?.solucion}">
				<li class="fieldcontain">
					<span id="solucion-label" class="property-label"><g:message code="solicitudDetalle.solucion.label" default="Solucion" /></span>
					
						<span class="property-value" aria-labelledby="solucion-label"><g:fieldValue bean="${solicitudDetalleInstance}" field="solucion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudDetalleInstance?.idTecnico}">
				<li class="fieldcontain">
					<span id="idTecnico-label" class="property-label"><g:message code="solicitudDetalle.idTecnico.label" default="Id Tecnico" /></span>
					
						<span class="property-value" aria-labelledby="idTecnico-label"><g:fieldValue bean="${solicitudDetalleInstance}" field="idTecnico"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudDetalleInstance?.fechaSolucion}">
				<li class="fieldcontain">
					<span id="fechaSolucion-label" class="property-label"><g:message code="solicitudDetalle.fechaSolucion.label" default="Fecha Solucion" /></span>
					
						<span class="property-value" aria-labelledby="fechaSolucion-label"><g:formatDate date="${solicitudDetalleInstance?.fechaSolucion}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudDetalleInstance?.idPrograma}">
				<li class="fieldcontain">
					<span id="idPrograma-label" class="property-label"><g:message code="solicitudDetalle.idPrograma.label" default="Id Programa" /></span>
					
						<span class="property-value" aria-labelledby="idPrograma-label"><g:link controller="catPrograma" action="show" id="${solicitudDetalleInstance?.idPrograma?.id}">${solicitudDetalleInstance?.idPrograma?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudDetalleInstance?.idServcat}">
				<li class="fieldcontain">
					<span id="idServcat-label" class="property-label"><g:message code="solicitudDetalle.idServcat.label" default="Id Servcat" /></span>
					
						<span class="property-value" aria-labelledby="idServcat-label"><g:link controller="cat_servCat" action="show" id="${solicitudDetalleInstance?.idServcat?.id}">${solicitudDetalleInstance?.idServcat?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${solicitudDetalleInstance?.descripcionTecnica}">
				<li class="fieldcontain">
					<span id="descripcionTecnica-label" class="property-label"><g:message code="solicitudDetalle.descripcionTecnica.label" default="Descripcion Tecnica" /></span>
					
						<span class="property-value" aria-labelledby="descripcionTecnica-label"><g:fieldValue bean="${solicitudDetalleInstance}" field="descripcionTecnica"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${solicitudDetalleInstance?.id}" />
					<g:link class="edit" action="edit" id="${solicitudDetalleInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<!--g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /-->
				</fieldset>
			</g:form>
		</div>
	</body>
</html>