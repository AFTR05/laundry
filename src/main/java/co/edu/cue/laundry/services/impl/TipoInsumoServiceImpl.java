package co.edu.cue.laundry.services.impl;

import co.edu.cue.laundry.domain.entities.Cliente;
import co.edu.cue.laundry.domain.entities.Servicio;
import co.edu.cue.laundry.domain.entities.TipoInsumo;
import co.edu.cue.laundry.infrastructure.exception.ClienteException;
import co.edu.cue.laundry.infrastructure.exception.InsumoException;
import co.edu.cue.laundry.infrastructure.exception.ServicioException;
import co.edu.cue.laundry.infrastructure.exception.TipoInsumoException;
import co.edu.cue.laundry.infrastructure.repository.ServicioRepository;
import co.edu.cue.laundry.infrastructure.repository.TipoInsumoRepository;
import co.edu.cue.laundry.mapping.dtos.TipoInsumoDTO;
import co.edu.cue.laundry.mapping.dtos.TipoInsumoRequestDTO;
import co.edu.cue.laundry.mapping.mappers.ServicioMapper;
import co.edu.cue.laundry.mapping.mappers.TipoInsumoMapper;
import co.edu.cue.laundry.services.TipoInsumoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TipoInsumoServiceImpl implements TipoInsumoService {

    private final TipoInsumoRepository repository;

    private final TipoInsumoMapper mapper;

    @Override
    public List<TipoInsumoDTO> getAllElements() {
        return mapper.mapFrom(repository.findAll());
    }

    @Override
    public TipoInsumoDTO getOneElement(String id) {
        return mapper.mapFromEntity(repository.getReferenceById(id));
    }

    @Override
    public TipoInsumoDTO createElement(TipoInsumoRequestDTO element) {
        TipoInsumo dataModification = mapper.mapFromRequestDTO(element);
        try {
            TipoInsumo savedReward = repository.save(dataModification);
            return mapper.mapFromEntity(savedReward);
        } catch (Exception e) {
            throw new TipoInsumoException("Error al guardar el tipo de insumo");
        }
    }

    @Override
    public TipoInsumoDTO updateElement(TipoInsumoRequestDTO element) {
        try{
            TipoInsumo dataModified = mapper.mapFromRequestDTO(element);
            return mapper.mapFromEntity(repository.save(dataModified));
        }catch(Exception e){
            throw new TipoInsumoException("Error al editar el tipo de insumo");
        }
    }

    @Override
    public void deleteElement(String element) {
        try{
            repository.deleteById(element);
        } catch (Exception e) {
            throw new TipoInsumoException("Error al eliminar el tipo de insumo");
        }
    }
}
