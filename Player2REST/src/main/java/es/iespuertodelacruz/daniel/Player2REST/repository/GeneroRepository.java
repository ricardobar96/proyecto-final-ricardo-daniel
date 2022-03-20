package es.iespuertodelacruz.daniel.Player2REST.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.iespuertodelacruz.daniel.Player2REST.entity.Genero;

public interface GeneroRepository extends JpaRepository <Genero, Integer> {
	
}
