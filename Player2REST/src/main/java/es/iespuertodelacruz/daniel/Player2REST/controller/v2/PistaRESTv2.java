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

import es.iespuertodelacruz.daniel.Player2REST.dto.PistaDTO;
import es.iespuertodelacruz.daniel.Player2REST.entity.Pista;
import es.iespuertodelacruz.daniel.Player2REST.entity.Usuario;
import es.iespuertodelacruz.daniel.Player2REST.entity.Videojuego;
import es.iespuertodelacruz.daniel.Player2REST.service.PistaService;
import es.iespuertodelacruz.daniel.Player2REST.service.UsuarioService;
import es.iespuertodelacruz.daniel.Player2REST.service.VideojuegoService;

@RestController
@RequestMapping("/api/v2/pista")
public class PistaRESTv2 {
	// private Logger logger = (Logger) LoggerFactory.logger(getClass());
	@Autowired
	PistaService pistaService;
	@Autowired
	VideojuegoService videojuegoService;
	@Autowired
	UsuarioService usuarioService;

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		Optional<Pista> optM = pistaService.findById(id);
		if (optM.isPresent()) {
			pistaService.deleteById(id);
			return ResponseEntity.ok("pista borrado");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("el id del pista no existe");
		}

	}
	/*

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody PistaDTO pistaIn) {
		Optional<Pista> optOp = pistaService.findById(id);
		if (optOp.isPresent()) {
			Pista pista = optOp.get();
			pista.setNombre(pistaIn.getNombre());
			pista.setApellidos(pistaIn.getApellidos());
			pista.setNacionalidad(pistaIn.getNacionalidad());
			return ResponseEntity.ok(pistaService.save(pista));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("el id del registro no existe");
		}
	}
*/
}
