package com.restaurante.mapper;

import com.restaurante.dto.RolResponseDTO;
import com.restaurante.entity.Rol;
import com.restaurante.dto.RolRequestDTO;

public class RolMapper {

    public static RolResponseDTO toDTO(Rol rol) {

        return RolResponseDTO.builder()
                .idRol(rol.getIdRol())
                .nombreRol(rol.getNombreRol())
                .estado(rol.getEstado())
                .build();
    }

    public static Rol toEntity(RolRequestDTO dto) {
        return Rol.builder()
                .nombreRol(dto.getNombreRol())
                .estado(dto.getEstado() != null ? dto.getEstado() : true)
                .build();
    }

}