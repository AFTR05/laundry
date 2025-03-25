package co.edu.cue.laundry.mapping.dtos;

import co.edu.cue.laundry.domain.entities.Cliente;

public record VehiculoDTO(
        String id,
        String tipo,
        String descripcion,
        String estado,
        Cliente cliente,
        String placa
) {
}
