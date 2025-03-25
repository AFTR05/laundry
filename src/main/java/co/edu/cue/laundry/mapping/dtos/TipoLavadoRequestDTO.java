package co.edu.cue.laundry.mapping.dtos;

import co.edu.cue.laundry.domain.entities.Insumo;

public record TipoLavadoRequestDTO(
        String id,
        String estado,
        String descripcion,
        String nombre,
        String insumo_id
) {
}
