package com.restaurante.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MesaResponseDTO {

    private Integer idMesa;
    private Integer numero;
    private Integer capacidad;
    private String estado;
}