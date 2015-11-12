<%@ page import="mx.gob.inr.catservicios.*" %>

<g:javascript library='jquery' />

<style type="text/css">
  textArea { width: 750px }
</style>

<table class="table table-condensed">
	<tr>
		<td>

<div class="fieldcontain ${hasErrors(bean: cat_servInstance, field: 'servicio', 'error')}">
	<label>
		<g:message code="cat_serv.servCat.label" default="Cat" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="servCat" name="servCat.id" required="true"
    from="${Cat_servCat.list()}"
    optionKey="id" class="many-to-one"
    value="${cat_bitacoraInstance?.servicio?.servSub?.servCat?.id}"
    onchange="categoryChanged(this.value)"
    noSelection="${['':'Seleccione una...']}"/>
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
		<g:message code="cat_serv.label" default="Servicio" />
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
<%-- // TODO: Quitar.
   <tr>
     <td colspan="3">
      <div class="fieldtablecontain ${hasErrors(bean: cat_bitacoraInstance, field: 'observaciones', 'error')} ">
       <label for="observaciones">
         <g:message code="cat_bitacora.observaciones.label" default="Solución" />
       </label>
       <g:textArea name="observaciones" cols="40" rows="5" maxlength="3000" value="${cat_bitacoraInstance?.observaciones}"/>
      </div>
     </td>
   </tr>
--%>
</table>