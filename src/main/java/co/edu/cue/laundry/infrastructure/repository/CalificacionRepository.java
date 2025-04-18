package co.edu.cue.laundry.infrastructure.repository;

import co.edu.cue.laundry.domain.entities.Calificacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalificacionRepository extends JpaRepository<Calificacion,Integer> {
}
