package co.edu.cue.laundry.mapping.dtos;

import co.edu.cue.laundry.domain.entities.Insumo;

public record InventarioRequestDTO(
        String insumo_id,
        Integer stock,
        String estado
) {
}
