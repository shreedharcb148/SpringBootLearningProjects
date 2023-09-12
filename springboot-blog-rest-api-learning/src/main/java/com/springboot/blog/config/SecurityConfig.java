package com.springboot.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.springboot.blog.security.JwtAuthenticationEntryPoint;
import com.springboot.blog.security.JwtAuthenticationFilter;



@Configuration
//@EnableMethodSecurity
public class SecurityConfig {
	
	//@Autowired
	UserDetailsService userDetailsService;

	
	private JwtAuthenticationEntryPoint authenticationEntryPoint;
	
	private JwtAuthenticationFilter authenticationFilter;
	
	
	
	public SecurityConfig() {
		super();
	}

	public SecurityConfig(UserDetailsService userDetailsService,
			JwtAuthenticationEntryPoint authenticationEntryPoint,
			JwtAuthenticationFilter authenticationFilter) {
		this.userDetailsService = userDetailsService;
		this.authenticationEntryPoint = authenticationEntryPoint;
		this.authenticationFilter = authenticationFilter;
	}

	@Bean
	public static PasswordEncoder passwordEncoder() {
		//it is migrated
		//return new BCryptPasswordEncoder();
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	/*
	 * AuthenticationManager uses UserDetailsService to get user detail from the database
	 * and also it uses passwordEncoder to encode the password no need to explicitly provide password encoder
	 * spring 5.2 on words spring security  automatically provides user detail to AuthenticationManager
	 */
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {	
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		
		//enabling basic authentication
		httpSecurity.csrf().disable()
				.authorizeHttpRequests((authorize)->
				//authorize.anyRequest().authenticated()
				
				authorize.requestMatchers(HttpMethod.GET,"/api/**").permitAll()
					.requestMatchers(HttpMethod.POST,"/api/auth/**").permitAll()
					.anyRequest().authenticated())
				
				.httpBasic(Customizer.withDefaults());
				//.exceptionHandling(ex-> ex.authenticationEntryPoint(authenticationEntryPoint))
				//configure session management policy
				//.sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		
		//httpSecurity.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
		return httpSecurity.build();
	}
	
	
	//In memory authentication which is role based authentication
	//not required as we are database authentication
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails shreedhar = User.builder()
				.username("shreedhar")
				.password(passwordEncoder().encode("shreedhar")) //spring security will not take direct password
				.roles("USER") 
				.build();
		
		
		UserDetails admin = User.builder()
				.username("admin")
				.password(passwordEncoder().encode("admin"))
				.roles("ADMIN")
				.build();
		
		return new InMemoryUserDetailsManager(shreedhar,admin);
	}
}
