package com.restaurante.mapper;

import com.restaurante.dto.InventarioRequestDTO;
import com.restaurante.dto.InventarioResponseDTO;
import com.restaurante.entity.Inventario;
import com.restaurante.entity.Producto;

import java.time.LocalDateTime;

public class InventarioMapper {

    public static Inventario toEntity(InventarioRequestDTO dto, Producto producto) {
        return Inventario.builder()
                .producto(producto)
                .cantidadActual(dto.getCantidadActual())
                .stockMinimo(dto.getStockMinimo())
                .fechaActualizacion(LocalDateTime.now())
                .build();
    }

    public static InventarioResponseDTO toDTO(Inventario inventario) {
        return InventarioResponseDTO.builder()
                .idInventario(inventario.getIdInventario())
                .idProducto(inventario.getProducto().getIdProducto())
                .nombreProducto(inventario.getProducto().getNombre())
                .cantidadActual(inventario.getCantidadActual())
                .stockMinimo(inventario.getStockMinimo())
                .fechaActualizacion(inventario.getFechaActualizacion())
                .stockBajo(inventario.getCantidadActual() <= inventario.getStockMinimo())
                .build();
    }
}