package co.edu.cue.laundry.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "dias_descanso")
public class DiasDescanso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_empleado", referencedColumnName = "id")
    private Empleado empleado;

    @Column(name = "dia_descanso", nullable = false)
    private Date diaDescanso;
}