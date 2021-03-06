<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Catálogo de Servicios</title>
		<style type="text/css" media="screen">
			#status {
				background-color: #eee;
				border: .2em solid #fff;
				margin: 2em 2em 1em;
				padding: 1em;
				width: 12em;
				float: left;
				-moz-box-shadow: 0px 0px 1.25em #ccc;
				-webkit-box-shadow: 0px 0px 1.25em #ccc;
				box-shadow: 0px 0px 1.25em #ccc;
				-moz-border-radius: 0.6em;
				-webkit-border-radius: 0.6em;
				border-radius: 0.6em;
			}

			.ie6 #status {
				display: inline; /* float double margin fix http://www.positioniseverything.net/explorer/doubled-margin.html */
			}

			#status ul {
				font-size: 0.9em;
				list-style-type: none;
				margin-bottom: 0.6em;
				padding: 0;
			}

			#status li {
				line-height: 1.3;
			}

			#status h1 {
				text-transform: uppercase;
				font-size: 1.1em;
				margin: 0 0 0.3em;
			}

			#page-body {
				margin: 2em 1em 1.25em 18em;
			}

			h2 {
				margin-top: 1em;
				margin-bottom: 0.3em;
				font-size: 1em;
			}

			p {
				line-height: 1.5;
				margin: 0.25em 0;
			}

			#controller-list ul {
				list-style-position: inside;
			}

			#controller-list li {
				line-height: 1.3;
				list-style-position: inside;
				margin: 0.25em 0;
			}

			@media screen and (max-width: 480px) {
				#status {
					display: none;
				}

				#page-body {
					margin: 0 1em 1em;
				}

				#page-body h1 {
					margin-top: 0;
				}
			}
		</style>
	</head>
	<body>
		<a href="#page-body" class="skip"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="page-body" role="main">
			<h1>Catálogo de Servicios</h1>

			<div id="controller-list" role="navigation">
				<h3>Opciones disponibles:</h3>
				<ul>
          <g:set var="firmado" bean="firmadoService"/>
					<g:each var="c" in="${grailsApplication.controllerClasses.findAll{ it.getStaticPropertyValue('ordenMenu', int) >= 0 }.sort{ it.getStaticPropertyValue('ordenMenu', int) } }">
						<g:if test="${c.fullName.indexOf("Dbdoc") == -1 &&
													c.fullName.indexOf("Jasper") == -1 &&
													c.fullName.indexOf("Log") == -1 &&
                          (c.fullName.indexOf("SolicitudAutoriza") == -1 || firmado.autoriza()) &&
                          (c.fullName.indexOf("SolicitudVB") == -1 || firmado.daVB())}" >
						  <sec:access url="/${c.logicalPropertyName}">
								<li><g:link controller="${c.logicalPropertyName}">
									${c.getStaticPropertyValue('nombreMenu', String)}
								</g:link></li>
						  </sec:access>
						</g:if>
						<g:else>
							<g:if test="${c.fullName.indexOf("Logout") == 0}" >
								<sec:ifLoggedIn>
									<li><g:link controller="${c.logicalPropertyName}">
										${c.getStaticPropertyValue('nombreMenu', String)}
									</g:link></li>
								</sec:ifLoggedIn>
							</g:if>
							<g:if test="${c.fullName.indexOf("Login") == 0}" >
								<sec:ifNotLoggedIn>
									<li><g:link controller="${c.logicalPropertyName}">
										${c.getStaticPropertyValue('nombreMenu', String)}
									</g:link></li>
								</sec:ifNotLoggedIn>
							</g:if>
						</g:else>
					</g:each>
				</ul>
			</div>
		</div>
	</body>
</html>
