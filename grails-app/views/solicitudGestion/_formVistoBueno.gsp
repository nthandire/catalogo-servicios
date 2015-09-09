<%@ page import="mx.gob.inr.catservicios.*" %>


<style type="text/css">
  textArea { width: 812px; }
</style>


<div class="fieldtablecontain ${hasErrors(bean: solicitudInstance, field: 'justificacion', 'error')} ">
	<label for="justificacion">
		<g:message code="solicitud.justificacion.label" default="Justificacion" />
		
	</label>
	<g:textArea name="justificacion" cols="40" rows="5" maxlength="1500"
    value="${solicitudInstance?.justificacion}" disabled="true"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: solicitudInstance, field: 'idVb', 'error')} ">
  <label for="idVb">
    <g:message code="solicitudDetalle.idVb.label" default="Responsable" />
    
  </label>
  <g:select id="idVb" name="idVb" 
    from="${Usuario.executeQuery("from Usuario u where exists( from UsuarioRol ur where ur.usuario.id = u.id and exists ( from Rol r where r.id = ur.rol.id and r.authority = 'ROLE_USUARIO'))")}" optionKey="id" value="${solicitudInstance?.idVb}" class="many-to-one" noSelection="['null': '']"/>
</div>
