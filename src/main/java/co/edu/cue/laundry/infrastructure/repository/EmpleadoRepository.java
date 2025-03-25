package co.edu.cue.laundry.infrastructure.repository;

import co.edu.cue.laundry.domain.entities.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<Empleado, String> {
}
