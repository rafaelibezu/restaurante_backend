package com.restaurante.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteResponseDTO {

    private Integer idCliente;
    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;
    private LocalDate fechaNacimiento;
    private String preferencias;
    private String alergias;
    private LocalDateTime fechaRegistro;
    private LocalDateTime ultimaVisita;
    private Boolean estado;
}