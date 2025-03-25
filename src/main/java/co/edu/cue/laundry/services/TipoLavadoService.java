package co.edu.cue.laundry.services;

import co.edu.cue.laundry.mapping.dtos.TipoLavadoDTO;
import co.edu.cue.laundry.mapping.dtos.TipoLavadoRequestDTO;

import java.util.List;

public interface TipoLavadoService {
    List<TipoLavadoDTO> getAllElements();
    TipoLavadoDTO getOneElement(String id);
    TipoLavadoDTO createElement(TipoLavadoRequestDTO element);
    TipoLavadoDTO updateElement(TipoLavadoRequestDTO element);
}
