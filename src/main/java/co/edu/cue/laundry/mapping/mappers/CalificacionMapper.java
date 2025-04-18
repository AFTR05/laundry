package co.edu.cue.laundry.mapping.mappers;

import co.edu.cue.laundry.domain.entities.Calificacion;
import co.edu.cue.laundry.mapping.dtos.CalificacionDTO;
import co.edu.cue.laundry.mapping.dtos.CalificacionRequestDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper(componentModel = "spring")
@Component
public interface CalificacionMapper {
    CalificacionDTO mapFromEntity(Calificacion source);
    Calificacion mapFromDTO(CalificacionDTO source);
    Calificacion mapFromRequestDTO(CalificacionRequestDTO source);
    List<CalificacionDTO> mapFrom(List<Calificacion> source);
    List<Calificacion> mapFromListDTO(List<CalificacionDTO> source);
}
