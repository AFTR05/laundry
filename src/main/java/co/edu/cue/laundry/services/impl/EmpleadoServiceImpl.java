package co.edu.cue.laundry.services.impl;

import co.edu.cue.laundry.domain.entities.Cliente;
import co.edu.cue.laundry.domain.entities.Empleado;
import co.edu.cue.laundry.infrastructure.exception.ClienteException;
import co.edu.cue.laundry.infrastructure.exception.EmpleadoException;
import co.edu.cue.laundry.infrastructure.repository.ClienteRepository;
import co.edu.cue.laundry.infrastructure.repository.EmpleadoRepository;
import co.edu.cue.laundry.mapping.dtos.ClienteDTO;
import co.edu.cue.laundry.mapping.dtos.ClienteRequestDTO;
import co.edu.cue.laundry.mapping.dtos.EmpleadoDTO;
import co.edu.cue.laundry.mapping.dtos.EmpleadoRequestDTO;
import co.edu.cue.laundry.mapping.mappers.ClienteMapper;
import co.edu.cue.laundry.mapping.mappers.EmpleadoMapper;
import co.edu.cue.laundry.services.EmpleadoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class EmpleadoServiceImpl implements EmpleadoService {

    private final EmpleadoRepository repository;

    private final EmpleadoMapper mapper;


    @Override
    public List<EmpleadoDTO> getAllElements() {
        return mapper.mapFrom(repository.findAll());
    }

    @Override
    public EmpleadoDTO getOneElement(String id) {
        return mapper.mapFromEntity(repository.getReferenceById(id));
    }

    @Override
    public EmpleadoDTO createElement(EmpleadoRequestDTO element) {
        Empleado dataModification = mapper.mapFromRequestDTO(element);
        try {
            Empleado savedReward = repository.save(dataModification);
            return mapper.mapFromEntity(savedReward);
        } catch (Exception e) {
            throw new EmpleadoException("Error al guardar el empleado");
        }
    }

    @Override
    public EmpleadoDTO updateElement(EmpleadoRequestDTO element) {
        return null;
    }
}
