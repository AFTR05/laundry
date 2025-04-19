package co.edu.cue.laundry.mapping.dtos;

import co.edu.cue.laundry.domain.entities.Insumo;
import co.edu.cue.laundry.domain.entities.Proveedor;

public record OfertaInsumoDTO(
        Long id,
        Double precio,
        Insumo insumo,
        Proveedor proveedor
) {
}
