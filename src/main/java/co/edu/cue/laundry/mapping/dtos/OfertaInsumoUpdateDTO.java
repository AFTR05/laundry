package co.edu.cue.laundry.mapping.dtos;

public record OfertaInsumoUpdateDTO(
        Long id,
        Double precio,
        String insumo_id,
        String proveedor_id
) {
}
