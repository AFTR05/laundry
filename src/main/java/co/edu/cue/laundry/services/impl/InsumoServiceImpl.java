package co.edu.cue.laundry.services.impl;

import co.edu.cue.laundry.domain.entities.Empleado;
import co.edu.cue.laundry.domain.entities.Insumo;
import co.edu.cue.laundry.infrastructure.exception.ClienteException;
import co.edu.cue.laundry.infrastructure.exception.EmpleadoException;
import co.edu.cue.laundry.infrastructure.exception.InsumoException;
import co.edu.cue.laundry.infrastructure.exception.TipoInsumoException;
import co.edu.cue.laundry.infrastructure.repository.EmpleadoRepository;
import co.edu.cue.laundry.infrastructure.repository.InsumoRepository;
import co.edu.cue.laundry.infrastructure.repository.TipoInsumoRepository;
import co.edu.cue.laundry.mapping.dtos.InsumoDTO;
import co.edu.cue.laundry.mapping.dtos.InsumoRequestDTO;
import co.edu.cue.laundry.mapping.mappers.EmpleadoMapper;
import co.edu.cue.laundry.mapping.mappers.InsumoMapper;
import co.edu.cue.laundry.services.InsumoService;
import lombok.AllArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
@Service
@AllArgsConstructor
public class InsumoServiceImpl implements InsumoService {

    private final InsumoRepository repository;
    private final TipoInsumoRepository tipoInsumoRepository;

    private final InsumoMapper mapper;

    @Override
    public List<InsumoDTO> getAllElements() {
        return mapper.mapFrom(repository.findAll());
    }

    @Override
    public InsumoDTO getOneElement(String id) {
        return mapper.mapFromEntity(repository.getReferenceById(id));
    }

    @Override
    public InsumoDTO createElement(InsumoRequestDTO element) {
        return tipoInsumoRepository.findById(element.tipoInsumoId())
                .map(tipoInsumo -> {
                    Insumo dataModification = mapper.mapFromRequestDTO(element);
                    dataModification.setTipoInsumo(tipoInsumo);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
                    String id = LocalDateTime.now().format(formatter);
                    dataModification.setId(id);
                    try {
                        Insumo savedProduct = repository.save(dataModification);
                        return mapper.mapFromEntity(savedProduct);
                    } catch (Exception e) {
                        throw new InsumoException("Error al guardar el insumo");
                    }
                })
                .orElseThrow(() -> new TipoInsumoException("Usuario no encontrado"));
    }

    @Override
    public InsumoDTO updateElement(InsumoRequestDTO element) {
        return null;
    }

    @Override
    public void deleteElement(String element) {
        try{
            repository.deleteById(element);
        } catch (Exception e) {
            throw new InsumoException("Error al eliminar el insumo");
        }
    }
}
