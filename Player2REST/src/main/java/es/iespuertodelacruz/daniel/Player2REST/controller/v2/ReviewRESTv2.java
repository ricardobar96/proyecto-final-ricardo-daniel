package es.iespuertodelacruz.daniel.Player2REST.controller.v2;

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

import es.iespuertodelacruz.daniel.Player2REST.dto.ReviewDTO;
import es.iespuertodelacruz.daniel.Player2REST.entity.Review;
import es.iespuertodelacruz.daniel.Player2REST.entity.Usuario;
import es.iespuertodelacruz.daniel.Player2REST.entity.Videojuego;
import es.iespuertodelacruz.daniel.Player2REST.service.ReviewService;
import es.iespuertodelacruz.daniel.Player2REST.service.UsuarioService;
import es.iespuertodelacruz.daniel.Player2REST.service.VideojuegoService;

@RestController
@RequestMapping("/api/v2/review")
public class ReviewRESTv2 {
	// private Logger logger = (Logger) LoggerFactory.logger(getClass());
	@Autowired
	ReviewService reviewService;
	@Autowired
	VideojuegoService videojuegoService;
	@Autowired
	UsuarioService usuarioService;


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
}
