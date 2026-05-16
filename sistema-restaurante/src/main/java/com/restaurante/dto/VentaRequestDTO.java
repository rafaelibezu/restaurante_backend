package com.restaurante.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VentaRequestDTO {

    @NotNull(message = "El pedido es obligatorio")
    private Integer idPedido;

    @NotNull(message = "El usuario es obligatorio")
    private Integer idUsuario;

    @NotBlank(message = "El método de pago es obligatorio")
    private String metodoPago;
}