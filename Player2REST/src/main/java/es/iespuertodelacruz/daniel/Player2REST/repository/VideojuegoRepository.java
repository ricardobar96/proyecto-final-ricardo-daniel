package es.iespuertodelacruz.daniel.Player2REST.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.iespuertodelacruz.daniel.Player2REST.entity.Videojuego;

public interface VideojuegoRepository extends JpaRepository <Videojuego, Integer> {
	@Query("SELECT t FROM Videojuego t where t.nombre = :name")
	 List<Videojuego> findByNombre(@Param("name") String strNombre);
	
}
