eventCreateWarStart = { warName, myDir ->
	println 'BORRANDO jars!'

	ant.delete(file:"${myDir}/WEB-INF/lib/cglib-2.2.jar")
	ant.delete(file:"${myDir}/WEB-INF/lib/jta-1.1.jar")
	ant.delete(file:"${myDir}/WEB-INF/lib/ifxjdbc.jar")
	//ant.delete(file:"${myDir}/WEB-INF/lib/jasperreports-4.7.0.jar")
	//ant.delete(file:"${myDir}/WEB-INF/lib/itext-2.1.7.jar")
	//       ant.delete(file:"${myDir}/WEB-INF/lib/groovy-all-2.0.8.jar")
	//       ant.delete(file:"${myDir}/WEB-INF/lib/h2-1.3.164.jar")
	ant.delete(file:"${myDir}/WEB-INF/lib/castor-1.2.jar")
	ant.delete(file:"${myDir}/WEB-INF/lib/jfreechart-1.0.12.jar")
	ant.delete(file:"${myDir}/WEB-INF/lib/antlr-2.7.7.jar")
	ant.delete(file:"${myDir}/WEB-INF/lib/xmlbeans-2.3.0.jar")
	ant.delete(file:"${myDir}/WEB-INF/lib/stax-api-1.0.1.jar")


	def deleteJars = new File("${myDir}/WEB-INF/lib/").listFiles().findAll {
		if( it.name.startsWith("hibernate") || it.name.startsWith("poi") ||
			 it.name.startsWith("jackson") || it.name.startsWith("bcmail") ||
			 it.name.startsWith("bcprov") || it.name.startsWith("bctsp")){
			it.delete()
		}
	}

	ant.delete(file:"${myDir}/WEB-INF/lib/log4j-1.2.16.jar") // log4j supplied by JBoss
	ant.delete(file:"${myDir}/WEB-INF/classes/log4j.properties") // logging conf done in JBoss only
	ant.delete(file:"${myDir}/WEB-INF/lib/slf4j-api-1.6.2.jar") // slf4j supplied by JBoss 5+
	ant.delete(file:"${myDir}/WEB-INF/lib/jcl-over-slf4j-1.6.2.jar") // jcl supplied by JBoss as well
	ant.delete(file:"${myDir}/WEB-INF/lib/jul-to-slf4j-1.6.2.jar")
 }