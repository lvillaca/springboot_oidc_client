package luis;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import luis.idp.KeycloakPrincipalExtractor;
import luis.idp.KeycloakAuthoritiesExtractor;

@EnableOAuth2Sso
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                //.csrf()
                //.disable()
                .authorizeRequests()
                    .antMatchers("/page_error.html", "/login**","/sso/login*")
                    .permitAll()
                    .antMatchers("/monitoramento/**").hasRole("MONITOR_PORTAL")
                    .antMatchers("/servicos/**").hasRole("ADMIN_PORTAL")
                .anyRequest().fullyAuthenticated();
	}

    @Bean
    public PrincipalExtractor keycloakPrincipalExtractor() {
        return new KeycloakPrincipalExtractor();
    }
 
    @Bean
    public AuthoritiesExtractor keycloakAuthoritiesExtractor() {
        return new KeycloakAuthoritiesExtractor();
    }

}
