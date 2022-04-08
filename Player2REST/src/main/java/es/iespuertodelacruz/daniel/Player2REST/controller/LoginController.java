package es.iespuertodelacruz.daniel.Player2REST.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.daniel.Player2REST.entity.Usuario;
import es.iespuertodelacruz.daniel.Player2REST.security.GestorDeJWT;
import es.iespuertodelacruz.daniel.Player2REST.service.UsuarioService;

@RestController
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	/*  funciona el form urlencode */
	@PostMapping(path = "/api/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<?> login(@RequestParam("name") String username, @RequestParam("password") String pwd) {
		
		
		String token = getJWTToken(username,pwd);
		
		//token nulo si usuario/pass no es válido
		if( token != null) {
			return ResponseEntity.ok(token);
		}else
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("usuario/pass erróneos");
		
	}
	
	
	static class UsuarioJsonLogin{
		String name;
		String password;
		public String getName() { return name;};
		public String getPassword() {return password;};
		public void setName(String name ) {this.name = name;};
		public void setPassword(String password ) {this.password = password;};
			
	}
	
	/* json post */
	@PostMapping(path = "/api/login", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> login(@RequestBody UsuarioJsonLogin usuarioJson) {
		
		
		String token = getJWTToken(usuarioJson.name, usuarioJson.password);
		
		//token nulo si usuario/pass no es válido
		if( token != null) {
			return ResponseEntity.ok(token);
		}else
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("usuario/pass erróneos");
		
	}	

	@Autowired
	UsuarioService usuarioService;
	
	
	private String getJWTToken(String username, String passTextoPlanoRecibida) {
		
		String respuesta = null;
		
		GestorDeJWT gestorDeJWT = GestorDeJWT.getInstance();
		
		Usuario usuario = usuarioService.findByNombre(username);
		
		if (usuario.getActivo() == 1) {
			String passwordUsuarioEnHash = "";
	        boolean autenticado = false;
	        
	        if(usuario != null) { 
	        	passwordUsuarioEnHash = usuario.getPassword();
	        	
	        	autenticado = BCrypt.checkpw(passTextoPlanoRecibida, passwordUsuarioEnHash);
	        	
	        }
	        
	   
	      
			if(autenticado) {

					
				String rol = usuario.getRol();
				List<String> roles = new ArrayList<String>(); 
				roles.add(rol);
				logger.info("los roles obtenidos: " + roles);
				

				int duracionMinutos = 600;
				
				String token = gestorDeJWT.generarToken(username, roles, duracionMinutos);
				
				respuesta = gestorDeJWT.BEARERPREFIX + token;			
			}
		}
        
        
		
		return respuesta;

	}	

}
