<%@ page import="mx.gob.inr.catservicios.*" %>

<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'idServfinal', 'error')}">
  <label for="idServfinal">
    <g:message code="cat_serv.servCat.final.label" default="Categoría final" />
  </label>
  <g:select id="servCat" name="idServfinal.servSub.servCat.id" from="${Cat_servCat.list()}" optionKey="id" value="${incidenteInstance?.idServfinal?.servSub?.servCat?.id}" class="many-to-one" onchange="categoryChangedFinal(this.value)" noSelection="${['':'Seleccione una...']}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'idServfinal', 'error')}">
  <label for="idServfinal">
    <g:message code="cat_serv.servSub.final.label" default="Subcategoría final" />
  </label>
  <span id="subContainerFinal"></span>
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'idServfinal', 'error')}">
  <label for="idServfinal">
    <g:message code="cat_bitacora.servicio.final.label" default="Servicio final" />
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

<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'solucionNivel', 'error')} ">
  <label for="solucionNivel">
    <g:message code="incidente.solucionNivel.label" default="Solución" />
  </label>
  <g:textArea style="width:412px;" name="solucionNivel" id="solucionNivel"
    cols="30" rows="5" maxlength="3000"
    value="${solucionNivel}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'idPrograma', 'error')} ">
	<label for="idPrograma">
		<g:message code="incidente.idPrograma.label" default="Estado de cierre" />
	</label>
	<g:select id="idPrograma" name="idPrograma.id" from="${CatPrograma.list()}"
    optionKey="id" value="${incidenteInstance?.idPrograma?.id}"
    class="many-to-one" noSelection="['': '']"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: firmadigitalInstance, field: 'passwordfirma', 'error')} ">
  <label for="passwordfirma">
    <g:message code="firmadigital.passwordfirma.label" default="Passwordfirma" />

  </label>
  <g:field name="passwordfirma" id="passwordfirma" type="password"
    value="${firmadigitalInstance?.passwordfirma}"/>
</div>
