package co.edu.cue.laundry.mapping.dtos;

public record ProveedorRequestDTO (
        String nombre,
        String usuario,
        String contrasena,
        String celular,
        String direccion,
        String correo
){
}
