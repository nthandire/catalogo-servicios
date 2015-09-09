import mx.gob.inr.catservicios.*

def query2 = "  from Usuario u            " +
            " where exists( from UsuarioRol ur      " +
            "              where ur.usuario.id = u.id    " +
            "                and exists         " +
            "                    ( from Rol r        " +
            "                     where r.id = ur.rol.id        " +
            "                       and r.authority = 'ROLE_TECNICO'))        " 

def query = "  from Usuario u            " +
            " where exists( from UsuarioRol ur      " +
            "              where ur.usuario.id = u.id    " +
            "                and exists         " +
            "                    ( from Rol r        " +
            "                     where r.id = ur.rol.id        " +
            "                       and r.authority = 'ROLE_SAST_COORDINADOR_DE_GESTION'))        " 

println Usuario.executeQuery(query)

def queryR = "  from Rol r                          " +
             " where r.authority = 'ROLE_TECNICO'   " 

println Rol.executeQuery(queryR)

def autorizables = Solicitud.withCriteria {
            projections {count()}
            eq ('idVb', (Integer)9)
            eq ('estado', 'A' as char)
        }

println "autorizables = $autorizables"