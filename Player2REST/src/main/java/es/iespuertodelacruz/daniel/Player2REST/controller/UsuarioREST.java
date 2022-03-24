package es.iespuertodelacruz.daniel.Player2REST.controller;

import java.util.ArrayList;
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

import es.iespuertodelacruz.daniel.Player2REST.dto.UsuarioDTO;
import es.iespuertodelacruz.daniel.Player2REST.entity.Usuario;
import es.iespuertodelacruz.daniel.Player2REST.service.UsuarioService;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioREST {
	// private Logger logger = (Logger) LoggerFactory.logger(getClass());
	@Autowired
	UsuarioService usuarioService;

	@GetMapping
	public ResponseEntity<?> getAll() {
		List<Usuario> l = (List<Usuario>) usuarioService.findAll();
		List<UsuarioDTO> listaVid = new ArrayList<>();
		for (Usuario usuario : l) {
			listaVid.add(new UsuarioDTO(usuario));
		}
		return new ResponseEntity<>(listaVid, HttpStatus.OK);
	}

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

	@GetMapping("/{id}")
	public ResponseEntity<?> getUsuarioById(@PathVariable("id") Integer id) {

		Optional<Usuario> optUsuario = usuarioService.findById(id);
		if (optUsuario.isPresent()) {
			return ResponseEntity.ok(new UsuarioDTO(optUsuario.get()));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
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
	@PostMapping
	public ResponseEntity<?> saveUsuario(@RequestBody Usuario usuarioDto) {
		Usuario usuario = new Usuario();
		usuario.setNombre(usuarioDto.getNombre());
		usuario.setPassword(usuarioDto.getPassword());
		usuario.setRol(usuarioDto.getRol());
		usuario.setActivo((byte) 1);
		Usuario usuarioC = null;
		try {
			usuarioC = usuarioService.save(usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (usuarioC != null) {
			return new ResponseEntity<>(usuarioC, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Ya existe ese nick.", HttpStatus.CONFLICT);
		}

	}
}
