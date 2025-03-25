package co.edu.cue.laundry.services;

import co.edu.cue.laundry.mapping.dtos.EmpleadoDTO;
import co.edu.cue.laundry.mapping.dtos.EmpleadoRequestDTO;
import co.edu.cue.laundry.mapping.dtos.TipoInsumoDTO;
import co.edu.cue.laundry.mapping.dtos.TipoInsumoRequestDTO;

import java.util.List;

public interface TipoInsumoService {
    List<TipoInsumoDTO> getAllElements();
    TipoInsumoDTO getOneElement(String id);
    TipoInsumoDTO createElement(TipoInsumoRequestDTO element);
    TipoInsumoDTO updateElement(TipoInsumoRequestDTO element);
}
