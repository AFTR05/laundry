package co.edu.cue.laundry.mapping.dtos;

import co.edu.cue.laundry.domain.entities.Cliente;

public record VehiculoRequestDTO(
        String id,
        String tipo,
        String descripcion,
        String estado,
        String cliente_id,
        String placa
) {
}
