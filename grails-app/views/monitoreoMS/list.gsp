<%@ page import="mx.gob.inr.catservicios.*" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main">
    <meta http-equiv="refresh" content="300">
    <g:set var="entityName" value="${message(code: 'author.label', default: 'Requerimientos')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
    <style type="text/css">
    .ui-jqgrid .ui-jqgrid-labels .ui-th-column>div {height: auto}
    </style>
</head>

<body>
<a href="#list-requerimiento" class="skip" tabindex="-1">
  <g:message code="default.link.skip.label"
    default="Skip to content&hellip;"/>
</a>

<div class="nav" role="navigation">
      <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
      </ul>
</div>

<div id="list-requerimiento" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <g:render template="jqgrid"/>

</div>
</body>
</html>
