package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService useDetailsService()
	{
		UserDetails admin =User.withUsername("pooja1")
				.password(passwordEncoder().encode("password"))
				.roles("ADMIN")
				.build();
		
		UserDetails u1 = User.withUsername("pooja")
				.password(passwordEncoder().encode("abc"))
				.roles("XYZ")
				.build();
		
//		   InMemoryUserDetailsManager inMemoryUserDetailsManager =new InMemoryUserDetailsManager();
		return new InMemoryUserDetailsManager(admin,u1);
		
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity.csrf().disable()
	     .authorizeHttpRequests()
	    
	     .requestMatchers("/home/admin")
	     .hasRole("ADMIN")
	     .requestMatchers("/home/xyz")
	     .hasRole("XYZ")	   
	     .requestMatchers("/home/public")
	     .permitAll()
	     .anyRequest()
	     .authenticated()
        .and()
        .formLogin();
	     
	     return httpSecurity.build();
	}
	
	
	

}
