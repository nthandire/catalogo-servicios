grails.servlet.version = "2.5" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.target.level = 1.6
grails.project.source.level = 1.6
grails.server.port.http = 8080
grails.project.war.file = "target/${appName}-${appVersion}.war"
// grails.project.war.file = "target/${appName}.war"

// uncomment (and adjust settings) to fork the JVM to isolate classpaths
//grails.project.fork = [
//   run: [maxMemory:1024, minMemory:64, debug:false, maxPerm:256]
//]

grails.project.dependency.resolution = {
  // inherit Grails' default dependencies
  inherits("global") {
    // specify dependency exclusions here; for example, uncomment this to disable ehcache:
    // excludes 'ehcache'
  }
  log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
  checksums true // Whether to verify checksums on resolve
  legacyResolve false // whether to do a secondary resolve on plugin installation, not advised and here for backwards compatibility

  repositories {
    inherits true // Whether to inherit repository definitions from plugins

    grailsPlugins()
    grailsHome()
    grailsCentral()

    mavenLocal()
    mavenCentral()

    // uncomment these (or add new ones) to enable remote dependency resolution from public Maven repositories
    //mavenRepo "http://snapshots.repository.codehaus.org"
    //mavenRepo "http://repository.codehaus.org"
    //mavenRepo "http://download.java.net/maven/2/"
    //mavenRepo "http://repository.jboss.com/maven2/"
    mavenRepo "http://repo.grails.org/grails/repo/"
    mavenRepo "http://jaspersoft.artifactoryonline.com/jaspersoft/third-party-ce-artifacts/"
  }

  dependencies {
    // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes e.g.

    // runtime 'mysql:mysql-connector-java:5.1.22'
    compile ('cglib:cglib-nodep:2.2')
  }

  plugins {
      runtime ":hibernate:$grailsVersion"
      runtime ":jquery:1.10.2"
      runtime ":resources:1.2"
      runtime ":twitter-bootstrap:2.3.2"
      runtime ":database-migration:1.3.2"

      // Uncomment these (or add new ones) to enable additional resources capabilities
      //runtime ":zipped-resources:1.0"
      //runtime ":cached-resources:1.0"
      //runtime ":yui-minify-resources:0.1.5"

      build ":tomcat:$grailsVersion"
      build ':jbossas:1.0'


      compile ":jquery-ui:1.10.4"
      compile ':cache:1.0.1'
      compile ':spring-security-core:1.2.7.3'
      compile ":jasper:1.11.0"
      compile ":mail:1.0.7"
  }

  environments {
    development {
      // relative to the project directory (the directory that contains the web-app directory)
      // jasper.dir.reports = '../src/reports'
    }
    production {
        // absolute path
        // jasper.dir.reports = '/home/sampleuser/Jasper-Reports'
    }
  }
}
