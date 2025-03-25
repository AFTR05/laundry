package co.edu.cue.laundry.mapping.dtos;

public record TipoInsumoRequestDTO(
        String id,
        String nombre,
        String descripcion,
        String estado
) {
}
