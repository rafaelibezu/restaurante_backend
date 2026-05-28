package com.restaurante.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservaRequestDTO {

    @NotNull(message = "El cliente es obligatorio")
    private Integer idCliente;

    @NotNull(message = "La mesa es obligatoria")
    private Integer idMesa;

    @NotNull(message = "El usuario es obligatorio")
    private Integer idUsuario;

    @NotNull(message = "La fecha de reserva es obligatoria")
    private LocalDate fechaReserva;

    @NotNull(message = "La hora de reserva es obligatoria")
    private LocalTime horaReserva;

    @NotNull(message = "La cantidad de personas es obligatoria")
    @Min(value = 1)
    private Integer cantidadPersonas;

    private String estado;

    private String observacion;
}