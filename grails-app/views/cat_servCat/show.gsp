
<%@ page import="mx.gob.inr.catservicios.*" %>
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
			<g:if test="${flash.error}">
			  <div class="errors" role="status">${flash.error}</div>
			</g:if>
<style type="text/css">
  textArea { width: 412px; }
</style>

<table class="table table-condensed">
	<tr>
		<td colspan="2">

<div class="fieldtablecontain ${hasErrors(bean: cat_servCatInstance, field: 'categoria', 'error')} required">
	<label for="categoria">
		<g:message code="cat_servCat.categoria.label" default="Categoria" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="categoria" cols="40" rows="5" maxlength="255" required=""
		value="${cat_servCatInstance?.categoria}" disabled="true"/>
</div>

		</td>
		<td colspan="2">

<div class="fieldtablecontain ${hasErrors(bean: cat_servCatInstance, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="cat_servCat.descripcion.label" default="Descripcion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="descripcion" cols="40" rows="5" maxlength="1000" required=""
		value="${cat_servCatInstance?.descripcion}" disabled="true"/>
</div>

		</td>
	</tr>
	<tr>
		<td>

<div class="fieldtablecontain ${hasErrors(bean: cat_servCatInstance, field: 'servResp', 'error')} required">
	<label for="servResp">
		<g:message code="cat_servCat.servResp.label" default="Serv Resp" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="servResp" name="servResp.id" from="${Cat_servResp.list()}"
		optionKey="id" required="" value="${cat_servCatInstance?.servResp?.id}"
		class="many-to-one" disabled="true"/>
</div>

		</td>
		<td>

<div class="fieldtablecontain ${hasErrors(bean: cat_servCatInstance, field: 'valoracion', 'error')} required">
	<label for="valoracion">
		<g:message code="cat_servCat.valoracion.label" default="Valoracion" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="valoracion" from="${[1,2,3]}" valueMessagePrefix="intensidad.valor"
		value="${cat_servCatInstance.valoracion}" required="" disabled="true" />
</div>

		</td>
		<td>

<div class="fieldtablecontain ${hasErrors(bean: cat_servCatInstance, field: 'disponibilidad', 'error')} required">
	<label for="disponibilidad">
		<g:message code="cat_servCat.disponibilidad.label" default="Disponibilidad" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="disponibilidad" type="number" min="0"
		value="${cat_servCatInstance.disponibilidad}" required="" disabled="true"/>
</div>

		</td>
		<td>

<div class="fieldtablecontain ${hasErrors(bean: cat_servCatInstance, field: 'estado', 'error')} required">
	<label for="estado">
		<g:message code="cat_servCat.estado.label" default="Estado" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="estado" from="${cat_servCatInstance.constraints.estado.inList}"
		valueMessagePrefix="cat_servCat.estado" value="${cat_servCatInstance.estado}"
		required="" disabled="true" />
</div>

		</td>
	</tr>
	<tr>
		<td>

<div class="fieldtablecontain ${hasErrors(bean: cat_servCatInstance, field: 'servCob', 'error')} required">
	<label for="servCob">
		<g:message code="cat_servCat.servCob.label" default="Serv Cob" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="servCob" name="servCob.id" from="${Cat_servCob.list()}"
		optionKey="id" required="" value="${cat_servCatInstance?.servCob?.id}"
		class="many-to-one" disabled="true"/>
</div>

		</td>
		<td colspan="3">

		</td>
	</tr>
	<tr>
		<td colspan="4">


<div class="fieldtablecontain ${hasErrors(bean: cat_servCatInstance, field: 'servSubs', 'error')} ">
	<label for="servSubs">
		<g:message code="cat_servCat.servSubs.label" default="Serv Subs" />

	</label>

<ul class="one-to-many">
<g:each in="${cat_servCatInstance?.servSubs?}" var="s">
    <li><g:link controller="cat_servSub" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></li>
</g:each>
<!--li class="add">
<g:link controller="cat_servSub" action="create" params="['cat_servCat.id': cat_servCatInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'cat_servSub.label', default: 'Cat_servSub')])}</g:link>
</li-->
</ul>

</div>

		</td>
	</tr>
</table>
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
