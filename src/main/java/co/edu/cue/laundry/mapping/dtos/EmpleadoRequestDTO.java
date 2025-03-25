package co.edu.cue.laundry.mapping.dtos;

import java.time.LocalDate;

public record EmpleadoRequestDTO(
        String id,
        String nombre,
        String apellidos,
        LocalDate fechaNacimiento,
        String estado
) {
}
