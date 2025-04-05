package co.edu.cue.laundry.mapping.mappers;

import co.edu.cue.laundry.domain.entities.Inventario;
import co.edu.cue.laundry.domain.entities.Servicio;
import co.edu.cue.laundry.mapping.dtos.*;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface ServicioMapper {
    ServicioDTO mapFromEntity(Servicio source);
    Servicio mapFromDTO(ServicioDTO source);
    Servicio mapFromRequestDTO(ServicioRequestDTO source);
    Servicio mapFromUpdateDTO(ServicioUpdateDTO source);
    List<ServicioDTO> mapFrom(List<Servicio> source);
    List<Servicio> mapFromListDTO(List<ServicioDTO> source);
}
