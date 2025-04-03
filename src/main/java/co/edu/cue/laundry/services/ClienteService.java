package co.edu.cue.laundry.services;

import co.edu.cue.laundry.mapping.dtos.ClienteDTO;
import co.edu.cue.laundry.mapping.dtos.ClienteRequestDTO;
import co.edu.cue.laundry.mapping.dtos.EmpleadoDTO;
import co.edu.cue.laundry.mapping.dtos.EmpleadoRequestDTO;

import java.util.List;

public interface ClienteService {
    List<ClienteDTO> getAllElements();
    ClienteDTO getOneElement(String id);
    ClienteDTO createElement(ClienteRequestDTO element);
    ClienteDTO updateElement(ClienteRequestDTO element);
    void deleteElement(String id);
}
