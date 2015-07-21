import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

class LogoutController {
    def nombreMenu = "Salir"
    def ordenMenu = 72

	/**
	 * Index action. Redirects to the Spring security logout uri.
	 */
	def index = {
		// TODO put any pre-logout code here
		redirect uri: SpringSecurityUtils.securityConfig.logout.filterProcessesUrl // '/j_spring_security_logout'
	}
}
