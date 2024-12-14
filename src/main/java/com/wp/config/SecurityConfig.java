package com.wp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.wp.jwt.AuthEntryPoint;
import com.wp.jwt.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	public static final String AUTHENTICATE = "/authenticate";
	public static final String REGISTER = "/register";
	public static final String REFRESH_TOKEN = "/refreshToken";
	public static final String SENDERS = "/senders";
	public static final String CONVERSATION = "/conversation";
	
	
	@Autowired
	private AuthenticationProvider authenticationProvider;
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Autowired
	private AuthEntryPoint authEntryPoint;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeHttpRequests(request-> 
		 request.requestMatchers(AUTHENTICATE , REGISTER , REFRESH_TOKEN,SENDERS,CONVERSATION, "/api/users/register","/api/events/{id}","/api/events/","/api/messages/send","/api/messages/chat","/api/messages/conversation","/api/messages/senders")
		.permitAll()
		.anyRequest()
		.authenticated())
		.exceptionHandling().authenticationEntryPoint(authEntryPoint).and()
		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authenticationProvider(authenticationProvider)
		.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
	

   
}
