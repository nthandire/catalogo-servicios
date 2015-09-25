<%@ page import="mx.gob.inr.catservicios.*" %>



<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'idServfinal', 'error')}">
  <label for="idServfinal">
    <g:message code="cat_serv.servCat.final.label" default="Categoría final" />
  </label>
  <g:select id="servCat" name="idServfinal.servSub.servCat.id" from="${Cat_servCat.list()}" optionKey="id" value="${incidenteInstance?.idServfinal?.servSub?.servCat?.id}" class="many-to-one" onchange="categoryChangedFinal(this.value)" noSelection="${['':'Seleccione una...']}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'idServfinal', 'error')} required">
  <label for="idServfinal">
    <g:message code="cat_serv.servSub.final.label" default="Subcategoría final" />
    <span class="required-indicator">*</span>
  </label>
  <span id="subContainerFinal"></span>
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'idServfinal', 'error')} required">
  <label for="idServfinal">
    <g:message code="cat_bitacora.servicio.final.label" default="Servicio final" />
    <span class="required-indicator">*</span>
  </label>
  <span id="serviciosContainerFinal"></span>
</div>

<script>
    function categoryChangedFinal(categoryId) {
        <g:remoteFunction controller="incidente" action="categoryChangedFinal"
            update="subContainerFinal"
            params="'categoryId='+categoryId"/>
    }
    function subcategoryChangedFinal(subcategoryId) {
        <g:remoteFunction controller="incidente" action="subcategoryChangedFinal"
            update="serviciosContainerFinal"
            params="'subcategoryId='+subcategoryId"/>
    }
</script>

<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'solucionNivel1', 'error')} ">
  <label for="solucionNivel1">
    <g:message code="incidente.solucionNivel1.label" default="Solución" />
  </label>
  <g:textArea style="width:412px;" name="solucionNivel1" cols="30" rows="5" maxlength="3000" value="${incidenteInstance?.solucionNivel1}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'idPrograma', 'error')} ">
	<label for="idPrograma">
		<g:message code="incidente.idPrograma.label" default="Estado de cierre" />
	</label>
	<g:select id="idPrograma" name="idPrograma.id" from="${CatPrograma.list()}"
    optionKey="id" value="${incidenteInstance?.idPrograma?.id}"
    class="many-to-one" noSelection="['': '']" required="true"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: firmadigitalInstance, field: 'passwordfirma', 'error')} ">
  <label for="passwordfirma">
    <g:message code="firmadigital.passwordfirma.label" default="Passwordfirma" />

  </label>
  <g:field name="passwordfirma" type="password" value="${firmadigitalInstance?.passwordfirma}" required="true"/>
</div>
