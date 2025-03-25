package co.edu.cue.laundry.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "mantenimiento")
public class Mantenimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_equipo", referencedColumnName = "id")
    private Equipo equipo;

    @Column(name = "fecha_mantenimiento", nullable = false)
    private Date fechaMantenimiento;

    @Column(name = "tipo_mantenimiento", nullable = false)
    private String tipoMantenimiento;

    @Column(name = "costo", nullable = false)
    private Double costo;
}