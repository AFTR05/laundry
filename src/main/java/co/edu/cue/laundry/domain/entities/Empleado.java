package co.edu.cue.laundry.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "empleado")
public class Empleado {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellidos", nullable = false)
    private String apellidos;

    @Column(name = "fec_nac", nullable = false)
    private LocalDate fechaNacimiento;

    @Column(name = "estado", nullable = false)
    private String estado;
}