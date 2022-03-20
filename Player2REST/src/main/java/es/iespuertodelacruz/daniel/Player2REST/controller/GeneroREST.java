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
@RequestMapping("/api/v1/genero")
public class GeneroREST {
	// private Logger logger = (Logger) LoggerFactory.logger(getClass());
	@Autowired
	GeneroService generoService;

	@GetMapping
	public ResponseEntity<?> getAll() {
		List<Genero> l = (List<Genero>) generoService.findAll();
		return new ResponseEntity<>(l, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		Optional<Genero> optM = generoService.findById(id);
		if (optM.isPresent()) {
			generoService.deleteById(id);
			return ResponseEntity.ok("genero borrado");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("el id del genero no existe");
		}

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

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Genero generoIn) {
		Optional<Genero> optOp = generoService.findById(id);
		if (optOp.isPresent()) {
			Genero genero = optOp.get();
			genero.setNombre(generoIn.getNombre());
			return ResponseEntity.ok(generoService.save(genero));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("el id del Genero no existe");
		}
	}

	@PostMapping
	public ResponseEntity<?> saveGenero(@RequestBody Genero generoDto) {
		Genero genero = new Genero();
		genero.setNombre(generoDto.getNombre());
		Genero generoC = null;
		try {
			generoC = generoService.save(genero);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (generoC != null) {
			return new ResponseEntity<>(generoC, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Ya existe ese género.", HttpStatus.CONFLICT);
		}

	}
}