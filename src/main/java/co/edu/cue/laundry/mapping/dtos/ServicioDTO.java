package co.edu.cue.laundry.mapping.dtos;

import co.edu.cue.laundry.domain.entities.Empleado;
import co.edu.cue.laundry.domain.entities.TipoLavado;
import co.edu.cue.laundry.domain.entities.Vehiculo;

import java.time.LocalTime;
import java.util.Date;

public record ServicioDTO(
        Integer id,
        Date fecha,
        Empleado empleadoRecibe,
        Empleado empleadoLava,
        Vehiculo tipoVehiculo,
        TipoLavado tipoLavado,
        LocalTime horaRecibe,
        LocalTime horaEntrega,
        String placa,
        Double precio,
        String estado,
        String motivo
) {
}
