
<%@ page import="mx.gob.inr.catservicios.Cat_bitacora" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'cat_bitacora.label', default: 'Cat_bitacora')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-cat_bitacora" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-cat_bitacora" class="content scaffold-list" role="main">
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

						<g:sortableColumn property="folio" title="${message(code: 'cat_bitacora.folio.label', default: 'Folio')}" />

						<th><g:message code="cat_bitacora.servicio.label" default="Servicio" /></th>

						<g:sortableColumn property="descripcion" title="${message(code: 'cat_bitacora.descripcion.label', default: 'Descripcion')}" />

					</tr>
				</thead>
				<tbody>
				<g:each in="${cat_bitacoraInstanceList}" status="i" var="cat_bitacoraInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

						<td><g:link action="show" id="${cat_bitacoraInstance.id}">
									${cat_bitacoraInstance}
						</g:link></td>

						<td>${fieldValue(bean: cat_bitacoraInstance, field: "servicio")}</td>

						<td>${fieldValue(bean: cat_bitacoraInstance, field: "descripcion")}</td>

					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${cat_bitacoraInstanceTotal}" />
			</div>
<!-- TODO: el formateo de las pantallas con bootstrap, en lugar de table -->
			<div class="row-fluid">
				<div class="span10 offset1">
				  <g:jasperReport
				          controller="Cat_bitacora"
				          action="reporteBitacoraSolicitudesDeCambio"
				          jasper="BitacoraSolicitudesDeCambio"
				          format="pdf"
				          name="Bitacora-de-Solicitudes-de-Cambios"
				          description="Bitácora: Llene los campos a continuación:">
				          <table>
				          	<tr>
				          		<td>
							          de la fecha
				          		</td>
				          		<td>
							          <% def ini = new Date(); ini[Calendar.DATE] = 1; %>
							          <g:datePicker name="startDate" value="${ini}" precision="day" years="${2015..2020}"/>
				          		</td>
				          	</tr>
				          	<tr>
				          		<td>
							          a la fecha
				          		</td>
				          		<td>
							          <g:datePicker name="endDate" value="${new Date()}" precision="day" years="${2015..2020}"/>
				          		</td>
				          	</tr>
				          </table>
				  </g:jasperReport>
				</div>
			</div>

		</div>
	</body>
</html>