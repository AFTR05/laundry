package co.edu.cue.laundry.mapping.dtos;

public record ClienteRequestDTO(
        String id,
        String nombre,
        String direccion,
        String distrito,
        String estado
) {
}
