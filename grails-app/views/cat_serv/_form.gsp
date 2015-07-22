<%@ page import="mx.gob.inr.catservicios.Cat_serv" %>

<g:javascript library='jquery' />

<style type="text/css">
  textArea { width: 412px; }
</style>

<table class="table table-condensed">
	<tr>
		<td colspan="2">

<div class="fieldcontain ${hasErrors(bean: cat_servInstance, field: 'servSub', 'error')} required">
	<label>
		<g:message code="cat_serv.servCat.label" default="Cat" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="servCat" name="servCat.id" from="${mx.gob.inr.catservicios.Cat_servCat.list()}" optionKey="id" required="" value="${cat_servInstance?.servSub?.servCat?.id}" class="many-to-one" onchange="categoryChanged(this.value)" noSelection="${['null':'Seleccione una...']}"/>
</div>

		</td>
		<td colspan="2">

<div class="fieldtablecontain ${hasErrors(bean: cat_servInstance, field: 'servSub', 'error')} required">
	<label for="servSub">
		<g:message code="cat_serv.servSub.label" default="Serv Sub" />
		<span class="required-indicator">*</span>
	</label>
	<span id="subContainer">
          <g:select id="servSub" name="servSub.id" from="${mx.gob.inr.catservicios.Cat_servSub.list()}" optionKey="id" required="" value="${cat_servInstance?.servSub?.id}" class="many-to-one"/>
        </span>
</div>

        <script>
            function categoryChanged(categoryId) {
                <g:remoteFunction controller="cat_serv" action="categoryChanged"
                    update="subContainer"
                    params="'categoryId='+categoryId"/>
            }
        </script>

		</td>

	</tr>
	<tr>
		<td colspan="2">

<div class="fieldtablecontain ${hasErrors(bean: cat_servInstance, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="cat_serv.descripcion.label" default="Descripcion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="descripcion" cols="40" rows="5" maxlength="255" required="" value="${cat_servInstance?.descripcion}"/>
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
	<g:checkBox name="portal" widget="checkbox" value="${cat_servInstance?.portal}" />
</div>

		</td>
		<td>

<div class="fieldtablecontain ${hasErrors(bean: cat_servInstance, field: 'incidente', 'error')} ">
	<label for="incidente">
		<g:message code="cat_serv.incidente.label" default="Incidente" />
		
	</label>
	<g:checkBox name="incidente" widget="checkbox" value="${cat_servInstance?.incidente}" />
</div>

		</td>
		<td>

<div class="fieldtablecontain ${hasErrors(bean: cat_servInstance, field: 'solicitud', 'error')} ">
	<label for="solicitud">
		<g:message code="cat_serv.solicitud.label" default="Solicitud" />
		
	</label>
	<g:checkBox name="solicitud" widget="checkbox" value="${cat_servInstance?.solicitud}" />
</div>

		</td>
		<td>

<div class="fieldtablecontain ${hasErrors(bean: cat_servInstance, field: 'problema', 'error')} ">
	<label for="problema">
		<g:message code="cat_serv.problema.label" default="Problema" />
		
	</label>
	<g:checkBox name="problema" widget="checkbox" value="${cat_servInstance?.problema}" />
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
	<g:select id="servResp1" name="servResp1.id" from="${mx.gob.inr.catservicios.Cat_servResp.list()}" optionKey="id" required="" value="${cat_servInstance?.servResp1?.id}" class="many-to-one"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: cat_servInstance, field: 'tiempo1', 'error')} required">
	<label for="tiempo1">
		<g:message code="cat_serv.tiempo1.label" default="Tiempo1" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="tiempo1" type="number" min="0" value="${cat_servInstance.tiempo1}" required=""/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: cat_servInstance, field: 'unidades1', 'error')} required">
	<label for="unidades1">
		<g:message code="cat_serv.unidades1.label" default="Unidades1" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="unidades1" name="unidades1.id" from="${mx.gob.inr.catservicios.Cat_tiempo.list()}" optionKey="id" required="" value="${cat_servInstance?.unidades1?.id}" class="many-to-one"/>
</div>

		</td>
		<td>

