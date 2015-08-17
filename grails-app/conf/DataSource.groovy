dataSource {
    pooled = true
    driverClassName = "org.h2.Driver"
    username = "sa"
    password = ""
    dbCreate = "update" // one of 'create', 'create-drop', 'update', 'validate', ''
    url = "jdbc:h2:mem:informixMemDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
    // url = "jdbc:h2:informixDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
}
dataSource_informix {
    dbCreate = "" // one of 'create', 'create-drop', 'update', 'validate', ''
    url = "jdbc:informix-sqli://192.168.10.1:1526/sast_dev:informixserver=ol_inrserver"
    driverClassName = "com.informix.jdbc.IfxDriver"
    dialect = "org.hibernate.dialect.InformixDialect"
    username = "informix"
    password = "informix"
    pooled = true
}
dataSource_catServ {
    pooled = true
    driverClassName = "org.h2.Driver"
    username = "sa"
    password = ""
    dbCreate = "update" // one of 'create', 'create-drop', 'update', 'validate', ''
    url = "jdbc:h2:catServDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
}
dataSource_seguridad {
    pooled = true
    driverClassName = "org.h2.Driver"
    username = "sa"
    password = ""
    dbCreate = "update" // one of 'create', 'create-drop', 'update', 'validate', ''
    url = "jdbc:h2:seguridadDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
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
      dataSource_catServ {}
      dataSource_seguridad {}
    }
    test {
        dataSource {
            dbCreate = "update"
            url = "jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
        }
    }
    production {
        dataSource {
            dbCreate = "update"
            url = "jdbc:h2:prodDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
            pooled = true
            properties {
               maxActive = -1
               minEvictableIdleTimeMillis=1800000
               timeBetweenEvictionRunsMillis=1800000
               numTestsPerEvictionRun=3
               testOnBorrow=true
               testWhileIdle=true
               testOnReturn=true
               validationQuery="SELECT 1"
            }
        }
    }
}
