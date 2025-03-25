package co.edu.cue.laundry.mapping.mappers;

import co.edu.cue.laundry.domain.entities.Insumo;
import co.edu.cue.laundry.domain.entities.Inventario;
import co.edu.cue.laundry.mapping.dtos.InsumoDTO;
import co.edu.cue.laundry.mapping.dtos.InsumoRequestDTO;
import co.edu.cue.laundry.mapping.dtos.InventarioDTO;
import co.edu.cue.laundry.mapping.dtos.InventarioRequestDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface InventarioMapper {
    InventarioDTO mapFromEntity(Inventario source);
    Inventario mapFromDTO(InventarioDTO source);
    Inventario mapFromRequestDTO(InventarioRequestDTO source);
    List<InventarioDTO> mapFrom(List<Inventario> source);
    List<Inventario> mapFromListDTO(List<InventarioDTO> source);
}
