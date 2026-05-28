package com.restaurante.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardCrmDTO {

    private Long totalClientes;

    private Long clientesFrecuentes;

    private Long clientesInactivos;

    private Long reservasPendientes;

    private Long promocionesActivas;

    private BigDecimal ticketPromedioGeneral;

    private String clienteTop;

    private BigDecimal totalGastadoClienteTop;

    private Long notificacionesPendientes;
}