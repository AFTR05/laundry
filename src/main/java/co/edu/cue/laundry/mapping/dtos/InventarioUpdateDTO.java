package co.edu.cue.laundry.mapping.dtos;

public record InventarioUpdateDTO(
        Integer id,
        String insumo_id,
        Integer stock,
        String estado
) {
}
