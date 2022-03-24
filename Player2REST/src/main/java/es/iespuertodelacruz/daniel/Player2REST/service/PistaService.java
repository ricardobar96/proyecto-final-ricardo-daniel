package es.iespuertodelacruz.daniel.Player2REST.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.iespuertodelacruz.daniel.Player2REST.entity.Pista;
import es.iespuertodelacruz.daniel.Player2REST.repository.PistaRepository;

@Service
public class PistaService implements GenericService<Pista, Integer> {
	@Autowired
	PistaRepository pistaRepository;

	@Override
	public Iterable<Pista> findAll() {
		return pistaRepository.findAll();
	}

	@Override
	public Page<Pista> findAll(Pageable pageable) {
		return pistaRepository.findAll(pageable);
	}

	@Override
	public Optional<Pista> findById(Integer id) {
		return pistaRepository.findById(id);
	}

	@Override
	public Pista save(Pista object) {
		return pistaRepository.save(object);
	}

	@Override
	public void deleteById(Integer id) {
		pistaRepository.deleteById(id);
		
	}

	@Override
	public void delete(Pista object) {
		pistaRepository.delete(object);
		
	}
	@Transactional(readOnly=true)
	public Pista findByNombre(Integer idUsuario) {
		Pista u = null;
		List<Pista> lista = pistaRepository.findByIdUsuario(idUsuario);
		if( lista != null && lista.size() ==1)
			u = lista.get(0);
		return u;
	}
}
