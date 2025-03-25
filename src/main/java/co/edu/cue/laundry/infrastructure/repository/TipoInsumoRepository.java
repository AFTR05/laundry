package co.edu.cue.laundry.infrastructure.repository;

import co.edu.cue.laundry.domain.entities.TipoInsumo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoInsumoRepository extends JpaRepository<TipoInsumo, String> {
}
