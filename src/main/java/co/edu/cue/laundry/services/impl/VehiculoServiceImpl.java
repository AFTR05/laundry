package co.edu.cue.laundry.services.impl;

import co.edu.cue.laundry.domain.entities.TipoLavado;
import co.edu.cue.laundry.domain.entities.Vehiculo;
import co.edu.cue.laundry.infrastructure.exception.*;
import co.edu.cue.laundry.infrastructure.repository.ClienteRepository;
import co.edu.cue.laundry.infrastructure.repository.TipoLavadoRepository;
import co.edu.cue.laundry.infrastructure.repository.VehiculoRepository;
import co.edu.cue.laundry.mapping.dtos.VehiculoDTO;
import co.edu.cue.laundry.mapping.dtos.VehiculoRequestDTO;
import co.edu.cue.laundry.mapping.mappers.TipoLavadoMapper;
import co.edu.cue.laundry.mapping.mappers.VehiculoMapper;
import co.edu.cue.laundry.services.VehiculoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@AllArgsConstructor
public class VehiculoServiceImpl implements VehiculoService{
    private final VehiculoRepository repository;

    private final VehiculoMapper mapper;
    private final ClienteRepository clienteRepository;

    @Override
    public List<VehiculoDTO> getAllElements() {
        return mapper.mapFrom(repository.findAll());
    }

    @Override
    public VehiculoDTO getOneElement(String id) {
        return mapper.mapFromEntity(repository.getReferenceById(id));
    }

    @Override
    public VehiculoDTO createElement(VehiculoRequestDTO element) {
        return clienteRepository.findById(element.cliente_id())
                .map(cliente -> {
                    Vehiculo dataModification = mapper.mapFromRequestDTO(element);
                    dataModification.setCliente(cliente);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
                    String id = LocalDateTime.now().format(formatter);
                    dataModification.setId(id);
                    try {
                        Vehiculo vehiculo = repository.save(dataModification);
                        return mapper.mapFromEntity(vehiculo);
                    } catch (Exception e) {
                        throw new VehiculoException("Error al guardar el vehiculo");
                    }
                })
                .orElseThrow(() -> new ClienteException("Error al guardar el cliente"));
    }

    @Override
    public VehiculoDTO updateElement(VehiculoRequestDTO element) {
        return null;
    }

    @Override
    public void deleteElement(String element) {
        try{
            repository.deleteById(element);
        } catch (Exception e) {
            throw new VehiculoException("Error al eliminar el vehiculo");
        }
    }
}
