package co.edu.cue.laundry.mapping.mappers;

import co.edu.cue.laundry.domain.entities.Insumo;
import co.edu.cue.laundry.domain.entities.Inventario;
import co.edu.cue.laundry.mapping.dtos.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface InventarioMapper {
    InventarioDTO mapFromEntity(Inventario source);
    Inventario mapFromDTO(InventarioDTO source);
    Inventario mapFromRequestDTO(InventarioRequestDTO source);

    @Mapping(target = "id", source = "source.id")
    Inventario mapFromUpdateDTO(InventarioUpdateDTO source);
    List<InventarioDTO> mapFrom(List<Inventario> source);
    List<Inventario> mapFromListDTO(List<InventarioDTO> source);
}
