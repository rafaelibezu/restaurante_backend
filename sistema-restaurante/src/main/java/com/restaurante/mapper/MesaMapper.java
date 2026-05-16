package com.restaurante.mapper;

import com.restaurante.dto.MesaRequestDTO;
import com.restaurante.dto.MesaResponseDTO;
import com.restaurante.entity.Mesa;

public class MesaMapper {

    public static Mesa toEntity(MesaRequestDTO dto) {
        return Mesa.builder()
                .numero(dto.getNumero())
                .capacidad(dto.getCapacidad())
                .estado(dto.getEstado() != null ? dto.getEstado() : "Libre")
                .build();
    }

    public static MesaResponseDTO toDTO(Mesa mesa) {
        return MesaResponseDTO.builder()
                .idMesa(mesa.getIdMesa())
                .numero(mesa.getNumero())
                .capacidad(mesa.getCapacidad())
                .estado(mesa.getEstado())
                .build();
    }
}