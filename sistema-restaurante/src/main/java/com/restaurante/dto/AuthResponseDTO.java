package com.restaurante.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponseDTO {

    private String token;
    private Integer idUsuario;
    private String nombre;
    private String correo;
    private String rol;
    private String telefono;
}