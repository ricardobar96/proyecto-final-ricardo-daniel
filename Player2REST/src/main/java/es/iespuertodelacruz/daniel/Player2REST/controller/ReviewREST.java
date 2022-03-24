package es.iespuertodelacruz.daniel.Player2REST.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.daniel.Player2REST.dto.ReviewDTO;
import es.iespuertodelacruz.daniel.Player2REST.entity.Review;
import es.iespuertodelacruz.daniel.Player2REST.entity.Usuario;
import es.iespuertodelacruz.daniel.Player2REST.entity.Videojuego;
import es.iespuertodelacruz.daniel.Player2REST.service.ReviewService;
import es.iespuertodelacruz.daniel.Player2REST.service.UsuarioService;
import es.iespuertodelacruz.daniel.Player2REST.service.VideojuegoService;

@RestController
@RequestMapping("/api/v1/review")
public class ReviewREST {
	// private Logger logger = (Logger) LoggerFactory.logger(getClass());
	@Autowired
	ReviewService reviewService;
	@Autowired
	VideojuegoService videojuegoService;
	@Autowired
	UsuarioService usuarioService;

	@GetMapping
	public ResponseEntity<?> getAll() {
		List<Review> l = (List<Review>) reviewService.findAll();
		List<ReviewDTO> listaVid = new ArrayList<>();
		for (Review review : l) {
			listaVid.add(new ReviewDTO(review));
		}
		return new ResponseEntity<>(listaVid, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		Optional<Review> optM = reviewService.findById(id);
		if (optM.isPresent()) {
			reviewService.deleteById(id);
			return ResponseEntity.ok("review borrado");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("el id del review no existe");
		}

	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getReviewById(@PathVariable("id") Integer id) {

		Optional<Review> optReview = reviewService.findById(id);
		if (optReview.isPresent()) {
			return ResponseEntity.ok(new ReviewDTO(optReview.get()));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	/*

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody ReviewDTO reviewIn) {
		Optional<Review> optOp = reviewService.findById(id);
		if (optOp.isPresent()) {
			Review review = optOp.get();
			review.setNombre(reviewIn.getNombre());
			review.setApellidos(reviewIn.getApellidos());
			review.setNacionalidad(reviewIn.getNacionalidad());
			return ResponseEntity.ok(reviewService.save(review));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("el id del registro no existe");
		}
	}
*/
	@PostMapping
	public ResponseEntity<?> saveReview(@RequestBody ReviewDTO reviewDto) {
		
		Review review = new Review();
		Optional<Videojuego> videojuego = videojuegoService.findById(reviewDto.getVideojuego().getId());
		Optional<Usuario> usuario = usuarioService.findById(reviewDto.getUsuario().getId());
		if (videojuego.get() != null && usuario.get() != null) {
			review.setContenido(reviewDto.getContenido());
			review.setFecha(BigInteger.valueOf(reviewDto.getFecha().getTime()));
			review.setUsuario(usuario.get());
			review.setVideojuego(videojuego.get());
			review.setTitulo(reviewDto.getTitulo());
			Review reviewC = null;
			try {
				reviewC = reviewService.save(review);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (reviewC != null) {
				return new ResponseEntity<>(reviewC, HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Ya existe esa combinación de valores (Nick, Password)", HttpStatus.CONFLICT);
			}
		} else{
			return new ResponseEntity<>("No se encuentra el videojuego o el usuario en la bbdd", HttpStatus.CONFLICT);
		}
		

	}
}
