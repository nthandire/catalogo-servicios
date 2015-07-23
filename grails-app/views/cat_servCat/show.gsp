
<%@ page import="mx.gob.inr.catservicios.Cat_servCat" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'cat_servCat.label', default: 'Cat_servCat')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-cat_servCat" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-cat_servCat" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list cat_servCat">
			
				<g:if test="${cat_servCatInstance?.categoria}">
				<li class="fieldcontain">
					<span id="categoria-label" class="property-label"><g:message code="cat_servCat.categoria.label" default="Categoria" /></span>
					
						<span class="property-value" aria-labelledby="categoria-label"><g:fieldValue bean="${cat_servCatInstance}" field="categoria"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cat_servCatInstance?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="cat_servCat.descripcion.label" default="Descripcion" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${cat_servCatInstance}" field="descripcion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cat_servCatInstance?.servResp}">
				<li class="fieldcontain">
					<span id="servResp-label" class="property-label"><g:message code="cat_servCat.servResp.label" default="Serv Resp" /></span>
					
						<span class="property-value" aria-labelledby="servResp-label"><g:link controller="cat_servResp" action="show" id="${cat_servCatInstance?.servResp?.id}">${cat_servCatInstance?.servResp?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${cat_servCatInstance?.valoracion}">
				<li class="fieldcontain">
					<span id="valoracion-label" class="property-label"><g:message code="cat_servCat.valoracion.label" default="Valoracion" /></span>
					
						<span class="property-value" aria-labelledby="valoracion-label">
							<g:message code="intensidad.valor.${cat_servCatInstance.valoracion}" default="Valora..." />
						</span>
					
				</li>
				</g:if>
			
				<g:if test="${cat_servCatInstance?.disponibilidad}">
				<li class="fieldcontain">
					<span id="disponibilidad-label" class="property-label"><g:message code="cat_servCat.disponibilidad.label" default="Disponibilidad" /></span>
					
						<span class="property-value" aria-labelledby="disponibilidad-label"><g:fieldValue bean="${cat_servCatInstance}" field="disponibilidad"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cat_servCatInstance?.estado}">
				<li class="fieldcontain">
					<span id="estado-label" class="property-label"><g:message code="cat_servCat.estado.label" default="Estado" /></span>
					
						<span class="property-value" aria-labelledby="estado-label"><g:fieldValue bean="${cat_servCatInstance}" field="estado"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cat_servCatInstance?.servCob}">
				<li class="fieldcontain">
					<span id="servCob-label" class="property-label"><g:message code="cat_servCat.servCob.label" default="Serv Cob" /></span>
					
						<span class="property-value" aria-labelledby="servCob-label"><g:link controller="cat_servCob" action="show" id="${cat_servCatInstance?.servCob?.id}">${cat_servCatInstance?.servCob?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${cat_servCatInstance?.servSubs}">
				<li class="fieldcontain">
					<span id="servSubs-label" class="property-label"><g:message code="cat_servCat.servSubs.label" default="Serv Subs" /></span>
					
						<g:each in="${cat_servCatInstance.servSubs}" var="s">
						<span class="property-value" aria-labelledby="servSubs-label"><g:link controller="cat_servSub" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${cat_servCatInstance?.id}" />
					<g:link class="edit" action="edit" id="${cat_servCatInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<!--g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /-->
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
