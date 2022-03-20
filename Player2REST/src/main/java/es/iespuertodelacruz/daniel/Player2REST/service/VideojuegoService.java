package es.iespuertodelacruz.daniel.Player2REST.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.iespuertodelacruz.daniel.Player2REST.entity.Videojuego;
import es.iespuertodelacruz.daniel.Player2REST.repository.VideojuegoRepository;


@Service
public class VideojuegoService implements GenericService<Videojuego, Integer> {
	@Autowired
	VideojuegoRepository videojuegoRepository;

	@Override
	public Iterable<Videojuego> findAll() {
		return videojuegoRepository.findAll();
	}

	@Override
	public Page<Videojuego> findAll(Pageable pageable) {
		return videojuegoRepository.findAll(pageable);
	}

	@Override
	public Optional<Videojuego> findById(Integer id) {
		return videojuegoRepository.findById(id);
	}

	@Override
	public Videojuego save(Videojuego object) {
		return videojuegoRepository.save(object);
	}

	@Override
	public void deleteById(Integer id) {
		videojuegoRepository.deleteById(id);
		
	}

	@Override
	public void delete(Videojuego object) {
		videojuegoRepository.delete(object);
		
	}
	@Transactional(readOnly=true)
	public Videojuego findByNombre(String nombre) {
		Videojuego u = null;
		List<Videojuego> lista = videojuegoRepository.findByNombre(nombre);
		if( lista != null && lista.size() ==1)
			u = lista.get(0);
		return u;
	}
}