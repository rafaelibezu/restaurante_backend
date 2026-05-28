package com.restaurante.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservaResponseDTO {

    private Integer idReserva;

    private Integer idCliente;
    private String nombreCliente;

    private Integer idMesa;
    private Integer numeroMesa;

    private Integer idUsuario;
    private String nombreUsuario;

    private LocalDate fechaReserva;
    private LocalTime horaReserva;
    private Integer cantidadPersonas;
    private String estado;
    private String observacion;
    private LocalDateTime fechaCreacion;
}