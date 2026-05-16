package com.restaurante.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VentaResponseDTO {

    private Integer idVenta;
    private LocalDate fecha;
    private BigDecimal total;
    private String metodoPago;
    private String estado;

    private Integer idPedido;
    private Integer idUsuario;
    private String nombreUsuario;
}