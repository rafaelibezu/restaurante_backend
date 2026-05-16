package com.restaurante.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventarioResponseDTO {

    private Integer idInventario;
    private Integer idProducto;
    private String nombreProducto;
    private Integer cantidadActual;
    private Integer stockMinimo;
    private LocalDateTime fechaActualizacion;
    private Boolean stockBajo;
}