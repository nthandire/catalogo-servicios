
<%@ page import="catalogo.servicios.Cat_serv" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'cat_serv.label', default: 'Cat_serv')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-cat_serv" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-cat_serv" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list cat_serv">
			
				<g:if test="${cat_servInstance?.servSub}">
				<li class="fieldcontain">
					<span id="servSub-label" class="property-label"><g:message code="cat_serv.servSub.label" default="Serv Sub" /></span>
					
						<span class="property-value" aria-labelledby="servSub-label"><g:link controller="cat_servSub" action="show" id="${cat_servInstance?.servSub?.id}">${cat_servInstance?.servSub?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${cat_servInstance?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="cat_serv.descripcion.label" default="Descripcion" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${cat_servInstance}" field="descripcion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cat_servInstance?.portal}">
				<li class="fieldcontain">
					<span id="portal-label" class="property-label"><g:message code="cat_serv.portal.label" default="Portal" /></span>
					
						<span class="property-value" aria-labelledby="portal-label"><g:formatBoolean boolean="${cat_servInstance?.portal}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${cat_servInstance?.incidente}">
				<li class="fieldcontain">
					<span id="incidente-label" class="property-label"><g:message code="cat_serv.incidente.label" default="Incidente" /></span>
					
						<span class="property-value" aria-labelledby="incidente-label"><g:formatBoolean boolean="${cat_servInstance?.incidente}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${cat_servInstance?.solicitud}">
				<li class="fieldcontain">
					<span id="solicitud-label" class="property-label"><g:message code="cat_serv.solicitud.label" default="Solicitud" /></span>
					
						<span class="property-value" aria-labelledby="solicitud-label"><g:formatBoolean boolean="${cat_servInstance?.solicitud}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${cat_servInstance?.problema}">
				<li class="fieldcontain">
					<span id="problema-label" class="property-label"><g:message code="cat_serv.problema.label" default="Problema" /></span>
					
						<span class="property-value" aria-labelledby="problema-label"><g:formatBoolean boolean="${cat_servInstance?.problema}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${cat_servInstance?.servResp1}">
				<li class="fieldcontain">
					<span id="servResp1-label" class="property-label"><g:message code="cat_serv.servResp1.label" default="Serv Resp1" /></span>
					
						<span class="property-value" aria-labelledby="servResp1-label"><g:link controller="cat_servResp" action="show" id="${cat_servInstance?.servResp1?.id}">${cat_servInstance?.servResp1?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${cat_servInstance?.servResp2}">
				<li class="fieldcontain">
					<span id="servResp2-label" class="property-label"><g:message code="cat_serv.servResp2.label" default="Serv Resp2" /></span>
					
						<span class="property-value" aria-labelledby="servResp2-label"><g:link controller="cat_servResp" action="show" id="${cat_servInstance?.servResp2?.id}">${cat_servInstance?.servResp2?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${cat_servInstance?.servResp3}">
				<li class="fieldcontain">
					<span id="servResp3-label" class="property-label"><g:message code="cat_serv.servResp3.label" default="Serv Resp3" /></span>
					
						<span class="property-value" aria-labelledby="servResp3-label"><g:link controller="cat_servResp" action="show" id="${cat_servInstance?.servResp3?.id}">${cat_servInstance?.servResp3?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${cat_servInstance?.tiempo1}">
				<li class="fieldcontain">
					<span id="tiempo1-label" class="property-label"><g:message code="cat_serv.tiempo1.label" default="Tiempo1" /></span>
					
						<span class="property-value" aria-labelledby="tiempo1-label"><g:fieldValue bean="${cat_servInstance}" field="tiempo1"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cat_servInstance?.tiempo2}">
				<li class="fieldcontain">
					<span id="tiempo2-label" class="property-label"><g:message code="cat_serv.tiempo2.label" default="Tiempo2" /></span>
					
						<span class="property-value" aria-labelledby="tiempo2-label"><g:fieldValue bean="${cat_servInstance}" field="tiempo2"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cat_servInstance?.tiempo3}">
				<li class="fieldcontain">
					<span id="tiempo3-label" class="property-label"><g:message code="cat_serv.tiempo3.label" default="Tiempo3" /></span>
					
						<span class="property-value" aria-labelledby="tiempo3-label"><g:fieldValue bean="${cat_servInstance}" field="tiempo3"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cat_servInstance?.unidades1}">
				<li class="fieldcontain">
					<span id="unidades1-label" class="property-label"><g:message code="cat_serv.unidades1.label" default="Unidades1" /></span>
					
						<span class="property-value" aria-labelledby="unidades1-label"><g:link controller="cat_tiempo" action="show" id="${cat_servInstance?.unidades1?.id}">${cat_servInstance?.unidades1?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${cat_servInstance?.unidades2}">
				<li class="fieldcontain">
					<span id="unidades2-label" class="property-label"><g:message code="cat_serv.unidades2.label" default="Unidades2" /></span>
					
						<span class="property-value" aria-labelledby="unidades2-label"><g:link controller="cat_tiempo" action="show" id="${cat_servInstance?.unidades2?.id}">${cat_servInstance?.unidades2?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${cat_servInstance?.unidades3}">
				<li class="fieldcontain">
					<span id="unidades3-label" class="property-label"><g:message code="cat_serv.unidades3.label" default="Unidades3" /></span>
					
						<span class="property-value" aria-labelledby="unidades3-label"><g:link controller="cat_tiempo" action="show" id="${cat_servInstance?.unidades3?.id}">${cat_servInstance?.unidades3?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${cat_servInstance?.impacto}">
				<li class="fieldcontain">
					<span id="impacto-label" class="property-label"><g:message code="cat_serv.impacto.label" default="Impacto" /></span>
					
						<span class="property-value" aria-labelledby="impacto-label"><g:fieldValue bean="${cat_servInstance}" field="impacto"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cat_servInstance?.authoriza}">
				<li class="fieldcontain">
					<span id="authoriza-label" class="property-label"><g:message code="cat_serv.authoriza.label" default="Authoriza" /></span>
					
						<span class="property-value" aria-labelledby="authoriza-label"><g:fieldValue bean="${cat_servInstance}" field="authoriza"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cat_servInstance?.servResp}">
				<li class="fieldcontain">
					<span id="servResp-label" class="property-label"><g:message code="cat_serv.servResp.label" default="Serv Resp" /></span>
					
						<span class="property-value" aria-labelledby="servResp-label"><g:link controller="cat_servResp" action="show" id="${cat_servInstance?.servResp?.id}">${cat_servInstance?.servResp?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${cat_servInstance?.plantilla}">
				<li class="fieldcontain">
					<span id="plantilla-label" class="property-label"><g:message code="cat_serv.plantilla.label" default="Plantilla" /></span>
					
						<span class="property-value" aria-labelledby="plantilla-label"><g:fieldValue bean="${cat_servInstance}" field="plantilla"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cat_servInstance?.observaciones}">
				<li class="fieldcontain">
					<span id="observaciones-label" class="property-label"><g:message code="cat_serv.observaciones.label" default="Observaciones" /></span>
					
						<span class="property-value" aria-labelledby="observaciones-label"><g:fieldValue bean="${cat_servInstance}" field="observaciones"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${cat_servInstance?.id}" />
					<g:link class="edit" action="edit" id="${cat_servInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
