package com.restaurante.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoResponseDTO {

    private Integer idPedido;
    private LocalDate fecha;
    private LocalTime hora;
    private String estado;
    private String observacion;
    private BigDecimal total;

    private Integer idMesa;
    private Integer numeroMesa;

    private Integer idUsuario;
    private String nombreUsuario;

    private Integer idCliente;
    private String nombreCliente;

    private List<DetallePedidoResponseDTO> detalles;

}