package com.restaurante.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoriaResponseDTO {

    private Integer idCategoria;
    private String nombre;
    private String descripcion;
    private Boolean estado;
}