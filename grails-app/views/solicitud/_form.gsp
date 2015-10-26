<%@ page import="mx.gob.inr.catservicios.*" %>


<style type="text/css">
  textArea { width: 412px; }
</style>


<div class="row-fluid">
  <div class="span10 offset1">
    <p style="height:10px;"> </p>
    <table>
      <g:if test="${solicitudInstance?.detalles}">
        <tr>
          <th>Categoría</th>
          <th>Subcategoría</th>
          <th>Tercer nivel</th>
          <th>Descripción</th>
          <th>Estado</th>
        </tr>
      </g:if>
      <g:each in="${solicitudInstance?.detalles.sort {it.id}}" var="d">
        <tr>
          <td>
            <a data-toggle="modal" href="#responsive"
              onclick="detalle(${d.id}, ${d.idServcat.id}, '${d.descripcion}', ${d.idResguardoentregadetalle?:0}, '${d.estado}')">
              ${d?.encodeAsHTML()}
            </a>
            <%--
            <g:link controller="solicitudDetalle" action="edit" id="${d.id}">${d?.encodeAsHTML()}</g:link>
            --%>
          </td>
          <td>${d?.idServ?.servSub?.descripcion}</td>
          <td>${d?.idServ?.descripcion}</td>
          <td>${d?.descripcion}</td>
          <td>${message(code: "cat_servCat.estado.${d?.estado}")}</td>
        </tr>
      </g:each>
      <g:if test="${!solicitudInstance?.estado || solicitudInstance?.estado == 'F' as char}">
        <tr><td>
          <a class="btn" data-toggle="modal" href="#responsive"
            onclick="detalleNuevo()">
            ${message(code: 'default.add.label',
                args: [message(code: 'solicitudDetalle.label',
                  default: 'SolicitudDetalle')])}</a>
        </td>
        <td colspan="4"></td>
        </tr>
      </g:if>
    </table>
  </div>
</div>


<h1> </h1>
<p style="height:40px;"> </p>


<H1>
  <g:message code="solicitud.archivos.label" default="Archivos" />
</H1>

<div class="row-fluid">
  <div class="span10 offset1">
    <ul class="one-to-many" style="list-style-type:decimal;">
      <g:if test="${solicitudInstance?.archivos}">
        <g:each in="${solicitudInstance.archivos.sort{it.id}}" var="a">
          <li><g:link controller="solicitudArchivoadjunto" action="download"
            id="${a.id}">${a?.encodeAsHTML()}</g:link></li>
        </g:each>
      </g:if>
      <g:if test="${((!solicitudInstance?.estado || solicitudInstance.estado == 'F' as char) &&
               (!solicitudInstance?.archivos || solicitudInstance.archivos.size() < 2))}">
        <li class="add">
          <g:link class="btn" controller="solicitudArchivoadjunto" action="create"
            params="['solicitud.id': solicitudInstance?.id]">
            ${message(code: 'default.add.label',
              args: [message(code: 'solicitudArchivoadjunto.label',
                default: 'SolicitudArchivoadjunto')])}
          </g:link>
        </li>
      </g:if>
    </ul>
  </div>
</div>


<h1> </h1>
<p style="height:40px;"> </p>


<div class="row-fluid">
  <div class="span6">
    <div class="fieldtablecontain ${hasErrors(bean: solicitudInstance, field: 'justificacion', 'error')} ">
      <label for="justificacion">
        <g:message code="solicitud.justificacion.label" default="Justificacion" />
      </label>
      <g:textArea id="justificacion" name="justificacion" cols="40" rows="5" maxlength="1500"
        value="${solicitudInstance?.justificacion}"/>
    </div>
  </div>

  <div class="span6">
    <div class="fieldtablecontain ${hasErrors(bean: solicitudInstance, field: 'idAutoriza', 'error')} ">
      <label for="idAutoriza">
        <g:message code="solicitud.idAutoriza.label" default="Autoriza" />
      </label>
      <g:select id="idAutoriza" name="idAutoriza" from="${autorizadores}"
        optionKey="id" optionValue="nombreMostrar" value="${solicitudInstance?.idAutoriza}" class="many-to-one"
        noSelection="['': '']"/>
    </div>
  </div>
</div>
