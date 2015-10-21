<%@ page import="mx.gob.inr.catservicios.Solicitud" %>


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
            <g:link controller="solicitudDetalle" action="show" id="${d.id}">${d?.encodeAsHTML()}</g:link>
          </td>
          <td>${d?.idServ?.servSub?.descripcion}</td>
          <td>${d?.idServ?.descripcion}</td>
          <td>${d?.descripcion}</td>
          <td>${message(code: "cat_servCat.estado.${d?.estado}")}</td>
        </tr>
      </g:each>
      <g:if test="${!solicitudInstance?.estado || solicitudInstance?.estado == 'F' as char}">
        <tr><td>
          <a class="btn" data-toggle="modal" href="#responsive" onclick="detalle()">
            ${message(code: 'default.add.label',
                args: [message(code: 'solicitudDetalle.label',
                  default: 'SolicitudDetalle')])}
          </a>
        </td>
        <td colspan="4"></td>
        </tr>
      </g:if>
    </table>
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
      <g:textArea name="justificacion" cols="40" rows="5" maxlength="1500"
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
