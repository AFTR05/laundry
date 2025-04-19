package co.edu.cue.laundry.services;

import co.edu.cue.laundry.mapping.dtos.*;

import java.util.List;

public interface OfertaInsumoService {
    List<OfertaInsumoDTO> getAllElements();
    OfertaInsumoDTO getOneElement(Integer id);
    OfertaInsumoDTO createElement(OfertaInsumoRequestDTO element);
    OfertaInsumoDTO updateElement(OfertaInsumoUpdateDTO element);
}
