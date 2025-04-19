package co.edu.cue.laundry.mapping.dtos;

import co.edu.cue.laundry.domain.entities.Insumo;
import co.edu.cue.laundry.domain.entities.Proveedor;

public record OfertaInsumoRequestDTO(
        Double precio,
        String insumo_id,
        String proveedor_id
) {
}
