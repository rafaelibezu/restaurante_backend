package com.restaurante.mapper;

import com.restaurante.dto.NotificacionResponseDTO;
import com.restaurante.entity.Notificacion;

public class NotificacionMapper {

    public static NotificacionResponseDTO toDTO(Notificacion notificacion) {
        return NotificacionResponseDTO.builder()
                .idNotificacion(notificacion.getIdNotificacion())
                .idCliente(notificacion.getCliente() != null ? notificacion.getCliente().getIdCliente() : null)
                .nombreCliente(notificacion.getCliente() != null
                        ? notificacion.getCliente().getNombre() + " " +
                        (notificacion.getCliente().getApellido() != null ? notificacion.getCliente().getApellido() : "")
                        : null)
                .titulo(notificacion.getTitulo())
                .mensaje(notificacion.getMensaje())
                .tipo(notificacion.getTipo())
                .estado(notificacion.getEstado())
                .fechaGeneracion(notificacion.getFechaGeneracion())
                .fechaProgramada(notificacion.getFechaProgramada())
                .build();
    }
}