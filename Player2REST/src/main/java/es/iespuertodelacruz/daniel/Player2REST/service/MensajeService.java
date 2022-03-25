package es.iespuertodelacruz.daniel.Player2REST.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.iespuertodelacruz.daniel.Player2REST.entity.Mensaje;
import es.iespuertodelacruz.daniel.Player2REST.repository.MensajeRepository;

@Service
public class MensajeService implements GenericService<Mensaje, Integer> {
	@Autowired
	MensajeRepository mensajeRepository;

	@Override
	public Iterable<Mensaje> findAll() {
		return mensajeRepository.findAll();
	}

	@Override
	public Page<Mensaje> findAll(Pageable pageable) {
		return mensajeRepository.findAll(pageable);
	}

	@Override
	public Optional<Mensaje> findById(Integer id) {
		return mensajeRepository.findById(id);
	}

	@Override
	public Mensaje save(Mensaje object) {
		return mensajeRepository.save(object);
	}

	@Override
	public void deleteById(Integer id) {
		mensajeRepository.deleteById(id);
		
	}

	@Override
	public void delete(Mensaje object) {
		mensajeRepository.delete(object);
		
	}
}
