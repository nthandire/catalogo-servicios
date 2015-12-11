        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
        <sec:access expression="hasAnyRole('ROLE_SAST_GESTOR')">
          <li><g:link class="list" action="listIncidentes">Semaforo de Incidentes</g:link></li>
        </sec:access>
