package co.edu.cue.laundry.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tipo_insumo")
public class TipoInsumo {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "description", nullable = false)
    private String descripcion;

    @Column(name = "estado", nullable = false)
    private String estado;
}