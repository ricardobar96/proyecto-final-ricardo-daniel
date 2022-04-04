package es.iespuertodelacruz.daniel.Player2REST.controller.v1;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.daniel.Player2REST.dto.JuegoUsuarioDTO;
import es.iespuertodelacruz.daniel.Player2REST.entity.JuegoUsuario;
import es.iespuertodelacruz.daniel.Player2REST.entity.Usuario;
import es.iespuertodelacruz.daniel.Player2REST.entity.Videojuego;
import es.iespuertodelacruz.daniel.Player2REST.security.GestorDeJWT;
import es.iespuertodelacruz.daniel.Player2REST.service.JuegoUsuarioService;
import es.iespuertodelacruz.daniel.Player2REST.service.UsuarioService;
import es.iespuertodelacruz.daniel.Player2REST.service.VideojuegoService;
import io.jsonwebtoken.Claims;

@RestController
@RequestMapping("/api/v1/juegousuario")
public class JuegoUsuarioRESTv1 {
	// private Logger logger = (Logger) LoggerFactory.logger(getClass());
	@Autowired
	JuegoUsuarioService juegousuarioService;
	@Autowired
	VideojuegoService videojuegoService;
	@Autowired
	UsuarioService usuarioService;
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id, @RequestHeader (name="Authorization") String token) {
		Optional<JuegoUsuario> optM = juegousuarioService.findById(id);
		if (optM.isPresent()) {
			GestorDeJWT gestorDeJwt = GestorDeJWT.getInstance();
			String tokenCorregido = token.split(" ")[1].trim();
			Claims claimsGestor = gestorDeJwt.getClaims(tokenCorregido);
			String username = claimsGestor.getSubject();
			if (optM.get().getUsuario().getNombre().equals(username)) {
				juegousuarioService.deleteById(id);
				return ResponseEntity.ok("juegousuario borrado");
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Solo el usuario propietario puede borrar sus entradas");
			}
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("el id del juegousuario no existe");
		}
	}
	/*

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody JuegoUsuarioDTO juegousuarioIn) {
		Optional<JuegoUsuario> optOp = juegousuarioService.findById(id);
		if (optOp.isPresent()) {
			JuegoUsuario juegousuario = optOp.get();
			juegousuario.setNombre(juegousuarioIn.getNombre());
			juegousuario.setApellidos(juegousuarioIn.getApellidos());
			juegousuario.setNacionalidad(juegousuarioIn.getNacionalidad());
			return ResponseEntity.ok(juegousuarioService.save(juegousuario));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("el id del registro no existe");
		}
	}
*/
	@PostMapping
	public ResponseEntity<?> saveJuegoUsuario(@RequestBody JuegoUsuarioDTO juegousuarioDto, @RequestHeader (name="Authorization") String token) {
		
			JuegoUsuario juegousuario = new JuegoUsuario();
			Optional<Videojuego> videojuego = videojuegoService.findById(juegousuarioDto.getVideojuego().getId());
			Optional<Usuario> usuario = usuarioService.findById(juegousuarioDto.getUsuario().getId());
			if (videojuego.get() != null && usuario.get() != null) {
				GestorDeJWT gestorDeJwt = GestorDeJWT.getInstance();
				String tokenCorregido = token.split(" ")[1].trim();
				Claims claimsGestor = gestorDeJwt.getClaims(tokenCorregido);
				String username = claimsGestor.getSubject();
				if (usuario.get().getNombre().equals(username)) {
					juegousuario.setCompletado((byte) (juegousuarioDto.isCompletado() ? 1 : 0));
					juegousuario.setHoras(juegousuarioDto.getHoras());
					juegousuario.setVideojuego(videojuego.get());
					juegousuario.setPuntuacion(juegousuarioDto.getPuntuacion());
					juegousuario.setUsuario(usuario.get());
					JuegoUsuario juegousuarioC = null;
					try {
						juegousuarioC = juegousuarioService.save(juegousuario);
					} catch (Exception e) {
						e.printStackTrace();
					}
					if (juegousuarioC != null) {
						return new ResponseEntity<>(juegousuarioC, HttpStatus.OK);
					} else {
						return new ResponseEntity<>("Ya existe esa combinación de valores (Nick, Password)", HttpStatus.CONFLICT);
					} 
				} else{
					return new ResponseEntity<>("Solo el usuario propietario puede crear nuevas entradas", HttpStatus.UNAUTHORIZED);
				}
			} else{
				return new ResponseEntity<>("No se encuentra el videojuego o el usuario en la bbdd", HttpStatus.CONFLICT);
			}
	}
}
