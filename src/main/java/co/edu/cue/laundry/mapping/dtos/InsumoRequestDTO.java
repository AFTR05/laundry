package co.edu.cue.laundry.mapping.dtos;

import co.edu.cue.laundry.domain.entities.TipoInsumo;

public record InsumoRequestDTO(
        String id,
        String nombre,
        Double precio,
        String estado,
        String tipoInsumoId
) {
}
