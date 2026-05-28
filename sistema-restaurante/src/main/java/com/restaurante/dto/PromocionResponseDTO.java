package com.restaurante.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PromocionResponseDTO {

    private Integer idPromocion;
    private String nombre;
    private String descripcion;
    private String tipo;
    private BigDecimal valorDescuento;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Boolean estado;
}