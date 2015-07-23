
<%@ page import="mx.gob.inr.catservicios.Cat_serv" %>
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

<table class="table table-condensed">
	<tr>
		<td colspan="2">

				<g:if test="${cat_servInstance?.servSub}">
				<li class="fieldcontain">
					<span id="servSub-label" class="property-label"><g:message code="cat_serv.servCat.label" default="Cat" /></span>
					
						<span class="property-value" aria-labelledby="servSub-label"><g:link controller="cat_servSub" action="show" id="${cat_servInstance?.servSub?.servCat?.id}">${cat_servInstance?.servSub?.servCat?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${cat_servInstance?.servSub}">
				<li class="fieldcontain">
					<span id="servSub-label" class="property-label"><g:message code="cat_serv.servSub.label" default="Serv Sub" /></span>
					
						<span class="property-value" aria-labelledby="servSub-label"><g:link controller="cat_servSub" action="show" id="${cat_servInstance?.servSub?.id}">${cat_servInstance?.servSub?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>

		</td>

		<td colspan="2">
		</td>

	</tr>
	<tr>
		<td colspan="2">

<div class="fieldcontain ${hasErrors(bean: cat_servInstance, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="cat_serv.descripcion.label" default="Descripcion" />
		<span class="required-indicator">*</span>
	</label>
	<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${cat_servInstance}" field="descripcion"/></span>
</div>

		</td>
		<td colspan="2">

		</td>
	</tr>
	<tr>
		<td>

<div class="fieldtablecontain ${hasErrors(bean: cat_servInstance, field: 'portal', 'error')} ">
	<label for="portal">
		<g:message code="cat_serv.portal.label" default="Portal" />
		
	</label>
	<span class="property-value" aria-labelledby="portal-label"><g:checkBox name="portal" value="${cat_servInstance?.portal}" /></span>
</div>

		</td>
		<td>

<div class="fieldtablecontain ${hasErrors(bean: cat_servInstance, field: 'incidente', 'error')} ">
	<label for="incidente">
		<g:message code="cat_serv.incidente.label" default="Incidente" />
		
	</label>
	<span class="property-value" aria-labelledby="incidente-label"><g:checkBox name="incidente" value="${cat_servInstance?.incidente}" /></span>
</div>

		</td>
		<td>

<div class="fieldtablecontain ${hasErrors(bean: cat_servInstance, field: 'solicitud', 'error')} ">
	<label for="solicitud">
		<g:message code="cat_serv.solicitud.label" default="Solicitud" />
		
	</label>
	<span class="property-value" aria-labelledby="solicitud-label"><g:checkBox name="solicitud" value="${cat_servInstance?.solicitud}" /></span>
</div>

		</td>
		<td>

<div class="fieldtablecontain ${hasErrors(bean: cat_servInstance, field: 'problema', 'error')} ">
	<label for="problema">
		<g:message code="cat_serv.problema.label" default="Problema" />
		
	</label>
	<span class="property-value" aria-labelledby="problema-label"><g:checkBox name="problema" value="${cat_servInstance?.problema}" /></span>
</div>

		</td>
	</tr>
	<tr>
		<td>

				<g:if test="${cat_servInstance?.servResp1}">
				<li class="fieldcontain">
					<span id="servResp1-label" class="property-label"><g:message code="cat_serv.servResp1.label" default="Serv Resp1" /></span>
					
						<span class="property-value" aria-labelledby="servResp1-label">${cat_servInstance?.servResp1?.encodeAsHTML()}</span>
					
				</li>
				</g:if>
			
				<g:if test="${cat_servInstance?.tiempo1}">
				<li class="fieldcontain">
					<span id="tiempo1-label" class="property-label"><g:message code="cat_serv.tiempo1.label" default="Tiempo1" /></span>
					
						<span class="property-value" aria-labelledby="tiempo1-label"><g:fieldValue bean="${cat_servInstance}" field="tiempo1"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cat_servInstance?.unidades1}">
				<li class="fieldcontain">
					<span id="unidades1-label" class="property-label"><g:message code="cat_serv.unidades1.label" default="Unidades1" /></span>
					
						<span class="property-value" aria-labelledby="unidades1-label">${cat_servInstance?.unidades1?.encodeAsHTML()}</span>
					
				</li>
				</g:if>

		</td>
		<td>

				<g:if test="${cat_servInstance?.servResp2}">
				<li class="fieldcontain">
					<span id="servResp2-label" class="property-label"><g:message code="cat_serv.servResp2.label" default="Serv Resp2" /></span>
					<span class="property-value" aria-labelledby="servResp2-label">${cat_servInstance?.servResp2?.encodeAsHTML()}</span>
				</li>
				</g:if>
			
				<g:if test="${cat_servInstance?.tiempo2}">
				<li class="fieldcontain">
					<span id="tiempo2-label" class="property-label"><g:message code="cat_serv.tiempo2.label" default="Tiempo2" /></span>
					<span class="property-value" aria-labelledby="tiempo2-label"><g:fieldValue bean="${cat_servInstance}" field="tiempo2"/></span>
				</li>
				</g:if>
			
				<g:if test="${cat_servInstance?.unidades2}">
				<li class="fieldcontain">
					<span id="unidades2-label" class="property-label"><g:message code="cat_serv.unidades2.label" default="Unidades2" /></span>
					<span class="property-value" aria-labelledby="unidades2-label">${cat_servInstance?.unidades2?.encodeAsHTML()}</span>
				</li>
				</g:if>

		</td>
		<td>

				<g:if test="${cat_servInstance?.servResp3}">
				<li class="fieldcontain">
					<span id="servResp3-label" class="property-label"><g:message code="cat_serv.servResp3.label" default="Serv Resp3" /></span>
					<span class="property-value" aria-labelledby="servResp3-label">${cat_servInstance?.servResp3?.encodeAsHTML()}</span>
				</li>
				</g:if>
			
				<g:if test="${cat_servInstance?.tiempo3}">
				<li class="fieldcontain">
					<span id="tiempo3-label" class="property-label"><g:message code="cat_serv.tiempo3.label" default="Tiempo3" /></span>
					<span class="property-value" aria-labelledby="tiempo3-label"><g:fieldValue bean="${cat_servInstance}" field="tiempo3"/></span>
				</li>
				</g:if>
			
				<g:if test="${cat_servInstance?.unidades3}">
				<li class="fieldcontain">
					<span id="unidades3-label" class="property-label"><g:message code="cat_serv.unidades3.label" default="Unidades3" /></span>
					<span class="property-value" aria-labelledby="unidades3-label">${cat_servInstance?.unidades3?.encodeAsHTML()}</span>
				</li>
				</g:if>

		</td>
		<td>

		</td>
	</tr>
	<tr>
		<td>

				<g:if test="${cat_servInstance?.impacto}">
				<li class="fieldcontain">
					<span id="impacto-label" class="property-label"><g:message code="cat_serv.impacto.label" default="Impacto" /></span>
					<span class="property-value" aria-labelledby="impacto-label">
						<g:message code="intensidad.valor.${cat_servInstance.impacto}" default="Impacto..." />
					</span>
					
				</li>
				</g:if>

		</td>
		<td>

				<g:if test="${cat_servInstance?.authoriza}">
				<li class="fieldcontain">
					<span id="authoriza-label" class="property-label"><g:message code="cat_serv.authoriza.label" default="Authoriza" /></span>
					
						<span class="property-value" aria-labelledby="authoriza-label"><g:fieldValue bean="${cat_servInstance}" field="authoriza"/></span>
					
				</li>
				</g:if>

		</td>
		<td>

				<g:if test="${cat_servInstance?.servResp}">
				<li class="fieldcontain">
					<span id="servResp-label" class="property-label"><g:message code="cat_serv.servResp.label" default="Serv Resp" /></span>
					
						<span class="property-value" aria-labelledby="servResp-label">${cat_servInstance?.servResp?.encodeAsHTML()}</span>
					
				</li>
				</g:if>

		</td>
		<td>

				<g:if test="${cat_servInstance?.estadoServ}">
				<li class="fieldcontain">
					<span id="estadoServ-label" class="property-label"><g:message code="cat_serv.estado.label" default="estadoServ" /></span>
					
					<span class="property-value" aria-labelledby="estadoServ-label">
						<g:message code="cat_servCat.estado.${cat_servInstance.estadoServ}" default="Estado..." />
					</span>
					
				</li>
				</g:if>

		</td>
	</tr>
	<tr>
		<td colspan="2">

				<g:if test="${cat_servInstance?.plantilla}">
				<li class="fieldcontain">
					<span id="plantilla-label" class="property-label"><g:message code="cat_serv.plantilla.label" default="Plantilla" /></span>
					
						<span class="property-value" aria-labelledby="plantilla-label"><g:fieldValue bean="${cat_servInstance}" field="plantilla"/></span>
					
				</li>
				</g:if>

		</td>
		<td colspan="2">

				<g:if test="${cat_servInstance?.observaciones}">
				<li class="fieldcontain">
					<span id="observaciones-label" class="property-label"><g:message code="cat_serv.observaciones.label" default="Observaciones" /></span>
					
						<span class="property-value" aria-labelledby="observaciones-label"><g:fieldValue bean="${cat_servInstance}" field="observaciones"/></span>
					
				</li>
				</g:if>

		</td>
	</tr>
</table>

			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${cat_servInstance?.id}" />
					<g:link class="edit" action="edit" id="${cat_servInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<!--g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /-->
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
