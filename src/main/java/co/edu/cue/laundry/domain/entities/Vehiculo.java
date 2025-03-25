package co.edu.cue.laundry.domain.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import  jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "vehiculo")
public class Vehiculo {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "description", nullable = false)
    private String descripcion;

    @Column(name = "estado", nullable = false)
    private String estado;

    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    private Cliente cliente;

    @Column(name = "placa", nullable = false)
    private String placa;
}