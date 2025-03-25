package co.edu.cue.laundry.mapping.dtos;

import co.edu.cue.laundry.domain.entities.TipoInsumo;

public record InsumoDTO(
        String id,
        String nombre,
        Double precio,
        String estado,
        TipoInsumo tipoInsumo
) {
}
