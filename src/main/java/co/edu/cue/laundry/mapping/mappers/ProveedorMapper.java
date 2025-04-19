package co.edu.cue.laundry.mapping.mappers;

import co.edu.cue.laundry.domain.entities.Cliente;
import co.edu.cue.laundry.domain.entities.Proveedor;
import co.edu.cue.laundry.mapping.dtos.ClienteDTO;
import co.edu.cue.laundry.mapping.dtos.ClienteRequestDTO;
import co.edu.cue.laundry.mapping.dtos.ProveedorDTO;
import co.edu.cue.laundry.mapping.dtos.ProveedorRequestDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface ProveedorMapper {

    ProveedorDTO mapFromEntity(Proveedor source);
    Proveedor mapFromDTO(ProveedorDTO source);
    Proveedor mapFromRequestDTO(ProveedorRequestDTO source);
    List<ProveedorDTO> mapFrom(List<Proveedor> source);
    List<Proveedor> mapFromListDTO(List<ProveedorDTO> source);
}
