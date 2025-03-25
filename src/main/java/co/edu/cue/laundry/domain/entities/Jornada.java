package co.edu.cue.laundry.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;

@Data
@Entity
@Table(name = "jornada")
public class Jornada {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "estado", nullable = false)
    private String estado;

    @Column(name = "hora_inicio", nullable = false)
    private LocalTime horaInicio;

    @Column(name = "hora_final", nullable = false)
    private LocalTime horaFinal;
}