package es.iespuertodelacruz.daniel.Player2REST.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.iespuertodelacruz.daniel.Player2REST.entity.Pista;

public interface PistaRepository extends JpaRepository <Pista, Integer> {
	@Query("SELECT t FROM Pista t where t.usuario.id = :idusuario")
	 List<Pista> findByIdUsuario(@Param("idusuario") Integer idUsuario);
}
