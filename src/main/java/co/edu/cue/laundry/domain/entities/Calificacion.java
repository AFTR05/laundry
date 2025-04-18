package co.edu.cue.laundry.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "calificacion")
public class Calificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "tiempo_espera")
    private Double tiempoEspera;

    @Column(name = "amabilidad")
    private Double amabilidad;

    @Column(name = "calidad")
    private Double calidad;

    @OneToOne
    @JoinColumn(name = "id_servicio", referencedColumnName = "id")
    private Servicio servicio;
}
