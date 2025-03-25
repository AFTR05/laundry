package co.edu.cue.laundry.infrastructure.repository;

import co.edu.cue.laundry.domain.entities.Insumo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsumoRepository extends JpaRepository<Insumo, String> {
}
