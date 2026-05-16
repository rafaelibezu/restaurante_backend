package com.restaurante.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MesaRequestDTO {

    @NotNull(message = "El número de mesa es obligatorio")
    private Integer numero;

    @NotNull(message = "La capacidad es obligatoria")
    private Integer capacidad;

    private String estado;
}