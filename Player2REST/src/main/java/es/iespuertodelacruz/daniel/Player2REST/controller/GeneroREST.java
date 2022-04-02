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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.daniel.Player2REST.entity.Genero;
import es.iespuertodelacruz.daniel.Player2REST.service.GeneroService;

@RestController
@RequestMapping("/api/v0/genero")
public class GeneroREST {
	// private Logger logger = (Logger) LoggerFactory.logger(getClass());
	@Autowired
	GeneroService generoService;

	@GetMapping
	public ResponseEntity<?> getAll() {
		List<Genero> l = (List<Genero>) generoService.findAll();
		return new ResponseEntity<>(l, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getGeneroById(@PathVariable("id") Integer id) {

		Optional<Genero> optGenero = generoService.findById(id);
		if (optGenero.isPresent()) {
			return ResponseEntity.ok(optGenero.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}