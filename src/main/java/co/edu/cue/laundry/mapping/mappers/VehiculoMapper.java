package co.edu.cue.laundry.mapping.mappers;

import co.edu.cue.laundry.domain.entities.Servicio;
import co.edu.cue.laundry.domain.entities.Vehiculo;
import co.edu.cue.laundry.mapping.dtos.ServicioDTO;
import co.edu.cue.laundry.mapping.dtos.ServicioRequestDTO;
import co.edu.cue.laundry.mapping.dtos.VehiculoDTO;
import co.edu.cue.laundry.mapping.dtos.VehiculoRequestDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface VehiculoMapper {
    VehiculoDTO mapFromEntity(Vehiculo source);
    Vehiculo mapFromDTO(VehiculoDTO source);
    Vehiculo mapFromRequestDTO(VehiculoRequestDTO source);
    List<VehiculoDTO> mapFrom(List<Vehiculo> source);
    List<Vehiculo> mapFromListDTO(List<VehiculoDTO> source);
}
