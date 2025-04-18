package co.edu.cue.laundry.services;

import co.edu.cue.laundry.mapping.dtos.CalificacionDTO;
import co.edu.cue.laundry.mapping.dtos.CalificacionRequestDTO;

import java.util.List;

public interface CalificacionService {
    List<CalificacionDTO> getAllElements();
    CalificacionDTO getOneElement(Integer id);
    CalificacionDTO createElement(CalificacionRequestDTO element);
}
