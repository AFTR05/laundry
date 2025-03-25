package co.edu.cue.laundry.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "equipo")
public class Equipo {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "fecha_adquisicion", nullable = false)
    private Date fechaAdquisicion;

    @Column(name = "estado", nullable = false)
    private String estado;
}