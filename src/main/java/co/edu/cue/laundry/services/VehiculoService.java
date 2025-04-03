package co.edu.cue.laundry.services;

import co.edu.cue.laundry.mapping.dtos.*;

import java.util.List;

public interface VehiculoService {
    List<VehiculoDTO> getAllElements();
    VehiculoDTO getOneElement(String id);
    VehiculoDTO createElement(VehiculoRequestDTO element);
    VehiculoDTO updateElement(VehiculoRequestDTO element);
    void deleteElement(String id);
}
