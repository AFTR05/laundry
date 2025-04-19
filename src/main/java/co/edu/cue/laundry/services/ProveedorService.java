package co.edu.cue.laundry.services;

import co.edu.cue.laundry.mapping.dtos.InventarioDTO;
import co.edu.cue.laundry.mapping.dtos.InventarioRequestDTO;
import co.edu.cue.laundry.mapping.dtos.ProveedorDTO;
import co.edu.cue.laundry.mapping.dtos.ProveedorRequestDTO;

import java.util.List;

public interface ProveedorService {
    List<ProveedorDTO> getAllElements();
    ProveedorDTO getOneElement(String id);
    ProveedorDTO createElement(ProveedorRequestDTO element);
    ProveedorDTO login(String usuario, String contrasena);
}
