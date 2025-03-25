package co.edu.cue.laundry.mapping.mappers;

import co.edu.cue.laundry.domain.entities.Servicio;
import co.edu.cue.laundry.domain.entities.TipoInsumo;
import co.edu.cue.laundry.mapping.dtos.ServicioDTO;
import co.edu.cue.laundry.mapping.dtos.ServicioRequestDTO;
import co.edu.cue.laundry.mapping.dtos.TipoInsumoDTO;
import co.edu.cue.laundry.mapping.dtos.TipoInsumoRequestDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface TipoInsumoMapper {
    TipoInsumoDTO mapFromEntity(TipoInsumo source);
    TipoInsumo mapFromDTO(TipoInsumoDTO source);
    TipoInsumo mapFromRequestDTO(TipoInsumoRequestDTO source);
    List<TipoInsumoDTO> mapFrom(List<TipoInsumo> source);
    List<TipoInsumo> mapFromListDTO(List<TipoInsumoDTO> source);
}
