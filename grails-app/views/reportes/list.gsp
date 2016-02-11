
<%@ page import="mx.gob.inr.catservicios.*" %>
%{--
<g:javascript src="servicios.js" />
--}%
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
			</ul>
		</div>
		<div id="list-cat_bitacora" class="content scaffold-list" role="main">
			<h1>Reportes</h1>
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
				          description="Resultados Y Seguimiento. Requerimientos">
				          <table>
				          	<tr>
				          		<td>
							          del mes:
				          		</td>
				          		<td>
							          <% def ini = new Date(); ini[Calendar.DATE] = 1; %>
							          <g:datePicker name="startDate" value="${ini}" precision="month" years="${2015..2025}"/>
				          		</td>
				          	</tr>
				          </table>
				  </g:jasperReport>
				</div>
			</div>

			<div class="row-fluid">
				<div class="span10 offset1">
				  <g:jasperReport
				          controller="Reportes"
				          action="reporteInciResultadosYSeguimiento"
				          jasper="ResultadosYSeguimiento"
				          format="pdf"
				          name="Resultados Y Seguimiento"
				          description="Resultados Y Seguimiento. Incidentes">
				          <table>
				          	<tr>
				          		<td>
							          del mes:
				          		</td>
				          		<td>
							          <% def ini = new Date(); ini[Calendar.DATE] = 1; %>
							          <g:datePicker name="startDate" value="${ini}" precision="month" years="${2015..2025}"/>
				          		</td>
				          	</tr>
				          </table>
				  </g:jasperReport>
				</div>
			</div>

			<div class="row-fluid">
				<div class="span10 offset1">
				  <g:jasperReport
				          controller="Reportes"
				          action="reporteMonitoreoOLAySLA"
				          jasper="MonitoreoOLAySLA"
				          format="pdf"
				          name="Monitoreo de OLA y SLA"
				          description="Monitoreo de OLA y SLA">
				          <table>
				          	<tr>
				          		<td>
							          del mes:
				          		</td>
				          		<td>
							          <% def ini = new Date(); ini[Calendar.DATE] = 1; %>
							          <g:datePicker name="startDate" value="${ini}" precision="month" years="${2015..2025}"/>
				          		</td>
				          	</tr>
				          </table>
				  </g:jasperReport>
				</div>
			</div>

			<div class="row-fluid">
				<div class="span10 offset1">
				  <g:jasperReport
				          controller="Reportes"
				          action="reporteSolicitudes"
				          jasper="Solicitudes"
				          format="xls"
				          name="Solicitudes de Servicio"
				          description="Solicitudes de Servicio">
				          <table>
				          	<tr>
				          		<td>
							          de la fecha:
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
				          <table>
                    <tr>
                      <td>
                        del número de solicitud&nbsp;&nbsp;
                      	<g:field type="number" name="folio" min="0" value="0"/>
                      </td>
                      <td>
                        del año&nbsp;&nbsp;<g:datePicker name="anioFolio" value="${new Date()}" precision="year" years="${2015..2025}"/>
                      </td>
				          </table>
				          %{--
				          <table>
                    <tr>
                      <td>
                        del empleado&nbsp;&nbsp;
								        <g:select id="idReporta" name="idReporta"
								          from="${Usuario.findAllEnabled().sort { it?.nombreMostrar }}"
								          value="${incidenteInstance?.idReporta}" class="many-to-one"
								          noSelection="${['':'']}" optionKey="id"
								          optionValue="nombreMostrar"/>
                      </td>
                      <td>
                      Extención&nbsp;&nbsp;
        							<g:field type="text" id="extension" name="extension"
        								disabled="true" value=""/>
                      </td>
				          </table>
				          --}%
				          <table>
                    <tr>
                      <td>
                        del número de Inventario&nbsp;&nbsp;
                      	<g:field type="number" name="inventario" min="0" value="0"/>
                      </td>
				          </table>
				          <table>
                    <tr>
                      <td>
                        del estado&nbsp;&nbsp;
                      	<g:select name="estado" from="${['primer nivel', 'segundo nivel', 'tercer nivel', 'asignado', 'cancelado', 'encuesta', 'revisado', 'terminado', 'visto bueno', 'Mantenimiento', 'aprovisionamiento', 'autorizado']}"
          								noSelection="['':'']"/>
                      </td>
				          </table>
				  </g:jasperReport>
				</div>
			</div>

			<div class="row-fluid">
				<div class="span10 offset1">
				  <g:jasperReport
				          controller="Reportes"
				          action="reportePortafolio"
				          jasper="Portafolio"
				          format="pdf"
				          name="PORTAFOLIO DE SERVICIOS POR CATEGORIA"
				          description="Portafolio De Servicios Por Categoría">
				  </g:jasperReport>
				</div>
			</div>

			<div class="row-fluid">
				<div class="span10 offset1">
				  <g:jasperReport
				          controller="Reportes"
				          action="reporteSubcategoria"
				          jasper="Subcategorias"
				          format="pdf"
				          name="PORTAFOLIO DE SERVICIOS POR SUBCATEGORIA"
				          description="Portafolio De Servicios Por Subcategoria">
				  </g:jasperReport>
				</div>
			</div>

			<div class="row-fluid">
				<div class="span10 offset1">
				  <g:jasperReport
				          controller="Reportes"
				          action="reporteServicios"
				          jasper="Servicios"
				          format="pdf"
				          name="CATALOGO DE SERVICIOS DE TIC"
				          description="Catálogo De Servicios De TIC">
				  </g:jasperReport>
				</div>
			</div>

			<div class="row-fluid">
				<div class="span10 offset1">
				  <g:jasperReport
				          controller="Reportes"
				          action="reporteNiveles"
				          jasper="Niveles"
				          format="pdf"
				          name="Tiempo de solución por nivel de atención - Requerimientos"
				          description="Tiempo de solución por nivel de atención - Requerimientos">
				          <table>
				          	<tr>
				          		<td>
							          del mes:
				          		</td>
				          		<td>
							          <% def ini = new Date(); ini[Calendar.DATE] = 1; %>
							          <g:datePicker name="startDate" value="${ini}" precision="month" years="${2015..2025}"/>
				          		</td>
				          	</tr>
				          </table>
				  </g:jasperReport>
				</div>
			</div>

			<div class="row-fluid">
				<div class="span10 offset1">
				  <g:jasperReport
				          controller="Reportes"
				          action="reporteInciNiveles"
				          jasper="Niveles"
				          format="pdf"
				          name="Tiempo de solución por nivel de atención - Incidentes"
				          description="Tiempo de solución por nivel de atención - Incidentes">
				          <table>
				          	<tr>
				          		<td>
							          del mes:
				          		</td>
				          		<td>
							          <% def ini = new Date(); ini[Calendar.DATE] = 1; %>
							          <g:datePicker name="startDate" value="${ini}" precision="month" years="${2015..2025}"/>
				          		</td>
				          	</tr>
				          </table>
				  </g:jasperReport>
				</div>
			</div>

			<div class="row-fluid">
				<div class="span10 offset1">
				  <g:jasperReport
				          controller="Reportes"
				          action="reporteTiempos"
				          jasper="Tiempos"
				          format="pdf"
				          name="Tiempo de solución"
				          description="Tiempo de solución - Requerimientos">
				          <table>
				          	<tr>
				          		<td>
							          del mes:
				          		</td>
				          		<td>
							          <% def ini = new Date(); ini[Calendar.DATE] = 1; %>
							          <g:datePicker name="startDate" value="${ini}" precision="month" years="${2015..2025}"/>
				          		</td>
				          	</tr>
				          </table>
				  </g:jasperReport>
				</div>
			</div>

			<div class="row-fluid">
				<div class="span10 offset1">
				  <g:jasperReport
				          controller="Reportes"
				          action="reporteInciTiempos"
				          jasper="Tiempos"
				          format="pdf"
				          name="Tiempo de solución"
				          description="Tiempo de solución - Incidentes">
				          <table>
				          	<tr>
				          		<td>
							          del mes:
				          		</td>
				          		<td>
							          <% def ini = new Date(); ini[Calendar.DATE] = 1; %>
							          <g:datePicker name="startDate" value="${ini}" precision="month" years="${2015..2025}"/>
				          		</td>
				          	</tr>
				          </table>
				  </g:jasperReport>
				</div>
			</div>

		</div>
	</body>
</html>