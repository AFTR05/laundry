package co.edu.cue.laundry.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "comentarios")
public class Comentario {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "description", nullable = false)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "id_empleado", referencedColumnName = "id")
    private Empleado empleado;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "fecha")
    private Date fecha;
}