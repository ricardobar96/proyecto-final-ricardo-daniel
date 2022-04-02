package es.iespuertodelacruz.daniel.Player2REST.controller.v1;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.daniel.Player2REST.dto.VideojuegoDTO;
import es.iespuertodelacruz.daniel.Player2REST.entity.Genero;
import es.iespuertodelacruz.daniel.Player2REST.entity.Videojuego;
import es.iespuertodelacruz.daniel.Player2REST.service.GeneroService;
import es.iespuertodelacruz.daniel.Player2REST.service.VideojuegoService;

@RestController
@RequestMapping("/api/v1/videojuego")
public class VideojuegoRESTv1 {
	// private Logger logger = (Logger) LoggerFactory.logger(getClass());
	@Autowired
	VideojuegoService videojuegoService;
	@Autowired
	GeneroService generoService;
	
	@PostMapping
	public ResponseEntity<?> saveVideojuego(@RequestBody VideojuegoDTO videojuegoDto) {
		List<Genero> generos = new ArrayList<>();
		for (Genero genero : videojuegoDto.getGeneros()) {
			Optional<Genero> generoFind = generoService.findById(genero.getId());
			if (generoFind.isPresent()) {
				generos.add(generoFind.get());
			}
			
		}
		Videojuego videojuego = new Videojuego();
		videojuego.setNombre(videojuegoDto.getNombre());
		videojuego.setDescripcion(videojuegoDto.getDescripcion());
		videojuego.setGeneros(generos);
		videojuego.setImagen(videojuegoDto.getImagen());
		videojuego.setFecha(BigInteger.valueOf(videojuegoDto.getFecha().getTime()));
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

	}
}
