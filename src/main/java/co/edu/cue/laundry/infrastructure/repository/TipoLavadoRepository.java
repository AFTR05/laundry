package co.edu.cue.laundry.infrastructure.repository;

import co.edu.cue.laundry.domain.entities.TipoLavado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoLavadoRepository extends JpaRepository<TipoLavado, String> {
}
