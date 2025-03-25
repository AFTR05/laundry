package co.edu.cue.laundry.infrastructure.repository;

import co.edu.cue.laundry.domain.entities.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehiculoRepository extends JpaRepository<Vehiculo, String> {
}
