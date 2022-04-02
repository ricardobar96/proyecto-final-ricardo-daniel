package es.iespuertodelacruz.daniel.Player2REST.controller;

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

import es.iespuertodelacruz.daniel.Player2REST.dto.PistaDTO;
import es.iespuertodelacruz.daniel.Player2REST.entity.Pista;
import es.iespuertodelacruz.daniel.Player2REST.entity.Usuario;
import es.iespuertodelacruz.daniel.Player2REST.entity.Videojuego;
import es.iespuertodelacruz.daniel.Player2REST.service.PistaService;
import es.iespuertodelacruz.daniel.Player2REST.service.UsuarioService;
import es.iespuertodelacruz.daniel.Player2REST.service.VideojuegoService;

@RestController
@RequestMapping("/api/v0/pista")
public class PistaREST {
	// private Logger logger = (Logger) LoggerFactory.logger(getClass());
	@Autowired
	PistaService pistaService;

	@GetMapping
	public ResponseEntity<?> getAll() {
		List<Pista> l = (List<Pista>) pistaService.findAll();
		List<PistaDTO> listaVid = new ArrayList<>();
		for (Pista pista : l) {
			listaVid.add(new PistaDTO(pista));
		}
		return new ResponseEntity<>(listaVid, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getPistaById(@PathVariable("id") Integer id) {

		Optional<Pista> optPista = pistaService.findById(id);
		if (optPista.isPresent()) {
			return ResponseEntity.ok(new PistaDTO(optPista.get()));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
}
