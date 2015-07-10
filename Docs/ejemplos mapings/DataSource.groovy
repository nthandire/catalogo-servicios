
dataSource_up00 {	
	//dbCreate = "validate"
	url = "jdbc:mysql://localhost:3306/up00"
	driverClassName = "com.mysql.jdbc.Driver"
	dialect = "org.hibernate.dialect.MySQL5InnoDBDialect"
	username = "sopuno"
	password = "mosome1"
	pooled = true
	properties {		
		initialSize = 10
		maxActive = 50
		minIdle = 5
		maxIdle = 25
		maxWait = 10000		
		timeBetweenEvictionRunsMillis = 5000
		minEvictableIdleTimeMillis = 60000
		validationQuery = "SELECT 1"
		validationQueryTimeout = 3		
		testOnBorrow = true
		testWhileIdle = true
		testOnReturn = false		
		defaultTransactionIsolation = java.sql.Connection.TRANSACTION_READ_COMMITTED
	}
}

dataSource_soptec {
	
	//dbCreate = "validate"
	url = "jdbc:mysql://localhost:3306/soptec"
	driverClassName = "com.mysql.jdbc.Driver"
	dialect = "org.hibernate.dialect.MySQL5InnoDBDialect"
	username = "sopuno"
	password = "mosome1"
	pooled = true
	properties {		
		initialSize = 10
		maxActive = 50
		minIdle = 5
		maxIdle = 25
		maxWait = 10000		
		timeBetweenEvictionRunsMillis = 5000
		minEvictableIdleTimeMillis = 60000
		validationQuery = "SELECT 1"
		validationQueryTimeout = 3		
		testOnBorrow = true
		testWhileIdle = true
		testOnReturn = false		
		defaultTransactionIsolation = java.sql.Connection.TRANSACTION_READ_COMMITTED
	}
}

hibernate {
	cache.use_second_level_cache = true
	cache.use_query_cache = true
	cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
	validator.apply_to_ddl = false
	validator.autoregister_listeners = false
}
// environment specific settings
environments {
	development {
			
		dataSource_up00{
			
		}
		
		dataSource_soptec{
		
		}
	}
	test {
		dataSource_up00 {
			/*dbCreate = "update"
			url = "jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000"*/
		}
		dataSource_soptec{
		
		}
	}
	production {
				
		dataSource_up00{
		
		}
		
		dataSource_soptec{
		
		}
	}
}
