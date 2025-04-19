package co.edu.cue.laundry.domain.entities;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "inventario")
public class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_insumo", referencedColumnName = "id")
    private Insumo insumo;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name = "estado", nullable = false)
    private String estado;
}