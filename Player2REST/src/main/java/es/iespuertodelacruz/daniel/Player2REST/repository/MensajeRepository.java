package es.iespuertodelacruz.daniel.Player2REST.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.iespuertodelacruz.daniel.Player2REST.entity.Mensaje;

public interface MensajeRepository extends JpaRepository <Mensaje, Integer> {
}
