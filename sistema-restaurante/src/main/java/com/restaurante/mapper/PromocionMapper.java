package com.restaurante.mapper;

import com.restaurante.dto.PromocionRequestDTO;
import com.restaurante.dto.PromocionResponseDTO;
import com.restaurante.entity.Promocion;

public class PromocionMapper {

    public static Promocion toEntity(PromocionRequestDTO dto) {
        return Promocion.builder()
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .tipo(dto.getTipo())
                .valorDescuento(dto.getValorDescuento())
                .fechaInicio(dto.getFechaInicio())
                .fechaFin(dto.getFechaFin())
                .estado(dto.getEstado() != null ? dto.getEstado() : true)
                .build();
    }

    public static PromocionResponseDTO toDTO(Promocion promocion) {
        return PromocionResponseDTO.builder()
                .idPromocion(promocion.getIdPromocion())
                .nombre(promocion.getNombre())
                .descripcion(promocion.getDescripcion())
                .tipo(promocion.getTipo())
                .valorDescuento(promocion.getValorDescuento())
                .fechaInicio(promocion.getFechaInicio())
                .fechaFin(promocion.getFechaFin())
                .estado(promocion.getEstado())
                .build();
    }
}