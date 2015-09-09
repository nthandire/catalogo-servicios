
<%@ page import="mx.gob.inr.catservicios.Incidente" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'incidente.label', default: 'Incidente')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-incidente" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-incidente" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list incidente">
			
				<g:if test="${incidenteInstance?.idSistema}">
				<li class="fieldcontain">
					<span id="idSistema-label" class="property-label"><g:message code="incidente.idSistema.label" default="Id Sistema" /></span>
					
						<span class="property-value" aria-labelledby="idSistema-label"><g:fieldValue bean="${incidenteInstance}" field="idSistema"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${incidenteInstance?.idResguardoentregadetalle}">
				<li class="fieldcontain">
					<span id="idResguardoentregadetalle-label" class="property-label"><g:message code="incidente.idResguardoentregadetalle.label" default="Id Resguardoentregadetalle" /></span>
					
						<span class="property-value" aria-labelledby="idResguardoentregadetalle-label"><g:fieldValue bean="${incidenteInstance}" field="idResguardoentregadetalle"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${incidenteInstance?.fechaIncidente}">
				<li class="fieldcontain">
					<span id="fechaIncidente-label" class="property-label"><g:message code="incidente.fechaIncidente.label" default="Fecha Incidente" /></span>
					
						<span class="property-value" aria-labelledby="fechaIncidente-label"><g:formatDate date="${incidenteInstance?.fechaIncidente}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${incidenteInstance?.numeroIncidente}">
				<li class="fieldcontain">
					<span id="numeroIncidente-label" class="property-label"><g:message code="incidente.numeroIncidente.label" default="Numero Incidente" /></span>
					
						<span class="property-value" aria-labelledby="numeroIncidente-label"><g:fieldValue bean="${incidenteInstance}" field="numeroIncidente"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${incidenteInstance?.estadoIncidente}">
				<li class="fieldcontain">
					<span id="estadoIncidente-label" class="property-label"><g:message code="incidente.estadoIncidente.label" default="Estado Incidente" /></span>
					
						<span class="property-value" aria-labelledby="estadoIncidente-label"><g:fieldValue bean="${incidenteInstance}" field="estadoIncidente"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${incidenteInstance?.idReporta}">
				<li class="fieldcontain">
					<span id="idReporta-label" class="property-label"><g:message code="incidente.idReporta.label" default="Id Reporta" /></span>
					
						<span class="property-value" aria-labelledby="idReporta-label"><g:fieldValue bean="${incidenteInstance}" field="idReporta"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${incidenteInstance?.idServ}">
				<li class="fieldcontain">
					<span id="idServ-label" class="property-label"><g:message code="incidente.idServ.label" default="Id Serv" /></span>
					
						<span class="property-value" aria-labelledby="idServ-label"><g:fieldValue bean="${incidenteInstance}" field="idServ"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${incidenteInstance?.idServfinal}">
				<li class="fieldcontain">
					<span id="idServfinal-label" class="property-label"><g:message code="incidente.idServfinal.label" default="Id Servfinal" /></span>
					
						<span class="property-value" aria-labelledby="idServfinal-label"><g:fieldValue bean="${incidenteInstance}" field="idServfinal"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${incidenteInstance?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="incidente.descripcion.label" default="Descripcion" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${incidenteInstance}" field="descripcion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${incidenteInstance?.nivel}">
				<li class="fieldcontain">
					<span id="nivel-label" class="property-label"><g:message code="incidente.nivel.label" default="Nivel" /></span>
					
						<span class="property-value" aria-labelledby="nivel-label"><g:fieldValue bean="${incidenteInstance}" field="nivel"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${incidenteInstance?.idServresp}">
				<li class="fieldcontain">
					<span id="idServresp-label" class="property-label"><g:message code="incidente.idServresp.label" default="Id Servresp" /></span>
					
						<span class="property-value" aria-labelledby="idServresp-label"><g:fieldValue bean="${incidenteInstance}" field="idServresp"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${incidenteInstance?.idCaptura}">
				<li class="fieldcontain">
					<span id="idCaptura-label" class="property-label"><g:message code="incidente.idCaptura.label" default="Id Captura" /></span>
					
						<span class="property-value" aria-labelledby="idCaptura-label"><g:fieldValue bean="${incidenteInstance}" field="idCaptura"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${incidenteInstance?.idNivel1}">
				<li class="fieldcontain">
					<span id="idNivel1-label" class="property-label"><g:message code="incidente.idNivel1.label" default="Id Nivel1" /></span>
					
						<span class="property-value" aria-labelledby="idNivel1-label"><g:fieldValue bean="${incidenteInstance}" field="idNivel1"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${incidenteInstance?.fechaNivel1}">
				<li class="fieldcontain">
					<span id="fechaNivel1-label" class="property-label"><g:message code="incidente.fechaNivel1.label" default="Fecha Nivel1" /></span>
					
						<span class="property-value" aria-labelledby="fechaNivel1-label"><g:formatDate date="${incidenteInstance?.fechaNivel1}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${incidenteInstance?.firmaNivel1}">
				<li class="fieldcontain">
					<span id="firmaNivel1-label" class="property-label"><g:message code="incidente.firmaNivel1.label" default="Firma Nivel1" /></span>
					
						<span class="property-value" aria-labelledby="firmaNivel1-label"><g:checkBox name="${incidenteInstance?.firmaNivel1}" value="${incidenteInstance?.firmaNivel1}"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${incidenteInstance?.solucionNivel1}">
				<li class="fieldcontain">
					<span id="solucionNivel1-label" class="property-label"><g:message code="incidente.solucionNivel1.label" default="Solucion Nivel1" /></span>
					
						<span class="property-value" aria-labelledby="solucionNivel1-label"><g:fieldValue bean="${incidenteInstance}" field="solucionNivel1"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${incidenteInstance?.fechaSolnivel1}">
				<li class="fieldcontain">
					<span id="fechaSolnivel1-label" class="property-label"><g:message code="incidente.fechaSolnivel1.label" default="Fecha Solnivel1" /></span>
					
						<span class="property-value" aria-labelledby="fechaSolnivel1-label"><g:formatDate date="${incidenteInstance?.fechaSolnivel1}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${incidenteInstance?.idAsignanivel2}">
				<li class="fieldcontain">
					<span id="idAsignanivel2-label" class="property-label"><g:message code="incidente.idAsignanivel2.label" default="Id Asignanivel2" /></span>
					
						<span class="property-value" aria-labelledby="idAsignanivel2-label"><g:fieldValue bean="${incidenteInstance}" field="idAsignanivel2"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${incidenteInstance?.idNivel2}">
				<li class="fieldcontain">
					<span id="idNivel2-label" class="property-label"><g:message code="incidente.idNivel2.label" default="Id Nivel2" /></span>
					
						<span class="property-value" aria-labelledby="idNivel2-label"><g:fieldValue bean="${incidenteInstance}" field="idNivel2"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${incidenteInstance?.fechaNivel2}">
				<li class="fieldcontain">
					<span id="fechaNivel2-label" class="property-label"><g:message code="incidente.fechaNivel2.label" default="Fecha Nivel2" /></span>
					
						<span class="property-value" aria-labelledby="fechaNivel2-label"><g:formatDate date="${incidenteInstance?.fechaNivel2}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${incidenteInstance?.firmaNivel2}">
				<li class="fieldcontain">
					<span id="firmaNivel2-label" class="property-label"><g:message code="incidente.firmaNivel2.label" default="Firma Nivel2" /></span>
					
						<span class="property-value" aria-labelledby="firmaNivel2-label"><g:checkBox name="${incidenteInstance?.firmaNivel2}" value="${incidenteInstance?.firmaNivel2}"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${incidenteInstance?.solucionNivel2}">
				<li class="fieldcontain">
					<span id="solucionNivel2-label" class="property-label"><g:message code="incidente.solucionNivel2.label" default="Solucion Nivel2" /></span>
					
						<span class="property-value" aria-labelledby="solucionNivel2-label"><g:fieldValue bean="${incidenteInstance}" field="solucionNivel2"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${incidenteInstance?.fechaSolnivel2}">
				<li class="fieldcontain">
					<span id="fechaSolnivel2-label" class="property-label"><g:message code="incidente.fechaSolnivel2.label" default="Fecha Solnivel2" /></span>
					
						<span class="property-value" aria-labelledby="fechaSolnivel2-label"><g:formatDate date="${incidenteInstance?.fechaSolnivel2}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${incidenteInstance?.idAsignanivel3}">
				<li class="fieldcontain">
					<span id="idAsignanivel3-label" class="property-label"><g:message code="incidente.idAsignanivel3.label" default="Id Asignanivel3" /></span>
					
						<span class="property-value" aria-labelledby="idAsignanivel3-label"><g:fieldValue bean="${incidenteInstance}" field="idAsignanivel3"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${incidenteInstance?.idNivel3}">
				<li class="fieldcontain">
					<span id="idNivel3-label" class="property-label"><g:message code="incidente.idNivel3.label" default="Id Nivel3" /></span>
					
						<span class="property-value" aria-labelledby="idNivel3-label"><g:fieldValue bean="${incidenteInstance}" field="idNivel3"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${incidenteInstance?.fechaNivel3}">
				<li class="fieldcontain">
					<span id="fechaNivel3-label" class="property-label"><g:message code="incidente.fechaNivel3.label" default="Fecha Nivel3" /></span>
					
						<span class="property-value" aria-labelledby="fechaNivel3-label"><g:formatDate date="${incidenteInstance?.fechaNivel3}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${incidenteInstance?.firmaNivel3}">
				<li class="fieldcontain">
					<span id="firmaNivel3-label" class="property-label"><g:message code="incidente.firmaNivel3.label" default="Firma Nivel3" /></span>
					
						<span class="property-value" aria-labelledby="firmaNivel3-label"><g:checkBox name="${incidenteInstance?.firmaNivel3}" value="${incidenteInstance?.firmaNivel3}"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${incidenteInstance?.solucionNivel3}">
				<li class="fieldcontain">
					<span id="solucionNivel3-label" class="property-label"><g:message code="incidente.solucionNivel3.label" default="Solucion Nivel3" /></span>
					
						<span class="property-value" aria-labelledby="solucionNivel3-label"><g:fieldValue bean="${incidenteInstance}" field="solucionNivel3"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${incidenteInstance?.fechaSolnivel3}">
				<li class="fieldcontain">
					<span id="fechaSolnivel3-label" class="property-label"><g:message code="incidente.fechaSolnivel3.label" default="Fecha Solnivel3" /></span>
					
						<span class="property-value" aria-labelledby="fechaSolnivel3-label"><g:formatDate date="${incidenteInstance?.fechaSolnivel3}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${incidenteInstance?.p01}">
				<li class="fieldcontain">
					<span id="p01-label" class="property-label"><g:message code="incidente.p01.label" default="P01" /></span>
					
						<span class="property-value" aria-labelledby="p01-label"><g:fieldValue bean="${incidenteInstance}" field="p01"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${incidenteInstance?.p02}">
				<li class="fieldcontain">
					<span id="p02-label" class="property-label"><g:message code="incidente.p02.label" default="P02" /></span>
					
						<span class="property-value" aria-labelledby="p02-label"><g:fieldValue bean="${incidenteInstance}" field="p02"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${incidenteInstance?.p03}">
				<li class="fieldcontain">
					<span id="p03-label" class="property-label"><g:message code="incidente.p03.label" default="P03" /></span>
					
						<span class="property-value" aria-labelledby="p03-label"><g:fieldValue bean="${incidenteInstance}" field="p03"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${incidenteInstance?.p04}">
				<li class="fieldcontain">
					<span id="p04-label" class="property-label"><g:message code="incidente.p04.label" default="P04" /></span>
					
						<span class="property-value" aria-labelledby="p04-label"><g:fieldValue bean="${incidenteInstance}" field="p04"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${incidenteInstance?.idPrograma}">
				<li class="fieldcontain">
					<span id="idPrograma-label" class="property-label"><g:message code="incidente.idPrograma.label" default="Id Programa" /></span>
					
						<span class="property-value" aria-labelledby="idPrograma-label"><g:fieldValue bean="${incidenteInstance}" field="idPrograma"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${incidenteInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="incidente.lastUpdated.label" default="Fecha Modificacion" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${incidenteInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${incidenteInstance?.ipTerminal}">
				<li class="fieldcontain">
					<span id="ipTerminal-label" class="property-label"><g:message code="incidente.ipTerminal.label" default="Ip Terminal" /></span>
					
						<span class="property-value" aria-labelledby="ipTerminal-label"><g:fieldValue bean="${incidenteInstance}" field="ipTerminal"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${incidenteInstance?.id}" />
					<g:link class="edit" action="edit" id="${incidenteInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<!--g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /-->
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
