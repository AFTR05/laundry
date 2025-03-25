package co.edu.cue.laundry.services;

import co.edu.cue.laundry.mapping.dtos.EmpleadoDTO;
import co.edu.cue.laundry.mapping.dtos.EmpleadoRequestDTO;
import co.edu.cue.laundry.mapping.dtos.InsumoDTO;
import co.edu.cue.laundry.mapping.dtos.InsumoRequestDTO;

import java.util.List;

public interface InsumoService {
    List<InsumoDTO> getAllElements();
    InsumoDTO getOneElement(String id);
    InsumoDTO createElement(InsumoRequestDTO element);
    InsumoDTO updateElement(InsumoRequestDTO element);
}
