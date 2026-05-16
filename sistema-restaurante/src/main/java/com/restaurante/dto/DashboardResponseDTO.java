package com.restaurante.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardResponseDTO {

    private Long totalPedidos;
    private Long pedidosPendientes;
    private Long pedidosEntregados;

    private Long mesasOcupadas;
    private Long mesasDisponibles;

    private Long productosStockBajo;

    private BigDecimal ventasTotales;
}