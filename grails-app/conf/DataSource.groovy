// dataSource {
//     pooled = true
//     driverClassName = "org.h2.Driver"
//     username = "sa"
//     password = ""
//     dbCreate = "update" // one of 'create', 'create-drop', 'update', 'validate', ''
//     // url = "jdbc:h2:mem:informixMemDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
//     url = "jdbc:h2:informixDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
// }
dataSource {
    dbCreate = "" // one of 'create', 'create-drop', 'update', 'validate', ''
    // url = "jdbc:informix-sqli://192.168.10.1:1526/sast_dev:informixserver=ol_inrserver" // dev
    // url = "jdbc:informix-sqli://192.168.10.12:1527/sast_dev:informixserver=ol_adminserver" // Piloto
    url = "jdbc:informix-sqli://192.168.10.12:1527/sast:informixserver=ol_adminserver" // Piloto 2
    driverClassName = "com.informix.jdbc.IfxDriver"
    dialect = "org.hibernate.dialect.InformixDialect"
    username = "informix"
    password = "informix"
    pooled = true
}
// dataSource_seguridad {
//     pooled = false
//     driverClassName = "org.h2.Driver"
//     username = "sa"
//     password = ""
//     dbCreate = "update" // one of 'create', 'create-drop', 'update', 'validate', ''
//     url = "jdbc:h2:seguridadDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
// }
dataSource_seguridad {
    dbCreate = "" // one of 'create', 'create-drop', 'update', 'validate', ''
    // url = "jdbc:informix-sqli://192.168.10.12:1527/saihweb:informixserver=ol_adminserver" // dev
    url = "jdbc:informix-sqli://192.168.10.12:1526/saihweb:informixserver=ol_inrserver" // Piloto
    driverClassName = "com.informix.jdbc.IfxDriver"
    dialect = "org.hibernate.dialect.InformixDialect"
    username = "informix"
    password = "informix"
    pooled = true
}
// dataSource_almacen {
//     pooled = false
//     driverClassName = "org.h2.Driver"
//     username = "sa"
//     password = ""
//     dbCreate = "update" // one of 'create', 'create-drop', 'update', 'validate', ''
//     url = "jdbc:h2:almacenDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
// }
dataSource_almacen {
    dbCreate = "" // one of 'create', 'create-drop', 'update', 'validate', ''
    // url = "jdbc:informix-sqli://192.168.10.1:1526/almacenes:informixserver=ol_inrserver" // _dev es la BD original
    // url = "jdbc:informix-sqli://192.168.10.12:1527/almacenes_dev:informixserver=ol_adminserver"
    // url = "jdbc:informix-sqli://192.168.10.1:1526/almacenes:informixserver=ol_inrserver" // dev
    url = "jdbc:informix-sqli://192.168.10.12:1527/almacenes:informixserver=ol_adminserver" // Piloto
    driverClassName = "com.informix.jdbc.IfxDriver"
    dialect = "org.hibernate.dialect.InformixDialect"
    username = "informix"
    password = "informix"
    pooled = true
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}
// environment specific settings
environments {
    development {
      dataSource {}
      dataSource_seguridad {}
      dataSource_almacen {}
    }
    test {
        dataSource {
            dbCreate = "update"
            url = "jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
        }
    }
    production {
      dataSource {}
      dataSource_seguridad {}
      dataSource_almacen {}
    }
}
