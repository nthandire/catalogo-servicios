package mx.gob.inr.catservicios

import org.codehaus.groovy.grails.plugins.springsecurity.GrailsUser
import org.codehaus.groovy.grails.plugins.springsecurity.GrailsUserDetailsService
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import org.springframework.security.core.authority.GrantedAuthorityImpl
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException

class FirmaService implements GrailsUserDetailsService {

   static final List NO_ROLES = [new GrantedAuthorityImpl(SpringSecurityUtils.NO_ROLE)]

   UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      Usuario.withTransaction { status ->
         Usuario user = Usuario.findByUsernameIlike(username)
         if (!user) throw new UsernameNotFoundException('User not found', username)

         def authorities = user.authorities.collect { new GrantedAuthorityImpl(it.authority) }

         new GrailsUser(user.username, user.password, user.enabled, !user.accountExpired, !user.passwordExpired,
            !user.accountLocked, authorities ?: NO_ROLES, user.id)
      }
   }

   UserDetails loadUserByUsername(String username, boolean loadRoles) throws UsernameNotFoundException {
      loadUserByUsername username
   }
}