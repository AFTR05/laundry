package co.edu.cue.laundry.infrastructure.repository;

import co.edu.cue.laundry.domain.entities.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventarioRepository extends JpaRepository<Inventario, Integer> {
}
