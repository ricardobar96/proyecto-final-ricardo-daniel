package es.iespuertodelacruz.daniel.Player2REST.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.iespuertodelacruz.daniel.Player2REST.entity.Review;

public interface ReviewRepository extends JpaRepository <Review, Integer> {
	@Query("SELECT t FROM Review t where t.usuario.id = :idusuario")
	 List<Review> findByIdUsuario(@Param("idusuario") Integer idUsuario);
}
