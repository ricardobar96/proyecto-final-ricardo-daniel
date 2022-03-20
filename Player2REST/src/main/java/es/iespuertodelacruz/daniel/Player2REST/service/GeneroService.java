package es.iespuertodelacruz.daniel.Player2REST.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.iespuertodelacruz.daniel.Player2REST.entity.Genero;
import es.iespuertodelacruz.daniel.Player2REST.repository.GeneroRepository;

@Service
public class GeneroService implements GenericService<Genero, Integer> {
	@Autowired
	GeneroRepository generoRepository;

	@Override
	public Iterable<Genero> findAll() {
		return generoRepository.findAll();
	}

	@Override
	public Page<Genero> findAll(Pageable pageable) {
		return generoRepository.findAll(pageable);
	}

	@Override
	public Optional<Genero> findById(Integer id) {
		return generoRepository.findById(id);
	}

	@Override
	public Genero save(Genero object) {
		return generoRepository.save(object);
	}

	@Override
	public void deleteById(Integer id) {
		generoRepository.deleteById(id);
		
	}

	@Override
	public void delete(Genero object) {
		generoRepository.delete(object);
		
	}
}