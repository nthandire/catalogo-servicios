
<%@ page import="mx.gob.inr.catservicios.*" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'incidente.label', default: 'Incidente')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-incidente" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
    <g:set var="servicios" bean="serviciosService"/>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-incidente" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
      <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
      </g:if>
      <g:if test="${flash.error}">
        <div class="errors" role="status">${flash.error}</div>
      </g:if>

      <g:if test="${incidenteInstance?.idResguardoentregadetalle}">
        <div class="row-fluid">
          <div class="span4">
            <div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'idResguardoentregadetalle', 'error')} ">
              <label for="idResguardoentregadetalle">
                <g:message code="incidente.idResguardoentregadetalle.label" default="Equipo" />
              </label>
              <g:field id="idResguardoentregadetalle" name="idResguardoentregadetalle"
                  disabled="true"
                  value="${servicios.nombreEquipo(incidenteInstance?.idResguardoentregadetalle)}"/>
            </div>
          </div>
        </div>
			</g:if>

      <div class="row-fluid">
        <div class="span4">
          <div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'idServ', 'error')} required">
            <label for="idServ">
              <g:message code="cat_serv.servCat.label" default="Cat" />
              <span class="required-indicator">*</span>
            </label>
            <g:select id="servCat" name="idServ.servSub.servCat.id"
              from="${Cat_servCat.list()}" optionKey="id" required=""
              value="${incidenteInstance?.idServ?.servSub?.servCat?.id}"
              class="many-to-one" onchange="categoryChanged(this.value)"
              noSelection="${['':'Seleccione una...']}" disabled="true"/>
          </div>
        </div>

        <div class="span4">
          <div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'idServ', 'error')} required">
            <label for="idServ">
              <g:message code="cat_serv.servSub.label" default="Serv Sub" />
              <span class="required-indicator">*</span>
            </label>
            <span id="subContainer">
              <g:if test="${incidenteInstance?.idServ}">
                <g:select id='servSub' name='servSub.id' disabled="true"
                  onchange='subcategoryChanged(this.value)' optionKey='id'
                  from="${Cat_servSub.findAllByServCat(incidenteInstance?.idServ?.servSub?.servCat, [order:'id'])}"
                  value="${incidenteInstance?.idServ?.servSub?.id}" noSelection="['':' ']"/>
              </g:if>
            </span>
          </div>
        </div>

        <div class="span4">
          <div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'idServ', 'error')} required">
            <label for="idServ">
              <g:message code="cat_serv.label" default="Servicio" />
              <span class="required-indicator">*</span>
            </label>
            <span id="serviciosContainer">
              <g:if test="${incidenteInstance?.idServ}">
                <g:select id='idServ' name='idServ.id' disabled="true"
                  optionKey='id' value="${incidenteInstance?.idServ?.id}"
                  from="${Cat_serv.findAllByServSub(incidenteInstance?.idServ?.servSub, [order:'id'])}"
                  noSelection="['':' ']"/>
              </g:if>
            </span>
          </div>
        </div>
      </div>

      <g:if test="${incidenteInstance?.idServfinal}">
      <div class="row-fluid">
        <div class="span4">
          <div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'idServfinal', 'error')}">
            <label for="idServfinal">
              <g:message code="cat_serv.servCat.final.label" default="Categoría final" />
            </label>
            <g:select id="servCat" name="idServfinal.servSub.servCat.id"
              from="${Cat_servCat.list()}" optionKey="id" disabled="true"
              value="${incidenteInstance?.idServfinal?.servSub?.servCat?.id}"
              class="many-to-one" onchange="categoryChangedFinal(this.value)"/>
          </div>
        </div>

        <div class="span4">
          <div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'idServfinal', 'error')}">
            <label for="idServfinal">
              <g:message code="cat_serv.servSub.final.label" default="Subcategoría final" />
            </label>
            <span id="subContainerFinal">
              <g:select id='servSubFinal' name='servSubFinal.id' disabled="true"
                onchange='subcategoryChanged(this.value)' optionKey='id'
                from="${Cat_servSub.findAllByServCat(incidenteInstance?.idServfinal?.servSub?.servCat, [order:'id'])}"
                value="${incidenteInstance?.idServfinal?.servSub?.id}"/>
            </span>
          </div>
        </div>

        <div class="span4">
          <div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'idServfinal', 'error')}">
            <label for="idServfinal">
              <g:message code="cat_serv.label" default="Servicio final" /> final
            </label>
            <span id="serviciosContainerFinal">
            <g:select id='idServfinal' name='idServfinal.id' disabled="true"
              optionKey='id' value="${incidenteInstance?.idServfinal?.id}"
              from="${Cat_serv.findAllByServSub(incidenteInstance?.idServfinal?.servSub, [order:'id'])}"/>
            </span>
          </div>
        </div>
      </div>
			</g:if>

      <div class="row-fluid">
        <div class="span6 offset1">
          <div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'descripcion', 'error')} ">
            <label for="descripcion">
              <g:message code="incidente.descripcion.label" default="Descripcion" />
            </label>
            <g:textArea name="descripcion" cols="40" rows="5" maxlength="3000"
              style="width:550px;"
              value="${incidenteInstance?.descripcion}" disabled="true"/>
          </div>
        </div>

        <div class="span5">
          <div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'estado', 'error')} ">
            <label for="estado">
              <g:message code="incidente.estado.label" default="Estado" />
            </label>
            <g:field name="estado" type="text"
              value="${incidenteInstance.estado}" disabled="true"/>
          </div>
        </div>
      </div>

      <div class="row-fluid">
        <div class="span4">
          <div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'idServresp', 'error')} ">
            <label for="idServresp">
              <g:message code="incidente.idServresp.label" default="Responsable" />
            </label>
            <g:select id="idServresp" name="idServresp.id" from="${Cat_servResp.list()}"
              optionKey="id" value="${incidenteInstance?.idServresp?.id}" class="many-to-one"
              noSelection="['': '']" disabled="true"/>
          </div>
        </div>

        <div class="span4">
          <div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'idNivel', 'error')} ">
            <label for="idNivel">
              <g:message code="incidente.idNivel.label" default="Técnico" />
            </label>
            <g:field name="idNivel" type="text"
              value="${tecnico.toString()}" disabled="true"/>
          </div>
        </div>

        <div class="span4">
          <div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'fechaIncidente', 'error')} ">
            <label for="fechaIncidente">
              <g:message code="incidente.fechaIncidente.label" default="Fecha Incidente" />
            </label>
            <g:field name="fechaIncidente" type="datetime"
              value="${incidenteInstance.fechaIncidente}" disabled="true"/>
          </div>
        </div>
      </div>

      <div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'p01', 'error')} ">
        <label for="p01">
          <span id="p01-label" class="property-label"><g:message code="solicitud.p01.label" default="P01" /></span>
        </label>
        <g:select name="p01" disabled="true"
          from="${[1,2]}" style="width: 60px"
          valueMessagePrefix="encuesta.valor" value="${incidenteInstance.p01}" />
      </div>

      <div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'p02', 'error')} ">
        <label for="p02">
          <span id="p02-label" class="property-label"><g:message code="solicitud.p02.label" default="P02" /></span>
        </label>
        <g:select name="p02" disabled="true"
          from="${[1,2]}" style="width: 60px"
          valueMessagePrefix="encuesta.valor" value="${incidenteInstance.p02}" />
      </div>

      <div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'p03', 'error')} ">
        <label for="p03">
          <span id="p03-label" class="property-label"><g:message code="solicitud.p03.label" default="P03" /></span>
        </label>
        <g:select name="p03" disabled="true"
          from="${[1,2]}" style="width: 60px"
          valueMessagePrefix="encuesta.valor" value="${incidenteInstance.p03}" />
      </div>

      <div class="fieldtablecontain ${hasErrors(bean: incidenteInstance, field: 'p04', 'error')} ">
        <label for="p04">
          <span id="p04-label" class="property-label"><g:message code="solicitud.p04.label" default="P04" /></span>
        </label>
        <g:select name="p04" disabled="true"
          from="${[1,2]}" style="width: 60px"
          valueMessagePrefix="encuesta.valor" value="${incidenteInstance.p04}" />
      </div>

      <g:if test="${archivos}">
        <div class="fieldtablecontain">
          <label for="archivos">
            <g:message code="incidente.archivos.label" default="Archivos" />
          </label>
  				<g:each in="${archivos}" var="a">
  				<span class="property-value" aria-labelledby="archivos-label">
            <g:link action="downloadIncidente" id="${a.id}">${a?.encodeAsHTML()}</g:link>
          </span>
  				</g:each>
        </div>
      </g:if>

			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${incidenteInstance?.id}" />
          <%--
					<g:link class="edit" action="edit" id="${incidenteInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
          --%>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
