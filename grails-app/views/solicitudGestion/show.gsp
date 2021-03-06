<%@ page import="mx.gob.inr.catservicios.*" %>
<!DOCTYPE html>
<html>
 <head>
   <meta name="layout" content="main">
   <g:set var="entityName" value="${message(code: 'solicitud.label', default: 'Solicitud')}" />
   <title><g:message code="default.show.label" args="[entityName]" /></title>
 </head>
 <body>
   <g:set var="servicios" bean="serviciosService"/>
   <a href="#show-solicitud" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
   <div class="nav" role="navigation">
     <ul>
       <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
       <li><g:link class="back" action="${params.back}">Regresar a la lista</g:link></li>
     </ul>
   </div>
   <div id="show-solicitud" class="content scaffold-show" role="main">
     <h1><g:message code="default.show.label" args="[entityName]" /></h1>
     <g:if test="${flash.message}">
       <div class="message" role="status">${flash.message}</div>
     </g:if>
     <g:if test="${flash.error}">
       <div class="errors" role="status">${flash.error}</div>
     </g:if>
     <ol class="property-list solicitud">

       <g:if test="${solicitudInstance?.numeroSolicitud}">
       <li class="fieldcontain">
         <span id="numeroSolicitud-label" class="property-label"><g:message code="solicitud.numeroSolicitud.label" default="Numero Solicitud" /></span>

           <span class="property-value" aria-labelledby="numeroSolicitud-label">${solicitudInstance}</span>

       </li>
       </g:if>

        <div class="row-fluid">
          <div class="span4">
              <span id="nombre-label" class="fieldcontain property-label"><g:message code="solicitud.nombre.label" default="Solicitante" /></span>
          </div>

          <div class="span2" style="width:150px;">
              <span id="fsolicita-label" style="width:100%;" class="fieldcontain property-label">
                Fecha de solicitud
              </span>
          </div>

          <div class="span1">
              <span id="telefono-label" class="fieldcontain property-label">
                <g:message code="solicitud.telefono.label" default="Ext:" />
              </span>
          </div>

          <div class="span5">
              <span id="area-label" class="fieldcontain property-label">
                <g:message code="solicitud.area.label" default="Área" />
              </span>
          </div>
        </div>

        <div class="row-fluid">
          <div class="span4">
            <li class="fieldcontain property-label">
              <span aria-labelledby="nombre-label">
                ${Usuario.get(solicitudInstance?.idSolicitante)}
              </span>
            </li>
          </div>

          <div class="span2" style="width:150px;">
            <li class="fieldcontain">
                <span aria-labelledby="fsolicita-label">
                  ${solicitudInstance.fechaSolicitud}
                </span>
            </li>
          </div>

          <div class="span1">
            <li class="fieldcontain">
                <span aria-labelledby="telefono-label"
                  style="width:100px;">
                  ${solicitudInstance.extension ?: ""}
                </span>
            </li>
          </div>

          <div class="span5">
            <li class="fieldcontain">
                <span style="width:420px;">
                  ${area}
                </span>
            </li>
          </div>
        </div>

        <div class="row-fluid">
          <div class="span4">
              <span id="nombre-label" class="fieldcontain property-label"><g:message code="solicitud.nombre.label" default="Autorizador" /></span>
          </div>
        </div>

        <div class="row-fluid">
          <div class="span4">
            <li class="fieldcontain property-label">
              <span aria-labelledby="nombre-label">
                ${Usuario.get(solicitudInstance?.idAutoriza)}
              </span>
            </li>
          </div>

          <div class="span2" style="width:150px;">
            <li class="fieldcontain">
                <span aria-labelledby="fsolicita-label">
                  ${solicitudInstance.fechaAutoriza}
                </span>
            </li>
          </div>

          <div class="span1">
            <li class="fieldcontain">
                <span aria-labelledby="telefono-label"
                  style="width:100px;">
                  ${servicios.extension([reporta:solicitudInstance?.idAutoriza])}
                </span>
            </li>
          </div>

          <div class="span5">
            <li class="fieldcontain">
                <span style="width:420px;">
                  ${areaAutoriza}
                </span>
            </li>
          </div>
        </div>

        <g:if test="${solicitudInstance?.idVb}">
          <div class="row-fluid">
            <div class="span4">
                <span id="nombre-label" class="fieldcontain property-label"><g:message code="solicitud.nombre.label" default="Visto Bueno" /></span>
            </div>
          </div>

          <div class="row-fluid">
            <div class="span4">
              <li class="fieldcontain property-label">
                <span aria-labelledby="nombre-label">
                  ${Usuario.get(solicitudInstance?.idVb)}
                </span>
              </li>
            </div>

            <div class="span2" style="width:150px;">
              <li class="fieldcontain">
                  <span aria-labelledby="fsolicita-label">
                    ${solicitudInstance.fechaVb}
                  </span>
              </li>
            </div>

            <div class="span1">
              <li class="fieldcontain">
                  <span aria-labelledby="telefono-label"
                    style="width:100px;">
                    ${servicios.extension([reporta:solicitudInstance?.idVb])}
                  </span>
              </li>
            </div>

            <div class="span5">
              <li class="fieldcontain">
                  <span style="width:420px;">
                    ${areaVb}
                  </span>
              </li>
            </div>
          </div>
        </g:if>

       <g:if test="${solicitudInstance?.justificacion}">
         <li class="fieldcontain">
           <span id="justificacion-label" class="property-label">
             <g:message code="solicitud.justificacion.label" default="Justificacion" />
           </span>
           <span class="property-value" aria-labelledby="justificacion-label">
             <g:fieldValue bean="${solicitudInstance}" field="justificacion"/>
           </span>
         </li>
       </g:if>

       <g:if test="${solicitudInstance?.fechaRevisa}">
         <li class="fieldcontain">
           <span id="fechaRevisa-label" class="property-label">
             <g:message code="solicitud.fechaRevisa.label" default="Fecha de Revisión" />
           </span>
           <span class="property-value" aria-labelledby="fechaRevisa-label">
             <g:formatDate date="${solicitudInstance?.fechaVb?:solicitudInstance?.fechaRevisa}" />
           </span>
         </li>
       </g:if>

       <g:if test="${solicitudInstance?.estado}">
         <li class="fieldcontain">
           <span id="estado-label" class="property-label">Estado</span>
           <span class="property-value" aria-labelledby="estado-label">
             <g:select name="estado" disabled="true"
               from="${['F' as char,'A' as char,'R' as char,'V' as char,'E' as char,'T' as char,'C' as char]}"
               valueMessagePrefix="solicitud.estado" value="${solicitudInstance.estado}" />
           </span>
         </li>
       </g:if>

     </ol>


     <H1>
       <g:message code="solicitud.detalles.label" default="Descripción del requerimiento" />
     </H1>

      <div class="row-fluid">
        <div class="span10 offset1">
          <table>
            <g:if test="${solicitudInstance?.detalles}">
              <tr>
                <th>Categoría</th>
                <th>Subcategoría</th>
                <th>Tercer nivel</th>
                <th>Descripción</th>
              </tr>
              <g:each in="${SolicitudDetalle.findAllByIdSolicitudAndEstado(solicitudInstance,
                              'A' as char).sort{it.id}}" var="d">
                <tr>
                  <td><g:link action="edit" id="${d.id}">
                    ${d?.encodeAsHTML()}
                  </g:link></td>
                  <td>${d?.idServ?.servSub?.descripcion}</td>
                  <td>${d?.idServ?.descripcion}</td>
                  <td>${d?.descripcion}</td>
                </tr>
              </g:each>
            </g:if>
          </table>
        </div>
      </div>


     <H1>
       <g:message code="solicitud.archivos.label" default="Archivos" />
     </H1>

     <div class="row-fluid">
         <div class="span10 offset1">
         <ul class="one-to-many">
           <g:if test="${solicitudInstance?.archivos}">
             <g:each in="${solicitudInstance.archivos.sort{it.id}}" var="a">
               <li><g:link controller="solicitudArchivoadjunto"
                           action="download" id="${a.id}">
                    ${a?.encodeAsHTML()}
               </g:link></li>
             </g:each>
           </g:if>
         </ul>
       </div>
     </div>

      <div id="responsiveFirma" class="modal hide fade" tabindex="-1" data-width="512">
        <g:form method="post" >
          <g:hiddenField name="id" value="${solicitudInstance?.id}" />
          <g:hiddenField name="version" value="${solicitudInstance?.version}" />
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            <h3 id="titulo">Marcar como revisado</h3>
          </div>
          <div class="modal-body">
            <div class="row-fluid">
              <div class="span12">
                <h4>Firma digital</h4>
                <fieldset class="form">
                  <g:render template="formFirmar"/>
                </fieldset>
              </div>
            </div>
          </div>
          <div class="modal-footer">
            <fieldset class="buttons">
              <a data-dismiss="modal" class="cancel">Cancelar</a>
              <input type="submit" name="_action_revisar" id="respButton"
                value="${message(code: 'default.button.update.label',
                  default: 'Update')}"
                class="save">
            </fieldset>
          </div>
        </g:form>
      </div>

      <div id="responsive" class="modal hide fade" tabindex="-1" data-width="512">
        <g:form method="post" >
          <g:hiddenField name="id" value="${solicitudInstance?.id}" />
          <g:hiddenField name="version" value="${solicitudInstance?.version}" />
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            <h3 id="titulo">Solicitar un visto bueno</h3>
          </div>
          <div class="modal-body">
            <div class="row-fluid">
              <div class="span12">
                <h4>Firma digital</h4>
                <fieldset class="form">
                  <g:render template="formVistoBueno"/>
                </fieldset>
              </div>
            </div>
          </div>
          <div class="modal-footer">
            <fieldset class="buttons">
              <a data-dismiss="modal" class="cancel">Cancelar</a>
              <input type="submit" name="_action_updateVB" id="respButton"
                value="${message(code: 'default.button.update.label',
                  default: 'Update')}"
                class="save">
            </fieldset>
          </div>
        </g:form>
      </div>

     <g:form>
       <fieldset class="buttons">
         <g:if test="${(solicitudInstance?.estado == 'A' as char ||
                        solicitudInstance?.estado == 'V' as char)}">
           <a class="edit" data-toggle="modal" href="#responsiveFirma">
             Marcar como revisado
           </a>
           <g:if test="${(solicitudInstance?.estado != 'V' as char)}">
             <a class="edit" data-toggle="modal" href="#responsive">
               Pedir un visto bueno
             </a>
           </g:if>
         </g:if>
       </fieldset>
     </g:form>
   </div>
 </body>
</html>
