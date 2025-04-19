package co.edu.cue.laundry.infrastructure.repository;

import co.edu.cue.laundry.domain.entities.Inventario;
import co.edu.cue.laundry.domain.entities.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProveedorRepository extends JpaRepository<Proveedor, String> {
}
