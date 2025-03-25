package co.edu.cue.laundry.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tipo_lavado")
public class TipoLavado {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "estado", nullable = false)
    private String estado;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "id_insumo", referencedColumnName = "id")
    private Insumo insumo;
}