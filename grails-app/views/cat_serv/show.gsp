
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

<style type="text/css">
  textArea { width: 412px; }
</style>

<table class="table table-condensed">
	<tr>
		<td colspan="2">

<div class="fieldcontain ${hasErrors(bean: cat_servInstance, field: 'servSub', 'error')} required">
	<span id="servCat-label" class="property-label"><g:message code="cat_serv.servCat.label" default="Cat" /></span>
	<span class="property-value" aria-labelledby="servCat-label">${cat_servInstance?.servSub?.servCat?.encodeAsHTML()}</span>
</div>

<div class="fieldcontain ${hasErrors(bean: cat_servInstance, field: 'servSub', 'error')} required">
	<span id="servSub-label" class="property-label"><g:message code="cat_serv.servSub.label" default="Serv Sub" /></span>
	<span class="property-value" aria-labelledby="servSub-label">${cat_servInstance?.servSub?.encodeAsHTML()}</span>
</div>

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
	<g:textArea name="descripcion" cols="40" rows="5" maxlength="255" required=""
		value="${cat_servInstance?.descripcion}" disabled="true"/>
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
	<g:checkBox name="portal" widget="checkbox" value="${cat_servInstance?.portal}" disabled="true" />
</div>

		</td>
		<td>

<div class="fieldtablecontain ${hasErrors(bean: cat_servInstance, field: 'incidente', 'error')} ">
	<label for="incidente">
		<g:message code="cat_serv.incidente.label" default="Incidente" />
		
	</label>
	<g:checkBox name="incidente" widget="checkbox" value="${cat_servInstance?.incidente}" disabled="true" />
</div>

		</td>
		<td>

<div class="fieldtablecontain ${hasErrors(bean: cat_servInstance, field: 'solicitud', 'error')} ">
	<label for="solicitud">
		<g:message code="cat_serv.solicitud.label" default="Solicitud" disabled="true" />
		
	</label>
	<g:checkBox name="solicitud" widget="checkbox" value="${cat_servInstance?.solicitud}" disabled="true" />
</div>

		</td>
		<td>

<div class="fieldtablecontain ${hasErrors(bean: cat_servInstance, field: 'problema', 'error')} ">
	<label for="problema">
		<g:message code="cat_serv.problema.label" default="Problema" />
		
	</label>
	<g:checkBox name="problema" widget="checkbox" value="${cat_servInstance?.problema}" disabled="true" />
</div>

		</td>
	</tr>
	<tr>
		<td>

<div class="fieldtablecontain ${hasErrors(bean: cat_servInstance, field: 'servResp1', 'error')} required">
	<label for="servResp1">
		<g:message code="cat_serv.servResp1.label" default="Serv Resp1" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="servResp1" name="servResp1.id" from="${mx.gob.inr.catservicios.Cat_servResp.list()}" optionKey="id" required="" value="${cat_servInstance?.servResp1?.id}" class="many-to-one" disabled="true"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: cat_servInstance, field: 'tiempo1', 'error')} required">
	<label for="tiempo1">
		<g:message code="cat_serv.tiempo1.label" default="Tiempo1" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="tiempo1" type="number" min="1" value="${cat_servInstance.tiempo1}" required="" disabled="true"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: cat_servInstance, field: 'unidades1', 'error')} required">
	<label for="unidades1">
		<g:message code="cat_serv.unidades1.label" default="Unidades1" />
	</label>
	<g:select id="unidades1" name="unidades1.id" from="${mx.gob.inr.catservicios.Cat_tiempo.list()}" optionKey="id" required="" value="${cat_servInstance?.unidades1?.id}" class="many-to-one" disabled="true"/>
</div>

		</td>
		<td>

