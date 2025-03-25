package co.edu.cue.laundry.mapping.mappers;

import co.edu.cue.laundry.domain.entities.Cliente;
import co.edu.cue.laundry.mapping.dtos.ClienteDTO;
import co.edu.cue.laundry.mapping.dtos.ClienteRequestDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface ClienteMapper {

    ClienteDTO mapFromEntity(Cliente source);
    Cliente mapFromDTO(ClienteDTO source);
    Cliente mapFromRequestDTO(ClienteRequestDTO source);
    List<ClienteDTO> mapFrom(List<Cliente> source);
    List<Cliente> mapFromListDTO(List<ClienteDTO> source);
}
