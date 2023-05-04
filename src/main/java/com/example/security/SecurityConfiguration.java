package com.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.constant.ApplicationConstants;
import com.example.filter.AuthenticationFilter;
import com.example.filter.AuthorizationFilter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private final UserDetailsService userDetailsService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		log.info("configure authenticating user");
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager());
		authenticationFilter.setFilterProcessesUrl("/api/login");
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests().antMatchers("POST", "/api/login").permitAll()
		.antMatchers(HttpMethod.GET, "/api/roles/**")
		.hasAnyAuthority("ROLE_ADMIN", "ROLE_USER", "ROLE_MANAGER", "ROLE_SUPER_ADMIN")
		.antMatchers(HttpMethod.POST, "/api/roles/**")
		.hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER", "ROLE_SUPER_ADMIN")
		.antMatchers(HttpMethod.PUT, "/api/roles/**")
		.hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER", "ROLE_SUPER_ADMIN")
		.antMatchers(HttpMethod.DELETE, "/api/roles/**")
		.hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER", "ROLE_SUPER_ADMIN")
		.antMatchers(HttpMethod.GET, "/api/users/**")
		.hasAnyAuthority("ROLE_ADMIN", "ROLE_USER", "ROLE_MANAGER", "ROLE_SUPER_ADMIN")
		.antMatchers(HttpMethod.POST, "/api/users/**")
		.hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER", "ROLE_SUPER_ADMIN")
		.antMatchers(HttpMethod.PUT, "/api/users/**")
		.hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER", "ROLE_SUPER_ADMIN")
		.antMatchers(HttpMethod.DELETE, "/api/users/**")
		.hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER", "ROLE_SUPER_ADMIN")
		.antMatchers(HttpMethod.GET, ApplicationConstants.ALL_URLS)
		.hasAnyAuthority("ROLE_ADMIN", "ROLE_USER", "ROLE_MANAGER", "ROLE_SUPER_ADMIN")
		.antMatchers(HttpMethod.POST, ApplicationConstants.ALL_URLS)
		.hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER", "ROLE_SUPER_ADMIN")
		.antMatchers(HttpMethod.PUT, ApplicationConstants.ALL_URLS)
		.hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER", "ROLE_SUPER_ADMIN")
		.antMatchers(HttpMethod.DELETE, ApplicationConstants.ALL_URLS)
		.hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER", "ROLE_SUPER_ADMIN")
				.anyRequest().denyAll();
		http.addFilter(authenticationFilter);
		http.addFilterBefore(new AuthorizationFilter(), AuthenticationFilter.class);
//		http.addFilterBefore(new AuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	
}
