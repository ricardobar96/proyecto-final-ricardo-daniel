package es.iespuertodelacruz.daniel.Player2REST.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.iespuertodelacruz.daniel.Player2REST.entity.Review;
import es.iespuertodelacruz.daniel.Player2REST.repository.ReviewRepository;

@Service
public class ReviewService implements GenericService<Review, Integer> {
	@Autowired
	ReviewRepository reviewRepository;

	@Override
	public Iterable<Review> findAll() {
		return reviewRepository.findAll();
	}

	@Override
	public Page<Review> findAll(Pageable pageable) {
		return reviewRepository.findAll(pageable);
	}

	@Override
	public Optional<Review> findById(Integer id) {
		return reviewRepository.findById(id);
	}

	@Override
	public Review save(Review object) {
		return reviewRepository.save(object);
	}

	@Override
	public void deleteById(Integer id) {
		reviewRepository.deleteById(id);
		
	}

	@Override
	public void delete(Review object) {
		reviewRepository.delete(object);
		
	}
	@Transactional(readOnly=true)
	public Review findByNombre(Integer idUsuario) {
		Review u = null;
		List<Review> lista = reviewRepository.findByIdUsuario(idUsuario);
		if( lista != null && lista.size() ==1)
			u = lista.get(0);
		return u;
	}
}