<div class="fieldtablecontain ${hasErrors(bean: cat_servInstance, field: 'servResp2', 'error')} ">
	<label for="servResp2">
		<g:message code="cat_serv.servResp2.label" default="Serv Resp2" />
		
	</label>
	<g:select id="servResp2" name="servResp2.id" from="${mx.gob.inr.catservicios.Cat_servResp.list()}" optionKey="id" value="${cat_servInstance?.servResp2?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: cat_servInstance, field: 'tiempo2', 'error')} ">
	<label for="tiempo2">
		<g:message code="cat_serv.tiempo2.label" default="Tiempo2" />
		
	</label>
	<g:field name="tiempo2" type="number" min="0" value="${cat_servInstance.tiempo2}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: cat_servInstance, field: 'unidades2', 'error')} required">
	<label for="unidades2">
		<g:message code="cat_serv.unidades2.label" default="Unidades2" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="unidades2" name="unidades2.id" from="${mx.gob.inr.catservicios.Cat_tiempo.list()}" optionKey="id" required="" value="${cat_servInstance?.unidades2?.id}" class="many-to-one"/>
</div>

		</td>
		<td>

<div class="fieldtablecontain ${hasErrors(bean: cat_servInstance, field: 'servResp3', 'error')} ">
	<label for="servResp3">
		<g:message code="cat_serv.servResp3.label" default="Serv Resp3" />
		
	</label>
	<g:select id="servResp3" name="servResp3.id" from="${mx.gob.inr.catservicios.Cat_servResp.list()}" optionKey="id" value="${cat_servInstance?.servResp3?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: cat_servInstance, field: 'tiempo3', 'error')} ">
	<label for="tiempo3">
		<g:message code="cat_serv.tiempo3.label" default="Tiempo3" />
		
	</label>
	<g:field name="tiempo3" type="number" min="0" value="${cat_servInstance.tiempo3}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: cat_servInstance, field: 'unidades3', 'error')} required">
	<label for="unidades3">
		<g:message code="cat_serv.unidades3.label" default="Unidades3" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="unidades3" name="unidades3.id" from="${mx.gob.inr.catservicios.Cat_tiempo.list()}" optionKey="id" required="" value="${cat_servInstance?.unidades3?.id}" class="many-to-one"/>
</div>

		</td>
		<td>

		</td>
	</tr>
	<tr>
		<td>

<div class="fieldtablecontain ${hasErrors(bean: cat_servInstance, field: 'impacto', 'error')} required">
	<label for="impacto">
		<g:message code="cat_serv.impacto.label" default="Impacto" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="impacto" type="number" min="0" max="3" value="${cat_servInstance.impacto}" required=""/>
</div>

		</td>
		<td>

<div class="fieldtablecontain ${hasErrors(bean: cat_servInstance, field: 'authoriza', 'error')} ">
	<label for="authoriza">
		<g:message code="cat_serv.authoriza.label" default="Authoriza" />
		
	</label>
	<g:textField name="authoriza" readonly="readonly" value="${cat_servInstance?.authoriza}"/>
</div>

		</td>
		<td>

<div class="fieldtablecontain ${hasErrors(bean: cat_servInstance, field: 'servResp', 'error')} required">
	<label for="servResp">
		<g:message code="cat_serv.servResp.label" default="Serv Resp" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="servResp" name="servResp.id" from="${mx.gob.inr.catservicios.Cat_servResp.list()}" optionKey="id" required="" value="${cat_servInstance?.servResp?.id}" class="many-to-one"/>
</div>

		</td>
		<td>

		</td>
	</tr>
	<tr>
		<td colspan="2">

<div class="fieldtablecontain ${hasErrors(bean: cat_servInstance, field: 'plantilla', 'error')} ">
	<label for="plantilla">
		<g:message code="cat_serv.plantilla.label" default="Plantilla" />
		
	</label>
	<g:textArea name="plantilla" cols="40" rows="5" maxlength="500" value="${cat_servInstance?.plantilla}"/>
</div>

		</td>
		<td colspan="2">

<div class="fieldtablecontain ${hasErrors(bean: cat_servInstance, field: 'observaciones', 'error')} ">
	<label for="observaciones">
		<g:message code="cat_serv.observaciones.label" default="Observaciones" />
		
	</label>
	<g:textArea name="observaciones" cols="40" rows="5" maxlength="1000" value="${cat_servInstance?.observaciones}"/>
</div>

		</td>
	</tr>
</table>
