
<%@ page import="mx.gob.inr.catservicios.IncidenteLaboratorio" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'incidenteLaboratorio.label', default: 'IncidenteLaboratorio')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-incidenteLaboratorio" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-incidenteLaboratorio" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list incidenteLaboratorio">
			
				<g:if test="${incidenteLaboratorioInstance?.idIncidente}">
				<li class="fieldcontain">
					<span id="idIncidente-label" class="property-label"><g:message code="incidenteLaboratorio.idIncidente.label" default="Id Incidente" /></span>
					
						<span class="property-value" aria-labelledby="idIncidente-label"><g:fieldValue bean="${incidenteLaboratorioInstance}" field="idIncidente"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${incidenteLaboratorioInstance?.fechaLaboratorio}">
				<li class="fieldcontain">
					<span id="fechaLaboratorio-label" class="property-label"><g:message code="incidenteLaboratorio.fechaLaboratorio.label" default="Fecha Laboratorio" /></span>
					
						<span class="property-value" aria-labelledby="fechaLaboratorio-label"><g:formatDate date="${incidenteLaboratorioInstance?.fechaLaboratorio}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${incidenteLaboratorioInstance?.numeroLaboratorio}">
				<li class="fieldcontain">
					<span id="numeroLaboratorio-label" class="property-label"><g:message code="incidenteLaboratorio.numeroLaboratorio.label" default="Numero Laboratorio" /></span>
					
						<span class="property-value" aria-labelledby="numeroLaboratorio-label"><g:fieldValue bean="${incidenteLaboratorioInstance}" field="numeroLaboratorio"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${incidenteLaboratorioInstance?.idEstado}">
				<li class="fieldcontain">
					<span id="idEstado-label" class="property-label"><g:message code="incidenteLaboratorio.idEstado.label" default="Id Estado" /></span>
					
						<span class="property-value" aria-labelledby="idEstado-label"><g:fieldValue bean="${incidenteLaboratorioInstance}" field="idEstado"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${incidenteLaboratorioInstance?.idTiposervicio}">
				<li class="fieldcontain">
					<span id="idTiposervicio-label" class="property-label"><g:message code="incidenteLaboratorio.idTiposervicio.label" default="Id Tiposervicio" /></span>
					
						<span class="property-value" aria-labelledby="idTiposervicio-label"><g:fieldValue bean="${incidenteLaboratorioInstance}" field="idTiposervicio"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${incidenteLaboratorioInstance?.fallaTecnica}">
				<li class="fieldcontain">
					<span id="fallaTecnica-label" class="property-label"><g:message code="incidenteLaboratorio.fallaTecnica.label" default="Falla Tecnica" /></span>
					
						<span class="property-value" aria-labelledby="fallaTecnica-label"><g:fieldValue bean="${incidenteLaboratorioInstance}" field="fallaTecnica"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${incidenteLaboratorioInstance?.solucion}">
				<li class="fieldcontain">
					<span id="solucion-label" class="property-label"><g:message code="incidenteLaboratorio.solucion.label" default="Solucion" /></span>
					
						<span class="property-value" aria-labelledby="solucion-label"><g:fieldValue bean="${incidenteLaboratorioInstance}" field="solucion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${incidenteLaboratorioInstance?.refacciones}">
				<li class="fieldcontain">
					<span id="refacciones-label" class="property-label"><g:message code="incidenteLaboratorio.refacciones.label" default="Refacciones" /></span>
					
						<span class="property-value" aria-labelledby="refacciones-label"><g:fieldValue bean="${incidenteLaboratorioInstance}" field="refacciones"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${incidenteLaboratorioInstance?.idUsuario}">
				<li class="fieldcontain">
					<span id="idUsuario-label" class="property-label"><g:message code="incidenteLaboratorio.idUsuario.label" default="Id Usuario" /></span>
					
						<span class="property-value" aria-labelledby="idUsuario-label"><g:fieldValue bean="${incidenteLaboratorioInstance}" field="idUsuario"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${incidenteLaboratorioInstance?.idProveedor}">
				<li class="fieldcontain">
					<span id="idProveedor-label" class="property-label"><g:message code="incidenteLaboratorio.idProveedor.label" default="Id Proveedor" /></span>
					
						<span class="property-value" aria-labelledby="idProveedor-label"><g:fieldValue bean="${incidenteLaboratorioInstance}" field="idProveedor"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${incidenteLaboratorioInstance?.fechaReporte}">
				<li class="fieldcontain">
					<span id="fechaReporte-label" class="property-label"><g:message code="incidenteLaboratorio.fechaReporte.label" default="Fecha Reporte" /></span>
					
						<span class="property-value" aria-labelledby="fechaReporte-label"><g:formatDate date="${incidenteLaboratorioInstance?.fechaReporte}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${incidenteLaboratorioInstance?.numeroReporte}">
				<li class="fieldcontain">
					<span id="numeroReporte-label" class="property-label"><g:message code="incidenteLaboratorio.numeroReporte.label" default="Numero Reporte" /></span>
					
						<span class="property-value" aria-labelledby="numeroReporte-label"><g:fieldValue bean="${incidenteLaboratorioInstance}" field="numeroReporte"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${incidenteLaboratorioInstance?.fechaSalida}">
				<li class="fieldcontain">
					<span id="fechaSalida-label" class="property-label"><g:message code="incidenteLaboratorio.fechaSalida.label" default="Fecha Salida" /></span>
					
						<span class="property-value" aria-labelledby="fechaSalida-label"><g:formatDate date="${incidenteLaboratorioInstance?.fechaSalida}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${incidenteLaboratorioInstance?.fechaEntrega}">
				<li class="fieldcontain">
					<span id="fechaEntrega-label" class="property-label"><g:message code="incidenteLaboratorio.fechaEntrega.label" default="Fecha Entrega" /></span>
					
						<span class="property-value" aria-labelledby="fechaEntrega-label"><g:formatDate date="${incidenteLaboratorioInstance?.fechaEntrega}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${incidenteLaboratorioInstance?.fechaReparacion}">
				<li class="fieldcontain">
					<span id="fechaReparacion-label" class="property-label"><g:message code="incidenteLaboratorio.fechaReparacion.label" default="Fecha Reparacion" /></span>
					
						<span class="property-value" aria-labelledby="fechaReparacion-label"><g:formatDate date="${incidenteLaboratorioInstance?.fechaReparacion}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${incidenteLaboratorioInstance?.tecnico}">
				<li class="fieldcontain">
					<span id="tecnico-label" class="property-label"><g:message code="incidenteLaboratorio.tecnico.label" default="Tecnico" /></span>
					
						<span class="property-value" aria-labelledby="tecnico-label"><g:fieldValue bean="${incidenteLaboratorioInstance}" field="tecnico"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${incidenteLaboratorioInstance?.observaciones}">
				<li class="fieldcontain">
					<span id="observaciones-label" class="property-label"><g:message code="incidenteLaboratorio.observaciones.label" default="Observaciones" /></span>
					
						<span class="property-value" aria-labelledby="observaciones-label"><g:fieldValue bean="${incidenteLaboratorioInstance}" field="observaciones"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${incidenteLaboratorioInstance?.fechaModificacion}">
				<li class="fieldcontain">
					<span id="fechaModificacion-label" class="property-label"><g:message code="incidenteLaboratorio.fechaModificacion.label" default="Fecha Modificacion" /></span>
					
						<span class="property-value" aria-labelledby="fechaModificacion-label"><g:formatDate date="${incidenteLaboratorioInstance?.fechaModificacion}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${incidenteLaboratorioInstance?.ipTerminal}">
				<li class="fieldcontain">
					<span id="ipTerminal-label" class="property-label"><g:message code="incidenteLaboratorio.ipTerminal.label" default="Ip Terminal" /></span>
					
						<span class="property-value" aria-labelledby="ipTerminal-label"><g:fieldValue bean="${incidenteLaboratorioInstance}" field="ipTerminal"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${incidenteLaboratorioInstance?.id}" />
					<g:link class="edit" action="edit" id="${incidenteLaboratorioInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<!--g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /-->
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
