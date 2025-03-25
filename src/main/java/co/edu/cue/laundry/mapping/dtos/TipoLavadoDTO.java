package co.edu.cue.laundry.mapping.dtos;

import co.edu.cue.laundry.domain.entities.Insumo;

public record TipoLavadoDTO(
        String id,
        String estado,
        String descripcion,
        String nombre,
        Insumo insumo
) {
}
