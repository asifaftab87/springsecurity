package org.liferayasif.secure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.DigestAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.DigestAuthenticationFilter;

public class AdminSecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.antMatcher("/support/admin/**")
			.addFilter(getDigestAuthFilter()).exceptionHandling()
			.authenticationEntryPoint(getDigestEntryPoint())
		.and()
			.authorizeRequests().antMatchers("/support/admin/**").hasRole("ADMIN");
	}
	
	public DigestAuthenticationFilter getDigestAuthFilter() throws Exception {
		DigestAuthenticationFilter filter = new DigestAuthenticationFilter();
		filter.setUserDetailsService(userDetailsService());
		filter.setAuthenticationEntryPoint(getDigestEntryPoint());
		return filter;
	}
	
	private DigestAuthenticationEntryPoint getDigestEntryPoint() {
		DigestAuthenticationEntryPoint entryPoint = new DigestAuthenticationEntryPoint();
		entryPoint.setRealmName("admin-digest-realm");
		entryPoint.setKey("fjkf33DD312_+");
		return entryPoint;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	 
		auth.inMemoryAuthentication()
			.withUser("user")
				.password("password1")
				.roles("USER")
		.and()
			.withUser("admin")
				.password("password2")
				.roles("ADMIN");
	}
	
	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		return super.userDetailsService();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
}
