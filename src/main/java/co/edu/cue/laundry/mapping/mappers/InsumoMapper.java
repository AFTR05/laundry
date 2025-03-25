package co.edu.cue.laundry.mapping.mappers;

import co.edu.cue.laundry.domain.entities.Empleado;
import co.edu.cue.laundry.domain.entities.Insumo;
import co.edu.cue.laundry.mapping.dtos.EmpleadoDTO;
import co.edu.cue.laundry.mapping.dtos.EmpleadoRequestDTO;
import co.edu.cue.laundry.mapping.dtos.InsumoDTO;
import co.edu.cue.laundry.mapping.dtos.InsumoRequestDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface InsumoMapper {
    InsumoDTO mapFromEntity(Insumo source);
    Insumo mapFromDTO(InsumoDTO source);
    Insumo mapFromRequestDTO(InsumoRequestDTO source);
    List<InsumoDTO> mapFrom(List<Insumo> source);
    List<Insumo> mapFromListDTO(List<InsumoDTO> source);
}
