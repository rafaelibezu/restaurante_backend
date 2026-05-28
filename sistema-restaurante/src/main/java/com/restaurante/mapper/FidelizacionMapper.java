package com.restaurante.mapper;

import com.restaurante.dto.FidelizacionResponseDTO;
import com.restaurante.entity.Fidelizacion;

public class FidelizacionMapper {

    public static FidelizacionResponseDTO toDTO(Fidelizacion fidelizacion) {
        return FidelizacionResponseDTO.builder()
                .idFidelizacion(fidelizacion.getIdFidelizacion())
                .idCliente(fidelizacion.getCliente().getIdCliente())
                .nombreCliente(fidelizacion.getCliente().getNombre() + " " +
                        (fidelizacion.getCliente().getApellido() != null ? fidelizacion.getCliente().getApellido() : ""))
                .puntos(fidelizacion.getPuntos())
                .nivel(fidelizacion.getNivel())
                .descuentoDisponible(fidelizacion.getDescuentoDisponible())
                .fechaActualizacion(fidelizacion.getFechaActualizacion())
                .build();
    }
}