package co.edu.cue.laundry.services;

import co.edu.cue.laundry.mapping.dtos.EmpleadoDTO;
import co.edu.cue.laundry.mapping.dtos.EmpleadoRequestDTO;
import co.edu.cue.laundry.mapping.dtos.VehiculoDTO;
import co.edu.cue.laundry.mapping.dtos.VehiculoRequestDTO;

import java.util.List;

public interface VehiculoService {
    List<VehiculoDTO> getAllElements();
    VehiculoDTO getOneElement(String id);
    VehiculoDTO createElement(VehiculoRequestDTO element);
    VehiculoDTO updateElement(VehiculoRequestDTO element);
}
