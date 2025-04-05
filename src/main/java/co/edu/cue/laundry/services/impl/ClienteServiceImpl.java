package co.edu.cue.laundry.services.impl;

import co.edu.cue.laundry.domain.entities.Cliente;
import co.edu.cue.laundry.infrastructure.exception.ClienteException;
import co.edu.cue.laundry.infrastructure.repository.ClienteRepository;
import co.edu.cue.laundry.mapping.dtos.ClienteDTO;
import co.edu.cue.laundry.mapping.dtos.ClienteRequestDTO;
import co.edu.cue.laundry.mapping.mappers.ClienteMapper;
import co.edu.cue.laundry.services.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@AllArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository repository;

    private final ClienteMapper mapper;

    @Override
    public List<ClienteDTO> getAllElements() {
        return mapper.mapFrom(repository.findAll());
    }

    @Override
    public ClienteDTO getOneElement(String id) {
        return mapper.mapFromEntity(repository.getReferenceById(id));
    }

    @Override
    public ClienteDTO createElement(ClienteRequestDTO element) {
        Cliente dataModification = mapper.mapFromRequestDTO(element);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String id = LocalDateTime.now().format(formatter);
        dataModification.setId(id);
        dataModification.setEstado("activo");
        try {
            Cliente savedReward = repository.save(dataModification);
            return mapper.mapFromEntity(savedReward);
        } catch (Exception e) {
            throw new ClienteException("Error al guardar el cliente");
        }
    }

    @Override
    public ClienteDTO updateElement(ClienteRequestDTO element) {
        try{
            Cliente dataModified = mapper.mapFromRequestDTO(element);
            return mapper.mapFromEntity(repository.save(dataModified));
        }catch(Exception e){
            throw new ClienteException("Error al editar el cliente");
        }
    }

    @Override
    public void deleteElement(String element) {
        try{
            repository.deleteById(element);
        } catch (Exception e) {
            throw new ClienteException("Error al eliminar el cliente");
        }
    }
}
