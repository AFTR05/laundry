package co.edu.cue.laundry.infrastructure.repository;

import co.edu.cue.laundry.domain.entities.OfertaInsumo;
import co.edu.cue.laundry.domain.entities.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfertaInsumoRepository extends JpaRepository<OfertaInsumo, Integer> {
}
