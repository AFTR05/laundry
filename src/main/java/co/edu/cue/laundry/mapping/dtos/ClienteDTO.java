package co.edu.cue.laundry.mapping.dtos;

import co.edu.cue.laundry.domain.entities.Vehiculo;

import java.util.List;

public record ClienteDTO(
        String id,
        String nombre,
        String direccion,
        String distrito,
        String estado
) {
}
