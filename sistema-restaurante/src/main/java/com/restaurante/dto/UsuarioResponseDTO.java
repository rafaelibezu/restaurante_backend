package com.restaurante.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioResponseDTO {

    private Integer idUsuario;
    private String nombre;
    private String correo;
    private String telefono;
    private Boolean estado;
    private Integer idRol;
    private String nombreRol;
}