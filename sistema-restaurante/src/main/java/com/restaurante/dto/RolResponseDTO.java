package com.restaurante.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RolResponseDTO {

    private Integer idRol;
    private String nombreRol;
    private Boolean estado;
}