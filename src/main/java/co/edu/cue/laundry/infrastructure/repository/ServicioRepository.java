package co.edu.cue.laundry.infrastructure.repository;

import co.edu.cue.laundry.domain.entities.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicioRepository extends JpaRepository<Servicio, Integer> {
}
