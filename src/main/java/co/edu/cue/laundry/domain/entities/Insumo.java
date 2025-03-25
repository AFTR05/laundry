package co.edu.cue.laundry.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "insumo")
public class Insumo {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "precio", nullable = false)
    private Double precio;

    @Column(name = "estado", nullable = false)
    private String estado;

    @ManyToOne
    @JoinColumn(name = "id_tipo", referencedColumnName = "id")
    private TipoInsumo tipoInsumo;
}