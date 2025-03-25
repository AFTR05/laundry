package co.edu.cue.laundry.mapping.mappers;

import co.edu.cue.laundry.domain.entities.TipoLavado;
import co.edu.cue.laundry.mapping.dtos.TipoLavadoDTO;
import co.edu.cue.laundry.mapping.dtos.TipoLavadoRequestDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface TipoLavadoMapper {
    TipoLavadoDTO mapFromEntity(TipoLavado source);
    TipoLavado mapFromDTO(TipoLavadoDTO source);
    TipoLavado mapFromRequestDTO(TipoLavadoRequestDTO source);
    List<TipoLavadoDTO> mapFrom(List<TipoLavado> source);
    List<TipoLavado> mapFromListDTO(List<TipoLavadoDTO> source);
}
