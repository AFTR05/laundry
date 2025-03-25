package co.edu.cue.laundry.infrastructure.repository;

import co.edu.cue.laundry.domain.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente,String> {
}
