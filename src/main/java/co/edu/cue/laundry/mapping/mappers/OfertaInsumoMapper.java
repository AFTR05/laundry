package co.edu.cue.laundry.mapping.mappers;

import co.edu.cue.laundry.domain.entities.Cliente;
import co.edu.cue.laundry.domain.entities.OfertaInsumo;
import co.edu.cue.laundry.domain.entities.Servicio;
import co.edu.cue.laundry.mapping.dtos.*;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper(componentModel = "spring")
@Component
public interface OfertaInsumoMapper {
    OfertaInsumoDTO mapFromEntity(OfertaInsumo source);
    OfertaInsumo mapFromDTO(OfertaInsumoDTO source);
    OfertaInsumo mapFromRequestDTO(OfertaInsumoRequestDTO source);
    OfertaInsumo mapFromUpdateDTO(OfertaInsumoUpdateDTO source);
    List<OfertaInsumoDTO> mapFrom(List<OfertaInsumo> source);
    List<OfertaInsumo> mapFromListDTO(List<OfertaInsumoDTO> source);
}
