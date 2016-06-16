<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="es" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="es" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="es" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="es" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="es" class="no-js"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="Catálogo de Servicios"/></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
		<link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
		<link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">
		<g:layoutHead/>
    <r:require modules="jquery,jquery-ui,bootstrap"/>
    <r:layoutResources />
    <g:javascript library="jquery" plugin="jquery"/>
	</head>
	<body>
    <g:set var="ss" bean="springSecurityService"/>
		<div id="Logo" role="banner" >
			<table>
				<tr>
					<td width="25%">
						<img src="${resource(dir: 'images', file: 'logo_salud.png')}" alt="" height="180px" width="280px" />
					</td>
					<td>
						<div align="center">
            %{--
                <h5>NO UTILIZAR</h5>
                <h5>Se va a estar tirando el sistema por un tiempo</h5>
                <h5>Sistema de Administración de Servicios de TIC's.</h5>
            --}%
						    <h5>INSTITUTO NACIONAL DE REHABILITACION</h5>
						    <h5>Subdirección de Tecnologías de la Información y Comunicaciones</h5>
						    <h5>Sistema de Administración de Servicios de TIC's</h5>
						</div>
					</td>
					<td width="10%">
						<img src="${resource(dir: 'images', file: 'logo_INR.gif')}" alt="" height="51px" width="68px"/>
					</td>
				</tr>
			</table>
		</div>
		<div class="row-fluid">
			<div class="span3 offset7">
				<sec:ifLoggedIn>
					¡ Bienvenido: <span style="color:blue">${ss.currentUser}</span> !
						<!-- <g:link controller="logout"><span style="color:red">Cerrar Sesion</span></g:link> -->
				</sec:ifLoggedIn>
			</div>
		</div>
		<g:layoutBody/>
		<div class="footer" role="contentinfo">
			<div class="row-fluid">
				<div class="span2 offset1" style="color:#969696">Versión ${grailsApplication.metadata['app.version']}</div>
			</div>
		</div>
		<div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
		<g:javascript library="application"/>
		<r:layoutResources />
	</body>
</html>