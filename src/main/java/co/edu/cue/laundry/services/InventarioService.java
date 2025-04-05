package co.edu.cue.laundry.services;

import co.edu.cue.laundry.mapping.dtos.*;

import java.util.List;

public interface InventarioService {
    List<InventarioDTO> getAllElements();
    InventarioDTO getOneElement(Integer id);
    InventarioDTO createElement(InventarioRequestDTO element);
    InventarioDTO updateElement(InventarioUpdateDTO element);
    void deleteElement(Integer id);
}
