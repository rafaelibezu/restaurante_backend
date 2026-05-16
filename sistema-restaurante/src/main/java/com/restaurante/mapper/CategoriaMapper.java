package com.restaurante.mapper;

import com.restaurante.dto.CategoriaRequestDTO;
import com.restaurante.dto.CategoriaResponseDTO;
import com.restaurante.entity.Categoria;

public class CategoriaMapper {

    public static Categoria toEntity(CategoriaRequestDTO dto) {
        return Categoria.builder()
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .estado(dto.getEstado() != null ? dto.getEstado() : true)
                .build();
    }

    public static CategoriaResponseDTO toDTO(Categoria categoria) {
        return CategoriaResponseDTO.builder()
                .idCategoria(categoria.getIdCategoria())
                .nombre(categoria.getNombre())
                .descripcion(categoria.getDescripcion())
                .estado(categoria.getEstado())
                .build();
    }
}