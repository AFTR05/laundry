package co.edu.cue.laundry.services;

import co.edu.cue.laundry.mapping.dtos.*;

import java.util.List;

public interface ServicioService {
    List<ServicioDTO> getAllElements();
    ServicioDTO getOneElement(Integer id);
    ServicioDTO createElement(ServicioRequestDTO element);
    ServicioDTO updateElement(ServicioUpdateDTO element);
    void deleteElement(Integer id);
}
