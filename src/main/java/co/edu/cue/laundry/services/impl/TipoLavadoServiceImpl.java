package co.edu.cue.laundry.services.impl;

import co.edu.cue.laundry.domain.entities.Insumo;
import co.edu.cue.laundry.domain.entities.Inventario;
import co.edu.cue.laundry.domain.entities.TipoInsumo;
import co.edu.cue.laundry.domain.entities.TipoLavado;
import co.edu.cue.laundry.infrastructure.exception.InsumoException;
import co.edu.cue.laundry.infrastructure.exception.InventarioException;
import co.edu.cue.laundry.infrastructure.exception.TipoInsumoException;
import co.edu.cue.laundry.infrastructure.exception.TipoLavadoException;
import co.edu.cue.laundry.infrastructure.repository.InsumoRepository;
import co.edu.cue.laundry.infrastructure.repository.TipoInsumoRepository;
import co.edu.cue.laundry.infrastructure.repository.TipoLavadoRepository;
import co.edu.cue.laundry.mapping.dtos.TipoLavadoDTO;
import co.edu.cue.laundry.mapping.dtos.TipoLavadoRequestDTO;
import co.edu.cue.laundry.mapping.mappers.TipoInsumoMapper;
import co.edu.cue.laundry.mapping.mappers.TipoLavadoMapper;
import co.edu.cue.laundry.services.TipoLavadoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@AllArgsConstructor
public class TipoLavadoServiceImpl implements TipoLavadoService {
    private final TipoLavadoRepository repository;
    private final InsumoRepository insumoRepository;

    private final TipoLavadoMapper mapper;

    @Override
    public List<TipoLavadoDTO> getAllElements() {
        return mapper.mapFrom(repository.findAll());
    }

    @Override
    public TipoLavadoDTO getOneElement(String id) {
        return mapper.mapFromEntity(repository.getReferenceById(id));
    }

    @Override
    public TipoLavadoDTO createElement(TipoLavadoRequestDTO element) {
        return insumoRepository.findById(element.insumo_id())
                .map(insumo -> {
                    TipoLavado dataModification = mapper.mapFromRequestDTO(element);
                    dataModification.setInsumo(insumo);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
                    String id = LocalDateTime.now().format(formatter);
                    dataModification.setId(id);
                    try {
                        TipoLavado tipoLavado = repository.save(dataModification);
                        return mapper.mapFromEntity(tipoLavado);
                    } catch (Exception e) {
                        throw new TipoLavadoException("Error al guardar el tipo de lavado");
                    }
                })
                .orElseThrow(() -> new InsumoException("Error al guardar el insumo"));
    }

    @Override
    public TipoLavadoDTO updateElement(TipoLavadoRequestDTO element) {
        return insumoRepository.findById(element.insumo_id())
                .map(insumo -> {
                    TipoLavado dataModification = mapper.mapFromRequestDTO(element);
                    dataModification.setInsumo(insumo);
                    try {
                        TipoLavado savedlavado = repository.save(dataModification);
                        return mapper.mapFromEntity(savedlavado);
                    } catch (Exception e) {
                        throw new TipoLavadoException("Error al guardar el tipo de lavado");
                    }
                })
                .orElseThrow(() -> new InsumoException("insumo no encontrado"));
    }

    @Override
    public void deleteElement(String element) {
        try{
            repository.deleteById(element);
        } catch (Exception e) {
            throw new TipoLavadoException("Error al eliminar el tipo de lavado");
        }
    }
}
