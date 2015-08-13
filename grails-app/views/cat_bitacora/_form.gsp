<%@ page import="mx.gob.inr.catservicios.Cat_bitacora" %>

<g:javascript library='jquery' />

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
	<g:field name="folio" type="number" value="${cat_bitacoraInstance.folio}" required=""/>
</div>
		</td>

		<td colspan="2">
		</td>

	</tr>
	<tr>
		<td>

<div class="fieldcontain ${hasErrors(bean: cat_servInstance, field: 'servicio', 'error')} required">
	<label>
		<g:message code="cat_serv.servCat.label" default="Cat" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="servCat" name="servCat.id" from="${mx.gob.inr.catservicios.Cat_servCat.list()}" optionKey="id" required="" value="${cat_bitacoraInstance?.servicio?.servSub?.servCat?.id}" class="many-to-one" onchange="categoryChanged(this.value)" noSelection="${['null':'Seleccione una...']}"/>
</div>

		</td>

		<td>
<div class="fieldtablecontain ${hasErrors(bean: cat_bitacoraInstance, field: 'servicio', 'error')} required">
	<label for="servicio">
		<g:message code="cat_serv.servSub.label" default="Serv Sub" />
		<span class="required-indicator">*</span>
	</label>
	<span id="subContainer"></span>
</div>

		</td>

		<td>

<div class="fieldtablecontain ${hasErrors(bean: cat_bitacoraInstance, field: 'servicio', 'error')} required">
	<label for="servicio">
		<g:message code="cat_bitacora.servicio.label" default="Servicio" />
		<span class="required-indicator">*</span>
	</label>
	<span id="serviciosContainer"></span>
</div>

        <script>
            function categoryChanged(categoryId) {
                <g:remoteFunction controller="Cat_bitacora" action="categoryChanged"
                    update="subContainer"
                    params="'categoryId='+categoryId"/>
            }
            function subcategoryChanged(subcategoryId) {
                <g:remoteFunction controller="Cat_bitacora" action="subcategoryChanged"
                    update="serviciosContainer"
                    params="'subcategoryId='+subcategoryId"/>
            }
        </script>

		</td>

	</tr>
	<tr>
		<td colspan="3">

<div class="fieldtablecontain ${hasErrors(bean: cat_bitacoraInstance, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="cat_bitacora.descripcion.label" default="Descripcion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="descripcion" cols="40" rows="5" maxlength="1000" required="" value="${cat_bitacoraInstance?.descripcion}"/>
</div>

		</td>
	</tr>
</table>