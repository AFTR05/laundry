package co.edu.cue.laundry.services.impl;

import co.edu.cue.laundry.domain.entities.Empleado;
import co.edu.cue.laundry.domain.entities.Insumo;
import co.edu.cue.laundry.domain.entities.Inventario;
import co.edu.cue.laundry.infrastructure.exception.InsumoException;
import co.edu.cue.laundry.infrastructure.exception.InventarioException;
import co.edu.cue.laundry.infrastructure.exception.TipoInsumoException;
import co.edu.cue.laundry.infrastructure.repository.InsumoRepository;
import co.edu.cue.laundry.infrastructure.repository.InventarioRepository;
import co.edu.cue.laundry.mapping.dtos.InventarioDTO;
import co.edu.cue.laundry.mapping.dtos.InventarioRequestDTO;
import co.edu.cue.laundry.mapping.dtos.InventarioUpdateDTO;
import co.edu.cue.laundry.mapping.mappers.InsumoMapper;
import co.edu.cue.laundry.mapping.mappers.InventarioMapper;
import co.edu.cue.laundry.services.InventarioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@AllArgsConstructor
public class InventarioServiceImpl implements InventarioService {

    private final InventarioRepository repository;
    private final InsumoRepository insumoRepository;

    private final InventarioMapper mapper;

    @Override
    public List<InventarioDTO> getAllElements() {
        return mapper.mapFrom(repository.findAll());
    }

    @Override
    public InventarioDTO getOneElement(Integer id) {
        return mapper.mapFromEntity(repository.getReferenceById(id));
    }

    @Override
    public InventarioDTO createElement(InventarioRequestDTO element) {
        return insumoRepository.findById(element.insumo_id())
                .map(insumo -> {
                    Inventario dataModification = mapper.mapFromRequestDTO(element);
                    dataModification.setInsumo(insumo);
                    try {
                        Inventario savedInventario = repository.save(dataModification);
                        return mapper.mapFromEntity(savedInventario);
                    } catch (Exception e) {
                        throw new InventarioException("Error al guardar el inventario");
                    }
                })
                .orElseThrow(() -> new InsumoException("insumo no encontrado"));
    }

    @Override
    public InventarioDTO updateElement(InventarioUpdateDTO element) {
        return insumoRepository.findById(element.insumo_id())
                .map(object -> {
                    Inventario dataModification = mapper.mapFromUpdateDTO(element);
                    dataModification.setInsumo(object);
                    try {
                        Inventario savedProduct = repository.save(dataModification);
                        return mapper.mapFromEntity(savedProduct);
                    } catch (Exception e) {
                        throw new InventarioException("Error al guardar el inventario");
                    }
                })
                .orElseThrow(() -> new InsumoException("Insumo no encontrado"));
    }

    @Override
    public void deleteElement(Integer element) {
        try{
            repository.deleteById(element);
        } catch (Exception e) {
            throw new InventarioException("Error al eliminar el inventario");
        }
    }
}
