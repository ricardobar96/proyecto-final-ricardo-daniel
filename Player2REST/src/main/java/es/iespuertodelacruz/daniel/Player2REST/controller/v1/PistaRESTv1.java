package es.iespuertodelacruz.daniel.Player2REST.controller.v1;

import java.math.BigInteger;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.daniel.Player2REST.dto.PistaDTO;
import es.iespuertodelacruz.daniel.Player2REST.dto.ReviewDTO;
import es.iespuertodelacruz.daniel.Player2REST.entity.Pista;
import es.iespuertodelacruz.daniel.Player2REST.entity.Review;
import es.iespuertodelacruz.daniel.Player2REST.entity.Usuario;
import es.iespuertodelacruz.daniel.Player2REST.entity.Videojuego;
import es.iespuertodelacruz.daniel.Player2REST.security.GestorDeJWT;
import es.iespuertodelacruz.daniel.Player2REST.service.PistaService;
import es.iespuertodelacruz.daniel.Player2REST.service.UsuarioService;
import es.iespuertodelacruz.daniel.Player2REST.service.VideojuegoService;
import io.jsonwebtoken.Claims;

@RestController
@RequestMapping("/api/v1/pista")
public class PistaRESTv1 {
	// private Logger logger = (Logger) LoggerFactory.logger(getClass());
	@Autowired
	PistaService pistaService;
	@Autowired
	VideojuegoService videojuegoService;
	@Autowired
	UsuarioService usuarioService;
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id, @RequestHeader (name="Authorization") String token) {
		Optional<Pista> optM = pistaService.findById(id);
		if (optM.isPresent()) {
			GestorDeJWT gestorDeJwt = GestorDeJWT.getInstance();
			String tokenCorregido = token.split(" ")[1].trim();
			Claims claimsGestor = gestorDeJwt.getClaims(tokenCorregido);
			String username = claimsGestor.getSubject();
			if (optM.get().getUsuario().getNombre().equals(username)) {
				pistaService.deleteById(id);
				return ResponseEntity.ok("pista borrado");
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Solo el usuario propietario puede borrar sus entradas");
			}
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("el id del pista no existe");
		}

	}


	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody PistaDTO pistaIn, @RequestHeader (name="Authorization") String token) {
		Optional<Pista> optOp = pistaService.findById(id);
		if (optOp.isPresent()) {
			Pista pista = optOp.get();
			GestorDeJWT gestorDeJwt = GestorDeJWT.getInstance();
			String tokenCorregido = token.split(" ")[1].trim();
			Claims claimsGestor = gestorDeJwt.getClaims(tokenCorregido);
			String username = claimsGestor.getSubject();
			if (pista.getUsuario().getNombre().equals(username)) {
				pista.setContenido(pistaIn.getContenido());
				pista.setFecha(BigInteger.valueOf(new Date().getTime()));
				pista.setTitulo(pistaIn.getTitulo());
				Pista pistaC = null;
				try {
					pistaC = pistaService.save(pista);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (pistaC != null) {
					return new ResponseEntity<>(pistaC, HttpStatus.OK);
				} else {
					return new ResponseEntity<>("Ya existe esa combinación de valores (Nick, Password)", HttpStatus.CONFLICT);
				}
			} else {
				return new ResponseEntity<>("Solo el usuario propietario puede crear nuevas entradas", HttpStatus.UNAUTHORIZED);
			}
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("el id del registro no existe");
		}
	}
	
	@PostMapping
	public ResponseEntity<?> savePista(@RequestBody PistaDTO pistaDto, @RequestHeader (name="Authorization") String token) {
		
		Pista pista = new Pista();
		Optional<Videojuego> videojuego = videojuegoService.findById(pistaDto.getVideojuego().getId());
		Optional<Usuario> usuario = usuarioService.findById(pistaDto.getUsuario().getId());
		if (videojuego.get() != null && usuario.get() != null) {
			GestorDeJWT gestorDeJwt = GestorDeJWT.getInstance();
			String tokenCorregido = token.split(" ")[1].trim();
			Claims claimsGestor = gestorDeJwt.getClaims(tokenCorregido);
			String username = claimsGestor.getSubject();
			if (usuario.get().getNombre().equals(username)) {
				pista.setContenido(pistaDto.getContenido());
				pista.setFecha(BigInteger.valueOf(new Date().getTime()));
				pista.setUsuario(usuario.get());
				pista.setVideojuego(videojuego.get());
				pista.setTitulo(pistaDto.getTitulo());
				Pista pistaC = null;
				try {
					pistaC = pistaService.save(pista);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (pistaC != null) {
					return new ResponseEntity<>(pistaC, HttpStatus.OK);
				} else {
					return new ResponseEntity<>("Ya existe esa combinación de valores (Nick, Password)", HttpStatus.CONFLICT);
				}
			} else {
				return new ResponseEntity<>("Solo el usuario propietario puede crear nuevas entradas", HttpStatus.UNAUTHORIZED);
			}
		} 
		else{
			return new ResponseEntity<>("No se encuentra el videojuego o el usuario en la bbdd", HttpStatus.CONFLICT);
		}
		

	}
}
