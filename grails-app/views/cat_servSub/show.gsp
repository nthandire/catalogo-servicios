
<%@ page import="mx.gob.inr.catservicios.Cat_servSub" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'cat_servSub.label', default: 'Cat_servSub')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>

<style type="text/css">
  textArea { width: 412px; }
</style>

		<a href="#show-cat_servSub" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-cat_servSub" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			  <div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:if test="${flash.error}">
			  <div class="errors" role="status">${flash.error}</div>
			</g:if>
<table class="table table-condensed">
	<tr>
		<td colspan="2">

<div class="fieldtablecontain ${hasErrors(bean: cat_servSubInstance, field: 'servCat', 'error')} required">
	<label for="servCat">
		<g:message code="cat_servSub.servCat.label" default="Serv Cat" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="servCat" name="servCat.id" from="${mx.gob.inr.catservicios.Cat_servCat.list()}" optionKey="id" required="" value="${cat_servSubInstance?.servCat?.id}" class="many-to-one" disabled="true"/>
</div>

		</td>
		<td colspan="2">

<div class="fieldtablecontain ${hasErrors(bean: cat_servSubInstance, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="cat_servSub.descripcion.label" default="Descripcion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="descripcion" cols="40" rows="5" maxlength="255" required=""
			value="${cat_servSubInstance?.descripcion}" disabled="true"/>
</div>

		</td>
	</tr>
	<tr>
		<td colspan="4">

<div class="fieldtablecontain ${hasErrors(bean: cat_servSubInstance, field: 'servs', 'error')} ">
	<label for="servs">
		<g:message code="cat_servSub.servs.label" default="Servs" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${cat_servSubInstance?.servs?}" var="s">
    <li><g:link controller="cat_serv" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></li>
</g:each>
<!--li class="add">
<g:link controller="cat_serv" action="create" params="['cat_servSub.id': cat_servSubInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'cat_serv.label', default: 'Cat_serv')])}</g:link>
</li-->
</ul>

</div>

		</td>
	</tr>
</table>

			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${cat_servSubInstance?.id}" />
					<g:link class="edit" action="edit" id="${cat_servSubInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<!--g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /-->
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
