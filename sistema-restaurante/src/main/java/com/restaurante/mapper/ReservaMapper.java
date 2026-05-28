package com.restaurante.mapper;

import com.restaurante.dto.ReservaResponseDTO;
import com.restaurante.entity.Reserva;

public class ReservaMapper {

    public static ReservaResponseDTO toDTO(Reserva reserva) {
        return ReservaResponseDTO.builder()
                .idReserva(reserva.getIdReserva())
                .idCliente(reserva.getCliente().getIdCliente())
                .nombreCliente(reserva.getCliente().getNombre() + " " +
                        (reserva.getCliente().getApellido() != null ? reserva.getCliente().getApellido() : ""))
                .idMesa(reserva.getMesa().getIdMesa())
                .numeroMesa(reserva.getMesa().getNumero())
                .idUsuario(reserva.getUsuario().getIdUsuario())
                .nombreUsuario(reserva.getUsuario().getNombre())
                .fechaReserva(reserva.getFechaReserva())
                .horaReserva(reserva.getHoraReserva())
                .cantidadPersonas(reserva.getCantidadPersonas())
                .estado(reserva.getEstado())
                .observacion(reserva.getObservacion())
                .fechaCreacion(reserva.getFechaCreacion())
                .build();
    }
}