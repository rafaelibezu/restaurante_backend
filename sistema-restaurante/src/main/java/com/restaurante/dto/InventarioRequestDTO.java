package com.restaurante.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventarioRequestDTO {

    @NotNull(message = "El producto es obligatorio")
    private Integer idProducto;

    @NotNull(message = "La cantidad actual es obligatoria")
    @Min(value = 0)
    private Integer cantidadActual;

    @NotNull(message = "El stock mínimo es obligatorio")
    @Min(value = 0)
    private Integer stockMinimo;
}