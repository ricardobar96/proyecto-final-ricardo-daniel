package es.iespuertodelacruz.daniel.Player2REST;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter;
import org.springframework.web.cors.CorsUtils;

import es.iespuertodelacruz.daniel.Player2REST.security.FiltroJWT;


@SpringBootApplication
public class Player2RestApplication {

	public static void main(String[] args) {
		SpringApplication.run(Player2RestApplication.class, args);
	}

	@EnableGlobalMethodSecurity(prePostEnabled = true)
	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {
		@Override
		public void configure(WebSecurity webSecurity) throws Exception
		{
			webSecurity
			.ignoring()
			.antMatchers(HttpMethod.POST, "/api/login")
			.antMatchers(HttpMethod.GET, "/api/v0/**");
			//.antMatchers("/api/v1/**");
			//.antMatchers("/**");
		}
		@Override
	    protected void configure(HttpSecurity http) throws Exception {
	    	
			
			http
            .addFilterBefore(new CustomCorsFilter(), WebAsyncManagerIntegrationFilter.class)
            .csrf().disable()
            .addFilterBefore(new FiltroJWT(), UsernamePasswordAuthenticationFilter.class)
            .authorizeRequests()
            .antMatchers("/api/v1/**").hasAnyRole("ADMIN","USER")
            .antMatchers("/api/v2/**").hasRole("ADMIN")
            .anyRequest().authenticated();
	    	
	    	
	    	http
	    	 .exceptionHandling()
	    	 .authenticationEntryPoint((request, response, e) ->
	    	 {

	    	response.setContentType("application/json;charset=UTF-8");
	    	 response.setStatus(HttpServletResponse.SC_FORBIDDEN);
	    	 response.getWriter().write(new JSONObject()
	    	 .put("timestamp", LocalDateTime.now())
	    	 .put("message", "token no correctamente autenticado")
	    	 .toString());
	    	 });
		
	    }	
	}
}
