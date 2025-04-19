package co.edu.cue.laundry.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "oferta_insumo")
public class OfertaInsumo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "precio", nullable = false )
    private Double precio;

    @ManyToOne
    @JoinColumn(name = "insumo_id", referencedColumnName = "id")
    private Insumo insumo;

    @ManyToOne
    @JoinColumn(name = "proveedor_id", referencedColumnName = "id")
    private Proveedor proveedor;
}
