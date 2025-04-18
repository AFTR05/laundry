package co.edu.cue.laundry.services.impl;

import co.edu.cue.laundry.domain.entities.Calificacion;
import co.edu.cue.laundry.domain.entities.Inventario;
import co.edu.cue.laundry.infrastructure.exception.CalificacionException;
import co.edu.cue.laundry.infrastructure.exception.InsumoException;
import co.edu.cue.laundry.infrastructure.exception.InventarioException;
import co.edu.cue.laundry.infrastructure.exception.ServicioException;
import co.edu.cue.laundry.infrastructure.repository.CalificacionRepository;
import co.edu.cue.laundry.infrastructure.repository.ClienteRepository;
import co.edu.cue.laundry.infrastructure.repository.ServicioRepository;
import co.edu.cue.laundry.mapping.dtos.CalificacionDTO;
import co.edu.cue.laundry.mapping.dtos.CalificacionRequestDTO;
import co.edu.cue.laundry.mapping.dtos.TipoInsumoDTO;
import co.edu.cue.laundry.mapping.dtos.TipoInsumoRequestDTO;
import co.edu.cue.laundry.mapping.mappers.CalificacionMapper;
import co.edu.cue.laundry.mapping.mappers.ClienteMapper;
import co.edu.cue.laundry.services.CalificacionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class CalificacionServiceImpl implements CalificacionService {
    private final ServicioRepository servicioRepository;
    private final CalificacionRepository repository;

    private final CalificacionMapper mapper;


    @Override
    public List<CalificacionDTO> getAllElements() {
        return mapper.mapFrom(repository.findAll());
    }

    @Override
    public CalificacionDTO getOneElement(Integer id) {
        return mapper.mapFromEntity(repository.getReferenceById(id));
    }

    @Override
    public CalificacionDTO createElement(CalificacionRequestDTO element) {
        return servicioRepository.findById(element.id_servicio())
                .map(servicio -> {
                    Calificacion dataModification = mapper.mapFromRequestDTO(element);
                    dataModification.setServicio(servicio);
                    try {
                        Calificacion savedCalificacion = repository.save(dataModification);
                        return mapper.mapFromEntity(savedCalificacion);
                    } catch (Exception e) {
                        throw new CalificacionException("Error al guardar la calificacion");
                    }
                })
                .orElseThrow(() -> new ServicioException("servicio no encontrado"));
    }
}
