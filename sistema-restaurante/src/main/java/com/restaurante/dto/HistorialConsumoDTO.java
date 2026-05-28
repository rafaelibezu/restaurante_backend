package com.restaurante.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistorialConsumoDTO {

    private Integer idCliente;
    private String nombreCliente;
    private Long cantidadPedidos;
    private BigDecimal totalGastado;
    private BigDecimal ticketPromedio;
    private LocalDateTime ultimaVisita;
    private List<PedidoResponseDTO> pedidos;
}