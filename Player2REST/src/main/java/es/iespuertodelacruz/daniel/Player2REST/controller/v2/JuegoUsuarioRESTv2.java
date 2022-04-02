package es.iespuertodelacruz.daniel.Player2REST.controller.v2;

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
@RequestMapping("/api/v2/juegousuario")
public class JuegoUsuarioRESTv2 {
	// private Logger logger = (Logger) LoggerFactory.logger(getClass());
	@Autowired
	JuegoUsuarioService juegousuarioService;
	@Autowired
	VideojuegoService videojuegoService;
	@Autowired
	UsuarioService usuarioService;

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		Optional<JuegoUsuario> optM = juegousuarioService.findById(id);
		if (optM.isPresent()) {
			juegousuarioService.deleteById(id);
			return ResponseEntity.ok("juegousuario borrado");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("el id del juegousuario no existe");
		}

	}
	/*

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody JuegoUsuarioDTO juegousuarioIn) {
		Optional<JuegoUsuario> optOp = juegousuarioService.findById(id);
		if (optOp.isPresent()) {
			JuegoUsuario juegousuario = optOp.get();
			juegousuario.setNombre(juegousuarioIn.getNombre());
			juegousuario.setApellidos(juegousuarioIn.getApellidos());
			juegousuario.setNacionalidad(juegousuarioIn.getNacionalidad());
			return ResponseEntity.ok(juegousuarioService.save(juegousuario));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("el id del registro no existe");
		}
	}
*/
}