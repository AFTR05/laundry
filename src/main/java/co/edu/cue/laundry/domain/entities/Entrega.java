package co.edu.cue.laundry.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "entrega")
public class Entrega {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre_proveedor")
    private String nombreProveedor;

    @Column(name = "fecha_entrega", nullable = false)
    private Date fechaEntrega;

    @Column(name = "puntualidad", nullable = false)
    private String puntualidad;
}
