package co.edu.cue.laundry.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "proveedor")
public class Proveedor {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "nombre", nullable = false )
    private String nombre;

    @Column(name = "usuario", nullable = false)
    private String usuario;

    @Column(name = "contrasena", nullable = false)
    private String contrasena;

    @Column(name = "celular")
    private String celular;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "correo")
    private String correo;

}
