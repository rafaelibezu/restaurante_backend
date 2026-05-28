package com.restaurante.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoRequestDTO {

    @NotNull(message = "La mesa es obligatoria")
    private Integer idMesa;

    @NotNull(message = "El usuario es obligatorio")
    private Integer idUsuario;

    private Integer idCliente;

    private String observacion;

    @NotEmpty(message = "El pedido debe tener al menos un producto")
    @Valid
    private List<DetallePedidoRequestDTO> detalles;

}