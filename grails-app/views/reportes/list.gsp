
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
			<div class="row-fluid">
				<div class="span10 offset1">
				  <g:jasperReport
				          controller="Reportes"
				          action="reporteResultadosYSeguimiento"
				          jasper="ResultadosYSeguimiento"
				          format="pdf"
				          name="Resultados Y Seguimiento"
				          description="Consulta:: Llene los campos a continuación:">
				          <table>
				          	<tr>
				          		<td>
							          de la fecha
				          		</td>
				          		<td>
							          <% def ini = new Date(); ini[Calendar.DATE] = 1; %>
							          <g:datePicker name="startDate" value="${ini}" precision="day" years="${2015..2025}"/>
				          		</td>
				          	</tr>
				          	<tr>
				          		<td>
							          a la fecha
				          		</td>
				          		<td>
							          <g:datePicker name="endDate" value="${new Date()}" precision="day" years="${2015..2025}"/>
				          		</td>
				          	</tr>
				          </table>
				  </g:jasperReport>
				</div>
			</div>

		</div>
	</body>
</html>