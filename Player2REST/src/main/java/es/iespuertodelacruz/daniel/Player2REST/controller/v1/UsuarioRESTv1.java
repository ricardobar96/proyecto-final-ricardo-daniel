package es.iespuertodelacruz.daniel.Player2REST.controller.v1;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.daniel.Player2REST.dto.MensajeDTO;
import es.iespuertodelacruz.daniel.Player2REST.dto.UsuarioDTO;
import es.iespuertodelacruz.daniel.Player2REST.entity.Mensaje;
import es.iespuertodelacruz.daniel.Player2REST.entity.Pista;
import es.iespuertodelacruz.daniel.Player2REST.entity.Usuario;
import es.iespuertodelacruz.daniel.Player2REST.security.GestorDeJWT;
import es.iespuertodelacruz.daniel.Player2REST.service.MensajeService;
import es.iespuertodelacruz.daniel.Player2REST.service.UsuarioService;
import io.jsonwebtoken.Claims;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioRESTv1 {
	// private Logger logger = (Logger) LoggerFactory.logger(getClass());
	@Autowired
	UsuarioService usuarioService;
	@Autowired
	MensajeService mensajeService;

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody UsuarioDTO usuarioDto,
			@RequestHeader(name = "Authorization") String token) {
		Optional<Usuario> optOp = usuarioService.findById(id);
		if (optOp.isPresent()) {
			Usuario usuario = optOp.get();
			GestorDeJWT gestorDeJwt = GestorDeJWT.getInstance();
			String tokenCorregido = token.split(" ")[1].trim();
			Claims claimsGestor = gestorDeJwt.getClaims(tokenCorregido);
			String username = claimsGestor.getSubject();
			if (usuario.getNombre().equals(username)) {
				usuario.setAvatar(usuarioDto.getAvatar());
				usuario.setBanner(usuarioDto.getBanner());
				usuario.setColor(usuarioDto.getColor());
				usuario.setDescripcion(usuarioDto.getDescripcion());
				Usuario usuarioC = null;
				try {
					usuarioC = usuarioService.save(usuario);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return ResponseEntity.ok(usuarioService.save(usuario));
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
						.body("Solo el usuario propietario puede modificar su perfil");
			}
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("el id del registro no existe");
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id, @RequestHeader(name = "Authorization") String token) {
		Optional<Usuario> optOp = usuarioService.findById(id);
		if (optOp.isPresent()) {
			Usuario usuario = optOp.get();
			GestorDeJWT gestorDeJwt = GestorDeJWT.getInstance();
			String tokenCorregido = token.split(" ")[1].trim();
			Claims claimsGestor = gestorDeJwt.getClaims(tokenCorregido);
			String username = claimsGestor.getSubject();
			if (usuario.getNombre().equals(username)) {
				usuario.setActivo((byte) 0);
				Usuario usuarioC = null;
				try {
					usuarioC = usuarioService.save(usuario);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return ResponseEntity.ok(usuarioService.save(usuario));
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
						.body("Solo el usuario propietario puede modificar su perfil");
			}
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("el id del registro no existe");
		}

	}

	/**
	 * @GetMapping("/{idUsuario}/mensajes") public ResponseEntity<?>
	 * getAllMensajes(@PathVariable("idUsuario") Integer idUsuario,
	 * 
	 * @RequestHeader(name = "Authorization") String token) { Optional<Usuario>
	 *                     optOp = usuarioService.findById(idUsuario); if
	 *                     (optOp.isPresent()) { Usuario usuario = optOp.get();
	 *                     GestorDeJWT gestorDeJwt = GestorDeJWT.getInstance();
	 *                     String tokenCorregido = token.split(" ")[1].trim();
	 *                     Claims claimsGestor =
	 *                     gestorDeJwt.getClaims(tokenCorregido); String username =
	 *                     claimsGestor.getSubject(); if
	 *                     (usuario.getNombre().equals(username)) { List<Mensaje> l
	 *                     = (List<Mensaje>) mensajeService.findAll();
	 *                     List<MensajeDTO> listaVid = new ArrayList<>(); for
	 *                     (Mensaje mensaje : l) { if (mensaje.getId() == idUsuario)
	 *                     { listaVid.add(new MensajeDTO(mensaje)); }
	 * 
	 *                     } return new ResponseEntity<>(listaVid, HttpStatus.OK); }
	 *                     else { return
	 *                     ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	 *                     .body("Solo el usuario propietario puede modificar su
	 *                     perfil"); } } else { return
	 *                     ResponseEntity.status(HttpStatus.BAD_REQUEST).body("el id
	 *                     del registro no existe"); } }
	 */

	@DeleteMapping("/{idUsuario}/mensajes/{idMensaje}")
	public ResponseEntity<?> deleteMensaje(@PathVariable Integer idUsuario, @PathVariable Integer idMensaje,
			@RequestHeader(name = "Authorization") String token) {
		Optional<Usuario> optU = usuarioService.findById(idUsuario);
		if (optU.isPresent()) {
			Optional<Mensaje> optM = mensajeService.findById(idMensaje);
			Usuario usuario = optM.get().getAutor();
			GestorDeJWT gestorDeJwt = GestorDeJWT.getInstance();
			String tokenCorregido = token.split(" ")[1].trim();
			Claims claimsGestor = gestorDeJwt.getClaims(tokenCorregido);
			String username = claimsGestor.getSubject();
			if (usuario.getNombre().equals(username)) {
				if (optM.isPresent()) {
					mensajeService.deleteById(idMensaje);
					return ResponseEntity.ok("mensaje borrado");
				} else {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body("el id del mensaje no existe");
				}
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
						.body("Solo el usuario propietario puede modificar su perfil");
			}
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("el id del usuario no existe");
		}

	}

	@GetMapping("/{idUsuario}/mensajes/{idMensaje}")
	public ResponseEntity<?> getMensajeById(@PathVariable("idUsuario") Integer idUsuario,
			@PathVariable("idMensaje") Integer idMensaje, @RequestHeader(name = "Authorization") String token) {
		Optional<Usuario> optU = usuarioService.findById(idUsuario);
		if (optU.isPresent()) {
			Optional<Mensaje> optM = mensajeService.findById(idMensaje);
			Usuario usuario = optM.get().getAutor();
			GestorDeJWT gestorDeJwt = GestorDeJWT.getInstance();
			String tokenCorregido = token.split(" ")[1].trim();
			Claims claimsGestor = gestorDeJwt.getClaims(tokenCorregido);
			String username = claimsGestor.getSubject();
			if (usuario.getNombre().equals(username)) {
				if (optM.isPresent()) {
					return ResponseEntity.ok(new MensajeDTO(optM.get()));
				} else {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body("el id del mensaje no existe");
				}
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
						.body("Solo el usuario propietario puede modificar su perfil");
			}
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("el id del usuario no existe");
		}
	}

	@PutMapping(("/{idUsuario}/mensajes/{idMensaje}"))
	public ResponseEntity<?> updateMensaje(@PathVariable Integer idUsuario,@PathVariable("idMensaje") Integer idMensaje,
			@RequestBody MensajeDTO mensajeDto,
			@RequestHeader(name = "Authorization") String token) {
		Optional<Usuario> optU = usuarioService.findById(idUsuario);
		if (optU.isPresent()) {
			Optional<Mensaje> optM = mensajeService.findById(idMensaje);
			Usuario usuario = optM.get().getAutor();
			GestorDeJWT gestorDeJwt = GestorDeJWT.getInstance();
			String tokenCorregido = token.split(" ")[1].trim();
			Claims claimsGestor = gestorDeJwt.getClaims(tokenCorregido);
			String username = claimsGestor.getSubject();
			if (usuario.getNombre().equals(username)) {
				Mensaje mensaje = optM.get();
				mensaje.setContenido(mensajeDto.getContenido());
				Mensaje mensajeC = null;
				try {
					mensajeC = mensajeService.save(mensaje);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (mensajeC != null) {
					return new ResponseEntity<>(mensajeC, HttpStatus.OK);
				} else {
					return new ResponseEntity<>("Error de conflicto.", HttpStatus.CONFLICT);
				}
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
						.body("Solo el usuario propietario puede modificar su perfil");
			}
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("el id del usuario no existe");
		}

	}
	@PostMapping(("/{idUsuario}/mensajes"))
	public ResponseEntity<?> saveMensaje(@PathVariable Integer idUsuario, @RequestBody MensajeDTO mensajeDto,
			@RequestHeader(name = "Authorization") String token) {
		Optional<Usuario> optU = usuarioService.findById(idUsuario);
		Optional<Usuario> destinatario = usuarioService.findById(mensajeDto.getDestinatario().getId());
		if (optU.isPresent()) {
			Usuario usuario = optU.get();
			GestorDeJWT gestorDeJwt = GestorDeJWT.getInstance();
			String tokenCorregido = token.split(" ")[1].trim();
			Claims claimsGestor = gestorDeJwt.getClaims(tokenCorregido);
			String username = claimsGestor.getSubject();
			if (usuario.getNombre().equals(username)) {
				Mensaje mensaje = new Mensaje();
				mensaje.setAutor(optU.get());
				mensaje.setContenido(mensajeDto.getContenido());
				mensaje.setDestinatario(destinatario.get());
				mensaje.setFecha(BigInteger.valueOf(new Date().getTime()));
				Mensaje mensajeC = null;
				try {
					mensajeC = mensajeService.save(mensaje);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (mensajeC != null) {
					return new ResponseEntity<>(mensajeC, HttpStatus.OK);
				} else {
					return new ResponseEntity<>("Error de conflicto.", HttpStatus.CONFLICT);
				}
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
						.body("Solo el usuario propietario puede modificar su perfil");
			}
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("el id del usuario no existe");
		}

	}
	
	@PostMapping(("/{idUsuario}/follow/{idToFollow}"))
	public ResponseEntity<?> saveFollow(@PathVariable Integer idUsuario,
			@RequestHeader(name = "Authorization") String token, @PathVariable Integer idToFollow) {
		Optional<Usuario> optU = usuarioService.findById(idUsuario);
		Optional<Usuario> optUSeguir = usuarioService.findById(idToFollow);
		if (optU.isPresent() && optUSeguir.isPresent()) {
			Usuario usuario = optU.get();
			Usuario usuarioSeguir = optUSeguir.get();
			GestorDeJWT gestorDeJwt = GestorDeJWT.getInstance();
			String tokenCorregido = token.split(" ")[1].trim();
			Claims claimsGestor = gestorDeJwt.getClaims(tokenCorregido);
			String username = claimsGestor.getSubject();
			if (usuario.getNombre().equals(username)) {
				List<Usuario> followedsList = usuario.getFolloweds();
				List<Usuario> followersList = usuarioSeguir.getFollowers();
				followedsList.add(usuarioSeguir);
				followersList.add(usuario);
				usuario.setFolloweds(followedsList);
				Usuario usuarioC = null;
				try {
					usuarioC = usuarioService.save(usuario);
					usuarioService.save(usuarioSeguir);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (usuarioC != null) {
					return new ResponseEntity<>(usuarioC, HttpStatus.OK);
				} else {
					return new ResponseEntity<>("Error de conflicto.", HttpStatus.CONFLICT);
				}
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
						.body("Solo el usuario propietario puede modificar su perfil");
			}
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("el id de uno de los usuarios no existe");
		}

	}
	
	@DeleteMapping(("/{idUsuario}/follow/{idToFollow}"))
	public ResponseEntity<?> deleteFollow(@PathVariable Integer idUsuario,
			@RequestHeader(name = "Authorization") String token, @PathVariable Integer idToFollow) {
		Optional<Usuario> optU = usuarioService.findById(idUsuario);
		Optional<Usuario> optUSeguir = usuarioService.findById(idToFollow);
		if (optU.isPresent() && optUSeguir.isPresent()) {
			Usuario usuario = optU.get();
			Usuario usuarioSeguir = optUSeguir.get();
			GestorDeJWT gestorDeJwt = GestorDeJWT.getInstance();
			String tokenCorregido = token.split(" ")[1].trim();
			Claims claimsGestor = gestorDeJwt.getClaims(tokenCorregido);
			String username = claimsGestor.getSubject();
			if (usuario.getNombre().equals(username)) {
				List<Usuario> followedsList = usuario.getFolloweds();
				List<Usuario> followersList = usuarioSeguir.getFollowers();
				followedsList.remove(usuarioSeguir);
				followersList.remove(usuario);
				usuario.setFolloweds(followedsList);
				Usuario usuarioC = null;
				try {
					usuarioC = usuarioService.save(usuario);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (usuarioC != null) {
					return new ResponseEntity<>("Eliminado el follow", HttpStatus.OK);
				} else {
					return new ResponseEntity<>("Error de conflicto.", HttpStatus.CONFLICT);
				}
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
						.body("Solo el usuario propietario puede modificar su perfil");
			}
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("el id de uno de los usuarios no existe");
		}

	}
	
	@DeleteMapping(("/{idUsuario}/follower/{idToFollow}"))
	public ResponseEntity<?> deleteFollower(@PathVariable Integer idUsuario,
			@RequestHeader(name = "Authorization") String token, @PathVariable Integer idToFollow) {
		Optional<Usuario> optU = usuarioService.findById(idUsuario);
		Optional<Usuario> optUSeguir = usuarioService.findById(idToFollow);
		if (optU.isPresent() && optUSeguir.isPresent()) {
			Usuario usuario = optU.get();
			Usuario usuarioSeguir = optUSeguir.get();
			GestorDeJWT gestorDeJwt = GestorDeJWT.getInstance();
			String tokenCorregido = token.split(" ")[1].trim();
			Claims claimsGestor = gestorDeJwt.getClaims(tokenCorregido);
			String username = claimsGestor.getSubject();
			if (usuario.getNombre().equals(username)) {
				List<Usuario> followedsList = usuarioSeguir.getFolloweds();
				List<Usuario> followersList = usuario.getFollowers();
				followedsList.remove(usuario);
				followersList.remove(usuarioSeguir);
				usuario.setFollowers(followersList);
				Usuario usuarioC = null;
				try {
					usuarioC = usuarioService.save(usuario);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (usuarioC != null) {
					return new ResponseEntity<>("Eliminado el follow", HttpStatus.OK);
				} else {
					return new ResponseEntity<>("Error de conflicto.", HttpStatus.CONFLICT);
				}
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
						.body("Solo el usuario propietario puede modificar su perfil");
			}
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("el id de uno de los usuarios no existe");
		}

	}
	
}
