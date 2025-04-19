package co.edu.cue.laundry.services.impl;

import co.edu.cue.laundry.domain.entities.Cliente;
import co.edu.cue.laundry.domain.entities.Proveedor;
import co.edu.cue.laundry.infrastructure.exception.ClienteException;
import co.edu.cue.laundry.infrastructure.exception.ProveedorException;
import co.edu.cue.laundry.infrastructure.repository.ClienteRepository;
import co.edu.cue.laundry.infrastructure.repository.ProveedorRepository;
import co.edu.cue.laundry.mapping.dtos.ProveedorDTO;
import co.edu.cue.laundry.mapping.dtos.ProveedorRequestDTO;
import co.edu.cue.laundry.mapping.mappers.ClienteMapper;
import co.edu.cue.laundry.mapping.mappers.ProveedorMapper;
import co.edu.cue.laundry.services.ProveedorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
@Service
@AllArgsConstructor
public class ProveedorServiceImpl implements ProveedorService {

    private final ProveedorRepository repository;

    private final ProveedorMapper mapper;
    @Override
    public List<ProveedorDTO> getAllElements() {
        return mapper.mapFrom(repository.findAll());
    }

    @Override
    public ProveedorDTO getOneElement(String id) {
        return mapper.mapFromEntity(repository.getReferenceById(id));
    }

    @Override
    public ProveedorDTO createElement(ProveedorRequestDTO element) {
        Proveedor dataModification = mapper.mapFromRequestDTO(element);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String id = LocalDateTime.now().format(formatter);
        dataModification.setId(id);
        try {
            Proveedor savedProveedor= repository.save(dataModification);
            return mapper.mapFromEntity(savedProveedor);
        } catch (Exception e) {
            throw new ProveedorException("Error al guardar el proveedor");
        }
    }

    public ProveedorDTO login(String usuario, String contrasena) {
        return repository.findAll().stream()
                .filter(p -> p.getUsuario().equals(usuario) && p.getContrasena().equals(contrasena))
                .findFirst()
                .map(mapper::mapFromEntity)
                .orElse(null);
    }
}
