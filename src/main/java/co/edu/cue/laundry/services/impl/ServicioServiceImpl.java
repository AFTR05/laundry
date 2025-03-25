package co.edu.cue.laundry.services.impl;

import co.edu.cue.laundry.domain.entities.*;
import co.edu.cue.laundry.infrastructure.exception.*;
import co.edu.cue.laundry.infrastructure.repository.*;
import co.edu.cue.laundry.mapping.dtos.ServicioDTO;
import co.edu.cue.laundry.mapping.dtos.ServicioRequestDTO;
import co.edu.cue.laundry.mapping.mappers.InventarioMapper;
import co.edu.cue.laundry.mapping.mappers.ServicioMapper;
import co.edu.cue.laundry.services.EmpleadoService;
import co.edu.cue.laundry.services.ServicioService;
import co.edu.cue.laundry.services.TipoLavadoService;
import co.edu.cue.laundry.services.VehiculoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ServicioServiceImpl implements ServicioService {

    private final ServicioRepository repository;
    private final EmpleadoRepository empleadoRepository;
    private final VehiculoRepository vehiculoRepository;
    private final TipoLavadoRepository tipoLavadoRepository;

    private final ServicioMapper mapper;

    @Override
    public List<ServicioDTO> getAllElements() {
        return mapper.mapFrom(repository.findAll());
    }

    @Override
    public ServicioDTO getOneElement(Integer id) {
        return mapper.mapFromEntity(repository.getReferenceById(id));
    }

    @Override
    public ServicioDTO createElement(ServicioRequestDTO element) {
        Empleado empRecibe = empleadoRepository.findById(element.empleadoRecibeId())
                .orElseThrow(() -> new EmpleadoException("Empleado not found"));
        Empleado empLava = empleadoRepository.findById(element.empleadoLavaId())
                .orElseThrow(() -> new EmpleadoException("Empleado not found"));
        Vehiculo vehiculo = vehiculoRepository.findById(element.tipoVehiculoId())
                .orElseThrow(() -> new VehiculoException("Vehiculo not found"));
        TipoLavado tipoLavado = tipoLavadoRepository.findById(element.tipoLavadoId())
                .orElseThrow(() -> new TipoLavadoException("Tipo de lavado not found"));
        Servicio dataModification = mapper.mapFromRequestDTO(element);
        dataModification.setPlaca(vehiculo.getPlaca());
        dataModification.setEmpleadoRecibe(empRecibe);
        dataModification.setEmpleadoLava(empLava);
        dataModification.setTipoVehiculo(vehiculo);
        dataModification.setFecha(Date.from(LocalDate.now().atStartOfDay(ZoneId.of("America/New_York")).toInstant()));
        dataModification.setTipoLavado(tipoLavado);
        try {
            Servicio savedservicio = repository.save(dataModification);
            return mapper.mapFromEntity(savedservicio);
        } catch (Exception e) {
            throw new ServicioException("Error while saving the service");
        }
    }

    @Override
    public ServicioDTO updateElement(ServicioRequestDTO element) {
        return null;
    }
}
