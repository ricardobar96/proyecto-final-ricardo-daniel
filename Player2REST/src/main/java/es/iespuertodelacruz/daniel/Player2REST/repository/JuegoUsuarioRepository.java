package es.iespuertodelacruz.daniel.Player2REST.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.iespuertodelacruz.daniel.Player2REST.entity.JuegoUsuario;
import es.iespuertodelacruz.daniel.Player2REST.entity.Usuario;

public interface JuegoUsuarioRepository extends JpaRepository <JuegoUsuario, Integer> {
	@Query("SELECT t FROM JuegoUsuario t where t.usuario.id = :idusuario")
	 List<JuegoUsuario> findByIdUsuario(@Param("idusuario") Integer idUsuario);
}

