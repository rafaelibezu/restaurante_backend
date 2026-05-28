package com.restaurante.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FidelizacionRequestDTO {

    @NotNull(message = "El cliente es obligatorio")
    private Integer idCliente;

    @NotNull(message = "Los puntos son obligatorios")
    @Min(value = 0)
    private Integer puntos;

    private String nivel;

    private BigDecimal descuentoDisponible;
}