package com.restaurante.mapper;

import com.restaurante.dto.ProductoRequestDTO;
import com.restaurante.dto.ProductoResponseDTO;
import com.restaurante.entity.Categoria;
import com.restaurante.entity.Producto;

public class ProductoMapper {

    public static Producto toEntity(ProductoRequestDTO dto, Categoria categoria) {
        return Producto.builder()
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .precio(dto.getPrecio())
                .stock(dto.getStock())
                .estado(dto.getEstado() != null ? dto.getEstado() : true)
                .categoria(categoria)
                .build();
    }

    public static ProductoResponseDTO toDTO(Producto producto) {
        return ProductoResponseDTO.builder()
                .idProducto(producto.getIdProducto())
                .nombre(producto.getNombre())
                .descripcion(producto.getDescripcion())
                .precio(producto.getPrecio())
                .stock(producto.getStock())
                .estado(producto.getEstado())
                .idCategoria(producto.getCategoria().getIdCategoria())
                .nombreCategoria(producto.getCategoria().getNombre())
                .build();
    }
}