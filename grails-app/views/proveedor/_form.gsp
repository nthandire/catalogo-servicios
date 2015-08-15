<%@ page import="mx.gob.inr.catservicios.Proveedor" %>


<style type="text/css">
  textArea { width: 412px; }
</style>


<div class="fieldtablecontain ${hasErrors(bean: proveedorInstance, field: 'desProveedor', 'error')} ">
	<label for="desProveedor">
		<g:message code="proveedor.desProveedor.label" default="Des Proveedor" />
		
	</label>
	<g:textField name="desProveedor" value="${proveedorInstance?.desProveedor}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: proveedorInstance, field: 'representante', 'error')} ">
	<label for="representante">
		<g:message code="proveedor.representante.label" default="Representante" />
		
	</label>
	<g:textField name="representante" value="${proveedorInstance?.representante}"/>
</div>

<div class="fieldtablecontain ${hasErrors(bean: proveedorInstance, field: 'telefono', 'error')} ">
	<label for="telefono">
		<g:message code="proveedor.telefono.label" default="Telefono" />
		
	</label>
	<g:textField name="telefono" maxlength="50" value="${proveedorInstance?.telefono}"/>
</div>

