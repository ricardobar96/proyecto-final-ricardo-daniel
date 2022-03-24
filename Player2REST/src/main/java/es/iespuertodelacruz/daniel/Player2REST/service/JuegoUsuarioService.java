package es.iespuertodelacruz.daniel.Player2REST.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.iespuertodelacruz.daniel.Player2REST.entity.JuegoUsuario;
import es.iespuertodelacruz.daniel.Player2REST.repository.JuegoUsuarioRepository;

@Service
public class JuegoUsuarioService implements GenericService<JuegoUsuario, Integer> {
	@Autowired
	JuegoUsuarioRepository juegoUsuarioRepository;

	@Override
	public Iterable<JuegoUsuario> findAll() {
		return juegoUsuarioRepository.findAll();
	}

	@Override
	public Page<JuegoUsuario> findAll(Pageable pageable) {
		return juegoUsuarioRepository.findAll(pageable);
	}

	@Override
	public Optional<JuegoUsuario> findById(Integer id) {
		return juegoUsuarioRepository.findById(id);
	}

	@Override
	public JuegoUsuario save(JuegoUsuario object) {
		return juegoUsuarioRepository.save(object);
	}

	@Override
	public void deleteById(Integer id) {
		juegoUsuarioRepository.deleteById(id);
		
	}

	@Override
	public void delete(JuegoUsuario object) {
		juegoUsuarioRepository.delete(object);
		
	}
	@Transactional(readOnly=true)
	public JuegoUsuario findByNombre(Integer idUsuario) {
		JuegoUsuario u = null;
		List<JuegoUsuario> lista = juegoUsuarioRepository.findByIdUsuario(idUsuario);
		if( lista != null && lista.size() ==1)
			u = lista.get(0);
		return u;
	}
}
