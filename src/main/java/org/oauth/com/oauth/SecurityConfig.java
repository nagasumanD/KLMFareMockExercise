package org.oauth.com.oauth;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//@EnableWebFluxSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		.antMatchers("/airports","/fare/{source}/{distination}","/logginResponseTime","/getLoggedData")
		.permitAll().anyRequest().authenticated()
		.and()
		.oauth2Client();
		
	}
//	@Bean
//	public SecurityWebFilterChain configure(ServerHttpSecurity http) throws Exception{
//		http.authorizeExchange().anyExchange().permitAll();
//	return http.build();
//				
//	}

}
