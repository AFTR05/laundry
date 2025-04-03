package co.edu.cue.laundry.services;

import co.edu.cue.laundry.mapping.dtos.*;

import java.util.List;

public interface InsumoService {
    List<InsumoDTO> getAllElements();
    InsumoDTO getOneElement(String id);
    InsumoDTO createElement(InsumoRequestDTO element);
    InsumoDTO updateElement(InsumoRequestDTO element);
    void deleteElement(String id);
}