<div class="fieldtablecontain ${hasErrors(bean: cat_servInstance, field: 'servResp2', 'error')} ">
	<label for="servResp2">
		<g:message code="cat_serv.servResp2.label" default="Serv Resp2" />
		
	</label>
	<g:select id="servResp2" name="servResp2.id" from="${mx.gob.inr.catservicios.Cat_servResp.list()}" optionKey="id" value="${cat_servInstance?.servResp2?.id}" class="many-to-one" noSelection="['null': '']" disabled="true"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: cat_servInstance, field: 'tiempo2', 'error')} ">
	<label for="tiempo2">
		<g:message code="cat_serv.tiempo2.label" default="Tiempo2" />
		
	</label>
	<g:field name="tiempo2" type="number" min="0" value="${cat_servInstance.tiempo2}" disabled="true"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: cat_servInstance, field: 'unidades2', 'error')} required">
	<label for="unidades2">
		<g:message code="cat_serv.unidades2.label" default="Unidades2" />
	</label>
	<g:select id="unidades2" name="unidades2.id" from="${mx.gob.inr.catservicios.Cat_tiempo.list()}" optionKey="id" required="" value="${cat_servInstance?.unidades2?.id}" class="many-to-one" disabled="true"/>
</div>

		</td>
		<td>

<div class="fieldtablecontain ${hasErrors(bean: cat_servInstance, field: 'servResp3', 'error')} ">
	<label for="servResp3">
		<g:message code="cat_serv.servResp3.label" default="Serv Resp3" />
		
	</label>
	<g:select id="servResp3" name="servResp3.id" from="${mx.gob.inr.catservicios.Cat_servResp.list()}" optionKey="id" value="${cat_servInstance?.servResp3?.id}" class="many-to-one" noSelection="['null': '']" disabled="true"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: cat_servInstance, field: 'tiempo3', 'error')} ">
	<label for="tiempo3">
		<g:message code="cat_serv.tiempo3.label" default="Tiempo3" />
		
	</label>
	<g:field name="tiempo3" type="number" min="0" value="${cat_servInstance.tiempo3}" disabled="true"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: cat_servInstance, field: 'unidades3', 'error')} required">
	<label for="unidades3">
		<g:message code="cat_serv.unidades3.label" default="Unidades3" />
	</label>
	<g:select id="unidades3" name="unidades3.id" from="${mx.gob.inr.catservicios.Cat_tiempo.list()}" optionKey="id" required="" value="${cat_servInstance?.unidades3?.id}" class="many-to-one" disabled="true"/>
</div>

		</td>
		<td>

		</td>
	</tr>
	<tr>
		<td>

<div class="fieldcontain ${hasErrors(bean: cat_servInstance, field: 'impacto', 'error')} required">
	<label for="impacto">
		<g:message code="cat_serv.impacto.label" default="Impacto" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="impacto" disabled="true" from="${[1,2,3]}"
		valueMessagePrefix="intensidad.valor" value="${cat_servInstance.impacto}"
		required="" />
</div>

		</td>
		<td>

<div class="fieldcontain ${hasErrors(bean: cat_servInstance, field: 'authoriza', 'error')} ">
	<label for="authoriza">
		<g:message code="cat_serv.authoriza.label" default="Authoriza" />
		
	</label>
	<g:select id="authoriza" name="authoriza.id" from="${mx.gob.inr.catservicios.Cat_servResp.list()}" optionKey="id" required="" value="${cat_servInstance?.authoriza?.id}" class="many-to-one" disabled="true"/>
</div>

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

<div class="fieldtablecontain ${hasErrors(bean: cat_servInstance, field: 'estadoServ', 'error')} required">
	<label for="estadoServ">
		<g:message code="cat_serv.estado.label" default="estadoServ" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="estadoServ" from="${cat_servInstance.constraints.estadoServ.inList}"
		valueMessagePrefix="cat_servCat.estado" value="${cat_servInstance.estadoServ}"
		required="" disabled="true" />
</div>

		</td>
	</tr>
	<tr>
		<td colspan="2">

<div class="fieldcontain ${hasErrors(bean: cat_servInstance, field: 'plantilla', 'error')} ">
	<label for="plantilla">
		<g:message code="cat_serv.plantilla.label" default="Plantilla" />
		
	</label>
	<g:textArea name="plantilla" cols="80" rows="5" maxlength="500" value="${cat_servInstance?.plantilla}" disabled="true"/>
</div>

		</td>
		<td colspan="2">

<div class="fieldcontain ${hasErrors(bean: cat_servInstance, field: 'observaciones', 'error')} ">
	<label for="observaciones">
		<g:message code="cat_serv.observaciones.label" default="Observaciones" />
		
	</label>
	<g:textArea name="observaciones" cols="80" rows="5" maxlength="1000" value="${cat_servInstance?.observaciones}" disabled="true"/>
</div>

		</td>
	</tr>
</table>
