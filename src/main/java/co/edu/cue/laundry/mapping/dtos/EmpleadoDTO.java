package co.edu.cue.laundry.mapping.dtos;

import java.time.LocalDate;

public record EmpleadoDTO(
        String id,
        String nombre,
        String apellidos,
        LocalDate fechaNacimiento,
        String estado
) {
}
