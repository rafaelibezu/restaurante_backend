package com.restaurante.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificacionResponseDTO {

    private Integer idNotificacion;
    private Integer idCliente;
    private String nombreCliente;
    private String titulo;
    private String mensaje;
    private String tipo;
    private String estado;
    private LocalDateTime fechaGeneracion;
    private LocalDateTime fechaProgramada;
}