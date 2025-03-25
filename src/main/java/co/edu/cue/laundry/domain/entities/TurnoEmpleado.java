package co.edu.cue.laundry.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "turno_empleado")
public class TurnoEmpleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_empleado", referencedColumnName = "id")
    private Empleado empleado;

    @ManyToOne
    @JoinColumn(name = "id_jornada", referencedColumnName = "id")
    private Jornada jornada;

    @Column(name = "dia", nullable = false)
    private Date dia;
}