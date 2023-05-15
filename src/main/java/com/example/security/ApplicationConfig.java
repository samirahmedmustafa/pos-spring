package com.example.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.repository.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

	private final EmployeeRepo employeeRepo;
	
//	private final UserDetailsService userDetailsService;
//	private final BCryptPasswordEncoder bCryptPasswordEncoder;
//	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		log.info("configure authenticating user");
//		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager());
//		authenticationFilter.setFilterProcessesUrl("/api/login");
//		http.csrf().disable();
//		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//		http.authorizeRequests().antMatchers("POST", "/api/login").permitAll()
//		.antMatchers(HttpMethod.GET, "/api/roles/**")
//		.hasAnyAuthority("ROLE_ADMIN", "ROLE_USER", "ROLE_MANAGER", "ROLE_SUPER_ADMIN")
//		.antMatchers(HttpMethod.POST, "/api/roles/**")
//		.hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER", "ROLE_SUPER_ADMIN")
//		.antMatchers(HttpMethod.PUT, "/api/roles/**")
//		.hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER", "ROLE_SUPER_ADMIN")
//		.antMatchers(HttpMethod.DELETE, "/api/roles/**")
//		.hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER", "ROLE_SUPER_ADMIN")
//		.antMatchers(HttpMethod.GET, "/api/users/**")
//		.hasAnyAuthority("ROLE_ADMIN", "ROLE_USER", "ROLE_MANAGER", "ROLE_SUPER_ADMIN")
//		.antMatchers(HttpMethod.POST, "/api/users/**")
//		.hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER", "ROLE_SUPER_ADMIN")
//		.antMatchers(HttpMethod.PUT, "/api/users/**")
//		.hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER", "ROLE_SUPER_ADMIN")
//		.antMatchers(HttpMethod.DELETE, "/api/users/**")
//		.hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER", "ROLE_SUPER_ADMIN")
//		.antMatchers(HttpMethod.GET, ApplicationConstants.ALL_URLS)
//		.hasAnyAuthority("ROLE_ADMIN", "ROLE_USER", "ROLE_MANAGER", "ROLE_SUPER_ADMIN")
//		.antMatchers(HttpMethod.POST, ApplicationConstants.ALL_URLS)
//		.hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER", "ROLE_SUPER_ADMIN")
//		.antMatchers(HttpMethod.PUT, ApplicationConstants.ALL_URLS)
//		.hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER", "ROLE_SUPER_ADMIN")
//		.antMatchers(HttpMethod.DELETE, ApplicationConstants.ALL_URLS)
//		.hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER", "ROLE_SUPER_ADMIN")
//				.anyRequest().denyAll();
//		http.addFilter(authenticationFilter);
//		http.addFilterBefore(new AuthorizationFilter(), AuthenticationFilter.class);
////		http.addFilterBefore(new AuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
//	}
//
//	@Bean
//	@Override
//	protected AuthenticationManager authenticationManager() throws Exception {
//		return super.authenticationManager();
//	}

	@Bean
	public UserDetailsService userDetailsService() {
		return username -> employeeRepo.findByAccountId(username).orElseThrow(() -> new UsernameNotFoundException("accountId " + username + " not found!"));
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());	
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		
		return config.getAuthenticationManager();
		
	}
	
	@Bean
	private PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
