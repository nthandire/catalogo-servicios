
<%@ page import="mx.gob.inr.catservicios.Incidente" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'incidente.label', default: 'Incidente')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-incidente" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
        <!-- Button to trigger modal -->
        <li><a href="#myModal" class="create" data-toggle="modal"><g:message code="default.new.label" args="[entityName]" /></a></li>
			</ul>
		</div>
		<div id="list-incidente" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
      <g:if test="${flash.error}">
      <div class="errors" role="status">${flash.error}</div>
      </g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="incidente.idSistema.label" default="Sistema" /></th>
					
						<g:sortableColumn property="idResguardoentregadetalle" title="${message(code: 'incidente.idResguardoentregadetalle.label', default: 'Id Resguardoentregadetalle')}" />
					
						<g:sortableColumn property="fechaIncidente" title="${message(code: 'incidente.fechaIncidente.label', default: 'Fecha Incidente')}" />
					
						<g:sortableColumn property="numeroIncidente" title="${message(code: 'incidente.numeroIncidente.label', default: 'Numero Incidente')}" />
					
						<g:sortableColumn property="estado" title="${message(code: 'incidente.estado.label', default: 'Estado')}" />
					
						<g:sortableColumn property="idReporta" title="${message(code: 'incidente.idReporta.label', default: 'Reporta')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${incidenteInstanceList}" status="i" var="incidenteInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
            <%--
            <td><g:link action="show" id="${incidenteInstance.id}">${fieldValue(bean: incidenteInstance, field: "idSistema")}</g:link></td>
            --%>
            <td>
              <button type="button" class="btn btn-link" data-target="myEditModal"
                data-toggle="#modal"
                onClick="$('#myEditModal .modal-body').load('<g:createLink action="edit" id="${incidenteInstance.id}" />');">
                ${fieldValue(bean: incidenteInstance, field: "idSistema") }
              </button>
            <%--
              <a href=<g:createLink action="edit" id="${incidenteInstance.id}" /> onClick="$(\"#myModal .modal-body\").load(<g:createLink action=\"edit\" id=\"${incidenteInstance.id}\" />);">
                ${fieldValue(bean: incidenteInstance, field: "idSistema")}

              </a>
            --%>
            </td>
					
						<td>${fieldValue(bean: incidenteInstance, field: "idResguardoentregadetalle")}</td>
					
						<td><g:formatDate date="${incidenteInstance.fechaIncidente}" /></td>
					
						<td>${fieldValue(bean: incidenteInstance, field: "numeroIncidente")}</td>
					
						<td>${fieldValue(bean: incidenteInstance, field: "estado")}</td>
					
						<td>${fieldValue(bean: incidenteInstance, field: "idReporta")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${incidenteInstanceTotal}" />
			</div>
		</div>

    <!-- Modal para captura -->
    <div id="myModal" class="modal hide fade" tabindex="-1" data-keyboard="true" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="myModalLabel">Incidente</h3>
      </div>
      <g:form method="post" >
        <div class="modal-body">
          <g:hiddenField name="id" value="-1" />
          <g:hiddenField name="version" value="0" />
          <fieldset class="form">
            <g:render template="form" model="${[incidenteInstance: new Incidente()]}"/>
          </fieldset>
        </div>
          <fieldset class="buttons">
            <g:actionSubmit class="save" action="save" value="${message(code: 'default.button.update.label', default: 'Update')}" />
          </fieldset>
      </g:form>
    </div>

    <!-- Modal para edición -->
    <div id="myEditModal" class="modal fade" tabindex="-1" data-keyboard="true" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="myModalLabel">Incidente</h3>
      </div>
      <div class="modal-body">
      --- rellenar ---
      </div>
    </div>

	</body>
</html>