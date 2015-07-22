<%@ page import="mx.gob.inr.catservicios.Cat_servSub" %>


<style type="text/css">
  textArea { width: 412px; }
</style>

<table class="table table-condensed">
	<tr>
		<td colspan="2">

<div class="fieldtablecontain ${hasErrors(bean: cat_servSubInstance, field: 'servCat', 'error')} required">
	<label for="servCat">
		<g:message code="cat_servSub.servCat.label" default="Serv Cat" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="servCat" name="servCat.id" from="${mx.gob.inr.catservicios.Cat_servCat.list()}" optionKey="id" required="" value="${cat_servSubInstance?.servCat?.id}" class="many-to-one"/>
</div>

		</td>
		<td colspan="2">

<div class="fieldtablecontain ${hasErrors(bean: cat_servSubInstance, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="cat_servSub.descripcion.label" default="Descripcion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="descripcion" cols="40" rows="5" maxlength="255" required="" value="${cat_servSubInstance?.descripcion}"/>
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
