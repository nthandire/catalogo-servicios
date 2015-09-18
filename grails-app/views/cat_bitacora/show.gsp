<%@ page import="mx.gob.inr.catservicios.Cat_bitacora" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'cat_bitacora.label', default: 'Cat_bitacora')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-cat_bitacora" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-cat_bitacora" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			  <div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:if test="${flash.error}">
			  <div class="errors" role="status">${flash.error}</div>
			</g:if>

<style type="text/css">
  textArea { width: 750px }
</style>

<table class="table table-condensed">
	<tr>
		<td>
<div class="fieldtablecontain ${hasErrors(bean: cat_bitacoraInstance, field: 'folio', 'error')} required">
	<label for="folio">
		<g:message code="cat_bitacora.folio.label" default="Folio" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="folio" type="number" value="${cat_bitacoraInstance.folio}" disabled="true"/>
</div>
		</td>

		<td colspan="2">
		</td>

	</tr>
	<tr>
		<td>

<div class="fieldcontain ${hasErrors(bean: cat_servInstance, field: 'servicio', 'error')} required">
	<label for="servicio">
		<g:message code="cat_serv.servCat.label" default="Cat" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="servCat" name="servCat.id" from="${mx.gob.inr.catservicios.Cat_servCat.list()}" optionKey="id"
		value="${cat_bitacoraInstance?.servicio?.servSub?.servCat?.id}" class="many-to-one" disabled="true"/><!-- TODO: efientizar el from -->
</div>

		</td>
		<td>

<div class="fieldtablecontain ${hasErrors(bean: cat_bitacoraInstance, field: 'servicio', 'error')} required">
	<label for="servicio">
		<g:message code="cat_serv.servSub.label" default="Serv Sub" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="servSub" name="servSub.id" from="${mx.gob.inr.catservicios.Cat_servSub.list()}" optionKey="id"
		value="${cat_bitacoraInstance?.servicio?.servSub?.id}" class="many-to-one" disabled="true"/><!-- TODO: efientizar el from -->
</div>

		</td>
		<td>

<div class="fieldtablecontain ${hasErrors(bean: cat_bitacoraInstance, field: 'servicio', 'error')} required">
	<label for="servicio">
		<g:message code="cat_bitacora.servicio.label" default="Servicio" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="servicio" name="servicio.id" from="${mx.gob.inr.catservicios.Cat_serv.list()}" optionKey="id"
		value="${cat_bitacoraInstance?.servicio?.id}" class="many-to-one" disabled="true"/><!-- TODO: efientizar el from -->
</div>

		</td>

	</tr>
	<tr>
		<td colspan="3">

<div class="fieldtablecontain ${hasErrors(bean: cat_bitacoraInstance, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="cat_bitacora.descripcion.label" default="Descripcion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="descripcion" cols="40" rows="5" maxlength="1000" required=""
		value="${cat_bitacoraInstance?.descripcion}" disabled="true"/>
</div>

		</td>
	</tr>
</table>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${cat_bitacoraInstance?.id}" />
					<g:link class="edit" action="edit" id="${cat_bitacoraInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<!--g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /-->
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
