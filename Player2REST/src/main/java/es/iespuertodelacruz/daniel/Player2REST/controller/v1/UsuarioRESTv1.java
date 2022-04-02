package es.iespuertodelacruz.daniel.Player2REST.controller.v1;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.daniel.Player2REST.dto.MensajeDTO;
import es.iespuertodelacruz.daniel.Player2REST.entity.Mensaje;
import es.iespuertodelacruz.daniel.Player2REST.entity.Usuario;
import es.iespuertodelacruz.daniel.Player2REST.service.MensajeService;
import es.iespuertodelacruz.daniel.Player2REST.service.UsuarioService;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioRESTv1 {
	// private Logger logger = (Logger) LoggerFactory.logger(getClass());
	@Autowired
	UsuarioService usuarioService;
	@Autowired
	MensajeService mensajeService;
	/*

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody UsuarioDTO usuarioIn) {
		Optional<Usuario> optOp = usuarioService.findById(id);
		if (optOp.isPresent()) {
			Usuario usuario = optOp.get();
			usuario.setNombre(usuarioIn.getNombre());
			usuario.setApellidos(usuarioIn.getApellidos());
			usuario.setNacionalidad(usuarioIn.getNacionalidad());
			return ResponseEntity.ok(usuarioService.save(usuario));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("el id del registro no existe");
		}
	}*/
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		Optional<Usuario> optM = usuarioService.findById(id);
		if (optM.isPresent()) {
			usuarioService.deleteById(id);
			return ResponseEntity.ok("usuario borrado");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("el id del usuario no existe");
		}

	}
	@GetMapping("/{idUsuario}/mensajes")
	public ResponseEntity<?> getAllMensajes(@PathVariable("idUsuario") Integer idUsuario) {
		List<Mensaje> l = (List<Mensaje>) mensajeService.findAll();
		List<MensajeDTO> listaVid = new ArrayList<>();
		for (Mensaje mensaje : l) {
			listaVid.add(new MensajeDTO(mensaje));
		}
		return new ResponseEntity<>(listaVid, HttpStatus.OK);
	}

	@DeleteMapping("/{idUsuario}/mensajes/{idMensaje}")
	public ResponseEntity<?> deleteMensaje(@PathVariable Integer idUsuario, @PathVariable Integer idMensaje) {
		Optional<Usuario> optU = usuarioService.findById(idUsuario);
			if (optU.isPresent()) {
			Optional<Mensaje> optM = mensajeService.findById(idMensaje);
			if (optM.isPresent()) {
				mensajeService.deleteById(idMensaje);
				return ResponseEntity.ok("mensaje borrado");
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("el id del mensaje no existe");
			} 
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("el id del usuario no existe");
		}

	}

	@GetMapping("/{idUsuario}/mensajes/{idMensaje}")
	public ResponseEntity<?> getMensajeById(@PathVariable("idUsuario") Integer idUsuario, @PathVariable("idMensaje") Integer idMensaje) {
		Optional<Usuario> optU = usuarioService.findById(idUsuario);
		if (optU.isPresent()) {
			Optional<Mensaje> optMensaje = mensajeService.findById(idMensaje);
			if (optMensaje.isPresent()) {
				return ResponseEntity.ok(new MensajeDTO(optMensaje.get()));
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("el id del mensaje no existe");
			}
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("el id del usuario no existe");
		}
	}
	/*

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody MensajeDTO mensajeIn) {
		Optional<Mensaje> optOp = mensajeService.findById(id);
		if (optOp.isPresent()) {
			Mensaje mensaje = optOp.get();
			mensaje.setNombre(mensajeIn.getNombre());
			mensaje.setApellidos(mensajeIn.getApellidos());
			mensaje.setNacionalidad(mensajeIn.getNacionalidad());
			return ResponseEntity.ok(mensajeService.save(mensaje));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("el id del registro no existe");
		}
	}*/
	@PostMapping(("/{idUsuario}/mensajes"))
	public ResponseEntity<?> saveMensaje(@PathVariable Integer idUsuario, @RequestBody MensajeDTO mensajeDto) {
		Optional<Usuario> optU = usuarioService.findById(idUsuario);
		Optional<Usuario> destinatario = usuarioService.findById(mensajeDto.getDestinatario().getId());
		if (optU.isPresent()) {
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
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("el id del usuario no existe");
		}
		

	}
}
