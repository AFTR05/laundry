package co.edu.cue.laundry.mapping.mappers;

import co.edu.cue.laundry.domain.entities.Cliente;
import co.edu.cue.laundry.domain.entities.Empleado;
import co.edu.cue.laundry.mapping.dtos.ClienteDTO;
import co.edu.cue.laundry.mapping.dtos.ClienteRequestDTO;
import co.edu.cue.laundry.mapping.dtos.EmpleadoDTO;
import co.edu.cue.laundry.mapping.dtos.EmpleadoRequestDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface EmpleadoMapper {
    EmpleadoDTO mapFromEntity(Empleado source);
    Empleado mapFromDTO(EmpleadoDTO source);
    Empleado mapFromRequestDTO(EmpleadoRequestDTO source);
    List<EmpleadoDTO> mapFrom(List<Empleado> source);
    List<Empleado> mapFromListDTO(List<EmpleadoDTO> source);
}
