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

import es.iespuertodelacruz.daniel.Player2REST.dto.VideojuegoDTO;
import es.iespuertodelacruz.daniel.Player2REST.entity.Videojuego;
import es.iespuertodelacruz.daniel.Player2REST.service.VideojuegoService;

@RestController
@RequestMapping("/api/v1/videojuego")
public class VideojuegoREST {
	// private Logger logger = (Logger) LoggerFactory.logger(getClass());
	@Autowired
	VideojuegoService videojuegoService;

	@GetMapping
	public ResponseEntity<?> getAll() {
		List<Videojuego> l = (List<Videojuego>) videojuegoService.findAll();
		List<VideojuegoDTO> listaVid = new ArrayList<>();
		for (Videojuego videojuego : l) {
			listaVid.add(new VideojuegoDTO(videojuego));
		}
		return new ResponseEntity<>(listaVid, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		Optional<Videojuego> optM = videojuegoService.findById(id);
		if (optM.isPresent()) {
			videojuegoService.deleteById(id);
			return ResponseEntity.ok("videojuego borrado");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("el id del videojuego no existe");
		}

	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getVideojuegoById(@PathVariable("id") Integer id) {

		Optional<Videojuego> optVideojuego = videojuegoService.findById(id);
		if (optVideojuego.isPresent()) {
			return ResponseEntity.ok(new VideojuegoDTO(optVideojuego.get()));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	/*

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody VideojuegoDTO videojuegoIn) {
		Optional<Videojuego> optOp = videojuegoService.findById(id);
		if (optOp.isPresent()) {
			Videojuego videojuego = optOp.get();
			videojuego.setNombre(videojuegoIn.getNombre());
			videojuego.setApellidos(videojuegoIn.getApellidos());
			videojuego.setNacionalidad(videojuegoIn.getNacionalidad());
			return ResponseEntity.ok(videojuegoService.save(videojuego));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("el id del registro no existe");
		}
	}

	@PostMapping
	public ResponseEntity<?> saveVideojuego(@RequestBody Videojuego videojuegoDto) {
		Videojuego videojuego = new Videojuego();
		videojuego.setNombre(videojuegoDto.getNombre());
		videojuego.setApellidos(videojuegoDto.getApellidos());
		videojuego.setNacionalidad(videojuegoDto.getNacionalidad());
		Videojuego videojuegoC = null;
		try {
			videojuegoC = videojuegoService.save(videojuego);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (videojuegoC != null) {
			return new ResponseEntity<>(videojuegoC, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Ya existe esa combinación de valores (Nick, Password)", HttpStatus.CONFLICT);
		}

	}*/
}

