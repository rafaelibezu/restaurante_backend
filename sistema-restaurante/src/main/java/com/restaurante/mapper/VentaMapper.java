package com.restaurante.mapper;

import com.restaurante.dto.VentaResponseDTO;
import com.restaurante.entity.Venta;

public class VentaMapper {

    public static VentaResponseDTO toDTO(Venta venta) {
        return VentaResponseDTO.builder()
                .idVenta(venta.getIdVenta())
                .fecha(venta.getFecha())
                .total(venta.getTotal())
                .metodoPago(venta.getMetodoPago())
                .estado(venta.getEstado())
                .idPedido(venta.getPedido().getIdPedido())
                .idUsuario(venta.getUsuario().getIdUsuario())
                .nombreUsuario(venta.getUsuario().getNombre())
                .build();
    }
}