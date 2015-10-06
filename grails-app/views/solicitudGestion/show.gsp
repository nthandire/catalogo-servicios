<%@ page import="mx.gob.inr.catservicios.*" %>
<!DOCTYPE html>
<html>
 <head>
   <meta name="layout" content="main">
   <g:set var="entityName" value="${message(code: 'solicitud.label', default: 'Solicitud')}" />
   <title><g:message code="default.show.label" args="[entityName]" /></title>
 </head>
 <body>
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

            <li class="fieldcontain">
              <span id="nombre-label" class="property-label"><g:message code="solicitud.nombre.label" default="Solicitante" /></span>
              <span class="property-value" aria-labelledby="nombre-label">${Usuario.get(solicitudInstance?.idSolicitante)}</span>
            </li>

          </div>
          <div class="span3">

            <li class="fieldcontain">
              <span id="telefono-label" class="property-label">
                <g:message code="solicitud.telefono.label" default="Extensión" />
              </span>
                <span class="property-value" aria-labelledby="telefono-label">
                  ${Usuario.get(solicitudInstance?.idSolicitante).extension}
                </span>
            </li>

          </div>
          <div class="span3">

            <li class="fieldcontain">
              <span id="area-label" class="property-label">
                <g:message code="solicitud.area.label" default="Área" />
              </span>
                <span class="property-value" aria-labelledby="area-label">
                  ${UsuarioAutorizado.get(solicitudInstance?.idSolicitante).area}
                </span>
            </li>

          </div>
        </div>

        <div class="row-fluid">
          <div class="span4">

            <li class="fieldcontain">
              <span id="nombre-label" class="property-label"><g:message code="solicitud.nombre.label" default="Autorizador" /></span>
              <span class="property-value" aria-labelledby="nombre-label">${Usuario.get(solicitudInstance?.idAutoriza)}</span>
            </li>

          </div>
          <div class="span3">

            <li class="fieldcontain">
              <span id="telefono-label" class="property-label">
                <g:message code="solicitud.telefono.label" default="Extensión" />
              </span>
                <span class="property-value" aria-labelledby="telefono-label">
                  ${Usuario.get(solicitudInstance?.idAutoriza).extension}
                </span>
            </li>

          </div>
          <div class="span3">

            <li class="fieldcontain">
              <span id="area-label" class="property-label">
                <g:message code="solicitud.area.label" default="Área" />
              </span>
                <span class="property-value" aria-labelledby="area-label">
                  ${UsuarioAutorizado.get(solicitudInstance?.idAutoriza).area}
                </span>
            </li>

          </div>
        </div>

        <g:if test="${solicitudInstance?.idVb}">
          <div class="row-fluid">
            <div class="span4">

              <li class="fieldcontain">
                <span id="nombre-label" class="property-label"><g:message code="solicitud.nombre.label" default="Visto Bueno" /></span>
                <span class="property-value" aria-labelledby="nombre-label">${Usuario.get(solicitudInstance?.idVb)}</span>
              </li>

            </div>
            <div class="span3">

              <li class="fieldcontain">
                <span id="telefono-label" class="property-label">
                  <g:message code="solicitud.telefono.label" default="Extensión" />
                </span>
                  <span class="property-value" aria-labelledby="telefono-label">
                    ${Usuario.get(solicitudInstance?.idVb).extension}
                  </span>
              </li>

            </div>
            <div class="span3">

              <li class="fieldcontain">
                <span id="area-label" class="property-label">
                  <g:message code="solicitud.area.label" default="Área" />
                </span>
                  <span class="property-value" aria-labelledby="area-label">
                    ${UsuarioAutorizado.get(solicitudInstance?.idVb).area}
                  </span>
              </li>

            </div>
          </div>
        </g:if>

       <g:if test="${solicitudInstance?.justificacion}">
       <li class="fieldcontain">
         <span id="justificacion-label" class="property-label"><g:message code="solicitud.justificacion.label" default="Justificacion" /></span>

           <span class="property-value" aria-labelledby="justificacion-label"><g:fieldValue bean="${solicitudInstance}" field="justificacion"/></span>

       </li>
       </g:if>

       <g:if test="${solicitudInstance?.lastUpdated}">
       <li class="fieldcontain">
         <span id="lastUpdated-label" class="property-label"><g:message code="solicitud.lastUpdated.label" default="Fecha Modificacion" /></span>

           <span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${solicitudInstance?.lastUpdated}" /></span>

       </li>
       </g:if>

       <g:if test="${solicitudInstance?.estado}">
       <li class="fieldcontain">
         <span id="estado-label" class="property-label">Estado</span>

         <span class="property-value" aria-labelledby="estado-label">
           <g:select name="estado" disabled="true"
             from="${['F' as char, 'A' as char, 'R' as char, 'V' as char, 'E' as char, 'T' as char, 'C' as char]}"
             valueMessagePrefix="solicitud.estado" value="${solicitudInstance.estado}" />
         </span>
       </li>
       </g:if>

     </ol>


     <H1>
       <g:message code="solicitud.detalles.label" default="Descripción de la solicitud" />
     </H1>

     <div class="row-fluid">
-        <div class="span10 offset1">
         <ul class="one-to-many">
           <g:each in="${solicitudInstance?.detalles}" var="d">
             <li>
               <g:link action="edit" id="${d.id}">${d?.encodeAsHTML()}</g:link>
               ${d?.idServ?.servSub?.descripcion}
               ${d?.idServ?.descripcion}
             </li>
           </g:each>
         </ul>
       </div>
     </div>


     <H1>
       <g:message code="solicitud.archivos.label" default="Archivos" />
     </H1>

     <div class="row-fluid">
         <div class="span10 offset1">
         <ul class="one-to-many">
           <g:each in="${solicitudInstance?.archivos}" var="a">
             <li><g:link action="showArchivo" id="${a.id}">${a?.encodeAsHTML()}</g:link></li>
           </g:each>
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
