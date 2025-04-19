package co.edu.cue.laundry.services.impl;

import co.edu.cue.laundry.domain.entities.*;
import co.edu.cue.laundry.infrastructure.exception.*;
import co.edu.cue.laundry.infrastructure.repository.InsumoRepository;
import co.edu.cue.laundry.infrastructure.repository.OfertaInsumoRepository;
import co.edu.cue.laundry.infrastructure.repository.ProveedorRepository;
import co.edu.cue.laundry.mapping.dtos.OfertaInsumoDTO;
import co.edu.cue.laundry.mapping.dtos.OfertaInsumoRequestDTO;
import co.edu.cue.laundry.mapping.dtos.OfertaInsumoUpdateDTO;
import co.edu.cue.laundry.mapping.mappers.OfertaInsumoMapper;
import co.edu.cue.laundry.mapping.mappers.ProveedorMapper;
import co.edu.cue.laundry.services.OfertaInsumoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
@Service
@AllArgsConstructor
public class OfertaInsumoServiceImpl implements OfertaInsumoService {

    private final OfertaInsumoRepository repository;
    private final InsumoRepository insumoRepository;
    private final ProveedorRepository proveedorRepository;

    private final OfertaInsumoMapper mapper;

    @Override
    public List<OfertaInsumoDTO> getAllElements() {
        return mapper.mapFrom(repository.findAll());
    }

    @Override
    public OfertaInsumoDTO getOneElement(Integer id) {
        return mapper.mapFromEntity(repository.getReferenceById(id));
    }

    @Override
    public OfertaInsumoDTO createElement(OfertaInsumoRequestDTO element) {
        Insumo insumo = insumoRepository.findById(element.insumo_id())
                .orElseThrow(() -> new InsumoException("insumo not found"));
        Proveedor proveedor = proveedorRepository.findById(element.proveedor_id())
                .orElseThrow(() -> new ProveedorException("proveedor not found"));
        OfertaInsumo dataModification = mapper.mapFromRequestDTO(element);
        dataModification.setInsumo(insumo);
        dataModification.setProveedor(proveedor);
        try {
            OfertaInsumo ofertaInsumo = repository.save(dataModification);
            return mapper.mapFromEntity(ofertaInsumo);
        } catch (Exception e) {
            throw new OfertaInsumoException("Error while saving the service");
        }
    }

    @Override
    public OfertaInsumoDTO updateElement(OfertaInsumoUpdateDTO element) {
        try {
            Insumo insumo = insumoRepository.findById(element.insumo_id())
                    .orElseThrow(() -> new InsumoException("insumo not found"));
            Proveedor proveedor = proveedorRepository.findById(element.proveedor_id())
                    .orElseThrow(() -> new ProveedorException("proveedor not found"));
            OfertaInsumo dataModification = mapper.mapFromUpdateDTO(element);
            dataModification.setInsumo(insumo);
            dataModification.setProveedor(proveedor);
            OfertaInsumo savedOferta = repository.save(dataModification);
            return mapper.mapFromEntity(savedOferta);
        } catch (Exception e) {
            throw new OfertaInsumoException("Error while saving the service");
        }
    }
}
