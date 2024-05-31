package com.learn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.activation.DataSource;


//Security is according to 3.1.4
@Configuration
//@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig  {

	@Autowired
	private CustomerUserDetails customerUserDetails;
	
	//need to add bean to perform database auth
	
	//httpBasic authentication
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		//cross site request forgery
		//csrf is kind of attack ,bu fault postman will protect it
		//if u use put/post method disabling it ,it will throw 403 unauthorized error
		http.csrf().disable()
			//.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
			//.and()
			.authorizeHttpRequests()
			//this can be added to method level
			//add @@EnableMethodSecurity(prePostEnabled = true) in securityConfig class
			//@PreAuthorize("hasRole('admin')") or 
			//@Secured("hasRole('admin')") or 
			//@RolesAllowed("hasRole('admin')")
			.requestMatchers("/public/**").hasRole("USER")
			.requestMatchers("/users").hasRole("ADMIN")
			.anyRequest()
			.authenticated()
			.and()
			.httpBasic();
		//http.authorizeHttpRequests((authz) -> authz.anyRequest().authenticated()).httpBasic();
		return http.build();
	}
	
	
	//In memory authentication which is role based authentication
	//not required as we are database authentication
	@Bean
	public UserDetailsService userDetailsService() {
		//User object is not model class object 
		//import org.springframework.security.core.userdetails.User;
		UserDetails shreedhar = User.builder()
				.username("shreedhar")
				//bcrypt}$2a$10$b3DW2V4NpNf4ckTpQXlN7esSeEFcDk0TZ9T5bP50T8CRxkwJvxSUC
				.password(passwordEncoder().encode("shreedhar")) //spring security will not take direct password
				//.password("12345")
				.roles("USER") 
				.build();
		
		
		UserDetails admin = User.builder()
				.username("admin")
				//bcrypt}$2a$10$VzYDZD4uwcDYtU6MMfR2U.kcFqUXMIAEDh5JInyqM85y/o/HLgGl
				.password(passwordEncoder().encode("admin"))
				//.password("12345")
				.roles("ADMIN")
				.build();
		
		//System.out.println(passwordEncoder().encode("shreedhar") + " :" + passwordEncoder().encode("admin"));
		return new InMemoryUserDetailsManager(shreedhar,admin);
	}
	
	@Bean
	public static PasswordEncoder passwordEncoder() {
		//it is migrated
		//return new BCryptPasswordEncoder();
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
		
		//return NoOpPasswordEncoder.getInstance();
	}
}
