package co.edu.cue.laundry.mapping.dtos;

import java.time.LocalTime;
import java.util.Date;

public record ServicioRequestDTO(
        Date fecha,
        String empleadoRecibeId,
        String empleadoLavaId,
        String tipoVehiculoId,
        String tipoLavadoId,
        LocalTime horaRecibe,
        LocalTime horaEntrega,
        String placa,
        Double precio,
        String estado,
        String motivo
) {
}
