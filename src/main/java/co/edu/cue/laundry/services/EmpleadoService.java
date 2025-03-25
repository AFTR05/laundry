package co.edu.cue.laundry.services;

import co.edu.cue.laundry.mapping.dtos.EmpleadoDTO;
import co.edu.cue.laundry.mapping.dtos.EmpleadoRequestDTO;

import java.util.List;

public interface EmpleadoService {
    List<EmpleadoDTO> getAllElements();
    EmpleadoDTO getOneElement(String id);
    EmpleadoDTO createElement(EmpleadoRequestDTO element);
    EmpleadoDTO updateElement(EmpleadoRequestDTO element);
}
