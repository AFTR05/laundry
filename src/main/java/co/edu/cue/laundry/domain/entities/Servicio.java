package co.edu.cue.laundry.domain.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalTime;
import java.util.Date;

@Data
@Entity
@Table(name = "servicio")
public class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "fecha", nullable = false)
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "id_emp_recibe", referencedColumnName = "id")
    private Empleado empleadoRecibe;

    @ManyToOne
    @JoinColumn(name = "id_emp_lava", referencedColumnName = "id")
    private Empleado empleadoLava;

    @ManyToOne
    @JoinColumn(name = "id_tipovehiculo", referencedColumnName = "id")
    private Vehiculo tipoVehiculo;

    @ManyToOne
    @JoinColumn(name = "id_tipolavado", referencedColumnName = "id")
    private TipoLavado tipoLavado;

    @Column(name = "hora_recibe")
    private LocalTime horaRecibe;

    @Column(name = "hora_entrega")
    private LocalTime horaEntrega;

    @Column(name = "placa", nullable = false)
    private String placa;

    @Column(name = "precio", nullable = false)
    private Double precio;

    @Column(name = "estado", nullable = false)
    private String estado;

    @Column(name = "motivo")
    private String motivo;
}