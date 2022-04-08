package es.iespuertodelacruz.daniel.Player2REST.controller.v2;
import java.math.BigInteger;
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
import es.iespuertodelacruz.daniel.Player2REST.entity.Genero;
import es.iespuertodelacruz.daniel.Player2REST.entity.Videojuego;
import es.iespuertodelacruz.daniel.Player2REST.service.GeneroService;
import es.iespuertodelacruz.daniel.Player2REST.service.VideojuegoService;

@RestController
@RequestMapping("/api/v2/videojuego")
public class VideojuegoRESTv2 {
	// private Logger logger = (Logger) LoggerFactory.logger(getClass());
	@Autowired
	VideojuegoService videojuegoService;
	@Autowired
	GeneroService generoService;


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
	

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody VideojuegoDTO videojuegoDto) {
		Optional<Videojuego> optOp = videojuegoService.findById(id);
		if (optOp.isPresent()) {
			List<Genero> generos = new ArrayList<>();
			for (Genero genero : videojuegoDto.getGeneros()) {
				Optional<Genero> generoFind = generoService.findById(genero.getId());
				if (generoFind.isPresent()) {
					generos.add(generoFind.get());
				}
				
			}
			Videojuego videojuego = optOp.get();
			videojuego.setNombre(videojuegoDto.getNombre());
			videojuego.setDescripcion(videojuegoDto.getDescripcion());
			videojuego.setGeneros(generos);
			videojuego.setImagen(videojuegoDto.getImagen());
			videojuego.setFecha(BigInteger.valueOf(videojuegoDto.getFecha().getTime()));
			return ResponseEntity.ok(videojuegoService.save(videojuego));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("el id del registro no existe");
		}
	}
}

