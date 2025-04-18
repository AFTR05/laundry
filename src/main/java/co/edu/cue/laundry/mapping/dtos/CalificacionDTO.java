package co.edu.cue.laundry.mapping.dtos;

import co.edu.cue.laundry.domain.entities.Servicio;

public record CalificacionDTO(
        Long id,
        Double tiempoEspera,
        Double amabilidad,
        Double calidad,
        Servicio servicio
) {
}
