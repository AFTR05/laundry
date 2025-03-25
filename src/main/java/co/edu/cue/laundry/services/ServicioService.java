package co.edu.cue.laundry.services;

import co.edu.cue.laundry.mapping.dtos.EmpleadoDTO;
import co.edu.cue.laundry.mapping.dtos.EmpleadoRequestDTO;
import co.edu.cue.laundry.mapping.dtos.ServicioDTO;
import co.edu.cue.laundry.mapping.dtos.ServicioRequestDTO;

import java.util.List;

public interface ServicioService {
    List<ServicioDTO> getAllElements();
    ServicioDTO getOneElement(Integer id);
    ServicioDTO createElement(ServicioRequestDTO element);
    ServicioDTO updateElement(ServicioRequestDTO element);
}
