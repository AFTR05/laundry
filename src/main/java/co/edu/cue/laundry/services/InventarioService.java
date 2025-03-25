package co.edu.cue.laundry.services;

import co.edu.cue.laundry.mapping.dtos.EmpleadoDTO;
import co.edu.cue.laundry.mapping.dtos.EmpleadoRequestDTO;
import co.edu.cue.laundry.mapping.dtos.InventarioDTO;
import co.edu.cue.laundry.mapping.dtos.InventarioRequestDTO;

import java.util.List;

public interface InventarioService {
    List<InventarioDTO> getAllElements();
    InventarioDTO getOneElement(Integer id);
    InventarioDTO createElement(InventarioRequestDTO element);
    InventarioDTO updateElement(InventarioRequestDTO element);
}
