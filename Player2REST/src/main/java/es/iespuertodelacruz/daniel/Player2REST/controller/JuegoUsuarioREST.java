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

import es.iespuertodelacruz.daniel.Player2REST.dto.JuegoUsuarioDTO;
import es.iespuertodelacruz.daniel.Player2REST.entity.JuegoUsuario;
import es.iespuertodelacruz.daniel.Player2REST.entity.Usuario;
import es.iespuertodelacruz.daniel.Player2REST.entity.Videojuego;
import es.iespuertodelacruz.daniel.Player2REST.service.JuegoUsuarioService;
import es.iespuertodelacruz.daniel.Player2REST.service.UsuarioService;
import es.iespuertodelacruz.daniel.Player2REST.service.VideojuegoService;

@RestController
@RequestMapping("/api/v0/juegousuario")
public class JuegoUsuarioREST {
	// private Logger logger = (Logger) LoggerFactory.logger(getClass());
	@Autowired
	JuegoUsuarioService juegousuarioService;

	@GetMapping
	public ResponseEntity<?> getAll() {
		List<JuegoUsuario> l = (List<JuegoUsuario>) juegousuarioService.findAll();
		List<JuegoUsuarioDTO> listaVid = new ArrayList<>();
		for (JuegoUsuario juegousuario : l) {
			listaVid.add(new JuegoUsuarioDTO(juegousuario));
		}
		return new ResponseEntity<>(listaVid, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getJuegoUsuarioById(@PathVariable("id") Integer id) {

		Optional<JuegoUsuario> optJuegoUsuario = juegousuarioService.findById(id);
		if (optJuegoUsuario.isPresent()) {
			return ResponseEntity.ok(new JuegoUsuarioDTO(optJuegoUsuario.get()));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}