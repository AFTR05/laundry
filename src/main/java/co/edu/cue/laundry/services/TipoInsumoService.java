package co.edu.cue.laundry.services;

import co.edu.cue.laundry.mapping.dtos.*;

import java.util.List;

public interface TipoInsumoService {
    List<TipoInsumoDTO> getAllElements();
    TipoInsumoDTO getOneElement(String id);
    TipoInsumoDTO createElement(TipoInsumoRequestDTO element);
    TipoInsumoDTO updateElement(TipoInsumoRequestDTO element);
    void deleteElement(String id);
}
