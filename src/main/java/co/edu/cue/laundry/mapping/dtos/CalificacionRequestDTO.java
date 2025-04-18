package co.edu.cue.laundry.mapping.dtos;

import co.edu.cue.laundry.domain.entities.Servicio;

public record CalificacionRequestDTO(
        Double tiempoEspera,
        Double amabilidad,
        Double calidad,
        Integer id_servicio
) {
}
