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
@RequestMapping("/api/v1/pista")
public class PistaREST {
	// private Logger logger = (Logger) LoggerFactory.logger(getClass());
	@Autowired
	PistaService pistaService;
	@Autowired
	VideojuegoService videojuegoService;
	@Autowired
	UsuarioService usuarioService;

	@GetMapping
	public ResponseEntity<?> getAll() {
		List<Pista> l = (List<Pista>) pistaService.findAll();
		List<PistaDTO> listaVid = new ArrayList<>();
		for (Pista pista : l) {
			listaVid.add(new PistaDTO(pista));
		}
		return new ResponseEntity<>(listaVid, HttpStatus.OK);
	}

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

	@GetMapping("/{id}")
	public ResponseEntity<?> getPistaById(@PathVariable("id") Integer id) {

		Optional<Pista> optPista = pistaService.findById(id);
		if (optPista.isPresent()) {
			return ResponseEntity.ok(new PistaDTO(optPista.get()));
		} else {
			return ResponseEntity.notFound().build();
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
	@PostMapping
	public ResponseEntity<?> savePista(@RequestBody PistaDTO pistaDto) {
		
		Pista pista = new Pista();
		Optional<Videojuego> videojuego = videojuegoService.findById(pistaDto.getVideojuego().getId());
		Optional<Usuario> usuario = usuarioService.findById(pistaDto.getUsuario().getId());
		if (videojuego.get() != null && usuario.get() != null) {
			pista.setContenido(pistaDto.getContenido());
			pista.setFecha(BigInteger.valueOf(new Date().getTime()));
			pista.setUsuario(usuario.get());
			pista.setVideojuego(videojuego.get());
			pista.setTitulo(pistaDto.getTitulo());
			Pista pistaC = null;
			try {
				pistaC = pistaService.save(pista);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (pistaC != null) {
				return new ResponseEntity<>(pistaC, HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Ya existe esa combinación de valores (Nick, Password)", HttpStatus.CONFLICT);
			}
		} else{
			return new ResponseEntity<>("No se encuentra el videojuego o el usuario en la bbdd", HttpStatus.CONFLICT);
		}
		

	}
}
