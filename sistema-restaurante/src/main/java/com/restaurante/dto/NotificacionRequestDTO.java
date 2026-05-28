package com.restaurante.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificacionRequestDTO {

    private Integer idCliente;
    private String titulo;
    private String mensaje;
    private String tipo;
    private String estado;
    private LocalDateTime fechaProgramada;
}