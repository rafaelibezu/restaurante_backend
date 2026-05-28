package com.restaurante.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FidelizacionResponseDTO {

    private Integer idFidelizacion;
    private Integer idCliente;
    private String nombreCliente;
    private Integer puntos;
    private String nivel;
    private BigDecimal descuentoDisponible;
    private LocalDateTime fechaActualizacion;
}