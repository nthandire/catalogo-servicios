
<%@ page import="mx.gob.inr.catservicios.Bitacora" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'bitacora.label', default: 'Bitacora')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-bitacora" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label.femenino" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-bitacora" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list bitacora">

				<g:if test="${bitacoraInstance?.tipoBitacora}">
				<li class="fieldcontain">
					<span id="tipoBitacora-label" class="property-label"><g:message code="bitacora.tipoBitacora.label" default="Tipo" /></span>

						<span class="property-value" aria-labelledby="tipoBitacora-label"><g:fieldValue bean="${bitacoraInstance}" field="tipoBitacora"/></span>

				</li>
				</g:if>

				<g:if test="${bitacoraInstance?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label">
						<g:message code="bitacora.descripcion.label" default="Descripción" />
					</span>
					<span class="property-value" aria-labelledby="descripcion-label">
						<g:fieldValue bean="${bitacoraInstance}" field="descripcion"/>
					</span>
				</li>
				</g:if>

				<g:if test="${bitacoraInstance?.estado}">
				<li class="fieldcontain">
					<span id="estado-label" class="property-label"><g:message code="bitacora.estado.label" default="Estado" /></span>
					<span class="property-value" aria-labelledby="estado-label">
						<g:message code="cat_servCat.estado.${bitacoraInstance.estado}" />
					</span>
				</li>
				</g:if>

				<g:if test="${bitacoraInstance?.id}">
				<li class="fieldcontain">
					<span id="detalles-label" class="property-label"><g:message code="bitacora.detalles.label" default="Detalles" /></span>

						<g:each in="${bitacoraInstance.detalles.sort{it.id}}" var="b">
						<span class="property-value" aria-labelledby="detalles-label">
							<g:link controller="bitacoraDetalle" action="show" id="${b.id}">${b?.encodeAsHTML()}</g:link>
							&nbsp;&nbsp;<g:message code="cat_servCat.estado.${b.estado}" />
						</span>
						</g:each>

				</li>
				</g:if>

				<g:if test="${bitacoraInstance?.monitoreos}">
				<li class="fieldcontain">
					<span id="monitoreos-label" class="property-label"><g:message code="bitacora.monitoreos.label" default="Monitoreos" /></span>

						<g:each in="${bitacoraInstance.monitoreos}" var="m">
						<span class="property-value" aria-labelledby="monitoreos-label"><g:link controller="monitoreo" action="show" id="${m.id}">${m?.encodeAsHTML()}</g:link></span>
						</g:each>

				</li>
				</g:if>

			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${bitacoraInstance?.id}" />
					<g:link class="edit" action="edit" id="${bitacoraInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
