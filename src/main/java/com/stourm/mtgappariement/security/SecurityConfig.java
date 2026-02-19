package com.stourm.mtgappariement.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration 
@EnableWebSecurity 
public class SecurityConfig {
	
	@Autowired 
	AuthenticationManager authMgr;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws
            Exception {  
	       
	       http.sessionManagement(session -> 
	session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Pas de service de sauvegarde des sessions des users
	       
	    
	    .csrf( csrf -> csrf.disable())	  // Systeme de vulnérabilité des authentification WEb (transmission de requête falsifiée)
	    
//	    .cors(cors -> cors.configurationSource(new CorsConfigurationSource() 
//	  { 
//	              @Override 
//	              public CorsConfiguration getCorsConfiguration(HttpServletRequest 
//	  request) { 
//	                  CorsConfiguration cors = new CorsConfiguration(); 
//	                  
//	  cors.setAllowedOrigins(Collections.singletonList("http://localhost:4200")); 
//	  cors.setAllowedMethods(Collections.singletonList("*")); 
//	  cors.setAllowedHeaders(Collections.singletonList("*")); 
//	  cors.setExposedHeaders(Collections.singletonList("Authorization")); 
//	                  return cors; 
//	              } 
//	          })) 
//
	    .authorizeHttpRequests( requests -> requests 
	            
	    		.requestMatchers("/login").permitAll()
	    		.requestMatchers("/mtg/**").hasAnyAuthority("ADMIN","USER") 
	    	/*	.requestMatchers(HttpMethod.GET,"/api/getbyid/**").hasAnyAuthority("ADMIN","USER") 
	    		.requestMatchers(HttpMethod.POST,"/api/addprod/**").hasAnyAuthority("ADMIN") 
	    		.requestMatchers(HttpMethod.PUT,"/api/updateprod/**").hasAuthority("ADMIN") 
	    		.requestMatchers(HttpMethod.DELETE,"/api/delprod/**").hasAuthority("ADMIN")  */
	    		.anyRequest().authenticated() ) 
	    		 

	    
	    	      .addFilterBefore(new JWTAuthenticationFilter (authMgr), 
	    	        UsernamePasswordAuthenticationFilter.class)
	       
	    	      .addFilterBefore(new 
	    	    		  JWTAuthorizationFilter(),UsernamePasswordAuthenticationFilter.class);
	 
	 return http.build(); 
	 } 
	  
	 

}
