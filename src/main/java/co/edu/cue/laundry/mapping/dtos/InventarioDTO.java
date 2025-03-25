package co.edu.cue.laundry.mapping.dtos;

import co.edu.cue.laundry.domain.entities.Insumo;

public record InventarioDTO(
        Integer id,
        Insumo insumo,
        Integer stock,
        String estado
) {
}
