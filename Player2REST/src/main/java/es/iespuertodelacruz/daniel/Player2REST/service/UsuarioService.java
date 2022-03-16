package es.iespuertodelacruz.daniel.Player2REST.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.iespuertodelacruz.daniel.Player2REST.entity.Usuario;
import es.iespuertodelacruz.daniel.Player2REST.repository.UsuarioRepository;


@Service
public class UsuarioService implements GenericService<Usuario, Integer> {
	@Autowired
	UsuarioRepository usuarioRepository;

	@Override
	public Iterable<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

	@Override
	public Page<Usuario> findAll(Pageable pageable) {
		return usuarioRepository.findAll(pageable);
	}

	@Override
	public Optional<Usuario> findById(Integer id) {
		return usuarioRepository.findById(id);
	}

	@Override
	public Usuario save(Usuario object) {
		return usuarioRepository.save(object);
	}

	@Override
	public void deleteById(Integer id) {
		usuarioRepository.deleteById(id);
		
	}

	@Override
	public void delete(Usuario object) {
		usuarioRepository.delete(object);
		
	}
	@Transactional(readOnly=true)
	public Usuario findByNombre(String nombre) {
		Usuario u = null;
		List<Usuario> lista = usuarioRepository.findByNombre(nombre);
		if( lista != null && lista.size() ==1)
			u = lista.get(0);
		return u;
	}
}
